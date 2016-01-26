package tain.kr.com.test.sync.v01;

public class TicketManager extends Thread {

	private static boolean flag = true;
	
	public SyncQueue queue = null;
	
	public TicketManager() {
		
		// queue
		this.queue = new SyncQueue();
	}
	
	public void run() {
		
		if (flag) {
			
			SyncThread syncThread = null;
			
			try {
				
				while (true) {
					
					if (flag) {
						/*
						 * TODO : REQUEST TICKET
						 *  receive a object of SyncThread
						 */
						syncThread = (SyncThread) this.queue.get();
						if (syncThread == null) {
							if (flag) System.out.printf("[%s] TICKET_MANAGER : ERROR : don't get SyncThread...\n", syncThread);
							if (flag) try { Thread.sleep(10 * 1000); } catch (InterruptedException e) {}
							
							continue;
						}
						
						if (flag) System.out.printf("[%s] TICKET_MANAGER : get message from SyncThread..\n", syncThread);
					}
					
					if (flag) {
						/*
						 * TODO : RESPONSE TICKET
						 *  send ticket usability
						 */
						String res = "RESPONSE TICKET";
						syncThread.queue.put(res);
						if (flag) System.out.printf("[%s] TICKET_MANAGER : put message [%s] into SyncThread queue..\n", syncThread, res);
					}

					
					if (flag) {
						/*
						 * TODO : RELEASE TICKET
						 *  release ticket
						 */
						String releaseTicket = (String) syncThread.queue.get();
						if (releaseTicket == null) {
							if (flag) System.out.printf("[%s] TICKET_MANAGER : ERROR : don't get SyncThread message\n", syncThread);
							if (flag) try { Thread.sleep(1 * 1000); } catch (InterruptedException e) {}

							break;
						}
						
						if (flag) System.out.printf("[%s] TICKET_MANAGER : [%s]\n", syncThread, releaseTicket);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (flag) System.out.printf("TICKET_MANAGER : close and end..\n");
		}
	}
}
