package com.healthkart.hkAutomation.business;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.pages.HK_AccountsLoginPage_p;
//import com.healthkart.hkAutomation.util.ExcelUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_AccountsLoginPage_b extends HK_AccountsLoginPage_p {
	
	public GenericDbActions dbActionsObj = new GenericDbActions();

	public HK_AccountsLoginPage_b(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Login with mobile number and password.
	 * @param mob
	 * @param pswd
	 */
	public void performLoginWithMobilePassword(String mob,String pswd) {
		setMobileNumber(mob);
		setPassword(pswd);
		submitLogin();
    }
	
	/**
	 * Login with mobile number and OTP.
	 * @param mob
	 * @throws InterruptedException 
	 */
	public void performLoginWithMobileOtp(String mob) {
		setMobileNumber(mob);
		clickLoginViaOtp();
			if(verifyOTPText()) {
				enterOtp(dbActionsObj.getOtp(mob,1));
				submitVerifyOtpLogin();
			}
    }
	
	/**
	 * Google Login through Accounts login page.
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
	 * Facebook Login through Accounts Login page.
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
     * Forgot password through Accounts login page.
     */
    public void performForgotPassword(String mob, String pswd) {
		clickForgotPassword();
		if(verifyForgotPassText()) {
			enterForgotPasswordMob(mob);
			clickPasswordResetContinueDone();
			waitForOtpSentText();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			setMobileOtp(dbActionsObj.getOtp(mob,1));
			clickPasswordResetOtpContinueDone();
			enterNewPassword(pswd);
			confirmNewPassword(pswd);
			clickPasswordResetSubmitDone();
			
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
			setMobileNumber(mob);
			setPassword(pswd);
			submitLogin();			
		}
	}    
}
