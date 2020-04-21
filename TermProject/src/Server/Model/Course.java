package Server.Model;

import java.util.ArrayList;
/**
 * The purpose of this class is to represent a course and contain the information associated with the course
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class Course {
	/*
	 * the name of the course
	 */
	private String courseName;
	/*
	 * the number of the course
	 */
	private int courseNum;
	/*
	 * the prerequisite list for the course
	 */
	private ArrayList<Course> preReq;
	/*
	 * the list of offerings for the course
	 */
	private ArrayList<CourseOffering> offeringList;
	/**
	 * constructs an instance of Course and initiates the lists and sets the
	 *  name and number of the course
	 * @param courseName, the name of the course
	 * @param courseNum, the number of the course
	 */
	public Course(String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
	}
	/**
	 * this method adds course offering to the course
	 * @param offering, the offering being added
	 */
	public void addOffering(CourseOffering offering) {
		if (offering != null && offering.getTheCourse() == null) {
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName) || offering.getTheCourse().getCourseNum() != courseNum) {
				System.err.println("Error! This section belongs to another course!");
				return;
			}
			
			offeringList.add(offering);
		}
	}
	/**
	 * this method is the getter for the offering list
	 * @return the offering list
	 */
	public ArrayList<CourseOffering> getOfferingList(){
		return offeringList;
	}
	/**
	 * this method is the getter for the course name
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * this method is the setter for the course name
	 * @param courseName, the course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * this method is the getter for the course number
	 * @return the course number
	 */
	public int getCourseNum() {
		return courseNum;
	}
	/**
	 * this method is the setter for the course number
	 * @param courseNum, the course number
	 */
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	/**
	 * this method converts the course into a String and returns that
	 */
	@Override
	public String toString () {
		String st = "\n";
		st += getCourseName() + " " + getCourseNum ();
		st += "\nAll course sections:\n";
		for (CourseOffering c : offeringList)
			st += c;
		st += "\n-------\n";
		return st;
	}
	/**
	 * this method finds the course offering at the given index of the course offering list
	 * @param i, the index for the offering list
	 * @return either the found course offering or null
	 */
	public CourseOffering getCourseOfferingAt(int i) {
		if (i < 0 || i >= offeringList.size() )
			return null;
		else
			return offeringList.get(i);
	}
	/**
	 * this method compares the given course with itself 
	 * @param comp, the other course being compared
	 * @return true if they are equal, otherwise false
	 */
	public boolean equals(Course comp) {
		
		if(this.getCourseName().equals(comp.getCourseName())) {
			if(this.getCourseNum() == comp.getCourseNum())
				return true;
		}
		return false;
	}

}

