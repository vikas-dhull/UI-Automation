package com.mb.MsiteAutomation.business;

import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.mb.MsiteAutomation.pages.MBmsite_SelectAddressPage;

import io.appium.java_client.android.AndroidDriver;

public class MBmsite_SelectAddressSteps extends MBmsite_SelectAddressPage
{
	AndroidDriver<WebElement> androidDriver;
	GenericDbActions dbActions = new GenericDbActions();
	
	public MBmsite_SelectAddressSteps(AndroidDriver<WebElement> androidDriver)
	{
		super(androidDriver);
	}

	public boolean verifyAddressPageDisplayed() 
	{
		return isAddressPageDisplayed();
	}

	public void clickSavedAddress() 
	{
		selectAddress();
	}

}
