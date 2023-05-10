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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fatma
 */
public class AdminController implements Initializable {
private PreparedStatement statment = null;
    private Connection con = null;
    private ResultSet rs = null;
    private Stage primaryStage;
    private Scene scene;
    private Parent root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    //------------------------Admins-----------------------
    @FXML
    private void Admins(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Manage.fxml"));
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
     //------------------------Students-----------------------
    @FXML
    private void Students(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Manage.fxml"));
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    //------------------------Students-----------------------
    @FXML
    private void Doctors(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Manage.fxml"));
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
}
