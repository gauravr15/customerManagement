package com.odin.pageController;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.odin.core.businessLogic;
import com.odin.customerController.customerHandler;
import com.odin.customerController.pointsController;
import com.odin.customerController.validateCustomer;
import com.odin.dbController.queryHandler;
import com.odin.jsResponse.responses;
import com.odin.smsController.sendSms;
//import com.odin.smsController.sendSms;




public class billingController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7768195107719600628L;
	Logger LOG = Logger.getLogger(billingController.class.getClass());
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		LOG.info("inside billingController class");
		HttpSession session= req.getSession();
		queryHandler queryObj = new queryHandler();
		if(session.getAttribute("user")!=null) {
			String name = req.getParameter("name");
			String mob = req.getParameter("mob_no");
			String service = req.getParameter("service");
			String point = req.getParameter("point");
			validateCustomer customerValidateObj = new validateCustomer();
			int billAmount = 0;
			boolean isCustomerAvailable = customerValidateObj.customerCheck(mob);
			if(isCustomerAvailable == false) {
				customerHandler customerObj = new customerHandler();
				boolean createCustomer = customerObj.createCustomer(mob, name);
				if(createCustomer == true) {
					LOG.debug("User created successfully");
				}
				
				boolean task_performed = queryObj.billingHandler(mob, service);
				if(task_performed == true) {
					billAmount = 0;
					String[] serviceList = service.split(",");
					String message = "Thank you for opting for";
					for(int i = 0;i<serviceList.length;i++) {
						String[] servicesOpt = serviceList[i].trim().split(" ");
						message = message+" "+servicesOpt[0]+",";
						billAmount = billAmount +  Integer.parseInt(servicesOpt[1]);
					}
					message = message+"services. Your total bill amount is "+billAmount+". Thank you and please visit again. Regards Radiance beauty Parlour.";
					LOG.debug(message);
					//sendSms smsObj = new sendSms();
					//smsObj.sms(message, mob);
				}
			}
			else if (isCustomerAvailable == true) {
				if(point.isEmpty() || point == Integer.toString(0)) {
					LOG.debug("Customer exists");
					boolean task_performed = queryObj.billingHandler(mob, service);
					if(task_performed == true) {
						LOG.debug("Billing successfully done");
						String[] serviceList = service.split(",");
						String message = "Thank you for opting for";
						for(int i = 0;i<serviceList.length;i++) {
							String[] servicesOpt = serviceList[i].trim().split(" ");
							message = message+" "+servicesOpt[0]+",";
							billAmount = billAmount +  Integer.parseInt(servicesOpt[1]);
						}
						message = message+"services. Your total bill amount is "+billAmount+". Thank you and please visit again. Regards Radiance beauty Parlour.";
						LOG.debug(message);
						businessLogic.getInstance();
						if(businessLogic.isSend_sms() == true) {
							sendSms smsObj = new sendSms();
							smsObj.sms(message, mob);
						}
					}
					else {
						LOG.error("Something went wrong");
					}
				}
				else if(!point.isEmpty() || point != Integer.toString(0)){
					pointsController pointObj = new pointsController();
					boolean IS_POINT_AVAILABLE = pointObj.pointsCheck(mob, point);
					if(IS_POINT_AVAILABLE == true) {
						LOG.debug("Points available");
						boolean task_performed = queryObj.billingHandler(mob, service, point);
						if(task_performed == true) {
							LOG.debug("Billing successfully done");
							String[] serviceList = service.split(",");
							String message = "Thank you for opting for";
							for(int i = 0;i<serviceList.length;i++) {
								String[] servicesOpt = serviceList[i].trim().split(" ");
								message = message+" "+servicesOpt[0]+",";
								billAmount = billAmount +  Integer.parseInt(servicesOpt[1]);
							}
							int newBill = billAmount - Integer.parseInt(point);
							message = message+"services. Your total bill amount is "+newBill+". Thank you and please visit again. Regards Radiance beauty Parlour.";
							LOG.debug(message);
							businessLogic.getInstance();
							if(businessLogic.isSend_sms() == true) {
								sendSms smsObj = new sendSms();
								smsObj.sms(message, mob);
							}
						}
						else {
							LOG.error("Something went wrong");
						}
					}
					else {
						responses resObj = new responses();
						resObj.service(req, res);
						LOG.debug("Insufficient points");
					}
				}
			}
		}
			
		else {
			res.sendRedirect("http://"+queryObj.ipSetup()+":8080/Subscription/login.html");
		}
	}
}
