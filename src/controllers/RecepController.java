package controllers;

import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;

import java.time.LocalDate;
import java.util.ResourceBundle;
import views.LoginView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.text.Text;
import models.AddUserModel;
import models.EditUserModel;

import views.AddUserView;
import views.AdminView;
import views.AlertBox;
import views.ConfirmationBox;
import views.EditUserView;

import views.PaymentCalcView;
import views.ReportsView;


public class RecepController extends User implements Initializable{
	

	@FXML
	private TextField fullname;

	@FXML
	private TextField age;

	@FXML
	private ChoiceBox gender;
	@FXML
	private TextField doctor;
	
	@FXML
	private TextField searchUserId;

	public void addUser(){
		
		new AddUserView();
	}

	
	public void editUser(){
		new EditUserView();
	}

	public void payslipCalculator(){
		System.out.println("HELLO HELLO");
		new PaymentCalcView();
	}

	public void addUserToDB(){
		AddUserModel addUserModel = new AddUserModel();
		if(fullname.getText() == null){
			AlertBox.display("Error", "Full name cannot be empty");
		}

		else if(doctor.getText() == null){
			AlertBox.display("Error", "Assign Doctor!");
		}


		else if(age.getText() == null){
			AlertBox.display("Error", "Age cannot be empty");
		}
		else{
			
			try{
				addUserModel.setFullname(fullname.getText());
				addUserModel.setAge(Integer.parseInt(age.getText()));
				addUserModel.setGender((String)gender.getValue());
				addUserModel.setDoctor(Integer.parseInt(doctor.getText()));
				
				addUserModel.insertUser(fullname.getText(), Integer.parseInt(age.getText()), (String)gender.getValue(), Integer.parseInt(doctor.getText()));
				AlertBox.display("Success", "User details added Successfully");

			}catch(Exception e){
				// TODO Auto-generated catch block
				AlertBox.display("Error", "Cannot Add the details. Maybe Userdata already exists");
			}
		}
		

	}
	EditUserModel editUserModel = new EditUserModel();
	/**
	 * This method is used to save the edited user details to database
	 */
	public void editUserInDB(){
		EditUserModel editUserModel = new EditUserModel();
		if(fullname.getText() == null){
			AlertBox.display("Error", "Full name cannot be empty");
		}

		else if(doctor.getText() == null){
			AlertBox.display("Error", "Assign Doctor!");
		}


		else if(age.getText() == null){
			AlertBox.display("Error", "Age cannot be empty");
		}else{
			
		try{
			editUserModel.setFullname(fullname.getText());
			editUserModel.setAge(Integer.parseInt(age.getText()));
			editUserModel.setGender((String)gender.getValue());
			editUserModel.setDoctor(Integer.parseInt(doctor.getText()));

			editUserModel.editUser(fullname.getText(), Integer.parseInt(age.getText()), (String)gender.getValue(), Integer.parseInt(doctor.getText()));
			AlertBox.display("Success", "Data Updated Successfully");
		}catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		String adminuser = "Howdy! " + User.getusername();
//		TextInputControl loginName = null;
//		loginName.setText(adminuser);
//		
	}
	
public void testing() throws SQLException{
		
		//String selection = listView1.getSelectionModel().getSelectedItem();
		System.out.println("hi");
}
	/**
	 * This will logout from the session
	 */
	public void logout(){
		LoginView.window.close();
		LoginController startAgain = new LoginController();
		startAgain.login();
	}
	

	public void search() throws ParseException{
		
		
		ResultSet rs = null;
		try {
		
			 rs = editUserModel.getUser(searchUserId);
			while(rs.next()){
				
				fullname.setText(rs.getString("patientName"));
				gender.getSelectionModel().select(rs.getString("patientGender"));
				age.setText(rs.getString("patientAge"));
				doctor.setText(rs.getString("DoctorId"));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AlertBox.display("Message", "Patient Not found. Try different Id");
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				AlertBox.display("Message", "Patient Not found. Try different Id");
			}
		}
		
	}

	public void fullnameValidate(){
		if(!fullname.getText().matches("^[a-zA-Z ]*$")){
			AlertBox.display("Error", "Text Only");
		}
	}
	
	public void phoneNumberValidate(){
		if(!age.getText().matches("^[0-9]*$")){
			AlertBox.display("Error", "Numbers Only");
		}
		
	}
	
	

	
	
	public void back(){
		new AdminView();
	}
	


	public void deleteUser(){
		boolean result = ConfirmationBox.display("DELETE", "Are you sure to delete this Patient?");
		EditUserModel deleteUser = new EditUserModel();
		deleteUser.deleteUser(fullname.getText());  
		AlertBox.display("Message", "Patient Details Deleted Successfully!");
	}
}
