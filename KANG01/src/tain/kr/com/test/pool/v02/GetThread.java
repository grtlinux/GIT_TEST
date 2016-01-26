package tain.kr.com.test.pool.v02;

import java.util.Date;
import java.util.Random;

public class GetThread extends Thread {

	private static boolean flag = true;
	
	private int thrNo = -1;
	private String thrName = null;
	private BaseQueue sendQueue = null;
	
	public GetThread(int thrNo, BaseQueue sendQueue) {
		super(String.format("GET_THREAD-%02d", thrNo));
		
		if (flag) {
			this.thrNo = thrNo;
			this.thrName = String.format("GET_THREAD-%02d", this.thrNo);
			this.sendQueue = sendQueue;
		}
	}
	
	public void run() {
		if (flag) System.out.printf("[%s] thread start..\n", this.thrName);
		
		if (flag) {
			
			PutThread putThread = null;
			Random rand = new Random(new Date().getTime());
			int seq = 0;
			
			try {

				while (true) {
					// get the object from the sendQueue
					putThread = (PutThread) this.sendQueue.get();
					if (flag) System.out.printf("                                        (%02d) [R->] %s\n", this.thrNo, putThread.getReq());
					
					// wait for job processing
					if (flag) System.out.printf("                                                     -> job processing\n");
					if (flag) try { Thread.sleep((rand.nextInt(10) + 2) * 1000); } catch (InterruptedException e) {}
					
					// make a response message
					putThread.putRes(String.format("res message and seq number is %d", ++seq));
					
					// put a object into recvQueue of itself
					putThread.recvQueue.put(putThread);
					if (flag) System.out.printf("                                        (%02d) [<-S] %s\n", this.thrNo, putThread.getRes());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String toString() {
		return this.thrName;
	}
}
