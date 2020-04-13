package Client.Controller;

import java.io.IOException;
import java.net.Socket;

//
public class CommunicationController {
	
	private Socket aSocket;
	
	public CommunicationController(String serverName, int portNumber) {
		try {
			aSocket = new Socket(serverName, portNumber);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void CommunicateWithServer() {
		
	}
	
	public static void main(String[] args) throws IOException  {
		CommunicationController aClient = new CommunicationController("localhost", 8099);
		aClient.CommunicateWithServer();
	}
}
