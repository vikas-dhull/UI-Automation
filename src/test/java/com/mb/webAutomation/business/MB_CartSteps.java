package com.mb.webAutomation.business;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.healthkart.hkAutomation.browser.BrowserFactory;
import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.mb.webAutomation.pages.MB_CartPage;

public class MB_CartSteps extends MB_CartPage
{
	WebDriver driver;
	GenericDbActions dbActions = new GenericDbActions();
	
	public MB_CartSteps(WebDriver driver)
	{
		super(driver);
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
	}

	public void applyCouponCode(String code) 
	{
		enterCouponCode(code);
		submitCouponCode();
	}
	
	public boolean applyCouponCode() 
	{
		String code = dbActions.getActiveCouponCode("9"); // store id
		if(code!=null)
		{
			enterCouponCode(code);
			submitCouponCode();
			return true;
		}
		else
		{
			reportLogAndPrintInConsole("NO ACTIVE COUPON CODES FOUND IN DB !!!!");
			return false;
		}
	}

	public boolean verifyCouponApplied() 
	{
		staticWait(4);
		return isCouponApplied();
	}

}
