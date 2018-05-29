package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	public Connection getConnection() {
		Connection con = null;
		
		try{
			String host = "jdbc:mysql://www.papademas.net:3306/fp510?autoReconnect=true&useSSL=false";
			
			String userName = "fpuser";
			String password = "510";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(host, userName, password);
		}catch(SQLException err){
			System.out.println(err.getMessage());
		}catch(ClassNotFoundException err){
			err.printStackTrace();
		}
		
		return con;
	}
	
}

