package tain.kr.com.test.pool.v01;

public class PoolTestMain {
	
	private static boolean flag = true;
	
	private static int CNT_POOL_CONNECT_PROCESS = 3;
	private static int CNT_POOL_THREAD = 5;
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			// create the queue to send a message
			PoolQueue sendQueue = new PoolQueue();
			
			// create the pool connect process threads
			for (int i=0; i < CNT_POOL_CONNECT_PROCESS; i++) {
				new PoolConnectProcess(i+1, sendQueue).start();
			}
			
			// create the pool threads
			for (int i=0; i < CNT_POOL_THREAD; i++) {
				new PoolThread(i+1, sendQueue).start();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		
		if (flag) test01(args);
	}
}
