package Client.View;

import java.awt.event.ActionListener;




public class FrameManager {
	
	private MainWindow mainWindow;
	private MyCourseWindow myCourseWindow;
	private SearchWindow searchWindow;
	private LoginWindow loginWindow;
	
	public FrameManager() {
		mainWindow = new MainWindow();
		myCourseWindow = new MyCourseWindow();
		searchWindow = new SearchWindow();
		loginWindow = new LoginWindow();
	}
	
	public void start() {
		loginWindow.setVisible(true);
	}

	public void displaySearchWindow() {
		searchWindow.setVisible(true);
	}

	public String [] getCourseFromSearch() {
		return searchWindow.getCourse();
	}


	public void sendMessagetoSearchWindow(String string) {
		searchWindow.displayMessage(string);	
	}	
	
	public void closeSearchWindow() {
		searchWindow.setVisible(false);
		searchWindow.dispose();
	}

	public void closeMainWindow() {
		mainWindow.setVisible(false);
		mainWindow.dispose();
		
	}

	public void displayMainWindow() {
		mainWindow.setVisible(true);
	}

	public void addListenersToSearchWindow(ActionListener searchSearchListener,
			ActionListener searchViewAllListener, ActionListener searchBackListener, ActionListener
			quitListener) {
		searchWindow.addSearchListener(searchSearchListener);
		searchWindow.addViewListener(searchViewAllListener);
		searchWindow.addBackListener(searchBackListener);
		searchWindow.addQuitListener(quitListener);
	}

	public void displayMyCoursesWindow() {
		myCourseWindow.setVisible(true);
	}

	public String[] getCourseFromMyCourse() {
		return myCourseWindow.getCourse();
	}

	public void addListenersToMyCourseWindow(ActionListener myCourseAddCourseListener,
			ActionListener myCourseDropCourseListener, ActionListener myCourseReturnListener,
			ActionListener quitListener) {
		myCourseWindow.addAddCourseListener(myCourseAddCourseListener);
		myCourseWindow.addDropCourseListener(myCourseDropCourseListener);
		myCourseWindow.addReturnListener(myCourseReturnListener);
		myCourseWindow.addQuitListener(quitListener);
	}

	public void closeMyCourseWindow() {
		myCourseWindow.setVisible(false);
		myCourseWindow.dispose();
	}

	public void addListenersToMainMenu(ActionListener mainSearchCoursesListener,
			ActionListener mainMyCoursesListener, ActionListener quitListener) {
		mainWindow.addCatalogueListener(mainSearchCoursesListener);
		mainWindow.addMyCoursesListener(mainMyCoursesListener);
		mainWindow.addQuitListener(quitListener);
	}

	public String getName() {
		return loginWindow.getName();
	}

	public String getID() {
		return loginWindow.getID();
	}

	public void addListenersToLogin(ActionListener loginLoginListener) {
		loginWindow.addLoginListener(loginLoginListener);
	}

	public void mainSetStudentInfo(String name, String id) {
		mainWindow.setStudentInfo(name, id);
	}

	public void searchSetStudentInfo(String name, String id) {
		searchWindow.setStudentInfo(name, id);
	}

	public void myCoursesSetStudentInfo(String name, String id) {
		myCourseWindow.setStudentInfo(name,id);
	}

	public void closeLoginWindow() {
		loginWindow.setVisible(false);
		loginWindow.dispose();
	}

	public void setAllCourses(String string) {
		searchWindow.viewAllCourses(string);
	}

	public void sendMessagetoMyCourseWindow(String string) {
		myCourseWindow.displayMessage(string);
	}

	public String[] getCourseToRemoveFromMyCourse() {
		return myCourseWindow.getCourseToRemove();
	}

	public void setAllStudentCourses(String string) {
		myCourseWindow.setCourses(string);
		
	}

	public void sendMessagetoMainWindow(String communicate) {
		mainWindow.sendMessage(communicate);
		
	}

	

}
