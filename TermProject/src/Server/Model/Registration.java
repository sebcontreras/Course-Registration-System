package Server.Model;
/**
 * The purpose of this class is to represent the registration into a class
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class Registration {
	/*
	 * the student that is trying to register
	 */
	private Student theStudent;
	/*
	 * the course offering that the student is trying to register into
	 */
	private CourseOffering theOffering;
	/*
	 * the grade the student receives in the class
	 */
	private char grade;
	/**
	 * constructs an instance of Registration and initiates the grade
	 */
	public Registration() {
		grade = '-';
	}
	/**
	 * this method completes the registration of a given student into a given course offering
	 * @param st, the student trying to register
	 * @param of, the course offering the student is interested in
	 */
	public void completeRegistration (Student st, CourseOffering of) {
		theStudent = st;
		theOffering = of;
		addRegistration ();
	}
	/**
	 * this method adds the registration to the student and the course offering
	 */
	private void addRegistration () {
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
	}
	
	/**
	 * this method is the getter for the student
	 * @return the student
	 */
	public Student getTheStudent() {
		return theStudent;
	}
	/**
	 * this method is the setter for the student
	 * @param theStudent, the student
	 */
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	/**
	 * this method is the getter for the course offering
 	 * @return the course offering
	 */
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	/**
	 * this method is the setter for the course offering
	 * @param theOffering, the course offering
	 */
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	/**
	 * this method is the getter for the grade
	 * @return the grade
	 */
	public char getGrade() {
		return grade;
	}
	/**
	 * this method is the setter for the grade
	 * @param grade, the grade
	 */
	public void setGrade(char grade) {
		this.grade = grade;
	}
	/**
	 * this method converts the registration into a String and returns that
	 */
	@Override
	public String toString () {
		String st = getTheOffering () + "";
		st += "Grade: " + getGrade();
		st += "\n-----------\n";
		return st;
		
	}
	
}

