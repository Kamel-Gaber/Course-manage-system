package application;

public class Students_Table {
	String first_name,last_name,email;
	int accadimic_number;
	Double gpa;
        Double degree;
        
	public Students_Table(String first_name,String last_name,String email,int accadimic_number ,Double gpa,Double degree) {
		this.first_name=first_name;
		this.last_name = last_name;
		this.email=email;
		this.accadimic_number=accadimic_number;
		this.gpa=gpa;
		this.degree = degree;
	}
   public Students_Table(Double degree){
            this.degree=degree;
    }
	public String getFirst_name() {
		return first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public String getEmail() {
		return email;
	}
	public int getAccadimic_number() {
		return accadimic_number;
	}
	public Double getGpa() {
		return gpa;
	}
        public Double getDegree() {
		return degree;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAccadimic_number(int accadimic_number) {
		this.accadimic_number = accadimic_number;
	}
	public void setGpa(Double gpa) {
		this.gpa = gpa;
	}
        public void setDegree(Double degree) {
		this.degree = degree;
	}
	int student_id;
	public Students_Table(int student_id ) {
		this.student_id=student_id;
		
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	

}
