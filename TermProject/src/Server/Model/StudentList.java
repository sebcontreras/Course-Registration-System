package Server.Model;

import java.util.ArrayList;

public class StudentList {

	private ArrayList<Student> studentList;
	
	public StudentList() {
		loadFromDataBase();
	}
	
	private void loadFromDataBase() {
		DBManager db = new DBManager();
		setStudentList(db.readStudentFromDataBase());
		
	}
	
	public void setStudentList(ArrayList <Student> studentList) {
		this.studentList = studentList;
	}
	
	public Student findStudent(int id) {
		
		for(Student x : studentList) {
			if(x.getStudentId() == id) {
				return x;
			}
		}
		return null;
	}
	
	public void registerStudent(int id, CourseOffering co) {
		
		Student tar = findStudent(id);
		
		if(tar != null) {
			
			Registration r = new Registration(tar, co);
			
		}
	}
	
	public boolean removeCourseReg(int id, Course course) {
		
		Student target = findStudent(id);
		
		if(target != null) {
			if(target.removeCourse(course)) {
				return true;
			}
		}
		return false;
	}
	
	public String viewStudentCourse(int id) {
		
		Student target = findStudent(id);
		String str = "";
		if(target != null) {
			str += target.regListToString();
		}
		
		return str;
	}
	
	public void listStudents() {
		System.out.println("The students are:");
		System.out.println("---------------------------------------");
		for(Student i : studentList) {
			System.out.println(i);
		}
		System.out.println("---------------------------------------");
		}
}
