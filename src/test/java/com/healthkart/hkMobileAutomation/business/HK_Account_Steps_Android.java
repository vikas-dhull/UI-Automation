package com.healthkart.hkMobileAutomation.business;

import com.healthkart.hkMobileAutomation.pages.HK_Account_Page_Android;

import io.appium.java_client.android.AndroidDriver;

public class HK_Account_Steps_Android 
{
	AndroidDriver<?> androidDriver;
	HK_Account_Page_Android accountPage;
	
	public HK_Account_Steps_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		accountPage = new HK_Account_Page_Android(androidDriver);
	}

	public boolean verifyLoggedInFromAccountPage() 
	{
		if(accountPage.getLoginText().contains("Hi"))
				return true;
		else
			return false;
	}

}
