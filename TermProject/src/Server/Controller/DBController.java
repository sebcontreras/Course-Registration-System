package Server.Controller;

import Server.Model.*;

/**
 * The purpose of this class is to contain the information extracted from the database and distribute it accordingly
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class DBController {
	/*
	 * the list of students known to the system acquired through the database
	 */
	private StudentList studentList;
	/*
	 * the list of all courses available to the user acquired through the database
	 */
	private CourseCatalogue courseCatalogue;
	/*
	 * constructs a default DBController by calling the default constructors for the StudentList and CourseCatalogue
	 */
	public DBController(){
		studentList = new StudentList();
		courseCatalogue = new CourseCatalogue();
		
	}
	/**
	 * this function is the getter function for the course catalogue
	 * @return the CourseCatalogue object of this class
	 */
	public CourseCatalogue getCourseCatalogue() {
		return this.courseCatalogue;
	}
	/**
	 * this method accesses all the courses in the course catalogue and returns them in a String format
	 * @return all the courses in the CoureCatalogue object, converted to Strings
	 */
	public String getAllCourses() {
		return courseCatalogue.toString();
	}
	/**
	 * this method searches for the course specified by the parameters in the course catalogue
	 * @param courseName, the name of the course being searched for
	 * @param courseNum, the number of the course being searched for
	 * @return the course that was searched for in String form, or null if the course wasn't found
	 */
	public synchronized String searchForCourse(String courseName, int courseNum) {
		Course course = courseCatalogue.searchCat(courseName, courseNum);
		if (course!=null) {
			return course.toString();
		}
		return null;
	}
	/**
	 * this method registers the user into the specified course if there is room in the course
	 * @param course, the course that the user would like to be registered into
	 * @param ID, the id of the user
	 * @return confirmation that the user was successfully registered into the given course
	 */
	public synchronized String addStudentToCourse(Course course, int ID) {
		for (int i=0; i<course.getOfferingList().size(); i++) {
			if (!course.getCourseOfferingAt(i).isFull()) {
				return studentList.registerStudent(ID, course.getCourseOfferingAt(i));
			}
		}
		return "Registration unsuccessful.";
	}
	/**
	 * this method removes the user from the specified course using the users id
	 * @param course, the course that the user would like to get removed from
	 * @param ID, the id of the user
	 * @return confirmation that the removal was successful, or not
	 */
	public synchronized String removeStudentFromCourse(Course course, int ID) {
		if (studentList.removeCourseReg(ID, course)) {
			return "Removal successful.";
		}
		return "Removal unsuccessful.";
	}
	/**
	 * this method access the courses that the user is registered into and returns them in String format
	 * @param ID, the id of the user
	 * @return the String containing all the courses that the user is registered for
	 */
	public synchronized String viewStudentCourses(int ID) {
		return studentList.viewStudentCourse(ID);
	}
	/**
	 * this method searches through the course catalogue for the given course name and number
	 * @param courseName, the name of the course being searched
	 * @param courseNum, the number of the course being searched
	 * @return either the course that was searched for or null, signifying the course was not found
	 */
	public synchronized Course searchCat(String courseName, int courseNum) {
		return courseCatalogue.searchCat(courseName, courseNum);
	}
	/**
	 * this method searches through previous users trying to find the student using the given id
	 * @param id, the id of the student/user being searched for
	 * @return either true if the student is found or false if not
	 */
	public synchronized boolean findStudent(int id) {
		Student student = studentList.findStudent(id);
		if (student==null) {
			return false;
		}else {
			return true;
		}
	}
	/**
	 * this method adds the current user to the list of previous users
	 * @param studentName, the name of the student being added to the student list
	 * @param id, the id of the student being added to the student list
	 */
	public synchronized void addStudentToList(String studentName, int id) {
		studentList.addStudent(studentName, id);
	}
	/**
	 * this method finds out how many courses the current user is registered for 
	 * @param id, the id of the user who's information is being searched for
	 * @return the number of courses the current user is signed up for
	 */
	public synchronized int checkStudentCourseCount(int id) {
		Student student = studentList.findStudent(id);
		return student.getNumberOfRegistrations();
	}
}
