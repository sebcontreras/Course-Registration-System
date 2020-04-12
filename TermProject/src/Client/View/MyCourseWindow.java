package Client.View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class MyCourseWindow extends JFrame implements Standardization{
	private JLabel subTitleLabel = new JLabel("My Courses");
	private JLabel studentName, studentID;
	private JButton addB = new JButton("Add Course");
	private JButton backB = new JButton("Back");
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
	
	public static void main (String []args) {
		MyCourseWindow myCourse = new MyCourseWindow();
		myCourse.setVisible(true);
	}

}
