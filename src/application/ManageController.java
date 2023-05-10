/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
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
            list.add(new Student(rs.getString(6), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),"Email"));
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
            list.add(new Student(rs.getString(6),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),"Email"));
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
       
    
    
}
