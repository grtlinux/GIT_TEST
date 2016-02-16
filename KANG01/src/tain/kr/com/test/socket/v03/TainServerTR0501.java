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
package tain.kr.com.test.socket.v03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TainServerThread.java
 *   -. Package    : tain.kr.com.test.socket.v02
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 16. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@SuppressWarnings("unused")
public class TainServerTR0501 {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TainServerTR0501.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private byte[] packet = null;

	public TainServerTR0501(Socket socket, DataInputStream dis, DataOutputStream dos, byte[] packet) throws Exception {
		
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
			//Exec.run(new String[] {"cmd", "/c", "dir"});
			Exec.run(new String[] {"cmd", "/c", "start"});
		}
		
		if (flag) {
			PacketHeader.TR_CODE.setVal(this.packet, "TR0501");
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
