package views;

import java.io.IOException;
/**
 * <h1>Admin View</h1>
 * 
 * This method is used to launch the admin page. FXML file is adminpage1.fxml
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class AdminView{
	
	public  AdminView(){
		
		
		Parent root;
		try {
			
			root = FXMLLoader.load(getClass().getResource("/fxml/adminPage1.fxml"));
			Scene scene = new Scene(root, 800, 500);
			LoginView.window.setScene(scene);
			LoginView.window.setTitle("HoMaS");
			LoginView.window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
