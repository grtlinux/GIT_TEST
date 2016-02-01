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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;


/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : EnumProtoTR0100.java
 *   -. Package    : tain.kr.com.test.enumtest.v02
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 1. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public enum EnumProtoTR0100 {

	// type, lead, off, len, name, desc, defVal
	
	TR_LEN            ('N', '0',   0,   4, "TR_LEN"        , "TR 길이"         , "0100"),
	TR_CODE           ('C', ' ',   4,   6, "TR_CODE"       , "TR 코드"         , "TR0100"),
	TR_DATE           ('C', '0',  10,   8, "TR_DATE"       , "TR 발생일자"     , "YYYYMMDD"),
	TR_TIME           ('C', '0',  18,   6, "TR_TIME"       , "TR 발생시간"     , "HHMMSS"),
	TR_USER           ('C', ' ',  24,  10, "TR_USER"       , "TR 사용자"       , "USER"),
	TR_PASS           ('C', ' ',  34,  10, "TR_PASS"       , "TR 비밀번호"     , "PASS"),
	KEY_CODE          ('C', ' ',  44,  10, "KEY_CODE"      , "키코드"          , "0000000000"),
	DATA_LEN          ('N', '0',  54,  15, "DATA_LEN"      , "DATA 길이"       , "000000000000000"),
	FILLER            ('C', '.',  69,  31, "FILLER"        , "RESERVED "       , ""),
	;
	
	private final char type;
	private final char lead;
	private final int off;
	private final int len;
	private final String name;
	private final String desc;
	private final String defVal; 
	
	public char getType() {
		return this.type;
	}
	
	public char getLead() {
		return this.lead;
	}
	
	public int getOff() {
		return this.off;
	}
	
	public int getLen() {
		return this.len;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDesc() {
		return this.desc;
	}
	
	public String getDefVal() {
		return this.defVal;
	}
	
	/**
	 * 
	 * Code Templates > Comments > Constructors
	 *
	 * <PRE>
	 *   -. ClassName  : EnumProtoTR0100
	 *   -. MethodName : EnumProtoTR0100
	 *   -. Comment    :
	 *   -. Author     : taincokr
	 *   -. First Date : 2016. 2. 1. {time}
	 * </PRE>
	 *
	 * @param type
	 * @param lead
	 * @param off
	 * @param len
	 * @param name
	 * @param desc
	 * @param defVal
	 */
	EnumProtoTR0100(char type, char lead, int off, int len, String name, String desc, String defVal) {
		this.type = type;
		this.lead = lead;
		this.off = off;
		this.len = len;
		this.name = name;
		this.desc = desc;
		this.defVal = defVal;
	}
	
	////////////////////////////////////////////////////////////////////

	public void fill(byte[] bytes, byte by) {
		
		for (int i=0; i < this.len; i++) {
			bytes[this.off + i] = by;
		}
	}

	public void setVal(byte[] bytes) {
		setVal(bytes, null);
	}
	
	public void setVal(byte[] bytes, String val) {
		
		if (val == null) {
			// clear the exist string
			for (int i=0; i < this.len; i++) {
				bytes[this.off + i] = (byte) ' ';
			}
			
			return;
		}
		
		byte[] byVal = val.getBytes();
		int byLen = byVal.length;
		
		if (this.type == 'C') {
			// left arrange
			
			for (int i=0, j=0; i < this.len; i++, j++) {
				if (j < byLen) {
					bytes[this.off + i] = byVal[j];
				} else {
					bytes[this.off + i] = (byte) this.lead;
				}
			}
			
		} else if (this.type == 'N') {
			// right arrange
			
			for (int i=0, j=0; i < this.len; i++, j++) {
				if (j < byLen) {
					bytes[this.off + this.len - 1 - i] = byVal[byLen - 1 - j];
				} else {
					bytes[this.off + this.len - 1 - i] = (byte) this.lead;
				}
			}
		}
	}

	public String getVal(byte[] bytes) {
		return new String(bytes, this.off, this.len);
	}
	
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////

	private static boolean flag = true;
	
	private static final Logger log = Logger.getLogger(EnumProtoTR0100.class);
	
	private static int lenTotal = -1;

	private static void setLenTotal() throws Exception {
		
		if (flag) {
			int off = 0;

			for (EnumProtoTR0100 fld : EnumProtoTR0100.values()) {
				off += fld.getLen();
			}
			
			lenTotal = off;
		}
	}
	
	public static byte[] makeBytes() throws Exception {
		
		if (lenTotal < 0) setLenTotal();
		
		byte[] bytes = new byte[lenTotal];
		
		if (!flag) {
			for (int i=0; i < lenTotal; i++) {
				bytes[i] = (byte) ' ';
			}
		}
		
		if (flag) {
			for (EnumProtoTR0100 fld : EnumProtoTR0100.values()) {
				fld.setVal(bytes, fld.getDefVal());
			}
		}

		if (flag) {
			String name = new Object(){}.getClass().getEnclosingClass().getName();
			int index = name.lastIndexOf("TR");
			if (index < 0)
				throw new Exception("couldn't get tr-code from class name.");
			
			String trCode = name.substring(index);
			
			TR_LEN  .setVal(bytes, String.valueOf(lenTotal));
			TR_CODE .setVal(bytes, trCode);
			TR_DATE .setVal(bytes, new SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(new Date()));
			TR_TIME .setVal(bytes, new SimpleDateFormat("HHmmss"  , Locale.KOREA).format(new Date()));
			TR_USER .setVal(bytes, "QWERT12345");
			TR_PASS .setVal(bytes, "1Q2WER4RKD");
			KEY_CODE.setVal(bytes, "FK39SXK3M3");
			DATA_LEN.setVal(bytes, String.valueOf(0));
			FILLER  .setVal(bytes, "");
		}

		return bytes;
	}
	
	public static byte[] clearBytes(byte[] bytes) throws Exception {
		
		if (lenTotal < 0) setLenTotal();

		for (int i=0; i < lenTotal; i++) {
			bytes[i] = (byte) ' ';
		}
		
		return bytes;
	}
	
	public static int getLength() throws Exception {
		
		if (lenTotal < 0) setLenTotal();
		
		return lenTotal;
	}
	
	public static void print() throws Exception {
		
		if (flag) {
			String name = new Object(){}.getClass().getEnclosingClass().getName();
			
			int len = 0;
			int off = 0;
			for (EnumProtoTR0100 fld : EnumProtoTR0100.values()) {
				len = fld.getLen();
				
				if (flag) log.debug(String.format("[%s] [%s] [%3d:%3d] [%3d:%3d] [%20s] [%s] [%s]"
						, name , fld.getType(), off, fld.getOff(), len, fld.getLen(), fld.getName(), fld.getDesc(), fld.getDefVal()));
				
				off += fld.getLen();
			}
			
			if (flag) log.debug("Total Length = " + off);
		}
	}
}
