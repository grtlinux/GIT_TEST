package tain.kr.com.test.regexp.v01;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class UsernameValidator {

	private Pattern pattern;
	private Matcher matcher;
	
	private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
	
	public UsernameValidator() {
		pattern = Pattern.compile(USERNAME_PATTERN);
	}
	
	public boolean validate(final String username) {
		matcher = pattern.matcher(username);
		return matcher.matches();
	}
}

public class UsernameValidatorTest {

	private static boolean flag = true;
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			
			UsernameValidator usernameValidator = new UsernameValidator();
			
			String[] username = new String[] {
					"myyoun34", "myyoun_2002", "myyoun-2002", "mk3-4_youn",	
			};
			
			for (String temp : username) {
				boolean valid = usernameValidator.validate(temp);
				System.out.println("Username is valid : " + temp + ", " + valid);
			}
		}
	}
	
	private static void test02(String[] args) throws Exception {
		
		if (flag) {
			
			UsernameValidator usernameValidator = new UsernameValidator();
			
			String[] username = new String[] {
					"mk","mk@young", "mkyong123456789_-",	
			};
			
			for (String temp : username) {
				boolean valid = usernameValidator.validate(temp);
				System.out.println("Username is valid : " + temp + ", " + valid);
			}
		}
	}
	
	private static void test03(String[] args) throws Exception {
		
		if (flag) {
			Pattern p = Pattern.compile("%([a-zA-Z0-9]{3,})%");
			
			Matcher m = p.matcher("정규표현%HELLO%식은 검색해 보%TIME%시면 아시%ID%겠지만 문자%ID01%열에서 특정 패턴을 이용해...");
			
			StringBuffer sb = new StringBuffer();
			
			while (m.find()) {
				m.appendReplacement(sb, "##" + m.group(1) + "##");
				//System.out.println("group = " + m.group());
				//String str = m.replaceFirst("#####");
				//System.out.println("str = " + str);
			}
			
			System.out.println(">" + sb.toString());
		}
	}
	
	private static void test04(String[] args) throws Exception {
		
		Map<String,String> mapWord = new HashMap<String,String>();
		String ptn = "%([a-zA-Z0-9]{3,})%";
		String str = "정규표현%HELLO%식은 검색해 보%TIME%시면 아시%ID%겠지만 문자%ID01%열에서 특정 패턴을 이용해...";
		StringBuffer sb = new StringBuffer();
		
		if (flag) {
			mapWord.put("%HELLO%  ".trim(), "안녕하세요.");
			mapWord.put("%TIME%   ".trim(), "시간은");
			mapWord.put("%ID01%   ".trim(), "C123456ABC34D45E");
		}
		
		if (flag) {
			
			Pattern pattern = Pattern.compile(ptn);
			Matcher matcher = pattern.matcher(str);
			
			while (matcher.find()) {
				String key = matcher.group();
				String grp1 = matcher.group(1);
				String value = mapWord.get(key);
				if (value == null)
					value = "";
				
				matcher.appendReplacement(sb, "[" + grp1 + "->" + value + "]");
			}
			
			System.out.println("> " + sb.toString());
		}
	}
	
	private static void test05(String[] args) throws Exception {
	
		if (flag) {
			System.out.println("> " + "M001".compareTo("M014"));
			System.out.println("> " + "M014".compareTo("M014"));
			System.out.println("> " + "M016".compareTo("M014"));
			
			System.out.println(">" + String.format("%,d", 1234567890));
			
			String[] list = "123_234_dkdk_kkk".split("_");
			System.out.println(">" + Arrays.asList(list));
		}
	}
	
	private static void test06(String[] args) throws Exception {
		
		if (flag) {
			//String fld1 = "hello";
			String fld1 = null;
			
			Integer fld2 = 12345;
			
			Double fld3 = 282828282.123;
			
			Object obj1 = (Object) fld1;
			Object obj2 = (Object) fld2;
			Object obj3 = (Object) fld3;
			
			System.out.println(">" + String.valueOf(obj1));
			System.out.println(">" + String.valueOf(obj2));
			System.out.println(">" + String.valueOf(obj3));
			
			if (obj1 instanceof String) {
				System.out.println("String");
			} else if (obj1 instanceof Integer) {
				System.out.println("Integer");
			} else if (obj1 instanceof Double) {
				System.out.println("Double");
			}
			
			if (obj2 instanceof String) {
				System.out.println("String");
			} else if (obj2 instanceof Integer) {
				System.out.println("Integer");
			} else if (obj2 instanceof Double) {
				System.out.println("Double");
			}

			if (obj3 instanceof String) {
				System.out.println("String");
			} else if (obj3 instanceof Integer) {
				System.out.println("Integer");
			} else if (obj3 instanceof Double) {
				System.out.println("Double");
			}
		}
		
		if (flag) {
			int ret = Integer.parseInt("12345678");
			
			System.out.printf("> %,d\n", ret);
		}
		
		if (flag) {
			try {
				int ret = Integer.parseInt("12345678   q");
				System.out.printf("> %,d\n", ret);
			} catch (Exception e) {
				System.out.println("wrong");
			}
		}
	}
	
	private static String printValue(Object obj, String defVal) throws Exception {
		String str = null;
	
		if (flag) {
			if (obj == null)
				return defVal;
			
			if (obj instanceof String) {
				str = String.valueOf(obj);
			} else if (obj instanceof Integer) {
				str = String.format("%,d", (Integer) obj);
			} else if (obj instanceof Long) {
				str = String.format("%,d", (Long) obj);
			} else if (obj instanceof BigDecimal) {
				str = String.format("%,d", ((BigDecimal) obj).longValue());
			} else if (obj instanceof Float) {
				str = String.format("%,.2f", (Float) obj);
			} else if (obj instanceof Double) {
				str = String.format("%,.2f", (Double) obj);
			} else {
				str = "-";
			}
		}
		
		return str;
	}
	
	private static void test07(String[] args) throws Exception {
		
		if (flag) {
			System.out.println(">" + printValue("Hello", "없슴"));
			System.out.println(">" + printValue(1234, "없슴"));
			System.out.println(">" + printValue(1234567890123L, "없슴"));
			System.out.println(">" + printValue(new BigDecimal(12345678912345L), "없슴"));
			System.out.println(">" + printValue(12345.67, "없슴"));
			System.out.println(">" + printValue((double) 12312345.67, "없슴"));
			System.out.println(">" + printValue(null, "없슴"));
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (!flag) test01(args);
		if (!flag) test02(args);
		if (!flag) test03(args);
		if (!flag) test04(args);
		if (!flag) test05(args);
		if (!flag) test06(args);
		if (flag) test07(args);
	}
}
