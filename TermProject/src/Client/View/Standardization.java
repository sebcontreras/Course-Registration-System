package Client.View;
import java.awt.Font;

/**
 * This class is responsible for setting the font for the windows used in the application.
 * 
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public interface Standardization {
	//standardizes fonts across view 
	static final Font titleFont = new Font("Verdana", Font.BOLD, 18);
	static final Font subTitleFont = new Font("Verdana", Font.BOLD + Font.ITALIC, 16);
	static final Font buttonFont = new Font("Verdana", Font.PLAIN, 12);
	static final Font studentFont = new Font("Verdana", Font.PLAIN, 16);
	static final Font standardFont = new Font("Verdana", Font.PLAIN, 14);
	
	
	
}
