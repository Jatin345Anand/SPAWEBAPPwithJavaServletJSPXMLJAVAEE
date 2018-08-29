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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         PrintWriter pw = response.getWriter();
         pw.println("WELCOME ONLINE SHOP....");
         String username = request.getParameter("username");
 		String password = request.getParameter("pwd");
 		UserDAO userDao = new UserDAO();
 		UserDTO userDTO = new UserDTO(username, password);
 		try {
			if(userDao.isUserExist(userDTO)){
				pw.println("Welcome "+username);
			}
			else{
				pw.println("Invalid Userid or Password");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		System.out.println(request.getParameter("username")+"    "+request.getParameter("pwd"));
 		 pw.println();
         pw.println();
		
         pw.close();
         //response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		doGet(request, response);
	}

}
