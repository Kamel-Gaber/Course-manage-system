///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package application;
//
//
//import static application.mysqlconnect_Students.connectDb;
//
//import java.io.IOException;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//import javax.swing.JOptionPane;
//
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.Stage;
//
//
///**
// *
// * @author hp
// */
//public class register_controller extends Login_PageController implements Initializable {
//	 String email = who2;
//	Alert alert = new Alert(AlertType.WARNING);
//	 static int id_student;
//	 static int course_id;
//	 static int course_i;
//    Connection conn = null; 
//    static int maximum = 6;
//    static int count;
//   ObservableList<regestier_Class_model> listM;
//   @FXML
//   private Stage primaryStage;
//   private Scene scene;
//   private Parent root;
//   int index = - 1;
//   ResultSet rs = null;
//   ResultSet rs2 = null;
//   PreparedStatement pst = null;
//   PreparedStatement pst2 = null;
//   @FXML
//   private Button add_btn;
//   
//   @FXML
//   private TextField code_text;
//
//   @FXML
//   private TextField id_text;
//
//   @FXML
//   private TextField coursename_text;
//
//   @FXML
//   private TextField coursehours_text;
//
//   @FXML
//   private TextField pre_text;
//
//   @FXML
//   private Button delete_btn;
//   @FXML
//   private Button back;
//   
//   @FXML
//   private TableColumn<regestier_Class_model, String> course_code2;
//   
//   @FXML
//   private TableColumn<regestier_Class_model, String> course_pre2;
//   
//   
//   @FXML
//   private TableColumn<regestier_Class_model, String> course_code1;
//
//    @FXML
//    private TableColumn<regestier_Class_model, String> Name_course1;
//
//    @FXML
//    private TableColumn<regestier_Class_model, Integer> course_hours1;
//
//    @FXML
//    private TableColumn<regestier_Class_model, Integer> course_hours2;
//
//    @FXML
//    private TableColumn<regestier_Class_model, String> course_name2;
//
//    @FXML
//    private TableColumn<regestier_Class_model, String> course_pre1;
//
//    @FXML
//    private TableColumn<regestier_Class_model, Integer> id_course1;
//
//    @FXML
//    private TableColumn<regestier_Class_model, Integer> id_course2;
//
//    @FXML
//    private TableView<regestier_Class_model> table_1;
//
//    @FXML
//    private TableView<regestier_Class_model> table_2;
//    
//    
//    
//
//    @FXML
//    void add(ActionEvent event) throws SQLException {
//    	  conn = mysqlconnect_Students.connectDb();
//          try {
//        	  
//              String sql_2 = "SELECT id FROM fci_registration.students WHERE email ='"+email+"'";
//              PreparedStatement stmt1 = conn.prepareStatement(sql_2);
//                System.out.println("id_student2");
//               ResultSet rs = stmt1.executeQuery();
//                while(rs.next()) {
//               System.out.println("id_student3");
//                 id_student= rs.getInt("id") ;
//                System.out.println(id_student);
//        	  
//             course_id = Integer.parseInt(id_text.getText());
//               String sql = "SELECT * FROM fci_registration.registration WHERE (student_id,course_id) = ('"+id_student+"','" + course_id + "')";
//               String query = "SELECT COUNT(*) FROM fci_registration.registration where student_id = '"+id_student+"' ";
//               pst = conn.prepareStatement(sql);
//               pst2 = conn.prepareStatement(query);
//               rs = pst.executeQuery();
//               rs2 = pst2.executeQuery();
//               while(rs2.next()) {
//            	    count = rs2.getInt(1);
//            	   System.out.println("count"+count);
//             if(count<=maximum) {
//              
//            	  
//               if (rs.next()) {
//            	   alert.setTitle("Wait");
//               		alert.setHeaderText(null);
//               		alert.setContentText("Alredy exiests");
//               		alert.showAndWait();
//            	   
//               } else {
//
//                     String sql_1 = "insert into fci_registration.registration (student_id,course_id) values ('"+id_student+"','"+course_id+"')";
//                     pst = conn.prepareStatement(sql_1);
//                     System.out.println(id_student);
//                     pst.executeUpdate();
//                     UpdateTable_2();
//                     
//               }
//             }else {
//            	alert.setTitle("Wait");
//            	alert.setHeaderText(null);
//            	alert.setContentText("You have reahed the Maximum course number");
//            	alert.showAndWait();
//             }
//                }
//                }
//                System.out.println("halaloyaaaaaa:"+course_id);
//                update_number_register(course_id);
//           } catch (Exception e) {
//        	   
//               e.printStackTrace();
//               System.out.println("okk");
//
//           }
//          }
//    @FXML
//    void delete(ActionEvent event) {
//    	  try {
//              conn = mysqlconnect_Students.connectDb();
//              
//              String sql_2 = "SELECT id FROM fci_registration.students WHERE email ='"+email+"'";
//                          
//              PreparedStatement stmt1 = conn.prepareStatement(sql_2);
//      
//                System.out.println("id_student2");
//                
//               ResultSet rs = stmt1.executeQuery();
//                while(rs.next()) {
//               System.out.println("id_student3");
//                 id_student= rs.getInt("id") ;
//                System.out.println(id_student);
//              
//              
//               course_i = Integer.parseInt(id_text.getText());
//              System.out.println("course_id"+course_i);
//              System.out.println("id_student "+id_student);
//              String sql = "DELETE FROM fci_registration.registration WHERE student_id ='"+id_student+"' and course_id = '"+course_i+"'"; 
//
//              pst = conn.prepareStatement(sql);
//              pst.executeUpdate();
//              UpdateTable_2();
//                }
//                update_number_register(course_i);
//    	  } catch (Exception e) {
//              JOptionPane.showMessageDialog(null, e);
//              System.out.println("okk2");
//              
//          }
//
//      }
//  
//    
//
//  public void update_number_register(int id) throws SQLException {
//	  String query = "SELECT COUNT(*) FROM fci_registration.registration where course_id = '"+id+"' ";
//      pst2 = conn.prepareStatement(query);
//     
//      rs2 = pst2.executeQuery();
//      while(rs2.next()) {
//   	    count = rs2.getInt(1);
//      }
//      String sql = "update fci_registration.courses set num_reg_students = '"+count+"' where id = '"+id+"'";
//      pst = conn.prepareStatement(sql);
//      pst.executeUpdate();
//  }
//    
//    public void UpdateTable_1() {
//
//        id_course1.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, Integer>("id_course"));
//        Name_course1.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, String>("course_name"));
//        course_hours1.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, Integer>("course_hours"));
//        course_pre1.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, String>("course_pre_requiest"));
//        course_code1.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, String>("course_code"));
//       
//        listM = mysqlconnect_Students.getDatausers();
//        table_1.setItems(listM);
//    }
//    
//    public void UpdateTable_2() {
//
//        id_course2.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, Integer>("id_course"));
//        course_name2.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, String>("course_name"));
//        course_hours2.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, Integer>("course_hours"));
//        course_pre2.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, String>("course_pre_requiest"));
//        course_code2.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, String>("course_pre_requiest"));
//       
//        listM = mysqlconnect_Students.getDatausers_2();
//        table_2.setItems(listM);
//    }
//
//    @FXML
//    void getItem(MouseEvent event) {
//    	index = table_1.getSelectionModel().getSelectedIndex();
//        if (index <= -1) {
//            return;
//
//        }
//
//        coursename_text.setText(id_course1.getCellData(index).toString());
//        pre_text.setText(course_pre1.getCellData(index).toString());
//        coursehours_text.setText(course_hours1.getCellData(index).toString());
//        id_text.setText(id_course1.getCellData(index).toString());
//        code_text.setText(course_code1.getCellData(index).toString());
//
//    }
//    @FXML
//    void getitem2(MouseEvent event) {
//    	index = table_2.getSelectionModel().getSelectedIndex();
//        if (index <= -1) {
//            return;
//
//        }
//
//        coursename_text.setText(id_course2.getCellData(index).toString());
//        pre_text.setText(course_pre2.getCellData(index).toString());
//        coursehours_text.setText(course_hours2.getCellData(index).toString());
//        id_text.setText(id_course2.getCellData(index).toString());
//        code_text.setText(course_code2.getCellData(index).toString());
//    }
//
//    @FXML
//    void Back(ActionEvent event) throws IOException {
//    	root = FXMLLoader.load(getClass().getResource("st_Welcom.fxml"));
//      	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//      	scene = new Scene(root);
//      	primaryStage.setScene(scene);
//      	primaryStage.show();
//    }
//    
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        Connection conn = connectDb();
//        UpdateTable_1();
//        UpdateTable_2();
//        
//    }    
//    
//}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;


import static application.mysqlconnect_Students.connectDb;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 *
 * @author hp
 */
public class register_controller extends Login_PageController implements Initializable {
	 String email = who2;
	Alert alert = new Alert(AlertType.WARNING);
	 static int id_student;
	 static int course_id;
	 static int course_i;
	 static int course_pre_id;
    Connection conn = null; 
    static int maximum = 6;
    static int count;
    static int  course_degree;
    
   ObservableList<regestier_Class_model> listM;
   @FXML
   private Stage primaryStage;
   private Scene scene;
   private Parent root;
   int index = - 1;
   ResultSet rs = null;
   ResultSet rs2 = null;
   PreparedStatement pst = null;
   PreparedStatement pst2 = null;
   @FXML
   private Button add_btn;
   
   @FXML
   private TextField code_text;

   @FXML
   private TextField id_text;

   @FXML
   private TextField coursename_text;

   @FXML
   private TextField coursehours_text;

   @FXML
   private TextField pre_text;

   @FXML
   private Button delete_btn;
   @FXML
   private Button back;
   
   @FXML
   private TableColumn<regestier_Class_model, String> course_code2;
   
//   @FXML
//   private TableColumn<regestier_Class_model, String> course_pre2;
   
   
   @FXML
   private TableColumn<regestier_Class_model, String> course_code1;

    @FXML
    private TableColumn<regestier_Class_model, String> Name_course1;

    @FXML
    private TableColumn<regestier_Class_model, Integer> course_hours1;

    @FXML
    private TableColumn<regestier_Class_model, Integer> course_hours2;

    @FXML
    private TableColumn<regestier_Class_model, String> course_name2;

   // @FXML
   // private TableColumn<regestier_Class_model, String> course_pre1;

    @FXML
    private TableColumn<regestier_Class_model, Integer> id_course1;

    @FXML
    private TableColumn<regestier_Class_model, Integer> id_course2;

    @FXML
    private TableView<regestier_Class_model> table_1;

    @FXML
    private TableView<regestier_Class_model> table_2;
    
    
    
    
    
    
       int get_pre_id() throws SQLException {
    	   
    	   
       
    	   String sql_5 = "SELECT course_id_pre_request FROM fci_registration.requests WHERE course_id ='"+course_id+"'";
           PreparedStatement stmt5 = conn.prepareStatement(sql_5);
            ResultSet rs5 = stmt5.executeQuery();
            
             while(rs5.next()) {
            	 course_pre_id = rs5.getInt(1) ;
             System.out.println("course_pre ="+course_pre_id);}
             
    	   
    	   
    	   return course_pre_id;
       }
       int get_course_passed(int x) throws SQLException {
    	   
    	   
    	   String sql_6 = "SELECT passed FROM fci_registration.grades WHERE (course_id,student_id) =('"+x+"','"+id_student+"')";
           PreparedStatement stmt6 = conn.prepareStatement(sql_6);
            ResultSet rs6 = stmt6.executeQuery();
            
             while(rs6.next()) {
            	 course_degree = rs6.getInt(1) ;
             System.out.println("course_pass ="+course_degree);}
             return course_degree;
    	   
       }
    
    

    @FXML
    void add(ActionEvent event) throws SQLException {
    	int i=0;
    	
    	
    	

    	int[] myArray = null;
    	  conn = mysqlconnect_Students.connectDb();
    	 boolean flag = true;
          try {
        	  
              String sql_2 = "SELECT id FROM fci_registration.students WHERE email ='"+email+"'";
              PreparedStatement stmt1 = conn.prepareStatement(sql_2);
               ResultSet rs = stmt1.executeQuery();
                while(rs.next()) {
                 id_student = rs.getInt("id") ;
           
                
               
             course_id = Integer.parseInt(id_text.getText());
               String sql = "SELECT * FROM fci_registration.registration WHERE (student_id,course_id) = ('"+id_student+"','" + course_id + "')";
               String query = "SELECT COUNT(*) FROM fci_registration.registration where student_id = '"+id_student+"' ";
               pst = conn.prepareStatement(sql);
               pst2 = conn.prepareStatement(query);
               rs = pst.executeQuery();
               rs2 = pst2.executeQuery();
           
                 
              
               while(rs2.next()) {
            	    count = rs2.getInt(1);
            	    
            	    
            	    String sql_5 = "SELECT course_id_pre_request FROM fci_registration.requests WHERE course_id ='"+course_id+"'";
                  PreparedStatement stmt5 = conn.prepareStatement(sql_5);
                   ResultSet rs5 = stmt5.executeQuery();
                   
                   
                    while(rs5.next()) {
                    	
                    	
                   	 course_pre_id = rs5.getInt(1) ;
                    System.out.println("course_pre ="+course_pre_id);
                    
            	    
                    String sql_7 = "SELECT COUNT(*) FROM fci_registration.requests where course_id = '"+course_id+"' ";
                    pst = conn.prepareStatement(sql_7);
                    ResultSet rs_7 = pst.executeQuery();
                    while(rs_7.next()) {
                    	
                    	
                    	
                    int size = rs_7.getInt(1);
                    	myArray = new int[size];
                   
                    	System.out.println(size+"size");
                    }
            	    
                  int degree= get_course_passed(course_pre_id);
                  
                  myArray[i]=degree;
                  if(myArray[i]==1&&flag==true||myArray.length==0) {
      	    		flag=true;
      	    	System.out.println("arrrrrrrrrrrrrrrr="+myArray[i]);
      	    	System.out.println("iasdsadasdasdasdasdasdas"+i);
                  }else {
                	  flag=false;
      	    	
                		                      }
                   
                    
                    
              
            	    	
            	    	}
                    
                    
            	   
            	   
            	  
            	   
             if(count<=maximum) {

            	 System.out.println("flaaaaaaaaaaaaaaaaaaag"+flag);
            	  
               if (rs.next()) {
            	   alert.setTitle("Wait");
               		alert.setHeaderText(null);
               		alert.setContentText("Alredy exiests");
               		alert.showAndWait();
               		
            	   
               } else if(flag==true)  {

                     String sql_1 = "insert into fci_registration.registration (student_id,course_id) values ('"+id_student+"','"+course_id+"')";
                     pst = conn.prepareStatement(sql_1);
    
                     pst.executeUpdate();
                     UpdateTable_2();
                     
               }else {
            	   alert.setTitle("Wait");
               	alert.setHeaderText(null);
               	alert.setContentText("You cant regester this course");
               	alert.showAndWait();
               }
             }else {
            	alert.setTitle("Wait");
            	alert.setHeaderText(null);
            	alert.setContentText("You have reahed the Maximum course number");
            	alert.showAndWait();
             }
                }
                }
    
                update_number_register(course_id);
                 } catch (Exception e) {
        	   
               e.printStackTrace();
   

           }
          }
    @FXML
    void delete(ActionEvent event) {
    	  try {
              conn = mysqlconnect_Students.connectDb();
              
              String sql_2 = "SELECT id FROM fci_registration.students WHERE email ='"+email+"'";
                          
              PreparedStatement stmt1 = conn.prepareStatement(sql_2);
      

                
               ResultSet rs = stmt1.executeQuery();
                while(rs.next()) {
           
                 id_student= rs.getInt("id") ;
            
              
               course_i = Integer.parseInt(id_text.getText());
             
              String sql = "DELETE FROM fci_registration.registration WHERE student_id ='"+id_student+"' and course_id = '"+course_i+"'"; 

              pst = conn.prepareStatement(sql);
              pst.executeUpdate();
              UpdateTable_2();
                }
                update_number_register(course_i);
    	  } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
           ;
              
          }

      }
  
    

  public void update_number_register(int id) throws SQLException {
	  String query = "SELECT COUNT(*) FROM fci_registration.registration where course_id = '"+id+"' ";
      pst2 = conn.prepareStatement(query);
     
      rs2 = pst2.executeQuery();
      while(rs2.next()) {
   	    count = rs2.getInt(1);
      }
      String sql = "update fci_registration.courses set num_reg_students = '"+count+"' where id = '"+id+"'";
      pst = conn.prepareStatement(sql);
      pst.executeUpdate();
  }
    
    public void UpdateTable_1() {

        id_course1.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, Integer>("id_course"));
        Name_course1.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, String>("course_name"));
        course_hours1.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, Integer>("course_hours"));
       // course_pre1.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, String>("course_pre_requiest"));
        course_code1.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, String>("course_code"));
       
        listM = mysqlconnect_Students.getDatausers();
        table_1.setItems(listM);
    }
    
    public void UpdateTable_2() {

        id_course2.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, Integer>("id_course"));
        course_name2.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, String>("course_name"));
        course_hours2.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, Integer>("course_hours"));
       // course_pre2.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, String>("course_pre_requiest"));
        course_code2.setCellValueFactory(new PropertyValueFactory<regestier_Class_model, String>("course_code"));
       
        listM = mysqlconnect_Students.getDatausers_2();
        table_2.setItems(listM);
    }

    @FXML
    void getItem(MouseEvent event) {
    	
    	
    	
    	index = table_1.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;

        }

        
        coursename_text.setText(id_course1.getCellData(index).toString());
    //    pre_text.setText(course_pre1.getCellData(index).toString());
        coursehours_text.setText(course_hours1.getCellData(index).toString());
        id_text.setText(id_course1.getCellData(index).toString());
        code_text.setText(course_code1.getCellData(index).toString());

    }
    @FXML
    void getitem2(MouseEvent event) {
    	index = table_2.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;

        }

        coursename_text.setText(id_course2.getCellData(index).toString());
      //  pre_text.setText(course_pre2.getCellData(index).toString());
        coursehours_text.setText(course_hours2.getCellData(index).toString());
        id_text.setText(id_course2.getCellData(index).toString());
        code_text.setText(course_code2.getCellData(index).toString());
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("st_Welcom.fxml"));
      	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
      	scene = new Scene(root);
      	primaryStage.setScene(scene);
      	primaryStage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = connectDb();
        UpdateTable_1();
        UpdateTable_2();
        
    }    
    
}



