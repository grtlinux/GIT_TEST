package tain.kr.com.test.charset.v02;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class CharSetTestMain {

	final static Logger log = Logger.getLogger(CharSetTestMain.class);
	
	private static boolean flag = true;
	
	////////////////////////////////////////////////////////////////////////////////////
	
	private static String BinToHex(byte[] buf) throws Exception {
		
		String res = "";
		String token = "";
		
		for (int i=0; i < buf.length; i++) {
			token = Integer.toHexString(buf[i]);
			
			if (token.length() >= 2) {
				token = token.substring(token.length() - 2);
			} else {
				for (int j=0; j < 2-token.length(); j++)
					token = "0" + token;
			}
			
			res += " " + token;
		}
		
		return res.toUpperCase();
	}

	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			String str = "가";  // EUC-KR : B0 A1,  UTF-8 : EA B0 80
			
			byte[] by1 = str.getBytes();
			byte[] by2 = str.getBytes("euc-kr");
			byte[] by3 = str.getBytes("utf-8");
			
			if (flag) {
				log.debug("byte[] by1 = str.getBytes();          -->" + BinToHex(by1));
				log.debug("byte[] by2 = str.getBytes('euc-kr');  -->" + BinToHex(by2));
				log.debug("byte[] by3 = str.getBytes('utf-8');   -->" + BinToHex(by3));
			}

			String hex1 = BinToHex(by1);
			String hex2 = BinToHex(by2);
			String hex3 = BinToHex(by3);

			if (hex1.equals(hex2)) {
				log.debug("이클립스의 Editor의 encoding은 EUC-KR 이다.");
			} else if (hex1.equals(hex3)) {
				log.debug("이클립스의 Editor의 encoding은 UTF-8 이다.");
			} else {
				log.debug("이클립스의 Editor의 encoding은 모른다.");
			}
			
			if (flag) log.debug(Integer.toBinaryString(by2[0] & 0xFF));
			if (flag) log.debug(Integer.toBinaryString(by3[0] & 0xFF));
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	
	private static void test02(String[] args) throws Exception {
		
		if (!flag) {
			if (flag) log.debug("########## System.getProperties()");
			
			Properties prop = System.getProperties();
			
			Set<String> setKeys = prop.stringPropertyNames();
			Iterator<String> iterKeys = setKeys.iterator();
			
			while (iterKeys.hasNext()) {
				String key = iterKeys.next();
				String value = prop.getProperty(key);
				
				if (flag) log.debug(String.format("[%s] -> [%s]", key, value));
			}
		}
		
		if (!flag) {
			if (flag) log.debug(String.format("########## System.getenv('PATH')"));
			if (flag) log.debug(String.format("[%s] -> [%s]", "PATH", System.getenv("PATH")));
		}
		
		if (!flag) {
			if (flag) log.debug("########## System.getenv()");
			
			Map<String, String> map = System.getenv();
			
			Set<String> setKeys = map.keySet();
			Iterator<String> iterKeys = setKeys.iterator();
			
			while (iterKeys.hasNext()) {
				String key = iterKeys.next();
				String value = map.get(key);
				
				if (flag) log.debug(String.format("[%s] -> [%s]", key, value));
			}
		}
		
		if (!flag) {
			if (flag) log.debug("########## System.getenv() - 2");
			
			Map<String, String> map = System.getenv();
			
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				
				if (flag) log.debug(String.format("[%s] -> [%s]", key, value));
			}
		}
		
		if (flag) {
			String dirBase = System.getProperty("user.dir");
			if (flag) log.debug("dirBase = " + dirBase);
		}
		
		if (flag) {
			String pkgName = CharSetTestMain.class.getPackage().getName();
			if (flag) log.debug("pkgName = " + pkgName);
		}
		
		if (flag) {
			String dirBase = System.getProperty("user.dir").replace('\\', '/');
			String pkgName = CharSetTestMain.class.getPackage().getName().replace('.', '/');
			String pathName = dirBase + "/src/" + pkgName;
			if (flag) log.debug("pathName = " + pathName);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	
	private static void test03(String[] args) throws Exception {
		
		if (!flag) {
			int num = Integer.parseInt("11111111", 2);
			if (flag) log.debug(String.format("%d %s", num, Integer.toBinaryString(num)));

			num = num >>> 3;
			if (flag) log.debug(String.format("%d %s", num, Integer.toBinaryString(num)));
		}
	
		if (flag) {
			String dirBase = System.getProperty("user.dir").replace('\\', '/');
			String pkgName = CharSetTestMain.class.getPackage().getName().replace('.', '/');

			String pathName = dirBase + "/src/" + pkgName;
			if (flag) log.debug("pathName = " + pathName);
			
			File filePath = new File(pathName);
			File[] files = null;
			
			try {
				files = filePath.listFiles(new FileFilter() {
					@Override
					public boolean accept(File file) {
						
						if (flag) log.debug(file.getName());

						if (!file.isFile())
							return false;
						
						int lastIndex = file.getName().lastIndexOf('.');
						if (lastIndex < 0)
							return false;
						
						String ext = file.getName().substring(lastIndex);
						if (!".txt".equals(ext))
							return false;
						
						return true;
					}
				});
				
				for (File f : files) {
					if (!flag) log.debug(f);
					
					// read data file
					FileInputStream fis = null;
					int length = 0;
					byte[] data = null;
					
					try {
						fis = new FileInputStream(f);

						length = (int) f.length();
						
						data = new byte[length];
						
						fis.read(data);
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (fis != null)
							try { fis.close(); } catch (Exception e) {}
					}
					
					// check charSet
					boolean charFlag = false;   // EUC-KR
					
					if (flag) {
						// processing for check
						for (int i=0; i < length; i++) {
							if ((data[i] & 0xFF) >>> 3 == 0x1E) {
								if ((data[i+1] & 0xFF) >>> 6 == 0x02
										&& (data[i+2] & 0xFF) >>> 6 == 0x02
										&& (data[i+3] & 0xFF) >>> 6 == 0x02) {
									charFlag = true;
									break;
								}
							}
							
							if ((data[i] & 0xFF) >>> 4 == 0x0E) {
								if ((data[i+1] & 0xFF) >>> 6 == 0x02
										&& (data[i+2] & 0xFF) >>> 6 == 0x02) {
									charFlag = true;
									break;
								}
							}
							
							if ((data[i] & 0xFF) >>> 5 == 0x0C) {
								if ((data[i+1] & 0xFF) >>> 6 == 0x02) {
									charFlag = true;
									break;
								}
							}
						}
					}
					
					if (flag) log.debug(String.format("%s %s %s", f.getAbsoluteFile(), charFlag, (charFlag ? "UTF-8" : "EUC-KR")));
					if (!flag) System.out.println(new String(data, (charFlag ? "UTF-8" : "EUC-KR")));
					
					if (flag) {
						// write data file
						FileWriter fw = null;
						
						try {
							fw = new FileWriter(f.getAbsoluteFile() + ".out");
							fw.write(new String(data, (charFlag ? "UTF-8" : "EUC-KR")));
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (fw != null)
								try { fw.close(); } catch (Exception e) {}
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) throws Exception {
		
		if (flag) test01(args);
		if (!flag) test02(args);
		if (!flag) test03(args);
	}
}
