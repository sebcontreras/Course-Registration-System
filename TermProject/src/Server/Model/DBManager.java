package Server.Model;

import java.sql.*;
import java.util.ArrayList;
//

public class DBManager implements IDBCredentials{
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private ArrayList <Course> courseList;
	private ArrayList <Student> studentList;

	public DBManager () {
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
		initializeConnection();
	}
	
	//needs to be called
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
	
	public void close() {
		try {
			stmt.close();
			rs.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
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