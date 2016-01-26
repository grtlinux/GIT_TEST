package tain.kr.com.test.rush.v01;

public class WebPageBean {

	/**
	 * bean parameters
	 */
	String pageKey = null;
	int pageWaitTime = 0;
	String pageUrl = null;
	
	/**
	 * getter
	 * @param pageKey
	 */
	public String getPageKey() {
		return pageKey;
	}
	public int getPageWaitTime() {
		return pageWaitTime;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	
	/**
	 * setter
	 * @return
	 */
	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}
	public void setPageWaitTime(int pageWaitTime) {
		this.pageWaitTime = pageWaitTime;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	/**
	 * toString
	 */
	public String toString() {
		return String.format("[%s](%d)[%s]", this.pageKey, this.pageWaitTime, this.pageUrl);
	}
}
