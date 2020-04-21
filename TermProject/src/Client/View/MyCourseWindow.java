package Client.View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This class houses the myCourse window. It is responsible for setting the layout of the myCourse window 
 * and assigning action listeners.
 * 
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class MyCourseWindow extends JFrame implements Standardization{
	
	/**
	 * The title label for the MyCourseWindow
	 */
	private JLabel titleLabel = new JLabel("Student Registration System");
	
	/**
	 * The subtitle label for MyCourseWindow
	 */
	private JLabel subTitleLabel = new JLabel("My Courses");
	
	/**
	 * The label for studentName of the login student
	 */
	private JLabel studentName = new JLabel();
	
	/**
	 * The label for studentID of the login student
	 */
	private JLabel studentID = new JLabel();
	
	/**
	 * The button for add course
	 */
	private JButton addB = new JButton("Add Course");
	
	/**
	 * The button for back to menu
	 */
	private JButton backB = new JButton("Back To Main Menu");
	
	/**
	 * The button for drop course
	 */
	private JButton dropB = new JButton("Drop Course");
	
	/**
	 * The button for quit
	 */
	private JButton quitB = new JButton("Quit");
	
	/**
	 * The text area for the student's courses to be displayed
	 */
	private JTextArea courses = new JTextArea();
	
	/**
	 * The window scroller for the window
	 */
	private JScrollPane scroller = new JScrollPane(courses);
	
	/**
	 * The north JPanel
	 */
	private JPanel north = new JPanel();
	
	/**
	 * The south Jpanel
	 */
	private JPanel south = new JPanel();
	
	/**
	 * Boolean variable to determine if the login student name has been added
	 */
	private boolean addedName = false;
	
	/**
	 * Constructor for the myCourse window. It sets the layout of the window.
	 */
	public MyCourseWindow() {
		super("My Course Window");
		setSize(500,500);
		setTitle("My Course Window");
		setLayout(new BorderLayout());
		titleLabel.setFont(titleFont);
		subTitleLabel.setFont(subTitleFont);
		north.setBackground(Color.red);
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		south.setBackground(Color.red);
		backB.setFont(buttonFont);
		backB.setBackground(Color.white);
		dropB.setFont(buttonFont);
		dropB.setBackground(Color.white);
		addB.setFont(buttonFont);
		addB.setBackground(Color.white);
		quitB.setFont(buttonFont);
		quitB.setBackground(Color.white);
		north.add(titleLabel);
		north.add(subTitleLabel);
		
		courses.setEditable(false);
		courses.setFont(standardFont);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		south.add(addB);
		south.add(dropB);
		south.add(backB);
		south.add(quitB);
		add("North", north);
		add("South", south);
		add("Center", scroller);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Method adds the action listener to 'add courses'
	 * 
	 * @param listener the listener to be added
	 */
	public void addAddCourseListener(ActionListener listener) {
		addB.addActionListener(listener);
	}
	
	/**
	 * Method adds the action listener to 'drop courses'
	 * 
	 * @param listener the listener to be added
	 */
	public void addDropCourseListener(ActionListener listener) {
		dropB.addActionListener(listener);
	}
	
	/**
	 * Method adds the action listener to 'return'
	 * 
	 * @param listener the listener to be added
	 */
	public void addReturnListener(ActionListener listener) {
		backB.addActionListener(listener);
	}
	
	/**
	 * Method adds the action listener to 'add courses'
	 * 
	 * @param listener the listener to be added
	 */
	public void addQuitListener(ActionListener listener) {
		quitB.addActionListener(listener);
	}
	
	/**
	 * Gets course from the input, formats the result and returns it as a string array.
	 * 
	 * @return string array of the course
	 */
	public String [] getCourse() {
		String input ="";
		String [] course;
		while (true) {
			input = JOptionPane.showInputDialog("Please enter the course you would like to add");
			course = input.split(" ");
			if (course[0].length()==4 && course[1].length()==3) {
				break;
			}
			displayMessage("Error. Invalid format. Enter your choice as a 4-letter word followed by the 3-digit number.");
		}
		
		return course;
	}
	
	/**
	 * Gets course from the input, formats the result and returns it as a string array so the caller knows what course to remove.
	 * 
	 * @return string array of the course
	 */
	public String [] getCourseToRemove() {
		String input ="";
		String [] course;
		while (true) {
			input = JOptionPane.showInputDialog("Please enter the course you would like to remove");
			course = input.split(" ");
			if (course[0].length()==4 && course[1].length()==3) {
				break;
			}
			displayMessage("Error. Invalid format. Enter your choice as a 4-letter word followed by the 3-digit number.");
		}
		
		return course;
	}
	
	/**
	 * Displays the parameter string to the JOptionPane.
	 * 
	 * @param string the string to be displayed
	 */
	public void displayMessage(String string) {
		JOptionPane.showMessageDialog(this, string);
	}
	
	/**
	 * Sets the student's registered courses from the parameter string.
	 * 
	 * @param string the string of courses to which the student is registered
	 */
	public void setCourses(String string) {
		courses.setText("REGISTERED COURSES:\n"+string);
	}

	/**
	 * Sets the student info of the logged in student to be shown in the display window.
	 * 
	 * @param name the name of the student
	 * @param id the ID of the student
	 */
	public void setStudentInfo(String name, String id) {
		if (!addedName) {
			studentName = new JLabel("Welcome, "+name);
			studentName.setFont(studentFont);
			studentID = new JLabel("ID: "+id);
			studentID.setFont(studentFont);
			north.add(studentName);
			north.add(studentID);
			addedName =true;
		}
	}


}
