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
import java.io.FileFilter;
import java.util.Date;
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
	
	@SuppressWarnings("unused")
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
			
			processFileInfo(base);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////
	
	private void processFileInfo(File folder) throws Exception {
		
		if (flag) {
			
			File[] files = null;
			
			try {
				
				files = folder.listFiles(new FileFilter() {
					@Override
					public boolean accept(File file) {
						
						if (flag) {
							String tmCal = null;
							
							if (flag) {
								long lm = file.lastModified();
								long ms = lm % 1000;
								long sec = (lm / 1000) % 60;
								long min = (lm / 1000 / 60) % 60;
								long hour = (lm / 1000 / 60 / 60 + 9) % 24;
								tmCal = String.format("[%02d:%02d:%02d.%03d]", hour, min, sec, ms);
							}
							
							try {
								StringBuffer sb = new StringBuffer();
								sb.append(String.format("1 > "));
								sb.append(String.valueOf(file.getName())).append(" - ");
								sb.append(String.valueOf(file.getAbsolutePath())).append(" - ");
								sb.append(String.valueOf(file.getCanonicalPath())).append(" - ");
								sb.append(String.valueOf(file.lastModified())).append(" - ").append(String.valueOf(new Date(file.lastModified()))).append(" - ").append(tmCal).append(" - ");
								sb.append(String.valueOf(file.length())).append(" - ");
								
								log.debug(sb.toString());
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						
						if (flag) {
							// get name of file
							String name = file.getName();
							
							// get last index of '.' char.
							int lastIndex = name.lastIndexOf('.');
							
							if (lastIndex > 0) {
								// get extension
								String ext = name.substring(lastIndex);
								
								// match each name extension
								if (".iso".equals(ext)) {
									return true;
								}
							}
						}

						return true;
					}
				});
				
				if (flag) {
					/*
					 * print files
					 */
					for (File f : files) {
						log.debug(">" + f);
					}
				}
			} catch (Exception e) {
				throw e;
			} finally {
				
			}
		}
	}
}
