package tain.kr.com.test.selector.v01;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SocketChannelClientTestMain {

	private static boolean flag = true;
	
	private static void test01(String[] args) throws Exception {
		
		Selector selector = null;
		
		SocketChannel socketChannel = null;
		
		while (true) {
			
			selector = Selector.open();
			socketChannel = null;
			
			while (true) {
				// get a client socket channel to connect to the server.
				try {
					socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 2345));
					break;
				} catch (Exception e) {
					// e.printStackTrace();
					if (flag) System.out.println("CLIENT : STATUS > try to connect to the server...\n");
				}
				
				try { Thread.sleep(10000); } catch (InterruptedException e) {}
			}
			
			socketChannel.configureBlocking(false);
			SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
			
			selectionKey.attach("CLIENT : STATUS > selectionKey.attach\n");
			
			// start to communicate to the server.
			while (selector.select() > 0) {
				
				Set<SelectionKey> setKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterKeys = setKeys.iterator();
				
				while (iterKeys.hasNext()) {
					
					SelectionKey key = iterKeys.next();
					iterKeys.remove();
					
					SelectableChannel channel = key.channel();
					
					if (channel instanceof SocketChannel) {
						
						// about a client socket channel
					
						socketChannel = (SocketChannel) channel;
						String socketChannelName = (String) key.attachment();
						
						if (key.isConnectable()) {
							if (socketChannel.isConnectionPending()) {
								socketChannel.finishConnect();
								socketChannel.close();
								
								if (flag) System.out.printf("CLIENT : STATUS > disconnection to the client. [%s]\n", socketChannelName);
								
								break;
							}
						}
						
						if (key.isReadable()) {
							ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
							byteBuffer.clear();
							
							int length = socketChannel.read(byteBuffer);
							byteBuffer.flip();
							
							if (flag) System.out.printf("CLIENT : STATUS > recv <- (%d) [%s]\n", length, new String(byteBuffer.array()));
						}
						
						if (key.isWritable()) {
							ByteBuffer byteBuffer = ByteBuffer.wrap("1234567890".getBytes());
							
							int length = socketChannel.write(byteBuffer);
							
							if (flag) System.out.printf("CLIENT : STATUS > send -> (%d) [%s]\n", length, new String(byteBuffer.array()));
						}
						
					} // end of if (channel instanceof SocketChannel)
					
				} // end of while (iterKeys.hasNext())
				
				if (flag) try { Thread.sleep(1000); } catch (InterruptedException e) {}
				
			} // end of while (selector.select() > 0)
			
		} // end of while (true)
	}
	
	private static void test02(String[] args) throws Exception {
		
		Selector selector = Selector.open();
		
		SocketChannel socketChannel = null;
		
		while (true) {
			try {
				socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 2345));
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (flag) try { Thread.sleep(5000); } catch (InterruptedException e) {}
		}
		
		socketChannel.configureBlocking(false);
		SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		selectionKey.attach("CLIENT CHANNEL");
		
		while (selector.select() > 0) {
			
			Set<SelectionKey> setKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterKeys = setKeys.iterator();
			
			while (iterKeys.hasNext()) {
				
				SelectionKey key = iterKeys.next();
				iterKeys.remove();
				
				SelectableChannel channel = key.channel();
				
				if (channel instanceof SocketChannel) {
					socketChannel = (SocketChannel) channel;
					String attachment = (String) key.attachment();
					
					if (key.isConnectable()) {
						if (socketChannel.isConnectionPending()) {
							socketChannel.finishConnect();
							socketChannel.close();
							return;
						}
					}
					
					if (key.isReadable()) {
						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
						byteBuffer.clear();
						
						int length = socketChannel.read(byteBuffer);
						byteBuffer.flip();
						
						if (flag) System.out.printf("%s (%d) [%s]\n", attachment, length, new String(byteBuffer.array()));
					}
					
					if (key.isWritable()) {
						ByteBuffer byteBuffer = ByteBuffer.wrap("1234567890".getBytes());
						
						int length = socketChannel.write(byteBuffer);
						
						if (flag) System.out.printf("%s (%d) [%s]\n", attachment, length, new String(byteBuffer.array()));
					}
				}
			}
		}
	}
	
	private static void test03(String[] args) throws Exception {
		
		Selector selector = Selector.open();
		
		SocketChannel socketChannel = null;
		
		while (true) {
			try {
				socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 2025));
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (flag) try { Thread.sleep(2000); } catch (InterruptedException e) {}
		}
		
		socketChannel.configureBlocking(false);
		
		SelectionKey selectionKey = null;
		selectionKey = socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		selectionKey.attach("CLIENT CHANNEL");
		
		socketChannel = null;
		selectionKey = null;
		
		while (selector.select() > 0) {
			
			Set<SelectionKey> setKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterKeys = setKeys.iterator();
			
			while (iterKeys.hasNext()) {
				selectionKey = iterKeys.next();
				iterKeys.remove();
				
				SelectableChannel channel = selectionKey.channel();
				
				if (channel instanceof SocketChannel) {
					socketChannel = (SocketChannel) channel;
					String attachment = (String) selectionKey.attachment();
					
					if (selectionKey.isConnectable()) {
						if (socketChannel.isConnectionPending()) {
							socketChannel.finishConnect();
							socketChannel.close();
							return;
						}
					}
					
					if (selectionKey.isReadable()) {
						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
						byteBuffer.clear();
						
						int length = socketChannel.read(byteBuffer);
						byteBuffer.flip();
						
						if (flag) System.out.printf("%s (%d, args) [%s]\n", attachment, length, new String(byteBuffer.array()));
					}
					
					if (selectionKey.isWritable()) {
						ByteBuffer byteBuffer = ByteBuffer.wrap("1234567890".getBytes());
						
						int length = socketChannel.write(byteBuffer);
						
						if (flag) System.out.printf("CLIENT : STATUS > SEND -> (%d) [%s]\n", length, new String(byteBuffer.array()));
					}
				}
			}
			
			if (flag) try { Thread.sleep(1000); } catch (InterruptedException e) {}
		}
	}
	
	public static void main(String[] args) throws Exception {
		if (!flag) test01(args);
		if (!flag) test02(args);
		if (flag) test03(args);
	}
}
