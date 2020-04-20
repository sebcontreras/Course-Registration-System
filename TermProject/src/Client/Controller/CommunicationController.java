package Client.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class CommunicationController {
	
	private Socket socket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	
	public CommunicationController(String serverName, int portNumber) {
		try {
			socket = new Socket(serverName, portNumber);
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream(), true);
		}catch(IOException  e) {
			e.printStackTrace();
		}
	}
	
	public String communicate (String line) {
		socketOut.println(line);
		String response = "";
		while (true) {
			try {
				response = response+socketIn.readLine()+"\n";
			}catch(IOException e) {
				e.printStackTrace();
			}
			if (response.contains("\0")) {
				response = response.replace("\0", "");
				break;
			}
		}
		return response;
	}
	
	

	
	

	
}
