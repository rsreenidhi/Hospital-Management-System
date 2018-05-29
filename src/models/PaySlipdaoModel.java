package models;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PaySlipdaoModel {
	private  int eIDC;
	private double salary;
	private Date payslipFrom;
	private Date payslipTo;
	private Date generatedDate;
	private Double ta;
	private Double da;
	private Double tax;
	private Double NetSalary;
	
	
	public int geteIDC() {
		return eIDC;
	}


	public void seteIDC(int eIDC) {
		this.eIDC = eIDC;
	}


	public Date getGeneratedDate() {
		return generatedDate;
	}


	public void setGeneratedDate(Date generatedDate) {
		this.generatedDate = generatedDate;
	}


	public Double getTa() {
		return ta;
	}


	public void setTa(Double ta) {
		this.ta = ta;
	}


	public Double getDa() {
		return da;
	}


	public void setDa(Double da) {
		this.da = da;
	}


	public Double getTax() {
		return tax;
	}


	public void setTax(Double tax) {
		this.tax = tax;
	}


	public Double getNetSalary() {
		return NetSalary;
	}


	public void setNetSalary(Double netSalary) {
		this.NetSalary = netSalary;
	}


	public Date getPayslipFrom() {
		return payslipFrom;
	}


	public void setPayslipFrom(Date payslipFrom) {
		this.payslipFrom = payslipFrom;
	}


	public Date getPayslipTo() {
		return payslipTo;
	}


	public void setPayslipTo(Date payslipTo) {
		this.payslipTo = payslipTo;
	}


	public int getEmployeeIdColoumn() {
		return eIDC;
	}


	public void setEmployeeIdColoumn(int employeeIdColoumn) {
		eIDC = employeeIdColoumn;
	}
	
	static Connector connection = new Connector();
	public Statement stmt = null;

	public  ResultSet getUserDetails(String username) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try{
			stmt = connection.getConnection().createStatement();
			String sql1 = "SELECT patientId FROM w_afaneh_patient WHERE patientName='"+username+"'";
			stmt.executeQuery(sql1);
			rs = stmt.getResultSet();
			

		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;


	}
	
	

	public  ResultSet getUserList() throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try{
			stmt = connection.getConnection().createStatement();


			String sql = "SELECT * FROM w_afaneh_patient ";

			rs  = stmt.executeQuery(sql);
			
			


		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;


	}
	

	
	public void savePayToDB(String patient_id, String bill_amount, String tax_amt, String total_bill){
			
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate localDate = LocalDate.now();
		
		try {
			stmt = connection.getConnection().createStatement();
			String sql = "INSERT INTO w_afaneh_bills (patientId, billAmount, billDate) "
					+  "VALUES"
					+ "('"+patient_id+"', '"+total_bill+"', '"+localDate+"')";

			
				stmt.executeUpdate(sql);
				
				stmt.close();
			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	}
	
	public ResultSet getPayFromDB(){
		return null;
		
	}
	
	public ResultSet getReport(String username){
		
		ResultSet rs = null;
		int eid = 0;
		try {
			stmt = connection.getConnection().createStatement();
			String sql = "SELECT UserId FROM w_afaneh_reports WHERE UserName= '"+ username + "'";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				 eid = rs.getInt("UserId");
			}
			
			
			String sql1 = "SELECT * FROM w_afaneh_reports WHERE PayEmployeeId = '"+eid+"'";
			rs= stmt.executeQuery(sql1);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	public PaySlipdaoModel(){
		
	}
	

	
	
	
//	public  PaySlipdaoModel(int EmployeeId, Double Salary, Date from, Date to, Date generatedDate,
//			Double ta, Double da,Double tax, Double netSalary){
//		
////		this.eIDC = EmployeeId;
////		this.salary = 	Salary;
////		this.payslipFrom = from;
////		this.payslipTo = to;
////		this.generatedDate = generatedDate;
////		this.ta = ta;
////		this.da = da;
////		this.tax = tax;
////		this.NetSalary = netSalary;
//		
//		
//	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}



}
