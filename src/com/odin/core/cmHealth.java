package com.odin.core;

public class cmHealth {
	
	public static cmHealth obj = new cmHealth() ;
	
	public static boolean clockHealth = false;
	public static boolean propCheck = false;
	public static boolean dbHealth = false;
	
	private cmHealth(){
		
	}
	
	public static cmHealth getInstance() {
		return obj;
	}
}
