/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class BranchesController implements Initializable {
	@FXML
	private Stage primaryStage;
	private Scene scene;
	private Parent root;
    @FXML
    private Button back;
    @FXML
    Button courses;
    @FXML
    Button depp;

    public void course_btn(ActionEvent event) {

    	try {

         	 root = FXMLLoader.load(getClass().getResource("main.fxml"));
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

    public void dept_btn(ActionEvent event) {

    	try {

        	 root = FXMLLoader.load(getClass().getResource("dept_main.fxml"));
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
    public void Bac_btn(ActionEvent event) {
 	   try {

         	 root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
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
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
