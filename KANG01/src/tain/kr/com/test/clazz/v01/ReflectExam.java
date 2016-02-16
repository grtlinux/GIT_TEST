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
 *   -. FileName   : ReflectExam.java
 *   -. Package    : tain.kr.com.test.clazz.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 16. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class ReflectExam {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ReflectExam.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			/*
			 * Object obj = [Class].newInstance();
			 * Class<?> cls = obj.getClass();
			 * cls.getMethod([methodName], [parameterClass]).invoke(obj, [parameterValue]);
			 * 
			 * parameterClass
			 *     Class[] argType = new Class[] { int.class, double.class };
			 *     
			 * parameterValue
			 *     Object[] argValue = new Object[] { 123, 1234.56 };
			 */
			try {
				Method[] methods = null;
				if (flag) {
					// TYPE-1
					methods = TargetBean.class.getMethods();
				} else {
					// TYPE-2
					Object obj = new TargetBean();
					Class<?> cls = obj.getClass();
					methods = cls.getMethods();
					
					// TYPE-3
					//methods = new TargetBean().getClass().getMethods();
				}
				for (Method method : methods) {
					if (flag) log.debug(">" + method.getName());
				}
				if (flag) log.debug("---------------------------------------------------");
				
				Method setMethod = null;
				Method getMethod = null;
				
				for (Method method : methods) {
					if ("setName".equals(method.getName())) {
						setMethod = method;
					} else if ("getName".equals(method.getName())) {
						getMethod = method;
					}
				}
				
				// create Object
				Class<?> cls = Class.forName("kiea.priv.zzz.java.lang.Class.TargetBean");
				TargetBean instance = (TargetBean) cls.newInstance();
				
				// setMethod
				setMethod.invoke(instance, "Hello Kang Seok");
				
				// getMethod
				String ret = (String) getMethod.invoke(instance);
				if (flag) log.debug("getMethod >" + ret);
				
				// instance.getName
				ret = instance.getName();
				if (flag) log.debug("getName >" + ret);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (flag) test01(args);
	}
}

class TargetBean
{
	private String name;
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
}
