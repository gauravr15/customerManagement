package com.odin.corporateController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.odin.dbController.queryHandler;

public class earningCalculator extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2204697409600656327L;
	
	Logger LOG =Logger.getLogger(earningCalculator.class.getClass());
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		LOG.debug("Inside earning calculator");
		queryHandler queryObj = new queryHandler();
		HttpSession session = req.getSession();
		String fromDate = req.getParameter("fromDate").trim();
		String toDate = req.getParameter("toDate").trim();
		if(fromDate.equals(toDate)) {
			LOG.debug("Date before Addition: "+toDate);
			//Specifying date format that matches the given date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			try{
			   //Setting the date to the given date
			   c.setTime(sdf.parse(toDate));
			}catch(ParseException e){
				e.printStackTrace();
			 }
			   
			//Number of Days to add
			c.add(Calendar.DAY_OF_MONTH, 1);  
			//Date after adding the days to the given date
			String newDate = sdf.format(c.getTime());  
			//Displaying the new Date after addition of Days
			toDate = newDate;
			LOG.debug("Date after Addition: "+toDate);
			session.setAttribute("fromDate", fromDate);
			session.setAttribute("toDate", toDate);
			try {
				LOG.debug("redirecting to earning calculator page");
				res.sendRedirect("http://"+queryObj.ipSetup()+":8080/Subscription/earning.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOG.error(e);
			}
		}
		else if(fromDate.compareTo(toDate) < 0) {
			LOG.debug("Date before Addition: "+toDate);
			//Specifying date format that matches the given date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			try{
			   //Setting the date to the given date
			   c.setTime(sdf.parse(toDate));
			}catch(ParseException e){
				e.printStackTrace();
			 }
			   
			//Number of Days to add
			c.add(Calendar.DAY_OF_MONTH, 1);  
			//Date after adding the days to the given date
			String newDate = sdf.format(c.getTime());  
			//Displaying the new Date after addition of Days
			toDate = newDate;
			LOG.debug("Date after Addition: "+toDate);
			LOG.debug("Fetching eaning from "+fromDate+" to "+toDate);
			session.setAttribute("fromDate", fromDate);
			session.setAttribute("toDate", toDate);
			try {
				LOG.debug("redirecting to earning calculator page");
				res.sendRedirect("http://"+queryObj.ipSetup()+":8080/Subscription/earning.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOG.error(e);
			}
		}
		else {
			try {
				LOG.debug("redirecting to earning calculator page");
				res.sendRedirect("http://"+queryObj.ipSetup()+":8080/Subscription/earning.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOG.error(e);
			}
		}
	}
}
