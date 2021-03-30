package com.odin.billing;

import org.apache.log4j.Logger;

public class billCalculator {
	
	Logger LOG = Logger.getLogger(billCalculator.class.getClass());
	
	public int billAmount(String service) {
		int amount = 0;
		LOG.debug("Inside billAmount");
		String srv = service;
		LOG.debug("Services opted are : "+srv);
		String[] serviceList = srv.split(",");
		int count =0;
		while(count<serviceList.length) {
			String[] product = serviceList[count].trim().split(" ");
			amount = amount + Integer.parseInt(product[1]);
			count++;
		}
		LOG.debug("total bill amount : "+amount);
		return amount;
	}
}
