package com.mb.webAutomation.business;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.mb.webAutomation.pages.MB_GuestLoginPage;

public class MB_GuestLoginSteps extends MB_GuestLoginPage
{
	WebDriver driver;
	GenericDbActions dbActions = new GenericDbActions();
	
	public MB_GuestLoginSteps(WebDriver driver)
	{
		super(driver);
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
