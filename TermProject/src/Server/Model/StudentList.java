package Server.Model;

import java.util.ArrayList;
/**
 * The purpose of this class is to contain the list of users/students within an ArrayList of Student objects
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class StudentList {
	/*
	 * the list of students that are in the database
	 */
	private ArrayList<Student> studentList;
	/*
	 * constructs an instance of StudentList which calls a function to fill the object with information
	 */
	public StudentList() {
		loadFromDataBase();
	}
	/**
	 * this method loads all the students within the database into the studentList
	 */
	private void loadFromDataBase() {
		DBManager db = new DBManager();
		setStudentList(db.readStudentFromDataBase());
		
	}
	/**
	 * this method adds new students/users to the studentList
	 * @param name, the name of the student being added to the list
	 * @param id, the id of the student being added to the list
	 */
	public void addStudent(String name, int id) {
		studentList.add(new Student(name, id));
	}
	/**
	 * this method is the setter for the studentList
	 * @param studentList, the studentList
	 */
	public void setStudentList(ArrayList <Student> studentList) {
		this.studentList = studentList;
	}
	/**
	 * this method searches for a student within the list given the students id, 
	 * @param id, the id of the student being searched for
	 * @return the Student object hat was searched for or null
	 */
	public Student findStudent(int id) {
		
		for(Student x : studentList) {
			if(x.getStudentId() == id) {
				return x;
			}
		}
		return null;
	}
	/**
	 * this method registers the user into their desired course given the course offering of the course
	 * @param id, the id of the user that is trying to register in the class
	 * @param co, the course offering of the course interested in
	 * @return confirmation of the registration or limitation messages, all in String form
	 */
	public String registerStudent(int id, CourseOffering co) {
		
		Student tar = findStudent(id);
		
		if(tar != null) {

			if(tar.checkCourse(co)) {
				return "Sorry! You are already registered in this course.";
			}else if(tar.getNumberOfRegistrations()>5) {
				return "Sorry! You are only allowed to register for a maximum of 6 courses.";
			}else {
				Registration r = new Registration();
				r.completeRegistration(tar, co);
				return "Registration successful!";
			}

			
		}
		return null;
	}
	/**
	 * this method removes students from the given course
	 * @param id, the id of the student who is being removed from the class
	 * @param course, the course that the student is leaving
	 * @return either true if the student was removed successfully or false
	 */
	public boolean removeCourseReg(int id, Course course) {
		
		Student target = findStudent(id);
		
		if(target != null) {
			if(target.removeCourse(course)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * this method gets all the courses the student/user is registered for 
	 * and returns them in String form
	 * @param id, the id of the student who's courses are searched
	 * @return the String of all the courses the user is registered for
	 */
	public String viewStudentCourse(int id) {
		
		Student target = findStudent(id);
		String str = "";
		if(target != null) {
			str += target.regListToString();
		}
		
		return str;
	}
	

}
