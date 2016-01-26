package tain.kr.com.test.rush.v01;

import java.io.File;

public class ValidateOption {
	
	private static boolean flag = true;
	
	private static final String PROPERTIES_FILE = "test.rush.properties.file";
	private static final String DOMAIN_KEY = "test.rush.domain.key";
	private static final String COUNT_THREAD = "test.rush.count.thread";
	private static final String SHOW_REQUEST = "test.rush.show.request";
	private static final String SHOW_RESPONSE = "test.rush.show.response";
	private static final String START_RANDOM = "test.rush.start.random";

	/**
	 * constructor
	 * 
	 * @throws Exception
	 */
	public ValidateOption() throws Exception {
		// TODO
	}
	
	public void validate() throws Exception {
		if (flag) {
			// test.rush.properties.file
			if (flag) System.out.printf("STATUS [%s] validating ..... ", PROPERTIES_FILE);
			
			String fileName = System.getProperty(PROPERTIES_FILE);
			if (fileName == null)
				throw new Exception(String.format("ERROR : could not find property string by key [%s]", PROPERTIES_FILE));
			
			File file = new File(fileName);
			
			if (!file.exists())
				throw new Exception(String.format("ERROR : [%s] is not existed...", fileName));

			if (!file.isFile())
				throw new Exception(String.format("ERROR : [%s] is not file...", fileName));
			
			if (flag) System.out.printf("[%s]  OK!!!  [DONE]\n", fileName);
		}

		if (flag) {
			// test.rush.domain.key
			if (flag) System.out.printf("STATUS [%s] validating .......... ", DOMAIN_KEY);
			
			String value = System.getProperty(DOMAIN_KEY);
			if (value == null)
				throw new Exception(String.format("ERROR : could not find property string by key [%s]", DOMAIN_KEY));
			
			if (flag) System.out.printf("[%s]  OK!!!  [DONE]\n", value);
		}
		
		if (flag) {
			// test.rush.count.thread
			if (flag) System.out.printf("STATUS [%s] validating ........ ", COUNT_THREAD);
			
			String value = System.getProperty(COUNT_THREAD);
			if (value == null)
				throw new Exception(String.format("ERROR : could not find property string by key [%s]", COUNT_THREAD));
			
			try {
				Integer.parseInt(value);
			} catch (Exception e) {
				throw new Exception(String.format("ERROR : value is not number [%s]", value));
			}
			
			if (flag) System.out.printf("[%s]  OK!!!  [DONE]\n", value);
		}
		
		if (flag) {
			// test.rush.show.request
			if (flag) System.out.printf("STATUS [%s] validating ........ ", SHOW_REQUEST);
			
			String value = System.getProperty(SHOW_REQUEST);
			if (value == null)
				throw new Exception(String.format("ERROR : could not find property string by key [%s]", SHOW_REQUEST));
			
			if (flag) System.out.printf("[%s]  OK!!!  [DONE]\n", value);
		}
		
		if (flag) {
			// test.rush.show.response
			if (flag) System.out.printf("STATUS [%s] validating ....... ", SHOW_RESPONSE);
			
			String value = System.getProperty(SHOW_RESPONSE);
			if (value == null)
				throw new Exception(String.format("ERROR : could not find property string by key [%s]", SHOW_RESPONSE));
			
			if (flag) System.out.printf("[%s]  OK!!!  [DONE]\n", value);
		}

		if (flag) {
			// test.rush.start.random
			if (flag) System.out.printf("STATUS [%s] validating ........ ", START_RANDOM);
			
			String value = System.getProperty(START_RANDOM);
			if (value == null)
				throw new Exception(String.format("ERROR : could not find property string by key [%s]", START_RANDOM));
			
			try {
				Integer.parseInt(value);
			} catch (Exception e) {
				throw new Exception(String.format("ERROR : value is not number [%s]", value));
			}
			
			if (flag) System.out.printf("[%s]  OK!!!  [DONE]\n", value);
		}
	}
}
