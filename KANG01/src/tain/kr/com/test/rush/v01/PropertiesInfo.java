package tain.kr.com.test.rush.v01;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertiesInfo {

	private static boolean flag = true;
	
	private static final String PROP_FILE_KEY = "test.rush.properties.file";
	private static final String DEFAULT_PROP_FILE = "K:/WebPageInfo.properties";
	private static final String CHAR_SET = "EUC-KR";
	
	private static PropertiesInfo instance = null;
	
	private String propFileName = null;
	private Map<String, String> mapDomain = null;
	private List<WebPageBean> listPage = null;
	
	/**
	 * constructor and initialization
	 */
	private PropertiesInfo() throws Exception {
		if (flag) {
			this.propFileName = System.getProperty(PROP_FILE_KEY, DEFAULT_PROP_FILE);
			
			Properties prop = new Properties();
			prop.load(new InputStreamReader(new FileInputStream(this.propFileName), CHAR_SET));
			if (!flag) prop.list(System.out);
			
			mapDomain = new HashMap<String, String>();
			listPage = new ArrayList<WebPageBean>();
			
			if (flag) {
				// get information of domain and page from the properties file
				for (Map.Entry<Object, Object> entry : prop.entrySet()) {
					String key = (String) entry.getKey();
					String value = (String) entry.getValue();
					
					if (key.indexOf("DOMAIN") >= 0) {
						// DOMAIN
						mapDomain.put(key, value);
					} else {
						// PAGE
						WebPageBean bean = new WebPageBean();
						String[] item = value.split(":");
						if (item.length != 2) {
							throw new Exception("ERROR : page parsing error [" + this.propFileName + "]");
						}
						bean.setPageKey(key);
						bean.setPageWaitTime(Integer.parseInt(item[0]));
						bean.setPageUrl(item[1]);
						
						listPage.add(bean);
					}
				}
			}
			
			if (flag) {
				// sort by pageKey
				Collections.sort(listPage, new Comparator<WebPageBean>() {
					@Override
					public int compare(WebPageBean bean1, WebPageBean bean2) {
						int ret = 0;
						
						ret = bean1.getPageKey().compareTo(bean2.getPageKey());
						if (ret != 0)
							return ret;
						
						return ret;
					}
				});
				
				if (!flag) {
					// check data and print bean object
					for (WebPageBean bean : listPage) {
						System.out.println(bean);
					}
				}
			}
		}
	}
	
	/**
	 * get the instance
	 * 
	 * @return
	 * @throws Exception
	 */
	public static synchronized PropertiesInfo getInstance() throws Exception {
		
		if (flag) {
			if (PropertiesInfo.instance == null) {
				PropertiesInfo.instance = new PropertiesInfo();
			}
		}
		
		return PropertiesInfo.instance;
	}
	
	/**
	 * get domain by key
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String getDomain(String key) throws Exception {
		
		String domain = null;
		
		if (flag) {
			domain = mapDomain.get(key);
			if (domain == null)
				throw new Exception(String.format("ERROR : counldn't find key [%s] from mapDomain.\n", key));
		}
		
		return domain;
	}
	
	/**
	 * get listPage, List Object
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<WebPageBean> getListPage() throws Exception {
		return listPage;
	}
}
