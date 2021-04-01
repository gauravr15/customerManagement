package com.odin.servlet.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.odin.dbController.queryHandler;



/**
 * Servlet Filter implementation class homeFilter
 */
@WebFilter({"/home.html","/billing.html"})

public class homeFilter implements Filter {

    /**
     * Default constructor. 
     */
	
	Logger LOG = Logger.getLogger(homeFilter.class.getClass());
	
    public homeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		LOG.debug(session.getAttribute("user"));
		queryHandler queryObj = new queryHandler();
		String ip = queryObj.ipSetup();
		if(session.getAttribute("user")!=null) {
			LOG.debug("user logged in");
			chain.doFilter(request, response);
		}
		else {
			HttpServletResponse res = (HttpServletResponse)response;
			res.sendRedirect("http://"+ip+":8080/Subscription/login.html");
			LOG.error("user needs to log in first");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
