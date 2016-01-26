package tain.kr.com.test.dev2real.v01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

public class Dev2RealJobProcess {

	private static boolean flag = true;
	
	private Dev2RealInfoBean infoBean = null;
	private String strExt = null;
	private List<String[]> transferList = null;
	
	public Dev2RealJobProcess(Dev2RealInfoBean infoBean) throws Exception {
		
		if (flag) {
			this.infoBean = infoBean;
			this.strExt = infoBean.getTransferFileExt();
			this.transferList = infoBean.getTransferList();
		}
		
		if (flag) validate();
	}
	
	private void validate() throws Exception {
		
		if (flag) {
			String hostInfo = System.getenv("HOSTINFO");
			
			if (hostInfo == null || !hostInfo.equals(this.infoBean.getHostInfo())) {
				throw new Exception(String.format("STATUS : HOSTINFO is dismatch... do not transfer process..[HOSTINFO=%s] [hostinfo=%s]\n", hostInfo, this.infoBean.getHostInfo()));
			}
			
			String basePath = this.infoBean.getTransferBasePath();
			if ("".equals(basePath)) {
				throw new Exception(String.format("ERROR : base path is not defined...[basepath=%s]", basePath));
			}
			
			File path = new File(basePath);
			
			if (!path.isDirectory()) {
				throw new Exception(String.format("ERROR : base path is not a directory...[basepath=%s]", basePath));
			}
		}
	}
	
	public void execute() throws Exception {
		
		if (flag) {
			
			if (flag) System.out.printf("\n########## START : Dev2Real Transfer Process #########\n");
			
			checkFile(new File(this.infoBean.getTransferBasePath()));

			if (flag) System.out.printf("\n########## END   : Dev2Real Transfer Process by Kang Seok in Tain Inc. at 2015.11.10 #########\n");
		}
	}
	
	private void checkFile(File file) throws Exception {
		
		if (flag) {
			if (file.isDirectory()) {
				// directory
				File[] files = file.listFiles(new FileFilter() {
					
					@Override
					public boolean accept(File pathname) {
						return pathname.isFile() || pathname.isDirectory();
					}
				});
				
				for (File f : files) {
					checkFile(f);
				}
			} else if (file.isFile()){
				// normal file
				if (!"".equals(this.strExt)) {
					if (file.getName().lastIndexOf(this.strExt) < 0)
						return;
				}
				
				// process transfer
				transfer(file);
			}
		}
	}
	
	private void transfer(File file) throws Exception {

		if (flag) System.out.printf("\n\n----------> Transfer Process with a file [%s] <------------\n\n", file);
		
		if (flag) {
			
			File tmpFile = new File(file.getParent() + "/tmp");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			PrintWriter writer = new PrintWriter(tmpFile);
			
			String line = null;
			String tranLine = null;
			int noLine = 0;
			while ((line = reader.readLine()) != null) {

				noLine ++;
				
				if (flag) {
					tranLine = line;
					for (String[] tranCode : this.transferList) {
						tranLine = tranLine.replaceAll(tranCode[0], tranCode[1]);
					}
				}
				
				if (!tranLine.equals(line)) {
					if (flag) System.out.printf("%4d)  %s\n     ->%s\n\n", noLine, line, tranLine);
				}
				
				if (!flag) writer.println(line);
				if (flag) writer.println(tranLine);
			}
			
			reader.close();
			writer.close();
			
			file.delete();
			tmpFile.renameTo(file);
		}
	}
}
