package tain.kr.com.test.dev2real.v01;

import java.util.List;

public class Dev2RealInfoBean {

	/**
	 * private variables
	 */
	private String hostInfo = null;
	private String transferBasePath = null;
	private String transferFileExt = null;
	private List<String[]> transferList = null;
	
	/**
	 * getter
	 * 
	 * @return
	 */
	public String getHostInfo() {
		return hostInfo;
	}
	public String getTransferBasePath() {
		return transferBasePath;
	}
	public String getTransferFileExt() {
		return transferFileExt;
	}
	public List<String[]> getTransferList() {
		return transferList;
	}
	
	/**
	 * setter
	 * 
	 * @param hostInfo
	 */
	public void setHostInfo(String hostInfo) {
		this.hostInfo = hostInfo;
	}
	public void setTransferBasePath(String transferBasePath) {
		this.transferBasePath = transferBasePath;
	}
	public void setTransferFileExt(String transferFileExt) {
		this.transferFileExt = transferFileExt;
	}
	public void setTransferList(List<String[]> transferList) {
		this.transferList = transferList;
	}

	/**
	 * toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (String[] str : this.transferList) {
			sb.append(String.format("(%s:%s)", str[0], str[1]));
		}
		
		return String.format("[%s][%s][%s][%s]"
				, this.hostInfo
				, this.transferBasePath
				, this.transferFileExt
				, sb.toString());
	}
}
