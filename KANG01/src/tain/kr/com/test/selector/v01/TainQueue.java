package tain.kr.com.test.selector.v01;

import java.util.Vector;

public class TainQueue {

	private static boolean  flag = true;

	private final Vector<Object>   queue;
	private int                    count;

	public TainQueue() {
		
		this.queue = new Vector<Object> (5, 5);
		this.count = 0;
	}

	public synchronized int put(Object object) {
		
		if (flag) {
			try {
				if (object != null) {
					
					this.queue.addElement(object);
					this.count ++;
					
					notifyAll();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return this.count;
	}
	
	public synchronized Object get() throws Exception {
		
		Object object = null;
		
		if (flag) {
			try {
				while (true) {
					try {
						wait();
					} catch (InterruptedException e) {}
					
					if (this.count > 0)
						break;
				}
				
				object = this.queue.elementAt(0);
				this.queue.remove(0);
				this.count --;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return object;
	}
	
	public synchronized Object get(long timeout) throws Exception {
		
		Object object = null;
		
		if (flag) {
			try {
				try {
					wait(timeout);
				} catch (InterruptedException e) {}
				
				if (this.count <= 0) {
					return object;
				}
				
				object = this.queue.elementAt(0);
				this.queue.remove(0);
				this.count --;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
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
