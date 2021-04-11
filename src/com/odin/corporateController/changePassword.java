package com.odin.corporateController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.odin.dbController.dbSetup;
import com.odin.dbController.queryHandler;

public class changePassword extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8256735273898500432L;
	Logger LOG = Logger.getLogger(changePassword.class.getClass());
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String pass = req.getParameter("pass").trim();
		String repass = req.getParameter("repass").trim();
		HttpSession session = req.getSession();
		queryHandler queryObj = new queryHandler();
		String user = (String) session.getAttribute("user");
		PrintWriter out = res.getWriter();
		if(!pass.equals(repass)) {
			out.print("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<body>\r\n"
					+ "<script>\r\n"
					+ "alert(\"New Password and confirm password are different\");\r\n"
					+ "window.location=\"http://"+queryObj.ipSetup()+":8080/Subscription/changePassword.html\"; \r\n"
					+ "</script>\r\n"
					+ "</body>\r\n"
					+ "</html>");
		}
		else if(pass.equals(repass)) {
			LOG.debug("Password and repassword are same");
			dbSetup dbObj = new dbSetup();
			Connection conn = dbObj.dbInit();
			Statement stmt = null;
			String query = "UPDATE USER_AUTH SET PASS = '"+pass+"' WHERE USER ='"+user+"';";
			LOG.debug("Query to fire : "+query);
			try {
				stmt = conn.createStatement();
				int result = stmt.executeUpdate(query);
				
				if(result != 0 ) {
					session.setAttribute("user", null);
					out.print("<!DOCTYPE html>\r\n"
							+ "<html>\r\n"
							+ "<body>\r\n"
							+ "<script>\r\n"
							+ "alert(\"Password changed successfully. Please login with new password.\");\r\n"
							+ "window.location=\"http://"+queryObj.ipSetup()+":8080/Subscription/login.html\"; \r\n"
							+ "</script>\r\n"
							+ "</body>\r\n"
							+ "</html>");
					
				}
				else {
					out.print("<!DOCTYPE html>\r\n"
							+ "<html>\r\n"
							+ "<body>\r\n"
							+ "<script>\r\n"
							+ "alert(\"Something went wrong. Please try again.\");\r\n"
							+ "window.location=\"http://"+queryObj.ipSetup()+":8080/Subscription/changePassword.html\"; \r\n"
							+ "</script>\r\n"
							+ "</body>\r\n"
							+ "</html>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOG.error(e);
			}
			finally {
				try {
					stmt.close();
					conn.close();
					LOG.debug("Releasing DB connection");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LOG.error(e);
				}
				
			}
		}
	}
	
}
