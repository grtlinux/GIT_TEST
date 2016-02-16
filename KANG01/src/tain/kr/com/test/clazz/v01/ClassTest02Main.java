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

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ClassTest02Main.java
 *   -. Package    : tain.kr.com.test.clazz.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 16. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class ClassTest02Main {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ClassTest02Main.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args)
	{
		if (flag) log.debug(">" + new Object(){}.getClass().getEnclosingClass().getName());
		
		try {
			Super obj = new Sub();
			Class<?> cls = obj.getClass();
			Method method = cls.getMethod("print", new Class[] {});
			
			Integer ret = (Integer) method.invoke(obj, new Object[] {});
			System.out.println(">" + ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

interface Super
{
	
}

class Sub implements Super
{
	public int print()
	{
		System.out.println("Hello");
		return 1;
	}
}
