package Client.Model;

import java.util.ArrayList;

import Client.Model.Course;


public class CourseCatalogue {

private ArrayList <Course> courseList;
	
	public CourseCatalogue () {}
	
	
	public ArrayList <Course> getCourseList() {
		return courseList;
	}


	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
	@Override
	public String toString () {
		String st = "All courses in the catalogue: \n";
		for (Course c : courseList) {
			st += c;  //This line invokes the toString() method of Course
			st += "\n";
		}
		return st;
	}
}
