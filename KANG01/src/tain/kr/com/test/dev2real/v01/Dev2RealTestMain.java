package tain.kr.com.test.dev2real.v01;

import java.io.File;

public class Dev2RealTestMain {

	private static boolean flag = true;
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			Dev2RealInfoBean infoBean = Dev2RealEnvironment.getInstance().getBean();
			
			new Dev2RealJobProcess(infoBean).execute();
		}
	}
	
	private static void test02(String[] args) throws Exception {
		
		if (flag) {
			String pathName1 = "K:/WORK/workspace/IB_Vela.2/lib";
			String fileName1 = "K:/WORK/workspace/IB_Vela.2/lib/IB_Vela.2.131108.jar";
			
			if (flag) {
				File path = new File(pathName1);

				System.out.println("1>" + path.getAbsoluteFile());
				System.out.println("2>" + path.getAbsolutePath());
				System.out.println("3>" + path.getCanonicalPath());
				System.out.println("4>" + path.getCanonicalFile());
				System.out.println("5>" + path.getName());
				System.out.println("6>" + path.getParent());
				System.out.println("7>" + path.getPath());
				System.out.println("8>" + path);
				System.out.println();
			}
			
			if (flag) {
				File file = new File(fileName1);
				
				System.out.println("1>" + file.getAbsoluteFile());
				System.out.println("2>" + file.getAbsolutePath());
				System.out.println("3>" + file.getCanonicalPath());
				System.out.println("4>" + file.getCanonicalFile());
				System.out.println("5>" + file.getName());
				System.out.println("6>" + file.getParent());
				System.out.println("7>" + file.getPath());
				System.out.println("8>" + file);
				System.out.println();
			}
		}
	}
	
	private static void test03(String[] args) throws Exception {
		
		if (flag) {
			String[] arrStr = { "Hello", "time" };
			System.out.println(arrStr);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) test01(args);
		if (!flag) test02(args);
		if (!flag) test03(args);
	}
}
