package com.healthkart.hkMsiteAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_Login_Popup_AndroidMsite extends ExtentReportingBaseUtil
{	
	@Test(priority=1,enabled=true)
	public void testLoginWithMobilePassword()
	{	
		System.out.println("## LOGIN TEST STARTS FROM POPUP PAGE with mobile and password..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM POPUP PAGE with mobile and password..");
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performLoginWithMobilePassword(testdata.get("Login_User_Mobile_Msite")
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
		System.out.println("## LOGIN TEST STARTS FROM POPUP PAGE with mobile and Otp..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM POPUP PAGE with mobile and Otp..");
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performLoginWithMobileOtp(testdata.get("Login_User_Mobile_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "home page not loaded..");
		test.log(LogStatus.PASS, "Home Page loaded successfully..");
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and Otp..");
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");		
	}
	
	@Test(priority=3,enabled=true)
	public void testLoginWithGoogle()
	{
		System.out.println("## LOGIN TEST STARTS FROM POPUP PAGE with Google..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM POPUP PAGE with Google..");
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performLoginWithGoogle(testdata.get("Login_User_Google_Msite")
				,testdata.get("Login_User_Google_Password_Msite"));
		comnFunc.staticWait(3);
		Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "home page not loaded..");
		test.log(LogStatus.PASS, "Home Page loaded successfully..");
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with google..");
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");
	}
	
	@Test(priority=4, enabled=true)
	public void testLoginWithFacebook()
	{
		System.out.println("## LOGIN TEST STARTS FROM POPUP PAGE with Facebook..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM POPUP PAGE with Facebook..");
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performLoginWithFacebook(testdata.get("Login_User_Facebook_Msite")
				,testdata.get("Login_User_Facebook_Password_Msite"));
		comnFunc.staticWait(3);
		Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "home page not loaded..");
		test.log(LogStatus.PASS, "Home Page loaded successfully..");
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with facebook..");
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");
	}
	
	@Test(priority=5, enabled=true)
	public void testForgotpasswordWithOtp()
	{
		System.out.println("## FORGOT PASSWORD TEST STARTS FROM POPUP PAGE with OTP..");
		test.log(LogStatus.INFO, "FORGOT PASSWORD TEST STARTS FROM POPUP PAGE with OTP..");
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performForgotPasswordWithOTP(testdata.get("Login_User_Mobile_Msite")
				, testdata.get("Login_User_Mobile_Password_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "home page not loaded..");
		test.log(LogStatus.PASS, "Home Page loaded successfully..");
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and Otp..");
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");
	}	
		
}