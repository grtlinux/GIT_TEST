package tain.kr.com.test.proxy.v01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ProxyThread extends Thread {

	private static boolean flag = true;

	private String thrName = null;
	
	private Socket socket = null;

	private BufferedReader in = null;
	private PrintStream out = null;

	public ProxyThread(int thrNo, Socket socket) {
		
		if (flag) {
			this.thrName = String.format("THR-%06d", thrNo);
			this.socket = socket;

			try {
				this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				this.out = new PrintStream(socket.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close() {
		
		if (flag) {
			try {
				if (socket != null) {
					socket.close();
					in.close();
					out.close();
				}
			} catch (Exception e) {}
		}
	}
	
	public void run() {
		
		if (flag) {
			try {
				
				if (flag) {
					/*
					 * receive request message.
					 */
					String req = null;
					
					for (int i=0; i < 20 && (req = in.readLine()) != null; i++) {
					// while ((req = in.readLine()) != null) {
						if (flag) System.out.printf("[%s] PROXY_THREAD : [%s]\n", this, req);
						//break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		close();
		
		if (flag) System.out.printf("[%s] PROXY_THREAD : ProxyThread is closed..\n", this);
	}
	
	public String toString() {
		return this.thrName;
	}
}
