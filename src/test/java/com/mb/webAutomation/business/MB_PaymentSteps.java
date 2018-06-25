package com.mb.webAutomation.business;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.healthkart.hkAutomation.browser.BrowserFactory;
import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.mb.webAutomation.pages.MB_CartPage;

import junit.framework.Assert;

public class MB_PaymentSteps extends MB_CartPage
{
	WebDriver driver;
	GenericDbActions dbActions = new GenericDbActions();
	
	
	public MB_PaymentSteps(WebDriver driver)
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
		while(!backToShoppingBtnPresent() || counter==5) {
			counter++;
//			if(verifyCartPageLoaded())
				clickRemoveFirstItem();
		}
		BrowserFactory.getWebDriver().manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
	}
	
	public void selectPaymentType(String paymentType)
	{
		
		clickPaymentType(paymentType);
	}

	public void verifyCaptchDisplayed() 
	{
		if((GlobalVar.jenkinsEnvironment==null && "prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) || "prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
			Assert.assertTrue(isCaptchaDisplayed());
	}

	public void enterCardDetails(Map<String, String> testdata) 
	{
		fillCardDetails(testdata);
	}

	public void proceedToPayment() 
	{
		clickProceedToPayment();	
	}

	public boolean verifyPaymentNotSuccessfullDisplayed() 
	{
		return isPaymentNotSuccessfullDisplayed();	
	}

	public void testNetBankingAccounts() 
	{
		selectNetBankingAccounts();
	}

	public void testWallets() 
	{
		selectWalletAccounts();
	}

	public void placeCODOrder() 
	{
		clickPlaceOrderButton();
	}

	

}
