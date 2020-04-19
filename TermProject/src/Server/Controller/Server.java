package Server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Client.Controller.GUIController;
import Client.View.FrameManager;
import Client.View.LoginWindow;
import Client.View.MainWindow;
import Client.View.MyCourseWindow;
import Client.View.SearchWindow;

public class Server {
	
	private ServerSocket serverSocket;
	
	public Server (int portNumber) {
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		MainWindow main = new MainWindow();
		MyCourseWindow myCourse = new MyCourseWindow();
		SearchWindow search = new SearchWindow();
		LoginWindow login = new LoginWindow();
		DBController database = new DBController();
		try {
			ServerCommunicationController serverCom= new ServerCommunicationController(serverSocket.accept(), database);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FrameManager frameM = new FrameManager(main, myCourse, search, login);
		
		
		GUIController controller;
		try {
			controller = new GUIController(serverSocket.accept(),frameM);
			controller.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Server server = new Server(8099);
		System.out.println("Server now running...");
		server.start();
	}

}
