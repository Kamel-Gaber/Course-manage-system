package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Dept_mainController implements Initializable {
	Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
	
	@FXML
private Stage primaryStage;
private Scene scene;
private Parent root;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private TextField code_dept;
    
    @FXML
    private TextField id;

    @FXML
    private Button delete;
    
    @FXML
    private TextField name_dept;

    @FXML
    private Button up;

    @FXML
    private TableColumn<main_department, String> dep_code;

    @FXML
    private TableColumn<main_department, String> dep_name;
    
    @FXML
    private TableColumn<main_department, Integer> dep_id;

    @FXML
    private TableView<main_department> dept_table;
    
    ObservableList<main_department> listM;

    public void dept_table() {
    	dep_code.setCellValueFactory(new PropertyValueFactory<main_department, String>("department_code"));
     	dep_name.setCellValueFactory(new PropertyValueFactory<main_department, String>("department_name"));
     }
    PreparedStatement pst1 ;
    @FXML
    void add_btn(ActionEvent event) {
        conn = mysqlconnect_admin.connectDb();
        
        try {
        	 String x1 = code_dept.getText();
             String x2 = name_dept.getText();
             String sql1 ="insert into fci_registration.department (department_code, department_name) VALUES ('"+x1+"','"+x2+"')";         
            pst1 = conn.prepareStatement(sql1);
            pst1.execute();
            table();
            JOptionPane.showMessageDialog(null,"saved");
        } catch (Exception e) {
           // JOptionPane.showMessageDialog(null,"unsaved");
         e.printStackTrace();
        }
    }

    @FXML
    void back_btn(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("branches.fxml"));
    	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    int index = - 1;
    
    @FXML
    void getSelected(MouseEvent event) {
        index = dept_table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        
        name_dept.setText(dep_name.getCellData(index).toString());
        code_dept.setText(dep_code.getCellData(index).toString());
        id.setText(dep_id.getCellData(index).toString());
       
    }
    @FXML
    void del_btn(ActionEvent event) {
    	try {
    	conn = mysqlconnect_admin.connectDb();
    	String value = id.getText();
		String value0 = code_dept.getText();
        String value1 = name_dept.getText();
        String sql = "delete from fci_registration.department where  id = '"+value+"' ";  
        pst = conn.prepareStatement(sql);
        pst.executeUpdate();
        id.setText(null);
        code_dept.setText(null);
        name_dept.setText(null);
        delete();
        }
    catch (Exception e) {
        e.printStackTrace();
    }
    }

    public void delete() {

    	dep_code.setCellValueFactory(new PropertyValueFactory<main_department, String>("department_code"));
    	dep_id.setCellValueFactory(new PropertyValueFactory<main_department, Integer>("id"));
    	dep_name.setCellValueFactory(new PropertyValueFactory<main_department, String>("department_name"));
        listM = mysqlconnect_admin.getdepartmentDatauser();
        dept_table.setItems(listM);
    }
	@FXML
    void up_btn(ActionEvent event) {
		try {
	    	conn = mysqlconnect_admin.connectDb();
			String value0 = code_dept.getText();
	        String value1 = name_dept.getText();
	        String value = id.getText();
	        String sql = "update fci_registration.department set department_code= '"+ value0 + "',department_name= '" + value1 + "' where id = '"+value+"'  ";  
	        pst = conn.prepareStatement(sql);
	        pst.execute();
	        up();}
	    catch (Exception e) {
	        JOptionPane.showMessageDialog(null, e);
	    }
	}
		public void up() {
			dep_code.setCellValueFactory(new PropertyValueFactory<main_department, String>("department_code"));
	     	dep_name.setCellValueFactory(new PropertyValueFactory<main_department, String>("department_name"));
	    	dep_id.setCellValueFactory(new PropertyValueFactory<main_department, Integer>("id"));

	     	listM = mysqlconnect_admin.getdepartmentDatauser();
	        dept_table.setItems(listM);
		}
		
		
	  
	    public void table() {
	     	dep_name.setCellValueFactory(new PropertyValueFactory<main_department, String>("department_name"));
	    	dep_code.setCellValueFactory(new PropertyValueFactory<main_department, String>("department_code"));
	    	dep_id.setCellValueFactory(new PropertyValueFactory<main_department, Integer>("id"));
	     	listM = mysqlconnect_admin.getdepartmentDatauser();
	        dept_table.setItems(listM);
	     }
	   

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			dep_name.setCellValueFactory(new PropertyValueFactory<main_department, String>("department_name"));
	    	dep_code.setCellValueFactory(new PropertyValueFactory<main_department, String>("department_code"));
	    	dep_id.setCellValueFactory(new PropertyValueFactory<main_department, Integer>("id"));

	    	table();
			
		}
	    
      

}
