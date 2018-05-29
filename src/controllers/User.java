package controllers;

import views.AdminView;
import views.DoctorView;
import views.ReceptionistView;

//import views.AdminView;


public class User {

	private static int  loginuserId;
	
	private static String  loginuser;
	
	private static String fullName;
	
	public  String getFullName() {
		return fullName;
	}

	public  void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setuserid(int loginuserId){
		this.loginuserId = loginuserId;
	}
	
	public   int getuserid(){
		return loginuserId;
	}

	public void setusername(String loginuser){
		this.loginuser = loginuser;
	}
	
	public static  String getusername(){
		return loginuser;
	}
	
	
	public void admin(){
		
		new DoctorView();

	}
	
	public void recep(){
		
		new AdminView();
	} 

		
	

}
