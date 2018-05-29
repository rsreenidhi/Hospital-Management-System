package models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import views.AlertBox;


public class EditUserModel {
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
	String sql = null;
	public void editUser(String full_name, int age, String gender, int doctor) throws Exception{

		try{

			stmt = connection.getConnection().createStatement();

			sql = "UPDATE w_afaneh_patient SET patientName ='"+full_name+"', patientAge = '"+age+"',"
					+ "patientGender = '"+gender+"', DoctorId = '"+ doctor+"'";

			stmt.executeUpdate(sql);



			System.out.println("Updated records into the table...");
			stmt.close();

		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}


	public  ResultSet getUser(TextField userid) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		String sql =  null;
		try{
			stmt = connection.getConnection().createStatement();


			String sql2 = "SELECT COUNT(*) AS COUNT  FROM w_afaneh_patient WHERE"
					+ " patientId= '" + userid.getText() + "'";


			ResultSet rs2 = stmt.executeQuery(sql2);
			int present = 0;
			if(rs2.next()){
				present = rs2.getInt("COUNT");
			}
			stmt.close();
			stmt = connection.getConnection().createStatement();
			if(present == 1){
				sql = "SELECT * FROM w_afaneh_patient WHERE patientId ='"+ userid.getText()+"'";
	
			}else{
				rs = null;
			}

			rs  = stmt.executeQuery(sql);




		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;


	}
	
	public void deleteUser(String userName ){
		ResultSet rs = null;
		String sql =  null;
		String sql2 =  null;
		String sql1 =  null;
		int patientId = 0;
		try{
			stmt = connection.getConnection().createStatement();
			sql1 = "SELECT patientId FROM w_afaneh_patient WHERE patientName='"+userName+"'";
			rs = stmt.executeQuery(sql1);
			while(rs.next())
			{
				patientId = rs.getInt("patientId");
			}
			sql2 = "DELETE FROM w_afaneh_reports WHERE reportPatient='"+patientId+"'";
			stmt.executeUpdate(sql2);
			sql = "DELETE FROM w_afaneh_patient WHERE patientName='"+userName+"'";
			
			stmt.executeUpdate(sql);

			stmt.close();
	}catch(SQLException e){
		e.printStackTrace();
	
	}

	}
}
