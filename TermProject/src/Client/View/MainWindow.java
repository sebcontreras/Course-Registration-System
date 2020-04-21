package Client.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * This class houses the main window. It is responsible for setting the layout of the main window 
 * and assigning action listeners.
 * 
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class MainWindow extends JFrame implements Standardization{
	
	/**
	 * The label for the main window
	 */
	private JLabel titleLabel = new JLabel("Student Registration System");
	
	/**
	 * The label for the student name
	 */
	private JLabel studentName = new JLabel();
	
	/**
	 * The label for the student ID
	 */
	private JLabel studentID = new JLabel();
	
	/**
	 * The sub title label for the main window
	 */
	private JLabel subTitleLabel = new JLabel("Main Window");
	
	/**
	 * The button for search courses
	 */
	private JButton catalogueB = new JButton("Search Courses");
	
	/**
	 * The button for my courses
	 */
	private JButton myCoursesB = new JButton("My Courses");
	
	/**
	 * The button for quit function
	 */
	private JButton quitB = new JButton("Quit");
	
	/**
	 * The north Jpanel
	 */
	private JPanel north = new JPanel();
	
	/**
	 * The south Jpanel
	 */
	private JPanel south = new JPanel();
	
	/**
	 * Boolean variable to determine if a name has been added yet
	 */
	private boolean addedName = false;

	/**
	 * Constructor method which sets the layout of the main window.
	 */
	public MainWindow() {
		super("Main Window");
		setSize(500,500);
		setTitle("Main Window");
		setLayout(new BorderLayout());
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.setBackground(Color.red);
		south.setBackground(Color.red);
		titleLabel.setFont(titleFont);
		subTitleLabel.setFont(subTitleFont);
		catalogueB.setFont(buttonFont);
		catalogueB.setBackground(Color.white);
		myCoursesB.setFont(buttonFont);
		myCoursesB.setBackground(Color.white);
		quitB.setFont(buttonFont);
		quitB.setBackground(Color.white);
		north.add(titleLabel);
		north.add(subTitleLabel);
		south.add(catalogueB);
		south.add(myCoursesB);
		south.add(quitB);
		
		add("North", north);
		add("South", south);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Adds the catalogue listener to the catalogue.
	 * 
	 * @param listener the listener for the  catalogue
	 */
	public void addCatalogueListener (ActionListener listener) {
		catalogueB.addActionListener(listener);
	}
	
	/**
	 * Adds the myCourses listener to myCourses.
	 * 
	 * @param listener the listener for myCourses
	 */
	public void addMyCoursesListener (ActionListener listener) {
		myCoursesB.addActionListener(listener);
	}
	
	/**
	 * Adds the quit listener to quit.
	 * 
	 * @param listener the listener for quit
	 */
	public void addQuitListener(ActionListener listener) {
		quitB.addActionListener(listener);
	}
	
	/**
	 * Adds the student info based on the parameters to the corresponding member variables.
	 * 
	 * @param name the name to be set
	 * @param id the id to be set
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

	/**
	 * Sends messages to the option pane.
	 * 
	 * @param communicate the string to be communicated
	 */
	public void sendMessage(String communicate) {
		JOptionPane.showMessageDialog(this, communicate);
	}
	

	
}
