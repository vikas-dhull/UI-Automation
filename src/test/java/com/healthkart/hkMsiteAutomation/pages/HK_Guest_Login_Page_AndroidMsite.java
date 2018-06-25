package com.healthkart.hkMsiteAutomation.pages;

import org.openqa.selenium.By;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;

public class HK_Guest_Login_Page_AndroidMsite 
{
	AndroidDriver<?> androidDriver;	
	CommonFunctions comnFunc;
	
	private By guestLoginText = By.xpath("//h3[text()='Express Checkout']");
	
	private By mobileNumberField = By.xpath("//form[@id='guestSignInForm']//input[@name='email']");
	private By loginContinue = By.xpath("//form[@id='guestSignInForm']//input[@name='guestLogin']");
	private By acctAlreadyExistsText = By.xpath("//h3[text()='Account already exists']");
	private By passwordField = By.xpath("//form[@id='signInForm']//input[@id='password']");
	private By loginSubmitButton = By.xpath("//input[@name='loginGuest']");
	
	private By loginViaOtp = By.xpath("//a[@id='loginViaOTP']");
	private By verifyOTPText = By.xpath("//h3[text()='Verify OTP']");
	private By otpField = By.xpath("//input[@id='otp']");
	private By loginViaOtpSubmitDone = By.xpath("//form[@id='phoneNumberVerificationForm']//input[contains(@class,'enterotpotp_done')]");	
	
	private By loginWithLabel = By.xpath("//div[text()='Or Log In with']");
	private By googleLoginBtn = By.xpath("//div[@id='customBtnGuestCheckout']//span[text()='Google']");
	private By facebookLoginBtn = By.xpath("//div[@id='customFbGuestCheckout']//span[text()='Facebook']");	
	
	private By closeNotifyVisitorOverlay = By.xpath("//a[@id='nv_js-modal-close-button']");
	
	public HK_Guest_Login_Page_AndroidMsite(AndroidDriver<?> androidDriver) 
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
	 * Verify Guest' Login Page loaded successfully.
	 * @return
	 */
	public boolean verifyGuestLoginPageLoaded() 
	{
		/*if(GlobalVar.jenkinsEnvironment==null)        // Handle notify visitor overlay [For PROD ENV only.]
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				clickCloseNotifyVisitorOverlay();			
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			clickCloseNotifyVisitorOverlay();
		}*/
		
		return comnFunc.isElementPresentBy(androidDriver, guestLoginText);
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
	 * Continue Login.
	 */
	public void continueLogin()
	{
		comnFunc.click(androidDriver.findElement(loginContinue));
	}
	
	/**
	 * verify account already exists.
	 */
	public boolean verifyIfExistingUser()
	{
		boolean flag = false;
    	if(comnFunc.isElementPresentBy(androidDriver, acctAlreadyExistsText))
    		flag=true;
    	return flag; 
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
	
	public void waitForVerifyOtpText()
	{
		comnFunc.waitForElementToBePresent(androidDriver, 45, verifyOTPText );
	}
	
	/**
	 * Enter OTP for Login.
	 * @param otp
	 */
	public void enterOtpForLogin(String otp) 
	{
		if(comnFunc.isElementPresentBy(androidDriver, verifyOTPText))
		{
			comnFunc.sendKeysThroughJavaScript(androidDriver,androidDriver.findElement(otpField),otp);
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

	public void waitForGuestPageToBeInvisible() 
	{
		comnFunc.waitForElementToBeInvisible(androidDriver, 30, guestLoginText);
	}
	
	
	
}
