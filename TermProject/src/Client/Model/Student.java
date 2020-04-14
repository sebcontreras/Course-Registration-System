package Client.Model;

import java.io.Serializable;
import java.util.ArrayList;

import Server.Model.Registration;

public class Student implements Serializable{

	private String studentName;
	private int studentId;
	private ArrayList<Registration> studentRegList;
	private CourseCatalogue courseList;
	private int choice;
	
	public Student () {}

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
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student Id: " + getStudentId() + "\n\n";
		return st;
	}

	public ArrayList<Registration> getStudentRegList() {
		return studentRegList;
	}

	public void setStudentRegList(ArrayList<Registration> studentRegList) {
		this.studentRegList = studentRegList;
	}

	public CourseCatalogue getCourseList() {
		return courseList;
	}

	public void setCourseList(CourseCatalogue courseList) {
		this.courseList = courseList;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}
}


