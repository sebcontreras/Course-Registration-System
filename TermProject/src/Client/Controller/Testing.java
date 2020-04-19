package Client.Controller;

import Client.View.*;
import Server.Model.*;
import Server.Controller.*;

import java.io.IOException;

//to test with creating student from testing
import Client.Model.*;

public class Testing {

	public static void main(String[] args) {
		MainWindow main = new MainWindow();
		MyCourseWindow myCourse = new MyCourseWindow();
		SearchWindow search = new SearchWindow();
		LoginWindow login = new LoginWindow();
		
		FrameManager frameM = new FrameManager(main, myCourse, search, login);
		
		
		GUIController controller;
		try {
			controller = new GUIController(servCom.getServerSocket().accept(),frameM, comM);
			controller.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		ServerCommunicationController servCom = new ServerCommunicationController(8099);
//		CommunicationController comM = new CommunicationController("localhost", 8099);
		//GUIController controller = new GUIController(frameM, comM);
		
		//testing creating student directly from testing and passing it to GUI
//		Student student = new Student("Sebastian", 1010101);
//		GUIController controller = new GUIController(frameM, comM, student);
//		controller.displayMainMenu();
		
	
	}

}
