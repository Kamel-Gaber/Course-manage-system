
package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Set;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class St_WelcomController extends Login_PageController implements Initializable {
   /////////////////////////////////////
	Login_PageController log = new Login_PageController();
	 String email = log.who2;
	 @FXML
	   private Stage primaryStage;
	   private Scene scene;
	   private Parent root;
   private static Connection connect;
   private PreparedStatement prest;
   private ResultSet result;
   static int student_id ;
   static int course_id ;
   static int dept_id ; 
   public static int Doc_id;
   public static int Dept_id;
   public static String Doc_N ;
   public static String Dept_N ;
   int index = - 1;
   public static String n;

    ObservableList<Data> listM;
   /////////////////////////////////////
    @FXML
    private TableColumn<Data, String> course_code;

    @FXML
    private TableColumn<Data, String> course_name;

    @FXML
    private TableColumn<Data, Integer> course_hour;

    @FXML
    private TableView<Data> st_tabel;

    @FXML
    private Button Back;
    @FXML
    private Button REG;
    
    @FXML
    void select(MouseEvent event) throws IOException {
index = st_tabel.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        root = FXMLLoader.load(getClass().getResource("Course_Student.fxml"));
    	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    
    @FXML
    void to_rgister(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("register.fxml"));
      	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
      	scene = new Scene(root);
      	primaryStage.setScene(scene);
      	primaryStage.show();
    }
    @FXML
    void Back(ActionEvent event) throws IOException {
    	 root = FXMLLoader.load(getClass().getResource("Login_Page.fxml"));
      	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
      	scene = new Scene(root);
      	primaryStage.setScene(scene);
      	primaryStage.show();
    }
    
        public void getStudentIdByEmail(String email) {
        String query = "SELECT id FROM students WHERE email = ?";
        try (PreparedStatement stmt = connect.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                student_id = rs.getInt("id"); 
            } 
        } catch (SQLException e) {
          e.printStackTrace();

        }
     
    }
    public int getcourseIdBy_st_id(int id) {
        String query = "SELECT course_id FROM registration WHERE student_id = ?";
        try (PreparedStatement stmt = connect.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("course_id"));
                course_id = rs.getInt("course_id");
               
            }
        } catch (SQLException e) {
           e.printStackTrace();

        }
        return course_id;
    }
    
   
public static ObservableList<Data> getDataSTUDENTS(int id ){
     
ObservableList<Data> list =  FXCollections.observableArrayList();
String query = "SELECT  course_id  FROM fci_registration.registration WHERE student_id  = ?";
try (PreparedStatement prest = connect.prepareStatement(query)) {
            prest.setInt(1, id);
            ResultSet rs = prest.executeQuery();
            while (rs.next()) {
                course_id = rs.getInt("course_id");
                java.sql.PreparedStatement ps = connect.prepareStatement("select course_name,course_code,course_hours from fci_registration.courses where id = '"+course_id+"' ");
                ResultSet  rs2 = ps.executeQuery();
               while (rs2.next()) {                    
                   list.add(new Data(rs2.getString("course_name"),rs2.getString("course_code"),rs2.getInt("course_hours")));
               }
            }
        } catch (SQLException e) {
           e.printStackTrace();
            
        }

            return list;
        }
   
   
    
     
     
     
     
   
    
    public void insert_data_to_tabel() {
        course_name.setCellValueFactory(new PropertyValueFactory<Data, String>("course_name"));
        course_code.setCellValueFactory(new PropertyValueFactory<Data, String>("course_code"));
        course_hour.setCellValueFactory(new PropertyValueFactory<Data, Integer>("course_hours"));
        
        listM = getDataSTUDENTS(student_id);
        st_tabel.setItems(listM);
        
    }

    public void get(){
        connect = mysqlconnect.connectDb();
        getStudentIdByEmail(email);
        getcourseIdBy_st_id(student_id);
        System.out.println(course_id);
        getDataSTUDENTS(student_id );
        insert_data_to_tabel();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         get();
        
        
    }

    
}
