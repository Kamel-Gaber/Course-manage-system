package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class Login_PageController implements Initializable {
	
	boolean f =false;
	  ResultSet rs;
	  ResultSet rs1;
	  PreparedStatement pst;
	  PreparedStatement pst1;
	  static String who;
	  static String who3;
	  static String who2;
	
		@FXML
		private Stage primaryStage;
		private Scene scene;
		private Parent root;
		
    @FXML
    private TextField email_txt;

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField pass_txt;

    @FXML
    private RadioButton rb_admin;

    @FXML
    private RadioButton rb_doctor;

    @FXML
    private RadioButton rb_student;

    
    
    
    @FXML
    void Login(ActionEvent event) throws SQLException, IOException {
    	if(rb_admin.isSelected()) {
    		database con = new database();
           	Connection con1;
           	con1 = con.fileConnection();
            if (email_txt.getText().trim().isEmpty()&& pass_txt.getText().trim().isEmpty()) {
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please provide an Email and a Password");
                alert.show();
                
            } else {
            
     	   loginadmin(email_txt.getText(),pass_txt.getText());
          if(f== true) {
        	  //Go to Admin Page
        		root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
               	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
               	scene = new Scene(root);
               	primaryStage.setScene(scene);
               	primaryStage.centerOnScreen();
               	primaryStage.show();
         	 
          }
        	  
    }
    }else if (rb_doctor.isSelected()) {
 	   database con = new database();
    	Connection con1;
    	con1 = con.fileConnection();

        if (email_txt.getText().trim().isEmpty()&& pass_txt.getText().trim().isEmpty()) {
            Alert aa =new Alert(Alert.AlertType.ERROR);
            aa.setContentText("please fill all information");
            aa.show();
            
        } else {
            loginDoctor(email_txt.getText(),pass_txt.getText());
            
            if(f== true) {
          	 // Go to Doctor page
            	root = FXMLLoader.load(getClass().getResource("Doctor_page.fxml"));
               	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
               	scene = new Scene(root);
               	primaryStage.setScene(scene);
               	primaryStage.centerOnScreen();
               	primaryStage.show();
          	 
           }
        }
    }else if (rb_student.isSelected()) {
  	   database con = new database();
     	Connection con1;
     	con1 = con.fileConnection();

         if (email_txt.getText().trim().isEmpty()&& pass_txt.getText().trim().isEmpty()) {
             Alert aa =new Alert(Alert.AlertType.ERROR);
             aa.setContentText("please fill all information");
             aa.show();
             
         } else {
             loginStudent(email_txt.getText(),pass_txt.getText());
             
             if(f== true) {
           	 // Go to Student page
            		root = FXMLLoader.load(getClass().getResource("st_Welcom.fxml"));
                   	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                   	scene = new Scene(root);
                   	primaryStage.setScene(scene);
                   	primaryStage.centerOnScreen();
                   	primaryStage.show();
           	 
            }
         }
     }
    	}
    
    
    
    //////////////////////
    //////Method to log in to admin/////////
    public void loginadmin(String username,String password){
        
        try{
        	database con = new database();
        	Connection con1;
        	con1 = con.fileConnection();
            pst=con1.prepareStatement("select * from fci_registration.admins where email=? and password=?");
            pst1=con1.prepareStatement("select * from fci_registration.admins where email=? and password=?");
            pst.setString(1, username);
            pst.setString(2, password);
            pst1.setString(1, username);
            pst1.setString(2, password);
            rs=pst.executeQuery();  
            rs1=pst1.executeQuery();  
            while(rs1.next()) {
            	System.out.println(rs1.getString(4));
            	who3=rs1.getString(4);
            }
            if(!rs.isBeforeFirst()){
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid");
                alert.show();
                f = false; 
            }else{
                while (rs.next()) { 
                    String passcheck =rs.getNString("password");   ///columtable
                    if(passcheck.equals(password)) {
                    	f= true;
                    }else{
                         Alert alert1 =new Alert(Alert.AlertType.ERROR);
                         alert1.setContentText("Please provid a correct password");
                         alert1.show();
                    }       
                    
                }                 
                    
                }
            
            
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                
                if(rs !=null){
                    try{
                    rs.close();
                    }catch(SQLException e){ e.printStackTrace();}
                }
                if(pst!=null){
                    try{
                    pst.close();
                    }catch(SQLException e){e.printStackTrace();}
                }
      
            }
            
        }
    ////////////////////////////////////////
    //////Method to login to doctor
    public void loginDoctor(String username,String password){
        try{
        	database con = new database();
        	Connection con1;
        	con1 = con.fileConnection();
            pst=con1.prepareStatement("select * from fci_registration.doctors where email=? and password=?");            
            pst1=con1.prepareStatement("select * from fci_registration.doctors where email=? and password=?");            
            pst.setString(1, username);
            pst.setString(2, password);
            
            pst1.setString(1, username);
            pst1.setString(2, password);
            rs=pst.executeQuery(); 
            rs1=pst1.executeQuery();
            while(rs1.next()) {
            	System.out.println(rs1.getString(4));
            	who=rs1.getString(4);
            	System.out.println(who);
            }
            if(!rs.isBeforeFirst()){
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid");
                alert.show();
                f = false; 
            }else{
                while (rs.next()) { 
                    String passcheck =rs.getNString("password");   ///columtable
                    if(passcheck.equals(password)) {
                    	f= true;
                    }else{
                         Alert alert1 =new Alert(Alert.AlertType.ERROR);
                         alert1.setContentText("the password is Invalid");
                         alert1.show();
                    }       
                }                   
                }
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                
                if(rs !=null){
                    try{
                    rs.close();
                    }catch(SQLException e){ e.printStackTrace();}
                }
                if(pst!=null){
                    try{
                    pst.close();
                    }catch(SQLException e){e.printStackTrace();}
                }      
            }
            
        }
    ///////////////////////////////////
    ////////Method to login to Students////////
    public void loginStudent(String username,String password){
        try{
        	database con = new database();
        	Connection con1;
        	con1 = con.fileConnection();
            pst=con1.prepareStatement("select * from fci_registration.students where email=? and password=?");            
            pst1=con1.prepareStatement("select * from fci_registration.students where email=? and password=?");            

            pst.setString(1, username);
            pst.setString(2, password);
            
            pst1.setString(1, username);
            pst1.setString(2, password);
            rs=pst.executeQuery(); 
            rs1=pst1.executeQuery();
            while(rs1.next()) {
            	System.out.println(rs1.getString(4));
            	who2=rs1.getString(4);
            }
            
            if(!rs.isBeforeFirst()){
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid");
                alert.show();
                f = false; 
            }else{
                while (rs.next()) { 
                    String passcheck =rs.getNString("password");   ///columtable
                    if(passcheck.equals(password)) {
                    	f= true;
                    }else{
                         Alert alert1 =new Alert(Alert.AlertType.ERROR);
                         alert1.setContentText("the password is Invalid");
                         alert1.show();
                    }       
                }                   
                }
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                
                if(rs !=null){
                    try{
                    rs.close();
                    }catch(SQLException e){ e.printStackTrace();}
                }
                if(pst!=null){
                    try{
                    pst.close();
                    }catch(SQLException e){e.printStackTrace();}
                }      
            }
            
        }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ToggleGroup tg = new ToggleGroup();
		rb_admin.setToggleGroup(tg);
		rb_doctor.setToggleGroup(tg);
		rb_student.setToggleGroup(tg);
		
	}
    
 
}
