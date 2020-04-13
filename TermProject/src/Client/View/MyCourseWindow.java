package Client.View;
//
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyCourseWindow extends JFrame implements Standardization{
	private JLabel titleLabel = new JLabel("Student Registration System");
	private JLabel subTitleLabel = new JLabel("My Courses");
	private JLabel studentName, studentID;
	private JButton addB = new JButton("Add Course");
	private JButton backB = new JButton("Back To Main Menu");
	private JButton dropB = new JButton("Drop Selected Course");
	private JPanel north = new JPanel();
	private JPanel center = new JPanel();
	private JPanel south = new JPanel();
	
	public MyCourseWindow() {
		super("My Course Window");
		getStudentInfo();
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
		north.add(titleLabel);
		north.add(subTitleLabel);
		north.add(studentName);
		north.add(studentID);
		
		south.add(addB);
		south.add(dropB);
		south.add(backB);
		add("North", north);
		add("South", south);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void getStudentInfo() {
		//method to get student's name
		//get student's name from controller?
		String name = "Name: Student's Name";
		studentName = new JLabel(name);
		studentName.setFont(studentFont);
		//get student's ID from controller?
		String id = "ID: 000000";
		studentID = new JLabel(id);
		studentID.setFont(studentFont);
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
	
	private void displayMessage(String string) {
		JOptionPane.showMessageDialog(this, string);
	}

//	public static void main (String []args) {
//		MyCourseWindow myCourse = new MyCourseWindow();
//		myCourse.setVisible(true);
//	}

}
