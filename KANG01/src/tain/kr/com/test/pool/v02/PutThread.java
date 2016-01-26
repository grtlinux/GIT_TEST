package tain.kr.com.test.pool.v02;

import java.util.Date;
import java.util.Random;

public class PutThread extends Thread {

	private static boolean flag = true;
	
	private int thrNo = -1;
	private String thrName = null;
	private BaseQueue sendQueue = null;
	public BaseQueue recvQueue = null;
	
	private String req = null;
	private String res = null;
	
	public PutThread(int thrNo, BaseQueue sendQueue) {
		super(String.format("PUT_THREAD-%02d", thrNo));
		
		if (flag) {
			this.thrNo = thrNo;
			this.thrName = String.format("PUT_THREAD-%02d", this.thrNo);
			this.sendQueue = sendQueue;
			this.recvQueue = new BaseQueue();
		}
	}
	
	public void run() {
		if (flag) System.out.printf("[%s] thread start..\n", this.thrName);
		
		if (flag) {
			
			Random rand = new Random(new Date().getTime());
			int seq = 0;
			
			try {

				while (true) {
					// wait
					if (flag) try { Thread.sleep((rand.nextInt(3) + 1) * 1000); } catch (InterruptedException e) {}
					
					// make a req message
					putReq(String.format("req message and seq number is %d..", ++seq));
					
					
					// put sendQueue
					this.sendQueue.put(this);
					if (flag) System.out.printf("(%02d) [S->] %s\n", this.thrNo, getReq());
					
					
					// wait a res message from recvQueue, and get a return PutThead by itself.
					this.recvQueue.get();
					if (flag) System.out.printf("(%02d) [<-R] %s\n", this.thrNo, getRes());
					
					
					// wait
					if (flag) try { Thread.sleep((rand.nextInt(5) + 1) * 1000); } catch (InterruptedException e) {}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void putReq(String req) {
		this.req = req;
	}
	
	public String getReq() {
		return this.req;
	}
	
	public void putRes(String res) {
		this.res = res;
	}
	
	public String getRes() {
		return this.res;
	}
	
	public String toString() {
		return this.thrName;
	}
}
