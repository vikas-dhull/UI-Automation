package com.healthkart.hkAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
//import com.healthkart.hkAutomation.util.ExcelUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_TestLoginFromPopup extends ExtentReportingBaseUtil{
	
	/**
	 * Test Login with Mobile number and password.
	 */
	@Test(priority = 1, enabled=true)
	public void testLoginWithMobilePassword() {	
		System.out.println("## LOGIN TEST STARTS FROM POPUP PAGE with mobile and password..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM POPUP PAGE with mobile and password..");
		homeB.performLoginWithMobilePassword(testdata.get("Login_User_Mobile"), testdata.get("Login_User_Mobile_Password"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.PASS, "User successfully logged in with mobile and password");
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User logged out successfully..!!");
	}
	
	/**
	 * Test Login with Mobile number and OTP.
	 */
	@Test(priority = 2, enabled=true)
	public void testLoginWithMobileOtp() {
		System.out.println("## LOGIN TEST STARTS FROM POPUP PAGE with mobile and otp..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM POPUP PAGE with mobile and otp..");
		homeB.performLoginWithMobileOtp(testdata.get("Login_User_Mobile"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.PASS, "User successfully logged in with mobile and otp");
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User logged out successfully..!!");
	}
	
	/**
	 * Test Login with Google account.
	 */

	@Test(priority = 3, enabled=true)
	public void testLoginWithGoogle() {
		System.out.println("## LOGIN TEST STARTS FROM POPUP PAGE with Google Account..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM POPUP PAGE with Google Account..");
		homeB.performLoginWithGoogle(testdata.get("Google_Login_Email_Id"), testdata.get("Google_Login_Password"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");	
		test.log(LogStatus.PASS, "User successfully logged in with google account");
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User logged out successfully..!!");
	}
	
	/**
	 * Test Login with Facebook account.
	 */
	@Test(priority = 4, enabled=true)
	public void testLoginWithFacebook() {
		System.out.println("## LOGIN TEST STARTS FROM POPUP PAGE with facebook Account..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM POPUP PAGE with facebook Account..");
		homeB.performLoginWithFacebook(testdata.get("Facebook_Login_EmailId"), testdata.get("Facebook_Login_Password"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");	
		test.log(LogStatus.PASS, "User successfully logged in with facebook account");
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User logged out successfully..!!");
	}
	
	/**
	 * Test Forgot Password with Mobile number and OTP.
	 */
	@Test(priority = 5, enabled=true)
	public void testForgotPasswordOtp(){
		System.out.println("## FORGOT PASSWORD TEST STARTS FROM POPUP PAGE with OTP..");
		test.log(LogStatus.INFO, "FORGOT PASSWORD TEST STARTS FROM POPUP PAGE with OTP..");
		homeB.performForgotPasswordWithOTP(testdata.get("Login_User_Mobile"), testdata.get("Login_User_Mobile_Password"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.PASS, "User successfully logged in after changing password");
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User logged out successfully..!!");
	}	

}
