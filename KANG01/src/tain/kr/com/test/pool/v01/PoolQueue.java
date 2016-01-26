package tain.kr.com.test.pool.v01;

import java.util.Vector;

public class PoolQueue {

	private static boolean  flag = true;

	private final Vector<Object>   queue;
	private int                    count;

	public PoolQueue() {
		
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
		
		if (!flag) {
			// first source -> out of order -> logic error
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
		}
		
		if (flag) {
			// repair
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
		
		if (!flag) {
			// first source -> out of order -> logic error
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
		}
		
		if (flag) {
			// repair
			try {
				while (true) {
					try {
						wait(timeout);
					} catch (InterruptedException e) {}
					
					break;
				}
				
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
