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
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ClassTest01Main.java
 *   -. Package    : tain.kr.com.test.clazz.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 16. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class ClassTest01Main {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ClassTest01Main.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug(">" + new Object(){}.getClass().getEnclosingClass().getName());
		
		if (flag) new MethodTest();
	}
}

class MethodTest
{
	public MethodTest()
	{
		methodTest();
	}
	
	public List<Integer> getList()
	{
		List<Integer> list = new ArrayList<Integer>();
		
		list.add(new Integer(1));
		list.add(new Integer(2));
		list.add(new Integer(3));
		
		return list;
	}
	
	private void methodTest()
	{
		try {
			Method method = this.getClass().getMethod("getList", new Class[] {});
			
			@SuppressWarnings("unchecked")
			List<Integer> list = (List<Integer>) method.invoke(this, new Object[] {});
			
			for (Integer integer : list) {
				System.out.println(">" + integer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
