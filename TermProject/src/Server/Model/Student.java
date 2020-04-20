package Server.Model;

import java.util.ArrayList;

import Server.Model.Registration;

public class Student {

	private String studentName;
	private int studentId;
	private ArrayList<Registration> studentRegList;
	private CourseCatalogue courseList;

	
	public Student () {
		setCourseList(new CourseCatalogue());
		studentRegList = new ArrayList<Registration>();
	}
	
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}
	
	public boolean checkCourse(CourseOffering courseOff) {
		for (Registration r: studentRegList) {
			if(courseOff.equals(r.getTheOffering())) {
				return true;
			}
		}
		return false;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public int getNumberOfRegistrations() {
		return studentRegList.size();
	}
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student Id: " + getStudentId() + "\n\n";
		return st;
	}

public void addRegistration(Registration registration) {
		
		if(studentRegList.size() > 5||checkCourseReg(registration)) {
			return;
		}
		else {
			studentRegList.add(registration);

		
	}
}
	
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
	
	public boolean removeCourse(Course course) {
		
		for(Registration i : studentRegList) {
			
			if(i.getTheOffering().getTheCourse().equals(course)) {
				studentRegList.remove(i);
				return true;
			}
		}
		return false;
	}

	public String regListToString() {
		
		String str = "";
		for(Registration reg : studentRegList) {
			str += reg;
		}
		return str;
	}

	public CourseCatalogue getCourseList() {
		return courseList;
	}

	public void setCourseList(CourseCatalogue courseList) {
		this.courseList = courseList;
	}


}

