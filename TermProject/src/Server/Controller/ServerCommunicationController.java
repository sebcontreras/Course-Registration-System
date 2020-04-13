package Server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Server.Model.CourseCatalogue;
import Server.Model.Student;

//
public class ServerCommunicationController {

	private Socket aSocket;
	private ServerSocket serverSocket;
	private CourseCatalogue theCourseList;

	public ServerCommunicationController(int portNumber) {
		try {
			serverSocket = new ServerSocket(portNumber);
			aSocket = serverSocket.accept();
			//Student theStudent = new Student("Sebastian",100,serverSocket.accept());
			System.out.println("Connection accepted by server.");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void communicateWithClient() {
		while(true) {
			
			switch(choice) {
			case 1:
				searchForCourse();
				break;
			case 2: 
				addCourseToStudent();
				break;
			case 3:
				removeCourseFromStudent();
				break;
			case 4:
				viewCourseCatalogue();
				break;
			case 5:
				viewStudentCourse();
				break;
			case 6:
				listStudents();
				break;
			/*case 7:
				System.out.println("\nGood Bye!");
				System.exit(1);
			default:
				System.out.println("\nInvalid selection Please try again!");
				break;*/
			}
		}
	}
	
	public static void main(String[] args) {
		ServerCommunicationController server = new ServerCommunicationController(8099);
		System.out.println("Server is now running.");
		server.communicateWithClient();
	}

}
