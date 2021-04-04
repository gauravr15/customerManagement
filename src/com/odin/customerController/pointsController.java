package com.odin.customerController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.odin.core.businessLogic;
import com.odin.dbController.dbSetup;

public class pointsController {
	
	Logger LOG = Logger.getLogger(pointsController.class.getClass());
	
	public boolean pointsCheck(String mob, String point) {
		LOG.debug("Inside points check");
		boolean task_performed = false;
		dbSetup dbObj = new dbSetup();
		Connection conn = dbObj.dbInit();
		Statement stmt= null;
		ResultSet rs = null;
		int total_point = 0;
		int redeem_point = Integer.parseInt(point);
		String query = "SELECT * FROM CUSTOMER_INFO WHERE MOBILE_NO = '"+mob+"';";
		try {
			LOG.debug("Query to fire : "+query);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				total_point = Integer.parseInt(rs.getString("TOTAL_POINTS"));
			}
			if(redeem_point > businessLogic.getMax_point()){
				LOG.debug("max redeem point is set as : "+businessLogic.getMax_point());
				LOG.debug("max redeemable point exceeded");
				task_performed = false;
			}
			else if(total_point >= redeem_point) {
				LOG.debug("desired redeem points is less than max redeem points");
				task_performed = true;
			}
			else {
				task_performed = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
				LOG.debug("Releasing db connection");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOG.error(e);
			}
		}
		return task_performed;
	}
}
