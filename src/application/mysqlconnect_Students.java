package application;




import java.awt.List;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
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

import javax.imageio.spi.RegisterableService;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import static java.util.Collections.list;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import static java.util.Collections.list;




public class mysqlconnect_Students extends Login_PageController {
	static int id_student;
	static int course_id;
	 
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
     
        } catch (SQLException ex) {
            Logger.getLogger(mysqlconnect_Students.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Not Connected... !!!!");
        }
            
            return  connection;
        }
        
        
        
         public static ObservableList<regestier_Class_model> getDatausers(){
            
            
            Connection conn = connectDb();
            ObservableList<regestier_Class_model> list =  FXCollections.observableArrayList();
            try {
                java.sql.PreparedStatement ps = conn.prepareStatement("select id,course_name,course_hours,course_code from fci_registration.courses where deleted = FALSE");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {                    
                    list.add(new regestier_Class_model(rs.getInt("id"),rs.getString("course_name"),rs.getInt("course_hours"),rs.getString("course_code")));
                    
                    
                }
            } catch (Exception e) {
            }
            
            return list;
        }
         
         
         public static ObservableList<regestier_Class_model> getDatausers_2() {
        	 
        	 Connection conn = connectDb();
        	 ObservableList<Data> list = FXCollections.observableArrayList();

        	 ObservableList<regestier_Class_model> list_2 = FXCollections.observableArrayList();
        	 try {
        		 String email = who2;
        		 String sql_2 = "SELECT id FROM fci_registration.students WHERE email ='"+email+"'";
                 PreparedStatement stmt1 = conn.prepareStatement(sql_2);
                  ResultSet rs_2 = stmt1.executeQuery();
                   while(rs_2.next()) {
                    id_student= rs_2.getInt("id") ;
                   System.out.println("id_student gggg = "+id_student);  
                   System.out.println("email gggg = "+email);
                   }
                   String sql_4 = "SELECT course_id FROM fci_registration.registration WHERE student_id ='"+id_student+"'";
                   PreparedStatement stmt_4 = conn.prepareStatement(sql_4);
           
                    // System.out.println("id_student2");
                     
                    ResultSet rs_4 = stmt_4.executeQuery();
                     while(rs_4.next()) {
                      course_id= rs_4.getInt("course_id") ;
                     System.out.println("course_id"+course_id);                
        		 java.sql.PreparedStatement ps = conn.prepareStatement("select id,course_name,course_hours,course_code from fci_registration.courses where id= '"+course_id+"'");
        		 ResultSet rs_5 =ps.executeQuery();
        		 while(rs_5.next()) {
        			 list_2.add(new regestier_Class_model(rs_5.getInt("id"),rs_5.getString("course_name"),rs_5.getInt("course_hours"),rs_5.getString("course_code")));
        		 }
                     }
                     
        	 }catch(Exception e) {
        		 e.printStackTrace();
     
        	 }

			return list_2;
        	 		}
         
         /////////////////////////////////////////////
         public static ObservableList<Data> getDatausers3(){

             Connection conn = connectDb();
             ObservableList<Data> list =  FXCollections.observableArrayList();
             try {
             	database con = new database();
             	Connection con1;
             	con1 = con.fileConnection();
             	 ResultSet rs;
            	  PreparedStatement pst;
            	  String email = who2;
            	  String sql_2 = "SELECT id FROM fci_registration.students WHERE email ='"+email+"'";
                  PreparedStatement stmt1 = conn.prepareStatement(sql_2);
                   ResultSet rs_2 = stmt1.executeQuery();
                    while(rs_2.next()) {
                     id_student= rs_2.getInt("id") ;
                    System.out.println("id_student = "+id_student);                  
                    }

                    String sql_4 = "SELECT course_id FROM fci_registration.registration WHERE student_id ='"+id_student+"'";
                    PreparedStatement stmt_4 = conn.prepareStatement(sql_4);
            
                     // System.out.println("id_student2");
                      
                     ResultSet rs_4 = stmt_4.executeQuery();
                      while(rs_4.next()) {
                       course_id= rs_4.getInt("course_id") ;
                      System.out.println("course_id"+course_id);                
         		 java.sql.PreparedStatement ps = conn.prepareStatement("select course_name from fci_registration.courses where id= '"+course_id+"'");
         		 ResultSet rs_6 =ps.executeQuery();
         		 while(rs_6.next()) {
         			 list.add(new Data(rs_6.getString("course_name")));
         		 }
                      }
                    
                    
               
                 
             } catch (Exception e) {
             }
             
             return list;
         }
         
}