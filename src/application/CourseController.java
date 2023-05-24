package application;

import static application.mysqlconnect.connectDb;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;


public class CourseController extends Login_PageController   implements Initializable {
	public Login_PageController log = new Login_PageController();
	String who_email = log.who;
	  FileChooser chooser = new FileChooser();
	  File f;
	  String fileName;
	  String filename;
	  String path;
	  String newpath;
	  String fileHash;
	  Blob blob;
	  int id_up=0;
	  int course_i;
	   String name_course;
	  static String cell;
	  InputStream in;
	  InputStream input;
	  OutputStream output;
	  ResultSet rs;
	  PreparedStatement pst;
	  Statement st;
	 static int id =0 ;
	  int id_student =0;
	 ObservableList<Data_courses> listM;
	int index = -1;
	  ObservableList<Files_Table> show = FXCollections.observableArrayList();
	  ObservableList<Students_Table> show2 = FXCollections.observableArrayList();
	  ObservableList<Students_Table> show4 = FXCollections.observableArrayList();
	  ObservableList<String> show3 = FXCollections.observableArrayList();

    @FXML
    private Pane BASE;

    @FXML
    private Button Back;

    @FXML
    private Button Delete;

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
    private TableColumn<Students_Table, Double> grades;

    @FXML
    private TableColumn<Students_Table, String> last_name;
    
    @FXML
    private Pane students_pane;

    @FXML
    private TableView<Students_Table> students_table;

    @FXML
    private Button Make;

    @FXML
    private Button Students;

    @FXML
    private Button Upload;

    @FXML
    private TableColumn<Data_courses, String> course_column;

   

    @FXML
    private Label email;

    @FXML
    private TableView<Data_courses> my_courses;

    @FXML
    private Label name;
    
    @FXML
    private TextField t1;
    
	@FXML
private Stage primaryStage;
private Scene scene;
private Parent root;
    

    @FXML
    void Back(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("Doctor_page.fxml"));
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
    void getSelected2(MouseEvent event) throws SQLException {
    	database con = new database();
    	Connection con1 = con.fileConnection();
    	index = students_table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        int k = accadimic_number.getCellData(index);
        String sql3 = "SELECT  id FROM fci_registration.students where accadimic_number = '"+k+"'  ";
		 pst = con1.prepareStatement(sql3);
	        rs = pst.executeQuery(sql3);
        while(rs.next()) {
        	id_up = rs.getInt(1);
        	System.out.println("xxxxxxxxxxxxxxxxxxxxxx "+id_up);
        }
    }
   

    @FXML
    void Delete(ActionEvent event) throws SQLException {
    	database con = new database();
    	Connection con1 = con.fileConnection();
    	String del = t1.getText();
    	 String sql2 ="delete from fci_registration.files where file_name = '"+del+"'";
         pst = con1.prepareStatement(sql2);
         pst.executeUpdate(sql2);
         t1.setText(null);
         get();
    }

    @FXML
    void Show_students(ActionEvent event) throws SQLException {
    	std_info();
    	students_pane.setVisible(true);
    	try {
    	database con = new database();
    	Connection con1;
    	con1 = con.fileConnection();
		 String sql3 = "SELECT  student_id FROM fci_registration.registration where course_id = '"+get_id_course(name_course)+"' ";
		 System.out.println("course auot matic "+name_course);
		 System.out.println(get_id_course(name_course));
		 pst = con1.prepareStatement(sql3);
	        rs = pst.executeQuery(sql3);
	        show2.clear();
		      while(rs.next()) {
		    	  String n = rs.getString(1);
		    	  id_student = Integer.parseInt(n);
		    	  System.out.println("ttttttttttttttttttttttttttttttttttt "+id_student);
		 String sql = "SELECT  first_name, last_name, email, accadimic_number, gpa FROM fci_registration.students where id = '"+id_student+"' ";
		 Statement s = con1.createStatement();
		 ResultSet r =s.executeQuery(sql);
		 String sql4 = "SELECT  degree FROM fci_registration.grades where course_id = '"+get_id_course(name_course)+"' and student_id = '"+id_student+"'  ";
		 Statement s1 = con1.createStatement();
		 ResultSet r1 =s1.executeQuery(sql4);
		 while(r1.next()) {
		 while(r.next()) {
			 show2.add(new Students_Table(
					 r.getString(1),
					 r.getString(2),
					 r.getString(3),
					 r.getInt(4),
					 r.getDouble(5),
					 r1.getDouble(1)
					 ));
			
		                  }
		 }
		         students_table.setItems(show2); 
		         
 
		      }
	} catch (SQLException e) {
	 System.out.println("Failed");
		e.printStackTrace();
	}
	 
	first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
	last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
	email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
	accadimic_number.setCellValueFactory(new PropertyValueFactory<>("accadimic_number"));
	gpa.setCellValueFactory(new PropertyValueFactory<>("gpa"));
	grades.setCellValueFactory(new PropertyValueFactory<>("degree"));
	students_table.setEditable(true);
	grades.setEditable(true);
	grades.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));        //degree.setCellValueFactory(new PropertyValueFactory<Students_Table, Double>("degree"));
    }
    @FXML
    public void onEditChanged(TableColumn.CellEditEvent<Students_Table,Double> Students_TableDoubleCellEditEvent) throws SQLException{
    	 database con = new database();
         Connection con1 = con.fileConnection();
         

	        Students_Table student = students_table.getSelectionModel().getSelectedItem();
	        student.setDegree(Students_TableDoubleCellEditEvent.getNewValue());
	        TablePosition<Students_Table, Double> pos = Students_TableDoubleCellEditEvent.getTablePosition();
	        int row = pos.getRow();

		    	   System.out.println("idddddddd"+id_up);
		    	   Double newValue = Students_TableDoubleCellEditEvent.getNewValue();
				      String sql = "update grades set degree ='"+newValue+"' where course_id = '"+get_id_course(name_course)+"' and student_id = '"+id_up+"'";
					   PreparedStatement stmt = con1.prepareStatement(sql);
					   stmt.executeUpdate();
					   passed(id_up);
				   

		     
		      
		    
    }
    
    


    @FXML
    void Upload(ActionEvent event) throws Exception {
    	//////////////Choose the file you want to upload//////////////
    	f = chooser.showOpenDialog(null);
        fileName = f.getName();
        String fileType ; 
        filename = f.getAbsolutePath();
        path = filename;
        newpath = path.replace('\\', '/');
        System.out.println(newpath);
        t1.setText(fileName);
        fileHash = calculateHash(newpath);
        ////////////////////////save it to database/////////////////
        database con = new database();
    	Connection con1 = con.fileConnection();
    	File pdfFile = new File(newpath);
    	System.out.println(newpath);
    	byte[] pdfData = new byte[(int) pdfFile.length()];
    	DataInputStream dis = new DataInputStream(new FileInputStream(pdfFile));
    	dis.readFully(pdfData); 
    	dis.close();
    	////////////////////////////
    	String sql1 ="select doctor_id from fci_registration.doctors where email = '"+email.getText()+"'";
        pst = con1.prepareStatement(sql1);
        rs = pst.executeQuery(sql1);
	      while(rs.next()) {
	    	  String n = rs.getString(1);
	    	  id = Integer.parseInt(n);
	      }
	      System.out.println(id);
	      String sql2 = "INSERT INTO `fci_registration`.`files` (file_name, file,course_id, file_hash) VALUES (?, ?, ?, ?)";
	    	PreparedStatement ps = con1.prepareStatement(sql2);
	    	ps.setString(1,fileName);
	    	ps.setBinaryStream(2, new ByteArrayInputStream(pdfData), pdfData.length);
	    	ps.setInt(3, get_id_course(name_course));
	    	ps.setString(4,fileHash);
	    	ps.executeUpdate();
        
        ///////// update table ////////
	    	
        get();
    }
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
    @FXML
    void show_files(ActionEvent event) {
    	Files_Pane.setVisible(true);
    	students_pane.setVisible(false);
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
        getfDatausers(id_get_file);
    
    }
    
    ///////// the method that update table//////
    public  ObservableList<Files_Table> getDatausers(){///
            ObservableList<Files_Table> list =  FXCollections.observableArrayList();
            try {
            	database con = new database();
            	Connection con1 = con.fileConnection();
                java.sql.PreparedStatement ps = con1.prepareStatement("select file_name from fci_registration.files where course_id = '"+get_id_course(name_course)+"' ");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {                    
                    list.add(new Files_Table(rs.getString(1)));
                }
            } catch (Exception e) {
            }
            return list;
        }
    ///////////////////////////////////////////////////
    
    public  ObservableList<Files_Table> getfDatausers(int id){
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
    
///////// the method that call getDatausers and update table//////
    public void get() {
		Files_table.getSelectionModel().setCellSelectionEnabled(true);
		Files_head.setCellValueFactory(new PropertyValueFactory<>("filename"));	
		show=getDatausers();
		Files_table.setItems(show);
		
	}
		
/////////////////////////
public void UpdateTable() {
course_column.setCellValueFactory(new PropertyValueFactory<Data_courses, String>("CourseName"));
listM = mysqlconnect.getDatausers();
my_courses.setItems(listM);
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
			  String sql1 ="select first_name from fci_registration.doctors where email = '"+who+"' ";
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
///////////end of getting doc info ////////////////
		//// getting files//////
		
		String sql1 ="select doctor_id from fci_registration.doctors where email = '"+email.getText()+"'";
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
	    	  String sql3 ="select file_name from fci_registration.files where course_id = '"+id+"' ";
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
		/////display doc info//////////
		doc_info();

		UpdateTable();
		
		
		
	
	}
	
	public void std_info() throws SQLException {
		database con = new database();
    	Connection con1 ;
    	con1=con.fileConnection();
    	String sql3 = "SELECT  student_id FROM fci_registration.registration where course_id = '"+get_id_course(name_course)+"' ";
		 pst = con1.prepareStatement(sql3);
	        rs = pst.executeQuery(sql3);
	        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhh444 "+name_course);
	        
	        while(rs.next()) {
	        	int id = rs.getInt(1);
		String sql5 = "INSERT INTO `fci_registration`.`grades` (student_id, course_id)\n"
				+ "SELECT ?, ? FROM dual\n"
				+ "WHERE NOT EXISTS (\n"
				+ "  SELECT 1\n"
				+ "  FROM `fci_registration`.`grades`\n"
				+ "  WHERE student_id = ? AND course_id = ?\n"
				+ "); ";
    	PreparedStatement ps = con1.prepareStatement(sql5);
    	ps.setInt(1,id );
    	ps.setInt(2, get_id_course(name_course));
    	ps.setInt(3,id );
    	ps.setInt(4, get_id_course(name_course));
    	ps.executeUpdate();
	        }
	
	}
	
	
	public  void doc_info(){
		try {
		database con = new database();
    	Connection con1 ;
    	con1=con.fileConnection();
    	ResultSet rs;
		PreparedStatement pst;
	    
	    
	         java.sql.PreparedStatement ps = con1.prepareStatement("select first_name from fci_registration.doctors where email = '"+who_email+"'");
	                 rs = ps.executeQuery();
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
	
    public void passed(int id ) throws SQLException {
    	database con = new database();
    	Connection con1 ;
    	con1=con.fileConnection();
    	double check;
    	String sql2 ="select degree from grades where course_id = '"+get_id_course(name_course)+"' and student_id = '"+id+"' ";
	      pst = con1.prepareStatement(sql2);
	      rs = pst.executeQuery(sql2);
	      while(rs.next()) {
	    	check = rs.getDouble(1);
	    	if(check >=50) {
	    		String sql3 = "update fci_registration.grades set passed = true where course_id = '"+get_id_course(name_course)+"' and student_id = '"+id+"' ";
		    	PreparedStatement ps = con1.prepareStatement(sql3);
		    	ps.executeUpdate();
	    	}else {
	    		String sql4 = "update fci_registration.grades set passed = false where course_id = '"+get_id_course(name_course)+"' and student_id = '"+id+"' ";
		    	PreparedStatement ps = con1.prepareStatement(sql4);
		    	ps.executeUpdate();
	    		
	    	}
	      }
    	
    }

}
