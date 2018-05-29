package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



public class AddUserModel {
	
	
	private String fullname;
	
	private String gender;
	
	private int age;
	
	private int doctor;
	
	
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getDoctor() {
		return doctor;
	}

	public void setDoctor(int doctor) {
		this.doctor = doctor;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}



	static Connector connection = new Connector();
	public Statement stmt = null;

	public void insertUser(String full_name, int age, String gender, int doctor) throws Exception{
		
		try{
			String sql = null;
			stmt = connection.getConnection().createStatement();
			sql = "INSERT INTO w_afaneh_patient (patientName,patientAge, patientGender , DoctorId)" +
					"VALUES ('"+full_name+"', '"+age+"','"+gender+"', '"
							+ doctor+"')";
		
			stmt.executeUpdate(sql);
			
			stmt.close();
			
			

			System.out.println("Patient record inserted into the table");
			stmt.close();

		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
}
