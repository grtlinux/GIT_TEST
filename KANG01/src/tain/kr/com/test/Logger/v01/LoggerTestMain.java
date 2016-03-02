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
package tain.kr.com.test.Logger.v01;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : LoggerTestMain.java
 *   -. Package    : tain.kr.com.test.Logger.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 3. 2. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class LoggerTestMain {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(LoggerTestMain.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			
			String className = new Object(){}.getClass().getEnclosingClass().getName().replace('.', '/');

			java.util.logging.Logger testLog = java.util.logging.Logger.getLogger("TestLog.log");
			
			/*
			 * FileHandler(String pattern, int limit, int count, boolean append)
			 *    pattern : 출력 파일의 이름부의 패턴
			 *    limit : 임의의 1개의 파일에 기입하는 최대 바이트수
			 *    count : 사용하는 파일 수
			 *    append : 추가 모드를 저정한다
			 */
			java.util.logging.FileHandler fileHandler = new java.util.logging.FileHandler(className + ".xml", 0, 1, true);
			
			testLog.addHandler(fileHandler);
			testLog.setLevel(java.util.logging.Level.INFO);
			
			/*
			 * Log 출력
			 */
			testLog.log(java.util.logging.Level.INFO, "Hello, world!!!");
		}
	}
	
	private static void test02(String[] args) throws Exception {
		
		if (flag) {
			
			java.util.logging.Logger testLog = java.util.logging.Logger.getLogger("TestLog.log");
			
			testLog.severe("심각레벨정보");
			testLog.info("일반정보");
			testLog.warning("경고메시지를 여기에 남긴다.");
			testLog.config("환경정보를 남긴다.");
			testLog.fine("상세정보를 남긴다.");
			testLog.finest("아주 상세하게 로그 정보를 남긴다.");
			testLog.finer("finer를 기록한다.");
			
			try {
				System.out.println(10 / 0);
			} catch (Exception e) {
				testLog.severe("추적 정보 없는 에러 메시지" + e.getMessage());
				testLog.log(java.util.logging.Level.SEVERE, "추적정보있는 메시지 --> 나누기 실패:" + e);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug(">>>>> " + new Object(){}.getClass().getEnclosingClass().getName());
		
		if (!flag) test01(args);
		if (flag) test02(args);
	}
}
