package tain.kr.com.test.jndi.v01;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

/*
class TestMain extends NamingManager implements InitialContextFactory {

	public TestMain() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}
}
*/

public class JndiTestMain {

	private static boolean flag = true;
	
	/**
	 * JNDI bind
	 * 
	 * @param args
	 * @throws Exception
	 */
	private static void testBind(String[] args) throws Exception {
		
		if (flag) {
			Properties prop = new Properties();
			prop.put(Context.INITIAL_CONTEXT_FACTORY, "tain.kr.test.jndi.v01.TestMain");
			prop.put(Context.PROVIDER_URL, "tain://localhost:2015");
			
			Context context = new InitialContext(prop);
			String key = "tain.co.kr";
			String value = "(주) 타인";
			context.bind(key, value);
			
			if (flag) System.out.println("JNDI 네이밍 서비스에 등록하였습니다.");
		}
	}
	
	/**
	 * JNDI lookup
	 * 
	 * @param args
	 * @throws Exception
	 */
	private static void testLookup(String[] args) throws Exception {
		
		if (flag) {
			Properties prop = new Properties();
			prop.put(Context.INITIAL_CONTEXT_FACTORY, "tain.kr.test.jndi.v01.TestMain");
			prop.put(Context.PROVIDER_URL, "tain://localhost:2015");
			
			Context context = new InitialContext(prop);
			String key = "tain.co.kr";
			String value = (String) context.lookup(key);
			
			if (flag) System.out.printf("JNDI 네이밍 서비스에 등록된 자료는 [%s]=>[%s]\n", key, value);
		}
	}
	
	/**
	 * JNDI rebind
	 * 
	 * @param args
	 * @throws Exception
	 */
	private static void testRebind(String[] args) throws Exception {
		
		if (flag) {
			Properties prop = new Properties();
			prop.put(Context.INITIAL_CONTEXT_FACTORY, "tain.kr.test.jndi.v01.TestMain");
			prop.put(Context.PROVIDER_URL, "tain://localhost:2015");
			
			Context context = new InitialContext(prop);
			String key = "tain.co.kr";
			String value = "TAIN Inc.";
			context.rebind(key, value);
			
			if (flag) System.out.println("JNDI 네이밍 서비스에 재등록하였습니다.");
		}
	}
	
	/**
	 * JNDI unbind
	 * 
	 * @param args
	 * @throws Exception
	 */
	private static void testUnbind(String[] args) throws Exception {
		
		if (flag) {
			Properties prop = new Properties();
			prop.put(Context.INITIAL_CONTEXT_FACTORY, "tain.kr.test.jndi.v01.TestMain");
			prop.put(Context.PROVIDER_URL, "tain://localhost:2015");
			
			Context context = new InitialContext(prop);
			String key = "tain.co.kr";
			context.unbind(key);
			
			if (flag) System.out.println("JNDI 네이밍 서비스에 등록된 자료를 제거합니다.");
		}
	}
	
	/**
	 * Main method
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		if (flag) testBind(args);
		if (flag) testLookup(args);
		
		if (flag) testRebind(args);
		if (flag) testLookup(args);
		
		if (flag) testUnbind(args);
		if (flag) testLookup(args);
	}
}
