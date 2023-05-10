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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class AddController implements Initializable {

    private PreparedStatement statment = null;
    private Connection con = null;
    private ResultSet rs = null;
    private ObservableList<Student> list;
    @FXML
    private Stage primaryStage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField Academ_txt;
    @FXML
    private TextField Firstname_txt;
    @FXML
    private TextField Lastname_txt;
    @FXML
    private TextField Email_txt;
    @FXML
    private TextField Password_txt;
    @FXML
    private TableView<Student> Add_Table;
    @FXML
    private TableColumn<Student, String> Academ_add;
    @FXML
    private TableColumn<Student, String> Firstname_add;
    @FXML
    private TableColumn<Student, String> Lastname_add;
    @FXML
    private TableColumn<Student, String> Email_add;
    @FXML
    private TableColumn<Student, String> Password_add;

    //----------------------------------Back to manage page----------------------------------  
    @FXML
    private void Back_Manage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Manage.fxml"));
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void display_Students() {

        Academ_add.setCellValueFactory(new PropertyValueFactory<>("Academ_Number"));
        Firstname_add.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        Lastname_add.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        Email_add.setCellValueFactory(new PropertyValueFactory<>("Email"));
        Password_add.setCellValueFactory(new PropertyValueFactory<>("Password"));

    }
//----------------------------------------Add Button--------------------------------

    @FXML
    private void Add_Student(ActionEvent event) throws SQLException, IOException {
        String academ=Academ_txt.getText();
        String first=Firstname_txt.getText();
        String last=Lastname_txt.getText();
        String email=Email_txt.getText();
        String password=Password_txt.getText();
         String sql1 = "INSERT INTO `fci_registration`.`students` (first_name, last_name, email,password,accadimic_number,gpa) VALUES ('" + first + "','" + last + "','" + email + "','" + password + "','" + academ + "','" + 0+ "')";
            statment = con.prepareStatement(sql1);
            statment.execute();
            
            Student register = new Student(academ, first, last, email,password,"");
            register.setAcadem_Number(academ);
            register.setFirstname(first);
            register.setLastname(last);
            register.setEmail(email);
            register.setPassword(password);
            Add_Table.getItems().add(register);
//            Coursesubm.getItems().add(register);

            Academ_txt.clear();
            Firstname_txt.clear();
            Lastname_txt.clear();
            Email_txt.clear();
            Password_txt.clear();

        
        
        

    }

    //------------------------
    public void loaddatafromDB() throws SQLException {
        statment = con.prepareStatement("select * from `fci_registration`.`students`");
        rs = statment.executeQuery();
        while (rs.next()) {
            list.add(new Student(rs.getString(6), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),"Email"));
        }
        Add_Table.setItems(list);
        Add_Table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Add_Table.getSelectionModel().setCellSelectionEnabled(true);
        Academ_add.setCellValueFactory(new PropertyValueFactory<>("Academ_Number"));
        Firstname_add.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        Lastname_add.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        Email_add.setCellValueFactory(new PropertyValueFactory<>("Email"));
        Password_add.setCellValueFactory(new PropertyValueFactory<>("Password"));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Connect_To_DB.connectDb();
        list = FXCollections.observableArrayList();
        display_Students();
        try {
            loaddatafromDB();
        } catch (SQLException ex) {
            Logger.getLogger(AddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
