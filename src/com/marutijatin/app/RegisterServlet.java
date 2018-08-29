package com.marutijatin.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.marutijatin.app.dao.UserDAO;
import com.marutijatin.app.dto.UserDTO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  System.out.println("Welcome in register..");
	  String username = request.getParameter("username");
	  String userid= request.getParameter("userid");
	  String password = request.getParameter("password");
	  String confirmPassword = request.getParameter("confirm-password");
	  String date = request.getParameter("date");
	  String address = request.getParameter("address");
	  String city = request.getParameter("city");
	  String gender = request.getParameter("gender");
	  boolean isFillPropeer=false;
	  PrintWriter pw = response.getWriter(); 
	  
	  System.out.println(userid+" "+username+" "+password+" "+date+" "+city+" "+gender+" "+address+" "+confirmPassword );
	  if((password.length()!=0) && (confirmPassword.length()!=0)){
		  if(password.equals(confirmPassword)) {
			  System.out.println("Y");
	          isFillPropeer=true;
		  }
		  else {
			  System.out.println("Password has not matched....");
			  pw.println("Password has not matched....");
		  }
	  }
	  else {
		  System.out.println("Not fill password...");
	  }
	  if( (!gender.equals("Gender")) && (!city.equals("City"))) {
		 System.out.println("Gender and City is macthed..."); 
		 isFillPropeer =true;
	  }
	  else {
		  
		   System.out.println("Gender and City is not matched...");
	  }
	  if(((password.length()!=0)) && ((confirmPassword.length()!=0))  &&  (!gender.equals("Gender")) && (!city.equals("City")) &&
			  (address.length()!=0) && (userid.length()!=0) && (username.length()!=0) && (date.length()!=0) ) {
		  System.out.println("All are good..");
		  isFillPropeer = true;
	  }
	  else {
		  isFillPropeer = false;
		  System.out.println("All are not good...");
	  }
	  if(isFillPropeer) {
		  System.out.println("Ready to store in db...");
		  UserDTO userDTO = new UserDTO(Integer.parseInt(userid), username, password, gender.charAt(0), address, city, date);
		  UserDAO userDAO = new UserDAO();
		  try {
			if(userDAO.AddUser(userDTO)) {
				  System.out.println("Done");
				  pw.println("Register Successfully...");
			  }
			  else {
				  System.out.println("Problem occur...");
				  pw.println("Please fill all query carefully...");
			  }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  else {
		  System.out.println("Please fill all query...");
		  pw.print("Please fill all query...");
	  }
//	  UserDTO userDTO = new UserDTO();
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	doGet(request, response);
	}

}
