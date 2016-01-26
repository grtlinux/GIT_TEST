package tain.kr.com.test.license.v01;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public enum LicenseType {

	KEY_1 ("1", "9"  ),
	KEY_2 ("2", "8"  ),
	KEY_3 ("3", "7"  ),
	KEY_4 ("4", "6"  ),
	KEY_5 ("5", "5"  ),
	KEY_6 ("6", "4"  ),
	KEY_7 ("7", "3"  ),
	KEY_8 ("8", "2"  ),
	KEY_9 ("9", "1"  ),
	KEY_A ("A", "0"  ),
	KEY_B ("B", "11" ),
	KEY_C ("C", "12" ),
	KEY_K ("K", "-20"),
	KEY_X ("X", "."  ),
	KEY_Y ("Y", "."  ),
	KEY_Z ("Z", "."  ),
	;
	
	private String key;
	private String val;
	
	LicenseType(String key, String val) {
		this.key = key;
		this.val = val;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final Logger log = Logger.getLogger(LicenseType.class);
	
	private static boolean flag = true;
	
	private static Map<String,String> map = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception {
		
		if (!flag) {
			
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
		}
		
		if (!flag) {	
			StringBuffer sb = new StringBuffer();
			
			InetAddress inetAddr = null;
			
			try {
				inetAddr = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

			byte[] ip = inetAddr.getAddress();
			for (int i=0; i < ip.length; i++) {
				sb.append(String.format("%03d", (int) ip[i] & 0xFF));   // bug
				if (i != ip.length - 1)
					sb.append(".");
			}
			log.debug(sb.toString());
		}

		if (!flag) {
			// set map from LicenseType enumeration
			map = new HashMap<String,String>();
			
			for (LicenseType type : LicenseType.values()) {
				map.put(type.getKey(), type.getVal());
			}
			
			if (!flag) {
				// print map
				for (Map.Entry<String,String> entry : map.entrySet()) {
					if (flag) log.debug(String.format("[%s] => [%s]", entry.getKey(), entry.getValue()));
				}
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static void test02(String[] args) throws Exception {
		
		if (flag) {
			
			/*
			 * ############################
			 *  license key
			 */
			String license = null;
			license = "918X942YAAAZAA2K94C8";    // correct [192.168.000.008-201612] 48 8
			license = "AAAX111YAAAZ111K94C9";    // correct : ignore ip check
			//license = "918X942YAAAZAA2K94C84";   // size error
			//license = "918X942YAAAZAA2K94T8";    // wrong license key
			//license = "918X942YAAAZAA1K94C7";    // wrong ip address
			//license = "918X942YAAAZAA2K94C9";    // crc error
			//license = "918X942YAAAZAA2K95C9";    // expire date error
			
			if (flag) {
				/*
				 * 1. check size of license
				 */
				if (license.length() != 20) {
					throw new Exception("ERROR : error for checking the size of license");
				}
			}
			
			if (flag) {
				/*
				 * 2. set map from LicenseType enumeration
				 */
				map = new HashMap<String,String>();
				
				for (LicenseType type : LicenseType.values()) {
					map.put(type.getKey(), type.getVal());
				}
				
				if (!flag) {
					// print map
					for (Map.Entry<String,String> entry : map.entrySet()) {
						if (flag) log.debug(String.format("[%s] => [%s]", entry.getKey(), entry.getValue()));
					}
				}
			}

			String key = license.substring(0, 19);
			String crc = license.substring(19);
			if (!flag) log.debug(String.format("[%s] = [%s] [%s]", license, key, crc));

			StringBuffer sbInfo = new StringBuffer();
			int crcDigit = 0;
			
			if (flag) {
				/*
				 * 3. check characters of license key
				 */
				
				char[] chs = key.toCharArray();
				
				for (char ch : chs) {
					String strKey = "" + ch;
					String strVal = map.get(strKey);
					if (strVal == null) {
						throw new Exception("ERROR : error because of wrong license key");
					}
					
					if (Character.isDigit(ch)) {
						crcDigit += Integer.parseInt(strKey);
					}
					
					sbInfo.append(strVal);
				}
				
				if (!flag) log.debug(String.format("[%s] %d %d", sbInfo.toString(), crcDigit, crcDigit % 10));
			}

			if (flag) {
				/*
				 * 4. check CRC value
				 */
				if (!crc.equals(String.valueOf(crcDigit % 10))) {
					throw new Exception("ERROR : error for crc check.");
				}
			}
			
			StringBuffer sbIp = new StringBuffer();
			
			if (flag) {
				/*
				 * 5. get local ip address
				 */
				InetAddress inetAddr = null;
				
				try {
					inetAddr = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					throw e;
				}

				byte[] ip = inetAddr.getAddress();
				for (int i=0; i < ip.length; i++) {
					sbIp.append(String.format("%03d", (int) ip[i] & 0xFF));
					if (i != ip.length - 1)
						sbIp.append(".");
				}
				
				if (!flag) log.debug("real ip : " + sbIp.toString());
			}
			
			if (flag) {
				/*
				 * 6. check ip address
				 *    if sbInfo is started with 000, then skip
				 */
				
				if (!flag) log.debug(String.format("[%s] [%s]", sbInfo.substring(0, 15), sbIp));
				
				if (!"000".equals(sbInfo.substring(0, 3))) {
					// license key does not start with AAA
					if (!sbIp.toString().equals(sbInfo.substring(0, 15))) {
						throw new Exception("ERROR : error because of wrong ip address.");
					}
				}
			}
			
			if (flag) {
				/*
				 * 7. check expire date
				 */
				String[] str = sbInfo.toString().split("-");
				String yyyymm = new SimpleDateFormat("yyyyMM").format(new Date());

				if (!flag) log.debug(String.format("[%s] [%s]", str[1], yyyymm));
				
				if (yyyymm.compareTo(str[1]) > 0) {
					throw new Exception("ERROR : error out of expire date");
				}
			}
			
			if (flag) log.debug("SUCCESS : success to check license");
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) throws Exception {
		
		if (!flag) test01(args);
		if (flag) test02(args);
	}
}
