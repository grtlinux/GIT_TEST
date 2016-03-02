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
	
	/**
	 * 
	 * Code Templates > Comments > Methods
	 *
	 * <PRE>
	 *   -. ClassName  : IpAddressTestMain
	 *   -. MethodName : test02
	 *   -. Comment    :
	 *   -. Author     : taincokr
	 *   -. First Date : 2016. 3. 2. {time}
	 * </PRE>
	 *
	 * @param args
	 * @throws Exception
	 * 
	 * [ OUTPUT ]
	 *     (IpAddressTestMain.java:152) - >>>>> tain.kr.com.test.IpAddress.v01.IpAddressTestMain
	 *     (IpAddressTestMain.java:73) - localhost, 127.0.0.1
	 *     (IpAddressTestMain.java:87) - 127.0.0.1
	 *     (IpAddressTestMain.java:102) - 127.000.000.001
	 *     (IpAddressTestMain.java:116) - taincokr-PC, 172.31.16.2
	 *     (IpAddressTestMain.java:130) - 172.31.16.2
	 *     (IpAddressTestMain.java:145) - 172.031.016.002
	 * 
	 */
	private static void test02(String[] args) throws Exception {
		
		if (flag) {
			/*
			 * for test
			 */
			StringBuffer sb = new StringBuffer();
			
			byte[] ip = { -4, -3, -2, -1 };
			for (int i=0; i < ip.length; i++) {
				sb.append((int) ip[i] & 0xFF);   // bug
				if (i != ip.length - 1)
					sb.append(".");
			}
			log.debug(sb.toString());
		}
		
		if (flag) {
			
			InetAddress inetAddress = null;
			
			try {
				inetAddress = InetAddress.getLoopbackAddress();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			log.debug(String.format("%s, %s", inetAddress.getHostName(), inetAddress.getHostAddress()));
			
			if (flag) {
				/*
				 * normal format : 127.0.0.1
				 */
				StringBuffer sb = new StringBuffer();
				
				byte[] ip = inetAddress.getAddress();
				for (int i=0; i < ip.length; i++) {
					sb.append((int) ip[i] & 0xFF);   // bug
					if (i != ip.length - 1)
						sb.append(".");
				}
				log.debug(sb.toString());
			}
			
			if (flag) {
				/*
				 * key format : 999.999.999.999
				 */
				StringBuffer sb = new StringBuffer();
				
				byte[] ip = inetAddress.getAddress();
				for (int i=0; i < ip.length; i++) {
					sb.append(String.format("%03d", (int) ip[i] & 0xFF));   // bug
					if (i != ip.length - 1)
						sb.append(".");
				}
				log.debug(sb.toString());
			}
		}
		
		if (flag) {
			
			InetAddress inetAddress = null;
			
			try {
				inetAddress = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			
			log.debug(String.format("%s, %s", inetAddress.getHostName(), inetAddress.getHostAddress()));
			
			if (flag) {
				/*
				 * normal format : 127.0.0.1
				 */
				StringBuffer sb = new StringBuffer();
				
				byte[] ip = inetAddress.getAddress();
				for (int i=0; i < ip.length; i++) {
					sb.append((int) ip[i] & 0xFF);   // bug
					if (i != ip.length - 1)
						sb.append(".");
				}
				log.debug(sb.toString());
			}
			
			if (flag) {
				/*
				 * key format : 999.999.999.999
				 */
				StringBuffer sb = new StringBuffer();
				
				byte[] ip = inetAddress.getAddress();
				for (int i=0; i < ip.length; i++) {
					sb.append(String.format("%03d", (int) ip[i] & 0xFF));   // bug
					if (i != ip.length - 1)
						sb.append(".");
				}
				log.debug(sb.toString());
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug(">>>>> " + new Object(){}.getClass().getEnclosingClass().getName());
		
		if (!flag) test01(args);
		if (flag) test02(args);
	}
}
