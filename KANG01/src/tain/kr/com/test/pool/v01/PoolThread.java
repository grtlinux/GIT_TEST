package tain.kr.com.test.pool.v01;

import java.util.Date;
import java.util.Random;

public class PoolThread extends Thread {

	private static boolean flag = true;
	
	private int thrNo = -1;
	private String thrName = null;
	
	private PoolQueue sendQueue = null;
	private PoolQueue recvQueue = null;
	
	public PoolThread(int thrNo, PoolQueue sendQueue) {
		
		super(String.format("POOL_THREAD-%d", thrNo));
		
		if (flag) {
			this.thrNo = thrNo;
			this.thrName = String.format("POOL_THREAD-%d", this.thrNo);
			this.sendQueue = sendQueue;
			this.recvQueue = new PoolQueue();
		}
	}
	
	public void run() {
		
		if (flag) System.out.printf("POOL STATUS : thread running, name is [%s]\n", this.thrName);
		
		if (flag) {
			
			Random rand = new Random(new Date().getTime());
			
			try {
				
				for (int i=0; i < 10; i++) {
					
					// send info
					this.sendQueue.put(this);
					if (flag) System.out.printf("[%s] -> sendQueue : (%d) send info\n", this, i);
					
					// recv message
					String retMessage = (String) this.recvQueue.get();
					if (flag) System.out.printf("[%s] <- recvQueue : (%d) recv message [%s]\n", this, i, retMessage);
					
					// wait for some time
					int waitTime = rand.nextInt(3) + 1;
					if (flag) System.out.printf("------------------ [%s] waitTime is %d --------------------------------------------------------------------------\n", this, waitTime);
					try { Thread.sleep(waitTime * 1000); } catch (InterruptedException e) {}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void recvMessage(String str) {
		this.recvQueue.put(str);
	}
	
	public String toString() {
		return this.thrName;
	}
}
