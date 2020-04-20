package Server.Controller;

import Server.Model.*;



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
	public String searchForCourse(String courseName, int courseNum) {
		Course course = courseCatalogue.searchCat(courseName, courseNum);
		if (course!=null) {
			return course.toString();
		}
		return null;
	}

	public String addStudentToCourse(Course course, int ID) {
		for (int i=0; i<course.getOfferingList().size(); i++) {
			if (!course.getCourseOfferingAt(i).isFull()) {
				studentList.registerStudent(ID, course.getCourseOfferingAt(i));
				return "Registration successful.";
			}
		}
		return "Registration unsuccessful.";
	}

	public String removeStudentFromCourse(Course course, int ID) {
		if (studentList.removeCourseReg(ID, course)) {
			return "Removal successful.";
		}
		return "Removal unsuccessful.";
	}

	public String viewStudentCourses(int ID) {
		return studentList.viewStudentCourse(ID);
	}
	

}
