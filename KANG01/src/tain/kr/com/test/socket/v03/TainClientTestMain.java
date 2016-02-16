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
package tain.kr.com.test.socket.v03;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TainClientTestMain.java
 *   -. Package    : tain.kr.com.test.socket.v02
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 16. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class TainClientTestMain {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TainClientTestMain.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static final String HOST = "127.0.0.1";
	private static final String PORT = "2025";
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception {
		
		if (!flag) {
			/*
			 * file transfer
			 */
			
			Thread thr = new TainClientTR0000(HOST, PORT);
			
			thr.start(); // start thread
			
			thr.join();  // wait for thread exit
		}

		if (!flag) {
			/*
			 * file transfer
			 */
			
			Thread thr = new TainClientTR0200(HOST, PORT);
			
			thr.start(); // start thread
			
			thr.join();  // wait for thread exit
		}

		if (flag) {
			/*
			 * file transfer
			 */
			
			Thread thr = new TainClientTR0500(HOST, PORT);
			
			thr.start(); // start thread
			
			thr.join();  // wait for thread exit
		}
	}
	
	private static void test02(String[] args) throws Exception {
		
	}
	
	private static void test03(String[] args) throws Exception {
		
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug(">" + new Object(){}.getClass().getEnclosingClass().getName());
		
		if (flag) test01(args);
		if (!flag) test02(args);
		if (!flag) test03(args);
	}
}
