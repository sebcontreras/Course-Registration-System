package Client.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This class houses the login window. It is responsible for setting the layout for the login
 * window and returning the inputed values from the text areas.
 * 
 * @author sebastiancontreras
 *
 */
public class LoginWindow extends JFrame implements Standardization{
	
	/**
	 * Welcome label for the login window.
	 */
	private JLabel welcome = new JLabel ("Welcome to the");
	
	/**
	 * Label for the name of the project.
	 */
	private JLabel titleLabel = new JLabel("Student Registration System");
	
	/**
	 * The login button.
	 */
	private JButton loginB = new JButton ("Login");
	
	/**
	 * The text area for the student to write his name.
	 */
	private JTextArea name = new JTextArea("Name:");
	
	/**
	 * The text field for the name input.
	 */
	private JTextField nameInput = new JTextField(20);
	
	/**
	 * The text area for the student to write his ID.
	 */
	private JTextArea id = new JTextArea("ID:");
	
	/**
	 * The text file for the id input.
	 */
	private JTextField idInput = new JTextField(5);
	
	/**
	 * The north Jpanel.
	 */
	private JPanel north= new JPanel();
	
	/**
	 * The center Jpanel.
	 */
	private JPanel center=new JPanel();
	
	/**
	 * The south Jpanel.
	 */
	private JPanel south= new JPanel();
	
	/**
	 * Constructor method which sets the layout of the login window.
	 */
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
		name.setEditable(false);
		id.setEditable(false);
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
	
	/**
	 * Adds the action listener for the login.
	 * 
	 * @param listener the listener
	 */
	public void addLoginListener(ActionListener listener) {
		loginB.addActionListener(listener);
	}
	
	/**
	 * Getter which returns the input name from the text box.
	 */
	public String getName () {
		return getStringFromTextBox(nameInput);
	}
	
	/**
	 * Getter which returns the ID from the text box.
	 * 
	 * @return the id from the text box
	 */
	public String getID () {
		return getStringFromTextBox(idInput);
	}
	
	/**
	 * Returns the string from the text box.
	 * 
	 * @param field the text field to be returned from
	 * @return the text from the field
	 */
	public String getStringFromTextBox(JTextField field) {
		return field.getText();
	}
	
}
