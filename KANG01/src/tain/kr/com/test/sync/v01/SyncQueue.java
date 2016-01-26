package tain.kr.com.test.sync.v01;

import java.util.Vector;

public class SyncQueue {

	@SuppressWarnings("unused")
	private static boolean  flag = true;

	private final Vector<Object>   queue;
	private int                    count;

	public SyncQueue() {
		
		this.queue = new Vector<Object> (5, 5);
		this.count = 0;
	}

	public synchronized int put(Object object) {
		
		try {
			if (object != null) {
				
				this.queue.addElement(object);
				this.count ++;
				
				notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.count;
	}
	
	public synchronized Object get() throws Exception {
		
		Object object = null;
		
		try {
			for (int i=0; i < 1 && this.count <= 0; i++) {

				try {
					wait();
				} catch (InterruptedException e) {}
			}
			
			if (this.count <= 0) {
				return object;
			}
			
			object = this.queue.elementAt(0);
			this.queue.remove(0);
			this.count --;
		
		//} catch (InterruptedException e) {
		//	throw new InterruptedException("[HANDLE] interrupted Exception by Kang Seok");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return object;
	}
	
	public synchronized Object get(long timeout) throws Exception {
		
		Object object = null;
		
		try {
			for (int i=0; i < 10 && this.count <= 0; i++) {

				try {
					wait(timeout);
				} catch (InterruptedException e) {}
			}
			
			if (this.count <= 0) {
				return object;
			}
			
			object = this.queue.elementAt(0);
			this.queue.remove(0);
			this.count --;
		
		//} catch (InterruptedException e) {
		//	throw new InterruptedException("[HANDLE] interrupted Exception by Kang Seok");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return object;
	}
	
	public synchronized void clear() {
		
		this.queue.removeAllElements();
		this.count = 0;
	}
	
	public int getSize() {
		
		return this.count;
	}
}
