package com.odin.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;



public class fetchProperties {
	
	static Logger LOG = Logger.getLogger(fetchProperties.class.getClass());
	public static fetchProperties obj = new fetchProperties();
	private static String dbDriver = null;
	private static String dbAddress = null;
	private static String dbPort = null;
	private static String dbName = null;
	private static String dbUser = null;
	private static String dbPass = null;
	
	
	private fetchProperties() {
		
	}
	
	public static fetchProperties getInstance() {
		LOG.debug("Properties file needs to be present at "+new File(".").getAbsolutePath());
		try {
			FileReader reader = new FileReader("dbConfig.properties");
			Properties prop = new Properties();
			try {
				prop.load(reader);
				setDbDriver(prop.getProperty("dbDriver"));
				setDbAddress(prop.getProperty("dbAddress"));
				setDbPort(prop.getProperty("dbPort"));
				setDbName(prop.getProperty("dbName"));
				setDbUser(prop.getProperty("dbUser"));
				setDbPass(prop.getProperty("dbPass"));
				cmHealth.propCheck = true;
				LOG.debug("propCheck is : "+cmHealth.propCheck);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOG.error("Error while processing properties file : "+e);
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			LOG.error("Error while reading properties file : "+e);
			e.printStackTrace();
			System.exit(0);
		}
		return obj;
	}

	public String getDbDriver() {
		return dbDriver;
	}

	public static void setDbDriver(String dbDriver) {
		fetchProperties.dbDriver = dbDriver;
	}

	public String getDbAddress() {
		return dbAddress;
	}

	public static void setDbAddress(String dbAddress) {
		fetchProperties.dbAddress = dbAddress;
	}

	public String getDbPort() {
		return dbPort;
	}

	public static void setDbPort(String dbPort) {
		fetchProperties.dbPort = dbPort;
	}

	public String getDbName() {
		return dbName;
	}

	public static void setDbName(String dbName) {
		fetchProperties.dbName = dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public static void setDbUser(String dbUser) {
		fetchProperties.dbUser = dbUser;
	}

	public String getDbPass() {
		return dbPass;
	}

	public static void setDbPass(String dbPass) {
		fetchProperties.dbPass = dbPass;
	}
	
}
