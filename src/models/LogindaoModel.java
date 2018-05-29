package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogindaoModel {
	static Connector connection = new Connector();
	public Statement stmt = null;
	

	public  ResultSet userCheck(TextField userName, PasswordField password) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		int login = 1;
		try{
			stmt = connection.getConnection().createStatement();
			
	
			String sql = "SELECT *  FROM w_afaneh_user_login WHERE userName='" + userName.getText() + "' AND password='"+ password.getText() + "'";
			
			rs = stmt.executeQuery(sql);
			
			//String name = rs.getString("userType");
			
			

		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;
	}
}
