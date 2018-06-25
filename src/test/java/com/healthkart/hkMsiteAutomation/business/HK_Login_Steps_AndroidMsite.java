package com.healthkart.hkMsiteAutomation.business;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.healthkart.hkMsiteAutomation.pages.HK_Login_Page_AndroidMsite;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class HK_Login_Steps_AndroidMsite
{
	AndroidDriver<?> androidDriver;
	HK_Login_Page_AndroidMsite loginPage;
	CommonFunctions comnFunc;
	GenericDbActions dbActionsObj;
	
	
	public HK_Login_Steps_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		loginPage = new HK_Login_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
		dbActionsObj = new GenericDbActions();
	}
	
	public void performSignupWithMobileNumber(String signupPswd)
	{
		String mob = "12345"+WebDriverUtil.getRandomNumberInRange(11111, 99999);
    	
    	while(dbActionsObj.verifyIfSignUpMobileExists(mob,1) != null) {
    		mob = "12345"+WebDriverUtil.getRandomNumberInRange(11111, 99999);    		
    	}   	
    	
    	System.out.println(mob);
    	loginPage.enterSignupMob(mob);
    	loginPage.clickSubmitSignUp();
    	comnFunc.staticWait(4);
		String otp = dbActionsObj.getOtp(mob,1);
		loginPage.enterSignupOtp(otp);
		loginPage.enterSignupPswd(signupPswd);
		loginPage.clickSubmitSignupDone();
		loginPage.waitForLoginPopupToBeInvisible();
	}
	
	public boolean verifyLoginPopupPresent() {
		return loginPage.verifyLoginPopupLoaded();
	}
	
	public void performLoginWithMobilePassword(String mobileNumber, String password) 
	{
		loginPage.enterMobileNumber(mobileNumber);
		loginPage.enterPassword(password);
		androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		loginPage.submitLogin();
		loginPage.waitForLoginPopupToBeInvisible();
	}
	
	public void performLoginWithMobileOtp(String mobileNumber) 
	{
		loginPage.enterMobileNumber(mobileNumber);
		androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		loginPage.clickLoginViaOtp();
		comnFunc.staticWait(4);
		String otp = dbActionsObj.getOtp(mobileNumber,1);
		loginPage.enterOtpForLogin(otp);
		loginPage.clickOtpSubmitDone();
		loginPage.waitForLoginPopupToBeInvisible();
	}

	public void performLoginWithGoogle(String gmailId, String gmailPswd) 
	{
		String hkWinHndl = androidDriver.getWindowHandle();
		loginPage.clickGoogleLoginBtn();
		comnFunc.staticWait(2);
		if(WebDriverUtil.waitForWindowCount(androidDriver, 2)) {
			
			for (String allHandles : androidDriver.getWindowHandles()) {
				androidDriver.switchTo().window(allHandles);
				System.out.println("current window title :" + androidDriver.getTitle());
				if(androidDriver.getTitle().contains("Sign in")) {
					System.out.println("Switched to Google window..");
					GlobalVar.test.log(LogStatus.INFO, "Switched to Google window....");
					WebElement gmailUserInpt = androidDriver.findElement(By.xpath("//input[@id='identifierId']"));
					comnFunc.sendKeysThroughJavaScript(androidDriver, gmailUserInpt,gmailId);					
					WebElement gmailIdentifierNext = androidDriver.findElement(By.xpath("//div[@id='identifierNext']"));
					comnFunc.scrollToObject(androidDriver, gmailIdentifierNext);
					gmailIdentifierNext.click();							
					comnFunc.staticWait(3);					
					WebElement gmailPswdInpt = androidDriver.findElement(By.xpath("//input[@name='password']"));
					gmailPswdInpt.sendKeys(gmailPswd);
					//comnFunc.sendKeysThroughJavaScript(androidDriver, gmailPswd, ExcelUtil.getMsiteGooglePassword());					
					WebElement gmailPswdNext = androidDriver.findElement(By.xpath(".//*[@id='passwordNext']"));
					comnFunc.scrollToObject(androidDriver, gmailPswdNext);
					gmailPswdNext.click();
				}
				else if(androidDriver.getTitle().contains("Error")) {
					System.out.println("current window title :" + androidDriver.getTitle());
					androidDriver.close();
					break;
				}
			}
			comnFunc.staticWait(10);
			androidDriver.switchTo().window(hkWinHndl);
			GlobalVar.test.log(LogStatus.INFO, "Switched to HK window....");
			System.out.println("current window title :" + androidDriver.getTitle());
			loginPage.waitForLoginPopupToBeInvisible();
		}
		else
		{
			System.out.println("window count not found as expected.." );
			GlobalVar.test.log(LogStatus.FAIL, "window count not found as expected..");
		}		
	}
	
	public void performLoginWithFacebook(String fbMailId, String fbPswd) 
	{		
		String hkWinHndl = androidDriver.getWindowHandle();
		loginPage.clickFacebookLoginBtn();
		comnFunc.staticWait(2);
		
		if(WebDriverUtil.waitForWindowCount(androidDriver, 2)) {	
			
			for (String allHandles : androidDriver.getWindowHandles()) { // traverse all window handles.
				androidDriver.switchTo().window(allHandles);
				System.out.println("current window title :" + androidDriver.getTitle());
				if(androidDriver.getTitle().contains("Facebook")) {
					System.out.println("Switched to Facebook window..");
					GlobalVar.test.log(LogStatus.INFO, "Switched to Facebook window....");
					WebElement fbUserInpt = androidDriver.findElement(By.id("m_login_email"));
					fbUserInpt.sendKeys(fbMailId);
					//comnFunc.sendKeysThroughJavaScript(androidDriver, fbUser, ExcelUtil.getMsiteFacebookUserEmail());					
					WebElement fbPswdInpt = androidDriver.findElement(By.id("m_login_password"));
					fbPswdInpt.sendKeys(fbPswd);
					//comnFunc.sendKeysThroughJavaScript(androidDriver, fbPswd, ExcelUtil.getMsiteFacebookPassword());					
					WebElement fbLoginSub = androidDriver.findElement(By.id("u_0_5"));
					comnFunc.scrollToObject(androidDriver, fbPswdInpt);
					fbLoginSub.click();
				}
				else if(androidDriver.getTitle().contains("Error")) {
					System.out.println("current window title :" + androidDriver.getTitle());
					androidDriver.close();
					break;
				}
			}
			comnFunc.staticWait(10);
			androidDriver.switchTo().window(hkWinHndl);
			GlobalVar.test.log(LogStatus.INFO, "Switched to HK window....");
			System.out.println("current window title :" + androidDriver.getTitle());
			loginPage.waitForLoginPopupToBeInvisible();
		}
		else
		{
			System.out.println("window count not found as expected.." );
			GlobalVar.test.log(LogStatus.FAIL, "window count not found as expected..");
		}		
	}

	public void performForgotPasswordWithOTP(String mobileNumber, String password) 
	{		
		loginPage.clickForgotpassword();
		loginPage.enterForgotMobileNumber(mobileNumber);
		loginPage.clickForgotPasswordContinue();
		comnFunc.staticWait(4);
		
		String otp = dbActionsObj.getOtp(mobileNumber,1);
		loginPage.enterOtpForLogin(otp);
		loginPage.clickOtpSubmitDone();
		comnFunc.staticWait(2);
		
		loginPage.enterNewPassword(password);
		loginPage.enterConfirmNewPassword(password);
		loginPage.clickResetPasswordSubmitDoneBtn();
		comnFunc.staticWait(2);
		
		loginPage.enterMobileNumber(mobileNumber);
		loginPage.enterPassword(password);
		androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		loginPage.submitLogin();
		loginPage.waitForLoginPopupToBeInvisible();
	}

}
