package tain.kr.com.test.charset.v03;

import java.io.File;
import java.io.FileFilter;

import org.apache.log4j.Logger;

public class FileFolderProcess {

	private final static Logger log = Logger.getLogger(FileFolderProcess.class);
	
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	private String rootPath = null;
	private String srcType = null;
	private String tgtType = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public FileFolderProcess(String rootPath, String srcType, String tgtType) {
		
		if (flag) {
			this.rootPath = rootPath;
			this.srcType = srcType;
			this.tgtType = tgtType;
		}
		
		if (flag) log.debug(String.format("PARAMS (rootPath:%s) (srcType:%s) (tgtType:%s)", this.rootPath, this.srcType, this.tgtType));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	public void execute() throws Exception {
		
		if (flag) {
			
			File filePath = null;
			
			try {
				filePath = new File(this.rootPath);
				if (!filePath.isDirectory()) {
					// if filePath is not a directory, then error
					
					if (flag) log.error(String.format("'%s' is not a directory (folder).. please check again carefully..!!", filePath.getAbsoluteFile()));
					
					return;
				}
				
				processFolder(filePath);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void processFolder(File filePath) throws Exception {
		
		if (!flag) log.debug("DIRECTORY : " + filePath.getAbsolutePath());
		
		if (!flag) {
			/*
			 * 1. testing for choosing specific file not java
			 */
			
			File[] files = null;
			
			try {
				// get files
				files = filePath.listFiles(new FileFilter() {
					
					@Override
					public boolean accept(File file) {
						
						String extJava = ".java";
						
						if (flag) {
							// skip if directory
							if (file.isDirectory())
								return true;
							
							// get a file not java
							int lastIndex = file.getName().lastIndexOf('.');
							if (lastIndex < 0)
								return true;
							
							String ext = file.getName().substring(lastIndex);
							if (!extJava.equals(ext))
								return true;
							
							return false;
						}
						
						return false;
					}
				});
				
				if (flag) {
					// print files
					for (File file : files) {
						if (flag) log.debug(file.isDirectory() + " " + file.getAbsoluteFile());
						
						if (file.isDirectory())
							processFolder(file);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
		}
		
		if (flag) {
			/*
			 * 2. processing
			 */
			
			File[] files = null;
			
			try {
				// get files
				files = filePath.listFiles(new FileFilter() {
					@Override
					public boolean accept(File file) {
						// choosing all files
						return true;
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
			
			if (flag) {
				for (File file : files) {
					// check whether file or directory
					if (file.isDirectory()) {
						processFolder(file);
					} else if (file.isFile()) {
						transferFile(file);
					} else {
						if (flag) log.error(String.format("'%s' is not folder nor file...", file.getAbsoluteFile()));
					}
				}
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void transferFile(File file) throws Exception {
		
		if (!flag) log.debug("FILE      : " + file.getAbsolutePath());
		
		new FileTransfer(file, this.srcType, this.tgtType).execute();
	}
}
