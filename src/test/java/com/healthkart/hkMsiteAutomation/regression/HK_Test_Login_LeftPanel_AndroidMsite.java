package com.healthkart.hkMsiteAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_Login_LeftPanel_AndroidMsite extends ExtentReportingBaseUtil{
	
	@Test(priority=1,enabled=true)
	public void testLoginWithMobilePassword()
	{	
		System.out.println("## LOGIN TEST STARTS FROM ACCOUNTS LOGIN PAGE with mobile and password..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM ACCOUNTS LOGIN PAGE with mobile and password..");
		homeStep.proceedToLoginInLeftPanel();
//		
		AccLoginSteps.performLoginWithMobilePassword(testdata.get("Login_User_Mobile_Msite")
				, testdata.get("Login_User_Mobile_Password_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "home page not loaded..");
		test.log(LogStatus.PASS, "Home Page loaded successfully..");
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password..");
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");
	}
	
	@Test(priority=2,enabled=true)
	public void testLoginWithMobileOtp()
	{	
		System.out.println("## LOGIN TEST STARTS FROM ACCOUNTS LOGIN PAGE with mobile and Otp..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM ACCOUNTS LOGIN PAGE with mobile and Otp..");
		homeStep.proceedToLoginInLeftPanel();
		AccLoginSteps.performLoginWithMobileOtp(testdata.get("Login_User_Mobile_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "home page not loaded..");
		test.log(LogStatus.PASS, "Home Page loaded successfully..");
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password..");
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");		
	}
	
	@Test(priority=3,enabled=true)
	public void testLoginWithGoogle()
	{
		System.out.println("## LOGIN TEST STARTS FROM ACCOUNTS LOGIN PAGE with Google Accounts..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM ACCOUNTS LOGIN PAGE with Google Accounts..");
		homeStep.proceedToLoginInLeftPanel();
		AccLoginSteps.performLoginWithGoogle(testdata.get("Login_User_Google_Msite")
				,testdata.get("Login_User_Google_Password_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "home page not loaded..");
		test.log(LogStatus.PASS, "Home Page loaded successfully..");
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password..");
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");
	}
	
	@Test(priority=4, enabled=true)
	public void testLoginWithFacebook()
	{
		System.out.println("## LOGIN TEST STARTS FROM ACCOUNTS LOGIN PAGE with Facebook Account..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM ACCOUNTS LOGIN PAGE with Facebook Accounts..");
		homeStep.proceedToLoginInLeftPanel();
		AccLoginSteps.performLoginWithFacebook(testdata.get("Login_User_Facebook_Msite")
				,testdata.get("Login_User_Facebook_Password_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "home page not loaded..");
		test.log(LogStatus.PASS, "Home Page loaded successfully..");
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password..");
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");
	}
	
}
