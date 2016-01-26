package tain.kr.com.test.IpAddress.v01;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

public class IpAddressTestMain {

	private static final Logger log = Logger.getLogger(IpAddressTestMain.class);
	
	private static boolean flag = true;
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			InetAddress inetAddress[] = null;
			
			try {
				inetAddress = InetAddress.getAllByName("naver.com");
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			
			for (int i=0; i < inetAddress.length; i++) {
				log.debug(String.format("%s, %s, %s"
						, inetAddress[i].getHostName()
						, inetAddress[i].getHostAddress()
						, inetAddress[i].toString()
						));
			}
		}
		
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
	
	public static void main(String[] args) throws Exception {
		
		if (flag) test01(args);
	}
}
