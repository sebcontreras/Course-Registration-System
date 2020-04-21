package Client.View;

import java.awt.event.ActionListener;



/**
 * This class is responsible for managing the display windows in the program.
 * It creates each window in the application, assigns actionListeners and 
 * manages the messages to be displayed by each window.
 * 
 * @author sebastiancontreras
 *
 */
public class FrameManager {
	
	/**
	 * The main window menu after logging in
	 */
	private MainWindow mainWindow;
	
	/**
	 * The window which displays the courses to which the student is registered
	 */
	private MyCourseWindow myCourseWindow;
	
	/**
	 * The window which prompts the user to type in a course to search for
	 */
	private SearchWindow searchWindow;
	
	/**
	 * The window for the student to log into 
	 */
	private LoginWindow loginWindow;
	
	/**
	 * Constructor for FrameManager. Responsible for creating each window. 
	 */
	public FrameManager() {
		mainWindow = new MainWindow();
		myCourseWindow = new MyCourseWindow();
		searchWindow = new SearchWindow();
		loginWindow = new LoginWindow();
	}
	
	/**
	 * Sets the visibility of the login window to true when the program first starts/
	 */
	public void start() {
		loginWindow.setVisible(true);
	}

	/**
	 * Sets the visibility of the search window to true.
	 */
	public void displaySearchWindow() {
		searchWindow.setVisible(true);
	}

	/**
	 * Calls get course function from search window and returns the course which the student is searching for.
	 * 
	 * @return the course which was search for in a string array.
	 */
	public String [] getCourseFromSearch() {
		return searchWindow.getCourse();
	}

	/**
	 * Sends the correspond message to the search window in response to the success of the search.
	 * @param string the corresponding message to the searchWindow.
	 */
	public void sendMessagetoSearchWindow(String string) {
		searchWindow.displayMessage(string);	
	}	
	
	/**
	 * Sets the search window visibility to false and disposes of the window.
	 */
	public void closeSearchWindow() {
		searchWindow.setVisible(false);
		searchWindow.dispose();
	}

	/**
	 * Sets the main window visibility to false and disposes of the window.
	 */
	public void closeMainWindow() {
		mainWindow.setVisible(false);
		mainWindow.dispose();
		
	}

	/**
	 * Sets the visibility of the main window to true.
	 */
	public void displayMainWindow() {
		mainWindow.setVisible(true);
	}

	/**
	 * Method responsible for adding action listeners to the buttons in the search window.
	 * 
	 * @param searchSearchListener the listener for the search button
	 * @param searchViewAllListener the listener for the view all button
	 * @param searchBackListener the listener for the search back button
	 * @param quitListener the listener for the quit button
	 */
	public void addListenersToSearchWindow(ActionListener searchSearchListener,
			ActionListener searchViewAllListener, ActionListener searchBackListener, ActionListener
			quitListener) {
		searchWindow.addSearchListener(searchSearchListener);
		searchWindow.addViewListener(searchViewAllListener);
		searchWindow.addBackListener(searchBackListener);
		searchWindow.addQuitListener(quitListener);
	}

	/**
	 * Sets the visibility of the myCourses window to true.
	 */
	public void displayMyCoursesWindow() {
		myCourseWindow.setVisible(true);
	}

	/**
	 * Method which calls method from myCourseWindow to search for and return a string array from
	 * the students course list.
	 * 
	 * @return string array with the course
	 */
	public String[] getCourseFromMyCourse() {
		return myCourseWindow.getCourse();
	}

	/**
	 * Method responsible for adding action listeners to the buttons in the myCourse window.
	 * 
	 * @param myCourseAddCourseListener the listener for the add course button
	 * @param myCourseDropCourseListener the listener for the drop course button
	 * @param myCourseReturnListener the listerner for the return course button
	 * @param quitListener the listener for the quit listener button
	 */
	public void addListenersToMyCourseWindow(ActionListener myCourseAddCourseListener,
			ActionListener myCourseDropCourseListener, ActionListener myCourseReturnListener,
			ActionListener quitListener) {
		myCourseWindow.addAddCourseListener(myCourseAddCourseListener);
		myCourseWindow.addDropCourseListener(myCourseDropCourseListener);
		myCourseWindow.addReturnListener(myCourseReturnListener);
		myCourseWindow.addQuitListener(quitListener);
	}

	/**
	 * Sets the myCourse window visibility to false and disposes of the window.
	 */
	public void closeMyCourseWindow() {
		myCourseWindow.setVisible(false);
		myCourseWindow.dispose();
	}

	/**
	 * Method responsible for adding action listeners to the buttons in the mainMenu window.
	 * 
	 * @param mainSearchCoursesListener the listener for the mainSearchCourse button
	 * @param mainMyCoursesListener the listener for the mainCourse button
	 * @param quitListener the listener for the quit button
	 */
	public void addListenersToMainMenu(ActionListener mainSearchCoursesListener,
			ActionListener mainMyCoursesListener, ActionListener quitListener) {
		mainWindow.addCatalogueListener(mainSearchCoursesListener);
		mainWindow.addMyCoursesListener(mainMyCoursesListener);
		mainWindow.addQuitListener(quitListener);
	}

	/**
	 * Method to return the name provided from the loginWindow.
	 * 
	 * @return the string name from the login window
	 */
	public String getName() {
		return loginWindow.getName();
	}

	/**
	 * Method to return the ID provided from the loginWindow.
	 * 
	 * @return the string ID from the login window
	 */
	public String getID() {
		return loginWindow.getID();
	}

	/**
	 * Method which adds the listener for the login window
	 * 
	 * @param loginLoginListener the listener for the login window
	 */
	public void addListenersToLogin(ActionListener loginLoginListener) {
		loginWindow.addLoginListener(loginLoginListener);
	}

	/**
	 * Method which sets the information for the student object in the mainWindow view
	 * 
	 * @param name the name of the login student
	 * @param id the id of the login student
	 */
	public void mainSetStudentInfo(String name, String id) {
		mainWindow.setStudentInfo(name, id);
	}

	/**
	 * Method which sets the information for the student object in the search view
	 * 
	 * @param name the name of the login student
	 * @param id the id of the login student
	 */
	public void searchSetStudentInfo(String name, String id) {
		searchWindow.setStudentInfo(name, id);
	}

	/**
	 * Method which sets the information for the student object in the myCourses view
	 * 
	 * @param name the name of the login student
	 * @param id the id of the login student
	 */
	public void myCoursesSetStudentInfo(String name, String id) {
		myCourseWindow.setStudentInfo(name,id);
	}

	/**
	 * Sets the login visibility to false and disposes of the window.
	 */
	public void closeLoginWindow() {
		loginWindow.setVisible(false);
		loginWindow.dispose();
	}

	/**
	 * Sets the list of all courses in the searchWindow.
	 * 
	 * @param string the string of the courses
	 */
	public void setAllCourses(String string) {
		searchWindow.viewAllCourses(string);
	}

	/**
	 * Method which passes the param string to the myCourse window.
	 * 
	 * @param string the message to be displayed
	 */
	public void sendMessagetoMyCourseWindow(String string) {
		myCourseWindow.displayMessage(string);
	}

	/**
	 * Method which returns the course to be removed from myCourses.
	 * 
	 * @return the string array of the course to be removed
	 */
	public String[] getCourseToRemoveFromMyCourse() {
		return myCourseWindow.getCourseToRemove();
	}

	/**
	 * Sets the list of all students in the searchWindow.
	 * 
	 * @param string the string of the students
	 */
	public void setAllStudentCourses(String string) {
		myCourseWindow.setCourses(string);
		
	}

	/**
	 * Method which communicates the provided message to the mainWindow.
	 * 
	 * @param communicate the message string to be displayed in the mainWindow
	 */
	public void sendMessagetoMainWindow(String communicate) {
		mainWindow.sendMessage(communicate);
		
	}
	
}
