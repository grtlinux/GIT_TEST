package tain.kr.com.test.dev2real.v01;

import java.util.ArrayList;
import java.util.List;

public class Dev2RealEnvironment {

	private static boolean flag = true;
	
	private static final String HOSTINFO = "tain.test.sas.hostinfo";
	private static final String BASEPATH = "tain.test.sas.transfer.root.path";
	private static final String FILEEXT = "tain.test.sas.transfer.file.ext";
	private static final String TRANSINFO = "tain.test.sas.transfer.info";
	
	private Dev2RealInfoBean infoBean = null;
	
	private Dev2RealEnvironment() throws Exception {
		
		if (flag) {
			this.infoBean = new Dev2RealInfoBean();
		}
		
		if (!flag) {
			// for testing
			String value = null;
			
			value = System.getProperty(HOSTINFO, "matcmsapp");
			this.infoBean.setHostInfo(value);
			
			value = System.getProperty(BASEPATH, "D:/PROJ/TEMP/emart");
			this.infoBean.setTransferBasePath(value);
			
			value = System.getProperty(FILEEXT, ".sas");
			this.infoBean.setTransferFileExt(value);
			
			value = System.getProperty(TRANSINFO, "CMSVIEW_DEV:CMSVIEW;CMSDATA_DEV:CMSDATA");
			
			List<String[]> list = new ArrayList<String[]>();
			String[] strTrans = value.split(";");
			for (String strTran : strTrans) {
				String[] items = strTran.split(":");
				list.add(items);
			}
			this.infoBean.setTransferList(list);
		}
		
		if (flag) {
			// for executing
			String value = null;
			
			value = System.getProperty(HOSTINFO, "");
			this.infoBean.setHostInfo(value);
			
			value = System.getProperty(BASEPATH, "");
			this.infoBean.setTransferBasePath(value);
			
			value = System.getProperty(FILEEXT, "");
			this.infoBean.setTransferFileExt(value);
			
			value = System.getProperty(TRANSINFO, "");
			
			List<String[]> list = new ArrayList<String[]>();
			if (!"".equals(value)) {
				String[] strTrans = value.split(";");
				for (String strTran : strTrans) {
					String[] items = strTran.split(":");
					list.add(items);
				}
			}
			this.infoBean.setTransferList(list);
		}
		
		if (flag) System.out.printf("ENV INFO : -> %s\n", this.infoBean);
	}
	
	public Dev2RealInfoBean getBean() throws Exception {
		
		return this.infoBean;
	}
	
	/**
	 * getInstance();
	 */
	private static Dev2RealEnvironment instance = null;
	
	public static synchronized Dev2RealEnvironment getInstance() throws Exception {
		
		if (flag) {
			if (Dev2RealEnvironment.instance == null) {
				Dev2RealEnvironment.instance = new Dev2RealEnvironment();
			}
		}
		
		return Dev2RealEnvironment.instance;
	}
}
