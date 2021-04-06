package com.odin.billing;



import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.odin.dbController.queryHandler;


public class viewBill extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4014748397190617573L;
	Logger LOG = Logger.getLogger(viewBill.class.getClass());
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		LOG.debug("Inside view bill");
		String mobile = req.getParameter("mobile");
		String date = req.getParameter("date");
		HttpSession session = req.getSession();
		//HttpSession sessionDate = req.getSession();
		queryHandler queryObj = new queryHandler();
		LOG.debug("Mobile number is : "+mobile);
		LOG.debug("date is : "+date);
		if(date.isEmpty()) {
			LOG.debug("performing flow with date as null");
			session.setAttribute("customer_phone", mobile);
			try {
				LOG.debug("Redirecting to invoice.jsp");
				res.sendRedirect("http://"+queryObj.ipSetup()+":8080/Subscription/invoice.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOG.error(e);
			}
		}
	}
}
