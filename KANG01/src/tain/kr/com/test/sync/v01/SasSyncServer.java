package tain.kr.com.test.sync.v01;

import java.net.ServerSocket;
import java.net.Socket;

public class SasSyncServer {

	private static boolean flag = true;
	
	private static final int PORT = 4096;
	
	private static void test01(String[] args) {
		
		if (flag) {
			
			// ticket manager thread
			TicketManager manager = new TicketManager();
			manager.start();
			
			// server socket
			ServerSocket serverSocket = null;
			
			try {
				serverSocket = new ServerSocket(PORT);
				if (flag) System.out.printf("SAS_SYNC_SERVER : ServerSocket Listening.. port is %d\n", PORT);
				
				for (int i=0; ; i++) {
					if (i > 1000000)
						i = 0;
					
					Socket socket = serverSocket.accept();
					if (flag) System.out.printf("SYS_SYNC_SERVER : accept of connection from client...\n");
					
					// synch thread
					new SyncThread(i, socket, manager).start();
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
