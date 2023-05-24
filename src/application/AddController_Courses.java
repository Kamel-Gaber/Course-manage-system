package application;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

//import static soft_project.Soft_project.st;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

/*
 * FXML Controller class
 *
 * @author MANARAT
 */
public class AddController_Courses implements Initializable {
	@FXML
private Stage primaryStage;
private Scene scene;
private Parent root;
ObservableList<courses> listM;
    @FXML
    Button add;
    
    @FXML
    private Button clear;
   @FXML
    private TextField courses_name;

     @FXML
    private TextField course_code;

    @FXML
    private TextField course_hours;
   @FXML
    private TextField course_doctor_id;
    
    @FXML
    private TextField department_id;
     
      @FXML
    private Button Save_btn;

    @FXML
    private Button Back_btn;
    
    @FXML
    private TableView<courses> table1;
    
    @FXML
    private TableColumn<courses, String> cou_code;

    @FXML
    private TableColumn<courses, String> cou_name;
   
    @FXML
    private Button pre;

    @FXML
    private TextField req1 ;

    @FXML
    private TextField req2 ;

    @FXML
    private TextField req3 ;
    
    @FXML
    private TextField req11 ;

    @FXML
    private TextField req21 ;

    @FXML
    private TextField req31;
    
    Connection conn;
    PreparedStatement pst1 ;
    ResultSet rs;
    
    @FXML
    void Save_Courses(ActionEvent event) {
       conn = mysqlconnect_admin.connectDb();
      
        try {
        	 String x1 = courses_name.getText();
             String x2 = course_code.getText();
             
             int x4 =Integer.parseInt( course_hours.getText());
             int x5 =Integer.parseInt(course_doctor_id .getText());
             int x6 =Integer.parseInt(department_id.getText());
             
             String sql1 ="insert into fci_registration.courses (course_name, course_code,course_hours,course_doctor_id,department_id) VALUES ('"+x1+"','"+x2+"','"+x4+"','"+x5+"','"+x6+"')";         
            pst1 = conn.prepareStatement(sql1);
            pst1.execute();
            table1.setDisable(false);
            JOptionPane.showMessageDialog(null,"saved");
        } catch (Exception e) {
           // JOptionPane.showMessageDialog(null,"unsaved");
         e.printStackTrace();
        }
    }
  
    int index = - 1;
    @FXML
    void getSelected(MouseEvent event) {
        index = table1.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        
    System.out.println("hghhhhhhhhh"+req1.getText());
   if(req1.getText().equals("Empty")&& !req2.getText().equals("Empty")&& !req3.getText().equals("Empty")) {
	   req1.setText(cou_code.getCellData(index).toString());
   	   req11.setText(cou_name.getCellData(index).toString());
   	   req2.setText("Empty");
   }else if(!req1.getText().equals("Empty")&& req2.getText().equals("Empty")&& !req3.getText().equals("Empty")) {
	   req2.setText(cou_code.getCellData(index).toString());
   	   req21.setText(cou_name.getCellData(index).toString());
   	   req3.setText("Empty");
   }else if(!req1.getText().equals("Empty")&& !req2.getText().equals("Empty")&& req3.getText().equals("Empty")) {
	   req3.setText(cou_code.getCellData(index).toString());
   	   req31.setText(cou_name.getCellData(index).toString());
   }
        
   pre.setDisable(false);
        
  
    }
    
   
    
     public void Back_btn(ActionEvent event) {
        try {

        	 root = FXMLLoader.load(getClass().getResource("main.fxml"));
        	   	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	   	scene = new Scene(root);
        	   	primaryStage.setScene(scene);
        	   	primaryStage.centerOnScreen();
        	   	primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (Exception ex) {

        }
    }
     
     @FXML
     void pre_btn(ActionEvent event) {
    	 try {
    		int course_id;
    		String course_Name;
    		 int pre_id1,pre_id2,pre_id3;
             conn = mysqlconnect_admin.connectDb();
            String query1 = "select id , course_name from fci_registration.courses where course_code = '"+course_code.getText()+"'";
            pst1 = conn.prepareStatement(query1);
          rs =  pst1.executeQuery();
            while (rs.next()) {
            	course_id = rs.getInt(1);
            	course_Name = rs.getString(2);
            System.out.println("KAAAAAAAAAAAAK "+course_id);
            System.out.println("KAAAAAAAAAAAAK "+course_Name);
            //////////////////////
            if(!req1.getText().equals("Empty")) {
            String query2 = "select id ,course_name  from fci_registration.courses where course_code = '"+req1.getText()+"'";
           
            pst1 = conn.prepareStatement(query2);
            rs =  pst1.executeQuery();
            while (rs.next()) {
            	pre_id1 = rs.getInt(1);
            	String first_course_request = rs.getString(2);
            	System.out.println("pre 1 "+pre_id1);
            	String sql1 ="insert into fci_registration.requests (course_id, course_name, course_id_pre_request,course_name_pre) values (?,?,?,?)";         
                pst1 = conn.prepareStatement(sql1);
                pst1.setInt(1, course_id);
                pst1.setString(2, course_Name);
                pst1.setInt(3, pre_id1);
                pst1.setString(4, first_course_request);
                pst1.executeUpdate();
            }
            }
            /////////////////////
            if(!req2.getText().equals("Empty")) {
            String query3 = "select id ,course_name  from fci_registration.courses where course_code = '"+req2.getText()+"'";
            pst1 = conn.prepareStatement(query3);
            rs =  pst1.executeQuery();
            while (rs.next()) {
            	pre_id2 = rs.getInt(1);
            	String second_course_request = rs.getString(2);
            	System.out.println("pre 2 "+pre_id2);
            	String sql1 ="insert into fci_registration.requests (course_id, course_name, course_id_pre_request,course_name_pre) values (?,?,?,?)";         
                pst1 = conn.prepareStatement(sql1);
                pst1.setInt(1, course_id);
                pst1.setString(2, course_Name);
                pst1.setInt(3, pre_id2);
                pst1.setString(4, second_course_request);
                pst1.executeUpdate();
            }
            }
            ///////////////////
            if(!req3.getText().equals("Empty")) {
            String query4 = "select id ,course_name  from fci_registration.courses where course_code = '"+req3.getText()+"'";
            pst1 = conn.prepareStatement(query4);
            rs =  pst1.executeQuery();
            while (rs.next()) {
            	pre_id3 = rs.getInt(1);
            	String third_course_request = rs.getString(2);
            	System.out.println("pre 3 "+pre_id3);
            	String sql1 ="insert into fci_registration.requests (course_id, course_name, course_id_pre_request,course_name_pre) values (?,?,?,?)";         
                pst1 = conn.prepareStatement(sql1);
                pst1.setInt(1, course_id);
                pst1.setString(2, course_Name);
                pst1.setInt(3, pre_id3);
                pst1.setString(4, third_course_request);
                pst1.executeUpdate();
            }
            }
           
            
            
            
            
    	 
    	 }
    	 } catch (Exception e) {
            e.printStackTrace();
         }
    	 
     }
     
     @FXML
     void Clear(ActionEvent event) {
    	 req1.setText("Empty");
         req2.setText("Empty2");
         req3.setText("Empty3");
         req11.setText("Empty");
         req21.setText("Empty2");
         req31.setText("Empty3");
     }
     
     @Override
     public void initialize(URL url, ResourceBundle rb) {
         // TODO
    	 cou_name.setCellValueFactory(new PropertyValueFactory<courses, String>("course_name"));
         cou_code.setCellValueFactory(new PropertyValueFactory<courses, String>("course_code"));
         listM = mysqlconnect_admin.getaddcourses();
         table1.setItems(listM);
         req1.setText("Empty");
         req2.setText("Empty2");
         req3.setText("Empty3");
         pre.setDisable(true);
        table1.setDisable(true);
     }
    
}