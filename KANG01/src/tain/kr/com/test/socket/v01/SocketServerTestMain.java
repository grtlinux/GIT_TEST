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

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : SocketServerTestMain.java
 *   -. Package    : tain.kr.com.test.socket.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 2. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class SocketServerTestMain {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(SocketServerTestMain.class);
	
	private static String className = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	// private static final String HOST = "127.0.0.1";
	private static final String PORT = "2025";
	
	private static void execute() throws Exception {
		
		if (flag) {
			/*
			 * first socket program
			 */
			
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(Integer.parseInt(PORT));
			if (flag) log.debug(String.format("%s : listening by port %s [%s]", className, PORT, serverSocket.toString()));

			for (int idxThread = 0; ; idxThread ++) {
				if (idxThread > 10000000)
					idxThread = 0;
				
				Socket socket = serverSocket.accept();
				if (flag) log.debug(String.format("%s : accept the connection(%d) from client [%s]", className, idxThread, socket.toString()));
				
				new SocketServerThread(idxThread, socket).start();
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			execute();
		}
	}
	
	public static void main(String[] args) throws Exception {
		className = new Object(){}.getClass().getEnclosingClass().getName();
		
		if (flag) log.debug("> " + className);
		
		if (flag) test01(args);
	}
}
