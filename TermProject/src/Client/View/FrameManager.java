package Client.View;
//
import java.awt.event.ActionListener;

import Client.Model.Student;



public class FrameManager {
	
	private MainWindow mainWindow;
	private MyCourseWindow myCourseWindow;
	private SearchWindow searchWindow;
	private LoginWindow loginWindow;
	
	public FrameManager(MainWindow mainWindow, MyCourseWindow myCourseWindow, SearchWindow searchWindow, LoginWindow loginWindow) {
		this.mainWindow=mainWindow;
		this.myCourseWindow=myCourseWindow;
		this.searchWindow=searchWindow;
		this.loginWindow=loginWindow;
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
			ActionListener searchViewAllListener, ActionListener searchBackListener) {
		searchWindow.addSearchListener(searchSearchListener);
		searchWindow.addViewListener(searchViewAllListener);
		searchWindow.addBackListener(searchBackListener);
	}

	public void displayMyCoursesWindow() {
		myCourseWindow.setVisible(true);
	}

	public String[] getCourseFromMyCourse() {
		return myCourseWindow.getCourse();
	}

	public void addListenersToMyCourseWindow(ActionListener myCourseAddCourseListener,
			ActionListener myCourseDropCourseListener, ActionListener myCourseReturnListener) {
		myCourseWindow.addAddCourseListener(myCourseAddCourseListener);
		myCourseWindow.addDropCourseListener(myCourseDropCourseListener);
		myCourseWindow.addReturnListener(myCourseReturnListener);
	}

	public void closeMyCourseWindow() {
		myCourseWindow.setVisible(false);
		myCourseWindow.dispose();
	}

	public void addListenersToMainMenu(ActionListener mainSearchCoursesListener,
			ActionListener mainMyCoursesListener) {
		mainWindow.addCatalogueListener(mainSearchCoursesListener);
		mainWindow.addMyCoursesListener(mainMyCoursesListener);
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

	

}
