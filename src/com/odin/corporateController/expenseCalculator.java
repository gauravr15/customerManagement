package com.odin.corporateController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.odin.billing.billCalculator;
import com.odin.dbController.dbSetup;
import com.odin.dbController.queryHandler;

public class expenseCalculator extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2710956567212756887L;
	Logger LOG =Logger.getLogger(expenseCalculator.class.getClass());
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		LOG.debug("Inside expense calculator");
		HttpSession session = req.getSession();
		String user = (String)session.getAttribute("user");
		String service = req.getParameter("service");
		LOG.debug("Expenses by user : "+user+" are : "+service);
		billCalculator billObj = new billCalculator();
		int amount = billObj.billAmount(service);
		dbSetup dbObj = new dbSetup();
		Connection conn = dbObj.dbInit();
		Statement stmt = null;
		String query = "INSERT INTO EXPENSE VALUES ('"+user+"',NOW(),'"+service+"','"+amount+"')";
		LOG.debug("Query to fire : "+query);
		try {
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(query);
			if(result != 0) {
				queryHandler queryObj = new queryHandler();
				LOG.debug("Expense recorded successfully");
				PrintWriter out = res.getWriter();
				out.print("<!DOCTYPE html>\r\n"
						+ "				<html>\r\n"
						+ "				<body>\r\n"
						+ "				<script>\r\n"
						+ "				alert(\"Expense recorded\");\r\n"
						+"				window.location=\"http://"+queryObj.ipSetup()+":8080/Subscription/expenses.html\";"
						+ "				</script>\r\n"
						+ "				</body>\r\n"
						+ "				</html>");
			}
			else {
				LOG.error("Something went wrong");
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
		
	}
}
