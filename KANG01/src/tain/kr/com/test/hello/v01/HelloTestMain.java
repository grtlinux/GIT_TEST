package tain.kr.com.test.hello.v01;

import org.apache.log4j.Logger;

public class HelloTestMain {

	final static Logger logger = Logger.getLogger(HelloTestMain.class);
	
	private static boolean flag = true;
	
	public static void main(String[] args) throws Exception {
		
		if (flag) {
			if (logger.isDebugEnabled()) {
				logger.debug("ENABLE debug : " + "Hello, world!!! æ»≥Á«œººø‰.");
			}
		}
		
		if (flag) {
			System.out.println("Hello, world!!! æ»≥Á«œººø‰.");
			
			logger.debug("debug : " + "Hello, world!!! æ»≥Á«œººø‰.");
			logger.info ("info  : " + "Hello, world!!! æ»≥Á«œººø‰.");
			logger.warn ("warn  : " + "Hello, world!!! æ»≥Á«œººø‰.");
			logger.error("error : " + "Hello, world!!! æ»≥Á«œººø‰.");
			logger.fatal("fatal : " + "Hello, world!!! æ»≥Á«œººø‰.");
		}
	}
}
