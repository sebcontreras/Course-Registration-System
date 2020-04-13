package Server.Model;

import java.util.ArrayList;

public class DBManager {
	
	ArrayList <Course> courseList;
	ArrayList <Student> studentList;

	public DBManager () {
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
	}
	
	public ArrayList<Course> readCourseFromDataBase(){
		//read from database here
		addOfferings();
	}
	
	public ArrayList<Student> readStudentFromDataBase(){
		//read from database here
	}
	
	private void addOfferings() {
		int index = 0;
		for(Course i : courseList) {
			
			int numberOfOfferings = setSec(index); 

			for(int sec = 1; sec <= numberOfOfferings; sec++) {
				
				int capacity = setCapacity(index, sec);
				CourseOffering off = new CourseOffering(sec, capacity);
				i.addOffering(off);
			}
			index++;
		}
	}
	
	private int setSec(int index) {
		int numberOfOfferings = 1;
		if(index % 3 == 0)
			numberOfOfferings = 2;
		else if(index % 4 == 0)
			numberOfOfferings = 3;
		else if(index % 5 == 0)
			numberOfOfferings = 4;
		else if(index % 7 == 0)
			numberOfOfferings = 5;
		return numberOfOfferings;
	}
	
	private int setCapacity(int index, int sec) {
		
		int capacity = 100;
		if(index % 3 == 0 && sec == 2)
			capacity = 80;
		else if(index % 3 == 0)
			capacity = 50;
		else if(index % 4 == 0 && sec == 3)
			capacity = 120;
		else if(index % 4 == 0)
			capacity = 200;
		else if(index % 5 == 0 && sec == 2)
			capacity = 150;
		else if(index % 5 == 0 && sec == 1)
			capacity = 180;
		else if(index % 5 == 0 && sec == 4)
			capacity = 30;
		else if(index % 5 == 0)
			capacity = 70;
		else if(index % 7 == 0 && (sec == 1 || sec == 2))
			capacity = 300;
		else if(index % 7 == 0 && (sec == 3 || sec == 4))
			capacity = 250;
		
		return capacity;
	}


}