package tain.kr.com.test.properties.v01;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesTestMain {

	private static boolean flag = true;
	
	private static void test01(String[] args) {
	
		if (flag) {
			if (flag) System.out.printf("\nSystem.getProperties()\n");
			
			Properties prop = System.getProperties();
			
			Set<String> setKeys = prop.stringPropertyNames();
			Iterator<String> iterKeys = setKeys.iterator();
			
			while (iterKeys.hasNext()) {
				String key = iterKeys.next();
				String value = prop.getProperty(key);
				
				if (flag) System.out.printf("[%s] -> [%s]\n", key, value);
			}
		}
		
		if (flag) {
			if (flag) System.out.printf("\nSystem.getenv('PATH')\n");
			if (flag) System.out.printf("[%s] -> [%s]\n", "PATH", System.getenv("PATH"));
		}
		
		if (flag) {
			if (flag) System.out.printf("\nSystem.getenv()\n");
			
			Map<String, String> map = System.getenv();
			
			Set<String> setKeys = map.keySet();
			Iterator<String> iterKeys = setKeys.iterator();
			
			while (iterKeys.hasNext()) {
				String key = iterKeys.next();
				String value = map.get(key);
				
				if (flag) System.out.printf("[%s] -> [%s]\n", key, value);
			}
		}
		
		if (flag) {
			if (flag) System.out.printf("\nSystem.getenv() - 2\n");
			
			Map<String, String> map = System.getenv();
			
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				
				if (flag) System.out.printf("[%s] -> [%s]\n", key, value);
			}
		}
	}
	
	public static void main(String[] args) {
		if (flag) test01(args);
	}
}
