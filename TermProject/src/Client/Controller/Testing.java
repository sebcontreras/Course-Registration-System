package Client.Controller;

import Client.View.*;
import Server.Controller.ServerCommunicationController;
//to test with creating student from testing
import Client.Model.*;

public class Testing {

	public static void main(String[] args) {
		MainWindow main = new MainWindow();
		MyCourseWindow myCourse = new MyCourseWindow();
		SearchWindow search = new SearchWindow();
		
		FrameManager frameM = new FrameManager(main, myCourse, search);
		ServerCommunicationController servCom = new ServerCommunicationController(8099);
		CommunicationController comM = new CommunicationController("localhost", 8099);
		//GUIController controller = new GUIController(frameM, comM);
		
		//testing creating student directly from testing and passing it to GUI
		Student student = new Student("Sebastian", 1010101);
		GUIController controller = new GUIController(frameM, comM, student);
		controller.displayMainMenu();
	}

}
