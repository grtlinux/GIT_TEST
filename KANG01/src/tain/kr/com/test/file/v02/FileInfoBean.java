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

/**
 * 
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : FileInfoBean.java
 *   -. Package    : tain.kr.com.test.file.v02
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 2. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class FileInfoBean {

	private static boolean flag = true;
	
	private String type;   // file type : RF(Remote File), LF(Local File), RD(Remote File Download), LU(Local File Upload)
	private String base;   // base path name
	private String name;   // file name except base path name
	private long time;    // last modified time by millisecond
	private long length;  // file size
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		if (flag) {
			sb.append(this.type).append("||");
			sb.append(this.base).append("||");
			sb.append(this.name).append("||");
			sb.append(this.time).append("||");
			sb.append(this.length);
		}
		
		return sb.toString();
	}
}
