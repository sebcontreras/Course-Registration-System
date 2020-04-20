package Client.View;
//
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchWindow extends JFrame implements Standardization{
	private JLabel titleLabel = new JLabel("Student Registration System");
	private JLabel studentName = new JLabel();
	private JLabel studentID = new JLabel();
	private JLabel subTitleLabel = new JLabel("Search Window");
	private JButton searchB = new JButton("Search");
	private JButton viewB = new JButton("View All Courses");
	private JButton backB = new JButton("Back To Main Menu");
	private JButton quitB = new JButton("Quit");
	private JTextArea allCourses = new JTextArea();
	private JScrollPane scroller = new JScrollPane(allCourses);
	private JPanel north = new JPanel();
//	private JPanel center = new JPanel();
	private JPanel south = new JPanel();
	private boolean addedName = false;
	
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
//		add("Center", center);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addSearchListener(ActionListener listener) {
		searchB.addActionListener(listener);
	}
	
	public void addViewListener(ActionListener listener) {
		viewB.addActionListener(listener);
	}
	
	public void addBackListener(ActionListener listener) {
		backB.addActionListener(listener);
	}
	
	public void addQuitListener(ActionListener listener) {
		quitB.addActionListener(listener);
	}
	
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
	
	public void displayMessage(String s) {
		JOptionPane.showMessageDialog(this, s);
	}
	
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
	
	public void viewAllCourses(String string) {
		allCourses.setText(string);
	}
	
//	public static void main (String []args) {
//		SearchWindow search = new SearchWindow();
//		search.setVisible(true);
//	}
}
