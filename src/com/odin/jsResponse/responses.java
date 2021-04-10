package com.odin.jsResponse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.odin.dbController.queryHandler;

public class responses extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7233825983134579536L;
	Logger LOG = Logger.getLogger(responses.class.getClass());
	
	public void service(HttpServletRequest req, HttpServletResponse res) {
		LOG.debug("Inside response java");
		queryHandler queryObj = new queryHandler();
		String redirectURL = queryObj.ipSetup();
		try {
			PrintWriter out = res.getWriter();
			out.print("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<body>\r\n"
					+ "<script>\r\n"
					+ "alert(\"Please check points that can be redeemed.\");\r\n"
					+ "window.location=\"http://"+redirectURL+":8080/Subscription/billing.html\"; \r\n"
					+ "</script>\r\n"
					+ "</body>\r\n"
					+ "</html>");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
	}

}
