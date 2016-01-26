package tain.kr.com.test.ftp.v01;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpTransfer {

	private static boolean flag = true;
	
	private static FtpTransfer instance = null;
	
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
	private String filetype = null;
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * constructor
	 */
	private FtpTransfer() {
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
			this.filetype = this.ftpMap.get("filetype");
			if (this.filetype == null)
				this.filetype = "S";  // storeFile
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
			if (flag) System.out.printf("TAIN : S (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			is.close();
		}
		
		return true;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean appendStream() throws Exception {

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
			this.ftpClient.appendFile(this.rfile, is);
			if (flag) System.out.printf("TAIN : A (%d) %s", this.ftpClient.getReplyCode(), this.ftpClient.getReplyString());
			is.close();
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param ftpMap
	 * @throws Exception
	 */
	public void transfer(Map<String, String> ftpMap) throws Exception {
		if (flag) {
			this.ftpMap = ftpMap;
		}
		
		if (flag) {
			
			if (flag) ftpConnection();
			
			if (flag) setWorkingFolder();
			
			if ("A".equalsIgnoreCase(this.filetype))
				appendStream();
			else
				storeStream();
			
			if (flag) ftpDisconnection();
		}
	}

	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized static FtpTransfer getInstance() throws Exception {
		if (FtpTransfer.instance == null) {
			FtpTransfer.instance = new FtpTransfer();
		}
		
		return FtpTransfer.instance;
	}
}
