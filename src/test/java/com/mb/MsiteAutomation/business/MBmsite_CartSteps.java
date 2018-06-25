package com.mb.MsiteAutomation.business;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.browser.BrowserFactory;
import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.mb.MsiteAutomation.pages.MBmsite_CartPage;

import io.appium.java_client.android.AndroidDriver;

public class MBmsite_CartSteps extends MBmsite_CartPage
{
	AndroidDriver<WebElement> androidDriver;
	GenericDbActions dbActions = new GenericDbActions();
	
	public MBmsite_CartSteps(AndroidDriver<WebElement> androidDriver)
	{
		super(androidDriver);
	}

	public void proceedToCheckout() 
	{
		staticWait(3);
		clickProceedToCheckout();
	}
	
	public void makeCartEmpty() 
	{
		BrowserFactory.getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int counter = 0;
		staticWait(5);
		while(!backToShoppingBtnPresent() || counter==5) {
			counter++;
//			if(verifyCartPageLoaded())
				clickRemoveFirstItem();
		}
		BrowserFactory.getWebDriver().manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		clickStartShoppingButton();
	}

	public void applyCouponCode(String code) 
	{
		enterCouponCode(code);
		submitCouponCode();
	}
	
	public void applyCouponCode() 
	{
		String code = dbActions.getActiveCouponCode("9"); // store id
		enterCouponCode(code);
		submitCouponCode();
	}

	public boolean verifyCouponApplied() 
	{
		staticWait(4);
		return isCouponApplied();
	}

}
