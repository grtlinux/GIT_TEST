package tain.kr.com.test.rush.v01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

public class RushThread extends Thread {

	private static boolean flag = true;
	
	private static final String DOMAIN_KEY = "test.rush.domain.key";
	private static final String SHOW_REQUEST = "test.rush.show.request";
	private static final String SHOW_RESPONSE = "test.rush.show.response";
	private static final String START_RANDOM = "test.rush.start.random";
	
	private PropertiesInfo propInfo = null;
	
	private boolean flagShowReq = false;
	private boolean flagShowRes = false;
	private int startRandom = -1;
	private String domain = null;
	private String protocol = null;
	private List<WebPageBean> listPage = null;
	
	private Random rand = null;
	
	/**
	 * rush thread
	 * 
	 * @param thrNo
	 * @param propInfo
	 * @throws Exception
	 */
	public RushThread(int thrNo, PropertiesInfo propInfo) throws Exception {
		
		super("RUSH_THREAD_" + thrNo);
		
		if (flag) {
			// get thread name and prop info
			if (flag) System.out.printf("CREATE THREAD [%s]\n", this.getName());

			this.propInfo = propInfo;
		}

		if (flag) {
			// get flag values
			String value = null;
			
			value = System.getProperty(SHOW_REQUEST);
			if (value.charAt(0) == 'y' || value.charAt(0) == 'Y') {
				this.flagShowReq = true;
			} else {
				this.flagShowReq = false;
			}

			value = System.getProperty(SHOW_RESPONSE);
			if (value.charAt(0) == 'y' || value.charAt(0) == 'Y') {
				this.flagShowRes = true;
			} else {
				this.flagShowRes = false;
			}
			
			value = System.getProperty(START_RANDOM);
			this.startRandom = Integer.parseInt(value);
		}
		
		if (flag) {
			// get WebPageInfo properties
			String key = System.getProperty(DOMAIN_KEY);
			this.domain = this.propInfo.getDomain(key);
			this.protocol = new URL(this.domain).getProtocol();
			this.listPage = this.propInfo.getListPage();
		}
		
		if (flag) {
			System.out.printf("[this.flagShowReq = %s]\n", this.flagShowReq);
			System.out.printf("[this.flagShowRes = %s]\n", this.flagShowRes);
			System.out.printf("[this.startRandom = %d]\n", this.startRandom);
			System.out.printf("[this.domain      = %s]\n", this.domain);
			System.out.printf("[this.protocol    = %s]\n", this.protocol);
		}
		
		if (flag) {
			// random parameter
			this.rand = new Random(new Date().getTime());
		}
	}

	/**
	 * thread processing
	 */
	public void run() {
		
		if (flag) {
			// starting wait time(seconds)
			try { Thread.sleep(rand.nextInt(this.startRandom) * 1000); } catch (InterruptedException e) {}
		}
		
		if (flag) System.out.printf("START ------> [%s]\n", this.getName());
		
		if (flag) {
			try {
				for (WebPageBean bean : listPage) {
					// each page
					String strUrl = this.domain + bean.getPageUrl();
					if (this.flagShowReq) System.out.printf("[%s] --> [%s] REQ [%s]\n", this.getName(), bean.getPageKey(), strUrl);
					
					URL url = new URL(strUrl);
					if ("https".equals(this.protocol)) {
						// https
						HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
						
						BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
						
						String line;
						
						while ((line = reader.readLine()) != null) {
							if (this.flagShowRes) System.out.println(line);
						}
						
						reader.close();
					} else {
						// http
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						
						BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
						
						String line;
						
						while ((line = reader.readLine()) != null) {
							if (this.flagShowRes) System.out.println(line);
						}
						
						reader.close();
					}
					
					if (flag) try { Thread.sleep((bean.getPageWaitTime() + rand.nextInt(5)) * 1000); } catch (InterruptedException e) {}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
