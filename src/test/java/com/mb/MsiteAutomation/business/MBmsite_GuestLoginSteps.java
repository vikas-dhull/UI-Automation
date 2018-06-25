package com.mb.MsiteAutomation.business;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.mb.MsiteAutomation.pages.MBmsite_GuestLoginPage;

import io.appium.java_client.android.AndroidDriver;

public class MBmsite_GuestLoginSteps extends MBmsite_GuestLoginPage
{
	WebDriver driver;
	GenericDbActions dbActions = new GenericDbActions();
	
	public MBmsite_GuestLoginSteps(AndroidDriver<WebElement> androidDriver)
	{
		super(androidDriver);
	}

	public void performMobileLogin(String mobileNumber, String password) 
	{
		enterMobileNumber(mobileNumber);
		clickProceedButton();
		enterPassword(password);
		clickLogin();
	}

	public void performLoginViaOtp(String mobileNumber) 
	{
		enterMobileNumber(mobileNumber);
		clickProceedButton();
		clickLoginViaOtp();
		clickLogin();
		staticWait(5);
		String otp = dbActions.getMBOtp(mobileNumber);
		enterOtp(otp);
		submitOtp();
	}

	public void performLoginViaGoogle(String googleUserName, String googlePassword) 
	{
		loginViaGoogle(googleUserName, googlePassword);
	}

	public void performLoginViaFacebook(String fbUserName, String fbPassword) 
	{
		loginViaFacebook(fbUserName, fbPassword);
	}

}
