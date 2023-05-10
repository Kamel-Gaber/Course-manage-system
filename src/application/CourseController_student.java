package application;

import static application.mysqlconnect.connectDb;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mysql.cj.jdbc.Blob;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CourseController_student extends Login_PageController   implements Initializable {
	public Login_PageController log = new Login_PageController();
	
	
	String who_email = log.who2;
	  FileChooser chooser = new FileChooser();
	  File f;
	  String fileName;
	  String filename;
	  String path;
	  String newpath;
	  int x;
	 static String name_course;
	 static int course;
	  Blob blob;
	  static String cell;
	  InputStream in;
	  InputStream input;
	  OutputStream output;
	  ResultSet rs;
	  PreparedStatement pst;
	  Statement st;
	static  int id =0 ;
	  int course_i =0 ;
	 int the_id_std;
	 ObservableList<Data> listM;
	int index = -1;
	  ObservableList<Files_Table> show = FXCollections.observableArrayList();
	  ObservableList<Students_Table> show2 = FXCollections.observableArrayList();
	  ObservableList<String> show3 = FXCollections.observableArrayList();

    @FXML
    private Pane BASE;

    @FXML
    private Button Back;


    @FXML
    private Button Files1;

    @FXML
    private Pane Files_Pane;

    @FXML
    private TableColumn<Files_Table, String> Files_head = new TableColumn<>();

    @FXML
    private TableView<Files_Table> Files_table;
    
    @FXML
    private TableColumn<Students_Table, Integer> accadimic_number;
    
    @FXML
    private TableColumn<Students_Table, String> email_col;

    @FXML
    private TableColumn<Students_Table, String> first_name;

    @FXML
    private TableColumn<Students_Table, Double> gpa;

    @FXML
    private TableColumn<Students_Table, String> last_name;
    
    @FXML
    private Pane students_pane;

    @FXML
    private TableView<Students_Table> students_table;

   

    @FXML
    private Button Students;

    @FXML
    private Button Download;

    @FXML
    private TableColumn<Data, String> course_column;

   

    @FXML
    private Label email;

    @FXML
    private TableView<Data> my_courses;

    @FXML
    private Label name;
    
    @FXML
    private TextField t1;
    
	@FXML
private Stage primaryStage;
private Scene scene;
private Parent root;
    
	////////// Check the file ////////
public String calculateHash(String filePath) throws Exception {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hash = md.digest(Files.readAllBytes(Paths.get(filePath)));
    StringBuilder sb = new StringBuilder();
    for (byte b : hash) {
        sb.append(String.format("%02x", b));
    }
    System.out.println(sb.toString());
    return sb.toString();
}
private boolean isFileCorrupted(String filePath, String hashFromDatabase) throws Exception {
    String hashOfFile = calculateHash(filePath);
    return hashOfFile.equals(hashFromDatabase);
}
////////////////////////////////////

	
	
////////////////////////////////////
    @FXML
    void Back(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("st_Welcom.fxml"));
    	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    
    @FXML
    void getSelected(MouseEvent event) {
        index = Files_table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        t1.setText(Files_head.getCellData(index).toString());
    }

 

    @FXML
    void show_files(ActionEvent event) {
    	Files_Pane.setVisible(true);
    }
    @FXML
    void select(MouseEvent event) throws IOException {
index = my_courses.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        
        name_course= course_column.getCellData(index).toString();
        System.out.println("name:"+name_course);
        int id_get_file =get_id_course(name_course);
        System.out.println("id "+id_get_file);
        getDatausers(id_get_file);
    }
    
    ///////// the method that update table//////
    public  ObservableList<Files_Table> getDatausers(int id){
            ObservableList<Files_Table> list =  FXCollections.observableArrayList();
            try {
            	database con = new database();
            	Connection con1 = con.fileConnection();
                java.sql.PreparedStatement ps = con1.prepareStatement("select file_name from fci_registration.files where course_id = '"+id+"' ");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {                    
                    list.add(new Files_Table(rs.getString(1)));
                }
                Files_table.setItems(list);
            } catch (Exception e) {
            }
            Files_table.getSelectionModel().setCellSelectionEnabled(true);
    		Files_head.setCellValueFactory(new PropertyValueFactory<>("filename"));	
            return list;
        }

	
/////////////////////////
public void UpdateTable() {
course_column.setCellValueFactory(new PropertyValueFactory<Data, String>("course_name"));
listM = mysqlconnect.getDatausers2();
my_courses.setItems(listM);
}

@FXML
void Download(ActionEvent event) throws IOException, SQLException {
	//////////////Choose the file you want to Download//////////////
    try
    {database con = new database();
	Connection con1 = con.fileConnection();
    	st = con1.createStatement();
    	int index = -1;
    	index = Files_table.getSelectionModel().getSelectedIndex();
    	String f =Files_head.getCellData(index).toString();
    	System.out.println(f);
    	String sql = "Select file ,file_hash from files where `file_name`='"+f+"'and course_id='"+get_id_course(name_course)+"';";
    	PreparedStatement ps = con1.prepareStatement(sql);
    	rs = ps.executeQuery();
    	System.out.println(id);
    	if (rs.next()) {
            InputStream inputStream = rs.getBinaryStream("file");

            // Write the file data to a local file
            FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.home") + "/Downloads/" + f);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
            	System.out.println("Read " + bytesRead + " bytes from the input stream.");
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
            boolean isFileCorrupted = isFileCorrupted(System.getProperty("user.home") + "/Downloads/" + f, rs.getString("file_hash"));
            if (isFileCorrupted) {
                System.out.println("File downloaded successfully and is not corrupted.");
            } else {
                System.out.println("File downloaded successfully but is corrupted.");
            }
    }
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    }
}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		///////////start of getting doc info ////////////////
		
		database con = new database();
    	Connection con1;
    	ResultSet rs;
		  PreparedStatement pst;
		 
		  
		try {
			con1 = con.fileConnection();
			  String sql1 ="select first_name from fci_registration.students where email = '"+who2+"' ";
		      pst = con1.prepareStatement(sql1);
		      rs = pst.executeQuery(sql1);
		      while(rs.next()) {
		      String n = rs.getString(1);
		      name.setText(n);
		     
		      }
		      String s = name.getText();
		      String sql2 ="select email from fci_registration.doctors where first_name = '"+s+"' ";
		      pst = con1.prepareStatement(sql2);
		      rs = pst.executeQuery(sql2);
		     
		      while(rs.next()) {
		      String e = rs.getString(1);
		      email.setText(e);
		      }		
		} catch (SQLException e) {
			e.printStackTrace();
		}
///////////end of getting std info ////////////////
		//// getting files//////
		
		String sql1 ="select id from fci_registration.students where email = '"+email.getText()+"'";
        try {con1 = con.fileConnection();
			pst = con1.prepareStatement(sql1);
			rs = pst.executeQuery(sql1);
		      while(rs.next()) {
		    	  String n = rs.getString(1);
		    	  id = Integer.parseInt(n);
		      }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	      try {
	    	  con1 = con.fileConnection();
	    	  String sql3 ="select file_name from fci_registration.files where course_id = '"+get_id_course(name_course)+"' ";
	    	  pst = con1.prepareStatement(sql3);
			  rs = pst.executeQuery(sql3);
		      while(rs.next()) {
		    	  show.add(new Files_Table(
							rs.getString(1))
		    			  );
		      }		
			Files_table.setItems(show);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		Files_table.getSelectionModel().setCellSelectionEnabled(true);
		Files_head.setCellValueFactory(new PropertyValueFactory<>("filename"));	
		////////////////////////////////
		/////display stud info//////////
		
		stud_info();
		 x =get_id_Student();
		System.out.println("xx = "+x);
		get_id_course(name_course);
		UpdateTable();
	
	}
	public  void stud_info(){
		try {
		database con = new database();
    	Connection con1 ;
    	con1=con.fileConnection();
    	ResultSet rs;
	         java.sql.PreparedStatement ps = con1.prepareStatement("select id ,first_name from fci_registration.students where email = '"+who_email+"'");
	                 rs = ps.executeQuery();
	                while (rs.next()) {                    
	             String Name = rs.getString("first_name"); 
	             String Email = who_email;   
	             the_id_std=rs.getInt(1);
	               name.setText(Name);
	               email.setText(Email);
	                }
	            } catch (Exception e) {
	            	System.out.println("Something Wrong");
	            	e.printStackTrace();
	            	
	            }
	    }
	////////////////////////////////////////////
	public int get_id_Student() {
		try {
			database con = new database();
			Connection con1 ;
			con1=con.fileConnection();
			ResultSet rs;
		         java.sql.PreparedStatement ps = con1.prepareStatement("SELECT id FROM students WHERE email = '"+who_email+"'");
		                 rs = ps.executeQuery();
		                while (rs.next()) {                    
		                	the_id_std = rs.getInt(1);
		                }
		            } catch (Exception e) {
		            	System.out.println("Something Wrong");
		            	e.printStackTrace();
		            }
		return the_id_std;
		}
		///////////////////////////////////
			public int get_id_course(String n) {
			try {
				database con = new database();
		    	Connection con1 ;
		    	con1=con.fileConnection();
		    	ResultSet rs;
			         java.sql.PreparedStatement ps = con1.prepareStatement("SELECT id FROM courses WHERE course_name = '"+n+"'");
			                 rs = ps.executeQuery();
			                while (rs.next()) {                    
			             course_i = rs.getInt(1);
			             System.out.println("DD"+ course_i);
			                }
			            } catch (Exception e) {
			            	System.out.println("Something Wrong");
			            	e.printStackTrace();
			            }
			
			return course_i;
		}

	
    

}
