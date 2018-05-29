package controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.LogindaoModel;
import views.AlertBox;
import views.LoginView;

public class LoginController implements Initializable {

	@FXML
	private TextField userName;
	@FXML
	private PasswordField password;
	
	public void login(){
		LoginView loginPage = new LoginView();
		loginPage.main();
		
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public  void loginAgain(){
		LoginView newlv = new LoginView();
		newlv.startagain();
	}
	
	public void validatorMessage() throws Exception {
		ResultSet rs = null;
		int usertype = 2;
		String username = null;
		int userId = 0;
		String firstname = null;
		String lastname = null;
		LogindaoModel user = new LogindaoModel();
		try {
			rs = user.userCheck(this.userName, this.password);
			
			while(rs.next()) {
				
				usertype =  rs.getInt("userType");
				username = rs.getString("userName");
				userId = rs.getInt("userId");
				firstname = rs.getString("firstName");
				lastname = rs.getString("lastName");
			}
			
			String fullname = firstname + " " + lastname;
			
			if(username == null)
				AlertBox.display("Error", "Invalid Username or Password");
			else if(usertype == 0){
				User Doctor = new User();
				Doctor.setusername(username);
				Doctor.setuserid(userId);
				Doctor.setFullName(fullname);
				Doctor.admin();
				
			}	
			else{
				
				User recep = new User();
				recep.setusername(username);
				recep.setuserid(userId);
				recep.setFullName(fullname);
				
				recep.recep();
				
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}
	
		
	}
	

}
