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
package tain.kr.com.test.clazz.v01;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ClassTest04Main.java
 *   -. Package    : tain.kr.com.test.clazz.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 16. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class ClassTest04Main {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ClassTest04Main.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public ClassTest04Main()
	{
		if (flag) log.debug("Constructor in");
		
		if (flag) log.debug("1 >" + ClassTest04Main.class);
		if (flag) log.debug("2 >" + getClass());
		if (flag) log.debug("3 >" + getClass().getName());
		
		if (flag) log.debug("Constructor out");
	}
	
	public void execute()
	{
		if (flag) log.debug("Method in");
		
		if (flag) log.debug("1 >" + ClassTest04Main.class);
		if (flag) log.debug("2 >" + getClass());
		if (flag) log.debug("3 >" + getClass().getName());
		
		if (flag) log.debug("Method out");
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			ClassTest04Main cls = new ClassTest04Main();
			cls.execute();
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (flag) test01(args);
	}
}
