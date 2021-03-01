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
	public static String dbDriver = null;
	public static String dbAddress = null;
	public static String dbPort = null;
	public static String dbName = null;
	public static String dbUser = null;
	public static String dbPass = null;
	
	
	private fetchProperties() {
		
	}
	
	public static fetchProperties getInstance() {
		LOG.debug("Properties file needs to be present at "+new File(".").getAbsolutePath());
		try {
			FileReader reader = new FileReader("dbConfig.properties");
			Properties prop = new Properties();
			try {
				prop.load(reader);
				dbDriver = prop.getProperty("dbDriver");
				dbAddress = prop.getProperty("dbAddress");
				dbPort = prop.getProperty("dbPort");
				dbName = prop.getProperty("dbName");
				dbUser = prop.getProperty("dbUser");
				dbPass = prop.getProperty("dbPass");
				cmHealth obj = cmHealth.getInstance();
				obj.propCheck = true;
				LOG.debug("propCheck is : "+obj.propCheck);
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
	
}
