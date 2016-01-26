package tain.kr.com.test.ftp.v01;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpReceiver {

	private static boolean flag = true;
	
	private static FtpReceiver instance = null;
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	private FTPClient ftpClient = null;
	private Map<String, String> ftpMap = null;
	
	private String host = null;
	private String port = null;
	private String user = null;
	private String pass = null;
	private String rpath = null;
	private String rfile = null;
	private String rencode = null;
	private String filecont = null;
	
	private List<String> listLine = null;
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * constructor
	 */
	private FtpReceiver() {
		// TODO for later
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean ftpConnection() throws Exception {
		
		if (flag) {
			this.host = this.ftpMap.get("host");
			this.port = this.ftpMap.get("port");
			this.user = this.ftpMap.get("user");
			this.pass = this.ftpMap.get("pass");
			this.rpath = this.ftpMap.get("rpath");
			this.rfile = this.ftpMap.get("rfile");
			this.rencode = this.ftpMap.get("rencode");
			this.filecont = this.ftpMap.get("filecont");
			
			this.listLine = new ArrayList<String>();
		}
		
		if (flag) {
			this.ftpClient = new FTPClient();
			this.ftpClient.setControlEncoding(this.rencode);

			if (flag) System.out.printf("TAIN : connection is OK !!!\n");
			
			this.ftpClient.connect(this.host, Integer.parseInt(this.port));
			if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			
			int reply = this.ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				throw new Exception(String.format("TAIN ERROR : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString()));
			}
			
			
			this.ftpClient.setSoTimeout(10000);
			
			if (!this.ftpClient.login(this.user, this.pass)) {
				throw new Exception(String.format("TAIN ERROR : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString()));
			}

			if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			
			this.ftpClient.enterLocalPassiveMode();
		}
		
		return true;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean ftpDisconnection() throws Exception {
		
		if (flag) {
			this.ftpClient.logout();
			if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());

			this.ftpClient.disconnect();
			if (flag) System.out.printf("TAIN : disconnection is OK !!!\n");
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private boolean makeWorkingFolder() throws Exception {
		
		if (flag) {
			this.ftpClient.makeDirectory(this.rpath);
			if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	private boolean setWorkingFolder() throws Exception {
		
		if (flag) {
			this.ftpClient.changeWorkingDirectory(this.rpath);
			if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
		}
		
		return true;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private List<String> getRemoteList() throws Exception {
		
		List<String> list = null;
		
		if (flag) {
			list = Arrays.asList(this.ftpClient.listNames());
			if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
		}
		
		return list;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private FTPFile[] getRemoteFTPFile() throws Exception {
		
		FTPFile[] files = null;
		
		if (flag) {
			files = this.ftpClient.listFiles();
			if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
		}
		
		return files;
	}
	
	/**
	 * [ NOT USED ]
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private boolean storeFile() throws Exception {

		if (flag) {
			
			// FTP transfer setting
			if (flag) {
				this.ftpClient.setFileType(FTP.STREAM_TRANSFER_MODE);
				if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			}
			
			if (flag) {
				this.ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			}
			
			if (flag) {
				this.ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
				if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			}
		}
		
		if (flag) {
			
			// FTP transfer file
			InputStream is = new FileInputStream(new File("D:/AAA.log"));
			this.ftpClient.storeFile("AAA.log", is);
			if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			is.close();
		}

		return true;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private boolean storeStream() throws Exception {

		if (flag) {
			
			// FTP transfer setting
			if (flag) {
				this.ftpClient.setFileType(FTP.STREAM_TRANSFER_MODE);
				if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			}
			
			if (flag) {
				this.ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			}
			
			if (!flag) {
				this.ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
				if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			}
		}
		
		if (flag) {
			ByteArrayInputStream is = new ByteArrayInputStream(this.filecont.getBytes("euc-kr"));
			this.ftpClient.storeFile(this.rfile, is);
			if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			is.close();
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean retrieveStream() throws Exception {

		if (flag) {
			
			// FTP transfer setting
			if (flag) {
				this.ftpClient.setFileType(FTP.STREAM_TRANSFER_MODE);
				if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			}
			
			if (flag) {
				this.ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			}
			
			if (!flag) {
				this.ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
				if (flag) System.out.printf("TAIN : (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			}
		}
		
		if (flag) {
			// 파일이 있는지 확인한다.
			
			String okFile = this.rfile.replace(".sam", "_OK.sam");
			
			FTPFile[] files = this.ftpClient.listFiles(okFile);
			
			if (files == null || files.length < 1) {
				if (flag) System.out.printf("TAIN : REMOTE CHECK -> [%s/%s] : file not exists..\n", this.rpath, okFile);
				return false;
			}
			
			if (flag) System.out.printf("TAIN : REMOTE CHECK -> [%s/%s] : file exists..\n", this.rpath, okFile);
		}
		
		if (flag) {
			// 파일을 내려받는다.
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.ftpClient.retrieveFileStream(this.rfile), this.rencode));
			
			int cnt = 0;
			String line = null;
			while ((line = reader.readLine()) != null) {
				cnt++;
				if (flag) System.out.printf("TAIN : (LINE-%06d) [%s]\n", cnt, line);
				
				listLine.add(line);
			}
			
			if (flag) System.out.println("TAIN : FILE LINE CNT = " + cnt);
			
			reader.close();
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param ftpMap
	 * @throws Exception
	 */
	public List<String> receiver(Map<String, String> ftpMap) throws Exception {
		
		if (flag) {
			this.ftpMap = ftpMap;
		}
		
		if (flag) {
			
			if (flag) ftpConnection();
			
			if (flag) setWorkingFolder();
			
			if (flag) retrieveStream();
			
			if (flag) ftpDisconnection();
		}
		
		return this.listLine;
	}

	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized static FtpReceiver getInstance() throws Exception {
		if (FtpReceiver.instance == null) {
			FtpReceiver.instance = new FtpReceiver();
		}
		
		return FtpReceiver.instance;
	}
	
	////////////////////////////////////////////////////////////////////////////

	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			
			Map<String, String> ftpMap = new HashMap<String, String>();
			
			ftpMap.put("host", "174.100.29.31");
			ftpMap.put("port", "21");
			ftpMap.put("user", "emartweb");
			ftpMap.put("pass", "sb2akxm#1");
			ftpMap.put("rpath", "/emartweb/batch/work_file/download");   // ftpMap.put("rpath", "/home/cmpn/" + "SMS" + "/RECV");
			ftpMap.put("rfile", "crm_gift_custrcv_20151210.sam");
			ftpMap.put("rencode", "EUC-KR");
			// ftpMap.put("filecont", "");                  // ftpMap.put("filecont", new String("한글입니다.1234567890".getBytes("utf-8"), "euc-kr"));
			
			if (flag) System.out.println(ftpMap);
			
			List<String> list = FtpReceiver.getInstance().receiver(ftpMap);
			
			for (String line : list) {
				if (!flag) System.out.println(line);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) test01(args);
	}
}
