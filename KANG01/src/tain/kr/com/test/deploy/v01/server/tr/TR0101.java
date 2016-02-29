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
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import tain.kr.com.test.deploy.v01.common.Exec;
import tain.kr.com.test.deploy.v01.common.PacketHeader;

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
	
	private String execCmd = null;
	private String execLog = null;

	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private byte[] packet = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public TR0101(Socket socket, DataInputStream dis, DataOutputStream dos, byte[] packet) throws Exception {
		
		if (flag) {
			this.className = this.getClass().getName();
			this.trCode = this.className.substring(this.className.lastIndexOf("TR"));
			this.resourceBundle = ResourceBundle.getBundle(this.className.replace('.', '/'));
			this.comment = this.resourceBundle.getString("tain.comment");

			this.execCmd = this.resourceBundle.getString("tain.exec.cmd");
			this.execLog = this.resourceBundle.getString("tain.exec.log");
		}
		
		if (flag) {
			this.socket = socket;
			this.dis = dis;
			this.dos = dos;
			this.packet = packet;
		}
		
		if (flag) {
			String yyyymmdd = PacketHeader.TR_DATE.getString(this.packet);
			String hhmmss = PacketHeader.TR_TIME.getString(this.packet);
			
			this.execLog = this.execLog.replaceAll("YYYYMMDD", yyyymmdd);
			this.execLog = this.execLog.replaceAll("HHMMSS", hhmmss);
		}

		if (flag) {
			log.debug(">>>>> " + this.className);
			log.debug(">>>>> " + this.comment);
			log.debug(">>>>> trCode = " + this.trCode);
			log.debug(">>>>> exec cmd = " + this.execCmd);
			log.debug(">>>>> exec log = " + this.execLog);
		}
	}
	
	public byte[] execute() throws Exception {
		
		if (flag) {
			/*
			 * execute shell program.
			 */
			
			if (!flag) Exec.run(new String[] {"cmd", "/c", "D:/TR500.cmd"}, false);
			if (!flag) Exec.run(new String[] {"cmd", "/c", "start"}, false);
			if (!flag) Exec.run(new String[] {"cmd", "/c", "M:/TEMP/DEPLOY_TEST/CLIENT/mvn_dos.bat"}, false);

			if (flag) Exec.run(new String[] {"cmd", "/c", execCmd}, new FileWriter(execLog), true);
		}
	
		if (flag) {
			/*
			 * response packet header
			 */
			PacketHeader.TR_CODE.setVal(this.packet, trCode);
			PacketHeader.RET_CODE.setVal(this.packet, "00000");
			PacketHeader.FILLER.setVal(this.packet, "SUCCESS");
		}
		
		return this.packet;
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
