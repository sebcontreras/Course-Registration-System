package Server.Model;

import java.sql.*;
import java.util.ArrayList;
//
/**
 * The purpose of this class is to access the database and retrieve information
 * @author Michael Vassilev 30068475, Vic Phan 30061885, Sebastian Contreras 30062418
 *
 */
public class DBManager implements IDBCredentials{
	/*
	 * the connection to the database
	 */
	private Connection conn;
	/*
	 * the query sent to the database
	 */
	private Statement stmt;
	/*
	 * the output of the database
	 */
	private ResultSet rs;
	/*
	 * the list of courses acquired from the database
	 */
	private ArrayList <Course> courseList;
	/*
	 * the list of students acquired from the database
	 */
	private ArrayList <Student> studentList;
	/**
	 * constructs an instance of DBManager and initiates the connection 
	 * to the database
	 */
	public DBManager () {
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
		initializeConnection();
	}
	
	/**
	 * this method initiates the connection with the database
	 */
	public void initializeConnection() {
		try {
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		}catch(SQLException e) {
			System.out.println("Problem, not connected");
			e.printStackTrace();
		}
	}
	/**
	 * this method closes all connections with the database and closes the 
	 * objects used for those connections
	 */
	public void close() {
		try {
			stmt.close();
			rs.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * this method reads the courses from the database and slots them into the course list
	 * @return the course list
	 */
	public ArrayList<Course> readCourseFromDataBase() {
		String query = "SELECT * FROM COURSES";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery(query);
			while(rs.next()) {
				//int id = rs.getInt("id");
				String name = rs.getString("name");
				int num = rs.getInt("number");
				//should we have id?
				courseList.add(new Course(name, num));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if (pStat != null)
				try {
					pStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		addOfferings();
		
		return courseList;
	}
	/**
	 * this method reads the students from the database and slots them into the student list
	 * @return the student list
	 */
	public ArrayList<Student> readStudentFromDataBase() {
		String query = "SELECT * FROM STUDENT";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery(query);
			while(rs.next()) {
				int id = rs.getInt("id");
				String first = rs.getString("first");
				String last = rs.getString("last");
				//should we pass in the other lists
				studentList.add(new Student(first+" "+last, id));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if (pStat != null)
				try {
					pStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		
		return studentList;
	}
	/**
	 * this method adds course offerings to the courses
	 */
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
	/**
	 * this method sets the section numbers for the course offerings
	 * @param index the index in the course list
	 * @return the section numbers
	 */
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
	/**
	 * this method sets the capacity for course offerings
	 * @param index, the index in the course list
	 * @param sec, the section of a course
	 * @return the capacity of a course offering
	 */
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