package Server.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Server.Model.CourseCatalogue;
import Server.Model.Student;

//
public class ServerCommunicationController {

	private Socket pipe;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private ServerSocket serverSocket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	
	private CourseCatalogue theCourseList;
	private Student theStudent;

	public ServerCommunicationController(int portNumber) {
		try {
			theStudent = null;
			theCourseList = new CourseCatalogue();
			serverSocket = new ServerSocket(portNumber);
			
			pipe = serverSocket.accept();
			objectInputStream = new ObjectInputStream(pipe.getInputStream());
			objectOutputStream = new ObjectOutputStream(pipe.getOutputStream());
			
			theStudent = (Student)objectInputStream.readObject();
			//here changes can be made to theStudent
			theStudent.setStudentName("ServerChange");
			objectOutputStream.writeObject(theStudent);
			
			//I think this should close at a later point?
			objectInputStream.close();
			objectOutputStream.close();

			System.out.println("Connection accepted by server.");
			
			//socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			//socketOut = new PrintWriter((aSocket.getOutputStream()), true);
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void communicateWithClient() {
		int choice = 0;
		while(true) {
			Student theStudent = new Student("Sevickey", 1234, serverSocket.accept());
			String result = "";
			choice =  Integer.parseInt(socketIn.readLine());
			switch(choice) {
			case 1:
				result = searchForCourse();
				socketOut.println(result);
				break;
			case 2: 
				result = addCourseToStudent();
				socketOut.println(result);
				break;
			case 3:
				result = removeCourseFromStudent();
				socketOut.println(result);
				break;
			case 4:
				result = viewCourseCatalogue();
				socketOut.println(result);
				break;
			case 5:
				result = viewStudentCourse();
				socketOut.println(result);
				break;
			case 6:
				result = listStudents();
				socketOut.println(result);
				break;
			}
		}
	}
	
	public void listStudents() {
		studentList.listStudents();
	}

	public void viewStudentCourse() {
		int id = inputStudentId();
		System.out.println(studentList.viewStudentCourse(id));
	}

	public String viewCourseCatalogue() {
		return theCourseList.toString();
	}

	public void removeCourseFromStudent() {
		
		String name = inputCourseName();
		int num = inputCourseNum();
		Course course = catalogue.searchCat(name, num);
		if(course != null) {
			int id = inputStudentId();
			studentList.removeCourseReg(id, course);
		}
	}

	public void addCourseToStudent() {
		
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

	public void searchForCourse(){
		System.out.println(theCourseList.searchCat(inputCourseName(), inputCourseNum()));
	}

	public static void main(String[] args) {
		ServerCommunicationController server = new ServerCommunicationController(8099);
		System.out.println("Server is now running.");
		server.communicateWithClient();
	}

}
