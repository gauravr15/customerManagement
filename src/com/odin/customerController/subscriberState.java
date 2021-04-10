package com.odin.customerController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

import com.odin.dbController.dbSetup;

public class subscriberState {
	
	Logger LOG = Logger.getLogger(subscriberState.class.getClass());
	
	public boolean state() {
		LOG.debug("inside subscriber state");
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss");
		String localTime = dtf.format(now);
		LOG.debug(localTime);
		boolean task_performed = false;
		dbSetup dbObj = new dbSetup();
		Connection conn = dbObj.dbInit();
		Statement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM CUSTOMER_INFO;";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String date = rs.getString("last_visit_date");
				//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
				//LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
				LOG.debug("last visit on : "+date);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		
		return task_performed;
	}
}
