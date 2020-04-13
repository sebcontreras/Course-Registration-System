package Client.View;
//
import java.awt.event.ActionListener;

import Client.Controller.GUIController.myCourseAddCourseListener;
import Client.Controller.GUIController.myCourseDropCourseListener;
import Client.Controller.GUIController.myCourseReturnListener;
import Client.Model.Student;



public class FrameManager {
	
	private MainWindow mainWindow;
	private MyCourseWindow myCourseWindow;
	private SearchWindow searchWindow;
	
	public FrameManager(MainWindow mainWindow, MyCourseWindow myCourseWindow, SearchWindow searchWindow) {
		this.mainWindow=mainWindow;
		this.myCourseWindow=myCourseWindow;
		this.searchWindow=searchWindow;
	}

	public void displaySearchWindow() {
		mainWindow.setVisible(true);
	}

	public String [] getCourseFromSearch() {
		return searchWindow.getCourse();
	}

		//MUST BE COMPLETED
		//checks course in database? returns true if actual course that can be enrolled
		public boolean checkCourse(String courseName, int courseNum) {
			return false;
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

	

}
