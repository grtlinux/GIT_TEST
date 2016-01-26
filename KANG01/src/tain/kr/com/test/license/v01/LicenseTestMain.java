package tain.kr.com.test.license.v01;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

public class LicenseTestMain {

	private final static Logger log = Logger.getLogger(LicenseTestMain.class);
	
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			
			InetAddress inetAddr = null;
			
			try {
				inetAddr = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			
			log.debug(String.format("%s, %s"
					, inetAddr.getHostName()
					, inetAddr.getHostAddress()
					));
			
			StringBuffer sb = new StringBuffer();
			
			byte[] ip = inetAddr.getAddress();
			for (int i=0; i < ip.length; i++) {
				sb.append((int) ip[i] & 0xFF);   // bug
				if (i != ip.length - 1)
					sb.append(".");
			}
			log.debug(sb.toString());
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug("This is a program to check a license to be able to use....");
		
		if (flag) test01(args);
	}
}
