
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.ObservableList;


public class database {
private	String url = "jdbc:mysql://localhost:3306/fci_registration" ; 
private	String username = "root";
private	String password = "123456";
public Connection fileConnection() throws SQLException {
	 try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connectdata = DriverManager.getConnection(url, username, password);
		System.out.println("Succes");
		return connectdata;
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		return null;	
	}
}

    

}
