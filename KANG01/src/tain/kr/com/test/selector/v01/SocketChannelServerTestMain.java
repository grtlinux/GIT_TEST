package tain.kr.com.test.selector.v01;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Set;

public class SocketChannelServerTestMain {
	
	private static boolean flag = true;
	
	private static int PORT = 2345;
	
	private static void test01(String[] args) throws Exception {

		Charset charset = Charset.forName("EUC-KR");
		CharsetEncoder encoder = charset.newEncoder();
		CharsetDecoder decoder = charset.newDecoder();
		
		Selector selector = Selector.open();
		
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(PORT));
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		if (flag) System.out.printf("SERVER : STATUS > wait for a client connection by port %d...\n", PORT);
		
		while (selector.select() > 0) {
			
			Set<SelectionKey> setKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterKeys = setKeys.iterator();
			
			while (iterKeys.hasNext()) {
				
				SelectionKey key = iterKeys.next();
				iterKeys.remove();
				
				SelectableChannel channel = key.channel();
				
				if (channel instanceof ServerSocketChannel) {
					
					if (key.isAcceptable()) {
						
						SocketChannel socketChannel = ((ServerSocketChannel) channel).accept();
						if (socketChannel == null) {
							if (flag) System.out.printf("SERVER : STATUS > null socketChannel\n");
							continue;
						}
						
						socketChannel.configureBlocking(false);
						SelectionKey clientKey = socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
						clientKey.attach("SERVER : STATUS > clientKey.attach\n");
						
						if (flag) System.out.printf("SERVER : STATUS > accepted client socket...." + socketChannel + "\n");
					}
					
				} else if (channel instanceof SocketChannel) {
					
					SocketChannel socketChannel = (SocketChannel) channel;
					
					if (key.isConnectable()) {
						
						if (flag) System.out.printf("SERVER : STATUS > client connection is OK!!.." + socketChannel + "\n");
						
						if (socketChannel.isConnectionPending()) {
							socketChannel.finishConnect();
							socketChannel.close();
							if (flag) System.out.printf("SERVER : STATUS > close socket..\n");
						}
					}
					
					if (key.isReadable()) {
						
						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
						byteBuffer.clear();
						
						int length = socketChannel.read(byteBuffer);
						if (length > 0) {
							byteBuffer.flip();
							
							CharBuffer charBuffer = decoder.decode(byteBuffer);
							
							if (flag) System.out.printf("SERVER : STATUS > recv <- (%d) [%s]\n", length, charBuffer.toString());
						}
						
						if (flag) try { Thread.sleep(100); } catch (InterruptedException e) {}
					}
					
					if (key.isWritable()) {
						
						String msg = "(서버 메시지) 이것은 테스트용 문자열입니다. This is for testing....";
						msg = "This is for testing....";
						
						CharBuffer charBuffer = CharBuffer.wrap(msg);
						ByteBuffer byteBuffer = encoder.encode(charBuffer);
						charBuffer.rewind();
						
						int length = socketChannel.write(byteBuffer);
						byteBuffer.rewind();
						if (length > 0) {
							if (flag) System.out.printf("SERVER : STATUS > send -> (%d) [%s]\n", length, new String(byteBuffer.array(), 0, length)); 
						}
						
						if (flag) try { Thread.sleep(1000); } catch (InterruptedException e) {}
						
					}
					
				} else if (channel instanceof DatagramChannel) {
				
				} else if (channel instanceof Pipe.SinkChannel) {
					
				} else if (channel instanceof Pipe.SourceChannel) {
					
				}
			} // end of while (iterKeys.hasNext())
		} // end of while (selector.select() > 0)
	}
	
	private static void test02(String[] args) throws Exception {
		
		Charset charset = Charset.forName("EUC-KR");
		CharsetEncoder encoder = charset.newEncoder();
		CharsetDecoder decoder = charset.newDecoder();

		Selector selector = Selector.open();
		
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(2345));
		SelectionKey serverKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		serverKey.attach("SERVER SOCKET CHANNEL");
		
		while (selector.select() > 0) {
			
			Set<SelectionKey> setKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterKeys = setKeys.iterator();
			
			while (iterKeys.hasNext()) {
				
				SelectionKey key = iterKeys.next();
				iterKeys.remove();
				
				SelectableChannel channel = key.channel();
				
				if (channel instanceof ServerSocketChannel) {
					
					String channelInfo = (String) key.attachment();
					if (flag) System.out.printf("ServerSocketChannel INFO : %s\n", channelInfo);
					
					if (key.isAcceptable()) {
						SocketChannel socketChannel = ((ServerSocketChannel) channel).accept();
						if (socketChannel == null) {
							if (flag) System.out.printf("null socket...\n");
							continue;
						}
						
						socketChannel.configureBlocking(false);
						SelectionKey clientKey = socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
						clientKey.attach("CLIENT SOCKET CHANNEL");
					}
					
				} else if (channel instanceof SocketChannel) {

					String channelInfo = (String) key.attachment();
					if (flag) System.out.printf("SocketChannel INFO : %s\n", channelInfo);
					
					SocketChannel socketChannel = (SocketChannel) channel;
					
					if (key.isConnectable()) {
						
						if (socketChannel.isConnectionPending()) {
							socketChannel.finishConnect();
							socketChannel.close();
						}
					}
					
					if (key.isReadable()) {
						
						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
						byteBuffer.clear();
						
						int length = socketChannel.read(byteBuffer);
						if (length > 0) {
							byteBuffer.flip();
							
							CharBuffer charBuffer = decoder.decode(byteBuffer);
							
							if (flag) System.out.printf("SERVER : recv <- (%d) [%s]\n", length, charBuffer.toString());
						}
						
					}
					
					if (key.isWritable()) {
						
						String msg = "This is for testing nio";
						
						CharBuffer charBuffer = CharBuffer.wrap(msg);
						ByteBuffer byteBuffer = encoder.encode(charBuffer);
						charBuffer.rewind();
						
						int length = socketChannel.write(byteBuffer);
						if (length > 0) {
							byteBuffer.rewind();
							
							if (flag) System.out.printf("SERVER : send -> (%d) [%s]\n", length, new String(byteBuffer.array(), 0, length));
						}
						
						if (flag) try { Thread.sleep(1000); } catch (InterruptedException e) {}
					}
					
				} else if (channel instanceof DatagramChannel) {
					
				} else if (channel instanceof Pipe.SinkChannel) {
					
				} else if (channel instanceof Pipe.SourceChannel) {
					
				}
			}
		}
	}
	
	private static void test03(String[] args) throws Exception {
		
		// Charset encode/decode
		Charset charset = Charset.forName("EUC-KR");
		CharsetEncoder encoder = charset.newEncoder();
		CharsetDecoder decoder = charset.newDecoder();

		Selector selector = Selector.open();
		
		// ServerSocketChannel
		ServerSocketChannel serverChannel = null;
		serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(2025));
		
		// SelectionKey
		SelectionKey selectionKey = null;
		selectionKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		selectionKey.attach("SERVER SOCKET CHANNEL");
		
		if (flag) System.out.printf("SERVER : STATUS > wait for a client connection by port %d...\n", 2025);
		
		// socketChannel sequence
		int seqChannel = 0;
		
		serverChannel = null;
		selectionKey = null;
		
		SocketChannel socketChannel = null;
		
		while (selector.select() > 0) {
			
			Set<SelectionKey> setKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterKeys = setKeys.iterator();
			
			while (iterKeys.hasNext()) {
				selectionKey = iterKeys.next();
				iterKeys.remove();
				
				SelectableChannel channel = selectionKey.channel();
				
				if (channel instanceof ServerSocketChannel) {
					
					serverChannel = (ServerSocketChannel) channel;
					String strAttachment = (String) selectionKey.attachment();
					if (flag) System.out.printf("SERVER SOCKET CHANNEL ATTACHMENT : %s\n", strAttachment);
					
					if (selectionKey.isAcceptable()) {
						socketChannel = serverChannel.accept();
						if (socketChannel == null) {
							if (flag) System.out.println("null socket .....");
							continue;
						}
						
						socketChannel.configureBlocking(false);
						SelectionKey clientKey = socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
						seqChannel ++;
						clientKey.attach("SOCKET CHANNEL-" + seqChannel);
						clientKey = null;
					}
					
					socketChannel = null;
					
				} else if (channel instanceof SocketChannel) {
					
					socketChannel = (SocketChannel) channel;
					String strAttachment = (String) selectionKey.attachment();
					if (flag) System.out.printf("SOCKET CHANNEL ATTACHMENT : %s\n", strAttachment);
					
					if (selectionKey.isConnectable()) {
						if (socketChannel.isConnectionPending()) {
							socketChannel.finishConnect();
							socketChannel.close();
						}
					}
					
					if (selectionKey.isReadable()) {
						
						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
						byteBuffer.clear();
						
						int length = socketChannel.read(byteBuffer);
						if (length > 0) {
							byteBuffer.flip();
							
							CharBuffer charBuffer = decoder.decode(byteBuffer);
							
							if (flag) System.out.printf("SERVER : recv <- (%d) [%s]\n", length, charBuffer.toString());
						}
					}
					
					if (selectionKey.isWritable()) {
						
						String msg = "This is for testing nio";
						
						CharBuffer charBuffer = CharBuffer.wrap(msg);
						ByteBuffer byteBuffer = encoder.encode(charBuffer);
						charBuffer.rewind();
						
						int length = socketChannel.write(byteBuffer);
						if (length > 0) {
							byteBuffer.rewind();
							
							if (flag) System.out.printf("SERVER : send -> (%d) [%s]\n", length, new String(byteBuffer.array(), 0, length));
						}
						
						if (flag) try { Thread.sleep(1000); } catch (InterruptedException e) {}
					}
					
				} else if (channel instanceof DatagramChannel) {
					
				} else if (channel instanceof Pipe.SinkChannel) {
					
				} else if (channel instanceof Pipe.SourceChannel) {
					
				}
			}
		}
	}
	
	private static void test04(String[] args) throws Exception {
		
		Selector selector = Selector.open();
		
		// ServerSocketChannel
		ServerSocketChannel serverChannel = null;
		serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(2025));
		
		// SelectionKey
		SelectionKey selectionKey = null;
		selectionKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		selectionKey.attach("SERVER SOCKET CHANNEL");
		
		if (flag) System.out.printf("SERVER : STATUS > wait for a client connection by port %d...\n", 2025);
		
		serverChannel = null;
		selectionKey = null;
		
		while (selector.select() > 0) {
			
			Set<SelectionKey> setKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterKeys = setKeys.iterator();
			
			while (iterKeys.hasNext()) {
				selectionKey = iterKeys.next();
				iterKeys.remove();
				
				SelectableChannel channel = selectionKey.channel();
				
				if (channel instanceof ServerSocketChannel) {
					
					// Server Socket Channel
					serverChannel = (ServerSocketChannel) channel;
					if (flag) System.out.printf("SERVER SOCKET CHANNEL ATTACHMENT : %s\n", (String) selectionKey.attachment());
					
					if (selectionKey.isAcceptable()) {
						if (flag) System.out.printf("SERVER SOCKET CHANNEL ATTACHMENT -> ACCEPTABLE : %s\n", (String) selectionKey.attachment());

						SocketChannel socketChannel = serverChannel.accept();
						if (socketChannel == null) {
							if (flag) System.out.println("null socket .....");
							continue;
						}
						
						socketChannel.configureBlocking(false);
						SelectionKey clientKey = socketChannel.register(selector
								, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
						
						// create attachment thread...
						AttachmentThread thread = new AttachmentThread();
						clientKey.attach(thread);
						thread.start();
					}
					
				} else if (channel instanceof SocketChannel) {
					
					try {
						// get socket channel and attachment thread..
						SocketChannel socketChannel = (SocketChannel) channel;
						AttachmentThread attachmentThread = (AttachmentThread) selectionKey.attachment();
						
						if (selectionKey.isConnectable()) {
							
							// connection
							if (flag) System.out.printf("<CONNECTION> selectionKey.isConnectable()");
							if (socketChannel.isConnectionPending()) {
								if (flag) System.out.printf(" -> socketChannel.isConnectionPending()");
								socketChannel.finishConnect();
								socketChannel.close();
								if (flag) System.out.printf(" -> socketChannel.finishConnect().close()");
							}
							if (flag) System.out.printf(" -> end()\n");
						}
						
						if (selectionKey.isReadable()) {
							
							// read REQ, and put REQ
							ByteBuffer reqBuffer = ByteBuffer.allocate(4096);
							reqBuffer.clear();
							
							int length = socketChannel.read(reqBuffer);
							if (length > 0) {
								reqBuffer.flip();
								
								attachmentThread.putReqQueue(reqBuffer);
								
								if (flag) System.out.printf("<READ->PUT> [%s]\n", new String(reqBuffer.array()));
							}
						}
						
						if (selectionKey.isWritable()) {
							
							// get RES, and write RES
							ByteBuffer resBuffer = attachmentThread.getResQueue();
							if (resBuffer != null) {
								resBuffer.flip();
								
								int length = socketChannel.write(resBuffer);
								if (length > 0) {
									resBuffer.rewind();

									if (flag) System.out.printf("<GET->WRITE> [%s]\n", new String(resBuffer.array()));
								}
							}
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				} else if (channel instanceof DatagramChannel) {
				} else if (channel instanceof Pipe.SinkChannel) {
				} else if (channel instanceof Pipe.SourceChannel) {
				}
				
			} // while (iterKeys.hasNext())
			
			if (flag) try { Thread.sleep(1000); } catch (InterruptedException e) {}
			
		} // while (selector.select() > 0)
	}
	
	public static void main(String[] args) throws Exception {
		if (!flag) test01(args);
		if (!flag) test02(args);
		if (!flag) test03(args);
		if (flag) test04(args);
	}
}
