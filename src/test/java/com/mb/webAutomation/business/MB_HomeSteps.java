package com.mb.webAutomation.business;

import org.openqa.selenium.WebDriver;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.mb.webAutomation.pages.MB_HomePage;

public class MB_HomeSteps extends MB_HomePage
{
	WebDriver driver;
	GenericDbActions dbActions = new GenericDbActions();
	
	public MB_HomeSteps(WebDriver driver)
	{
		super(driver);
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
		clickSignUpButton();
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

}
