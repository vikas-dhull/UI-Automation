package com.mb.MsiteAutomation.business;

import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.mb.MsiteAutomation.pages.MBmsite_PdpPage;

import io.appium.java_client.android.AndroidDriver;

public class MBmsite_PdpSteps extends MBmsite_PdpPage
{
	AndroidDriver<WebElement> androidDriver;
	GenericDbActions dbActions = new GenericDbActions();
	
	public MBmsite_PdpSteps(AndroidDriver<WebElement> androidDriver)
	{
		super(androidDriver);
	}

	public void addToCart() 
	{
		clickAddToCart();
	}
	

}
