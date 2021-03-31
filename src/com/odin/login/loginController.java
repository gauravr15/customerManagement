package com.odin.login;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.odin.core.cmHealth;
import com.odin.core.ipConfig;
import com.odin.dbController.queryHandler;



public class loginController extends HttpServlet{
	
	Logger LOG =Logger.getLogger(loginController.class.getClass());
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		cmHealth obj = cmHealth.getInstance();
		HttpSession session = req.getSession();
		if(obj.propCheck == true && obj.clockHealth == true && obj.dbHealth == true) {
			
				LOG.debug("login servlet started.");
				String UserName = req.getParameter("username");
				String Password = req.getParameter("password");
				LOG.debug("Logging in as "+UserName+" and password as "+Password);
				queryHandler queryObj = new queryHandler();
				boolean userAuth = queryObj.loginHandler(UserName, Password);
				if(userAuth == true) {
					String ip = queryObj.ipSetup();
					session.setAttribute("user", UserName);
					LOG.debug("logged in as : "+session.getAttribute("user"));
					LOG.debug("User being redirected to home.html");
					res.sendRedirect("http://"+ip+":8080/Subscription/home.html");
				}
				else {
					LOG.error("no such user found.");
				}
		}
		else {
			LOG.error("Please check system health from log.");
		}
		
	}
}
