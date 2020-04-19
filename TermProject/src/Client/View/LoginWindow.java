package Client.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginWindow extends JFrame implements Standardization{
	private JLabel welcome = new JLabel ("Welcome to the");
	private JLabel titleLabel = new JLabel("Student Registration System");
	private JButton loginB = new JButton ("Login");
	private JTextArea name = new JTextArea("Name:");
	private JTextField nameInput = new JTextField(20);
	private JTextArea id = new JTextArea("ID:");
	private JTextField idInput = new JTextField(5);
	private JPanel north= new JPanel();
	private JPanel center=new JPanel();
	private JPanel south= new JPanel();
	
	public LoginWindow() {
		super("Login Window");
		setSize(400,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		titleLabel.setFont(titleFont);
		welcome.setFont(subTitleFont);
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.setBackground(Color.red);
		north.add(welcome);
		north.add(titleLabel);
		center.setLayout(new FlowLayout());
		center.setBackground(Color.white);
		center.add(name);
		center.add(nameInput);
		center.add(id);
		center.add(idInput);
		south.setLayout(new FlowLayout());
		south.setBackground(Color.red);
		south.add(loginB);
		add("North", north);
		add("Center", center);
		add("South", south);
	}
	
	public void addLoginListener(ActionListener listener) {
		loginB.addActionListener(listener);
	}
	
	public String getName () {
		return getStringFromTextBox(nameInput);
	}
	
	public String getID () {
		return getStringFromTextBox(idInput);
	}
	
	public String getStringFromTextBox(JTextField field) {
		return field.getText();
	}
	
//	public static void main (String args[]) {
//		LoginWindow login = new LoginWindow();
//		login.setVisible(true);
//	}
}
