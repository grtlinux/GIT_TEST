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

import org.apache.log4j.Logger;

/**
 * 
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : FileInfoTestMain.java
 *   -. Package    : tain.kr.com.test.file.v02
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 2. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class FileInfoTestMain {

	private static final Logger log = Logger.getLogger(FileInfoTestMain.class);
	
	private static boolean flag = true;
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			String basePath = "L:/src.org";
			String timeDiff = "S60";   // 1 minute
			String timeApply = "H48";  // 2 days
			
			new FileInfo(basePath, timeDiff, timeApply).execute();
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug("This is a program to use File class for file information.");
		
		if (flag) test01(args);
	}
}
