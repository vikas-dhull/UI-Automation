package com.healthkart.hkMsiteAutomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidDriver;

public class HK_Login_Page_AndroidMsite extends CommonFunctions
{
	AndroidDriver<?> androidDriver;
	JavascriptExecutor executor;
	
	private By userLoginPopup = By.xpath("//div[@id='UserLogin']");
	
	// private By userLoginPopup = By.xpath("//div[@class='login-box-container']");

	private By mobileNumberField = By.xpath("//input[@id='email']");
	private By passwordField = By.xpath("//input[@id='password' and @tabindex='0']");
	private By loginSubmitButton = By.xpath("//input[@value='Log In' and @tabindex='0']");
	
	private By loginViaOtp = By.xpath("//a[@id='loginViaOTP']");
	private By enterOtpForLogin = By.xpath("//input[@name='otpLoginPopup']");	
	
	private By continueWithLabel = By.xpath("//div[@id='tab-1-content']//p[contains(text(),'Continue with')]");
	private By googleLoginBtn = By.xpath("//div[@id='tab-1-content']//span[text()='Google']");
	private By facebookLoginBtn = By.xpath("//div[@id='tab-1-content']//span[text()='Facebook']");
	
	private By forgotPassword = By.xpath("//span[@class='forgotPass']");
	private By forgotPasswordContinue = By.xpath("//div[@class='forgotPassword']//input[@value='Continue']");	
	private By forgotPasswordMobileField = By.xpath("//input[@id='userName']");	
	private By OtpSubmitDone = By.xpath("//input[contains(@class,'enterotpotp_done')]");
	private By resetNewPasswordField = By.xpath("//input[@id='newPassword']");
	private By resetConfirmNewPasswordField = By.xpath("//input[@id='confirmNewPassword']");
	private By resetPasswordSubmitDoneBtn = By.xpath("//form[@class='forgotEmail']//input[@class='submit-btn']");

	private By signupMobileNumberFeild = By.xpath("//input[@id='contactNumber']");
	private By continueSignUp = By.xpath("//form[@class='signUpForm']//input[@type='submit']");		
	private By signupOtpFeild = By.xpath("//input[@id='otp']");
	private By signupPasswordFeild = By.xpath("//form[@class='OTPForm']//input[@id='password']");
	private By submitSignUp = By.xpath("//form[@class='OTPForm']//input[@type='submit']");

	private By closeNotifyVisitorOverlay = By.xpath("//a[@id='nv_js-modal-close-button']");
	
	public HK_Login_Page_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		executor = (JavascriptExecutor)androidDriver;
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
	 * Verify Login PopUp loaded successfully.
	 * @return
	 */
	public boolean verifyLoginPopupLoaded() 
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
		
		return isElementPresentBy(androidDriver,userLoginPopup);
	}
	
	public void waitForLoginPopupToBeInvisible()
	{
		waitForElementToBeInvisible(androidDriver, 30, userLoginPopup);
	}
	/**
	 * Enter Mobile Number.
	 * @param mobileNumber
	 */
	public void enterMobileNumber(String mobileNumber)
	{	    
	    sendKeys(androidDriver.findElement(mobileNumberField),mobileNumber);
	}

	/**
	 * Enter Password.
	 * @param password
	 */
	public void enterPassword(String password) 
	{
		sendKeys(androidDriver.findElement(passwordField),password);
	}
	
	/**
	 * Submit Login.
	 */
	public void submitLogin() 
	{
		click(androidDriver.findElement(loginSubmitButton));
	}
	
	/**
	 * Login via OTP.
	 */
	public void clickLoginViaOtp() {
		click(androidDriver.findElement(loginViaOtp));
	}
	
	/**
	 * Enter OTP for Login.
	 * @param otp
	 */
	public void enterOtpForLogin(String otp) 
	{
		sendKeysThroughJavaScript(androidDriver,androidDriver.findElement(enterOtpForLogin),otp);
	}
	
	/**
	 * Submit OTP for Login.
	 */
	public void clickOtpSubmitDone()
	{
		click(androidDriver.findElement(OtpSubmitDone));
	}
	
	/**
	 * Google Login.
	 */
	public void clickGoogleLoginBtn() 
	{
		scrollToObject(androidDriver, androidDriver.findElement(continueWithLabel));
		click(androidDriver.findElement(googleLoginBtn));
	}
	
	/**
	 * Facebook Login.
	 */
	public void clickFacebookLoginBtn() 
	{
		scrollToObject(androidDriver, androidDriver.findElement(continueWithLabel));
		click(androidDriver.findElement(facebookLoginBtn));
	}
	
	/**
	 * Forgot password.
	 */
	public void clickForgotpassword() 
	{
		click(androidDriver.findElement(forgotPassword));
	}
	
	/**
	 * Enter mobile number for forgot password.
	 * @param mobileNumber
	 */
	public void enterForgotMobileNumber(String mobileNumber) 
	{
		sendKeysThroughJavaScript(androidDriver,androidDriver.findElement(forgotPasswordMobileField),mobileNumber);
	}
	
	/**
	 * Forgot Password continue.
	 */
	public void clickForgotPasswordContinue() 
	{
		click(androidDriver.findElement(forgotPasswordContinue));
	}
	
	/**
	 * Enter new password to reset password.
	 * @param password
	 */
	public void enterNewPassword(String password) 
	{
		sendKeysThroughJavaScript(androidDriver,androidDriver.findElement(resetNewPasswordField),password);
	}
	
	/**
	 * Enter confirm new password to reset password.
	 * @param password
	 */
	public void enterConfirmNewPassword(String password) 
	{
		sendKeysThroughJavaScript(androidDriver,androidDriver.findElement(resetConfirmNewPasswordField),password);
	}
	
	/**
	 * Reset password submit.
	 */
	public void clickResetPasswordSubmitDoneBtn()
	{
		click(androidDriver.findElement(resetPasswordSubmitDoneBtn));
	}

	/**
	 * Signup with mobile number.
	 */
	public void enterSignupMob(String mob) 
	{
		sendKeysThroughJavaScript(androidDriver, androidDriver.findElement(signupMobileNumberFeild), mob);
	}

	public void clickSubmitSignUp() 
	{
		clickBy(continueSignUp);
	}

	public void enterSignupOtp(String otp) 
	{
		sendKeysThroughJavaScript(androidDriver, androidDriver.findElement(signupOtpFeild), otp);
	}

	public void enterSignupPswd(String signupPassword) 
	{
		sendKeysThroughJavaScript(androidDriver, androidDriver.findElement(signupPasswordFeild), signupPassword);
	}

	public void clickSubmitSignupDone()
	{
		clickBy(submitSignUp);
	}

}
