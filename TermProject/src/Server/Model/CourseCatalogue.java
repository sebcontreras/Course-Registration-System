package Server.Model;

import java.util.ArrayList;
/**
 * The purpose of this class is to contain the list of courses within an ArrayList of Course objects
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class CourseCatalogue {
	/*
	 * the list of courses acquired from the database
	 */
	private ArrayList <Course> courseList;
	/**
	 * constructs an instance of CourseCatalogue which calls a function to fill the object with information
	 */
	public CourseCatalogue () {
		loadFromDataBase ();
	}
	/**
	 * this method loads all the courses within the database into the courseList
	 */
	private void loadFromDataBase() {
		DBManager db = new DBManager();
		setCourseList(db.readCourseFromDataBase());
		
	}
	/**
	 * this method creates a course offering for the given course
	 * @param c, the course 
	 * @param secNum, the section number of the new course offering
	 * @param secCap, the capacity of the new course offering
	 */
	public void createCourseOffering (Course c, int secNum, int secCap) {
		if (c!= null) {
			CourseOffering theOffering = new CourseOffering (secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	/**
	 * this method searches through the courseList looking for the given course
	 * @param courseName, the name of the course being searched for
	 * @param courseNum, the number of the course being searched for
	 * @return the course if fount, if not null
	 */
	public Course searchCat (String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) && courseNum == c.getCourseNum()) {
				return c;
			}	
		}
		return null;
	}
	/**
	 * this method is the getter for the course list
	 * @return the course list
	 */
	public ArrayList <Course> getCourseList() {
		return courseList;
	}

	/**
	 * this method is the setter for the course list
	 * @param courseList, the course list
	 */
	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
	/**
	 * this method converts the course catalogue into a String and returns it
	 */
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
