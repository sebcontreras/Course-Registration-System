package Client.Controller;

//
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Client.View.FrameManager;


/**
 * This class controls the GUI. Its main job is establishing the action listeners for each window.
 * 
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class GUIController {
	
	/**
	 * The manager which is in charge of managing the frames in the application 
	 */
	private FrameManager frameManager;
	
	/**
	 * The communication controller which connects with the server
	 */
	private CommunicationController comController;
	
	/**
	 * The name of the student who is logged in
	 */
	private String name;
	
	/**
	 * The ID of the student who is logged in
	 */
	private String id;

	/**
	 * Constructor which assigns the communication controller from the parameter, and 
	 * creates the frame manager object.
	 * 
	 * @param comController the controller which connects with the server
	 */
	public GUIController(CommunicationController comController) {

		frameManager = new FrameManager();
		this.comController = comController;
	}
	
	/**
	 * Method which calls the frame manager to start and adds listeners to the login.
	 */
	public void start() {
		frameManager.start();
		frameManager.addListenersToLogin(new loginLoginListener());
	}
	
	/**
	 * Method which makes the program quit.
	 */
	public class quitListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	/**
	 * Assigns the name and ID by calling helper functions. Once the name and ID are received it closes the login window,
	 * and displays the main menu.
	 */
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

	/**
	 * Calls the frame manager to display the main menu.
	 */
	public void displayMainMenu() {
		frameManager.mainSetStudentInfo(name, id);
		frameManager.displayMainWindow();
		frameManager.addListenersToMainMenu(new mainSearchCoursesListener(), new mainMyCoursesListener(), new quitListener());
	}

	/**
	 * Listener for "Search Courses" button in main.
	 */
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

	/**
	 * Listener for "My Courses" button in main.
	 */
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

	/**
	 * Listener for "Add Course" button in My Course window
	 */
	public class myCourseAddCourseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String [] course = frameManager.getCourseFromMyCourse(); 
			frameManager.sendMessagetoMyCourseWindow(comController.communicate("3 "+course[0]+" "+course[1]+" "+id+" "+"\0"));
			frameManager.setAllStudentCourses(comController.communicate("6 "+id+" \0"));
			
			
			 
		}
	}

	/**
	 * Listener for "Drop Course" button in My Course window
	 */
	public class myCourseDropCourseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String [] course = frameManager.getCourseToRemoveFromMyCourse();
			frameManager.sendMessagetoMyCourseWindow(comController.communicate("4 "+course[0]+" "+course[1]+" "+id+" "+"\0"));
			frameManager.setAllStudentCourses(comController.communicate("6 "+id+" \0"));
		}
	}

	/**
	 * Listener for "Return to Main Menu" button in My Course window
	 */
	public class myCourseReturnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frameManager.closeMyCourseWindow();
			frameManager.displayMainWindow();
		}
	}

	/**
	 * Listener for "Search" button in search window
	 */
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

	/**
	 * Listener for "View All" button in search window
	 */
	public class searchViewAllListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frameManager.setAllCourses(comController.communicate("2 \0"));

		}
	}

	/**
	 * Listener for "Return to Main Menu" button in search window
	 */
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
