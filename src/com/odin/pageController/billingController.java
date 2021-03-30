package com.odin.pageController;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.odin.customerController.customerHandler;
import com.odin.customerController.validateCustomer;
import com.odin.dbController.queryHandler;
import com.odin.smsController.sendSms;




public class billingController extends HttpServlet{
	Logger LOG = Logger.getLogger(billingController.class.getClass());
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		LOG.info("inside billingController class");
		HttpSession session= req.getSession();
		if(session.getAttribute("user")!=null) {
			String name = req.getParameter("name");
			String mob = req.getParameter("mob_no");
			String service = req.getParameter("service");
			String point = req.getParameter("point");
			validateCustomer customerValidateObj = new validateCustomer();
			boolean isCustomerAvailable = customerValidateObj.customerCheck(mob);
			if(isCustomerAvailable == false) {
				customerHandler customerObj = new customerHandler();
				boolean createCustomer = customerObj.createCustomer(mob, name);
				if(createCustomer == true) {
					LOG.debug("User created successfully");
				}
				queryHandler queryObj = new queryHandler();
				boolean task_performed = queryObj.billingHandler(mob, service);
				if(task_performed == true) {
					int billAmount = 0;
					String[] serviceList = service.split(",");
					String message = "Thank you for opting for";
					for(int i = 0;i<serviceList.length;i++) {
						String[] servicesOpt = serviceList[i].trim().split(" ");
						message = message+" "+servicesOpt[0]+",";
						billAmount = billAmount +  Integer.parseInt(servicesOpt[1]);
					}
					
					
					message = message+"services. Your total bill amount is "+billAmount+". Thank you and please visit again. Regards Radiance beauty Parlour.";
					LOG.debug(message);
					sendSms smsObj = new sendSms();
					smsObj.sms(message, mob);
				}
			}
			else if (isCustomerAvailable == true) {
				LOG.debug("Customer exists");
			}
		}
			
		else {
			res.sendRedirect("http://localhost:8080/Subscription/login.html");
		}
	}
}
