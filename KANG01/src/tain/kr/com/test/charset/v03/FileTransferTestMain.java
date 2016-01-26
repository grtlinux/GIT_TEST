package tain.kr.com.test.charset.v03;

import org.apache.log4j.Logger;

public class FileTransferTestMain {
	
	final static Logger log = Logger.getLogger(FileTransferTestMain.class);
	
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			String rootPath = "L:/src";
			String srcType = "UTF-8";
			String tgtType = "EUC-KR";
			
			new FileFolderProcess(rootPath, srcType, tgtType).execute();
		}
		
		if (!flag) {
			String rootPath = "L:/WORK/workspace/IB_Vela.v02/src";
			String srcType = "UTF-8";
			String tgtType = "EUC-KR";
			
			new FileFolderProcess(rootPath, srcType, tgtType).execute();
		}
	}
	
	///////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////

	public static void main(String[] args) throws Exception {
		
		if (flag) test01(args);
	}
}
