package start;

import controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main {
	
	public static void main(String[] args){
		
		LoginController controller = new LoginController();
		controller.login();
		
	}
	
	
}

