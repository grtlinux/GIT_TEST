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
import java.io.FileOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import tain.kr.com.test.socket.v04.common.PacketHeader;

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
public class TainServerTR0211 {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TainServerTR0211.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private byte[] packet = null;
	
	private long fileSize = -1;
	private String fileName = "M:/TEMP/DEPLOY_TEST/SERVER/SASEMARTCMS-1.0.0.war";

	public TainServerTR0211(Socket socket, DataInputStream dis, DataOutputStream dos, byte[] packet) throws Exception {
		
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
			 * get file size
			 */
			this.fileSize = Long.parseLong(PacketHeader.DATA_LEN.getString(this.packet));
			
			if (flag) log.debug(String.format("fileSize = %,d", this.fileSize));
		}
	
		if (flag) {
			/*
			 * file receiver
			 */
			
			FileOutputStream fos = null;
			
			try {
				
				fos = new FileOutputStream(this.fileName);
				
				byte[] buf = new byte[10240];
				
				for (int i=1; ; i++) {
				
					int readed = this.dis.read(buf);
					if (readed < 0)
						break;
					
					fos.write(buf, 0, readed);
					
					if (flag) {
						System.out.print("#");
						if (i % 100 == 0)
							System.out.println();
					}
					
					fileSize -= readed;
					if (fileSize <= 0) {
						if (flag) System.out.println();
						break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fos != null) try { fos.close(); } catch (Exception e) {}
			}
		}
		
		if (flag) {
			PacketHeader.TR_CODE.setVal(this.packet, "TR0201");
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
