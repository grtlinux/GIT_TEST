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
package tain.kr.com.test.deploy.v01.server.tr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import tain.kr.com.test.deploy.v01.common.Exec;
import tain.kr.com.test.deploy.v01.common.PacketHeader;
import tain.kr.com.test.deploy.v01.common.ParamMap;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TR0001.java
 *   -. Package    : tain.kr.com.test.deploy.v01.server.tr
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 25. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@SuppressWarnings("unused")
public class TR0101 {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TR0101.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private String className = null;
	private String trCode = null;
	private ResourceBundle resourceBundle = null;
	private String comment = null;
	
	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	
	private byte[] header = null;
	
	private byte[] body = null;
	private int bodyLen = 0;

	private String execCmd = null;
	private String execLog = null;

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public TR0101(Socket socket, DataInputStream dis, DataOutputStream dos, byte[] packet) throws Exception {
		
		if (flag) {
			/*
			 * base parameter
			 */
			this.className = this.getClass().getName();
			this.trCode = this.className.substring(this.className.lastIndexOf("TR"));
			this.resourceBundle = ResourceBundle.getBundle(this.className.replace('.', '/'));
			this.comment = this.resourceBundle.getString("tain.comment");
		}
		
		if (flag) {
			/*
			 * parameters
			 */
			this.execCmd = ParamMap.getInstance().get("tain.client.exec.cmd");
			if (this.execCmd == null) {
				this.execCmd = this.resourceBundle.getString("tain.client.exec.cmd");
			}
			
			this.execLog = ParamMap.getInstance().get("tain.client.exec.log");
			if (this.execLog == null) {
				this.execLog = this.resourceBundle.getString("tain.client.exec.log");
			}
		}
		
		if (flag) {
			/*
			 * hired parameter
			 */
			this.socket = socket;
			this.dis = dis;
			this.dos = dos;
			this.header = packet;
			
			this.bodyLen = Integer.parseInt(PacketHeader.BODY_LEN.getString(this.header));
		}
		
		if (flag) {
			String yyyymmdd = PacketHeader.TR_DATE.getString(this.header);
			String hhmmss = PacketHeader.TR_TIME.getString(this.header);
			
			this.execLog = this.execLog.replaceAll("YYYYMMDD", yyyymmdd);
			this.execLog = this.execLog.replaceAll("HHMMSS", hhmmss);
		}

		if (flag) {
			/*
			 * print information
			 */
			log.debug(">>>>> " + this.className);
			log.debug(">>>>> " + this.comment);
			log.debug(">>>>> trCode = " + this.trCode);
			log.debug(">>>>> exec cmd = " + this.execCmd);
			log.debug(">>>>> exec log = " + this.execLog);
		}
	}
	
	public void execute() throws Exception {
		
		if (flag) {
			/*
			 * 2. recv data
			 */

			this.body = recv(this.bodyLen);
			if (flag) log.debug(String.format("<- 2. REQ RECV DATA   [%s]", new String(this.body)));
		}
		
		if (flag) {
			/*
			 * 3. execute job
			 */
			
			if (flag) {
				// Exec.run
				if (!flag) Exec.run(new String[] {"cmd", "/c", "D:/TR500.cmd"}, false);
				if (!flag) Exec.run(new String[] {"cmd", "/c", "start"}, false);
				if (!flag) Exec.run(new String[] {"cmd", "/c", "M:/TEMP/DEPLOY_TEST/CLIENT/mvn_dos.bat"}, false);

				if (flag) Exec.run(new String[] {"cmd", "/c", this.execCmd}, new FileWriter(this.execLog), true);
			}

			if (flag) {
				// make return body

				this.body = "TR0101_OK".getBytes("EUC-KR");
				this.bodyLen = this.body.length;

				if (flag) log.debug(String.format("-- 3. DATA [%d:%s]", this.bodyLen, new String(this.body)));
			}
		}
		
		if (flag) {
			/*
			 * 4. send header
			 */

			this.header = PacketHeader.makeBytes();
			PacketHeader.TR_CODE.setVal(this.header, this.trCode);
			PacketHeader.BODY_LEN.setVal(this.header, String.valueOf(this.bodyLen));
			PacketHeader.RET_CODE.setVal(this.header, "00000");
			PacketHeader.RET_MSG.setVal(this.header, "SUCCESS");
			
			this.dos.write(this.header, 0, this.header.length);
			if (flag) log.debug(String.format("-> 4. RES SEND HEADER [%s]", new String(this.header)));
		}
		
		if (flag) {
			/*
			 * 5. send data
			 */

			this.dos.write(this.body, 0, this.bodyLen);
			if (flag) log.debug(String.format("-> 5. RES SEND DATA   [%s]", new String(this.body)));
		}
		
		if (flag) {
			/*
			 * 6. post job
			 */
			
			if (flag) log.debug(String.format("-- 6. [%s] process is OK!!", this.trCode));
		}
	}
	
	private byte[] recv(final int size) throws Exception {
		
		int ret = 0;
		int readed = 0;
		byte[] buf = new byte[size];
		
		this.socket.setSoTimeout(0);
		while (readed < size) {
			ret = this.dis.read(buf, readed, size - readed);
			if (!flag) log.debug("    size:" + size + "    readed:" + readed + "     ret:" + ret);
			
			if (ret <= 0) {
				try { Thread.sleep(1000); } catch (Exception e) {}
				continue;
			} else {
				if (flag) this.socket.setSoTimeout(1000);
			}
			
			readed += ret;
		}
		
		return buf;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
}

/*
@echo on

:: ----------------------------------------------------------------------------
:: set environment

set JAVA_HOME=M:\PROG\jdk1.7.0_79
set M2_HOME=P:\maven\apache-maven-3.3.3
set PATH=%PATH%;%JAVA_HOME%\bin;%M2_HOME%\bin;

set JOB_HOME=M:\TEMP\DEPLOY_TEST\CLIENT

:: ----------------------------------------------------------------------------
:: version check

cmd /c svnserve --version

cmd /c java -version

cmd /c mvn --version

:: pause

:: ----------------------------------------------------------------------------
:: export

del %JOB_HOME%\SASEMARTCMS-1.0.0.war

rmdir /S /Q   %JOB_HOME%\SASEMARTCMS

cmd /c svn export svn://localhost/REPO_02/SASEMARTCMS   %JOB_HOME%\SASEMARTCMS

echo "########################## EXPORT SUCCESS ###########################"

:: pause

:: ----------------------------------------------------------------------------
:: build

cmd /c mvn -file  %JOB_HOME%\SASEMARTCMS       clean install

echo "########################## MAVEN COMPILE SUCCESS ###########################"


:: ----------------------------------------------------------------------------
:: finish

move %JOB_HOME%\SASEMARTCMS\target\SASEMARTCMS-1.0.0.war %JOB_HOME%

echo "########################## ALL SUCCESS ###########################"


 */

