package com.healthkart.hkAutomation.util;

import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;

public class GlobalVar 
{
	public static WebDriver driver;
	public static int waitTime = 60;
	
	public static String jenkinsEnvironment = System.getProperty("Environment");
	public static String jenkinsApplicationURL = System.getProperty("ApplicationURL"); 
	public static String jenkinsRetailApplicationURL = System.getProperty("RetailApplicationURL");
	public static String jenkinsDBURL = System.getProperty("DBURL");
	
	public static String jenkinsMsiteEnvironment = System.getProperty("MsiteEnvironment");
	public static String jenkinsMsiteApplicationURL = System.getProperty("MsiteApplicationURL");
	public static String jenkinsMsiteDBURL = System.getProperty("MsiteDBURL");
	
	public static String jenkinsAndroidAppVersion=System.getProperty("AndroidAppVersion");
	public static String jenkinsAndroidPlatformVersion=System.getProperty("AndroidPlatformVersion");
	
	public static String baseAppURL;
	public static ExtentTest test ;
	public static String browserName;
	public static String OmniChnlOrderId;
}
