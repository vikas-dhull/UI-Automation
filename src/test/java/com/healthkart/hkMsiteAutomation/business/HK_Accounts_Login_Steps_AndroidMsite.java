package com.healthkart.hkMsiteAutomation.business;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.healthkart.hkMsiteAutomation.pages.HK_Accounts_Login_Page_AndroidMsite;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

public class HK_Accounts_Login_Steps_AndroidMsite {
	
	AndroidDriver<?> androidDriver;
	HK_Accounts_Login_Page_AndroidMsite AccLoginPage;
	CommonFunctions comnFunc;
	GenericDbActions dbActionsObj;
	
	public HK_Accounts_Login_Steps_AndroidMsite(AndroidDriver<?> androidDriver)
	{
		this.androidDriver = androidDriver;
		AccLoginPage = new HK_Accounts_Login_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
		dbActionsObj = new GenericDbActions();
	}
	
	public boolean verifyAccountsLoginPageLoaded() {
		return AccLoginPage.verifyAccountsLoginPageLoaded();
	}
	
	public void performLoginWithMobilePassword(String mobileNumber, String password) 
	{
		AccLoginPage.enterMobileNumber(mobileNumber);
		AccLoginPage.enterPassword(password);
		AccLoginPage.submitLogin();
	}
	
	public void performLoginWithMobileOtp(String mobileNumber) 
	{
		AccLoginPage.enterMobileNumber(mobileNumber);
		AccLoginPage.clickLoginViaOtp();
		comnFunc.staticWait(5);
		String otp = dbActionsObj.getOtp(mobileNumber,1);
		AccLoginPage.enterOtpForLogin(otp);
		AccLoginPage.clickOtpSubmitDone();		
	}

	public void performLoginWithGoogle(String gmailId, String gmailPswd) 
	{
		String hkWinHndl = androidDriver.getWindowHandle();
		AccLoginPage.clickGoogleLoginBtn();
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
			//AccLoginPage.waitForGuestPageToBeInvisible();
			comnFunc.staticWait(5);
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
		AccLoginPage.clickFacebookLoginBtn();
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
			// AccLoginPage.waitForGuestPageToBeInvisible();
			comnFunc.staticWait(5);
		}		
		else
		{
			System.out.println("window count not found as expected.." );
			GlobalVar.test.log(LogStatus.FAIL, "window count not found as expected..");
		}
		
	}

	public void performForgotPasswordWithOTP(String mobileNumber, String password) 
	{		
		AccLoginPage.clickForgotpassword();
		AccLoginPage.enterForgotMobileNumber(mobileNumber);
		AccLoginPage.clickForgotPasswordContinue();
		comnFunc.staticWait(10);
		
		String otp = dbActionsObj.getOtp(mobileNumber,1);
		AccLoginPage.enterOTPforFYD(otp);
		AccLoginPage.forgotYourPasswordOtpSubmitDone();
		comnFunc.staticWait(4);
				
		AccLoginPage.enterNewPassword(password);
		AccLoginPage.enterConfirmNewPassword(password);
		AccLoginPage.clickResetPasswordSubmitDoneBtn();
		comnFunc.staticWait(4);
		
		AccLoginPage.enterMobileNumber(mobileNumber);
		AccLoginPage.enterPassword(password);
		AccLoginPage.submitLogin();		
	}

}
