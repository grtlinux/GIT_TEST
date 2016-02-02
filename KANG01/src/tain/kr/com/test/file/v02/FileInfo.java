/**
 * Copyright 2014, 2015, 2016 TAIN, Inc. all rights reserved.
 *
 * Licensed under the GNU GENERAL PUBLIC LICENSE, Version 3, 29 June 2007 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * -----------------------------------------------------------------
 * Copyright 2014, 2015, 2016 TAIN, Inc.
 *
 */
package tain.kr.com.test.file.v02;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : FileInfo.java
 *   -. Package    : tain.kr.com.test.file.v02
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 2. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
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
