/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.StatementImpl;

import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author fatma
 */
public class Doctor_manageController implements Initializable {
    
	@FXML
	private Stage primaryStage;
	private Scene scene;
	private Parent root;
	
	   @FXML
	    private Button b1;

	    @FXML
	    private Button b2;

	    @FXML
	    private Button b3;
	    
	   
	       @FXML
	    private TextField txt_id;
	       
	    

	    @FXML
	    private TextField t2;

	    @FXML
	    private TextField t3;

	    @FXML
	    private TextField t4;
	      @FXML
	    private PasswordField t5;

	    @FXML
	    private TableView<doctordata> tbl1;

	    
	      @FXML
	    private TableColumn<doctordata, Integer> tc_doc;

	    @FXML
	    private TableColumn<doctordata,String> tc_l;

	    @FXML
	    private TableColumn<doctordata,String> tc_n1;

	    @FXML
	    private TableColumn<doctordata, String> tc_n2;

	    @FXML
	    private TableColumn<doctordata, String> tc_o;
	    
	   private Connection connect;
	   private PreparedStatement prest;
	   private ResultSet result;
	   private StatementImpl st;
	  
	   

	      @FXML
	    void adddoctor(ActionEvent event) {
	        connect = database_con.connectDb();
	    	 
	        try {
	        	 String x1 = t2.getText();
	             
	             String x2 = t3.getText();
	            
	             String x3 = t4.getText();
	             String x4 = t5.getText();
	           
	  
	         String sql1 ="INSERT INTO `fci_registration`.`doctors` (first_name, last_name,email,password) VALUES ('"+x1+"','"+x2+"','"+x3+"','"+x4+"')";
	         
	         prest = connect.prepareStatement(sql1);
	      
	            prest.execute();
	            UpdateTable();
	        	

	            JOptionPane.showMessageDialog(null,"saved");
	            clear();
	        } catch (Exception e) {
	           // JOptionPane.showMessageDialog(null,"unsaved");
	        	e.printStackTrace();
	        }

	    }
	    public void clear(){
	       txt_id.setText("");
	        t2.setText("");
	        t3.setText("");
	        t4.setText("");
	        t5.setText("");
	    
	    
	    }
	   
	    public void update(ActionEvent event){
	       try {
	            connect = database_con.connectDb();
	            
	            String value =  t2.getText();
	            String value1 = t3.getText();
	            String value2 = t4.getText();
	            String value3 = t5.getText();
	            String value4 = txt_id.getText();
	            System.out.println("id ="+value4);
	            
	            String sql = "update fci_registration.doctors set first_name= '" + value + "',last_name= '"
	                    + value1 + "',email= '" + value2 + "',password= '" + value3 + "' where doctor_id='" + value4 + "';" 
	                    ;
	           //"update fci_registration.doctors set first_name= '" + value + "',last_name= '"value4 + "' ";
	                    
	            prest = connect.prepareStatement(sql);
	            prest.execute();


	            UpdateTable();
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }


	    
	    }
	     
	   public boolean check() throws SQLException{
                 prest = connect.prepareStatement("select * from  fci_registration.courses ");
                 ResultSet rs = prest.executeQuery();
                 while (rs.next()) {
                    int i =rs.getInt("course_doctor_id");
                    if(Integer.parseInt(txt_id.getText())==i){
                        return true;
                    
                    }
                
                }
                 return false;
                 
        
        
    }
     
	    @FXML
	    void delete(ActionEvent event) throws SQLException {
                
                
	         connect = database_con.connectDb();
                 
                 if(check()){
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setContentText(" cant delete this doctor because resposible of course");
                     alert.showAndWait();
                     return;
                 
                 
                 }else{
	          try {
	              String sql="DELETE from doctors where doctor_id=? ";
	            
	            
	          
	                    
	            prest = connect.prepareStatement(sql);
	            prest.setString(1,txt_id.getText());       
	            prest.execute();
	             JOptionPane.showMessageDialog(null, "delete");
	             clear();
	             
	            UpdateTable();
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }

	        

	    }
	    
            }
	    
	 
	      ObservableList<doctordata> list1;
	     public void UpdateTable() {

	        tc_doc.setCellValueFactory(new PropertyValueFactory<doctordata,Integer>("doctor_id"));
	        tc_n1.setCellValueFactory(new PropertyValueFactory<doctordata, String>("first_name"));
	        tc_n2.setCellValueFactory(new PropertyValueFactory<doctordata, String>("last_name"));
	        tc_l.setCellValueFactory(new PropertyValueFactory<doctordata, String>("email"));
	        tc_o.setCellValueFactory(new PropertyValueFactory<doctordata, String>("password"));
	        
	        list1 = database_con.getdoctor();
	        tbl1.setItems(list1);
	    }
	        @FXML
	    void adddoctorselect(MouseEvent event) {
	        doctordata doctorb=tbl1.getSelectionModel().getSelectedItem();
	        int id=tbl1.getSelectionModel().getSelectedIndex();
	        
	        if ((id-1)<-1){return;}
	        
	        
	        txt_id.setText( tc_doc.getCellData(id).toString());
	         t2.setText(tc_n1.getCellData(id).toString());
	        t3.setText(tc_n2.getCellData(id).toString());
	        t4.setText(tc_l.getCellData(id).toString());
	        t5.setText(tc_o.getCellData(id).toString());
	       


	    }
	        
	        @FXML
	        void Back(ActionEvent event) throws IOException {
	        	   root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
	               primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
	       	scene = new Scene(root);
	       	primaryStage.setScene(scene);
	       	primaryStage.show();
	        }
	    
	  
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {

	        UpdateTable();
	        

	    }    
	    
	}
