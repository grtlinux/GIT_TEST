package tain.kr.com.test.sync.v01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SyncThread extends Thread {
	
	private static boolean flag = true;

	private String thrName = null;
	
	private Socket socket = null;

	private TicketManager manager = null;

	public SyncQueue queue = null;

	private BufferedReader in = null;
	private PrintStream out = null;

	public SyncThread(int thrNo, Socket socket, TicketManager manager) {
		
		if (flag) {
			this.thrName = String.format("THR-%06d", thrNo);
			this.socket = socket;
			this.manager = manager;
			this.queue = new SyncQueue();

			try {
				this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				this.out = new PrintStream(socket.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close() {
		
		if (flag) {
			try {
				if (socket != null) {
					socket.close();
					in.close();
					out.close();
				}
			} catch (Exception e) {}
		}
	}
	
	public void run() {
		
		if (flag) {
			try {
				
				if (flag) {
					/*
					 * TODO : REQUEST TICKET
					 * receive request message.
					 */
					String req = null;
					
					while ((req = in.readLine()) != null) {
						if (flag) System.out.printf("[%s] SYNC_THREAD : [%s]\n", this, req);
						break;
					}
					
					/*
					 * request ticket and put into queue
					 */
					manager.queue.put(this);
				}

				// wait
				//if (flag) try { Thread.sleep(1 * 1000); } catch (InterruptedException e) {}
				
				if (flag) {
					/*
					 * TODO : RESPONSE TICKET
					 * send a ticket to the client
					 */
					String res = (String) this.queue.get();
					if (res == null) {
						if (flag) System.out.printf("[%s] SYNC_THREAD : ERROR : SyncThread.queue.get() returns null value..\n", this);
						throw new Exception(String.format("[%s] ERROR : res is null.\n", this));
					}

					out.println(res);
					
					if (flag) System.out.printf("[%s] SYNC_THREAD : [%s]\n", this, res);
				}
				
				// wait
				//if (flag) try { Thread.sleep(1 * 1000); } catch (InterruptedException e) {}
				
				if (flag) {
					/*
					 * TODO : RELEASE TICKET
					 * receive release message and put it into SyncThread
					 */
					String releaseTicket = null;
					
					while ((releaseTicket = in.readLine()) != null) {
						if (flag) System.out.printf("[%s] SYNC_THREAD : [%s]\n", this, releaseTicket);
						break;
					}
					
					this.queue.put(releaseTicket);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		close();
		
		if (flag) System.out.printf("[%s] SYNC_THREAD : SyncThread is closed..\n", this);
	}
	
	public String toString() {
		return this.thrName;
	}
}
