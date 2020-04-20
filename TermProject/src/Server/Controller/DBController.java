package Server.Controller;

import Server.Model.*;


//
public class DBController {

	private StudentList studentList;
	private CourseCatalogue courseCatalogue;
	
	public DBController(){
		studentList = new StudentList();
		courseCatalogue = new CourseCatalogue();
		
	}
	
	public CourseCatalogue getCourseCatalogue() {
		return this.courseCatalogue;
	}
	
	public String getAllCourses() {
		return courseCatalogue.toString();
	}
	public synchronized String searchForCourse(String courseName, int courseNum) {
		Course course = courseCatalogue.searchCat(courseName, courseNum);
		if (course!=null) {
			return course.toString();
		}
		return null;
	}

	public synchronized String addStudentToCourse(Course course, int ID) {
		for (int i=0; i<course.getOfferingList().size(); i++) {
			if (!course.getCourseOfferingAt(i).isFull()) {
				return studentList.registerStudent(ID, course.getCourseOfferingAt(i));
			}
		}
		return "Registration unsuccessful.";
	}

	public synchronized String removeStudentFromCourse(Course course, int ID) {
		if (studentList.removeCourseReg(ID, course)) {
			return "Removal successful.";
		}
		return "Removal unsuccessful.";
	}

	public synchronized String viewStudentCourses(int ID) {
		return studentList.viewStudentCourse(ID);
	}

	public synchronized Course searchCat(String courseName, int courseNum) {
		return courseCatalogue.searchCat(courseName, courseNum);
	}
	
	public synchronized boolean findStudent(int id) {
		Student student = studentList.findStudent(id);
		if (student==null) {
			return false;
		}else {
			return true;
		}
	}

	public synchronized void addStudentToList(String studentName, int id) {
		studentList.addStudent(studentName, id);
	}

	public synchronized int checkStudentCourseCount(int id) {
		Student student = studentList.findStudent(id);
		return student.getNumberOfRegistrations();
	}
}
