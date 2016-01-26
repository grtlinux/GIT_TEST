package tain.kr.com.test.file.v02;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class FileInfo {

	private static final Logger log = Logger.getLogger(FileInfo.class);
	
	private static boolean flag = true;
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	private String basePath = null;
	private String timeDiff = null;
	private String timeApply = null;
	
	private Map<String,FileInfo> mapFileInfo = null;
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public FileInfo(String basePath, String timeDiff, String timeApply) {
		
		if (flag) {
			this.basePath = basePath;
			this.timeDiff = timeDiff;
			this.timeApply = timeApply;
			
			this.mapFileInfo = new HashMap<String,FileInfo>();
		}
		
		if (flag) log.debug(String.format("[%s:%s:%s]", this.basePath, this.timeDiff, this.timeApply));
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public void execute() throws Exception {
		
		if (flag) {
			
			File base = new File(this.basePath);
			
			processFileInfo(base, mapFileInfo);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////
	
	private void processFileInfo(File folder, Map<String,FileInfo> map) throws Exception {
		
		if (flag) {
			
		}
	}
}
