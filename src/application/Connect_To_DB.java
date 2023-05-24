/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author fatma
 */
public class Connect_To_DB {
 private static String HOST = "127.0.0.1";
        private static int PORT = 3306;
        private static String DB_NAME = "fci_registration";
        private static String USERNAME = "root";
        private static String PASSWORD = "123456";
        private static Connection connection ;
        
        // connect database 
        public static Connection connectDb (){
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST,PORT,DB_NAME),USERNAME,PASSWORD);
//            JOptionPane.showMessageDialog(null, "Connected  ..........");
        } catch (SQLException ex) {
            Logger.getLogger(Connect_To_DB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Not Connected... !!!!");
        }
            
            return  connection;
        }
        
   
}
