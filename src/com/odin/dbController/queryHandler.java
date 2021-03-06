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
	
	public void billingHandler(String mob,String bill) {
		dbSetup dbObj = new dbSetup();
		Connection conn = dbObj.dbInit();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM CUSTOMER_INFO WHERE mobile_no = '"+mob+"';";
			stmt = conn.createStatement();
			LOG.info("query to fire is "+query);
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				String total_point = rs.getString("TOTAL_POINTS");
				LOG.info("Total points for user :"+mob+" is :"+total_point);
				int calc_point = Integer.parseInt(bill)/10;
				total_point = Integer.toString(Integer.parseInt(total_point)+calc_point);
				LOG.info("Points added for this transaction is : "+calc_point);
				LOG.info("Total points for user : "+mob+" is : "+total_point);
				query = "UPDATE CUSTOMER_INFO SET TOTAL_POINTS = '"+total_point+"' WHERE mobile_no = '"+mob+"';";
				LOG.info("Query to fire : "+query);
				if(stmt.executeUpdate(query)!=0) {
					LOG.info("Points updated successfully");
				}
				else {
					LOG.error("Failed to update points");
				}
			}
			else {
				LOG.info("customer doesn't exist");
			}
		}
		catch (SQLException e) {
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
	}
	
	public void billingHandler(String mob,String bill, String point) {
		dbSetup dbObj = new dbSetup();
		Connection conn = dbObj.dbInit();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM CUSTOMER_INFO WHERE mobile_no = '"+mob+"';";
			stmt = conn.createStatement();
			LOG.info("query to fire is "+query);
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				int total_point = Integer.parseInt(rs.getString("TOTAL_POINTS"));
				LOG.info("Total points for user :"+mob+" is :"+total_point);
				if(total_point < Integer.parseInt(point)) {
					LOG.debug("Insufficient points");
					billingHandler(mob,bill);
				}
				else {
					int calc_point = total_point - Integer.parseInt(point);
					query = "UPDATE CUSTOMER_INFO SET TOTAL_POINTS = '"+calc_point+"' WHERE mobile_no = '"+mob+"';";
					if(stmt.executeUpdate(query)!=0){
						LOG.info("Points deducted successfully");
					}
					else {
						LOG.info("Points deduction failed");
					}
				}
			}
			else {
				LOG.error("user doesn't exists");
			}
		}
		catch (SQLException e) {
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
	}
}
