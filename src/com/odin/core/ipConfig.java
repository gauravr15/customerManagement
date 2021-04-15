package com.odin.core;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.odin.dbController.dbSetup;

public class ipConfig {
	
	Logger LOG = Logger.getLogger(ipConfig.class.getClass());
	    public boolean ipSetup(){
	    	boolean task_performed = false;
	        InetAddress localhost = null;
	        dbSetup dbObj = new dbSetup();
			Connection conn = dbObj.dbInit();
			Statement stmt = null;
			try {
				localhost = InetAddress.getLocalHost();
				LOG.debug("Host IP Address : " +(localhost.getHostAddress()).trim());
				String query = "UPDATE CONFIG SET PARAM_VALUE = '"+localhost.getHostAddress().trim()+"' WHERE PARAM_NAME = 'HOST_IP';";
				LOG.debug("Query to fire : "+query);
				stmt = conn.createStatement();
				int result = stmt.executeUpdate(query);
				LOG.debug("Result is "+result);
				if(result !=0) {
					task_performed = true;
				}
			} catch (UnknownHostException | SQLException e) {
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
			return task_performed;
	    }
}
