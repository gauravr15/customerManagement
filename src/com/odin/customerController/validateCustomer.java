package com.odin.customerController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.odin.dbController.dbSetup;

public class validateCustomer {
	
	Logger LOG = Logger.getLogger(validateCustomer.class.getClass());
	
	public boolean customerCheck(String mob) {
		boolean isCustomerAvailable = false;
		LOG.debug("Inside validate customer");
		dbSetup dbObj = new dbSetup();
		Connection conn = dbObj.dbInit();
		Statement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM CUSTOMER_INFO WHERE MOBILE_NO = '"+mob+"';";
		try {
			LOG.debug("Query to fire : "+query);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				LOG.debug("Customer already present");
				isCustomerAvailable = true;
			}
			else {
				LOG.debug("Customer data not available");
				isCustomerAvailable = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		finally {
			LOG.debug("Releasing db Connection");
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOG.error(e);
			}
			
		}
		
		return isCustomerAvailable;
	}
}
