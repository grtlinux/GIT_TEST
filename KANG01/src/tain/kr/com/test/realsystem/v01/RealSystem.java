package tain.kr.com.test.realsystem.v01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class RealSystem {

	private static boolean flag = true;
	
	private String osName = null;
	private String catalinaBase = null;
	private String webBase = null;
	
	/**
	 * constructor
	 * 
	 * @throws Exception
	 */
	public RealSystem() throws Exception {
		if (flag) {
			if ("matcmsapp".equals(System.getenv("HOSTINFO"))) {
				if (flag) System.out.printf("############################## KANG-3 : Real System...[HOSTINFO=%s]\n", System.getenv("HOSTINFO"));

				if (!flag) testRealSystemEnvironment(); 
				if (flag) makeRealSystemEnvironment();
			} else {
				if (flag) System.out.printf("############################## KANG-3 : Development System...[HOSTINFO=%s]\n", System.getenv("HOSTINFO"));
			}
		}
	}

	/**
	 * job function
	 * 
	 * @throws Exception
	 */
	private void makeRealSystemEnvironment() throws Exception {
		
		if (flag) {
			// get the environment of PATH
			this.osName = System.getProperty("os.name");
			this.catalinaBase = System.getProperty("catalina.base");
			
			if ("Linux".equals(osName)) {
				// Linux
				this.webBase = this.catalinaBase + "/sas_webapps/sas.emartcms.war";
			} else {
				// windows
				this.webBase = this.catalinaBase + "/wtpwebapps/SASEMARTCMS";
			}
			
			if (flag) {
				// print the information of PATH
				System.out.printf("#################### osName       : [%s]\n", this.osName);
				System.out.printf("#################### catalinaBase : [%s]\n", this.catalinaBase);
				System.out.printf("#################### webInf       : [%s]\n", this.webBase);
			}
		}
		
		if (flag) {
			String basePath = this.webBase + "/WEB-INF/classes/egovframework/egovProps";
			String realSystem = basePath + "/RealSystem";
			
			if (new File(realSystem).isFile()) {
				// if RealSystem file exist, then return.
				if (flag) System.out.printf("############################## KANG-3 : If a file RealSystem exist, then return\n");
				
				return;
			}
		}
		
		if (flag) {
			// modify to real system : globals.properties
			String basePath = this.webBase + "/WEB-INF/classes/egovframework/egovProps";
			String srcGlobalsProperties = basePath + "/globals.properties";
			String tgtGlobalsProperties = basePath + "/temp.file";

			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(srcGlobalsProperties)));
			PrintWriter writer = new PrintWriter(new File(tgtGlobalsProperties));
			
			String line = null;
			while ((line = reader.readLine()) != null) {
				
				if (!flag) System.out.printf("########## > %s\n", line);
				line = line.replaceAll("cmsdata_dev", "cmsdata");          // default database
				line = line.replaceAll("cmsdata01_dev", "cmsdata01");      // user authentication
				if (!flag) System.out.printf("########## > %s\n\n", line);
				
				writer.println(line);
			}
			
			reader.close();
			writer.close();

			// file move
			File tgtFile = new File(tgtGlobalsProperties);
			File srcFile = new File(srcGlobalsProperties);
			srcFile.delete();
			tgtFile.renameTo(srcFile);
			
			if (flag) System.out.printf("############################## KANG-3 : convert a file %s to set real environment\n", srcGlobalsProperties);
		}
		
		if (flag) {
			// Directory
			String xmlPathName = this.webBase + "/WEB-INF/classes/emart/sqlmap/campaign";
			if (flag) System.out.printf("#################### [xml path] -> [%s]\n", xmlPathName);
			
			File[] xmlFiles = new File(xmlPathName).listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if (name.lastIndexOf('.') > 0) {
						int lastIndex = name.lastIndexOf('.');
						String str = name.substring(lastIndex);
						
						if (".xml".equals(str)) {
							return true;
						}
					}
					
					return false;
				}
			});
			
			File tmpFile = new File(xmlPathName + "/temp.file");

			for (File xmlFile : xmlFiles) {
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(xmlFile)));
				PrintWriter writer = new PrintWriter(tmpFile);
				
				String line = null;
				String line2 = null;
				while ((line = reader.readLine()) != null) {
					
					if (!flag) System.out.printf("########## > %s\n", line);
					line2 = line.replaceAll("CMSDATA_DEV\\.", "CMSDATA.");
					line2 = line2.replaceAll("CMSVIEW_DEV\\.", "CMSVIEW.");
					if (!flag) System.out.printf("########## > %s\n\n", line2);
					
					if (!line.equals(line2)) {
						if (flag) System.out.printf("########## > %s\n", line2);
					}
					
					writer.println(line2);
				}
				
				reader.close();
				writer.close();
				
				// file move
				xmlFile.delete();
				tmpFile.renameTo(xmlFile);

				if (flag) System.out.printf("########## > convertion is OK.. %s\n\n", xmlFile.getName());
				
				// break;
			}
		}
		
		if (flag) {
			/**
			 * TO-DO : 2015.11.10  have to change after moniter log file. -> adjust errors to below...
			 */
			// directory mate to exchange real and test
			String matePathName = this.webBase + "/js/emart/mate";
			
			File realFile = new File(matePathName + "/real");
			File testFile = new File(matePathName + "/test");
			File devFile  = new File(matePathName + "/dev");
			
			if (realFile.isDirectory()) {
				System.out.println("1> rename : test/ -> dev/  : " + testFile.renameTo(devFile));
				System.out.println("2> rename : real/ -> test/ : " + realFile.renameTo(testFile));
				
				if (flag) System.out.printf("#################### [%s] : [real] -> [test] -> [dev]\n", matePathName);
			}
		}
		
		if (flag) {
			// create a file RealSystem.
			String basePath = this.webBase + "/WEB-INF/classes/egovframework/egovProps";
			String realSystem = basePath + "/RealSystem";
			
			new File(realSystem).createNewFile();
			
			if (flag) System.out.printf("############################## KANG-3 : create a file RealSystem...\n");
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * TEST
	 * 
	 * @throws Exception
	 */
	private void testRealSystemEnvironment() throws Exception {
		
		if (!flag) {
			Properties prop = System.getProperties();
			
			Set<String> setKeys = prop.stringPropertyNames();
			Iterator<String> iterKeys = setKeys.iterator();
			
			while (iterKeys.hasNext()) {
				String key = iterKeys.next();
				String value = prop.getProperty(key);
				
				if (flag) System.out.printf("#################### [%s] -> [%s]\n", key, value);
			}
		}
		
		if (flag) {
			String osName = System.getProperty("os.name");
			String catalinaBase = System.getProperty("catalina.base");
			
			if ("Linux".equals(osName)) {
				// Linux
				catalinaBase += "/sas_webapps/sas.emartcms.war";
			} else {
				// windows
				catalinaBase += "/wtpwebapps/SASEMARTCMS";
			}
			
			if (flag) System.out.printf("#################### [catalinaBase] -> [%s]\n", catalinaBase);

			if (flag) {
				// read properties file
				String globalsProperties = catalinaBase + "/WEB-INF/classes/egovframework/egovProps/globals.properties";
				if (flag) System.out.printf("#################### [read properties file] -> [%s]\n", globalsProperties);

				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(globalsProperties)));
				String line = null;
				while ((line = reader.readLine()) != null) {
					line = line.replaceAll("cmsdata_dev", "cmsdata");
					line = line.replaceAll("cmsdata01_dev", "cmsdata01");
					
					if (flag) System.out.printf("########## > %s\n", line);
				}
				
				reader.close();
			}
			
			if (!flag) {
				String basePath = catalinaBase + "/WEB-INF/classes/egovframework/egovProps";
				String srcGlobalsProperties = basePath + "/globals.properties";
				String tgtGlobalsProperties = basePath + "/globals.properties.tgt";
				if (flag) System.out.printf("#################### [copy file] : [%s] -> [%s]\n", srcGlobalsProperties, tgtGlobalsProperties);

				// file copy
				FileInputStream fis = new FileInputStream(srcGlobalsProperties);
				FileOutputStream fos = new FileOutputStream(tgtGlobalsProperties);
				
				byte[] byteBuffer = new byte[4096];
				int nRead;
				
				while ((nRead = fis.read(byteBuffer)) != -1) {
					fos.write(byteBuffer, 0, nRead);
				}
				
				fis.close();
				fos.close();
			}
			
			if (!flag) {
				String basePath = catalinaBase + "/WEB-INF/classes/egovframework/egovProps";
				String srcGlobalsProperties = basePath + "/globals.properties.tgt";
				String tgtGlobalsProperties = basePath + "/globals.properties.tgt.convert";
				if (flag) System.out.printf("#################### [move file] : [%s] -> [%s]\n", srcGlobalsProperties, tgtGlobalsProperties);

				// file move
				File file = new File(srcGlobalsProperties);
				file.renameTo(new File(tgtGlobalsProperties));
			}
			
			if (!flag) {
				// read xml file
				String xmlFile = catalinaBase + "/WEB-INF/classes/emart/sqlmap/campaign/AA01001W.xml";
				if (flag) System.out.printf("#################### [read properties file] -> [%s]\n", xmlFile);
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(xmlFile)));
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (flag) System.out.printf("########## > %s\n", line);
				}
				
				reader.close();
			}
			
			if (!flag) {
				// Directory
				String xmlPathName = catalinaBase + "/WEB-INF/classes/emart/sqlmap/campaign";
				if (flag) System.out.printf("#################### [xml path] -> [%s]\n", xmlPathName);
				
				File[] xmlFiles = new File(xmlPathName).listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						if (name.lastIndexOf('.') > 0) {
							int lastIndex = name.lastIndexOf('.');
							String str = name.substring(lastIndex);
							
							if (".xml".equals(str)) {
								return true;
							}
						}
						
						return false;
					}
				});
				
				for (File xmlFile : xmlFiles) {
					if (flag) System.out.printf("########## > %s\n", xmlFile.getName());
				}
				
			}
		}
		
		// end of function
	}
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			// directory mate to exchange real and test
			String matePathName = "D:/PROJ/KANG/WORK_01/PROG/libs/mate";
			
			File srcFile = new File(matePathName + "/test");
			File tgtFile = new File(matePathName + "/real");
			File tmpFile = new File(matePathName + "/tmp");
			
			System.out.println("1> srcFile.renameTo(tmpFile) : " + srcFile.renameTo(tmpFile));
			System.out.println("2> tgtFile.renameTo(srcFile) : " + tgtFile.renameTo(srcFile));
			System.out.println("3> tmpFile.renameTo(tgtFile) : " + tmpFile.renameTo(tgtFile));
			
			if (flag) System.out.printf("#################### [%s] <-> [%s]\n", srcFile, tgtFile);
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		if (flag) { new RealSystem(); }
		if (!flag) test01(args);
	}
}
