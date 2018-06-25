package com.healthkart.hkMsiteAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_Login_Accounts_AndroidMsite extends ExtentReportingBaseUtil{
	
	@Test(priority=1,enabled=true)
	public void testLoginWithMobilePassword()
	{	
		System.out.println("## LOGIN TEST STARTS FROM ACCOUNTS LOGIN PAGE with mobile and password..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM ACCOUNTS LOGIN PAGE with mobile and password..");
		homeStep.goToHKAccountsLoginPage();
		Assert.assertTrue(AccLoginSteps.verifyAccountsLoginPageLoaded(), "Failed to load Accounts' login page.");
		test.log(LogStatus.INFO, "Accounts' login page loaded..");
		AccLoginSteps.performLoginWithMobilePassword(testdata.get("Login_User_Mobile_Msite")
				, testdata.get("Login_User_Mobile_Password_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(profSteps.verifyProfilePageLoaded(),"User failed to Login");
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
		homeStep.goToHKAccountsLoginPage();
		Assert.assertTrue(AccLoginSteps.verifyAccountsLoginPageLoaded(), "Failed to load Accounts' login page.");
		test.log(LogStatus.INFO, "Accounts' login page loaded..");
		AccLoginSteps.performLoginWithMobileOtp(testdata.get("Login_User_Mobile_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(profSteps.verifyProfilePageLoaded(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and Otp..");
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");		
	}
	
	@Test(priority=3,enabled=true)
	public void testLoginWithGoogle()
	{
		System.out.println("## LOGIN TEST STARTS FROM ACCOUNTS LOGIN PAGE with Google Accounts..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM ACCOUNTS LOGIN PAGE with Google Accounts..");
		homeStep.goToHKAccountsLoginPage();
		Assert.assertTrue(AccLoginSteps.verifyAccountsLoginPageLoaded(), "Failed to load Accounts' login page.");
		test.log(LogStatus.INFO, "Accounts' login page loaded..");
		AccLoginSteps.performLoginWithGoogle(testdata.get("Login_User_Google_Msite")
				,testdata.get("Login_User_Google_Password_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(profSteps.verifyProfilePageLoaded(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and Otp..");
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");
	}
	
	@Test(priority=4, enabled=true)
	public void testLoginWithFacebook()
	{
		System.out.println("## LOGIN TEST STARTS FROM ACCOUNTS LOGIN PAGE with Facebook Account..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM ACCOUNTS LOGIN PAGE with Facebook Accounts..");
		homeStep.goToHKAccountsLoginPage();
		Assert.assertTrue(AccLoginSteps.verifyAccountsLoginPageLoaded(), "Failed to load Accounts' login page.");
		test.log(LogStatus.INFO, "Accounts' login page loaded..");
		AccLoginSteps.performLoginWithFacebook(testdata.get("Login_User_Facebook_Msite")
				,testdata.get("Login_User_Facebook_Password_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(profSteps.verifyProfilePageLoaded(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and Otp..");
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");
	}
	
	@Test(priority=5, enabled=true)
	public void testForgotpasswordWithOtp()
	{
		System.out.println("## FORGOT PASSWORD TEST STARTS FROM ACCOUNTS PAGE with OTP..");
		test.log(LogStatus.INFO, "FORGOT PASSWORD TEST STARTS FROM ACCOUNTS PAGE with OTP..");
		homeStep.goToHKAccountsLoginPage();
		Assert.assertTrue(AccLoginSteps.verifyAccountsLoginPageLoaded(), "Failed to load Accounts' login page.");
		test.log(LogStatus.INFO, "Accounts' login page loaded..");
		AccLoginSteps.performForgotPasswordWithOTP(testdata.get("Login_User_Mobile_Msite")
				, testdata.get("Login_User_Mobile_Password_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(profSteps.verifyProfilePageLoaded(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and Otp..");
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");
	}
}
