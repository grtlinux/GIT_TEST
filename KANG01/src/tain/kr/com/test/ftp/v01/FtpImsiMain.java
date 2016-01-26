package tain.kr.com.test.ftp.v01;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpImsiMain {

	private static boolean flag = true;
	
	private static final String HOST = "174.100.67.129";
	private static final String PORT = "21";
	private static final String USER = "cmpn";
	private static final String PASS = "cmpn12#";
	
	private FTPClient ftpClient = null;
	
	private FtpImsiMain() {
		// TODO auto-generated constructor stub
	}
	
	private boolean ftpConnection() throws Exception {
		if (flag) {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("EUC-KR");

			ftpClient.connect(HOST, Integer.parseInt(PORT));
			if (flag) System.out.printf("KANG : (%d) %s\n", ftpClient.getReplyCode(), ftpClient.getReplyString());
			
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				throw new Exception(String.format("KANG ERROR : (%d) %s", ftpClient.getReplyCode(), ftpClient.getReplyString()));
			}
			
			
			ftpClient.setSoTimeout(10000);
			
			if (!ftpClient.login(USER, PASS)) {
				throw new Exception(String.format("KANG ERROR : (%d) %s", ftpClient.getReplyCode(), ftpClient.getReplyString()));
			}

			if (flag) System.out.printf("KANG : (%d) %s\n", ftpClient.getReplyCode(), ftpClient.getReplyString());
			
			ftpClient.enterLocalPassiveMode();
		}
		
		return true;
	}
	
	private boolean ftpDisconnection() throws Exception {
		if (flag) {
			ftpClient.logout();
			if (flag) System.out.printf("KANG : (%d) %s\n", ftpClient.getReplyCode(), ftpClient.getReplyString());

			ftpClient.disconnect();
			if (flag) System.out.printf("KANG : disconnection is OK !!!\n");
		}
		
		return true;
	}
	
	private boolean makeWorkingFolder(String path) throws Exception {
		if (flag) {
			ftpClient.makeDirectory(path);
			if (flag) System.out.printf("KANG : (%d) %s\n", ftpClient.getReplyCode(), ftpClient.getReplyString());
		}
		
		return true;
	}
	
	private boolean setWorkingFolder(String path) throws Exception {
		if (flag) {
			ftpClient.changeWorkingDirectory(path);
			if (flag) System.out.printf("KANG : (%d) %s\n", ftpClient.getReplyCode(), ftpClient.getReplyString());
		}
		
		return true;
	}
	
	private List<String> getRemoteList() throws Exception {
		List<String> list = null;
		
		if (flag) {
			list = Arrays.asList(ftpClient.listNames());
			if (flag) System.out.printf("KANG : (%d) %s\n", ftpClient.getReplyCode(), ftpClient.getReplyString());
		}
		
		return list;
	}
	
	private boolean storeFiles() throws Exception {
		if (flag) {
			if (flag) {
				ftpClient.setFileType(FTP.STREAM_TRANSFER_MODE);
				if (flag) System.out.printf("KANG : (%d) %s\n", ftpClient.getReplyCode(), ftpClient.getReplyString());
			}
			
			if (flag) {
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				if (flag) System.out.printf("KANG : (%d) %s\n", ftpClient.getReplyCode(), ftpClient.getReplyString());
			}
			
			if (flag) {
				ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
				if (flag) System.out.printf("KANG : (%d) %s\n", ftpClient.getReplyCode(), ftpClient.getReplyString());
			}
			
			if (flag) {
				InputStream is = new FileInputStream(new File("D:/AAA.log"));
				ftpClient.storeFile("AAA.log", is);
				if (flag) System.out.printf("KANG : (%d) %s\n", ftpClient.getReplyCode(), ftpClient.getReplyString());
				is.close();
			}
		}
		
		return true;
	}
	
	private boolean storeStreams() throws Exception {
		if (flag) {
			if (flag) {
				ftpClient.setFileType(FTP.STREAM_TRANSFER_MODE);
				if (flag) System.out.printf("KANG : (%d) %s\n", ftpClient.getReplyCode(), ftpClient.getReplyString());
			}
			
			if (!flag) {
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				if (flag) System.out.printf("KANG : (%d) %s\n", ftpClient.getReplyCode(), ftpClient.getReplyString());
			}
			
			if (flag) {
				ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
				if (flag) System.out.printf("KANG : (%d) %s\n", ftpClient.getReplyCode(), ftpClient.getReplyString());
			}
			
			if (flag) {
				StringBuffer sb = new StringBuffer();
				sb.append("1. Hello,, world...(한글)").append("\n");
				sb.append("2. Hello,, world...(이름)").append("\n");
				sb.append("3. Hello,, world...(가능한지)").append("\n");
				sb.append("4. Hello,, world...(확인이 필요)").append("\n");
				sb.append("5. Hello,, world...(합니다.)").append("\n");
				sb.append("6. Hello,, world...(즐건)").append("\n");
				sb.append("7. Hello,, world...(하루를)").append("\n");
				sb.append("8. Hello,, world...(시작하세요)").append("\n");
				
				ByteArrayInputStream is = new ByteArrayInputStream(sb.toString().getBytes("euc-kr"));
				ftpClient.storeFile("BBB.log", is);
				if (flag) System.out.printf("KANG : (%d) %s\n", ftpClient.getReplyCode(), ftpClient.getReplyString());
				is.close();
			}
		}
		
		return true;
	}
	
	private void sendFtp() throws Exception {
		if (flag) {
			
			ftpConnection();
			if (flag) System.out.printf("KANG : FTP server connection is OK!!!\n");

			if (flag) {
				List<String> list = getRemoteList();
				for (String item : list) {
					if (flag) System.out.printf("> %s\n", item);
				}
			}
			
			if (!flag) setWorkingFolder("/home/cmpn");

			if (flag) makeWorkingFolder("KANG");
			
			if (flag) setWorkingFolder("KANG");
			
			if (flag) storeFiles();
			
			if (flag) storeStreams();
			
			ftpDisconnection();
		}
	}
	
	///////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception {
		if (flag) {
			new FtpImsiMain().sendFtp();
		}
	}
	
	private static void test02(String[] args) throws Exception {
		if (!flag) {
			// 2015.11.? : first test
			FTPClient client = new FTPClient();
			client.connect(HOST);
			client.login(USER, PASS);
			
			client.changeWorkingDirectory("/home/cmpn");
			
			FTPFile[] files = client.listFiles();
			
			for (FTPFile file : files) {
				SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				String formattedDate = formatDate.format(file.getTimestamp().getTime());
				if (flag) System.out.println(file.getName() + "   " + file.getSize() + "   " + formattedDate);
			}
			
			client.disconnect();
		}
		
		if (flag) {
			// 2015.12.11 : second test
			String host = "174.100.29.31";
			String user = "emartweb";
			String pass = "sb2akxm#1";
			String path = "/emartweb/batch/work_file/download";
			//String filePath = "crm_gift_cusrcv_20151210.sam";

			FTPClient client = new FTPClient();
			client.connect(host);
			client.login(user, pass);
			
			client.changeWorkingDirectory(path);
			
			FTPFile[] files = client.listFiles();
			
			for (FTPFile file : files) {
				SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.KOREA);
				String formattedDate = formatDate.format(file.getTimestamp().getTime());
				if (flag) System.out.println(file.getName() + "   " + file.getSize() + "   " + formattedDate);
			}
			
			client.disconnect();
		}
	}
	
	private static void test03(String[] args) throws Exception {
		if (!flag) {
			// 2015.11.? : first test
			String ftpUrl = "ftp://%s:%s@%s/%s";
			String host = HOST;
			String user = USER;
			String pass = PASS;
			//String filePath = "KANG/.profile";
			String filePath = "";
			
			ftpUrl = String.format(ftpUrl, user, pass, host, filePath);
			if (flag) System.out.println("URL: " + ftpUrl);
			
			URL url = new URL(ftpUrl);
			URLConnection conn = url.openConnection();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (flag) System.out.println(line);
			}
		}
		
		if (flag) {
			// 2015.12.11 : second test
			//String ftpUrl = "ftp://%s:%s@%s:%s/%s;%s";
			String ftpUrl = "ftp://%s:%s@%s/%s;%s";
			String user = "emartweb";
			String pass = "sb2akxm#1";
			String host = "174.100.29.31";
			//String port = "21";
			//String filePath = "/emartweb/batch/work_file/download/crm_gift_cusrcv_20151210.sam";
			String filePath = "/";
			String type = "type=i";
			
			//ftpUrl = String.format(ftpUrl, user, pass, host, port, filePath, type);
			ftpUrl = String.format(ftpUrl, user, pass, host, filePath, type);
			if (flag) System.out.println("URL: " + ftpUrl);

			URL url = new URL(ftpUrl);
			URLConnection conn = url.openConnection();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (flag) System.out.println(line);
			}
		}
	}
	
	private static void test04(String[] args) {
        String server = HOST;
        int port = 21;
        String user = USER;
        String pass = PASS;
 
        FTPClient ftpClient = new FTPClient();
 
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
 
            // use local passive mode to pass firewall
            //ftpClient.enterLocalPassiveMode();
 
            // get details of a file or directory
            String remoteFilePath = "KANG/AAA.log";
 
            FTPFile ftpFile = ftpClient.mlistFile(remoteFilePath);
            if (ftpFile != null) {
                String name = ftpFile.getName();
                long size = ftpFile.getSize();
                String timestamp = ftpFile.getTimestamp().getTime().toString();
                String type = ftpFile.isDirectory() ? "Directory" : "File";
 
                System.out.println("Name: " + name);
                System.out.println("Size: " + size);
                System.out.println("Type: " + type);
                System.out.println("Timestamp: " + timestamp);
            } else {
                System.out.println("The specified file/directory may not exist!");
            }
 
            ftpClient.logout();
            ftpClient.disconnect();
 
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
	
	///////////////////////////////////////////////////////////////////////////////

	private static void test05(String[] args) throws Exception {
		if (flag) {
			// 2015.11.? : first test
			Map<String, String> ftpMap = new HashMap<String, String>();
			
			ftpMap.put("host", "174.100.67.129");
			ftpMap.put("port", "21");
			ftpMap.put("user", "cmpn");
			ftpMap.put("pass", "cmpn12#");
			// ftpMap.put("rpath", "/home/cmpn/" + "SMS" + "/RECV");
			ftpMap.put("rpath", "/home/cmpn/");
			ftpMap.put("rfile", "TEST");
			ftpMap.put("rencode", "EUC-KR");
			ftpMap.put("filecont", "한글입니다.1234567890");
			
			FtpTransfer.getInstance().transfer(ftpMap);
		}
		
		if (flag) {
			// 2015.12.11 : second test
		}
	}
	
	private static void test06(String[] args) throws Exception {
		
		if (flag) {
			String host = "174.100.29.31";
	        int port = 21;
			String user = "emartweb";
			String pass = "sb2akxm#1";
	 
	        FTPClient ftpClient = new FTPClient();
	 
	        try {
	            ftpClient.connect(host, port);
	            ftpClient.login(user, pass);
	 
	            // use local passive mode to pass firewall
	            ftpClient.enterLocalPassiveMode();
	 
	            // get details of a file or directory
	            //String remoteFilePath = "/emartweb/batch/work_file/download/crm_gift_custrcv_20151210.sam";
	            //String remoteFilePath = "emartweb/wget-log";
	            String remoteFilePath = "wget-log";
	 
	            FTPFile ftpFile = ftpClient.mlistFile(remoteFilePath);
	            if (ftpFile != null) {
	                String name = ftpFile.getName();
	                long size = ftpFile.getSize();
	                String timestamp = ftpFile.getTimestamp().getTime().toString();
	                String type = ftpFile.isDirectory() ? "Directory" : "File";
	 
	                System.out.println("Name: " + name);
	                System.out.println("Size: " + size);
	                System.out.println("Type: " + type);
	                System.out.println("Timestamp: " + timestamp);
	            } else {
	                System.out.println("The specified file/directory may not exist!");
	            }
	 
	            ftpClient.logout();
	            ftpClient.disconnect();
	 
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	            if (ftpClient.isConnected()) {
	                try {
	                    ftpClient.disconnect();
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        }
		}
		
		if (flag) {
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		if (!flag) test01(args);
		if (!flag) test02(args);
		if (flag) test03(args);
		if (!flag) test04(args);
		if (!flag) test05(args);
		if (flag) test06(args);
	}
}
