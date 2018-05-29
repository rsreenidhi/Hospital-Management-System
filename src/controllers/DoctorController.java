package controllers;

import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ResourceBundle;

import javafx.collections.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import models.PatientsDAOModel;
import views.AdminView;
import views.AlertBox;
import views.DoctorView;

public class DoctorController extends User implements Initializable{

	@FXML
	private ListView<String> listView;

	@FXML
	private TextField patientName;

	@FXML
	private TextArea patientReportSubject;

	@FXML
	private TextArea patientReport;

	@FXML
	private DatePicker patientReportDate;

	
	private int doctorId;
	
	
	
	public TextField getPatientName() {
		return patientName;
	}

	public void setPatientName(TextField patientName) {
		this.patientName = patientName;
	}

	public TextArea getPatientReportSubject() {
		return patientReportSubject;
	}

	public void setPatientReportSubject(TextArea patientReportSubject) {
		this.patientReportSubject = patientReportSubject;
	}

	public TextArea getPatientReport() {
		return patientReport;
	}

	public void setPatientReport(TextArea patientReport) {
		this.patientReport = patientReport;
	}

	public DatePicker getPatientReportDate() {
		return patientReportDate;
	}

	public void setPatientReportDate(DatePicker patientReportDate) {
		this.patientReportDate = patientReportDate;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	PatientsDAOModel getPatients = new PatientsDAOModel();


	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DoctorController controller = new DoctorController();
		controller.setDoctorId(controller.getuserid());
		ObservableList<String> list = FXCollections.observableArrayList();
		ResultSet usersList = null;
		try {
			usersList =  getPatients.getPatientsList();
			while(usersList.next()){

				list.add(usersList.getString("patientName"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				usersList.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		listView.getItems().addAll(list);
	}



	public void cancelReport() {

	}
	
	public void getPatientDetails() {
		String selection = listView.getSelectionModel().getSelectedItem();
		ResultSet patientInfo = null;
		PatientsDAOModel getpatientInfo = new PatientsDAOModel();

		try {
			patientInfo = getpatientInfo.getpatientDetails(selection);
			while(patientInfo.next()){
				String patientname = (String) selection;
				int patientid = patientInfo.getInt("reportPatient");
				String reportsubject = patientInfo.getString("reportSubject");
				String reportmatter = patientInfo.getString("reportMatter");
				Date reportdate = patientInfo.getDate("reportDate");
				
				this.patientName.setText(String.valueOf(patientid));
				this.patientReportSubject.appendText(reportsubject);
				this.patientReport.appendText(reportmatter);
			      
			      
				this.patientReportDate.setValue(LOCAL_DATES(reportdate));
			}
		}catch(Exception e) {

		}

	}

	public static final LocalDate LOCAL_DATES(Date theDate){
		
	   
	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    String dateString = df.format(theDate);
	    System.out.println(dateString);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	   
	    
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
		return localDate;

	}

	public void saveReport(){

		PatientsDAOModel patients = new PatientsDAOModel();
		try{
			

			String patientId = patientName.getText();
			String reportSubject = patientReportSubject.getText();
			String reportMatter = patientReport.getText();
			
			
			Date reportDate = java.sql.Date.valueOf(patientReportDate.getValue());
			
			patients.saveReport(patientId, reportSubject, reportMatter, LOCAL_DATES(reportDate), 3);
			AlertBox.display("Success" , "Report Saved to Database");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			AlertBox.display("Error" , "OOps");
		}
}



public void back(){
	new AdminView();
}
}
