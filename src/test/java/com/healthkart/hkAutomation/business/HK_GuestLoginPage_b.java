package com.healthkart.hkAutomation.business;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.pages.HK_AccountsLoginPage_p;
import com.healthkart.hkAutomation.pages.HK_GuestLoginPage_p;
//import com.healthkart.hkAutomation.util.ExcelUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_GuestLoginPage_b extends HK_GuestLoginPage_p {
	
	public GenericDbActions dbActionsObj = new GenericDbActions();

	public HK_GuestLoginPage_b(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Login with mobile number and password.
	 * @param mob
	 * @param pswd
	 */
	public void performLoginWithMobilePassword(String mob,String pswd) {
		setMobileNumber(mob);
		clickLoginContinue();
		if (verifyIfExistingUser()) {
			System.out.println("user with mobile : " + mob + " already exists in system..");
			setPassword(pswd);
			submitLogin();
		}
		else {
			System.out.println("user with mobile : " + mob + " does not exists in system..");
		}
						
    }
	
	/**
	 * Login with mobile number and OTP.
	 * @param mob
	 * @throws InterruptedException 
	 */
	public void performLoginWithMobileOtp(String mob){
		setMobileNumber(mob);
		clickLoginContinue();
		if (verifyIfExistingUser()) {
			System.out.println("user with mobile : " + mob + " already exists in system..");
			clickLoginViaOtp();
			if(verifyOTPText()) {
				enterOtp(dbActionsObj.getOtp(mob,1));
				submitVerifyOtpLogin();
			}
			else {
				System.out.println("Verify OTP text not displayed..");
			}
		}
		else {
			System.out.println("user with mobile : " + mob + " does not exists in system..");
		}
    }
	
	/**
	 * Login with Google account.
	 * @throws InterruptedException
	 */
	public void performLoginWithGoogle(String gmailId, String gmailPswd) {
		String hkWinHndl = driver.getWindowHandle();
		
		WebDriverUtil.staticWait(4);
		clickGoogleLoginBtn();		
		
		if(WebDriverUtil.waitForWindowCount(driver, 2)) {
		
			for (String allHandles : driver.getWindowHandles()) {
				driver.switchTo().window(allHandles);
				System.out.println("current window title :" + driver.getTitle());
				if(driver.getTitle().contains("Sign in")) {
					System.out.println("Switched to Google window..");
					ExtentReportingBaseUtil.test.log(LogStatus.INFO, "Switched to Google window..");
					WebElement gmailUserInpt = driver.findElement(By.xpath("//input[@id='identifierId']"));
					gmailUserInpt.sendKeys(gmailId);					
					WebElement gmailIdentifierNext = driver.findElement(By.xpath("//div[@id='identifierNext']"));
					gmailIdentifierNext.click();							
					WebDriverUtil.staticWait(4);					
					WebElement gmailPswdInpt = driver.findElement(By.xpath("//input[@name='password']"));
					gmailPswdInpt.sendKeys(gmailPswd);					
					WebElement gmailPswdNext = driver.findElement(By.xpath(".//*[@id='passwordNext']"));
					gmailPswdNext.click();
				}
				else if(driver.getTitle().contains("Error")) {
					System.out.println("current window title :" + driver.getTitle());
					driver.close();
					break;
				}
			}
		
		WebDriverUtil.staticWait(10);
		driver.switchTo().window(hkWinHndl);
		System.out.println("current window title :" + driver.getTitle());
		}
	}
	
	/**
	 * Login with Facebook account.
	 */	
    public void performLoginWithFacebook(String fbMailId, String fbPswd) {
    	String hkWinHndl = driver.getWindowHandle(); // current window handle.
    	
    	WebDriverUtil.staticWait(4);
		clickFacebookLoginBtn();
		
		if(WebDriverUtil.waitForWindowCount(driver, 2)) {	
		
			for (String allHandles : driver.getWindowHandles()) { // traverse all window handles.
				driver.switchTo().window(allHandles);
				System.out.println("current window title :" + driver.getTitle());
				if(driver.getTitle().contains("Facebook")) {
					System.out.println("Switched to Facebook window..");					
					WebElement fbUserInpt = driver.findElement(By.id("email"));
					fbUserInpt.sendKeys(fbMailId);					
					WebElement fbPswdInpt = driver.findElement(By.id("pass"));
					fbPswdInpt.sendKeys(fbPswd);					
					WebElement fbLoginSub = driver.findElement(By.id("u_0_0"));					
					fbLoginSub.click();
				}
				else if(driver.getTitle().contains("Error")) {
					System.out.println("current window title :" + driver.getTitle());
					driver.close();
					break;
				}
			}
		
		WebDriverUtil.staticWait(10);
		driver.switchTo().window(hkWinHndl);
		System.out.println("current window title :" + driver.getTitle());
		}
	}
    
    /**
     * Forgot password through guest login page.
     */
    public void performForgotPasswordOnGuestPage(String mob, String pswd) {
    	String hkWinHndl = driver.getWindowHandle(); // current guest login page window handle.
    	clickForgotPassword();
    	
    	if(WebDriverUtil.waitForWindowCount(driver, 2)) {	
    		
			for (String allHandles : driver.getWindowHandles()) { // traverse all window handles.
				driver.switchTo().window(allHandles);
				System.out.println("current window title :" + driver.getTitle());
				if(driver.getCurrentUrl().contains("ForgotPassword.action?")) {
					HK_AccountsLoginPage_p accLgn = new HK_AccountsLoginPage_p(driver);
					
					if(accLgn.verifyForgotPassText()) {
						accLgn.enterForgotPasswordMob(mob);
						accLgn.clickPasswordResetContinueDone();
						accLgn.waitForOtpSentText();
						
						WebDriverUtil.staticWait(3);
						
						accLgn.setMobileOtp(dbActionsObj.getOtp(mob,1));
						accLgn.clickPasswordResetOtpContinueDone();
						accLgn.enterNewPassword(pswd);
						accLgn.confirmNewPassword(pswd);
						accLgn.clickPasswordResetSubmitDone();
						
						WebDriverUtil.staticWait(3);
						
						accLgn.setMobileNumber(mob);
						accLgn.setPassword(pswd);
						accLgn.submitLogin();			
					}
					else {
						System.out.println("Hold On..You are on a different page. Its not a forgot password page.");
					}					
				}
				else if(driver.getTitle().contains("Error")) {
					System.out.println("current window title :" + driver.getTitle());
					driver.close();
				}
			}			
    	}
    	driver.switchTo().window(hkWinHndl);
		System.out.println("current window title :" + driver.getTitle());
    	
    }
}
