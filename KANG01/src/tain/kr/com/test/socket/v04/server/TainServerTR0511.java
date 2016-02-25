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
package tain.kr.com.test.socket.v04.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.net.Socket;

import org.apache.log4j.Logger;

import tain.kr.com.test.socket.v04.common.Exec;
import tain.kr.com.test.socket.v04.common.PacketHeader;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TainServerThread.java
 *   -. Package    : tain.kr.com.test.socket.v02
 *   -. Comment    :
 *        @echo on
 *
 *        :: program : mvn_dos.ba
 *        ::
 *        :: ----------------------------------------------------------------------------
 *        :: set environment
 *
 *        set JAVA_HOME=M:\PROG\jdk1.7.0_79
 *        set M2_HOME=P:\maven\apache-maven-3.3.3
 *        set PATH=%PATH%;%JAVA_HOME%\bin;%M2_HOME%\bin;
 *
 *        :: ----------------------------------------------------------------------------
 *        :: version check
 *
 *        cmd /c svnserve --version
 *
 *        cmd /c java -version
 *
 *        cmd /c mvn --version
 *
 *        :: pause
 *
 *        :: ----------------------------------------------------------------------------
 *        :: export
 *
 *        del M:\TEMP\DEPLOY_TEST\CLIENT\SASEMARTCMS-1.0.0.war
 *
 *        rmdir /S /Q                                      M:\TEMP\DEPLOY_TEST\CLIENT\SASEMARTCMS
 *
 *        cmd /c svn export svn://localhost/REPO_02/SASEMARTCMS   M:\TEMP\DEPLOY_TEST\CLIENT\SASEMARTCMS
 *
 *        echo "########################## EXPORT SUCCESS ###########################"
 *
 *        :: pause
 *
 *        :: ----------------------------------------------------------------------------
 *        :: build
 *
 *        cmd /c mvn -file  M:\TEMP\DEPLOY_TEST\CLIENT\SASEMARTCMS       clean install
 *
 *        echo "########################## MAVEN COMPILE SUCCESS ###########################"
 *
 *
 *        :: ----------------------------------------------------------------------------
 *        :: finish
 *
 *        move M:\TEMP\DEPLOY_TEST\CLIENT\SASEMARTCMS\target\SASEMARTCMS-1.0.0.war M:\TEMP\DEPLOY_TEST\CLIENT
 *
 *        echo "########################## ALL SUCCESS ###########################"
 *    
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 16. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@SuppressWarnings("unused")
public class TainServerTR0511 {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TainServerTR0511.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private byte[] packet = null;

	public TainServerTR0511(Socket socket, DataInputStream dis, DataOutputStream dos, byte[] packet) throws Exception {
		
		if (flag) {
			this.socket = socket;
			this.dis = dis;
			this.dos = dos;
			this.packet = packet;
		}
	}
	
	public byte[] execute() throws Exception {
	
		if (flag) {
			/*
			 * execute shell program
			 */
			if (!flag) Exec.run(new String[] {"cmd", "/c", "D:/TR500.cmd"}, false);
			if (!flag) Exec.run(new String[] {"cmd", "/c", "start"}, false);
			if (flag) Exec.run(new String[] {"cmd", "/c", "M:/TEMP/DEPLOY_TEST/SERVER/mvn_server_dos.bat"}, new FileWriter("M:/TEMP/DEPLOY_TEST/SERVER/TR0511.log"), false);
			if (!flag) Exec.run(new String[] {"cmd", "/c", "M:/TEMP/DEPLOY_TEST/CLIENT/mvn_dos.bat"}, false);
		}
		
		if (flag) {
			PacketHeader.TR_CODE.setVal(this.packet, "TR0511");
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
