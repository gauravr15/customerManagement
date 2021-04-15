package com.odin.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.odin.dbController.dbSetup;


public class startup extends HttpServlet{
	
	/**
	 * hesaabKhaataV1.1
	 */
	private static final long serialVersionUID = 5987901213242640293L;
	static Logger LOG = Logger.getLogger(startup.class.getClass());
	
	public void init() {
		LOG.debug("startup has started");
		LOG.debug("Calling timeCheck");
		timeCheck timeObj = new timeCheck();
		try {
			if(timeObj.TimeInit()==true) {
				LOG.debug("Time check has been performed successfully.....");
				cmHealth.clockHealth = true;
				LOG.debug("Clock health is : "+cmHealth.clockHealth);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error("Clock health is : "+cmHealth.clockHealth);
			LOG.error("Time Check failed .....");
			e.printStackTrace();
			System.exit(0);
		}
		ipConfig ipObj = new ipConfig();
		if(ipObj.ipSetup()==false) {
			LOG.error("Cannot set host ip");
			System.exit(0);
		}
		LOG.debug("Calling db setup");
		dbSetup dbSetupObj = new dbSetup();
		Connection conn = dbSetupObj.dbInit();
		if(conn!=null) {
			LOG.debug("Successful connection returned");
			cmHealth.dbHealth = true;
			LOG.debug("db health is : "+cmHealth.dbHealth);
		}
		else {
			LOG.error("db Health is : "+cmHealth.dbHealth);
			LOG.error("Unsuccessful connection returned");
		}
		try {
			conn.close();
			LOG.debug("Connection released successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		
		finally {
			try {
				conn.close();
				LOG.debug("Releasing DB connection");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOG.error(e);
			}
		}
	}
}
