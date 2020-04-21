package Client.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * This class is responsible for establishing a connection from the client to the server.
 * 
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class CommunicationController {
	
	/**
	 * The socket through which the server/client communication is made
	 */
	private Socket socket;
	
	/**
	 * The PrintWriter for the out socket communication
	 */
	private PrintWriter socketOut;
	
	/**
	 * The BufferedReader for the in socket communication
	 */
	private BufferedReader socketIn;
	
	/**
	 * Constructor which assigns the socket and the reader and writer with the parameters passed in. 
	 * 
	 * @param serverName the name of the server
	 * @param portNumber the corresponding port number
	 */
	public CommunicationController(String serverName, int portNumber) {
		try {
			socket = new Socket(serverName, portNumber);
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream(), true);
		}catch(IOException  e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is responsible for communicating the client with the server.
	 * 
	 * @param line the message to be passed to the socketOut
	 * @return the response from the socketIn in the correct format
	 */
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
