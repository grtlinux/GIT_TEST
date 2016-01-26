package tain.kr.com.test.map.v01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class MapTestMain {

	private static final Logger log = Logger.getLogger(MapTestMain.class);
	
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("seq", new Integer(123));
			map.put("name", "Hello");
			map.put("arrStr", new String[] { "str1", "str2", "str3" });
			
			if (flag) log.debug(String.format("%d, %s, %s", map.get("seq"), map.get("name"), Arrays.asList((String[]) map.get("arrStr"))));
			if (flag) log.debug(">" + Arrays.asList((String[]) map.get("arrStr")));
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static void test02(String[] args) throws Exception {
		
		if (flag) {
			Map<String,String> map = new HashMap<String,String>();
			
			for (int i=0; i < 100; i++) {
				String key = String.format("KEY-%02d", i);
				String val = String.format("VALUE-%02d", i);
				
				map.put(key, val);
			}
			
			for (Map.Entry<String,String> entry : map.entrySet()) {
				String key = entry.getKey();
				String val = entry.getValue();
				
				if (flag) log.debug(String.format("[%s] => [%s]", key, val));
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) throws Exception {
	
		if (flag) test01(args);
		if (flag) test02(args);
	}
}
