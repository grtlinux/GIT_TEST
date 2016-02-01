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
package tain.kr.com.test.socket.v01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : SocketServerThread.java
 *   -. Package    : tain.kr.com.test.socket.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 2. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class SocketServerThread extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(SocketServerThread.class);
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private int idxThread = -1;
	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	
	public SocketServerThread(int idxThread, Socket socket) throws Exception {
		
		if (flag) {
			this.idxThread = idxThread;
			this.socket = socket;
			this.dis = new DataInputStream(this.socket.getInputStream());
			this.dos = new DataOutputStream(this.socket.getOutputStream());
			
			if (flag) log.debug(String.format("%s : idxThread=%d socket=%s", this.getName(), this.idxThread, this.socket.toString()));
		}
	}

	public void run() {
		
		if (flag) {
			
			try {
				byte[] bytes = null;
				
				if (flag) {
					/*
					 * recv a packet for response
					 */
					byte[] trLen = recv(4);
					int size = Integer.parseInt(new String(trLen));
					if (flag) log.debug(String.format("<- RECV LEN  [%s][%d]", new String(trLen), size));
					
					byte[] trData = recv(size - 4);
					if (flag) log.debug(String.format("<- RECV DATA [%s]", new String(trData)));
					
					bytes = EnumTR0000.setBytes(trLen, trData);
					if (flag) log.debug(String.format("<- RECV [%s]", new String(bytes)));
				}
				
				if (flag) {
					/*
					 * make a packet for sending
					 */
					bytes = EnumTR0001.makeBytes();
					if (!flag) log.debug("[" + new String(bytes) + "]");
				}
				
				if (flag) {
					/*
					 * send a packet for request
					 */
					dos.write(bytes, 0, EnumTR0001.getLength());
					if (flag) log.debug(String.format("-> SEND DATA [%s]", new String(bytes)));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (this.socket != null) try { this.socket.close(); } catch (Exception e) {}
				if (this.dis != null) try { this.dis.close(); } catch (Exception e) {}
				if (this.dos != null) try { this.dos.close(); } catch (Exception e) {}
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	public byte[] recv(final int size) throws Exception {
		
		int ret = 0;
		int readed = 0;
		byte[] buf = new byte[size];
		
		this.socket.setSoTimeout(0);
		while (readed < size) {
			ret = this.dis.read(buf, readed, size - readed);
			if (!flag) log.debug("    size:" + size + "    readed:" + readed + "     ret:" + ret);
			
			if (ret <= 0) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {}

				continue;
			} else {
				if (flag) this.socket.setSoTimeout(1000);
			}
			
			readed += ret;
		}
		
		return buf;
	}
}
