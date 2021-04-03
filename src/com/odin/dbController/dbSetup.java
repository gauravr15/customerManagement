package com.odin.dbController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import org.apache.log4j.Logger;

import com.odin.core.fetchProperties;


public class dbSetup {
	
	Logger LOG = Logger.getLogger(dbSetup.class.getClass());
	
	public Connection dbInit() {
		LOG.debug("inside db setup ");
		//fetchProperties propObj = fetchProperties.getInstance();
		String dbDriver = fetchProperties.getInstance().getDbDriver();
		String dbAddress = fetchProperties.getInstance().getDbAddress();
		String dbPort = fetchProperties.getInstance().getDbPort();
		String dbName = fetchProperties.getInstance().getDbName();
		String dbUser = fetchProperties.getInstance().getDbUser();
		String dbPass = fetchProperties.getInstance().getDbPass();
		String url = dbAddress+dbPort+dbName;
		Connection conn = null;
		
		LOG.debug("DB DRIVER : "+dbDriver);
		LOG.debug("DB URL TO HIT : "+url);
		LOG.debug("Logging into db with usename as : "+dbUser+" and password as : "+dbPass);
		try {
			Class.forName(dbDriver);
			try {
				conn = DriverManager.getConnection(url,dbUser,dbPass);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOG.error("Unable to create connection.");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			LOG.error("Unable to fetch db driver class.");
			e.printStackTrace();
		}
		return conn;
	}
}
