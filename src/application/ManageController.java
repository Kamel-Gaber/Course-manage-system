/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.w3c.dom.ls.LSException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author fatma
 */
public class ManageController implements Initializable {
	static String select;
	String first_name;
	String last_name;
	String email;
	String c1[];
	String c2[];

private PreparedStatement statment = null;
private Connection con = null;
private ResultSet rs = null;
@FXML
private Stage primaryStage;
private Scene scene;
private Parent root;
@FXML
private ObservableList<Student> list;
@FXML
private TextField txt_Academ_Number;
@FXML
private TableView<Student> Manage_Student;
@FXML
private TableColumn<Student,String> ID_COL;
@FXML
private TableColumn<Student,String> firstNAME_COL;
@FXML
private TableColumn<Student,String> lastNAME_COL;
@FXML
private TableColumn<Student,String> Email_COL;
@FXML
private TableColumn<Student,String> Password_COL;
private int table_index;
@FXML
private Button back;
@FXML
private Button b4;
@FXML
private ComboBox<String> compo;

    
    //------------------------------------display data in tableview----------------------------
    Connect_To_DB file;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            con = Connect_To_DB.connectDb();
            list = FXCollections.observableArrayList();
            display_Students();
            loaddatafromDB();
        } catch (SQLException ex) {
            
        }
       //-------------------------------------------------//
       ObservableList<String> attendance = FXCollections.observableArrayList();
       try {
    database con = new database();
   	Connection con1;
	con1 = con.fileConnection();
	ResultSet rs;
	PreparedStatement pst;
	String sql3 = "SELECT  course_name from fci_registration.courses ";
	 pst = con1.prepareStatement(sql3);
       rs = pst.executeQuery(sql3);
	      while(rs.next()) {
	    	  attendance.add(new String(rs.getString("course_name")));
	      }
	} catch (SQLException e) {
		e.printStackTrace();
	}
       compo.setItems(attendance);
       compo.setValue(compo.getItems().get(0));
        
       //-----------------------------------------//
        
        }   
   
    
    @FXML
    void choose(ActionEvent  event) {
    	select = compo.getValue();
    	System.out.println(select);
    }
     
    
    //--------------------------------Display Students--------------------------
     public void display_Students() {

        ID_COL.setCellValueFactory(new PropertyValueFactory<>("Academ_Number"));
        firstNAME_COL.setCellValueFactory(new PropertyValueFactory<>("FisrtName"));
        lastNAME_COL.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        Email_COL.setCellValueFactory(new PropertyValueFactory<>("Email"));
        Password_COL.setCellValueFactory(new PropertyValueFactory<>("Password"));
        

    }
     
     //--------------------------------load data from database----------------------------
     
    public void loaddatafromDB() throws SQLException {
        statment = con.prepareStatement("select * from `fci_registration`.`students`");
        rs = statment.executeQuery();
        while (rs.next()) {
            list.add(new Student(rs.getString(6), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        Manage_Student.setItems(list);
        Manage_Student.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Manage_Student.getSelectionModel().setCellSelectionEnabled(true);
        ID_COL.setCellValueFactory(new PropertyValueFactory<>("Academ_Number"));
        firstNAME_COL.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastNAME_COL.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        Email_COL.setCellValueFactory(new PropertyValueFactory<>("Email"));
        Password_COL.setCellValueFactory(new PropertyValueFactory<>("Password"));

    }
    
    //-------------------------Search Button-----------------------
    
    @FXML
    private void Search_Button(ActionEvent event) throws SQLException{
         list.clear();
        con = Connect_To_DB.connectDb();
        String t = txt_Academ_Number.getText();
        Statement s = con.createStatement();

        ResultSet rs = s.executeQuery("SELECT * FROM `fci_registration`.`students` WHERE `accadimic_number`='" + t + "';");
        while (rs.next()) {
            list.add(new Student(rs.getString(6),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }

        Manage_Student.setItems(list);
        

    }
    
    //-------------------------Select Student---------------------------
    @FXML
    private void get_Student(MouseEvent event){
           table_index=Manage_Student.getSelectionModel().getSelectedIndex();
       if(table_index<=-1){
           return;
       }
       txt_Academ_Number.setText(ID_COL.getCellData(table_index).toString());
    }
    
    //------------------------------Delete Button--------------------------------
    @FXML
    private void Delete_Button(ActionEvent event) throws SQLException{
         Manage_Student.getItems().removeAll(Manage_Student.getSelectionModel().getSelectedItem());
         String academ=txt_Academ_Number.getText();
    	String sql1 = "DELETE FROM `fci_registration`.`students` WHERE `accadimic_number`='" + academ +"';";
        System.out.println(academ);
        statment = con.prepareStatement(sql1);
        statment.execute();
    }
    
    //-----------------------------Move to Add Frame----------------------------------


    @FXML
    private void Student_Add(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("Add.fxml"));
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	primaryStage.setScene(scene);
	primaryStage.show();
        
    }
    
    //------------------------------------Move to Update Frame-----------------------------
    
    @FXML
    private void Student_Update(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Update.fxml"));
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	primaryStage.setScene(scene);
	primaryStage.show();
    }
    //----------------------------------------Back-------------------
    
    @FXML
    void Back(ActionEvent event) throws IOException {
    	   root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
           primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   	scene = new Scene(root);
   	primaryStage.setScene(scene);
   	primaryStage.show();
    }
    
    //-----------------------------Generate--------------//
    @FXML
    public void generate(ActionEvent event)throws IOException, SQLException  {
    	ObservableList<Integer> ids = FXCollections.observableArrayList();
    	ObservableList<String> names = FXCollections.observableArrayList();
    	int id_attendance;
    	int id_std_att;
    	database con = new database();
       	Connection con1;
    	con1 = con.fileConnection();
    	ResultSet rs1;
    	ResultSet rs2;
    	ResultSet rs3;
    	PreparedStatement pst;
    	String sql3 = "SELECT  id from fci_registration.courses where course_name = '"+select+"' ";
    	 pst = con1.prepareStatement(sql3);
           rs1 = pst.executeQuery(sql3);
    	      while(rs1.next()) {
    	    	  id_attendance = rs1.getInt(1);
    	    	String sql4 = "SELECT  student_id from fci_registration.registration where course_id = '"+id_attendance+"' ";
    	    	 pst = con1.prepareStatement(sql4);
    	           rs2 = pst.executeQuery(sql4);
    	    	      while(rs2.next()) {
    	    	    	id_std_att=rs2.getInt(1);
    	    	    	
    	    	    	 ids.add(new Integer(rs2.getInt(1)));
    	    	      }    
    	      }
    	     names.clear();
    	      for (int item : ids) {
    	    	    
    	       	 String sql5 = "SELECT  first_name , last_name , email from fci_registration.students where id = '"+item+"' ";
     	    	 pst = con1.prepareStatement(sql5);
     	           rs3 = pst.executeQuery(sql5);
     	    	      while(rs3.next()) {
     	    	    	 first_name = rs3.getString(1);
     	    	    	 last_name = rs3.getString(2);
     	    	    	 email = rs3.getString(3);
     	    	    	 names.add(new String (first_name+" "+last_name+" "+email));
     	    	      }
     	    	   
    	    	}
    	      for (String fullName : names) {
	    	        csv(names);
	    	    }
    	      
    	      names.clear();
    	      ids.clear();
    	      Alert alert = new Alert(AlertType.INFORMATION);
    	      alert.setTitle("CSV File Created");
    	      alert.setHeaderText(null);
    	      alert.setContentText("The Attendance file has been created"+"\n"+"Look at your Downloads");
    	      alert.showAndWait();
    	      
    	     
   }
    


	public void csv(List<String> names) throws IOException {
	    File f = new File(System.getProperty("user.home") + "/Downloads/" + select + ".csv");
	    FileWriter write = new FileWriter(f);
	    write.append("First Name,Last Name,Email,Week1,Week2,Week3,Week4,Week5,Week6,Week7,\n");
	    for (String fullName : names) {
	        String[] parts = fullName.split(" ");
	        write.append(parts[0]).append(",").append(parts[1]).append(",").append(parts[2]).append("\n");
	    }
	    write.flush();
	    write.close();
	}
  
  
    
    
}
