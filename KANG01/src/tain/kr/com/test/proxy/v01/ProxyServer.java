package tain.kr.com.test.proxy.v01;

import java.net.ServerSocket;
import java.net.Socket;

public class ProxyServer {

	private static boolean flag = true;
	
	private static final int PORT = 4096;
	
	private static void test01(String[] args) {
		
		if (flag) {
			
			// server socket
			ServerSocket serverSocket = null;
			
			try {
				serverSocket = new ServerSocket(PORT);
				if (flag) System.out.printf("PROXY_SERVER : ServerSocket Listening.. port is %d  [%s]\n", PORT, serverSocket.toString());
				
				for (int i=0; ; i++) {
					if (i > 1000000)
						i = 0;
					
					Socket socket = serverSocket.accept();
					if (flag) System.out.printf("PROXY_SERVER : accept of connection from client...[%s]\n", socket.toString());
					
					// synch thread
					new ProxyThread(i, socket).start();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		if (flag) test01(args);
	}
}
