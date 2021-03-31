package com.odin.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

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
		cmHealth obj = cmHealth.getInstance();
		LOG.debug("startup has started");
		LOG.debug("Calling timeCheck");
		timeCheck timeObj = new timeCheck();
		try {
			if(timeObj.TimeInit()==true) {
				LOG.debug("Time check has been performed successfully.....");
				obj.clockHealth = true;
				LOG.debug("Clock health is : "+obj.clockHealth);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error("Clock health is : "+obj.clockHealth);
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
			obj.dbHealth = true;
			LOG.debug("db health is : "+obj.dbHealth);
		}
		else {
			LOG.error("db Health is : "+obj.dbHealth);
			LOG.error("Unsuccessful connection returned");
		}
	}
}
