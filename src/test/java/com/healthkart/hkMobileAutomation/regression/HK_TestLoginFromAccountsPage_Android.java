package com.healthkart.hkMobileAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class HK_TestLoginFromAccountsPage_Android extends ExtentReportingBaseUtil
{	
	@Test(priority=1,enabled=false)
	public void testLoginWithMobilePasswordFromAccountsPage()
	{	
		comnFunc.reportLogAndPrintInConsole("## LOGIN FROM ACCOUNTS PAGE TEST STARTS WITH MOBILE AND PASSWORD..");
//		homeSteps.swipeNavigationPage();
		homeSteps.openMyAccountLoginPage();
		loginSteps.performLogin(mobileNumber, password);
		Assert.assertTrue(accountSteps.verifyLoggedInFromAccountPage());
	}
	
	@Test(priority=2,enabled=false)
	public void testLoginWithMobileOtpFromAccountsPage()
	{
		comnFunc.reportLogAndPrintInConsole("## LOGIN FROM ACCOUNTS PAGE TEST STARTS WITH MOBILE AND OTP..");
		homeSteps.openMyAccountLoginPage();
		loginSteps.performLoginWithMobileOtp(mobileNumber);
		Assert.assertTrue(accountSteps.verifyLoggedInFromAccountPage());
	}
	
	@Test(priority=3,enabled=false)
	public void testLoginWithGoogleFromAccountsPage()
	{
		comnFunc.reportLogAndPrintInConsole("## LOGIN FROM ACCOUNTS PAGE TEST STARTS WITH GOOGLE ACCOUNTS..");
		homeSteps.openMyAccountLoginPage();
		loginSteps.performLoginWithGoogle();
		Assert.assertTrue(accountSteps.verifyLoggedInFromAccountPage());
		
	}

	@Test(priority=4,enabled=false)
	public void testLoginWithFacebookFromAccountsPage() 
	{
		comnFunc.reportLogAndPrintInConsole("## LOGIN FROM ACCOUNTS PAGE TEST STARTS WITH FACEBOOK ACCOUNTS..");
		homeSteps.openMyAccountLoginPage();
		loginSteps.performLoginWithFacebook(fbUserName, fbPassword);
		Assert.assertTrue(accountSteps.verifyLoggedInFromAccountPage());
	}
	
	@Test(priority=5, enabled=true)
	public void testTrueCallerLogin()
	{
		comnFunc.reportLogAndPrintInConsole("## LOGIN FROM ACCOUNTS PAGE TEST STARTS WITH TRUECALLER ACCOUNTS..");
		homeSteps.openMyAccountLoginPage();
		loginSteps.performLoginWithTrueCaller();
		Assert.assertTrue(accountSteps.verifyLoggedInFromAccountPage());
	}
}
