package Server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * The purpose of this class is to represent the server side of this server/client application and run the server
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class Server {
	/*
	 * the connection to the client side
	 */
	private ServerSocket serverSocket;
	/*
	 * the pool of the threds that allows the application handle multiple clients at the same time
	 */
	private ExecutorService pool;
	/**
	 * constructs an instance of Server that initiates the ServerSocket and thread pool
	 * @param portNumber
	 */
	public Server (int portNumber) {
		try {
			serverSocket = new ServerSocket(portNumber);
			pool = Executors.newCachedThreadPool();
			System.out.println("Server running...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * this method starts the application on the server side by loading the information from the database to the program
	 * as well as waiting for the connection from the client side. Lastly, it continuously waits for input from the client side
	 */
	public void start() {
		try {
			DBController database = new DBController();
			while (true) {
				ServerCommunicationController serverCom= new ServerCommunicationController(serverSocket.accept(), database);
				System.out.println("New client connected...");
				pool.execute(serverCom);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		Server server = new Server(8099);
		server.start();
	}

}
