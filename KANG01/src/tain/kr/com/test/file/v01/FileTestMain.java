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
package tain.kr.com.test.file.v01;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : FileTestMain.java
 *   -. Package    : tain.kr.com.test.file.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 2. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class FileTestMain {

	final static Logger log = Logger.getLogger(FileTestMain.class);
	
	private static boolean flag = true;
	
	//////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception {
		
		if (!flag) {
			/*
			 * 1. String[] File.list()
			 */
			File file = null;
			String[] files = null;
			
			try {
				// create new file
				file = new File("D:/PROJ");
				
				// return list of files
				files = file.list();
				
				// for loop of files
				for (String f : files) {
					
					// print file entry
					log.debug("2 > " + f);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
		}
		
		if (!flag) {
			/*
			 * 2. String[] File.list(FilenameFilter)
			 */
			File file = null;
			String[] files = null;
			
			try {
				// create new file
				file = new File("D:/PROJ");
				
				// return list of files
				files = file.list(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						
						if (!flag) log.debug("1 > " + dir.getName() + "   " + name);
						if (flag) log.debug("1 > " + dir.getAbsolutePath() + "   " + name);
						if (!flag) return true;
						
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
						
						return false;
					}
				});
				
				// for loop of files
				for (String f : files) {
					
					// print file entry
					log.debug("2 > " + f);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
		}
		
		if (!flag) {
			/*
			 * 3. File[] File.listFiles()
			 */
			File file = null;
			File[] files = null;
			
			try {
				// create new file
				file = new File("D:/PROJ");
				
				// return list of files
				files = file.listFiles();
				
				// for loop of files
				for (File f : files) {
					
					// print file entry
					log.debug("2 > " + f);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
		}
		
		if (!flag) {
			/*
			 * 4. File[] File.listFiles(FileFilter)
			 */
			File file = null;
			File[] files = null;
			
			try {
				// create new file
				file = new File("D:/PROJ");
				
				// create new file filter
				FileFilter fileFilter = new FileFilter() {
					
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
						
						return false;
					}
				};
				
				// return list of files
				files = file.listFiles(fileFilter);
				
				// for loop of files
				for (File f : files) {
					
					// print file entry
					log.debug("2 > " + f);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
		}

		if (flag) {
			/*
			 * 5. File[] File.listFiles(FilenameFilter)
			 */
			File file = null;
			File[] files = null;
			
			try {
				// create new file
				file = new File("D:/PROG_P");
				
				// create new filename filter
				FilenameFilter filenameFilter = new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						
						if (flag) log.debug("1 > " + dir.getName() + "   " + name);
						if (flag) log.debug("1 > " + dir.getAbsolutePath() + "   " + name);
						if (flag) return true;
						
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
						
						return false;
					}
				};
				
				// return list of files
				files = file.listFiles(filenameFilter);
				
				// for loop of files
				for (File f : files) {
					
					// print file entry
					log.debug("2 > " + f);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
		}
		
		if (!flag) {
			/*
			 * 6. File[] File.listRoots()
			 */
			File[] files = null;
			
			try {
				// return list of files
				files = File.listRoots();
				
				// for loop of files
				for (File f : files) {
					
					// print file entry
					log.debug("2 > " + f);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
		}
		
	}
	
	private static void test02(String[] args) throws Exception {
		
		if (flag) {
			
		}
	}
	
	private static void test03(String[] args) throws Exception {
		
		if (flag) {
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug("This program is to process the class File to do usefully.." + new Object(){}.getClass().getEnclosingClass().getName());
		
		if (flag) test01(args);
		if (flag) test02(args);
		if (flag) test03(args);
	}
}
