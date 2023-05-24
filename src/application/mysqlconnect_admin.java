/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import java.sql.DriverManager;





public class mysqlconnect_admin {
    
    private static String HOST = "127.0.0.1";
        private static int PORT = 3306;
        private static String DB_NAME = "fci_registration";
        private static String USERNAME = "root";
        private static String PASSWORD = "123456";
        private static Connection connection ;
        
        // connect database 
        public static Connection connectDb (){
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST,PORT,DB_NAME),USERNAME,PASSWORD);
//            JOptionPane.showMessageDialog(null, "Connected  ..........");
            System.out.println("success");
        } catch (SQLException ex) {
            Logger.getLogger(mysqlconnect_admin.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Not Connected... !!!!");
        }
            
            return  connection;
        }
        
        
        public static ObservableList<courses> getmodifyData(){
            
            
            Connection conn = connectDb();
            ObservableList<courses> list =  FXCollections.observableArrayList();
            try {
                java.sql.PreparedStatement ps = conn.prepareStatement("select course_name, course_code, course_hours, course_doctor_id, department_id, num_reg_students from fci_registration.courses where deleted = FALSE ");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {                    
                    list.add(new courses(rs.getString("course_name"),rs.getString("course_code"),rs.getInt("course_hours"),rs.getInt("course_doctor_id"),rs.getInt("department_id"),rs.getInt("num_reg_students")));
                    
                    
                }
            } catch (Exception e) {
            	e.printStackTrace();
            }
            
            return list;
        }
public static ObservableList<main_department> getdepartmentDatauser(){
            
            
            Connection conn = connectDb();
            ObservableList<main_department> list =  FXCollections.observableArrayList();
            try {
                java.sql.PreparedStatement ps = conn.prepareStatement("select id, department_code, department_name from fci_registration.department ");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {                    
                    list.add(new main_department(rs.getString("department_code"),rs.getString("department_name"),rs.getInt("id")));
                    
                    
                }
            } catch (Exception e) {
            	e.printStackTrace();
            }
            
            return list;
        }
public static ObservableList<main_courses> getcoursesData_Current(){
    
    
    Connection conn = connectDb();
    ObservableList<main_courses> list =  FXCollections.observableArrayList();
    try {
        java.sql.PreparedStatement ps = conn.prepareStatement("select course_code, course_name,course_hours, course_doctor_id, department_id, num_reg_students from fci_registration.courses where deleted = FALSE  ");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {                    
            list.add(new main_courses(rs.getString("course_name"),rs.getString("course_code"),rs.getInt("course_hours"),rs.getInt("course_doctor_id"),rs.getInt("department_id"),rs.getInt("num_reg_students")));
            
            
        }
    } catch (Exception e) {
    	e.printStackTrace();
    }
    
    return list;
} 

public static ObservableList<main_courses> getcoursesData_Old(){
    
    
    Connection conn = connectDb();
    ObservableList<main_courses> list =  FXCollections.observableArrayList();
    try {
        java.sql.PreparedStatement ps = conn.prepareStatement("select course_code, course_name,course_hours, course_doctor_id, department_id, num_reg_students from fci_registration.courses where deleted = TRUE  ");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {                    
            list.add(new main_courses(rs.getString("course_code"),rs.getString("course_name"),rs.getInt("course_hours"),rs.getInt("course_doctor_id"),rs.getInt("department_id"),rs.getInt("num_reg_students")));
            
            
        }
    } catch (Exception e) {
    	e.printStackTrace();
    }
    
    return list;
}
public static ObservableList<courses> getaddcourses(){
    
    
    Connection conn = connectDb();
    ObservableList<courses> list =  FXCollections.observableArrayList();
    try {
        java.sql.PreparedStatement pst1 = conn.prepareStatement("select course_name, course_code from fci_registration.courses");
        
		ResultSet rs = pst1.executeQuery();
        while (rs.next()) {                    
            list.add(new courses(rs.getString("course_name"),rs.getString("course_code")));
                    }
    } catch (Exception e) {
    	e.printStackTrace();
    }
    
    return list;
}
        

  
}