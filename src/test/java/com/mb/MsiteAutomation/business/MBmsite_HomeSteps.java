package com.mb.MsiteAutomation.business;

import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.browser.BrowserFactory;
import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.mb.MsiteAutomation.pages.MBmsite_HomePage;

import io.appium.java_client.android.AndroidDriver;

public class MBmsite_HomeSteps extends MBmsite_HomePage
{
	AndroidDriver<WebElement> androidDriver;
	GenericDbActions dbActions = new GenericDbActions();
	
	public MBmsite_HomeSteps(AndroidDriver<WebElement> androidDriver)
	{
		super(androidDriver);
	}
	
	public void proceedToLogin() 
	{
		clickNewUserButton();
	}

	public void performGoogleLogin(String googleUserName, String googlePassword) 
	{
		loginViaGoogle(googleUserName, googlePassword);
	}

	public boolean verifyUserLoggedIn() 
	{
		return checkUserLoggedIn();
	}
	
	public boolean verifyUserLoggedOut()
	{
		return checkUserLoggedOut();
	}

	public void performLogout() 
	{
		clickLogout();
	}

	public void performFacebookLogin(String fbMailId, String fbPassword) 
	{
		loginViaFacebook(fbMailId, fbPassword);
	}

	public void perfromLoginViaOTP(String mobileNumber) 
	{
		clickLoginViaOTP();
		enterMobileNumber(mobileNumber);
		clickLoginButton();
		staticWait(5);
		String otp = dbActions.getMBOtp(mobileNumber);
		enterOTP(otp);
		clickContinueButton();
	}

	public void performMobileLogin(String mobileNumber, String password) 
	{
		enterMobileNumber(mobileNumber);
		enterPassword(password);
		clickLoginButton();
	}

	public void selectVariantFromTopSellerMenu(String variantName) 
	{
		selectVariant(variantName);
	}

	public void performSignUp(String mobileNumber) 
	{
//		clickSignUpButton();
		checkNewCustomer();
		dbActions.deleteSignUpUserDataMB(mobileNumber);
		enterMobileNumberForSignup(mobileNumber);
		clickContinueSignUpButton();
		String otp = dbActions.getMBOtp(mobileNumber);
		enterSignUpOtp(otp);
		enterSignUpPassword("hk@12345");
		clickSignUpOtpButton();
	}

	public void openCartPage() 
	{
		BrowserFactory.getAndroidDriver().navigate().refresh();
		clickCartIcon();
	}

	public boolean verifyAuthenticationDiv() 
	{
		return isAuthenticationDivDisplayed();
	}

	public boolean verifyAuthenticationCode() 
	{
		String productCode = dbActions.fetchCodeForMBAuthentication();
		enterAuthenticationCode(productCode);
		submitAuthenticationCode("9552360289");
		return verifyGenuineProductDisplayed();
	}

	public boolean verifyProteinLabCertification() 
	{
		clickProteinLabCertificateButton();
		return verifyLabReportDisplayed();
	}

	public void selectCheckYourProfile() 
	{
		clickCheckYourProfile();
	}

	public void closeVideoBanner() 
	{
		clickCloseVideoBanner();
	}

	public void selectVariantFromLeftPanel(String variantName) 
	{
		clickLeftPanel();
		clickShopButton();
		clickVariantFromLeftPanel(variantName);
	}

}
