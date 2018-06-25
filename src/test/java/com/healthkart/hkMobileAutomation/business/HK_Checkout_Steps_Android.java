package com.healthkart.hkMobileAutomation.business;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkMobileAutomation.pages.HK_Checkout_Page_Android;

import io.appium.java_client.android.AndroidDriver;

public class HK_Checkout_Steps_Android
{
	AndroidDriver<?> androidDriver;
	HK_Checkout_Page_Android checkoutPage;
	String otp;
	GenericDbActions dbActions;
	CommonFunctions common;
	
	public HK_Checkout_Steps_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		checkoutPage = new HK_Checkout_Page_Android(androidDriver);
		dbActions = new GenericDbActions();
		common = new CommonFunctions();
	}
	
	public void checkoutLoginWithMobileAndPassword(String mobileNumber, String password)
	{
		try
		{
		checkoutPage.enterMobileNumber(mobileNumber);
		checkoutPage.clickContinueButton();
		checkoutPage.enterPassword(password);
		checkoutPage.clickLoginButton();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			common.captureScreenshot("AndroidApp", "checkoutLoginWithMobileAndPassword", androidDriver);
		}
	}

	public boolean verifyLoggedInFromGuestPage() 
	{
		if(checkoutPage.verifySelectAddressPageDisplayed())
			return true;
		else
			return false;
	}

	public void checkoutLoginWithMobilOtp(String mobileNumber) 
	{
		checkoutPage.enterMobileNumber(mobileNumber);
		checkoutPage.clickContinueButton();
		checkoutPage.clickLoginViaOtp();
		checkoutPage.waitForLoginWindow();
		otp = dbActions.getOtp(mobileNumber,1);
		checkoutPage.setOtp(otp);
		checkoutPage.clickDoneButton();
	}

	public void selectAddress() 
	{
		checkoutPage.selectAddress();
	}

	public void proceedToPay() 
	{
		checkoutPage.proceedToPay();
	}


}
