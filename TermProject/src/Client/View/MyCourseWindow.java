package Client.View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyCourseWindow extends JFrame implements Standardization{
	private JLabel titleLabel = new JLabel("Student Registration System");
	private JLabel subTitleLabel = new JLabel("My Courses");
	private JLabel studentName = new JLabel();
	private JLabel studentID = new JLabel();
	private JButton addB = new JButton("Add Course");
	private JButton backB = new JButton("Back To Main Menu");
	private JButton dropB = new JButton("Drop Course");
	private JButton quitB = new JButton("Quit");
	private JTextArea courses = new JTextArea();
	private JScrollPane scroller = new JScrollPane(courses);
	private JPanel north = new JPanel();
	private JPanel south = new JPanel();
	private boolean addedName = false;
	
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

	
	public void addAddCourseListener(ActionListener listener) {
		addB.addActionListener(listener);
	}
	
	public void addDropCourseListener(ActionListener listener) {
		dropB.addActionListener(listener);
	}
	
	public void addReturnListener(ActionListener listener) {
		backB.addActionListener(listener);
	}
	
	public void addQuitListener(ActionListener listener) {
		quitB.addActionListener(listener);
	}
	
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
	
	public void displayMessage(String string) {
		JOptionPane.showMessageDialog(this, string);
	}
	
	public void setCourses(String string) {
		courses.setText("REGISTERED COURSES:\n"+string);
	}

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
