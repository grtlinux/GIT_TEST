package tain.kr.com.test.exec.v01;

public class ExecTestMain {

	private static boolean flag = true;
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			Exec.run(new String[] {"cmd", "/c", "dir"});
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) test01(args);
	}
}
