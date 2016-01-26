package tain.kr.com.test.url.v01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class URLConnectionTestMain {

	private static boolean flag = true;
	
	private static void test01(String[] args) {
		
		if (flag) {
			try {
				URL url = new URL("http://cms.emart.com:8343/SASEMARTCMS/images/emart/login/logo_h1.gif");
				URLConnection con = url.openConnection();
				con.connect();

				System.out.println("type : " + con.getContentType());
				System.out.println("date : " + new Date(con.getLastModified()));
				System.out.println("len  : " + con.getContentLength());
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test02(String[] args) {
		
		if (flag) {
			try {
				// get HTTPS URL connection
				URL url = new URL("https://cms.emart.com:8343/SASEMARTCMS/");
				HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
				
				// set hostname verification
				conn.setHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						// ignore host name verification. it always returns true.
						return true;
					}
				});
				
				// SSL setting
				SSLContext context = SSLContext.getInstance("TLS");
				context.init(null, null, null);   // No validation for now
				conn.setSSLSocketFactory(context.getSocketFactory());
				
				// connect to host
				conn.connect();
				conn.setInstanceFollowRedirects(true);
				
				// print response from host
				InputStream in = conn.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				
				String line = null;
				while ((line = reader.readLine()) != null) {
					System.out.printf("%s\n", line);
				}
				reader.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (KeyManagementException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private static class SavingTrustManager implements X509TrustManager {
		
		private final X509TrustManager tm;

		private X509Certificate[] chain;
		
		SavingTrustManager(X509TrustManager tm) {
			this.tm = tm;
		}
		
		public X509Certificate[] getAcceptedIssuers() {
			//throw new UnsupportedOperationException();
			return new X509Certificate[0];
		}
		
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			throw new UnsupportedOperationException();
		}
		
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			this.chain = chain;
			tm.checkServerTrusted(chain, authType);
		}
	}
	
	private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();
	
	private static String toHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer(bytes.length * 3);
		for (int b : bytes) {
			b &= 0xff;
			sb.append(HEXDIGITS[b >> 4]);
			sb.append(HEXDIGITS[b & 0x0f]);
			sb.append(' ');
		}
		return sb.toString();
	}
	
	/*
	 * very important module
	 */
	private static void test03(String[] args) throws Exception {
		
		if (flag) {
			args = new String[] { "cms.emart.com:8343", "changeit" };
		}

		if (flag) {
			String host;
			int port;
			char[] passphrase;
			if ((args.length == 1) || (args.length ==2)) {
				String[] c = args[0].split(":");
				host = c[0];
				port = (c.length == 1) ? 443 : Integer.parseInt(c[1]);
				String p = (args.length == 1) ? "changeit" : args[1];
				passphrase = p.toCharArray();
			} else {
				if (flag) System.out.println("USAGE : java InstallCert <host>[:port] [passphrase]");
				return;
			}
			
			File file = new File("jssecacerts");
			if (file.isFile() == false) {
				char SEP = File.separatorChar;
				File dir = new File(System.getProperty("java.home") + SEP + "lib" + SEP + "security");
				file = new File(dir, "jssecacerts");
				if (file.isFile() == false) {
					file = new File(dir, "cacerts");
				}
			}
			
			if (flag) System.out.println("Loading KeyStore " + file + ".....");
			InputStream in = new FileInputStream(file);
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(in, passphrase);
			in.close();
			
			SSLContext context = SSLContext.getInstance("TLS");
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(ks);
			X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
			SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
			context.init(null,  new TrustManager[] {tm}, null);
			SSLSocketFactory factory = context.getSocketFactory();
			
			if (flag) System.out.println("Opening connection to " + host + ":" + port + "...");
			SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
			socket.setSoTimeout(10000);
			try {
				if (flag) System.out.println("Starting SSL handshake...");
				socket.startHandshake();
				socket.close();
				if (flag) System.out.println();
				if (flag) System.out.println("No errors, certificate is already trusted");
			} catch (SSLException e) {
				if (flag) System.out.println();
				e.printStackTrace();
			}
			
			X509Certificate[] chain = tm.chain;
			if (chain == null) {
				if (flag) System.out.println("Could not obtain server certificate chain");
				return;
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			if (flag) System.out.println();
			if (flag) System.out.println("Server sent " + chain.length + " certificate(s):");
			if (flag) System.out.println();
			MessageDigest sha1 = MessageDigest.getInstance("SHA1");
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			for (int i=0; i < chain.length; i++) {
				X509Certificate cert = chain[i];
				if (flag) System.out.println(" " + (i + 1) + " Subject " + cert.getSubjectDN());
				if (flag) System.out.println("   Issuer  " + cert.getIssuerDN());
				sha1.update(cert.getEncoded());
				if (flag) System.out.println("   sha1    " + toHexString(sha1.digest()));
				md5.update(cert.getEncoded());
				if (flag) System.out.println("   md5     " + toHexString(md5.digest()));
				if (flag) System.out.println();
			}
			
			if (flag) System.out.println("Enter certificate to add to trusted keystore or 'q' to quit : [1]");
			String line = reader.readLine().trim();
			int k;
			try {
				k = (line.length() == 0) ? 0 : Integer.parseInt(line) - 1;
			} catch (NumberFormatException e) {
				if (flag) System.out.println("KeyStore not changed");
				return;
			}
			
			X509Certificate cert = chain[k];
			String alias = host + "-" + (k + 1);
			ks.setCertificateEntry(alias, cert);
			
			OutputStream out = new FileOutputStream("jssecacerts");
			ks.store(out, passphrase);
			out.close();
			
			if (flag) System.out.println();
			if (flag) System.out.println(cert);
			if (flag) System.out.println();
			if (flag) System.out.println("Added certificated to keystore 'jssecacerts' using alias '" + alias + "'");
		}
	}
	
	private static void test04(String[] args) throws Exception {
		if (flag) {
			String host = "cms.emart.com";
			int port = 8343;
			char[] passphrase = "changeit".toCharArray();
			
			if (flag) System.out.println(System.getProperty("java.home"));
			File file = new File("jssecacerts");
			if (file.isFile() == false) {
				char SEP = File.separatorChar;
				File dir = new File(System.getProperty("java.home") + SEP + "lib" + SEP + "security");
				file = new File(dir, "jssecacerts");
				if (file.isFile() == false) {
					file = new File(dir, "cacerts");
				}
			}
			
			if (flag) System.out.println("Loading KeyStore " + file + "...");
			InputStream in = new FileInputStream(file);
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(in, passphrase);
			in.close();

			SSLContext context = SSLContext.getInstance("TLS");
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(ks);
			X509TrustManager defaultTrustManager = (X509TrustManager)tmf.getTrustManagers()[0];
			SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
			context.init(null, new TrustManager[] {tm}, null);
			SSLSocketFactory factory = context.getSocketFactory();

			if (flag) System.out.println("Opening connection to " + host + ":" + port + "...");
			SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
			socket.setSoTimeout(10000);
			try {
				if (flag) System.out.println("Starting SSL handshake...");
				socket.startHandshake();
				socket.close();
				if (flag) System.out.println();
				if (flag) System.out.println("No errors, certificate is already trusted");
				return;
			} catch (SSLException e) {
				if (flag) System.out.println(e.getMessage());
				//e.printStackTrace(System.out);
			}
			
			X509Certificate[] chain = tm.chain;
			if (chain == null) {
				System.out.println("Could not obtain server certificate chain");
				return;
			}

			//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			//System.out.println();
			//System.out.println("Server sent " + chain.length + " certificate(s):");
			//System.out.println();
			MessageDigest sha1 = MessageDigest.getInstance("SHA1");
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			for (int i = 0; i < chain.length; i++) {
				X509Certificate cert = chain[i];
				//System.out.println
				//  (" " + (i + 1) + " Subject " + cert.getSubjectDN());
				//System.out.println("   Issuer  " + cert.getIssuerDN());
				sha1.update(cert.getEncoded());
				//System.out.println("   sha1    " + toHexString(sha1.digest()));
				md5.update(cert.getEncoded());
				//System.out.println("   md5     " + toHexString(md5.digest()));
				//System.out.println();
			}

			//System.out.println("Enter certificate to add to trusted keystore or 'q' to quit: [1]");
			//String line = reader.readLine().trim();
			//int k;
			//try {
			//    k = (line.length() == 0) ? 0 : Integer.parseInt(line) - 1;
			//} catch (NumberFormatException e) {
			//    System.out.println("KeyStore not changed");
			//    return;
			//}

			int k=0;
			X509Certificate cert = chain[k];
			String alias = host + "-" + (k + 1);
			ks.setCertificateEntry(alias, cert);

			OutputStream out = new FileOutputStream("jssecacerts");
			ks.store(out, passphrase);
			out.close();

			//System.out.println();
			//System.out.println(cert);
			//System.out.println();
			System.out.println("Added certificate to keystore 'jssecacerts' using alias '" + alias + "'");
		}
	}
	
	private static void test05(String[] args) throws Exception {
		
		if (!flag) {
			String httpUrl = "http://matcmsmine01:8888/SASEMARTCMS/common/FF12060W.do?method=page";
			URL myUrl = new URL(httpUrl);
			HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
			reader.close();
		}
		
		if (flag) {
			String httpsUrl = "https://cms.emart.com:8343/SASEMARTCMS/common/FF12060W.do?method=page";
			URL myUrl = new URL(httpsUrl);
			HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
			reader.close();
		}
	}
	
	private static void test06(String[] args) throws Exception {
		
		String fileName = "K:/WebPageInfo.properties";
		String domain = "DOMAIN.03";
		
		Properties prop = new Properties();
		prop.load(new InputStreamReader(new FileInputStream(fileName), "EUC-KR"));
		if (!flag) prop.list(System.out);
		
		List<WebPageInfoBean> list = new ArrayList<WebPageInfoBean>();
		
		for (Map.Entry<Object, Object> entry : prop.entrySet()) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			
			if (key.indexOf("DOMAIN") >= 0)
				continue;
			
			WebPageInfoBean bean = new WebPageInfoBean();
			String[] item = value.split(":");
			if (item.length != 2) {
				throw new Exception("ERROR : page parsing error [" + fileName + "]");
			}
			bean.setPageKey(key);
			bean.setPageWaitTime(Integer.parseInt(item[0]));
			bean.setPageUrl(item[1]);
			
			list.add(bean);
		}
		
		if (flag) {
			// sort by pageKey
			Collections.sort(list, new Comparator<WebPageInfoBean>() {
				@Override
				public int compare(WebPageInfoBean bean1, WebPageInfoBean bean2) {
					int ret = 0;
					
					ret = bean1.getPageKey().compareTo(bean2.getPageKey());
					if (ret != 0)
						return ret;
					
					return ret;
				}
			});
			
			if (!flag) {
				// check data
				for (WebPageInfoBean bean : list) {
					System.out.println(bean);
				}
			}
		}
		
		if (flag) {
			// set pages to domain
			String strDomain = prop.getProperty(domain);
			
			for (WebPageInfoBean bean : list) {
				
				boolean ret = urlConnection(strDomain, bean.getPageUrl(), bean.getPageWaitTime());
				if (!ret) {
					System.out.printf("ERROR : Could not find page....[%s]\n", bean.getPageUrl());
					break;
				}
			}
		}
	}
	
	private static boolean urlConnection(String domain, String page, int waitTime) throws Exception {
	
		if (flag) {
			String httpsUrl = domain + page;
			URL myUrl = new URL(httpsUrl);
			HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
			if (flag) System.out.printf("########## [%s][%s]\n", myUrl.getProtocol(), httpsUrl);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
			reader.close();
			
			if (flag) try { Thread.sleep(waitTime * 1000); } catch (InterruptedException e) {}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		if (!flag) test01(args);
		if (!flag) test02(args);
		if (!flag) test03(args);
		if (!flag) test04(args);
		if (!flag) test05(args);
		if (flag) test06(args);
	}
}

class WebPageInfoBean {
	
	String pageKey = null;
	int pageWaitTime = 0;
	String pageUrl = null;
	
	public String getPageKey() {
		return pageKey;
	}
	public int getPageWaitTime() {
		return pageWaitTime;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}
	public void setPageWaitTime(int pageWaitTime) {
		this.pageWaitTime = pageWaitTime;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	public String toString() {
		return String.format("[%s](%d)[%s]", this.pageKey, this.pageWaitTime, this.pageUrl);
	}
}
