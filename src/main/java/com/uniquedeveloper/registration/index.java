package com.uniquedeveloper.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class feedback
 */
@WebServlet("/submit")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String number = request.getParameter("number");
		String message = request.getParameter("message");
		RequestDispatcher dispatcher ;
		Connection con = null;
		try {		
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube","root","w2macair");
			PreparedStatement pst = con.prepareStatement("insert into collecteddata(name,email,number,message) values (?,?,?,?) ");			
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, number);
			pst.setString(4, message);
			
			
			int rowcount = pst.executeUpdate();
		
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request,response);
	
			if(rowcount < 0) {				
			}
			}catch(Exception e) {
	
				e.printStackTrace();
				
			}try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
		
	}

}
