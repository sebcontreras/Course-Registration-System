package Client.Controller;

//
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Client.View.FrameManager;

public class GUIController {
	private FrameManager frameManager;
	private CommunicationController comController;
	private String name;
	private String id;

	public GUIController(CommunicationController comController) {

		frameManager = new FrameManager();
		this.comController = comController;
	}
	

	
	public void start() {
		frameManager.start();
		frameManager.addListenersToLogin(new loginLoginListener());
	}
	
	public class quitListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	public class loginLoginListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			name = frameManager.getName();
			id = frameManager.getID();
			frameManager.closeLoginWindow();
			frameManager.sendMessagetoMainWindow(comController.communicate("7 "+name+" "+id+" \0"));
			displayMainMenu();
			
		}
	}

	public void displayMainMenu() {
		frameManager.mainSetStudentInfo(name, id);
		frameManager.displayMainWindow();
		frameManager.addListenersToMainMenu(new mainSearchCoursesListener(), new mainMyCoursesListener(), new quitListener());
	}


	// listener for "Search Courses" button in main
	public class mainSearchCoursesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frameManager.searchSetStudentInfo(name, id);
			frameManager.closeMainWindow();
			frameManager.displaySearchWindow();
			frameManager.addListenersToSearchWindow(new searchSearchListener(), new searchViewAllListener(),
					new searchBackListener(), new quitListener());
		}

	}

	// listener for "My Courses" button in main
	public class mainMyCoursesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frameManager.myCoursesSetStudentInfo(name,id);
			frameManager.closeMainWindow();
			frameManager.setAllStudentCourses(comController.communicate("6 "+id+" \0"));
			frameManager.displayMyCoursesWindow();
			frameManager.addListenersToMyCourseWindow(new myCourseAddCourseListener(), new myCourseDropCourseListener(),
					new myCourseReturnListener(), new quitListener());

		}
	}

	// listener for "Add Course" button in My Course window
	public class myCourseAddCourseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String [] course = frameManager.getCourseFromMyCourse(); 
			frameManager.sendMessagetoMyCourseWindow(comController.communicate("3 "+course[0]+" "+course[1]+" "+id+" "+"\0"));
			frameManager.setAllStudentCourses(comController.communicate("6 "+id+" \0"));
			
			
			 
		}
	}

	// listener for "Drop Course" button in My Course window
	public class myCourseDropCourseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String [] course = frameManager.getCourseToRemoveFromMyCourse();
			frameManager.sendMessagetoMyCourseWindow(comController.communicate("4 "+course[0]+" "+course[1]+" "+id+" "+"\0"));
			frameManager.setAllStudentCourses(comController.communicate("6 "+id+" \0"));
		}
	}

	// listener for "Return to Main Menu" button in My Course window
	public class myCourseReturnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frameManager.closeMyCourseWindow();
			frameManager.displayMainWindow();
		}
	}

	// listener for "Search" button in search window
	public class searchSearchListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] course = frameManager.getCourseFromSearch(); //gets user input for course to search
			String response = comController.communicate("1 "+course[0]+" "+course[1]+"\0");
			if (response.equals("Sorry, course not found")) {
				frameManager.sendMessagetoSearchWindow(response);
			}
			else {
				frameManager.sendMessagetoSearchWindow(response);
			}
			
		}
	}

	// listener for "View All" button in search window
	public class searchViewAllListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frameManager.setAllCourses(comController.communicate("2 \0"));

		}
	}

	// listener for "Return to Main Menu" button in search window
	public class searchBackListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frameManager.closeSearchWindow();
			frameManager.displayMainWindow();
		}

	}
	
	public static void main(String args[]) {
		GUIController controller = new GUIController(new CommunicationController("localhost", 8099));
		controller.start();
	}
	

}
