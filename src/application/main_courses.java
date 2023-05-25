package application;

public class main_courses {
	String course_name,course_code;
	int course_hours,course_doctor,course_dept,number_std;
	public main_courses(String course_name,String course_code,int course_hours,int course_doctor,int course_dept,int number_std ) {
		this.course_name=course_name;
		this.course_code=course_code;
		this.course_dept=course_dept;
		this.course_doctor=course_doctor;
		this.course_hours=course_hours;
		this.number_std=number_std;
		
	}
	public String getCourse_name() {
		return course_name;
	}
	public String getCourse_code() {
		return course_code;
	}
	
	public int getCourse_hours() {
		return course_hours;
	}
	public int getCourse_doctor() {
		return course_doctor;
	}
	public int getCourse_dept() {
		return course_dept;
	}
	public int getNumber_std() {
		return number_std;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}
	
	public void setCourse_hours(int course_hours) {
		this.course_hours = course_hours;
	}
	public void setCourse_doctor(int course_doctor) {
		this.course_doctor = course_doctor;
	}
	public void setCourse_dept(int course_dept) {
		this.course_dept = course_dept;
	}
	public void setNumber_std(int number_std) {
		this.number_std = number_std;
	}
	

}
