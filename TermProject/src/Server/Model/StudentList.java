package Server.Model;

import java.util.ArrayList;
//
public class StudentList {

	private ArrayList<Student> studentList;
	
	public StudentList() {
		loadFromDataBase();
	}
	
	private void loadFromDataBase() {
		DBManager db = new DBManager();
		setStudentList(db.readStudentFromDataBase());
		
	}
	
	public void addStudent(String name, int id) {
		studentList.add(new Student(name, id));
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
	
	public String registerStudent(int id, CourseOffering co) {
		
		Student tar = findStudent(id);
		
		if(tar != null) {

			if(tar.checkCourse(co)) {
				return "Sorry! You are already registered in this course.";
			}else if(tar.getNumberOfRegistrations()>5) {
				return "Sorry! You are only allowed to register for a maximum of 6 courses.";
			}else {
				Registration r = new Registration();
				r.completeRegistration(tar, co);
				return "Registration successful!";
			}

			
		}
		return null;
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
