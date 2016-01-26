package tain.kr.com.test.sync.v01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SasSyncClient {
	
	private static boolean flag = true;
	
	private static final String IPADDR = "127.0.0.1";
	private static final int PORT = 4096;
	
	private Socket socket = null;
	private BufferedReader in = null;
	private PrintStream out = null;
	
	public SasSyncClient() {
		if (flag) {
			try {
				socket = new Socket(IPADDR, PORT);
				if (flag) System.out.printf("CLIENT STATUS : connecting to SERVER\n");
				
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintStream(socket.getOutputStream());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start() {
		
		if (flag) {
			try {
				if (flag) {
					/*
					 * TODO : REQUEST TICKET
					 * send a request message to the server
					 */
					String req = "REQUEST TICKET";
					out.println(req);
					
					if (flag) System.out.printf("SAS_SYNC_CLIENT : [%s]\n", req);
				}

				if (flag) {
					/*
					 * TODO : RESPONSE TICKET
					 * receive a response message from the server
					 */
					String res = null;
					
					while ((res = in.readLine()) != null) {
						if (flag) System.out.printf("SAS_SYNC_CLIENT : [%s]\n", res);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void end() {
		
		if (flag) {
			try {
				if (flag) {
					/*
					 * TODO : RELEASE TICKET
					 * send a release message to the server and close job
					 */
					String req = "RELEASE TICKET";
					out.println(req);
					
					if (flag) System.out.printf("SAS_SYNC_CLIENT : [%s]\n", req);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close() {
		
		if (flag) {
			/*
			 * close the resource
			 */
			try {
				if (socket != null) {
					socket.close();
					in.close();
					out.close();
				}
			} catch (Exception e) {}
		}
		
		if (flag) System.out.printf("SYS_SYNC_CLIENT : close SasSyncClient...\n");
	}
	
	private static void test01(String[] args) {
		
		if (flag) {
			Socket socket = null;
			
			BufferedReader in = null;
			PrintStream out = null;
			
			try {
				socket = new Socket(IPADDR, PORT);
				if (flag) System.out.printf("CLIENT STATUS : connecting to SERVER\n");
				
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintStream(socket.getOutputStream());
				
				if (flag) {
					/*
					 * TODO : REQUEST TICKET
					 * send a request message to the server
					 */
					String req = "REQUEST TICKET";
					out.println(req);
					
					if (flag) System.out.printf("SAS_SYNC_CLIENT : [%s]\n", req);
				}

				if (flag) {
					/*
					 * TODO : RESPONSE TICKET
					 * receive a response message from the server
					 */
					String res = null;
					
					while ((res = in.readLine()) != null) {
						if (flag) System.out.printf("SAS_SYNC_CLIENT : [%s]\n", res);
						break;
					}
				}
				
				/*
				 * TODO : start job
				 * processing job required long time.
				 */
				try { Thread.sleep(10 * 1000); } catch (InterruptedException e) {}

				
				if (flag) {
					/*
					 * TODO : RELEASE TICKET
					 * send a release message to the server and close job
					 */
					String req = "RELEASE TICKET";
					out.println(req);
					
					if (flag) System.out.printf("SAS_SYNC_CLIENT : [%s]\n", req);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null) {
						socket.close();
						in.close();
						out.close();
					}
				} catch (Exception e) {}
			}
			
			if (flag) System.out.printf("SYS_SYNC_CLIENT : close SasSyncClient...\n");
		}
	}
	
	private static void test02(String[] args) {
		
		if (flag) {
			SasSyncClient client = new SasSyncClient();
			
			for (int i=0; i < 1; i++) {
				client.start();
				
				// do a hard job.
				if (flag) try { Thread.sleep(5 * 1000); } catch (InterruptedException e) {}
				
				client.end();
			}
			
			client.close();
		}
	}

	public static void main(String[] args) {
		if (!flag) test01(args);
		if (flag) test02(args);
	}
}
