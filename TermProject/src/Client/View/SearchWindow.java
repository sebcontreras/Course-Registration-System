package Client.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchWindow extends JFrame implements FontConstants{
	private JLabel titleLabel = new JLabel("Student Registration System");
	private JLabel subTitleLabel = new JLabel("Search Window");
	private JButton searchB = new JButton("Search");
	private JButton viewB = new JButton("View All Courses");
	private JButton backB = new JButton("Back To Main Menu");
	private JPanel north = new JPanel();
	private JPanel center = new JPanel();
	private JPanel south = new JPanel();
	
	public SearchWindow() {
		super("Search Window");
		setSize(500,500);
		setTitle("Search Window");
		setLayout(new BorderLayout());
		north.setLayout(new BorderLayout());
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
		north.add("North", titleLabel);
		north.add("South", subTitleLabel);
		south.add(searchB);
		south.add(viewB);
		south.add(backB);
		
		add("North", north);
		add("South", south);
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
	
	public static void main (String []args) {
		SearchWindow search = new SearchWindow();
		search.setVisible(true);
	}
}
