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
package tain.kr.com.test.enumtest.v02;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : EnumProtoTestMain.java
 *   -. Package    : tain.kr.com.test.enumtest.v02
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 1. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class EnumProtoTestMain {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(EnumProtoTestMain.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static void test01(String[] args) throws Exception {
		
		if (!flag) {
			String name = new Object(){}.getClass().getEnclosingClass().getName();
			
			int index = name.lastIndexOf("Test");
			if (index < 0) {
				throw new Exception("class error");
			}
			
			String trCode = name.substring(index);
			log.debug(">" + trCode);
		}
		
		if (!flag) {
			int len = 0;
			for (EnumProtoTR0100 fldTr : EnumProtoTR0100.values()) {
				len += fldTr.getLen();
			}
			
			if (flag) log.debug("Total Length = " + len);
		}
		
		if (!flag) {
			EnumProtoTR0100.print();
			
			if (flag) System.out.println();
			
			EnumProtoTR0200.print();
		}
	}
	
	private static void test02(String[] args) throws Exception {
		
		if (flag) {
			if (flag) log.debug("TR0100 > " + new String(EnumProtoTR0100.makeBytes()));
			if (flag) log.debug("TR0200 > " + new String(EnumProtoTR0200.makeBytes()));
		}

		if (flag) {
			byte[] bytes = EnumProtoTR0200.makeBytes();
			EnumProtoTR0200.FILLER2.fill(bytes, (byte) '-');
			if (flag) log.debug("TR0200 > " + new String(bytes));
		}

	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug("" + new Object(){}.getClass().getEnclosingClass().getName());
		
		if (!flag) test01(args);
		if (flag) test02(args);
	}
}
