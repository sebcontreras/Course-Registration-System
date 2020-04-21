package Server.Model;

import java.util.ArrayList;

import Server.Model.Registration;
/**
 * The purpose of this class is to represent the Student/user of the application and to carry their information
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class Student {
	/*
	 * the name of the student
	 */
	private String studentName;
	/*
	 * the id of the student
	 */
	private int studentId;
	/*
	 * the list of all the courses the student is registered for
	 */
	private ArrayList<Registration> studentRegList;
	/*
	 * the list of all courses offered
	 */
	private CourseCatalogue courseList;

	/**
	 * constructs an instance of Student and initiates the course catalogue and registration list
	 */
	public Student () {
		setCourseList(new CourseCatalogue());
		studentRegList = new ArrayList<Registration>();
	}
	/**
	 * constructs an instance of Student and initiates the students name, id and registration list
	 * @param studentName, the name of the student
	 * @param studentId, the id of the student
	 */
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}
	/**
	 * this method checks if the course given exists 
	 * @param courseOff, the course offering that is being searched
	 * @return true if the course is found, false otherwise
	 */
	public boolean checkCourse(CourseOffering courseOff) {
		for (Registration r: studentRegList) {
			if(courseOff.equals(r.getTheOffering())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * this method is the getter for the student name
	 * @return the students name
	 */
	
	public String getStudentName() {
		return studentName;
	}
	/**
	 * this method is the setter for the student name
	 * @param studentName, the student name
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * this method is the fetter for the student id
	 * @return the student id
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * this method is the setter for the student id
	 * @param studentId, the student id
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	/**
	 * this method gets how many courses the student is registered for
	 * @return the number of courses the student is registered for
	 */
	public int getNumberOfRegistrations() {
		return studentRegList.size();
	}
	/**
	 * this method converts the student object to a String and returns that
	 */
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student Id: " + getStudentId() + "\n\n";
		return st;
	}
	/**
	 * this method registers the student for a class by checking for limitations 
	 * and then adding the registration to the list of registrations
	 * @param registration, the registration trying to be added to the list
	 */
	public void addRegistration(Registration registration) {
		
		if(studentRegList.size() > 5||checkCourseReg(registration)) {
			return;
		}
		else {
			studentRegList.add(registration);
		}
	}
	/**
	 * this method checks if the student was registered to the course successfully
	 * @param reg, the registration being attempted
	 * @return true if the registration worked, otherwise false
	 */
	public boolean checkCourseReg(Registration reg) {
		
		if(studentRegList.size() <= 0)
			return false;
		
		for(Registration regList : studentRegList) {
			
			String t1 = reg.getTheOffering().getTheCourse().getCourseName();
			String t2 = regList.getTheOffering().getTheCourse().getCourseName();
			int n1 = reg.getTheOffering().getTheCourse().getCourseNum();
			int n2 = regList.getTheOffering().getTheCourse().getCourseNum();
			
			if(t1.equals(t2) && n1 == n2) {
				return true;
			}
		}
		return false;
	}
	/**
	 * this method attempts to removes the given course from the registration list of the student
	 * @param course, the course the student is trying to leave
	 * @return true if removal was successful, otherwise false
	 */
	public boolean removeCourse(Course course) {
		
		for(Registration i : studentRegList) {
			
			if(i.getTheOffering().getTheCourse().equals(course)) {
				studentRegList.remove(i);
				return true;
			}
		}
		return false;
	}
	/**
	 * this method converts the registration list to a String
	 * @return the String that is the registration list
	 */
	public String regListToString() {
		
		String str = "";
		for(Registration reg : studentRegList) {
			str += reg;
		}
		return str;
	}
	/**
	 * this method is the getter for the course catalogue
	 * @return the course catalogue
	 */
	public CourseCatalogue getCourseList() {
		return courseList;
	}
	/**
	 * this method is the setter for the course catalogue
	 * @param courseList, the course catalogue
	 */
	public void setCourseList(CourseCatalogue courseList) {
		this.courseList = courseList;
	}


}

