package tain.kr.com.kang02.hello;

public class Kang02 {

	private static boolean flag = true;
	
	private static void test01(String[] args) throws Exception {
		if (flag) {
			System.out.println("Kang02. test01");
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) test01(args);
	}
}
