package com.mb.MsiteAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class MBmsite_TestLoginHomePage extends ExtentReportingBaseUtil
{
	
	@Test(priority=1,enabled=true)
	public void testGoogleLogin()
	{
		homeStepsMbMsite.proceedToLogin();
		homeStepsMbMsite.performGoogleLogin(googleUserName,googlePassword);
		Assert.assertTrue(homeStepsMbMsite.verifyUserLoggedIn());
		homeStepsMbMsite.performLogout();
		Assert.assertTrue(homeStepsMbMsite.verifyUserLoggedOut());
	}
	
	@Test(priority=2,enabled=true)
	public void testFacebookLogin()
	{
		homeStepsMbMsite.proceedToLogin();
		homeStepsMbMsite.performFacebookLogin(fbUserName,fbPassword);
		Assert.assertTrue(homeStepsMbMsite.verifyUserLoggedIn());
		homeStepsMbMsite.performLogout();
		Assert.assertTrue(homeStepsMbMsite.verifyUserLoggedOut());
	}
	
	@Test(priority=3,enabled=true)
	public void testLoginViaOTP()
	{
		homeStepsMbMsite.proceedToLogin();
		homeStepsMbMsite.perfromLoginViaOTP(mobileNumber);
		Assert.assertTrue(homeStepsMbMsite.verifyUserLoggedIn());
		homeStepsMbMsite.performLogout();
		Assert.assertTrue(homeStepsMbMsite.verifyUserLoggedOut());
	}
	
	@Test(priority=4,enabled=true)
	public void testMobileLogin()
	{
		homeStepsMbMsite.proceedToLogin();
		homeStepsMbMsite.performMobileLogin(mobileNumber, password);
		Assert.assertTrue(homeStepsMbMsite.verifyUserLoggedIn());
		homeStepsMbMsite.performLogout();
		Assert.assertTrue(homeStepsMbMsite.verifyUserLoggedOut());
	}
}
