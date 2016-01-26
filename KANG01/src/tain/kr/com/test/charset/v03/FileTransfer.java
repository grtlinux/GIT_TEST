package tain.kr.com.test.charset.v03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;

public class FileTransfer {

	private final static Logger log = Logger.getLogger(FileTransfer.class);
	
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private File file;
	private String srcType;
	private String tgtType;
	
	private int length = 0;
	private byte[] data = null;

	///////////////////////////////////////////////////////////////////////////////////////////////

	public FileTransfer(File file, String srcType, String tgtType) {
		
		if (flag) {
			this.file = file;
			this.srcType = srcType;
			this.tgtType = tgtType;
		}
		
		if (!flag) log.debug(String.format("PARAMS (file:%s) (srcType:%s) (tgtType:%s)", this.file.getAbsolutePath(), this.srcType, this.tgtType));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void execute() throws Exception {
		
		if (flag) {
			// true : UTF-8, false : EUC-KR
			FileType fileType = getFileType(this.file);
			
			if (!flag) log.debug(String.format("[%s] [%s]", this.srcType, fileType.getName()));
			
			if (this.srcType.equalsIgnoreCase(fileType.getName())) {
				transfer(this.file);
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private FileType getFileType(File file) throws Exception {
		
		FileType fileType = FileType.EUC_KR;   // EUC-KR
		
		if (flag) {
			
			if (flag) {
				// read data file
				FileInputStream fis = null;
				
				try {
					fis = new FileInputStream(file);

					length = (int) file.length();
					data = new byte[length];
					
					fis.read(data);
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (fis != null)
						try { fis.close(); } catch (Exception e) {}
				}
			}
			
			if (flag) {
				// processing for check
				for (int i=0; i < length; i++) {
					if ((data[i] & 0xFF) >>> 3 == 0x1E) {
						if ((data[i+1] & 0xFF) >>> 6 == 0x02
								&& (data[i+2] & 0xFF) >>> 6 == 0x02
								&& (data[i+3] & 0xFF) >>> 6 == 0x02) {
							fileType = FileType.UTF_8;
							break;
						}
					}
					
					if ((data[i] & 0xFF) >>> 4 == 0x0E) {
						if ((data[i+1] & 0xFF) >>> 6 == 0x02
								&& (data[i+2] & 0xFF) >>> 6 == 0x02) {
							fileType = FileType.UTF_8;
							break;
						}
					}
					
					if ((data[i] & 0xFF) >>> 5 == 0x0C) {
						if ((data[i+1] & 0xFF) >>> 6 == 0x02) {
							fileType = FileType.UTF_8;
							break;
						}
					}
				}
			}
		}
		
		
		return fileType;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void transfer(File file) throws Exception {
		
		if (flag) log.debug(String.format("TRANSFER [%s->%s] [%s]", this.srcType, this.tgtType, this.file.getAbsolutePath()));
		
		if (flag) {
			// write data file
			FileOutputStream fos = null;
			
			try {
				
				fos = new FileOutputStream(file);
				fos.write(new String(data, this.srcType).getBytes(this.tgtType));
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fos != null)
					try { fos.close(); } catch (Exception e) {}
			}
		}
		
		if (!flag) System.exit(-1);
	}
}
