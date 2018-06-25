package com.healthkart.hkMsiteAutomation.business;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.healthkart.hkMsiteAutomation.pages.HK_Guest_Login_Page_AndroidMsite;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

public class HK_Guest_Login_Steps_AndroidMsite 
{	
	AndroidDriver<?> androidDriver;
	HK_Guest_Login_Page_AndroidMsite guestPage;
	CommonFunctions comnFunc;
	GenericDbActions dbActionsObj;
	
	public HK_Guest_Login_Steps_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		guestPage = new HK_Guest_Login_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
		dbActionsObj = new GenericDbActions();
	}
	
	public boolean verifyGuestLoginPageLoaded() {
		return guestPage.verifyGuestLoginPageLoaded();
	}
	
	public void performLoginWithMobilePassword(String mobileNumber, String password) 
	{
		guestPage.enterMobileNumber(mobileNumber);
		guestPage.continueLogin();
		if(guestPage.verifyIfExistingUser())
		{
			System.out.println("user with mobile : " + mobileNumber + " already exists in system..");
			GlobalVar.test.log(LogStatus.INFO, "user with mobile : " + mobileNumber + " already exists in system..");
			guestPage.enterPassword(password);
			guestPage.submitLogin();
		}
		else
		{
			System.out.println("user with mobile : " + mobileNumber + " does not exists in system..");
			GlobalVar.test.log(LogStatus.FAIL, "user with mobile : " + mobileNumber + " does not exists in system..");
		}		
	}
	
	public void performLoginWithMobileOtp(String mobileNumber) 
	{
		guestPage.enterMobileNumber(mobileNumber);
		guestPage.continueLogin();
		
		if(guestPage.verifyIfExistingUser())
		{
			System.out.println("user with mobile : " + mobileNumber + " already exists in system..");
			GlobalVar.test.log(LogStatus.INFO, "user with mobile : " + mobileNumber + " already exists in system..");
			guestPage.clickLoginViaOtp();
			guestPage.waitForVerifyOtpText();
			comnFunc.staticWait(5);
			String otp = dbActionsObj.getOtp(mobileNumber,1);
			guestPage.enterOtpForLogin(otp);
			guestPage.clickOtpSubmitDone();
		}
		else
		{
			System.out.println("user with mobile : " + mobileNumber + " does not exists in system..");
			GlobalVar.test.log(LogStatus.FAIL, "user with mobile : " + mobileNumber + " does not exists in system..");
		}
				
	}

	public void performLoginWithGoogle(String gmailId, String gmailPswd) 
	{
		String hkWinHndl = androidDriver.getWindowHandle();
		guestPage.clickGoogleLoginBtn();
		comnFunc.staticWait(5);
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
					comnFunc.staticWait(4);					
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
				
				comnFunc.staticWait(10);
			}
		
			androidDriver.switchTo().window(hkWinHndl);
			GlobalVar.test.log(LogStatus.INFO, "Switched to HK window....");
			System.out.println("current window title :" + androidDriver.getTitle());
			guestPage.waitForGuestPageToBeInvisible();
			comnFunc.staticWait(3);
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
		guestPage.clickFacebookLoginBtn();
		comnFunc.staticWait(5);
		
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
				
				comnFunc.staticWait(10);
			}
			
			androidDriver.switchTo().window(hkWinHndl);
			GlobalVar.test.log(LogStatus.INFO, "Switched to HK window....");
			System.out.println("current window title :" + androidDriver.getTitle());
			guestPage.waitForGuestPageToBeInvisible();
			comnFunc.staticWait(3);
		}		
		else
		{
			System.out.println("window count not found as expected.." );
			GlobalVar.test.log(LogStatus.FAIL, "window count not found as expected..");
		}
		
	}

}
