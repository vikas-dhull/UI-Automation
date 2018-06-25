package com.healthkart.hkMobileAutomation.pages;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

public class HK_Account_Page_Android 
{
	AndroidDriver<?> androidDriver;
	
	private By loginTextBy = By.id("com.healthkart.healthkart:id/name");
	
	public HK_Account_Page_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
	}

	public String getLoginText() 
	{
		return androidDriver.findElement(loginTextBy).getText();
	}

	
	
}
