package application;

public class main_department {
     String department_code,department_name;
     int id;
     
     public main_department(String department_code,String department_name , int id) {
    	 this.department_code=department_code;
 		this.department_name=department_name;
 		this.id = id;
     }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartment_code() {
		return department_code;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
   
}
