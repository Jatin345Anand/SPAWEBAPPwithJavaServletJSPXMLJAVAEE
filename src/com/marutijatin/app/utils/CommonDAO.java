package com.marutijatin.app.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.marutijatin.app.dto.CommonGenericDTO;

public class CommonDAO {
	public static boolean isAddUser=false;
//	public static Connection getConnection() throws ClassNotFoundException, SQLException{
//		ResourceBundle rb = ResourceBundle.getBundle("config");
//		Class.forName(rb.getString("drivername"));
//		Connection connection = DriverManager.
//				getConnection(rb.getString("dburl"),rb.getString("userid"),rb.getString("password"));
//		return connection;
//	}
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Connection con = null;
		
		Context context;
		try {
			InitialContext initialContext = new InitialContext();
			context = (Context) initialContext.lookup("java:comp/env");
			DataSource ds = (DataSource) context.lookup("jdbc/onlineshop");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException("Naming Exception "+e);
		}
		return con;
	}
	public static ArrayList<CommonGenericDTO> getCommonGenericParameters(String key) throws ClassNotFoundException, SQLException{
		Connection con  = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CommonGenericDTO> commonList = new ArrayList<>();
		try{
			con = CommonDAO.getConnection();
			pstmt = con.prepareStatement(QueryConstants.COMMON_GENERIC_SQL);
			pstmt.setString(1, key);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CommonGenericDTO commonGenericDTO = 
						new CommonGenericDTO(rs.getString("name")
								,rs.getString("descr"));
				commonList.add(commonGenericDTO);
			}
			return commonList;
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
	}
	
	public static String ChangeDateFormate(String date) {
		String s1 ="";
		String s2 ="";
		String s3 ="";
		int firstindex=date.indexOf('/');
		int secondindex= date.indexOf('/', (firstindex+1));
		for(int i=0;i<firstindex;i++) {
			s1=s1+date.charAt(i);
		}
	 	for(int i=firstindex+1;i<secondindex;i++) {
			s2=s2+date.charAt(i);
		}
	 	for(int i=secondindex+1;i<date.length();i++) {
			s3=s3+date.charAt(i);
		}
	 	return s3+"-"+s1+"-"+s2;
	}
	 
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("Enter Date...");
		String s= scanner.next();
		System.out.println(ChangeDateFormate(s));
		scanner.close();
	}
}
