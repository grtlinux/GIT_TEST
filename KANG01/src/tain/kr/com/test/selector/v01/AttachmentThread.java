package tain.kr.com.test.selector.v01;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Set;

public class AttachmentThread extends Thread {

	private static boolean flag = true;
	
	private Charset charset = null;
	@SuppressWarnings("unused")
	private CharsetEncoder encoder = null;
	@SuppressWarnings("unused")
	private CharsetDecoder decoder = null;

	private TainQueue reqQueue = null;
	private TainQueue resQueue = null;
	
	private boolean flagRun = true;
	
	private Selector selector = null;
	private SocketChannel socketChannel = null;
	private SelectionKey selectionKey = null;
	
	public AttachmentThread() throws Exception {
		
		if (flag) {
			this.reqQueue = new TainQueue();
			this.resQueue = new TainQueue();
			this.flagRun = true;

			// Charset encode/decode
			this.charset = Charset.forName("EUC-KR");
			this.encoder = this.charset.newEncoder();
			this.decoder = this.charset.newDecoder();
			
			// Selector
			this.selector = Selector.open();

			// SocketChannel
			while (true) {
				try {
					// this.socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 2025));
					this.socketChannel = SocketChannel.open(new InetSocketAddress("174.100.67.38", 8888));
					this.socketChannel.configureBlocking(false);
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if (flag) System.out.printf("try to connect again.\n");
				if (flag) try { Thread.sleep(2000); } catch (InterruptedException e) {}
			}
			
			// register, and attach
			this.selectionKey = this.socketChannel.register(this.selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
			this.selectionKey.attach("CLIENT CHANNEL");
			
			this.socketChannel = null;
			this.selectionKey = null;
		}
	}
	
	public void run() {
		
		if (flag) {
			
			try {
				
				while (this.flagRun && this.selector.select() > 0) {
					
					Set<SelectionKey> setKeys = this.selector.selectedKeys();
					Iterator<SelectionKey> iterKeys = setKeys.iterator();
					
					while (iterKeys.hasNext()) {
						this.selectionKey = iterKeys.next();
						iterKeys.remove();
						
						SelectableChannel channel = this.selectionKey.channel();
						
						if (channel instanceof SocketChannel) {
							this.socketChannel = (SocketChannel) channel;
							// String attachment = (String) this.selectionKey.attachment();
							
							if (this.selectionKey.isConnectable()) {
								// finish connection and close.
								if (this.socketChannel.isConnectionPending()) {
									this.socketChannel.finishConnect();
									this.socketChannel.close();
									
									if (flag) System.out.printf("ATTACHMENT_THREAD : FINISH, CLOSE\n");

									return;
								}
							}
							
							if (this.selectionKey.isWritable()) {
								// get REQ, and then write REQ -> send
								ByteBuffer reqBuffer = (ByteBuffer) this.reqQueue.get(500);
								if (reqBuffer != null) {
									
									int length = this.socketChannel.write(reqBuffer);
									
									if (!flag) System.out.printf("ATTACHMENT_THREAD : REQ -> SEND (%d) [%s]\n", length, new String(reqBuffer.array()));
								}
							}
							
							if (this.selectionKey.isReadable()) {
								// recv -> read RES, and then put RES
								ByteBuffer resBuffer = ByteBuffer.allocate(4096);
								resBuffer.clear();
								
								int length = this.socketChannel.read(resBuffer);
								if (length > 0) {
									resBuffer.flip();
									
									if (!flag) System.out.printf("ATTACHMENT_THREAD : RES <- RECV (%d) [%s]\n", length, new String(resBuffer.array()));
									
									this.resQueue.put(resBuffer);
								}
							}
						}
					}
					
					if (flag) try { Thread.sleep(500); } catch (InterruptedException e) {}
					
				}  // while (selector.select() > 0)
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (flag) System.out.printf("AttachmentThread is killed.\n");
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	
	public void killThread() {
		if (flag) {
			if (flag) System.out.printf("Kill signal.\n");
			this.flagRun = false;
		}
	}
	
	public void putReqQueue(ByteBuffer reqBuffer) throws Exception {
		if (flag) {
			this.reqQueue.put(reqBuffer);
		}
	}
	
	public ByteBuffer getResQueue() throws Exception {
		
		ByteBuffer resBuffer = null;
		
		if (flag) {
			resBuffer = (ByteBuffer) this.resQueue.get(500);
		}
		
		return resBuffer;
	}
}
