package com.healthkart.hkAutomation.util;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;


public class DriverSession {
	private static WebDriver driver;
	private static AppiumDriver appDriver=null;

	public static void setLastExecutionDriver(WebDriver wd) {
		driver = wd;
		GlobalVar.driver = wd;

	}	

	public static WebDriver getLastExecutionDriver() {
		return driver;
	}

	public static void setAppDriver(AppiumDriver appDrivert) {
		appDriver=appDrivert;
		
	}
	
	public static AppiumDriver getAppDriver() {
		return appDriver;
		
	}

}
