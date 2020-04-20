package Client.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements Standardization{
	private JLabel titleLabel = new JLabel("Student Registration System");
	private JLabel studentName = new JLabel();
	private JLabel studentID = new JLabel();
	private JLabel subTitleLabel = new JLabel("Main Window");
	private JButton catalogueB = new JButton("Search Courses");
	private JButton myCoursesB = new JButton("My Courses");
	private JButton quitB = new JButton("Quit");
	private JPanel north = new JPanel();
	private JPanel south = new JPanel();
	private boolean addedName = false;

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
	
	public void addCatalogueListener (ActionListener listener) {
		catalogueB.addActionListener(listener);
	}
	
	public void addMyCoursesListener (ActionListener listener) {
		myCoursesB.addActionListener(listener);
	}
	
	public void addQuitListener(ActionListener listener) {
		quitB.addActionListener(listener);
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

	public void sendMessage(String communicate) {
		JOptionPane.showMessageDialog(this, communicate);
	}
	
//	public static void main (String []args) {
//		MainWindow main = new MainWindow();
//		main.setVisible(true);
//	}

	
}
