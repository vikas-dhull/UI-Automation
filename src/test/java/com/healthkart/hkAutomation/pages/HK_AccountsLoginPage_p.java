package com.healthkart.hkAutomation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_AccountsLoginPage_p {
	
public WebDriver driver;
	
	public HK_AccountsLoginPage_p(WebDriver driver) {		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Handle notify visitor[For PROD ENV only.]
	 *//*	
	@FindBy(xpath = "//a[@id='nv_js-modal-close-button']")
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
	 * Is Accounts Login page loaded successfully.
	 */
	@FindBy(xpath = "//h3[text()='Log In']")
	private WebElement logInText;
	
	public boolean verifyAccountsLoginPageLoaded() {
		boolean flag=false;
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
		
		if(WebDriverUtil.isElementPresent(logInText, driver,20)) {
			if(driver.getCurrentUrl().contains("accounts.healthkart.com"))
				flag = true;
		}
		System.out.println("verify Accounts login page flag : " + flag);	
		return flag;
	}
	
	/**
	 * enter mobile number.
	 */
	@FindBy(xpath = "//input[@name='email']")
	private WebElement mobileLoginInput;
	
	public void setMobileNumber(String mob) {
		if(WebDriverUtil.isElementPresent(mobileLoginInput, driver,20)) {
		mobileLoginInput.clear();
		mobileLoginInput.sendKeys(mob);
		}
	}
	
	/**
	 * enter password.
	 */
	@FindBy(xpath = "//input[@name='password']")
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
	@FindBy(xpath = "//input[@name='login']")
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
	@FindBy(xpath = ".//h3[text()='Login Via OTP']")
	private WebElement verifyOTP;
	
	public boolean verifyOTPText() {
		
		return (WebDriverUtil.isElementPresent(verifyOTP, driver,20));
		/*boolean flag = false;
		if(WebDriverUtil.isElementPresent(verifyOTP, driver))
			flag = true;
		return flag;*/
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
	 * Verify Forgot Your Password text.
	 */
	@FindBy(xpath = "//div[@id='forgotPassword1']//h3[text()='Forgot your password']")
	private WebElement forgotPassText;
	
	public boolean verifyForgotPassText() {
		
		return (WebDriverUtil.isElementPresent(forgotPassText, driver,20));
		
		/*boolean flag = false;
		if(WebDriverUtil.isElementPresent(forgotPassText, driver))
			flag = true;
		return flag;*/
	}
	
	/**
	 * Input forgot password mobile No.
	 */
	@FindBy(xpath = ".//input[@id='email']")
	private WebElement forgotPasswordMobInput;
	
	public void enterForgotPasswordMob(String mobile) {
		if(WebDriverUtil.isElementPresent(forgotPasswordMobInput, driver,20))
			forgotPasswordMobInput.clear();
			forgotPasswordMobInput.sendKeys(mobile);		
	}	
	
	/**
	 * Forgot Password continue done.
	 */
	@FindBy(xpath = ".//input[@id='sendEmail']")
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
	 * OTP sent meassage
	 */
	@FindBy(xpath = "//div[@class='personalized-label' and text()='Please enter the OTP sent on your number']")
	private WebElement OtpSentText;
	
	public void waitForOtpSentText() {
		WebDriverUtil.waitForElementToBeDisplayed(driver, 45, OtpSentText);
    }
	
	/**
	 * input OTP for forgot password.
	 */
    @FindBy(xpath = ".//input[@id='OTP']")
	private WebElement enterMobileOtp;	
    
    public void setMobileOtp(String otp) {
    	if(WebDriverUtil.isElementPresent(enterMobileOtp, driver,20))
    		enterMobileOtp.clear();
    		enterMobileOtp.sendKeys(otp);
    }
    
    /**
	 * Forgot Password continue done.
	 */
	@FindBy(xpath = ".//input[@id='sendEmail1']")
	private WebElement passwordResetOtpContinueDone;
	
	public void clickPasswordResetOtpContinueDone() {
		WebDriverUtil.click(driver, passwordResetOtpContinueDone, "passwordResetOtpContinueDone");
		
		/*if(WebDriverUtil.isElementPresent(passwordResetOtpContinueDone, driver)) {
			Actions action = new Actions(driver);		 
            action.moveToElement(passwordResetOtpContinueDone).build().perform();
            if(WebDriverUtil.isElementClickable(passwordResetOtpContinueDone, driver))
            	passwordResetOtpContinueDone.click();
		}*/		
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
			newPasswordInput.clear();
			newPasswordInput.sendKeys(pswd);		
	}
	
	/**
	 * Confirm New password.
	 */
	@FindBy(xpath = ".//input[@id='confirmPasswordID']")
	private WebElement confirmNewPasswordInput;
	
	public void confirmNewPassword(String pswd) {
		if(WebDriverUtil.isElementPresent(confirmNewPasswordInput, driver,20))
			confirmNewPasswordInput.clear();
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
            WebDriverUtil.staticWait(4);
		}*/		
	}
	
	/**
	 * Google login.
	 */
	@FindBy(xpath = "//span[text()='Log in with Google']")
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
	@FindBy(xpath = "//span[text()='Log in with Facebook']")
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
