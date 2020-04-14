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

import Client.Model.Course;
import Client.Model.Student;
import Client.View.FrameManager;

public class GUIController {
	private FrameManager frameManager;
	private CommunicationController comController;
	private Student student;
	private Socket socket;
	private BufferedReader socketIn;
	private PrintWriter socketOut;
//	private ObjectInputStream objectStreamIn;
//	private ObjectOutputStream objectStreamOut;

	public GUIController(FrameManager frameManager, CommunicationController comController, Socket socket) {
		this.socket = socket;
		try {
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.frameManager = frameManager;
		this.comController = comController;
	}

	public GUIController(FrameManager frameManager, CommunicationController comController, Student student) {
		this.frameManager = frameManager;
		this.comController = comController;
		this.student = student;
	}


	public void displayMainMenu() {
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
			String[] course = frameManager.getCourseFromSearch();
			String courseName = course[0];
			int courseNum = Integer.parseInt(course[1]);
			Course toSearch = checkCourse(courseName, courseNum);
			if (toSearch!=null) {
				frameManager.sendMessagetoSearchWindow(
						"Course Found! Course Information is as follows:\n" + course[0] + " " + course[1] + "\n");
				// *needs to print out an object*
			} else {
				frameManager.sendMessagetoSearchWindow("Sorry, course not found!");
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
	
	public Course checkCourse(String courseName, int courseID) {
		socketOut.println("1");
		socketOut.flush();
		socketOut.println(courseName+" "+courseID);
		socketOut.flush();
		String result="";
		try {
			result = socketIn.readLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
		if(result.contains("NOT FOUND")) {
			return null;
		}
		else {
			Course toReturn = null;
			try {
				toReturn= (Course)objectStreamIn.readObject();
			}catch(ClassNotFoundException | IOException e) {
				frameManager.sendMessagetoSearchWindow("Error. Please Try Again! (ClassNotFoundException)");
			}
			return toReturn;
		}
		
	}
	

}
