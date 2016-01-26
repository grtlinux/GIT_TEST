package tain.kr.com.test.file.v02;

import org.apache.log4j.Logger;

public class FileInfoTestMain {

	private static final Logger log = Logger.getLogger(FileInfoTestMain.class);
	
	private static boolean flag = true;
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			String basePath = "L:/src.org";
			String timeDiff = "S60";   // 1 minute
			String timeApply = "H48";  // 2 days
			
			new FileInfo(basePath, timeDiff, timeApply).execute();
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug("This is a program to use File class for file information.");
		
		if (flag) test01(args);
	}
}
