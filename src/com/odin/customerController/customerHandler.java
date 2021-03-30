package com.odin.customerController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.odin.dbController.dbSetup;

public class customerHandler {
	
	Logger LOG = Logger.getLogger(customerHandler.class.getClass());
	
	public boolean createCustomer(String mob, String name) {
		LOG.debug("Inside create customer");
		boolean task_performed = false;
		dbSetup dbObj = new dbSetup();
		Connection conn = dbObj.dbInit();
		Statement stmt = null;
		int rs = 0;
		try {
			String query = "INSERT INTO CUSTOMER_INFO (MOBILE_NO,NAME,REGISTRATION_DATE,TOTAL_POINTS) VALUES ('"+mob+"','"+name+"',NOW(),'0')";
			LOG.debug("Query to execute : "+query);
			stmt = conn.createStatement();
			rs = stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		
		return task_performed;
	}
}
