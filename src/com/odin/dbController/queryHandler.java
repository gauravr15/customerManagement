package com.odin.dbController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;

import com.odin.billing.billCalculator;



public class queryHandler extends HttpServlet{
	
	
	Logger LOG = Logger.getLogger(queryHandler.class.getClass());
	HttpServletResponse res = null;
	public boolean loginHandler(String user, String pass) {
		LOG.debug("Inside login handler.");
		boolean userAuth = false;
		dbSetup dbObj = new dbSetup();
		Connection conn = dbObj.dbInit();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM USER_AUTH WHERE USER = '"+user+"' AND PASS = '"+pass+"';";
			stmt = conn.createStatement();
			LOG.debug("query to fire is "+query);
			rs = stmt.executeQuery(query);
			int count =0;
			while(rs.next()) {
				count = count+1;
			}
			if(count == 1) {
				userAuth = true;
				return userAuth;
			}
			else {
				userAuth = false;
				return userAuth;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error("Unable to create statement.");
			e.printStackTrace();
		}
		finally{
			try {
				LOG.debug("Releasing db connection");
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOG.error("Failed to release connection.");
				e.printStackTrace();
			}
		}
		return userAuth;
	}
	
	public String ipSetup() {
		String ip = null;
		dbSetup dbObj = new dbSetup();
		Connection conn = dbObj.dbInit();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM CONFIG WHERE MODULE = 'SYSTEM' AND TYPE = 0 AND PARAM_NAME = 'HOST_IP';";
			LOG.debug("Query to fire : "+query);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				ip = rs.getString("PARAM_VALUE");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOG.error(e);
			}
			
		}
		return ip;
	}
	
	public boolean billingHandler(String mob,String service) {
		boolean task_performed = false;
		dbSetup dbObj = new dbSetup();
		Connection conn = dbObj.dbInit();
		Statement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM CUSTOMER_INFO WHERE MOBILE_NO = '"+mob+"'";
		try {
			LOG.debug("Query to fire : "+query);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			String name = null;
			String registration_date = null;
			int total_point = 0;
			while(rs.next()) {
				name = rs.getString("NAME");
				registration_date = rs.getString("REGISTRATION_DATE");
				total_point = Integer.parseInt(rs.getString("total_points"));
			}
			billCalculator billObj = new billCalculator();
			int billAmount = billObj.billAmount(service);
			int newPoint = total_point + (billAmount/10);
			query = "INSERT INTO CUSTOMERS VALUES ('"+name+"','"+mob+"','"+registration_date+"',NOW(),'"+service+"','"+billAmount+"','"+newPoint+"');";
			stmt.executeUpdate(query);
			query = "UPDATE CUSTOMER_INFO SET TOTAL_POINTS = '"+total_point+"' WHERE mobile_no = '"+mob+"';";
			stmt.executeUpdate(query);
			task_performed = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOG.error(e);
			}
			
		}
		return task_performed;
	}
	
	public boolean billingHandler(String mob,String service,String bill, String point) {
		boolean task_performed = false;
		return task_performed;
	}
}
