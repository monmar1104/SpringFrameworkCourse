package com.monmar.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String user = "root";
		String pass = "root";
		String jdbcUrl = "jdbc:mysql://localhost:32768/web_customer_tracker?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			PrintWriter pw = response.getWriter();
			
			pw.println("Connecting to database "+jdbcUrl);
			
			Class.forName(driver);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			pw.println("SUCCESS");
			
			myConn.close();
					
		}
		catch(Exception exc){
			exc.printStackTrace();
			throw new ServletException(exc);
			
		}
		
		
		
	}

}
