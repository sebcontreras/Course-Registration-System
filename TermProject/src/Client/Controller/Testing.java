package Client.Controller;

import Client.View.*;

public class Testing {

	public static void main(String[] args) {
		MainWindow main = new MainWindow();
		MyCourseWindow myCourse = new MyCourseWindow();
		SearchWindow search = new SearchWindow();
		
		FrameManager frameM = new FrameManager(main, myCourse, search);
		CommunicationController comM = new CommunicationController();
		GUIController controller = new GUIController(frameM, comM);
		controller.displayMainMenu();
	}

}
