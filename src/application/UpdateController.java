package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UpdateController implements Initializable {

    private PreparedStatement statment = null;
    private Connection con = null;
    private ResultSet rs = null;

    @FXML
    private Stage primaryStage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField Academ_txt_update;
    @FXML
    private ObservableList<Student> list;
//@FXML
//private TextField txt_Academ_Number;
    @FXML
    private TableView<Student> Update_Student;
    @FXML
    private TableColumn<Student, String> Academ_update;
    @FXML
    private TableColumn<Student, String> Firstname_update;
    @FXML
    private TableColumn<Student, String> Lastname_update;
    @FXML
    private TableColumn<Student, String> Email_update;
    @FXML
    private TableColumn<Student, String> Password_update;

    @FXML
    private TextField Academ_data;
    @FXML
    private TextField First_data;
    @FXML
    private TextField Last_data;
    @FXML
    private TextField Email_data;
    @FXML
    private TextField Password_data;

    private int table_index;

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

        Academ_update.setCellValueFactory(new PropertyValueFactory<>("Academ_Number"));
        Firstname_update.setCellValueFactory(new PropertyValueFactory<>("FisrtName"));
        Lastname_update.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        Email_update.setCellValueFactory(new PropertyValueFactory<>("Email"));
        Password_update.setCellValueFactory(new PropertyValueFactory<>("Password"));

    }

    //--------------------------------load data from database----------------------------
    public void loaddatafromDB() throws SQLException {
        statment = con.prepareStatement("select * from `fci_registration`.`students`");
        rs = statment.executeQuery();
        while (rs.next()) {
            list.add(new Student(rs.getString(6), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        Update_Student.setItems(list);
        Update_Student.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Update_Student.getSelectionModel().setCellSelectionEnabled(true);
        Academ_update.setCellValueFactory(new PropertyValueFactory<>("Academ_Number"));
        Firstname_update.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        Lastname_update.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        Email_update.setCellValueFactory(new PropertyValueFactory<>("Email"));
        Password_update.setCellValueFactory(new PropertyValueFactory<>("Password"));

    }

    //--------------------------Search ------------------------------------------
    @FXML
    private void Search_update(ActionEvent event) throws SQLException {
        list.clear();
        con = Connect_To_DB.connectDb();
        String t = Academ_txt_update.getText();
        Statement s = con.createStatement();

        ResultSet rs = s.executeQuery("SELECT * FROM `fci_registration`.`students` WHERE `accadimic_number`='" + t + "';");
        while (rs.next()) {
            list.add(new Student(rs.getString(6), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }

        Update_Student.setItems(list);
        Academ_data.clear();
        First_data.clear();
        Last_data.clear();
        Email_data.clear();
        Password_data.clear();

    }

    //---------------------------------Get student data-------------------------------
    @FXML
    private void Get_Old_Data(MouseEvent event) {
        table_index = Update_Student.getSelectionModel().getSelectedIndex();
        if (table_index <= -1) {
            return;
        }
        Academ_data.setText(Academ_update.getCellData(table_index).toString());
        First_data.setText(Firstname_update.getCellData(table_index).toString());
        Last_data.setText(Lastname_update.getCellData(table_index).toString());
        Email_data.setText(Email_update.getCellData(table_index).toString());
        Password_data.setText(Password_update.getCellData(table_index).toString());

    }

  
   

    //--------------------------------Update data-----------------------------------
    @FXML
    private void Update_data(ActionEvent event) throws SQLException {
        String academ = Academ_data.getText();
        String first = First_data.getText();
        String last = Last_data.getText();
        String email = Email_data.getText();
        String password = Password_data.getText();
        String sql = "update `fci_registration`.`students` set first_name= '" + first + "',last_name= '"
                + last + "',email= '" + email + "',password= '" + password + "',accadimic_number='" + academ + "' where accadimic_number='" + academ + "' ";
        statment = con.prepareStatement(sql);
        statment.execute();list.clear();
        loaddatafromDB();
        

    }

    //-------------------------------------back to manage-----------------------------
    @FXML
    private void Back_from_Update(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Manage.fxml"));
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
