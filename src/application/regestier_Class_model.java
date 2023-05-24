///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package application;
//
///**
// *
// * @author hp
// */
//public class regestier_Class_model {
//    
//	int id_course ;
//	int course_hours;
//	String course_name, course_pre_requiest,course_code;
//        public regestier_Class_model(int id_course, String course_name, int course_hours, String course_pre_requiest,String course_code) {
//		this.id_course = id_course;
//		this.course_name = course_name;
//		this.course_hours = course_hours;
//		this.course_pre_requiest = course_pre_requiest;
//		this.course_code =course_code;
//	}
//
//    public String getCourse_code() {
//			return course_code;
//		}
//
//		public void setCourse_code(String course_code) {
//			this.course_code = course_code;
//		}
//
//	public int getId_course() {
//        return id_course;
//    }
//
//    public int getCourse_hours() {
//        return course_hours;
//    }
//
//    public String getCourse_name() {
//        return course_name;
//    }
//
//    public String getCourse_pre_requiest() {
//        return course_pre_requiest;
//    }
//
//    public void setId_course(int id_course) {
//        this.id_course = id_course;
//    }
//
//    public void setCourse_hours(int course_hours) {
//        this.course_hours = course_hours;
//    }
//
//    public void setCourse_name(String course_name) {
//        this.course_name = course_name;
//    }
//
//    public void setCourse_pre_requiest(String course_pre_requiest) {
//        this.course_pre_requiest = course_pre_requiest;
//    }
//    
//}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 *
 * @author hp
 */
public class regestier_Class_model {
    
	int id_course ;
	int course_hours;
	String course_name , course_code;
        public regestier_Class_model(int id_course, String course_name, int course_hours, String course_code) {
		this.id_course = id_course;
		this.course_name = course_name;
		this.course_hours = course_hours;
		//this.course_pre_requiest = course_pre_requiest;
		this.course_code =course_code;
	}

    public String getCourse_code() {
			return course_code;
		}

		public void setCourse_code(String course_code) {
			this.course_code = course_code;
		}

	public int getId_course() {
        return id_course;
    }

    public int getCourse_hours() {
        return course_hours;
    }

    public String getCourse_name() {
        return course_name;
    }

//    public String getCourse_pre_requiest() {
//        return course_pre_requiest;
//    }

    public void setId_course(int id_course) {
        this.id_course = id_course;
    }

    public void setCourse_hours(int course_hours) {
        this.course_hours = course_hours;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

//    public void setCourse_pre_requiest(String course_pre_requiest) {
//        this.course_pre_requiest = course_pre_requiest;
//    }
    
}

