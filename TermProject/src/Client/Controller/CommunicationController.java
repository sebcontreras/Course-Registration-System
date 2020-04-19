package Client.Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import Client.Model.*;

//
public class CommunicationController {
	
	private Socket socket;
	private ObjectOutputStream clientOutputStream;
	private ObjectInputStream clientInputStream;
	private Student theStudent;
	
	
	public CommunicationController(String serverName, int portNumber) {
		try {
			
			socket = new Socket(serverName, portNumber);
			
			clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
			clientInputStream = new ObjectInputStream(socket.getInputStream());
			
			
		}catch(IOException  e) {
			e.printStackTrace();
		}
	}
	
	public void CommunicateWithServer() {
		try {
			theStudent = (Student)clientInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		while(true) {
			try {
				//assign numbers to different buttons pressed, have method that determines and returns the number? 
				//send a number to tell the server what action to perform
//				socketOut.println(choice);
				response = socketIn.readLine();
				choice = Integer.parseInt(response);
				socketOut.println(choice);
				//clientOutputStream.writeObject(theStudent);
				//the response from the server is, it sends back a Student object
				//theStudent = (Student)clientInputStream.readObject();

				//send response to where its needed in the client package
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			try {
				clientOutputStream.close();
				clientInputStream.close();
			}catch(IOException  e) {
				e.getStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws IOException  {
		CommunicationController aClient = new CommunicationController("localhost", 8099);
		aClient.CommunicateWithServer();
	}
	
}
