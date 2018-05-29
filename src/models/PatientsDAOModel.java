package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class PatientsDAOModel {

	private int patientId;

	private String patientName;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	static Connector connection = new Connector();
	public Statement stmt = null;

	public  ResultSet getPatientsList() throws Exception {
		ResultSet rs = null;
		try{
			stmt = connection.getConnection().createStatement();


			String sql = "SELECT * FROM w_afaneh_patient";

			rs  = stmt.executeQuery(sql);




		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;
	}

	public ResultSet getpatientDetails(String patientname) {
		ResultSet rs = null;
		int patient_id = 0;
		try {
			stmt = connection.getConnection().createStatement();
			String sql = "SELECT patientId FROM w_afaneh_patient WHERE patientName='"+patientname+"'";
			stmt.executeQuery(sql);
			rs = stmt.getResultSet();
			if(rs.next()) {
				patient_id = rs.getInt("patientId");
			}
			String sql1 = "SELECT * FROM w_afaneh_reports WHERE reportPatient='"+patient_id+"'";
			stmt.executeQuery(sql1);
			rs = stmt.getResultSet();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;

	}
	


	public void saveReport(String patientId2, String reportSubject, String reportMatter, LocalDate localDate, int i) {

		try {
			stmt = connection.getConnection().createStatement();
			String sql = "INSERT INTO w_afaneh_reports (reportSubject, reportMatter, reportDoctor, reportPatient, reportDate)"
					+ "VALUES('"+reportSubject+"','"+reportMatter+"','"+i+"','"+patientId2+"','"+localDate+"')";
			stmt.executeUpdate(sql);
			
			stmt.close();
						
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet getUserDetails(String selection) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultSet getPatient(String selection) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		int patient_id = 0;
		try {
			stmt = connection.getConnection().createStatement();
			String sql = "SELECT patientId FROM W_afaneh_patient WHERE patientName='"+((String)selection)+"'";
			stmt.executeQuery(sql);
			rs = stmt.getResultSet();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
