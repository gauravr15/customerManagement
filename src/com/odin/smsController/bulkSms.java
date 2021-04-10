package com.odin.smsController;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.odin.customerController.subscriberState;
import com.odin.dbController.queryHandler;

public class bulkSms extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8076577185046122175L;
	Logger LOG = Logger.getLogger(bulkSms.class.getClass());
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		LOG.debug("Inside send bulk sms");
		queryHandler queryObj = new queryHandler();
		try {
			LOG.error("Bulk sms is not avaiable at the moment");
			res.sendRedirect("http://"+queryObj.ipSetup()+":8080/Subscription/login.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		/*subscriberState subObj = new subscriberState();
		boolean task_performed = subObj.state();
		LOG.debug("task performed is : "+task_performed);*/
	}
}
