package com.marutijatin.app.dao;
import static com.marutijatin.app.utils.CommonDAO.ChangeDateFormate;
import static com.marutijatin.app.utils.CommonDAO.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.marutijatin.app.dto.RightDTO;
import com.marutijatin.app.dto.UserDTO;
import com.marutijatin.app.utils.QueryConstants;

class B{
	static{
		System.out.println("B Static Block call");
	}
}
class A{
	A(){
		System.out.println("A Cons Call");
	}
	static{
		System.out.println("A Static Block call");
	}
}


public class UserDAO {
	Logger logger = Logger.getLogger(UserDAO.class);
	public boolean AddUser(UserDTO userDTO) throws ClassNotFoundException, SQLException {
		boolean isExist = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			con = getConnection();
			logger.debug("Inside UserDAO After Creating Connection..."+userDTO.getUserName());
//			pstmt = con.prepareStatement("SELECT userid,password FROM userlogin WHERE userid=? and password=?");
			System.out.println(userDTO.getCity()+"  "+ ChangeDateFormate(userDTO.getDOB())+"  "+userDTO.getAddress()+"  "+userDTO.getUserID()+" "+userDTO.getGender()+" "+
             userDTO.getUserName()+"  "+userDTO.getPassword() );
 			pstmt = con.prepareStatement(QueryConstants.REGISTER_SQL);
			pstmt.setInt(1, userDTO.getUserID());
			pstmt.setString(2, userDTO.getUserName()); 
			java.sql.Date date = java.sql.Date.valueOf(ChangeDateFormate(userDTO.getDOB())) ;
			//long date = Date.parse(ChangeDateFormate(userDTO.getDOB()));
			System.out.println(date);
			pstmt.setString(3, userDTO.getPassword());
			pstmt.setDate(4, date);
	 		pstmt.setString(5, String.valueOf(userDTO.getGender()));
			pstmt.setString(6, userDTO.getCity());
			pstmt.setString(7, userDTO.getAddress());
			System.out.println("1Done query");
//			isAddUser=true;
//			rs = pstmt.executeQuery();
//			System.out.println("2Done query");
//			if(rs.next()){
//				isExist = true;
//			}
			 int noOfRecordsUpdated = pstmt.executeUpdate();
				
			 if(noOfRecordsUpdated>0){
				 logger.debug("Record Added....");
				 return true;
			 }
			 else{
				 logger.debug("No Record Found....");
				 return false;
			 }
		}
		finally{
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(con!=null){
				con.close();
			}
			logger.debug("Resources Close....");
		}
		
	}
	public boolean isUserExist(UserDTO userDTO) throws ClassNotFoundException, SQLException{
		boolean isExist = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RightDTO> rights = null;
		UserDTO userDTO2=null;
		try
		{
			con = getConnection();
			logger.debug("Inside UserDAO After Creating Connection..."+userDTO.getUserName());
//			pstmt = con.prepareStatement("SELECT userid,password FROM userlogin WHERE userid=? and password=?");
 			pstmt = con.prepareStatement(QueryConstants.LOGIN_SQL);
			pstmt.setString(1, userDTO.getUserName());
			pstmt.setString(2, userDTO.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()){
				isExist = true;
			}
			  while(rs.next()){
				  if(userDTO2 == null){
					  userDTO2 = new UserDTO();
					  userDTO.setUserName(rs.getString("USERID"));
					  userDTO.setRoleName(rs.getString("ROLE"));
					  rights= new ArrayList<>();
					  userDTO.setRights(rights);
				  }
				  RightDTO right = new RightDTO(rs.getString("RIGHT"), rs.getString("screen"));
				  rights.add(right);
			  }
//			  return userDTO2;
		}
		finally{
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(con!=null){
				con.close();
			}
		}
		return isExist;
	}
 public static void main(String[] args) throws ClassNotFoundException, SQLException {
	 
	 UserDAO ud = new UserDAO();
	 Scanner s = new Scanner(System.in);
     
	 System.out.println("Enter the Username..");
	 String username = s.next();
	 System.out.println("Enter the Password..");
	 String pwd = s.next();
	 UserDTO userDTO = new UserDTO(username, pwd);
	 
	 Connection connection = getConnection();
	 if(connection!=null){
		 System.out.println("Connection Created....");
	 }
	 System.out.println(userDTO.getUserName());
	 System.out.println(userDTO.getPassword());
	 
     System.out.println(ud.isUserExist(userDTO));
	 System.out.println(QueryConstants.LOGIN_SQL);
	 s.close();
	 
}

}
 