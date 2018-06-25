package com.mb.MsiteAutomation.business;

import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.mb.MsiteAutomation.pages.MBmsite_MyAccountPage;

import io.appium.java_client.android.AndroidDriver;

public class MBmsite_MyAccountSteps extends MBmsite_MyAccountPage
{
	AndroidDriver<WebElement> androidDriver;
	GenericDbActions dbActions = new GenericDbActions();
	
	public MBmsite_MyAccountSteps(AndroidDriver<WebElement> androidDriver)
	{
		super(androidDriver);
	}

	public void selectSideTab(String sideTab) 
	{
		clickSideTab(sideTab);
	}

	public void cancelOrder(String reason, boolean orderType) 
	{
		clickCancelOrder();
		selectReason(reason);
		if(orderType)
			selectRefundType();
		clickCancelOrderPopup();
	}

}
