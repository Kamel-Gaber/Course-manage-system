package application;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class MainController implements Initializable {
	@FXML
private Stage primaryStage;
private Scene scene;
private Parent root;

@FXML
private TableColumn<main_courses, String> m_code;

@FXML
private TableColumn<main_courses, Integer> m_dep;

@FXML
private TableColumn<main_courses, Integer> m_dr_id;

@FXML
private TableColumn<main_courses, Integer> m_hours;

@FXML
private TableColumn<main_courses, String> m_name;


@FXML
private TableColumn<main_courses, Integer> m_reg_stu;



@FXML
private TableView<main_courses> main_table;
    @FXML
    Button add1;
    
    @FXML
    private Button back;
    
    @FXML
    private Button edit;
    
    @FXML
    private RadioButton Current;

    @FXML
    private RadioButton Old;
    
    ObservableList<main_courses> listM;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	ToggleGroup tg = new ToggleGroup();
		Current.setToggleGroup(tg);
		Old.setToggleGroup(tg);
		if(Current.isSelected())
		{
			table();	
		}
	
        
    }    
       public void handle(ActionEvent event) {

    	   try {

          	 root = FXMLLoader.load(getClass().getResource("add_course.fxml"));
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
       public void Back_btn(ActionEvent event) {
    	   try {

            	 root = FXMLLoader.load(getClass().getResource("branches.fxml"));
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
       
       public void edit_btn(ActionEvent event) {

    	   try {

          	 root = FXMLLoader.load(getClass().getResource("modify.fxml"));
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
       
       public void table() {
    	 	m_code.setCellValueFactory(new PropertyValueFactory<main_courses, String>("course_code"));
    	 	m_name.setCellValueFactory(new PropertyValueFactory<main_courses, String>("course_name"));
    	 	m_hours.setCellValueFactory(new PropertyValueFactory<main_courses, Integer>("course_hours"));
    	 	m_dr_id.setCellValueFactory(new PropertyValueFactory<main_courses, Integer>("course_doctor"));
    	 	m_dep.setCellValueFactory(new PropertyValueFactory<main_courses, Integer>("course_dept"));
    	 	m_reg_stu.setCellValueFactory(new PropertyValueFactory<main_courses, Integer>("number_std"));
    	 	listM = mysqlconnect_admin.getcoursesData_Current();
    	    main_table.setItems(listM);
    	 }
       
 public void table_Current(ActionEvent event) {
 	m_code.setCellValueFactory(new PropertyValueFactory<main_courses, String>("course_code"));
 	m_name.setCellValueFactory(new PropertyValueFactory<main_courses, String>("course_name"));
 	m_hours.setCellValueFactory(new PropertyValueFactory<main_courses, Integer>("course_hours"));
 	m_dr_id.setCellValueFactory(new PropertyValueFactory<main_courses, Integer>("course_doctor"));
 	m_dep.setCellValueFactory(new PropertyValueFactory<main_courses, Integer>("course_dept"));
 	m_reg_stu.setCellValueFactory(new PropertyValueFactory<main_courses, Integer>("number_std"));
 	listM = mysqlconnect_admin.getcoursesData_Current();
    main_table.setItems(listM);
 }
 
 public void table_Old(ActionEvent event) {
	 	m_code.setCellValueFactory(new PropertyValueFactory<main_courses, String>("course_code"));
	 	m_name.setCellValueFactory(new PropertyValueFactory<main_courses, String>("course_name"));
	 	m_hours.setCellValueFactory(new PropertyValueFactory<main_courses, Integer>("course_hours"));
	 	m_dr_id.setCellValueFactory(new PropertyValueFactory<main_courses, Integer>("course_doctor"));
	 	m_dep.setCellValueFactory(new PropertyValueFactory<main_courses, Integer>("course_dept"));
	 	m_reg_stu.setCellValueFactory(new PropertyValueFactory<main_courses, Integer>("number_std"));
	 	listM = mysqlconnect_admin.getcoursesData_Old();
	    main_table.setItems(listM);
	 }
       
       
}
