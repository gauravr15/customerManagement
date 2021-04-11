package com.odin.corporateController;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.odin.dbController.queryHandler;

public class logout extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2625459915769060403L;
	Logger LOG = Logger.getLogger(logout.class.getClass());
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		queryHandler queryObj = new queryHandler();
		session.setAttribute("user", null);
		try {
			if(session.getAttribute("user") == null) {
				LOG.debug("User Logged out.");
				res.sendRedirect("http://"+queryObj.ipSetup()+":8080/Subscription/login.html");
			}
			else {
				res.sendRedirect("http://"+queryObj.ipSetup()+":8080/Subscription/home.html");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
	}

}
