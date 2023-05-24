package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

//import application.Course_T;
import application.mysqlconnect_admin;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class modifyController implements Initializable {
	
	 
	    PreparedStatement pst1 ;
	  
	
	Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ResultSet rs2 = null;
    PreparedStatement pst2 = null;
    static int id_for_course;
	Alert alert = new Alert(AlertType.WARNING);

	@FXML
private Stage primaryStage;
private Scene scene;
private Parent root;
@FXML
private TableView<courses> table_courses;
    @FXML
    private Button bck;
    
    @FXML
    private TextField id_field;
    
    @FXML
    private TextField old1;

    @FXML
    private TextField old2;

    @FXML
    private TextField old3;

    @FXML
    private TableColumn<courses, String> c_code;

    @FXML
    private TableColumn<courses, Integer> c_hours;

    @FXML
    private TableColumn<courses, String> c_name;



    @FXML
    private TextField code_feild;

    @FXML
    private Button del;

    @FXML
    private TextField dep_feild;

    @FXML
    private TableColumn<courses, Integer> dep_id;

    @FXML
    private TableColumn<courses, Integer> dr_id;

    @FXML
    private TextField dr_id_feild;

    @FXML
    private TextField hours_feild;

    @FXML
    private TextField name_feild;

    @FXML
    private TableColumn<courses, Integer> num_reg;

    @FXML
    private TextField num_stu_feild;

  

    @FXML
    private Button up_date;
    
    
    //////////////////////
    @FXML
    private TableView<courses> table1;
    
    @FXML
    private TableColumn<courses, String> cou_code;

    @FXML
    private TableColumn<courses, String> cou_name;
   
    @FXML
    private Button pre;

    @FXML
    private TextField req1 ;

    @FXML
    private TextField req2 ;

    @FXML
    private TextField req3 ;
    
    @FXML
    private TextField req11 ;

    @FXML
    private TextField req21 ;

    @FXML
    private TextField req31;
    
    //////////////////////
    

    ObservableList<courses> listM;
    @FXML
    void btn_del(ActionEvent event) {
    	try {
    		int exist=0;
    	conn = mysqlconnect_admin.connectDb();
    	String sql2 = "select num_reg_students from fci_registration.courses where id = '"+get_id()+"'  ";
        pst = conn.prepareStatement(sql2);
       rs =  pst.executeQuery();
       while(rs.next()) {
    	    exist = rs.getInt(1);
       }
    	if(exist >0) {
    		 alert.setTitle("Wait");
        		alert.setHeaderText(null);
        		alert.setContentText("Can't Delete this Course \n there is "+exist+" students registerd it");
        		alert.showAndWait();
    		
    	}else {
        String value0 = code_feild.getText();
        String value1 = dep_feild.getText();
        String value2 = dr_id_feild.getText();
        String value3 =  hours_feild.getText();
        String value4 = name_feild.getText();
        String value5 = num_stu_feild.getText();
        
//        String sql = "delete from fci_registration.courses where course_name ='"+value4+"' and course_code = '"+value0+"'";
        String sql = "update fci_registration.courses set deleted = TRUE where id = '"+get_id()+"' ";

        pst = conn.prepareStatement(sql);
        pst.execute();
        up_date();
        code_feild.setText(null);
        dep_feild.setText(null);
        dr_id_feild.setText(null);
        hours_feild.setText(null);
        name_feild.setText(null);
        num_stu_feild.setText(null);
      
    	}
    	}
    	
    catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
    
    public void del() {

    	c_name.setCellValueFactory(new PropertyValueFactory<courses, String>("course_name"));
    	c_code.setCellValueFactory(new PropertyValueFactory<courses, String>("course_code"));
    	c_hours.setCellValueFactory(new PropertyValueFactory<courses, Integer>("course_hours"));
    	dr_id.setCellValueFactory(new PropertyValueFactory<courses, Integer>("course_doctor"));
    	dep_id.setCellValueFactory(new PropertyValueFactory<courses, Integer>("course_dept"));
    	num_reg.setCellValueFactory(new PropertyValueFactory<courses, Integer>("number_std"));
        listM = mysqlconnect_admin.getmodifyData();
        table_courses.setItems(listM);
    }
    
    public int get_id() throws SQLException {
    	conn = mysqlconnect_admin.connectDb();
    	String value0 = code_feild.getText();
    	  String sql2 = "select id from fci_registration.courses where course_code = '"+value0+"' ";
          pst2 = conn.prepareStatement(sql2);
        rs2 =  pst2.executeQuery();
        while(rs2.next()) {
        	id_for_course = rs2.getInt(1);
        	System.out.println("id_for_course "+id_for_course);
        }
    	return id_for_course;
    }

    @FXML
    void update_btn(ActionEvent event) {
    	 try {
             conn = mysqlconnect_admin.connectDb();
            int get = get_id();
             String value0 = code_feild.getText();
             String value1 = dep_feild.getText();
             String value2 = dr_id_feild.getText();
             String value3 =  hours_feild.getText();
             String value4 = name_feild.getText();
             String value5 = num_stu_feild.getText();
           
             String sql = "update fci_registration.courses set course_name= '"+ value4 + "',course_code= '" + value0 + "',course_hours= '" +value3 + "',course_doctor_id= '" + value2 + "',department_id= '" +value1 + "',num_reg_students= '"+value5+"' where id = '"+get+"' ";
             pst = conn.prepareStatement(sql);
             pst.execute();
             up_date();
         } catch (Exception e) {
            e.printStackTrace();
         }

    	 table1.setDisable(false);
    	 
    }
    int index = - 1;
    @FXML
    void getSelected(MouseEvent event) throws SQLException {
        index = table_courses.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        name_feild.setText(c_name.getCellData(index).toString());
        code_feild.setText(c_code.getCellData(index).toString());
        hours_feild.setText(c_hours.getCellData(index).toString());
        dr_id_feild.setText(dr_id.getCellData(index).toString());
        dep_feild.setText(dep_id.getCellData(index).toString());
        num_stu_feild.setText(num_reg.getCellData(index).toString());
        get_old_request();
    }
    /////////////////////////////
    @FXML
    void getSelected2(MouseEvent event) throws SQLException {
        index = table1.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        
    System.out.println("hghhhhhhhhh"+req1.getText());
   if(req1.getText().equals("Empty")&& !req2.getText().equals("Empty")&& !req3.getText().equals("Empty")) {
	   req1.setText(cou_code.getCellData(index).toString());
   	   req11.setText(cou_name.getCellData(index).toString());
   	   req2.setText("Empty");
   }else if(!req1.getText().equals("Empty")&& req2.getText().equals("Empty")&& !req3.getText().equals("Empty")) {
	   req2.setText(cou_code.getCellData(index).toString());
   	   req21.setText(cou_name.getCellData(index).toString());
   	   req3.setText("Empty");
   }else if(!req1.getText().equals("Empty")&& !req2.getText().equals("Empty")&& req3.getText().equals("Empty")) {
	   req3.setText(cou_code.getCellData(index).toString());
   	   req31.setText(cou_name.getCellData(index).toString());
   }
        get_old_request();
   pre.setDisable(false);
        
  
    }
    
    
    @FXML
    void pre_btn(ActionEvent event) {
   	 try {
   		
   		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM fci_registration.requests WHERE course_id = ?");
   	    stmt.setInt(1, get_id()); // Set the value of the parameter to the desired ID
   	    ResultSet rs = stmt.executeQuery();
   	    boolean there_is_data = rs.next();
   		 
   		 if(there_is_data) {
   			 System.out.println("there exist data");
   		int course_id;
   		String course_Name;
   		int id_req;
   		String name_req;
   		 int pre_id1,pre_id2,pre_id3;
            conn = mysqlconnect_admin.connectDb();
           String query1 = "select id , course_name from fci_registration.courses where course_code = '"+code_feild.getText()+"'";
           pst1 = conn.prepareStatement(query1);
         rs =  pst1.executeQuery();
           while (rs.next()) {
           	course_id = rs.getInt(1);
           	course_Name = rs.getString(2);
           	System.out.println("KOOOK "+course_id);
           	System.out.println("KOOOK "+course_Name);

           //////////////////////
           if(!req1.getText().equals("Empty")) {
           String query2 = "select id  from fci_registration.courses where course_code = '"+req1.getText()+"'";
           pst1 = conn.prepareStatement(query2);
           ResultSet rs2;
           rs2 =  pst1.executeQuery();
           while (rs2.next()) {
           	pre_id1 = rs2.getInt(1);
           	
           	System.out.println("pre 1 "+pre_id1);
           	String sql1 ="UPDATE fci_registration.requests SET course_name = ?, course_id_pre_request = ? , course_name_pre = ? WHERE course_id = ? and course_id_pre_request = '"+old1.getText()+"'  ";        
               pst1 = conn.prepareStatement(sql1);
               pst1.setString(1, course_Name);
               pst1.setInt(2, pre_id1);
               pst1.setString(3, req11.getText() );
               pst1.setInt(4, course_id);
               pst1.executeUpdate();
           }
           }
           
           /////////////////////
           if(!req2.getText().equals("Empty")) {
           String query3 = "select id  from fci_registration.courses where course_code = '"+req2.getText()+"'";
           pst1 = conn.prepareStatement(query3);
           ResultSet rs3;
           rs3 =  pst1.executeQuery();
           while (rs3.next()) {
           	pre_id2 = rs3.getInt(1);
           	
           	System.out.println("pre 2 "+pre_id2);
           	String sql1 ="UPDATE fci_registration.requests SET course_name = ?, course_id_pre_request = ? ,course_name_pre = ? WHERE course_id = ? and course_id_pre_request = '"+old2.getText()+"'";        
            pst1 = conn.prepareStatement(sql1);
            pst1.setString(1, course_Name);
            pst1.setInt(2, pre_id2);
            pst1.setString(3, req21.getText());
            pst1.setInt(4, course_id);
            pst1.executeUpdate();
           }
           }
           ///////////////////
           if(!req3.getText().equals("Empty")) {
           String query4 = "select id  from fci_registration.courses where course_code = '"+req3.getText()+"'";
           pst1 = conn.prepareStatement(query4);
           ResultSet rs4;
           rs4 =  pst1.executeQuery();
           while (rs4.next()) {
           	pre_id3 = rs4.getInt(1);
           	
           	System.out.println("pre 3 "+pre_id3);
           	String sql1 ="UPDATE fci_registration.requests SET course_name = ?, course_id_pre_request = ? ,course_name_pre = ? WHERE course_id = ? and course_id_pre_request = '"+old3.getText()+"'";        
            pst1 = conn.prepareStatement(sql1);
            pst1.setString(1, course_Name);
            pst1.setInt(2, pre_id3);
            pst1.setString(3, req31.getText());
            pst1.setInt(4, course_id);
            pst1.executeUpdate();
           }
           }
          
           
           
           
            }
           }else {
        	   int course_id;
       		String course_Name;
       		 int pre_id1,pre_id2,pre_id3;
                conn = mysqlconnect_admin.connectDb();
               String query1 = "select id , course_name from fci_registration.courses where course_code = '"+code_feild.getText()+"'";
               pst1 = conn.prepareStatement(query1);
             rs =  pst1.executeQuery();
               while (rs.next()) {
               	course_id = rs.getInt(1);
               	course_Name = rs.getString(2);
               
               //////////////////////
               if(!req1.getText().equals("Empty")) {
               String query2 = "select id ,course_name  from fci_registration.courses where course_code = '"+req1.getText()+"'";
              
               pst1 = conn.prepareStatement(query2);
               rs =  pst1.executeQuery();
               while (rs.next()) {
               	pre_id1 = rs.getInt(1);
               	String first_course_request = rs.getString(2);
               	System.out.println("pre 1 "+pre_id1);
               	String sql1 ="insert into fci_registration.requests (course_id, course_name, course_id_pre_request,course_name_pre) values (?,?,?,?)";         
                   pst1 = conn.prepareStatement(sql1);
                   pst1.setInt(1, course_id);
                   pst1.setString(2, course_Name);
                   pst1.setInt(3, pre_id1);
                   pst1.setString(4, first_course_request);
                   pst1.executeUpdate();
               }
               }
               /////////////////////
               if(!req2.getText().equals("Empty")) {
               String query3 = "select id ,course_name  from fci_registration.courses where course_code = '"+req2.getText()+"'";
               pst1 = conn.prepareStatement(query3);
               rs =  pst1.executeQuery();
               while (rs.next()) {
               	pre_id2 = rs.getInt(1);
               	String second_course_request = rs.getString(2);
               	System.out.println("pre 2 "+pre_id2);
               	String sql1 ="insert into fci_registration.requests (course_id, course_name, course_id_pre_request,course_name_pre) values (?,?,?,?)";         
                   pst1 = conn.prepareStatement(sql1);
                   pst1.setInt(1, course_id);
                   pst1.setString(2, course_Name);
                   pst1.setInt(3, pre_id2);
                   pst1.setString(4, second_course_request);
                   pst1.executeUpdate();
               }
               }
               ///////////////////
               if(!req3.getText().equals("Empty")) {
               String query4 = "select id , course_name  from fci_registration.courses where course_code = '"+req3.getText()+"'";
               pst1 = conn.prepareStatement(query4);
               rs =  pst1.executeQuery();
               while (rs.next()) {
               	pre_id3 = rs.getInt(1);
               	String third_course_request = rs.getString(2);
               	System.out.println("pre 3 "+pre_id3);
               	String sql1 ="insert into fci_registration.requests (course_id, course_name, course_id_pre_request,course_name_pre) values (?,?,?,?)";         
                   pst1 = conn.prepareStatement(sql1);
                   pst1.setInt(1, course_id);
                   pst1.setString(2, course_Name);
                   pst1.setInt(3, pre_id3);
                   pst1.setString(4, third_course_request);
                   pst1.executeUpdate();
               }
               }
              
               
               
               
               
       	 
       	 }
        	   
        	   
        	   
           }
   		 
   	 
   	 
   	 } catch (Exception e) {
               e.printStackTrace();
   	 
   	 }
           }
    
    

   	 
    
    
    @FXML
    void Clear(ActionEvent event) {
   	 req1.setText("Empty");
        req2.setText("Empty2");
        req3.setText("Empty3");
        req11.setText("Empty");
        req21.setText("Empty2");
        req31.setText("Empty3");
    }
    
    
    ////////////////////////////
    public void up_date() {

    	c_name.setCellValueFactory(new PropertyValueFactory<courses, String>("course_name"));
    	c_code.setCellValueFactory(new PropertyValueFactory<courses, String>("course_code"));
    	c_hours.setCellValueFactory(new PropertyValueFactory<courses, Integer>("course_hours"));
    	dr_id.setCellValueFactory(new PropertyValueFactory<courses, Integer>("course_doctor"));
    	dep_id.setCellValueFactory(new PropertyValueFactory<courses, Integer>("course_dept"));
    	num_reg.setCellValueFactory(new PropertyValueFactory<courses, Integer>("number_std"));
        listM = mysqlconnect_admin.getmodifyData();
        table_courses.setItems(listM);
    }
    
    @FXML
    public void btn_back(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("main.fxml"));
    	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();

    }
    public void get_old_request() throws SQLException {
    	conn = mysqlconnect_admin.connectDb();
    	String sql2 = "select course_id_pre_request from fci_registration.requests where course_id = '"+get_id()+"'  ";
        pst = conn.prepareStatement(sql2);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
        	String id1 = rs.getString(1);
            old1.setText(id1);
        }
        if (rs.next()) {
            old2.setText(rs.getString(1));
        }
        if (rs.next()) {
            old3.setText(rs.getString(1));
        }
       
    	
    	
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		c_name.setCellValueFactory(new PropertyValueFactory<courses, String>("course_name"));
    	c_code.setCellValueFactory(new PropertyValueFactory<courses, String>("course_code"));
    	c_hours.setCellValueFactory(new PropertyValueFactory<courses, Integer>("course_hours"));
    	dr_id.setCellValueFactory(new PropertyValueFactory<courses, Integer>("course_doctor"));
    	dep_id.setCellValueFactory(new PropertyValueFactory<courses, Integer>("course_dept"));
    	num_reg.setCellValueFactory(new PropertyValueFactory<courses, Integer>("number_std"));
        listM = mysqlconnect_admin.getmodifyData();
        table_courses.setItems(listM);
        
        
        cou_name.setCellValueFactory(new PropertyValueFactory<courses, String>("course_name"));
        cou_code.setCellValueFactory(new PropertyValueFactory<courses, String>("course_code"));
        listM = mysqlconnect_admin.getaddcourses();
        table1.setItems(listM);
        req1.setText("Empty");
        req2.setText("Empty2");
        req3.setText("Empty3");
        
        old1.setText("Empty1");
        old2.setText("Empty2");
        old3.setText("Empty3");
        pre.setDisable(true);
       table1.setDisable(true);
	}

}
