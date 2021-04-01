package com.odin.smsController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.odin.dbController.dbSetup;





public class sendSms {
	
	Logger LOG = Logger.getLogger(sendSms.class.getClass());
	
	public void sms(String message,String number)
	{
		dbSetup dbObj = new dbSetup();
		Connection conn = dbObj.dbInit();
		Statement stmt = null;
		try
		{
		String query = "INSERT INTO send_sms values (NOW(),'"+number+"','"+message+"')";
		LOG.debug("Query to fire : "+query);
		stmt = conn.createStatement();
		int result = stmt.executeUpdate(query);
		if(result != 0) {
			LOG.debug("DATA INSERTED IN SEND SMS TABLE");
		}
		else {
			LOG.debug("UNABLE TO INSERT DATA IN SEND SMS TABLE");
		}
		String apiKey="C18ZxQSlaz36R7rBJjwFcbHnGYuM5fpsD9AhXT2VyvIeKioW0EvQVtbzMAeoyGwS4u0dKOaH3nBZgi1h";
		String sendId="FSTSMS";
		//important step...
		message=URLEncoder.encode(message, "UTF-8");
		String language="english";
		
		String route="p";
		
		
		String myUrl="https://www.fast2sms.com/dev/bulk?authorization="+apiKey+"&sender_id="+sendId+"&message="+message+"&language="+language+"&route="+route+"&numbers="+number;
		
		//sending get request using java..
		
		URL url=new URL(myUrl);
		
		HttpsURLConnection con=(HttpsURLConnection)url.openConnection();
		
		
		con.setRequestMethod("GET");
		
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("cache-control", "no-cache");
		LOG.debug("Wait..............");
		
		int code=con.getResponseCode();
		
		LOG.debug("Response code : "+code);
		
		StringBuffer response=new StringBuffer();
		
		BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		while(true)
		{
			String line=br.readLine();
			if(line==null)
			{
				break;
			}
			response.append(line);
		}
		
		LOG.debug(response);
		
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
				conn.close();
				LOG.debug("Connection released successfully");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOG.error(e);
			}
		}
	}
	
}
