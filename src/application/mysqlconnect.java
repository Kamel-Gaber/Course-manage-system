
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




public class mysqlconnect extends Login_PageController {
	static int doc_id=0;
	static int std_id=0;
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
            System.out.println("Success");
        } catch (SQLException ex) {
            Logger.getLogger(mysqlconnect.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, "Not Connected... !!!!");
            System.out.println("No Success");
        }
            
            return  connection;
        }
        
        
        public static ObservableList<Data_courses> getDatausers(){
            Connection conn = connectDb();
            ObservableList<Data_courses> list =  FXCollections.observableArrayList();
            try {
            	database con = new database();
            	Connection con1;
            	con1 = con.fileConnection();
            	 ResultSet rs;
           	  PreparedStatement pst;
            	String sql3 = "SELECT  doctor_id FROM fci_registration.doctors where email = '"+who+"' ";
       		 pst = con1.prepareStatement(sql3);
       	        rs = pst.executeQuery(sql3);
       		      while(rs.next()) {
       		    	  String n = rs.getString(1);
       		    	 doc_id  = Integer.parseInt(n);
                java.sql.PreparedStatement ps = conn.prepareStatement("select course_name from fci_registration.courses where course_doctor_id = '"+doc_id+"' ");
                 rs = ps.executeQuery();
                while (rs.next()) {                    
                    list.add(new Data_courses(rs.getString("Course_Name")));
                }
                }
            } catch (Exception e) {
            }
            
            return list;
        }
        ////////////////////////////////////////
      public static ObservableList<Data> getDatausers2(){

            Connection conn = connectDb();
            ObservableList<Data> list =  FXCollections.observableArrayList();
            try {
            	database con = new database();
            	Connection con1;
            	con1 = con.fileConnection();
            	 ResultSet rs;
           	  PreparedStatement pst;
            	String sql3 = "SELECT  id FROM fci_registration.students where email = '"+who2+"' ";
       		 pst = con1.prepareStatement(sql3);
       	        rs = pst.executeQuery(sql3);
       		      while(rs.next()) {
       		    	  String n = rs.getString(1);
       		    	 std_id  = Integer.parseInt(n);
                java.sql.PreparedStatement ps = conn.prepareStatement("select course_name from fci_registration.courses where course_doctor_id = '"+std_id+"' ");
                 rs = ps.executeQuery();
                while (rs.next()) {                    
                    list.add(new Data(rs.getString("course_name")));
                }
                }
            } catch (Exception e) {
            }
            
            return list;
        }
        
        
        ////////////////////////////////////////
    public static ObservableList<regestier_Class_model> getDataRegister_students(){
            
            Connection conn = connectDb();
            ObservableList<regestier_Class_model> list =  FXCollections.observableArrayList();
            try {
                java.sql.PreparedStatement ps = conn.prepareStatement("select id,course_name,course_hours,course_code from fci_registration.courses");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {                    
                    list.add(new regestier_Class_model(rs.getInt("id"),rs.getString("course_name"),rs.getInt("course_hours"),rs.getString("course_code")));
                }
            } catch (Exception e) {
            }
            
            return list;
        }
        
       
        

    
}