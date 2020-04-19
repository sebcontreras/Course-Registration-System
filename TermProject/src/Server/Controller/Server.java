package Server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Client.Controller.GUIController;
import Client.View.FrameManager;
import Client.View.LoginWindow;
import Client.View.MainWindow;
import Client.View.MyCourseWindow;
import Client.View.SearchWindow;

public class Server {
	
	private ServerSocket serverSocket;
	
	private ExecutorService pool;
	
	public Server (int portNumber) {
		try {
			serverSocket = new ServerSocket(portNumber);
			pool = Executors.newCachedThreadPool();
			System.out.println("Server running...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		try {
			ServerCommunicationController serverCom= new ServerCommunicationController(serverSocket.accept());
			System.out.println("New client connected...");
			pool.execute(serverCom);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		Server server = new Server(8099);
		server.start();
	}

}
