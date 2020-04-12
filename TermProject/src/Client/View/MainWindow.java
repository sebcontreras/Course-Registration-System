package Client.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements FontConstants{
	private JLabel titleLabel = new JLabel("Student Registration System");
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
		titleLabel.setFont(titleFont);
		subTitleLabel.setFont(subTitleFont);
		north.setBackground(Color.red);
		north.setLayout(new BorderLayout());
		south.setBackground(Color.red);
		catalogueB.setFont(buttonFont);
		catalogueB.setBackground(Color.white);
		myCoursesB.setFont(buttonFont);
		myCoursesB.setBackground(Color.white);
		north.add("North", titleLabel);
		north.add("South", subTitleLabel);
		south.add(catalogueB);
		south.add(myCoursesB);
		north.add(titleLabel);
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
	
	public static void main (String []args) {
		MainWindow main = new MainWindow();
		main.setVisible(true);
	}
}
