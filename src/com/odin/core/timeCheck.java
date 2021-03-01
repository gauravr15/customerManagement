package com.odin.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import org.apache.log4j.Logger;



public class timeCheck {
	public boolean TimeInit() throws IOException {
		Logger LOG = Logger.getLogger(timeCheck.class.getClass());
		URL url = new URL("https://www.google.com/");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String localTime = dtf.format(now);
		String globalTime = df.format(conn.getDate());
		LOG.debug("System time is : "+dtf.format(now));
		LOG.debug("World time is : "+df.format(conn.getDate()));
		LOG.debug(conn.getResponseCode());
		if(localTime.equals(globalTime)) {
			LOG.debug("Time is same");
			return true;
		}
		else {
			LOG.error("Time mismatch. Please connect to internet and correct your system date and time.");
			return false;
		}
		
	}
}
