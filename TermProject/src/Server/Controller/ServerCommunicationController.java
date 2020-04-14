package Server.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Server.Model.Course;
import Server.Model.CourseCatalogue;
import Server.Model.CourseOffering;
import Server.Model.Student;

//
public class ServerCommunicationController {

	private Socket pipe;
	private ServerSocket serverSocket;

	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	
	private Student theStudent;

	public ServerCommunicationController(int portNumber) {
		try {
			theStudent = null;
	
			serverSocket = new ServerSocket(portNumber);
			
			pipe = serverSocket.accept();
			objectInputStream = new ObjectInputStream(pipe.getInputStream());
			objectOutputStream = new ObjectOutputStream(pipe.getOutputStream());
			
			objectInputStream.close();
			objectOutputStream.close();
		}catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private void communicateWithClient() {
		int choice = 0;
		theStudent = new Student();
		try {
			objectOutputStream.writeObject(theStudent);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while(true) {
			try {
			Student temp = (Student)objectInputStream.readObject();
			choice = temp.getChoice();
			switch(choice) {
			case 1:
				searchForCourse();
				objectOutputStream.writeObject(theStudent);
				break;
			case 2: 
				addCourseToStudent();
				objectOutputStream.writeObject(theStudent);
				break;
			case 3:
				removeCourseFromStudent();
				objectOutputStream.writeObject(theStudent);
				break;
			case 4:
				viewStudentCourse();
				objectOutputStream.writeObject(theStudent);
				break;
			}
			}catch(IOException | ClassNotFoundException e) {
				e.getStackTrace();
			}
			try {
				objectInputStream.close();
				objectOutputStream.close();
			}catch(IOException e) {
				e.getStackTrace();
			}
		}
	}

	public String viewStudentCourse() {
		int id = inputStudentId();
		System.out.println(studentList.viewStudentCourse(id));
	}

	public String removeCourseFromStudent() {
		
		String name = inputCourseName();
		int num = inputCourseNum();
		Course course = catalogue.searchCat(name, num);
		if(course != null) {
			int id = inputStudentId();
			studentList.removeCourseReg(id, course);
		}
	}

	public String addCourseToStudent() {
		
		String name = inputCourseName();
		int num = inputCourseNum();
		Course course = catalogue.searchCat(name, num);
		if(course != null) {
			int sec = inputSecNum();
			CourseOffering cOff = course.getCourseOfferingAt(sec - 1);
			
			if(cOff != null) {
				int id = inputStudentId();
				studentList.registerStudent(id, cOff);
			}
		}
	}

	public String searchForCourse(){
		System.out.println(theCourseList.searchCat(inputCourseName(), inputCourseNum()));
	}

	public static void main(String[] args) {
		ServerCommunicationController server = new ServerCommunicationController(8099);
		System.out.println("Server is now running.");
		server.communicateWithClient();
	}

}
