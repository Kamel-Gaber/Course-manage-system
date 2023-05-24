/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;
import static application.mysqlconnect.connectDb;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class Doctor_Page_Controller extends Login_PageController  implements Initializable{
   @FXML
private Stage primaryStage;
private Scene scene;
private Parent root;

public Login_PageController log = new Login_PageController();
String who_email = log.who;
public static String n;
    @FXML
    private Button back;

    @FXML
    private TableColumn<Data_courses, String > course_column;

    @FXML
    private Label email;

    @FXML
    private ImageView logo;

    @FXML
    private TableView<Data_courses> my_courses;

    @FXML
    private Label name;

    Connection conn = null; 
    ObservableList<Data_courses> listM;
    int index = - 1;
    ResultSet rs = null;
    PreparedStatement pst = null;
   
   
    
    @FXML
    void back(ActionEvent event) throws IOException {
    	 root = FXMLLoader.load(getClass().getResource("Login_Page.fxml"));
     	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
     	scene = new Scene(root);
     	primaryStage.setScene(scene);
     	primaryStage.show();

    }
    
    @FXML
    void select(MouseEvent event) throws IOException {
index = my_courses.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        root = FXMLLoader.load(getClass().getResource("Course.fxml"));
    	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
     n =course_column.getCellData(index).toString();
     
    }
    
    
// GET data and passing to label   
public  void name_email(){
    Connection conn = connectDb();
    
    try {
         java.sql.PreparedStatement ps = conn.prepareStatement("select first_name from fci_registration.doctors where email = '"+who_email+"'");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {                    
             String Name = rs.getString("first_name"); 
             String Email = who_email;    
               name.setText(Name);
               email.setText(Email);
                }
            } catch (Exception e) {
            	System.out.println("Something Wrong");
            	e.printStackTrace();
            	
            }
    }
///////////////////////////////////
    public void UpdateTable() {

        course_column.setCellValueFactory(new PropertyValueFactory<Data_courses, String>("CourseName"));
        listM = mysqlconnect.getDatausers();
        my_courses.setItems(listM);
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	 UpdateTable();
         name_email();
        }
	


}



