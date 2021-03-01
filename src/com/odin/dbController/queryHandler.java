package com.odin.dbController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;



public class queryHandler {
	
	Logger LOG = Logger.getLogger(queryHandler.class.getClass());
	
	public boolean loginHandler(String user, String pass) {
		LOG.debug("Inside login handler.");
		boolean userAuth = false;
		dbSetup dbObj = new dbSetup();
		Connection conn = dbObj.dbInit();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String query = "SELECT * FROM USER_AUTH WHERE USER = '"+user+"' AND PASS = '"+pass+"';";
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
}
