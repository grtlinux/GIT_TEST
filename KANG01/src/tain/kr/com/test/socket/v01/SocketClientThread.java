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
 *   -. FileName   : SocketClientThread.java
 *   -. Package    : tain.kr.com.test.socket.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 2. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class SocketClientThread extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(SocketClientThread.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private int idxThread = -1;
	private String host = null;
	private String port = null;
	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	
	public SocketClientThread(int idxThread, String host, String port) throws Exception {
		
		if (flag) {
			this.idxThread = idxThread;
			this.host = host;
			this.port = port;
			
			if (flag) log.debug(String.format("%s : idxThread=%d host=%s port=%s", this.getName(), this.idxThread, this.host, this.port));
		}
	}

	public void run() {
		
		if (flag) {
			
			try {
				this.socket = new Socket(this.host, Integer.parseInt(this.port));
				if (flag) log.debug("Connection....");
				
				this.dis = new DataInputStream(this.socket.getInputStream());
				this.dos = new DataOutputStream(this.socket.getOutputStream());
				
				byte[] bytes = null;
				
				if (flag) {
					/*
					 * make a packet for sending
					 */
					bytes = EnumTR0000.makeBytes();
					if (!flag) log.debug("[" + new String(bytes) + "]");
				}
				
				if (flag) {
					/*
					 * send a packet for request
					 */
					dos.write(bytes, 0, EnumTR0000.getLength());
					if (flag) log.debug(String.format("-> SEND DATA [%s]", new String(bytes)));
				}
				
				if (flag) {
					/*
					 * recv a packet for response
					 */
					byte[] trLen = recv(4);
					int size = Integer.parseInt(new String(trLen));
					if (flag) log.debug(String.format("<- RECV LEN  [%s][%d]", new String(trLen), size));
					
					byte[] trData = recv(size - 4);
					if (flag) log.debug(String.format("<- RECV DATA [%s]", new String(trData)));
					
					bytes = EnumTR0001.setBytes(trLen, trData);
					if (flag) log.debug(String.format("<- RECV [%s]", new String(bytes)));
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
