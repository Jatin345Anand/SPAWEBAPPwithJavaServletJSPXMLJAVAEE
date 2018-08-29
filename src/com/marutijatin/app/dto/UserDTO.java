package com.marutijatin.app.dto;

import java.util.ArrayList;

public class UserDTO {
 public UserDTO(String userName, String password) {
		super();
		UserName = userName;
		Password = password;
	}
public UserDTO(int userID, String userName, String password, char gender, String address, String city, String dOB) {
		super();
		UserID = userID;
		UserName = userName;
		Password = password;
		Gender = gender;
		Address = address;
		City = city;
		DOB = dOB;
	}
public UserDTO() {
	// TODO Auto-generated constructor stub
}
private int UserID;
 private String UserName;
 private String Password;
 private char Gender;
 private String Address;
 private String City;
 private String DOB;
 private String RoleName;
 public String getRoleName() {
	return RoleName;
}
public void setRoleName(String roleName) {
	RoleName = roleName;
}
public ArrayList<RightDTO> getRights() {
	return rights;
}
public void setRights(ArrayList<RightDTO> rights) {
	this.rights = rights;
}
private ArrayList<RightDTO> rights;
public int getUserID() {
	return UserID;
}
public void setUserID(int userID) {
	UserID = userID;
}
public String getUserName() {
	return UserName;
}
public void setUserName(String userName) {
	UserName = userName;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public char getGender() {
	return Gender;
}
public void setGender(char gender) {
	Gender = gender;
}
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}
public String getCity() {
	return City;
}
public void setCity(String city) {
	City = city;
}
public String getDOB() {
	return DOB;
}
public void setDOB(String dOB) {
	DOB = dOB;
}
 
   
}
