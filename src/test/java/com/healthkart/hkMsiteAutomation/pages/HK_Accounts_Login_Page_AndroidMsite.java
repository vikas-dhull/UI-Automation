package com.healthkart.hkMsiteAutomation.pages;

import org.openqa.selenium.By;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

public class HK_Accounts_Login_Page_AndroidMsite {
	
	AndroidDriver<?> androidDriver;
	CommonFunctions comnFunc;
	
	private By accountsLoginText = By.xpath("//div[contains(text(),'Before you proceed Log In, it saves time')]");
	
	private By mobileNumberField = By.xpath("//input[@name='email']");
	private By passwordField = By.xpath("//form[@id='signInForm']//input[@id='password']");
	private By loginSubmitButton = By.xpath("//form[@id='signInForm']//input[@name='login']");
	
	private By loginViaOtp = By.xpath("//form[@id='signInForm']//a[@id='loginViaOTP']");
	private By loginViaOtpText = By.xpath("//div[@id='numberVerificationForm']//div[contains(@class,'loginVerifiedCase')]//h3[contains(text(),'Login Via OTP')]");
    private By enterOtpForLogin = By.xpath("//input[@id='otp']");
    private By loginViaOtpSubmitDone = By.xpath("//form[@id='phoneNumberVerificationForm']//input[contains(@class,'enterotpotp_done')]");
	
	private By loginWithLabel = By.xpath("//div[text()='Or Log In with']");
	private By googleLoginBtn = By.xpath("//div[@id='customBtnLoginAccounts']//span[text()='Google']");
	private By facebookLoginBtn = By.xpath("//div[@id='customFbLoginAccounts']//span[text()='Facebook']");
	
	private By forgotPassword = By.xpath("//form[@id='signInForm']//a[text()='Forgot password?']");
	private By forgotYourPasswordText = By.xpath("//div[@id='forgotPassword1']//h3[contains(text(),'Forgot your password')]");
	private By forgotPasswordMobileField = By.xpath("//input[@name='email']");
	private By forgotPasswordContinue = By.xpath("//input[@id='sendEmail']");
	private By enterOtpFYPFeild = By.xpath("//input[@id='OTP']");
	private By forgotYourPasswordOtpSubmitDone = By.xpath("//input[@id='sendEmail1']");
	private By resetNewPasswordField = By.xpath("//input[@name='newPassword']");
	private By resetConfirmNewPasswordField = By.xpath("//input[@name='confirmPassword']");
	private By resetPasswordSubmitDoneBtn = By.xpath("//input[@id='sendEmail2']");
	
	private By closeNotifyVisitorOverlay = By.xpath("//a[contains(@id,'-close-button')]");

	
	public HK_Accounts_Login_Page_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		comnFunc = new CommonFunctions();
	}
	
	/**
	 * Handle notify visitor[For PROD ENV only.]
	 */
	public void clickCloseNotifyVisitorOverlay() {
	    /*if(comnFunc.isElementPresentBy(androidDriver, closeNotifyVisitorOverlay))
	       	comnFunc.clickBy(closeNotifyVisitorOverlay);*/
		WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);
	}
	
	/**
	 * Verify Accounts' Login Page loaded successfully.
	 * @return
	 */
	public boolean verifyAccountsLoginPageLoaded() 
	{
		/*if(GlobalVar.jenkinsEnvironment==null)        // Handle notify visitor overlay [For PROD ENV only.]
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				//clickCloseNotifyVisitorOverlay();
				WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);			
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			//clickCloseNotifyVisitorOverlay();
			WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);
		}*/
		
		WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);
		
		return comnFunc.isElementPresentBy(androidDriver, accountsLoginText);
	}
	
	/**
	 * Enter Mobile Number.
	 * @param mobileNumber
	 */
	public void enterMobileNumber(String mobileNumber)
	{	    
	    comnFunc.sendKeysThroughJavaScript(androidDriver,androidDriver.findElement(mobileNumberField),mobileNumber);
	}

	/**
	 * Enter Password.
	 * @param password
	 */
	public void enterPassword(String password) 
	{
		comnFunc.sendKeysThroughJavaScript(androidDriver,androidDriver.findElement(passwordField),password);
	}
	
	/**
	 * Submit Login.
	 */
	public void submitLogin() 
	{
		comnFunc.click(androidDriver.findElement(loginSubmitButton));
	}
	
	/**
	 * Login via OTP.
	 */
	public void clickLoginViaOtp() {
		comnFunc.click(androidDriver.findElement(loginViaOtp));
	}
	
	/**
	 * Enter OTP for Login.
	 * @param otp
	 */
	public void enterOtpForLogin(String otp) 
	{
		if(comnFunc.isElementPresentBy(androidDriver, loginViaOtpText))
		{
			comnFunc.sendKeysThroughJavaScript(androidDriver,androidDriver.findElement(enterOtpForLogin),otp);
		}
		else
		{
			System.out.println("Login via OTP page not loaded successfully..");
			GlobalVar.test.log(LogStatus.FAIL, "Login via OTP page not loaded successfully..");
		}		
	}
	
	/**
	 * Submit OTP for Login.
	 */
	public void clickOtpSubmitDone()
	{
		comnFunc.click(androidDriver.findElement(loginViaOtpSubmitDone));
	}
	
	/**
	 * Google Login.
	 */
	public void clickGoogleLoginBtn() 
	{
		comnFunc.scrollToObject(androidDriver, androidDriver.findElement(loginWithLabel));
		comnFunc.click(androidDriver.findElement(googleLoginBtn));
	}
	
	/**
	 * Facebook Login.
	 */
	public void clickFacebookLoginBtn() 
	{
		comnFunc.scrollToObject(androidDriver, androidDriver.findElement(loginWithLabel));
		comnFunc.click(androidDriver.findElement(facebookLoginBtn));
	}
	
	/**
	 * Forgot password.
	 */
	public void clickForgotpassword() 
	{
		comnFunc.click(androidDriver.findElement(forgotPassword));
	}
	
	/**
	 * Enter mobile number for forgot password.
	 * @param mobileNumber
	 */
	public void enterForgotMobileNumber(String mobileNumber) 
	{
		if(comnFunc.isElementPresent(androidDriver, androidDriver.findElement(forgotYourPasswordText)))
		{
			comnFunc.sendKeysThroughJavaScript(androidDriver,androidDriver.findElement(forgotPasswordMobileField),mobileNumber);
		}
		else {
			System.out.println("Forgot Your Password page not loaded successfully..");
			GlobalVar.test.log(LogStatus.FAIL, "Forgot Your Password page not loaded successfully..");
		}		
	}
	
	/**
	 * Forgot Password continue.
	 */
	public void clickForgotPasswordContinue() 
	{
		comnFunc.click(androidDriver.findElement(forgotPasswordContinue));
	}
	
	/**
	 * Enter OTP for Forgot Your Password
	 */
	public void enterOTPforFYD(String otp) 
	{
		comnFunc.scrollToObject(androidDriver, androidDriver.findElement(enterOtpFYPFeild));
		comnFunc.sendKeysThroughJavaScript(androidDriver, androidDriver.findElement(enterOtpFYPFeild), otp);
	}
	
	/**
	 * OTP Submit done for Forgot Your Password.
	 */
	public void forgotYourPasswordOtpSubmitDone() 
	{
		comnFunc.click(androidDriver.findElement(forgotYourPasswordOtpSubmitDone));
	}

	/**
	 * Enter new password to reset password.
	 * @param password
	 */
	public void enterNewPassword(String password) 
	{
		comnFunc.sendKeysThroughJavaScript(androidDriver,androidDriver.findElement(resetNewPasswordField),password);
	}
	
	/**
	 * Enter confirm new password to reset password.
	 * @param password
	 */
	public void enterConfirmNewPassword(String password) 
	{
		comnFunc.sendKeysThroughJavaScript(androidDriver,androidDriver.findElement(resetConfirmNewPasswordField),password);
	}
	
	/**
	 * Reset password submit.
	 */
	public void clickResetPasswordSubmitDoneBtn()
	{
		comnFunc.click(androidDriver.findElement(resetPasswordSubmitDoneBtn));
	}

	public void waitForGuestPageToBeInvisible() 
	{
		comnFunc.waitForElementToBeInvisible(androidDriver, 30, accountsLoginText);
	}
	
}
