package tain.kr.com.test.pool.v02;

public class TestMain {

	private static boolean flag = true;
	
	private static int CNT_PUT_THREAD = 10;
	private static int CNT_GET_THREAD = 5;
	
	private static void test01(String[] args) {
		if (flag) {
			
			BaseQueue sendQueue = new BaseQueue();
			
			for (int i=0; i < CNT_PUT_THREAD; i++) {
				new PutThread(i+1, sendQueue).start();
			}
			
			for (int i=0; i < CNT_GET_THREAD; i++) {
				new GetThread(i+1, sendQueue).start();
			}
			
			if (flag) System.out.printf("TEST_MAIN process is OK to close..\n");
		}
	}
	
	public static void main(String[] args) {
		if (flag) test01(args);
	}
}
