package Client.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;


import Client.Model.*;
import Server.Controller.DBController;
import Server.Controller.ServerCommunicationController;

//
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
	
	public String communicate () {
		String response = null;
		while (true) {
			try {
				response = socketIn.readLine();
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
	
	public void communicateWithServer(String line) {
		socketOut.print(line);
	}
	
	
	
	

	
}
