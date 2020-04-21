package Server.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


import Server.Model.Course;

/**
 * The purpose of this class is to handle the input from the client side and return the appropriate response
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class ServerCommunicationController implements Runnable{
	/*
	 * the output sender from the server side that sends Strings back to the client to be displayed
	 */
	private PrintWriter socketOut;
	/*
	 * the input reader from the client that decides what functionalities the server should perform
	 */
	private BufferedReader socketIn;
	/*
	 * the information from the database that is used throughout this class to perform functions
	 */
	private DBController database;
	/**
	 * constructs an instance of ServerCommunicationController which initializes the input and output readers of the server side
	 * @param socket
	 * @param database
	 */
	public ServerCommunicationController(Socket socket, DBController database) {
		try {
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream(), true);
		}catch (IOException e) {
			e.printStackTrace();
		} 
		this.database =database;
	}
	/*
	 * this method comes with the Runnable interface and calls the function that will communicate with the client
	 */
	@Override
	public void run() {
		communicate();
	}
	/**
	 * this method continuously communicates with the client and processes the information sent from there
	 */
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
	/**
	 * this method decides what functionalities to perform with the processed input from the client
	 * @param input, the input from the client
	 */
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
	/**
	 * this method searches for the user's id and name in the database for the login feature, and sends confirmation 
	 * to the client side in String form
	 * @param studentName, the name of the user
	 * @param studentID, the id of the user
	 */
	private void checkStudentInDatabase(String studentName, String studentID) {
		if(!database.findStudent(Integer.parseInt(studentID))) {
			database.addStudentToList(studentName, Integer.parseInt(studentID));
		}
		socketOut.println("Login successful.\0");
	}
	/**
	 * this method gets all the courses in the database and sends them to the client side in String form
	 */
	public void viewAllCourses() {
		socketOut.println(database.getAllCourses()+"\0");
	}
	/**
	 * this method gets all the courses the current user is registered for and sends them to the client
	 * side in String form
	 * @param ID, the id of the user, who's information is being searched 
	 */
	public void viewStudentCourse(String ID) {
		if (database.checkStudentCourseCount(Integer.parseInt(ID))==0) {
			socketOut.println("You are not yet registered for any courses. \0");
		}
		else {
			socketOut.println(database.viewStudentCourses(Integer.parseInt(ID))+"\0");
		}
	
	}
	/**
	 * this method removes a student from the given course by first checking if the course is
	 * available, after which the student is removed from the course and the confirmation 
	 * is sent back to the client in String form
	 * @param courseName, the name of the course the user is getting out of 
	 * @param courseNum, the number of the course the user is getting out of 
	 * @param ID, the id of the user
	 */
	public void removeCourseFromStudent(String courseName, String courseNum, String ID) {
		courseName = courseName.toUpperCase();
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
	/**
	 * this method registers the user into a given course by first checking if the given course
	 * is in the database, after which the student is registered and the confirmation is 
	 * sent to the client side in String form
	 * @param courseName, the name of the course the student is trying to register for
	 * @param courseNum, the number of the course the student is trying to register for
	 * @param ID, the id of the student/user
	 */
	public void addCourseToStudent(String courseName, String courseNum, String ID) {
		courseName = courseName.toUpperCase();
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
	/**
	 * this method searches for the given course in the course catalogue, sending the confirmation 
	 * back to the client in String form
	 * @param courseName, the name of the course being searched for
	 * @param courseNum, the number of the course being searched for
	 */
	public void searchForCourse(String courseName, String courseNum){
		courseName = courseName.toUpperCase();
		String output = database.searchForCourse(courseName, Integer.parseInt(courseNum));
		if (output==null) {
			socketOut.println("Sorry, course not found"+"\0");
		} else {
			socketOut.println(output+"\0");
		}
	}

}
