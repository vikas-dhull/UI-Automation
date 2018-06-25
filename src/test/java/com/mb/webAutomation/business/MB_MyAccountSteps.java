package com.mb.webAutomation.business;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.mb.webAutomation.pages.MB_MyAccountPage;

public class MB_MyAccountSteps extends MB_MyAccountPage
{
	WebDriver driver;
	GenericDbActions dbActions = new GenericDbActions();
	
	public MB_MyAccountSteps(WebDriver driver)
	{
		super(driver);
	}

	public void selectSideTab(String sideTab) 
	{
		clickSideTab(sideTab);
	}

	public void cancelOrder(String reason) 
	{
		clickCancelOrder();
		selectReason(reason);
		clickCancelOrderPopup();
	}

}
