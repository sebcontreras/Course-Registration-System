package Client.View;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
//
public interface Standardization {
	//standardizes fonts across view 
	static final Font titleFont = new Font("Verdana", Font.BOLD, 18);
	static final Font subTitleFont = new Font("Verdana", Font.BOLD + Font.ITALIC, 16);
	static final Font buttonFont = new Font("Verdana", Font.PLAIN, 12);
	static final Font studentFont = new Font("Verdana", Font.PLAIN, 16);
	
	//standardizes labels across view
	static final JLabel titleLabel = new JLabel("Student Registration System");
	
}
