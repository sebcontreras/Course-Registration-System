package Client.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import Client.Model.*;

//
public class CommunicationController {
	
	private Socket socket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private ObjectOutputStream clientOutputStream;
	private ObjectInputStream clientInputStream;
	
	
	public CommunicationController(String serverName, int portNumber) {
		try {
			Student joe = new Student("joe", 1000);
			//initial name
			System.out.println("Name: "+joe.getStudentName()+" ID: "+joe.getStudentId());
			
			socket = new Socket(serverName, portNumber);
			
			clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
			clientInputStream = new ObjectInputStream(socket.getInputStream());
			
			clientOutputStream.writeObject(joe);
			//waiting for changes from server to be applied
			joe = (Student)clientInputStream.readObject();
			//name should be changed after server messes with it.
			System.out.println("Name: "+joe.getStudentName()+" ID: "+joe.getStudentId());
			
			//Should these close after?
			clientOutputStream.close();
			clientInputStream.close();
			
			//socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			//socketOut = new PrintWriter((aSocket.getOutputStream()), true);
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void CommunicateWithServer() {
		String response = "";
		while(true) {
			try {
				//send a number to tell the server what action to perform
				response = socketIn.readLine();
				//send response to where its needed in the client package
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws IOException  {
		CommunicationController aClient = new CommunicationController("localhost", 8099);
		aClient.CommunicateWithServer();
	}
	
}
