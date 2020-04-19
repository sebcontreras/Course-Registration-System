package Client.Controller;

//
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JLabel;

import Client.Model.Course;
import Client.Model.Student;
import Client.View.FrameManager;

public class GUIController {
	private FrameManager frameManager;
	private CommunicationController comController;
	private Student student;
	private String name;
	private String id;
	private Socket socket;
	private BufferedReader socketIn;
	private PrintWriter socketOut;

//	public GUIController(FrameManager frameManager) {
//		student = new Student();
//		this.frameManager = frameManager;
////		this.comController = comController;
//	}
	
	public GUIController(CommunicationController comController, FrameManager frameManager) {
//		this.socket=socket;
//		try {
//			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			socketOut = new PrintWriter(socket.getOutputStream(), true);
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
		student = new Student();
		this.frameManager = frameManager;
		this.comController = comController;
	}
	
	public void setComController(CommunicationController comController) {
		this.comController=comController;
	}

	public GUIController(FrameManager frameManager, CommunicationController comController, Student student) {
		this.frameManager = frameManager;
		this.comController = comController;
		this.student = student;
	}
	
	public void start() {
		frameManager.start();
		frameManager.addListenersToLogin(new loginLoginListener());
	}
	
	public class loginLoginListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			name = frameManager.getName();
			student.setStudentName(name);
			id = frameManager.getID();
			student.setStudentId(Integer.parseInt(id));
			//send name and id over to server to create new student 
			frameManager.closeLoginWindow();
			displayMainMenu();
			
		}
	}

	public void displayMainMenu() {
		frameManager.mainSetStudentInfo(name, id);
		frameManager.displayMainWindow();
		frameManager.addListenersToMainMenu(new mainSearchCoursesListener(), new mainMyCoursesListener());
	}

	public Student getStudent() {
		return student;
	}

	// listener for "Search Courses" button in main
	public class mainSearchCoursesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frameManager.searchSetStudentInfo(name, id);
			frameManager.closeMainWindow();
			frameManager.displaySearchWindow();
			frameManager.addListenersToSearchWindow(new searchSearchListener(), new searchViewAllListener(),
					new searchBackListener());
		}

	}

	// listener for "My Courses" button in main
	public class mainMyCoursesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frameManager.myCoursesSetStudentInfo(name,id);
			frameManager.closeMainWindow();
			frameManager.displayMyCoursesWindow();
			frameManager.addListenersToMyCourseWindow(new myCourseAddCourseListener(), new myCourseDropCourseListener(),
					new myCourseReturnListener());

		}
	}

	// listener for "Add Course" button in My Course window
	public class myCourseAddCourseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String [] course = frameManager.getCourseFromMyCourse(); 
			String courseName = course[0]; 
			int courseNum = Integer.parseInt(course[1]);
			
			
			 
		}
	}

	// listener for "Drop Course" button in My Course window
	public class myCourseDropCourseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

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
//			String courseName = course[0];
//			int courseNum = Integer.parseInt(course[1]);
			System.out.println("Got here G");
			comController.communicateWithServer("1 "+course[0]+" "+course[1]+"\0");
			String response;
			response = comController.communicate();
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
			// display all courses in info text area

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
		CommunicationController comM = new CommunicationController ("localhost", 8099);
		comM.communicate();
	}
	

}
