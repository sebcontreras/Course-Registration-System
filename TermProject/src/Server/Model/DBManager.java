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
		String query = "SELECT * FROM termproject.courses where name= ? and number= ?";
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
		String query = "SELECT * FROM termproject.student where id= ? and first= ? and last= ?";
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
	
	/**public ArrayList<Course> readCourseFromDataBase() {
		courseList.add(new Course ("ENGG", 200));
		courseList.add(new Course ("MATH", 275));
		courseList.add(new Course ("MATH", 211));
		courseList.add(new Course ("ENGG", 233));
		courseList.add(new Course ("GRST", 211));
		courseList.add(new Course ("GRST", 209));
		courseList.add(new Course ("MATH", 271));
		courseList.add(new Course ("MATH", 277));
		courseList.add(new Course ("ENGG", 201));
		courseList.add(new Course ("ENGG", 225));
		courseList.add(new Course ("ENGG", 319));
		courseList.add(new Course ("ENGG", 209));
		courseList.add(new Course ("COMS", 311));
		courseList.add(new Course ("MATH", 375));
		courseList.add(new Course ("PHYS", 269));
		courseList.add(new Course ("PHYS", 369));
		
		addOfferings();
		
		return courseList;
	}
	*/
	/**
	public ArrayList<Student> readStudentFromDataBase() {
		studentList.add(new Student("Mikey", 100));
		studentList.add(new Student("Dave", 101));
		studentList.add(new Student("Tom", 102));
		studentList.add(new Student("Sam", 103));
		studentList.add(new Student("Kate", 104));
		studentList.add(new Student("Emma", 105));
		studentList.add(new Student("Tim", 106));
		studentList.add(new Student("Cris", 107));
		studentList.add(new Student("Vladimir", 108));
		studentList.add(new Student("Vic", 109));
		studentList.add(new Student("Aby", 110));
		studentList.add(new Student("Des", 111));
		studentList.add(new Student("Hunter", 112));
		studentList.add(new Student("Ben", 113));
		studentList.add(new Student("Jon", 114));
		studentList.add(new Student("Ee", 115));
		studentList.add(new Student("Hannah", 116));
		studentList.add(new Student("Julia", 117));
		studentList.add(new Student("Perez", 118));
		studentList.add(new Student("Arthur", 119));
		studentList.add(new Student("George", 120));
		studentList.add(new Student("Seb", 121));
		studentList.add(new Student("Adi", 122));
		studentList.add(new Student("Clair", 123));
		studentList.add(new Student("Bob", 124));
		studentList.add(new Student("Don", 125));
		studentList.add(new Student("Ron", 126));
		studentList.add(new Student("Harry", 127));
		studentList.add(new Student("Jake", 128));
		studentList.add(new Student("Ryan", 129));
		studentList.add(new Student("Keven", 130));
		studentList.add(new Student("Oscar", 131));
		studentList.add(new Student("Pam", 132));
		studentList.add(new Student("Angela", 133));
		studentList.add(new Student("Creed", 134));
		studentList.add(new Student("Autin", 135));
		studentList.add(new Student("Ethan", 136));
		studentList.add(new Student("Noah", 137));
		studentList.add(new Student("Andy", 138));
		studentList.add(new Student("Jadon", 139));
		studentList.add(new Student("Stanley", 140));
		
		
		return studentList;
	}
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