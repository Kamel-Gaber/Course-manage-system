package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


 
public class Admin_modController implements Initializable {
	
	
	@FXML
private Stage primaryStage;
private Scene scene;
private Parent root;
PreparedStatement pst1 ;
ResultSet rs;
Connection conn;

    @FXML
    private Button add;

    @FXML
    private TableView<admin_model> admin_table;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private TableColumn<admin_model, String> email;

    @FXML
    private TextField emailtxt;

    @FXML
    private TableColumn<admin_model, String> fname;

    @FXML
    private TextField fnametxt;

    @FXML
    private TableColumn<admin_model, String> lname;

    @FXML
    private TextField lnametxt;

    @FXML
    private TableColumn<admin_model, String> pass;

    @FXML
    private TextField passtxt;

    @FXML
    private Button update;
    
    int id;

    @FXML
    void add(ActionEvent event) throws SQLException {
    	database con = new database();
    	Connection con1 = con.fileConnection();
    	String fname=fnametxt.getText();
    	String lname=lnametxt.getText();
    	String email=emailtxt.getText();
    	String password=passtxt.getText();
    	String insert = "insert into fci_registration.admins (first_name, last_name, email, password) values ('"+fname+"','"+lname+"','"+email+"','"+password+"')  ";
    	pst1 = con1.prepareStatement(insert);
    	pst1.execute();
    	fnametxt.clear();
    	lnametxt.clear();
    	emailtxt.clear();
    	passtxt.clear();
    	update_admins();
    	
    		
    }

    @FXML
    void back(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
	   	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
	   	scene = new Scene(root);
	   	primaryStage.setScene(scene);
	   	primaryStage.centerOnScreen();
	   	primaryStage.show();
    }
    int index = - 1;
    
    public int get_admin_id() throws SQLException {
    	int admin_id = 0;
    	database con = new database();
    	Connection con1 = con.fileConnection();
    	   String gethim = "select admin_id from fci_registration.admins where first_name = '"+fnametxt.getText()+"' and last_name = '"+lnametxt.getText()+"' and email = '"+emailtxt.getText()+"' ";
           pst1 = con1.prepareStatement(gethim);
	        rs = pst1.executeQuery(gethim);
       while(rs.next()) {
    	   admin_id = rs.getInt(1);
       		
       }
      
    	return admin_id;
		
	}
    
    @FXML
    void getselected_admins(MouseEvent event) throws SQLException {
    	
    	   index = admin_table.getSelectionModel().getSelectedIndex();
           if (index <= -1) {
               return;
           }
           
          
          
           fnametxt.setText(fname.getCellData(index).toString());
           lnametxt.setText(lname.getCellData(index).toString());
           emailtxt.setText(email.getCellData(index).toString());
           passtxt.setText(pass.getCellData(index).toString());
           get_admin_id();
           id = get_admin_id();
          

    }
    
    @FXML
    void delete(ActionEvent event) throws SQLException {
    	database con = new database();
    	Connection con1 = con.fileConnection();
    	String delete = "DELETE FROM `fci_registration`.`admins` WHERE (`admin_id` = '"+id+"')";
    	pst1 = con1.prepareStatement(delete);
    	pst1.executeUpdate();
    	fnametxt.clear();
    	lnametxt.clear();
    	emailtxt.clear();
    	passtxt.clear();
    	update_admins();
    	
    }

    @FXML
    void update(ActionEvent event) throws SQLException {
    	database con = new database();
    	Connection con1 = con.fileConnection();
    	String update = "UPDATE `fci_registration`.`admins` SET `first_name` = '"+fnametxt.getText()+"', `last_name` = '"+lnametxt.getText()+"', `email` = '"+emailtxt.getText()+"', `password` = '"+passtxt.getText()+"' WHERE (`admin_id` = '"+id+"')";
    	pst1 = con1.prepareStatement(update);
    	pst1.executeUpdate();
    	fnametxt.clear();
    	lnametxt.clear();
    	emailtxt.clear();
    	passtxt.clear();
    	update_admins();

    }
    
    public static ObservableList<admin_model> getDataAdmins() throws SQLException{
    	database con = new database();
       	Connection con1;
       	con1 = con.fileConnection();
        
        
        ObservableList<admin_model> list =  FXCollections.observableArrayList();
        try {
            java.sql.PreparedStatement ps = con1.prepareStatement("select  first_name, last_name, email, password from fci_registration.admins  ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                    
                list.add(new admin_model(rs.getString("first_name"),rs.getString("last_name"),rs.getString("email"),rs.getString("password")));
                
                
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return list;
    }
    public void update_admins() throws SQLException {
    	 fname.setCellValueFactory(new PropertyValueFactory<admin_model, String>("first_name"));
	        lname.setCellValueFactory(new PropertyValueFactory<admin_model, String>("last_name"));
	        email.setCellValueFactory(new PropertyValueFactory<admin_model, String>("email"));
	        pass.setCellValueFactory(new PropertyValueFactory<admin_model, String>("password"));
	        list1 = getDataAdmins();
	        admin_table.setItems(list1);
		
	}
    
    ObservableList<admin_model> list1;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 
	        fname.setCellValueFactory(new PropertyValueFactory<admin_model, String>("first_name"));
	        lname.setCellValueFactory(new PropertyValueFactory<admin_model, String>("last_name"));
	        email.setCellValueFactory(new PropertyValueFactory<admin_model, String>("email"));
	        pass.setCellValueFactory(new PropertyValueFactory<admin_model, String>("password"));
	        try {
				list1 = getDataAdmins();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	        admin_table.setItems(list1);
	        
		
	}

}
