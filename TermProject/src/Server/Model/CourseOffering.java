package Server.Model;

import java.util.ArrayList;
/**
 * The purpose of this class is to represent course offerings of the courses in the database
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class CourseOffering {
	/*
	 * the section number
	 */
	private int secNum;
	/*
	 * the section capacity
	 */
	private int secCap;
	/*
	 * the course of this course offering
	 */
	private Course theCourse;
	/*
	 * the list of registrations in this course offering
	 */
	private ArrayList <Registration> offeringRegList;
	/*
	 * the checker for a full course offering
	 */
	private boolean full;
	/**
	 * constructs an instance if CourseOffering and sets the section and capacity,
	 * also initiates the registration list
	 * @param secNum
	 * @param secCap
	 */
	public CourseOffering (int secNum, int secCap) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
	}
	/**
	 * this method checks id the course offering is full
	 * @return true if full, false otherwise
	 */
	public boolean isFull() {
		if (offeringRegList.size()<secCap) {
			full=false;
		}
		else {
			full=true;
		}
		return full;
	}
	
	/**
	 * this method is the getter for the section number
	 * @return the section number
	 */
	public int getSecNum() {
		return secNum;
	}
	/**
	 * this method is the setter for the section number
	 * @param secNum, the section number
	 */
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	/**
	 * this method is the getter for the capacity
	 * @return the capacity
	 */
	public int getSecCap() {
		return secCap;
	}
	/**
	 * this method is the setter for the capacity
	 * @param secCap, the capacity
	 */
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	/**
	 * this method is the getter for the course
	 * @return the course
	 */
	public Course getTheCourse() {
		return theCourse;
	}
	/**
	 * this method is the setter for the course
	 * @param theCourse, the course
	 */
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	/**
	 * this method converts the course offering into a String and returns it
	 */
	@Override
	public String toString () {
		String st = getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\n";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() +"\n";
		return st;
	}
	/**
	 * this method adds the given registration to the registration list
	 * @param registration, the registration to be added
	 */
	public void addRegistration(Registration registration) {
		offeringRegList.add(registration);
	}
}
