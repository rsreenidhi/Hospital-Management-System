package views;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LoginView extends Application {
	 public static Stage window;
	
	public  void startagain(){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/loginPage.fxml"));
			Scene scene = new Scene(root, 600, 400);
			LoginView.window.setScene(scene);
			LoginView.window.setTitle("HoMaS");
			LoginView.window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void main(){
		launch();
		
	}

	@Override
	public  void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		window = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginPage.fxml"));
		Scene scene = new Scene(root, 800, 500);
		primaryStage.resizableProperty().setValue(Boolean.FALSE);
		primaryStage.setScene(scene);
		primaryStage.setTitle("HoMaS");
		primaryStage.show();
		
	}


}
