package tain.kr.com.kang01.hello;

import org.apache.log4j.Logger;

public class Hello01 {
	
	private static boolean flag = true;
	
	private static final Logger log = Logger.getLogger(Hello01.class);

	public static void main(String[] args) throws Exception
	{
		// System.out.println("Hello, world!!! KANG01");
		
		if (flag) log.debug("Hello, world!!! KANG01");
	}
}
