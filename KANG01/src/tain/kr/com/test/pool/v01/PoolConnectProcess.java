package tain.kr.com.test.pool.v01;

import java.util.Date;
import java.util.Random;

public class PoolConnectProcess extends Thread {

	private static boolean flag = true;
	
	private int thrNo = -1;
	private PoolQueue sendQueue = null;
	
	private String thrName = null;
	
	public PoolConnectProcess(int thrNo, PoolQueue sendQueue) {
		
		super(String.format("POOL_CONNECT_PROCESS-%d", thrNo));
		
		if (flag) {
			this.thrNo = thrNo;
			this.sendQueue = sendQueue;
			this.thrName = String.format("POOL_CONNECT_PROCESS-%d", this.thrNo);
		}
	}
	
	public void run() {
		
		if (flag) System.out.printf("POOL STATUS : thread running, name is [%s]\n", this.thrName);
		
		if (flag) {
			try {
				PoolThread poolThread = null;
				Random rand = new Random(new Date().getTime());
				
				while (true) {
					
					// read info from send queue
					poolThread = (PoolThread) this.sendQueue.get();
					if (flag) System.out.printf("                                                  [%s] -> sendQueue -> [%s] : recv info from sendQueue\n", poolThread, this.thrName);
					
					// wait job time
					int waitTime = rand.nextInt(5) + 1;
					if (flag) System.out.printf("                                                                                                  JOB PROCESS : waitTime is %d\n", waitTime);
					try { Thread.sleep(waitTime * 1000); } catch (InterruptedException e) {}
					
					// write message into recv queue by recvMessage function
					poolThread.recvMessage(String.format("MESSAGE OK!!!"));
					if (flag) System.out.printf("                                                  [%s] <- recvQueue <- [%s] : send message into recvQueue \n", poolThread, this.thrName);
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
