package com.odin.pageController;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.odin.dbController.queryHandler;



public class billingController extends HttpServlet{
	Logger LOG = Logger.getLogger(billingController.class.getClass());
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		LOG.info("inside billingController class");
		HttpSession session= req.getSession();
		if(session.getAttribute("user")!=null) {
			String name = req.getParameter("name");
			String mob = req.getParameter("mob_no");
			String service = req.getParameter("service");
			String bill = req.getParameter("bill");
			String point = req.getParameter("point");
			queryHandler queryObj = new queryHandler();
			if(point.isEmpty()) {
				queryObj.billingHandler(mob,bill);
			}
			else if (!point.isEmpty() || point == "0") {
				queryObj.billingHandler(mob,bill,point);
			}
		}
		else {
			res.sendRedirect("http://localhost:8080/Subscription/login.html");
		}
	}
}
