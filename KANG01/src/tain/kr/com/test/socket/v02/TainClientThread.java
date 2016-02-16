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
package tain.kr.com.test.socket.v02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TainClientThread.java
 *   -. Package    : tain.kr.com.test.socket.v02
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 16. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class TainClientThread extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TainClientThread.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private int idxThr = -1;
	private String host = null;
	private String port = null;
	
	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;

	public TainClientThread(int idxThr, String host, String port) throws Exception {
		
		if (flag) {
			this.idxThr = idxThr;
			this.host = host;
			this.port = port;
			if (flag) log.debug(String.format("%s : idxThr=%d, host=%s, port=%s", this.getName(), this.idxThr, this.host, this.port));
			
			this.socket = new Socket(this.host, Integer.parseInt(this.port));
			this.dis = new DataInputStream(this.socket.getInputStream());
			this.dos = new DataOutputStream(this.socket.getOutputStream());
			if (flag) log.debug("Connection .....");
		}
	}
	
	public void run() {
		
		if (flag) {
			try {
				
				byte[] packet = null;
				
				if (flag) {
					/*
					 * create a request
					 */
					
					packet = PacketHeader.makeBytes();
					PacketHeader.TR_CODE.setVal(packet, "TR0010");
					if (!flag) log.debug("[" + new String(packet) + "]");
				}
				
				if (flag) {
					/*
					 * send the request
					 */
					
					dos.write(packet, 0, PacketHeader.getLength());
					if (flag) log.debug(String.format("-> REQ SEND DATA [%s]", new String(packet)));
				}
				
				if (flag) {
					/*
					 * recv the response of the request
					 */
					
					packet = recv(PacketHeader.getLength());
					if (flag) log.debug(String.format("<- RES RECV DATA [%s]", new String(packet)));
				}
				
				if (flag) {
					/*
					 * finish
					 */
					
					try { Thread.sleep(2000); } catch (InterruptedException e) {}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (this.dis != null) try { this.dis.close(); } catch (Exception e) {}
				if (this.dos != null) try { this.dos.close(); } catch (Exception e) {}
				if (this.socket != null) try { this.socket.close(); } catch (Exception e) {}
			}
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
