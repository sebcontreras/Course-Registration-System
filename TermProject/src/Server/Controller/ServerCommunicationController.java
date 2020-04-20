package Server.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

import Client.Controller.GUIController;
import Client.View.FrameManager;
import Client.View.LoginWindow;
import Client.View.MainWindow;
import Client.View.MyCourseWindow;
import Client.View.SearchWindow;
import Server.Model.Course;
import Server.Model.CourseCatalogue;
import Server.Model.CourseOffering;
import Server.Model.Registration;
import Server.Model.Student;

//
public class ServerCommunicationController implements Runnable{

	private ServerSocket serverSocket;
	private Socket socket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private DBController database;
	private ExecutorService pool;
	
	public ServerCommunicationController(Socket socket) {
		try {
			this.socket=socket;
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream(), true);
		}catch (IOException e) {
			e.printStackTrace();
		} 
		database = new DBController();
	}
	
	@Override
	public void run() {
		communicate();
	}
	
	public void communicate() {
		try {
			
			while(true) {
				String read= "";
				
				while (true) {
					read = socketIn.readLine();
					if (read.contains("\0")) {
						read = read.replace("\0", "");
						break;
					}
					
				}
				String []input = read.split(" ");
				decision(input);
				

			} 
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socketIn.close();
				socketOut.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void decision(String []input) {
		int choice = Integer.parseInt(input[0]);
		switch(choice) {
			case 1:
				searchForCourse(input[1], input[2]);
				break;
			case 2:
				viewAllCourses();
				break;
			case 3: 
				addCourseToStudent(input[1], input[2], input[3]);
				break;
			case 4:
				removeCourseFromStudent(input[1], input[2], input[3]);
				break;
			case 6:
				viewStudentCourse(input[1]);
				break;
			case 7:
				checkStudentInDatabase(input[1], input[2]);
		}
	}
	
	private void checkStudentInDatabase(String studentName, String studentID) {
		if(!database.findStudent(Integer.parseInt(studentID))) {
			database.addStudentToList(studentName, Integer.parseInt(studentID));
		}
		socketOut.println("Login successful.\0");
	}

	public void viewAllCourses() {
		socketOut.println(database.getAllCourses()+"\0");
	}
	
	public void viewStudentCourse(String ID) {
		if (database.checkStudentCourseCount(Integer.parseInt(ID))==0) {
			socketOut.println("You are not yet registered for any courses. \0");
		}
		else {
			socketOut.println(database.viewStudentCourses(Integer.parseInt(ID))+"\0");
		}
	
	}

	public void removeCourseFromStudent(String courseName, String courseNum, String ID) {
		Course course = database.searchCat(courseName, Integer.parseInt(courseNum));
		if (course==null) {
			socketOut.println("Course does not exist\0");
			return;
		}
		else {
			String response = database.removeStudentFromCourse(course, Integer.parseInt(ID));
			socketOut.println(response+"\0");
		}
	}

	public void addCourseToStudent(String courseName, String courseNum, String ID) {
		Course course = database.searchCat(courseName, Integer.parseInt(courseNum));
		
		if (course==null) {
			socketOut.println("Course does not exist\0");
			return;
		}
		else {
			String response = database.addStudentToCourse(course, Integer.parseInt(ID));
				socketOut.println(response+"\0");
		}
	}

	public void searchForCourse(String courseName, String courseNum){
		String output = database.searchForCourse(courseName, Integer.parseInt(courseNum));
		if (output==null) {
			socketOut.println("Sorry, course not found"+"\0");
		} else {
			socketOut.println(output+"\0");
		}
	}





}
