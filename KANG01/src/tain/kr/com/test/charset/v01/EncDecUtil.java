package tain.kr.com.test.charset.v01;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

public class EncDecUtil {

	final static Logger log = Logger.getLogger(EncDecUtil.class);
	
	private static boolean flag = true;
	
	/**
	 * 
	 * @throws Exception
	 */
	private EncDecUtil() throws Exception {

		if (flag) {
			
			System.out.println(">" + getEncodingType("알아내기"));
			System.out.println(">" + getCharacterSet());
			
			System.out.println();
		}
		
		String source = "게임";

		if (flag) {
			String hex_utf8 = encode(source, "UTF-8");
			System.out.println(hex_utf8);
			System.out.println(decode(hex_utf8, "UTF-8"));
			System.out.println();
			
			String hex_euckr = encode(source, "EUC-KR");
			System.out.println(hex_euckr);
			System.out.println(decode(hex_euckr, "EUC-KR"));
			System.out.println();
		}

		if (flag) {
			System.out.println(changeCharset(source, "UTF-8"));
			System.out.println(changeCharset(source, "EUC-KR"));
		}
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	private String getEncodingType(String str) throws Exception {
		
		String charSet[] = {"euc-kr", "ksc5601", "iso-8859-1", "8859_1", "ascii", "MS949", "UTF-8" };
		if (flag) {
			if (str != null) {
				
				try {
					for (int i=0; i < charSet.length; i++) {
						for (int j=0; j < charSet.length; j++) {
							if (i == j)
								continue;
							
							System.out.println(charSet[i] + ":" + charSet[j] + ":" + new String(str.getBytes(charSet[i]), charSet[j]) + "<br>");
						}
					}
				} catch (Exception e) {
					return "FAIL";
				}
			}
		}
		
		return "OK";
	}
	
	/**
	 * 
	 * @return
	 */
	private String getCharacterSet() {
		if (flag) {
			byte[] BOM = new byte[4];
			BOM = "확인할문자열".getBytes();
			
			if ( (BOM[0] & 0xFF) == 0xEF && (BOM[1] & 0xFF) == 0xBB && (BOM[2] & 0xff) == 0xBF)
				return "UTF-8";
			else if ( (BOM[0] & 0xFF) == 0xFE && (BOM[1] & 0xFF) == 0xFF)
				return "UTF-16BE";
			else if ( (BOM[0] & 0xFF) == 0xFF && (BOM[1] & 0xFF) == 0xFE)
				return "UTF-16LE";
			else if ( (BOM[0] & 0xFF) == 0x00 && (BOM[1] & 0xFF) == 0x00 && (BOM[2] & 0xFF) == 0xFE && (BOM[3] & 0xFF) == 0xFF)
				return "UTF-32BE";
			else if ( (BOM[0] & 0xFF) == 0xFF && (BOM[1] & 0xFF) == 0xFE && (BOM[2] & 0xFF) == 0x00 && (BOM[3] & 0xFF) == 0x00)
				return "UTF-32LE";
			else
				return "EUC-KR";
		}
		
		return "";
	}

	/**
	 * 
	 * @param str
	 * @param charset
	 * @return
	 */
	private String encode(String str, String charset) {
		StringBuffer sb = new StringBuffer();
		try {
			byte[] key_source = str.getBytes(charset);
			for (byte b : key_source) {
				String hex = String.format("%02X", b).toUpperCase();
				sb.append("%");
				sb.append(hex);
			}
		} catch (UnsupportedEncodingException e) {}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param hex
	 * @param charset
	 * @return
	 */
	private String decode(String hex, String charset) {
		byte[] bytes = new byte[hex.length()/3];
		int len = hex.length();
		
		for (int i=0; i < len; ) {
			int pos = hex.substring(i).indexOf("%");
			if (pos == 0) {
				String hex_code = hex.substring(i+1, i+3);
				bytes[i/3] = (byte)Integer.parseInt(hex_code, 16);
				i += 3;
			} else {
				i += pos;
			}
		}
		try {
			return new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {}
		
		return "";
	}
	
	/**
	 * 
	 * @param str
	 * @param charset
	 * @return
	 */
	private String changeCharset(String str, String charset) {
		try {
			byte[] bytes = str.getBytes(charset);
			return new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {}
		
		return "";
	}

	/**
	 * 
	 * @return
	 */
	private static String getCharSet(String str) throws Exception {
		if (flag) {
			byte[] BOM = new byte[4];
			BOM = str.getBytes("UTF-8");
			
			if ( (BOM[0] & 0xFF) == 0xEF && (BOM[1] & 0xFF) == 0xBB && (BOM[2] & 0xff) == 0xBF)
				return "UTF-8";
			else if ( (BOM[0] & 0xFF) == 0xFE && (BOM[1] & 0xFF) == 0xFF)
				return "UTF-16BE";
			else if ( (BOM[0] & 0xFF) == 0xFF && (BOM[1] & 0xFF) == 0xFE)
				return "UTF-16LE";
			else if ( (BOM[0] & 0xFF) == 0x00 && (BOM[1] & 0xFF) == 0x00 && (BOM[2] & 0xFF) == 0xFE && (BOM[3] & 0xFF) == 0xFF)
				return "UTF-32BE";
			else if ( (BOM[0] & 0xFF) == 0xFF && (BOM[1] & 0xFF) == 0xFE && (BOM[2] & 0xFF) == 0x00 && (BOM[3] & 0xFF) == 0x00)
				return "UTF-32LE";
			else
				return "EUC-KR";
		}
		
		return "";
	}

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		if (flag) new EncDecUtil();
		
		if (flag) {
			String str = "연습";
			
			System.out.println(">" + getCharSet(str));
		}
	}
}
