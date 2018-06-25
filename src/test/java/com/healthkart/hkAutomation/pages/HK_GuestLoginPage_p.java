package com.healthkart.hkAutomation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_GuestLoginPage_p {
	
	public WebDriver driver;
	
	public HK_GuestLoginPage_p(WebDriver driver) {		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Handle notify visitor[For PROD ENV only.]
	 */	
	/*@FindBy(xpath = "//a[@id='nv_js-modal-close-button']")
	private WebElement closeNotifyVisitorOverlay;
	
	public void clickCloseNotifyVisitorOverlay() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if(WebDriverUtil.isElementClickable(closeNotifyVisitorOverlay, driver,1))
	       	closeNotifyVisitorOverlay.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebDriverUtil.click(driver, closeNotifyVisitorOverlay, "closeNotifyVisitorOverlay");
	    if(WebDriverUtil.isElementClickable(closeNotifyVisitorOverlay, driver,20))
	       	closeNotifyVisitorOverlay.click();
	}*/
	
	/**
	 * Is Guest Login page loaded successfully.
	 */
	@FindBy(xpath = "//h3[text()='Express Checkout']")
	private WebElement expressCheckoutText;
	
	public boolean verifyGuestLoginPageLoaded() {
		boolean  flag = false;
		
		/*if(GlobalVar.jenkinsEnvironment==null)        // Handle notify visitor overlay [For PROD ENV only.]
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);			
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);
		}*/
		
		WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);
		
		if(WebDriverUtil.isElementPresent(expressCheckoutText, driver,20)) {
			if(driver.getCurrentUrl().contains("GuestLogin"))
				flag = true;
		}
		System.out.println("verify Guest login page flag : " + flag);	
		return flag;
	}
	
	/**
	 * enter mobile number.
	 */
	@FindBy(xpath = ".//*[@id='guestSignInForm']/div/input")
	private WebElement mobileLoginInput;
	
	public void setMobileNumber(String mob) {
		if(WebDriverUtil.isElementPresent(mobileLoginInput, driver,20)) {
		mobileLoginInput.clear();
		mobileLoginInput.sendKeys(mob);
		}
	}
	
	
	/**
	 * Continue to login.
	 */
	@FindBy(xpath = ".//*[@id='guestSignInForm']/input[@value='Continue']")
	private WebElement loginContinue;
	
	public void clickLoginContinue() {
		WebDriverUtil.click(driver, loginContinue, "loginContinue");
		
    	/*if(WebDriverUtil.isElementPresent(loginContinue, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(loginContinue).build().perform();
            if(WebDriverUtil.isElementClickable(loginContinue, driver))
            	loginContinue.click();
    	}*/
    }
	
	/**
	 * Account already exists, verify Text.
	 */
	@FindBy(xpath = ".//h3[text()='Account already exists']")
	private WebElement accAlreadyExists;
	
	public boolean verifyIfExistingUser() {
    	return (WebDriverUtil.isElementPresent(accAlreadyExists, driver,20));           
    }	
	
	/**
	 * enter password.
	 */
	@FindBy(xpath = "//form[@id='signInForm']//input[@id='password']")
	private WebElement mobilePasswordInput;
	
	public void setPassword(String pswd) {
		if(WebDriverUtil.isElementPresent(mobilePasswordInput, driver,20)) {
    	mobilePasswordInput.clear();
    	mobilePasswordInput.sendKeys(pswd);
		}
	}
	
	/**
	 * do Login.   
	 */
	@FindBy(xpath = "//form[@id='signInForm']//input[@value='Log In']")
	private WebElement loginSubmit;
	
	public void submitLogin() {
		WebDriverUtil.click(driver, loginSubmit, "loginSubmit");
		
		/*if(WebDriverUtil.isElementPresent(loginSubmit, driver)) {
			Actions action = new Actions(driver);		 
            action.moveToElement(loginSubmit).build().perform();
            if(WebDriverUtil.isElementClickable(loginSubmit, driver))
            	loginSubmit.click();
		}*/
	}	
	
	/**
	 * Login via OTP.
	 */
	@FindBy(xpath = ".//a[@id='loginViaOTP']")
	private WebElement mobileLoginOtp;
	
	public void clickLoginViaOtp() {
		WebDriverUtil.click(driver, mobileLoginOtp, "mobileLoginOtp");
		
		/*if(WebDriverUtil.isElementPresent(mobileLoginOtp, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(mobileLoginOtp).build().perform();
	        if(WebDriverUtil.isElementClickable(mobileLoginOtp, driver))
	        	mobileLoginOtp.click();
		}*/
    }
	
	/**
	 * Verify OTP text on enter OTP screen.
	 */
	@FindBy(xpath = ".//h3[text()='Verify OTP']")
	private WebElement verifyOTP;
	
	public boolean verifyOTPText() {
		return (WebDriverUtil.isElementPresent(verifyOTP, driver,20));
	}
	
	/**
	 * enter OTP for login via OTP.
	 */
	@FindBy(xpath = ".//input[@id='otp']")
	private WebElement enterOtp;
	
	public void enterOtp(String otp) {
    	if(WebDriverUtil.isElementPresent(enterOtp, driver,20))
    		enterOtp.sendKeys(otp);
    }
	
	/**
	 * Login through OTP verify.
	 */
	@FindBy(xpath = "//form[@id='phoneNumberVerificationForm']//input[@value='Submit']")
	private WebElement loginVerifyOtpSubmit;
	
	public void submitVerifyOtpLogin() {
		WebDriverUtil.click(driver, loginVerifyOtpSubmit, "loginVerifyOtpSubmit");
		
		/*if(WebDriverUtil.isElementPresent(loginVerifyOtpSubmit, driver)) {
			Actions action = new Actions(driver);		 
            action.moveToElement(loginVerifyOtpSubmit).build().perform();
            if(WebDriverUtil.isElementClickable(loginVerifyOtpSubmit, driver))
            	loginVerifyOtpSubmit.click();
		}*/
	}
	
	/**
	 * Forgot password.  	
	 */
	@FindBy(linkText = "Forgot password?")
	private WebElement forgotPassword;
	
	public void clickForgotPassword() {
		WebDriverUtil.click(driver, forgotPassword, "forgotPassword");
		
		/*if(WebDriverUtil.isElementPresent(forgotPassword, driver)) {
			Actions action = new Actions(driver);		 
            action.moveToElement(forgotPassword).build().perform();
            if(WebDriverUtil.isElementClickable(forgotPassword, driver))
            	forgotPassword.click();
		}*/
	}
	
	/**
	 * Input forgot password mobile No.
	 */
	@FindBy(xpath = ".//input[@id='email']")
	private WebElement forgotPasswordMobInput;
	
	public void enterForgotPasswordMob(String mobile) {
		if(WebDriverUtil.isElementPresent(forgotPasswordMobInput, driver,20))
			forgotPasswordMobInput.sendKeys(mobile);		
	}	
	
	/**
	 * Forgot Password continue done.
	 */
	@FindBy(xpath = ".//input[@id='sendEmail1']")
	private WebElement passwordResetContinueDone;
	
	public void clickPasswordResetContinueDone() {
		WebDriverUtil.click(driver, passwordResetContinueDone, "passwordResetContinueDone");
		
		/*if(WebDriverUtil.isElementPresent(passwordResetContinueDone, driver)) {
			Actions action = new Actions(driver);		 
            action.moveToElement(passwordResetContinueDone).build().perform();
            if(WebDriverUtil.isElementClickable(passwordResetContinueDone, driver))
            	passwordResetContinueDone.click();
		}*/		
	}
	
	/**
	 * input OTP for forgot password.
	 */
    @FindBy(xpath = ".//input[@id='OTP']")
	private WebElement enterMobileOtp;	
    
    public void setMobileOtp(String otp) {
    	if(WebDriverUtil.isElementPresent(enterMobileOtp, driver,20))
    		enterMobileOtp.sendKeys(otp);
    }
    
    /**
     * Resend OTP.
     */
    @FindBy(xpath = ".//a[@id='resendOtpAccounts']")
    private WebElement resendOtp;
    
    public void clickResendOtp() {
		WebDriverUtil.click(driver, resendOtp, "resendOtp");
    	
    	/*if(WebDriverUtil.isElementPresent(resendOtp, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(resendOtp).build().perform();
            if(WebDriverUtil.isElementClickable(resendOtp, driver))
            	resendOtp.click();
    	}*/
    }
	
	/**
	 * Input New password.
	 */
	@FindBy(xpath = ".//input[@id='newPasswordID']")
	private WebElement newPasswordInput;
	
	public void enterNewPassword(String pswd) {
		if(WebDriverUtil.isElementPresent(newPasswordInput, driver,20))
			newPasswordInput.sendKeys(pswd);		
	}
	
	/**
	 * Confirm New password.
	 */
	@FindBy(xpath = ".//input[@id='confirmPasswordID']")
	private WebElement confirmNewPasswordInput;
	
	public void confirmNewPassword(String pswd) {
		if(WebDriverUtil.isElementPresent(confirmNewPasswordInput, driver,20))
			confirmNewPasswordInput.sendKeys(pswd);		
	}
	
	/**
	 * Reset Password confirmation done.
	 */
	@FindBy(xpath = ".//input[@id='sendEmail2']")
	private WebElement passwordResetSubmitDone;
	
	public void clickPasswordResetSubmitDone() {
		WebDriverUtil.click(driver, passwordResetSubmitDone, "passwordResetSubmitDone");
		
		/*if(WebDriverUtil.isElementPresent(passwordResetSubmitDone, driver)) {
			Actions action = new Actions(driver);		 
            action.moveToElement(passwordResetSubmitDone).build().perform();
            if(WebDriverUtil.isElementClickable(passwordResetSubmitDone, driver))
            	passwordResetSubmitDone.click();
		}*/		
	}	
	
	/**
	 * Google login.
	 */
	@FindBy(xpath = ".//*[@id='customBtnGuestCheckout']//span[text()='Log in with Google']")
	private WebElement googleLoginBtn;
	
	public void clickGoogleLoginBtn() {
		WebDriverUtil.click(driver, googleLoginBtn, "googleLoginBtn");
		
		/*if(WebDriverUtil.isElementPresent(googleLoginBtn, driver)) {
			Actions action = new Actions(driver);		 
            action.moveToElement(googleLoginBtn).build().perform();
            if(WebDriverUtil.isElementClickable(googleLoginBtn, driver))
            	googleLoginBtn.click();
		}*/
	}
	
	/**
	 * Facebook login.
	 */
	@FindBy(xpath = ".//*[@id='customFbGuestCheckout']//span[text()='Log in with Facebook']")
	private WebElement facebookLoginBtn;
	
	public void clickFacebookLoginBtn() {
		WebDriverUtil.click(driver, facebookLoginBtn, "facebookLoginBtn");
		
		/*if(WebDriverUtil.isElementPresent(facebookLoginBtn, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(facebookLoginBtn).build().perform();
	        if(WebDriverUtil.isElementClickable(facebookLoginBtn, driver))
	        	facebookLoginBtn.click();
		}*/		
	}
	

}
