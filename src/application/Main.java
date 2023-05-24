package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root1 =FXMLLoader.load(getClass().getResource("Login_Page.fxml"));
		Scene scene = new Scene(root1);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
