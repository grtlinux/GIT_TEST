package tain.kr.com.test.rush.v01;

public class RushTestMain {

	private static boolean flag = true;
	
	private static final String COUNT_THREAD = "test.rush.count.thread";

	private PropertiesInfo propInfo = null;
	private int cntThread = -1;
	
	/**
	 * constructor
	 * 
	 * @throws Exception
	 */
	public RushTestMain() throws Exception {
		if (flag) {
			new ValidateOption().validate();
		}
		
		if (flag) {
			this.propInfo = PropertiesInfo.getInstance();
		}
		
		if (flag) {
			String value = System.getProperty(COUNT_THREAD);
			this.cntThread = Integer.parseInt(value);
		}
	}
	
	/**
	 * execute threads
	 * 
	 * @throws Exception
	 */
	public void execute() throws Exception {
		
		if (flag) {
			for (int thrNo=1; thrNo <= cntThread; thrNo++) {
				new RushThread(thrNo, propInfo).start();
			}
		}
	}
	
	/**
	 * testing first
	 * 
	 * @param args
	 * @throws Exception
	 */
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			new RushTestMain().execute();
		}
	}
	
	/**
	 * main entry point
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		if (flag) test01(args);
	}
}
