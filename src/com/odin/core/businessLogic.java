package com.odin.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.odin.dbController.dbSetup;

public class businessLogic {
	private static int business_type= -1;
	private static int point_ratio = -1;
	private static int max_point = -1;
	private static boolean send_sms = false;
	public static businessLogic obj = new businessLogic();
	static Logger LOG =Logger.getLogger(businessLogic.class.getClass());
	
	private businessLogic() {
		
	}
	public static businessLogic getInstance() {
		LOG.debug("Inside business logic");
		dbSetup dbObj = new dbSetup();
		Connection conn = dbObj.dbInit();
		Statement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM CONFIG WHERE PARAM_NAME = 'POINT_RATIO';";
		LOG.debug("Query to fire : "+query);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				point_ratio = Integer.parseInt(rs.getString("PARAM_VALUE"));
				LOG.debug("Points ratio is set as : "+point_ratio+"/1");
			}
			query = "SELECT * FROM CONFIG WHERE PARAM_NAME = 'MAX_POINT';";
			LOG.debug("Query to fire : "+query);
			rs= stmt.executeQuery(query);
			while(rs.next()) {
				max_point = Integer.parseInt(rs.getString("PARAM_VALUE"));
				LOG.debug(query);
			}
			query = "SELECT * FROM CONFIG WHERE PARAM_NAME = 'SEND_SMS';";
			LOG.debug("Query to fire : "+query);
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				setSend_sms(Boolean.parseBoolean(rs.getString("PARAM_VALUE")));
				LOG.debug("Value of send_sms is : "+isSend_sms());
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
				return obj;
				}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				LOG.error(e);
			}
			
		}
		return obj;
	}
	public static int getBusiness_type() {
		return business_type;
	}
	public static void setBusiness_type(int business_type) {
		businessLogic.business_type = business_type;
	}
	public static int getPoint_ratio() {
		return point_ratio;
	}
	public static void setPoint_ratio(int point_ratio) {
		businessLogic.point_ratio = point_ratio;
	}
	public static int getMax_point() {
		return max_point;
	}
	public static void setMax_point(int max_point) {
		businessLogic.max_point = max_point;
	}
	public static boolean isSend_sms() {
		return send_sms;
	}
	public static void setSend_sms(boolean send_sms) {
		businessLogic.send_sms = send_sms;
	}
}
