package com.healthkart.hkMobileAutomation.business;

import org.openqa.selenium.WebElement;

import com.healthkart.hkMobileAutomation.pages.HK_NavigationPage_AndroidApp;

import io.appium.java_client.android.AndroidDriver;

public class HK_NavigationSteps_Android extends HK_NavigationPage_AndroidApp
{
	public HK_NavigationSteps_Android(AndroidDriver<?> androidDriver) 
	{
		super(androidDriver);
	}

	public void login()
	{
		swipeNavigationPage();
	}
	
}
