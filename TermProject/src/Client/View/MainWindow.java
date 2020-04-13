package Client.View;
//
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements Standardization{
	private JLabel studentName, studentID;
	private JLabel subTitleLabel = new JLabel("Main Window");
	private JButton catalogueB = new JButton("Search Courses");
	private JButton myCoursesB = new JButton("My Courses");
	private JPanel north = new JPanel();
	private JPanel center = new JPanel();
	private JPanel south = new JPanel();

	public MainWindow() {
		super("Main Window");
		setSize(500,500);
		setTitle("Main Window");
		setLayout(new BorderLayout());
		subTitleLabel.setText("Main Window");
		catalogueB.setFont(buttonFont);
		catalogueB.setBackground(Color.white);
		myCoursesB.setFont(buttonFont);
		myCoursesB.setBackground(Color.white);
		getStudentInfo();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		titleLabel.setFont(titleFont);
		subTitleLabel.setFont(subTitleFont);
		north.setBackground(Color.red);
		south.setBackground(Color.red);
		
		north.add(titleLabel);
		north.add(subTitleLabel);
		north.add(studentName);
		north.add(studentID);
		south.add(catalogueB);
		south.add(myCoursesB);
		
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
		MainWindow main = new MainWindow();
		main.setVisible(true);
	}

	
}
