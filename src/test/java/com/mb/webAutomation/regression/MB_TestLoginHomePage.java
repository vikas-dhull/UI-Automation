package com.mb.webAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class MB_TestLoginHomePage extends ExtentReportingBaseUtil
{
	
	@Test(priority=1,enabled=true)
	public void testGoogleLogin()
	{
		homeStepsMb.proceedToLogin();
		homeStepsMb.performGoogleLogin(googleUserName,googlePassword);
		Assert.assertTrue(homeStepsMb.verifyUserLoggedIn());
		homeStepsMb.performLogout();
		Assert.assertTrue(homeStepsMb.verifyUserLoggedOut());
	}
	
	@Test(priority=2,enabled=true)
	public void testFacebookLogin()
	{
		homeStepsMb.proceedToLogin();
		homeStepsMb.performFacebookLogin(fbUserName,fbPassword);
		Assert.assertTrue(homeStepsMb.verifyUserLoggedIn());
		homeStepsMb.performLogout();
		Assert.assertTrue(homeStepsMb.verifyUserLoggedOut());
	}
	
	@Test(priority=3,enabled=true)
	public void testLoginViaOTP()
	{
		homeStepsMb.proceedToLogin();
		homeStepsMb.perfromLoginViaOTP(mobileNumber);
		Assert.assertTrue(homeStepsMb.verifyUserLoggedIn());
		homeStepsMb.performLogout();
		Assert.assertTrue(homeStepsMb.verifyUserLoggedOut());
	}
	
	@Test(priority=4,enabled=true)
	public void testMobileLogin()
	{
		homeStepsMb.proceedToLogin();
		homeStepsMb.performMobileLogin(mobileNumber, password);
		Assert.assertTrue(homeStepsMb.verifyUserLoggedIn());
		homeStepsMb.performLogout();
		Assert.assertTrue(homeStepsMb.verifyUserLoggedOut());
	}
}
