package Server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Server.Model.Student;

//
public class ServerCommunicationController {

	private Socket aSocket;
	private ServerSocket serverSocket;

	public ServerCommunicationController(int portNumber) {
		try {
			serverSocket = new ServerSocket(portNumber);
			//aSocket = serverSocket.accept();
			Student theStudent = new Student("Sebastian",100,serverSocket.accept());
			System.out.println("Connection accepted by server.");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void communicateWithClient() {
		try {
			while(true) {
				Student theStudent = new Student("Jerry Smith", 100);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ServerCommunicationController server = new ServerCommunicationController(8099);
		System.out.println("Server is now running.");
		server.communicateWithClient();
	}

}
