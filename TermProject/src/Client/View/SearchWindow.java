package Client.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * This class houses the search window. It is responsible for setting the layout of the search window 
 * and assigning action listeners.
 * 
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class SearchWindow extends JFrame implements Standardization{
	
	
	/**
	 * The title label for the SearchWindow.
	 */
	private JLabel titleLabel = new JLabel("Student Registration System");
	
	/**
	 * The studentName label.
	 */
	private JLabel studentName = new JLabel();
	
	/**
	 * The student ID label.
	 */
	private JLabel studentID = new JLabel();
	
	/**
	 * The subtitle label for the search window.
	 */
	private JLabel subTitleLabel = new JLabel("Search Window");
	
	/**
	 * The button for the 'search' method
	 */
	private JButton searchB = new JButton("Search");
	
	/**
	 * The button for the 'view all courses' method
	 */
	private JButton viewB = new JButton("View All Courses");
	
	/**
	 * The button for the 'back to main menu' method
	 */
	private JButton backB = new JButton("Back To Main Menu");
	
	/**
	 * The button for the 'quit' method
	 */
	private JButton quitB = new JButton("Quit");
	
	/**
	 * The text area for the student's courses to be displayed
	 */
	private JTextArea allCourses = new JTextArea();
	
	/**
	 * The window scroller for the SearchWindow
	 */
	private JScrollPane scroller = new JScrollPane(allCourses);
	
	/**
	 * The north JPanel
	 */
	private JPanel north = new JPanel();
	
	/**
	 * The south JPanel
	 */
	private JPanel south = new JPanel();
	
	/**
	 * Boolean variable to determine if the login student name has been added
	 */
	private boolean addedName = false;
	
	/**
	 * Constructor for the SearchWindow. Sets the layout of the searchWindow pane.
	 */
	public SearchWindow() {
		super("Search Window");
		setSize(500,500);
		setTitle("Search Window");
		setLayout(new BorderLayout());
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.setBackground(Color.red);
		south.setBackground(Color.red);
		titleLabel.setFont(titleFont);
		subTitleLabel.setFont(subTitleFont);
		searchB.setFont(buttonFont);
		searchB.setBackground(Color.white);
		viewB.setFont(buttonFont);
		viewB.setBackground(Color.white);
		backB.setFont(buttonFont);
		backB.setBackground(Color.white);
		quitB.setFont(buttonFont);
		quitB.setBackground(Color.white);
		north.add(titleLabel);
		north.add(subTitleLabel);
		
		allCourses.setEditable(false);
		allCourses.setFont(standardFont);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add("Center", scroller);
		
		south.add(searchB);
		south.add(viewB);
		south.add(backB);
		south.add(quitB);
		
		add("North", north);
		add("South", south);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Method adds the action listener to 'add courses'
	 * 
	 * @param listener the listener to be added
	 */
	public void addSearchListener(ActionListener listener) {
		searchB.addActionListener(listener);
	}
	
	/**
	 * Method adds the action listener to 'add view'
	 * 
	 * @param listener the listener to be added
	 */
	public void addViewListener(ActionListener listener) {
		viewB.addActionListener(listener);
	}
	
	/**
	 * Method adds the action listener to 'back'
	 * 
	 * @param listener the listener to be added
	 */
	public void addBackListener(ActionListener listener) {
		backB.addActionListener(listener);
	}
	
	/**
	 * Method adds the action listener to 'quit'
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
			input = JOptionPane.showInputDialog("Please enter the course you would like to search");
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
	public void displayMessage(String s) {
		JOptionPane.showMessageDialog(this, s);
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
			addedName = true;
		}
	}
	
	/**
	 * Methods which displays all courses.
	 * 
	 * @param string the courses to be displayed
	 */
	public void viewAllCourses(String string) {
		allCourses.setText(string);
	}
	

}
