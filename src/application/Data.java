/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

public class Data  {

    int  doc_id , dept_id;
    
	int course_hour;
	int course_hours;
	String first_name;
	String last_name;
	String email;
	String course_name, course_code , DOC_N , DEPT_N;
        public Data(String course_name,String course_code,  int course_hour,int doc_id , int dept_id , String DOC_N , String DEPT_N  ) {
		
		this.course_name = course_name;
                this.course_code = course_code;
		this.course_hour = course_hour;
                this.doc_id = doc_id;
                this.dept_id = dept_id;
                this.DOC_N = DOC_N ;
                this.DEPT_N = DEPT_N ;
		
	}
    	public int getCourse_hours() {
    		return course_hours;
    	}

    	public void setCourse_hours(int course_hours) {
    		this.course_hours = course_hours;
    	}
    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public int getCourse_hour() {
        return course_hour;
    }

    public void setCourse_hour(int course_hour) {
        this.course_hour = course_hour;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getDOC_N() {
        return DOC_N;
    }

    public void setDOC_N(String DOC_N) {
        this.DOC_N = DOC_N;
    }

    public String getDEPT_N() {
        return DEPT_N;
    }

    public void setDEPT_N(String DEPT_N) {
        this.DEPT_N = DEPT_N;
    }
public Data(String course_name,String course_code,  int course_hours) {
	this.course_name = course_name;
    this.course_code = course_code;
this.course_hours = course_hours;

}
public Data(String course_name) {
	this.course_name = course_name;
}
public Data(String first_name,String last_name,String email) {
	this.first_name = first_name;
	this.last_name=last_name;
	this.email=email;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getFirst_name() {
	return first_name;
}
public String getLast_name() {
	return last_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}
  

}
