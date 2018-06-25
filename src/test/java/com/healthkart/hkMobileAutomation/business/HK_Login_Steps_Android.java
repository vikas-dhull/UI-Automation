package com.healthkart.hkMobileAutomation.business;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkMobileAutomation.pages.HK_Login_Page_Android;

import commonStepsAndMethods.ILoginStepsCommon;
import io.appium.java_client.android.AndroidDriver;

public class HK_Login_Steps_Android
{
	AndroidDriver<?> androidDriver;
	HK_Login_Page_Android loginPage;
	String otp;
	GenericDbActions dbActions;
	
	public HK_Login_Steps_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		loginPage = new HK_Login_Page_Android(androidDriver);
		dbActions = new GenericDbActions();
	}
	
	public void performLogin(String mobileNumber, String password) 
	{
		loginPage.enterMobileNumber(mobileNumber);
		loginPage.enterPassword(password);
		loginPage.submitLogin();
	}

	public void performLoginWithMobileOtp(String mobileNumber) 
	{
		loginPage.enterMobileNumber(mobileNumber);
		loginPage.clickLoginViaOtp();
		otp = dbActions.getOtp(mobileNumber,1);
		loginPage.setOtp(otp);
		loginPage.submitLogin();
	}

	public void performLoginWithGoogle() 
	{
		loginPage.clickGoogleButton();
		loginPage.selectGoogleAccount();
	}

	public void performLoginWithFacebook(String fbUserName, String fbPassword) 
	{
		loginPage.clickFacebookButton();
		loginPage.enterFacebookIdPassword(fbUserName, fbPassword);
		loginPage.confirmFacebookLogin();
	}

	public void performLoginWithTrueCaller() 
	{
		loginPage.clickTrueCallerButton();
		loginPage.continueTrueCaller();
	}
	
	
	
	

}
