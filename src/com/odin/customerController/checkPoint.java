package com.odin.customerController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.odin.dbController.dbSetup;
import com.odin.dbController.queryHandler;

public class checkPoint extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6608341959724752728L;
	Logger LOG = Logger.getLogger(checkPoint.class.getClass());
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		LOG.debug("Inside check point");
		String mobile_no = req.getParameter("mob_no");
		dbSetup dbObj = new dbSetup();
		Connection conn = dbObj.dbInit();
		Statement stmt = null;
		ResultSet rs = null;
		queryHandler queryObj = new queryHandler();
		String redirectURL = queryObj.ipSetup();
		try {
			String point = null;
			String query = "SELECT * FROM CUSTOMER_INFO WHERE MOBILE_NO = '"+mobile_no+"'";
			LOG.debug("Query to fire : "+query);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				point = rs.getString("TOTAL_POINTS");
				LOG.debug("Total point is : "+point);
			}
			PrintWriter out = res.getWriter();
			out.print("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<body>\r\n"
					+ "<script>\r\n"
					+ "alert(\"Available point is "+point+"\");\r\n"
					+ "window.location=\"http://"+redirectURL+":8080/Subscription/checkPoint.html\"; \r\n"
					+ "</script>\r\n"
					+ "</body>\r\n"
					+ "</html>");
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		finally {
			try {
				rs.close();
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
