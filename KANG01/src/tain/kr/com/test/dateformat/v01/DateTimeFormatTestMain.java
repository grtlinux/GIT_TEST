package tain.kr.com.test.dateformat.v01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

public class DateTimeFormatTestMain {

	final static Logger log = Logger.getLogger(DateTimeFormatTestMain.class);
	
	private static boolean flag = true;
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
			log.debug("> " + dtf.format(new Date(0L)));
		}
		
		if (flag) {
			SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS", Locale.KOREA);
			log.debug("> " + dtf.format(new Date(0L)));
		}
		
		if (flag) {
			SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS", Locale.KOREA);
			log.debug("> " + dtf.format(new Date(59999L)));
		}
	}
	
	private static void test02(String[] args) throws Exception {
		
		if (flag) {
			SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS", Locale.KOREA);
			log.debug("> " + dtf.format(new Date()));
		}
		
		if (flag) {
			SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA);
			log.debug("> " + dtf.format(new Date()));
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) test01(args);
		if (flag) test02(args);
	}
}
