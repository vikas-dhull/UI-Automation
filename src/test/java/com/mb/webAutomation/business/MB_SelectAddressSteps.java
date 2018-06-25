package com.mb.webAutomation.business;

import org.openqa.selenium.WebDriver;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.mb.webAutomation.pages.MB_SelectAddressPage;

public class MB_SelectAddressSteps extends MB_SelectAddressPage
{
	WebDriver driver;
	GenericDbActions dbActions = new GenericDbActions();
	
	public MB_SelectAddressSteps(WebDriver driver)
	{
		super(driver);
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
