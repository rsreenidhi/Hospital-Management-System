package controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

import javafx.scene.control.TextField;

import models.PaySlipdaoModel;
import views.AdminView;
import views.AlertBox;


public class PaySlip extends User implements Initializable{
	

	@FXML
	private ListView<String> listView;
	
	
	@FXML
	private TextField patientId;

	@FXML
	private TextField bill;
	@FXML
	private TextField tax;
	@FXML
	private TextField totalBill;
	
	
	PaySlipdaoModel paySlip = new PaySlipdaoModel();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> list = FXCollections.observableArrayList();
		ResultSet usersList = null;
		try {
			 usersList =  paySlip.getUserList();
			while(usersList.next()){
				list.add(usersList.getString("patientName"));
			}
			listView.setItems(list);
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
		listView.setItems(list);
	}

	public void testing() throws SQLException{
		
		String selection = listView.getSelectionModel().getSelectedItem();
		ResultSet userInfo = null;
		PaySlipdaoModel getUserInfo = new PaySlipdaoModel();
		try {
			
			 userInfo = getUserInfo.getUserDetails(selection);
			while(userInfo.next()){
				
				int patient_id =  userInfo.getInt("patientId");
			
				patientId.setText(String.valueOf(patient_id));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			userInfo.close();
		}
	}


	public void calculatePay(){
		
	
		String bill_amount = bill.getText();
		String tax_amt = tax.getText();
		double total_bill = ((Double.parseDouble(bill_amount) / 100) * Double.parseDouble(tax_amt)) + Double.parseDouble(bill_amount);
		totalBill.setText(String.valueOf(total_bill));
		
	}

	public void savePay(){
		String patient_id = patientId.getText();
		String bill_amount = bill.getText();
		String tax_amt = tax.getText();
		String total_bill = totalBill.getText();
		PaySlipdaoModel saveBill = new PaySlipdaoModel();
		
		saveBill.savePayToDB(patient_id, bill_amount, tax_amt, total_bill);
		AlertBox.display("Success", "Bill Generated successfully");
	}

	
	
	public void back(){
		new AdminView();
	}
	
	

	
}
