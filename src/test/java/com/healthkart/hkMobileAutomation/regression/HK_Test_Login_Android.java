package com.healthkart.hkMobileAutomation.regression;

import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Test_Login_Android extends ExtentReportingBaseUtil
{
	@Test(priority=1,enabled=true)
	public void testLoginWithMobilePassword()
	{	
		comnFunc.reportLogAndPrintInConsole("## LOGIN FROM POPUP TEST STARTS WITH MOBILE AND PASSWORD..");
//		homeSteps.swipeNavigationPage();
		homeSteps.proceedToLogin();
		loginSteps.performLogin(mobileNumber,password);
		homeSteps.verifySuccessfullLogin();
	}

	@Test(priority=2,enabled=true)
	public void testLoginWithMobileOTP()
	{	
		comnFunc.reportLogAndPrintInConsole("## LOGIN FROM POPUP TEST STARTS WITH MOBILE AND OTP..");
		homeSteps.proceedToLogin();
		loginSteps.performLoginWithMobileOtp(mobileNumber);
		homeSteps.verifySuccessfullLogin();
	}
	
	@Test(priority=3,enabled=true)
	public void testLoginWithGoogle()
	{
		comnFunc.reportLogAndPrintInConsole("## LOGIN FROM POPUP TEST STARTS WITH GOOGLE ACCOUNTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLoginWithGoogle();
		homeSteps.verifySuccessfullLogin();
	}
	
	@Test(priority=4, enabled=true)
	public void testLoginWithFacebook()
	{
		comnFunc.reportLogAndPrintInConsole("## LOGIN FROM POPUP TEST STARTS WITH FACEBOOK ACCOUNTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLoginWithFacebook(fbUserName, fbPassword);
		homeSteps.verifySuccessfullLogin();
	}
	
	@Test(priority=5, enabled=true)
	public void testLoginWithTruecaller()
	{
		comnFunc.reportLogAndPrintInConsole("## LOGIN FROM POPUP TEST STARTS WITH TRUECALLER ACCOUNTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLoginWithTrueCaller();
		WebDriverUtil.staticWait(1);
		homeSteps.verifySuccessfullLogin();
	}
}
