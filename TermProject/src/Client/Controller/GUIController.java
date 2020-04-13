package Client.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Client.Model.Student;
import Client.View.FrameManager;

public class GUIController {
	private FrameManager frameManager;
	private CommunicationController comController;
	private Student student;
	
	public GUIController (FrameManager frameManager, CommunicationController comController) {
		this.frameManager=frameManager;
		this.comController=comController;
	}
	
	//listener for "Search Courses" button in main
	public class mainSearchCoursesListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			frameManager.closeMainWindow();
			frameManager.displaySearchWindow();
			frameManager.addListenersToSearchWindow(new searchSearchListener(), new searchViewAllListener(), new searchBackListener());
		}

	}
	
	//listener for "My Courses" button in main 
	public class mainMyCoursesListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			frameManager.closeMainWindow();
			frameManager.displayMyCoursesWindow();
			frameManager.addListenersToMyCourseWindow(new myCourseAddCourseListener(), 
					new myCourseDropCourseListener(), new myCourseReturnListener());
			
		}
	}
	
	//listener for "Add Course" button in My Course window
	public class myCourseAddCourseListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			/*
			 * String [] course = frameManager.getCourseFromMyCourse(); String courseName =
			 * course[0]; int courseNum = Integer.parseInt(course[1]); if
			 * (frameManager.checkCourse(courseName, courseNum) && //checkPreq(student,
			 * courseName, courseNum))
			 */			
		}
	}
	
	//listener for "Drop Course" button in My Course window
	public class myCourseDropCourseListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	//listener for "Return to Main Menu" button in My Course window
	public class myCourseReturnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			frameManager.closeMyCourseWindow();
			frameManager.displayMainWindow();
		}
	}
	
	//listener for "Search" button in search window
	public class searchSearchListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String [] course = frameManager.getCourseFromSearch();
			String courseName = course[0];
			int courseNum = Integer.parseInt(course[1]);
			if (frameManager.checkCourse(courseName, courseNum)) {
				frameManager.sendMessagetoSearchWindow("Course Found! Course Information is as follows:\n"+course[0]+" "+course[1]+"\n");
						//more information about course??
			}
			else {
				frameManager.sendMessagetoSearchWindow("Sorry, course not found!");
			}
		}
	}
	
	//listener for "View All" button in search window
	public class searchViewAllListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//display all courses in info text area
			
		}
	}
	
	//listener for "Return to Main Menu" button in search window
	public class searchBackListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			frameManager.closeSearchWindow();
			frameManager.displayMainWindow();
		}
		
	}
}
