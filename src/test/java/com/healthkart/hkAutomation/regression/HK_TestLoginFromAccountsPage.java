package com.healthkart.hkAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
//import com.healthkart.hkAutomation.util.ExcelUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_TestLoginFromAccountsPage extends ExtentReportingBaseUtil{
		
		public void goToAccountLoginPage() {
			
			homeB.performLoginWithMobilePassword(testdata.get("Login_User_Mobile"), testdata.get("Login_User_Mobile_Password"));
			Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
			test.log(LogStatus.INFO, "User successfully logged in with mobile and password to take user to HK Accounts Login Page..");
			homeB.openMyProfile();
			Assert.assertTrue(myProfB.verifyProfilePageLoaded(),"Profile page failed to open..");
			test.log(LogStatus.INFO, "User profile page loaded..");
			homeB.logout();			
			Assert.assertTrue(AccHkB.verifyAccountsLoginPageLoaded(), "Accounts Page failed to load..");
			test.log(LogStatus.INFO, "HK Accounts Login Page loaded after logout from profile page..");
		}
		
		/**
		 * Test Login with Mobile number and password.
		 */
		@Test(priority = 1, enabled=true)
		public void testLoginWithMobilePassword() {	
			
			System.out.println("## LOGIN TEST STARTS FROM ACCOUNTS PAGE with mobile and password..");
			test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM ACCOUNTS PAGE with mobile and password..");
			goToAccountLoginPage();
			AccHkB.performLoginWithMobilePassword(testdata.get("Login_User_Mobile"), testdata.get("Login_User_Mobile_Password"));
			Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
			test.log(LogStatus.PASS, "User successfully logged in with mobile and password from HK Accounts Login Page..");
			homeB.logout();
			Assert.assertTrue(AccHkB.verifyAccountsLoginPageLoaded(), "Accounts Page failed to load..");
			test.log(LogStatus.INFO, "User logged out successfully..!!");			
		}
		
		/**
		 * Test Login with Mobile number and otp.
		 */
		@Test(priority = 2, enabled=true)
		public void testLoginWithMobileOtp() {
			
			System.out.println("## LOGIN TEST STARTS FROM ACCOUNTS PAGE with mobile and otp..");
			test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM ACCOUNTS PAGE with mobile and otp..");
			goToAccountLoginPage();
			AccHkB.performLoginWithMobileOtp(testdata.get("Login_User_Mobile"));
			Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
			test.log(LogStatus.PASS, "User successfully logged in with mobile and otp from HK Accounts Login Page..");
			homeB.logout();
			Assert.assertTrue(AccHkB.verifyAccountsLoginPageLoaded(), "Accounts Page failed to load..");
			test.log(LogStatus.INFO, "User logged out successfully..!!");
		}
		
		/**
		 * Test Login with google.
		 */
		@Test(priority = 3, enabled=true)
		public void testLoginWithGoogle() {
			
			System.out.println("## LOGIN TEST STARTS FROM ACCOUNTS PAGE with Google Account..");
			test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM ACCOUNTS PAGE with Google Account..");
			goToAccountLoginPage();
			AccHkB.performLoginWithGoogle(testdata.get("Google_Login_Email_Id"), testdata.get("Google_Login_Password"));
			Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login with Google Account..");	
			test.log(LogStatus.PASS, "User successfully logged in with Google Account from HK Accounts Login Page..");
			homeB.logout();
			Assert.assertTrue(AccHkB.verifyAccountsLoginPageLoaded(), "Accounts Page failed to load..");
			test.log(LogStatus.INFO, "User logged out successfully..!!");
		}
		
		/**
		 * Test Login with facebook.
		 */
		@Test(priority = 4, enabled=true)
		public void testLoginWithfacebook() {
			
			System.out.println("## LOGIN TEST STARTS FROM ACCOUNTS PAGE with Facebook Account..");
			test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM ACCOUNTS PAGE with Facebook Account..");
			goToAccountLoginPage();
			AccHkB.performLoginWithFacebook(testdata.get("Facebook_Login_EmailId"), testdata.get("Facebook_Login_Password"));
			Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login with Facebook Account..");
			test.log(LogStatus.PASS, "User successfully logged in with Facebook Account from HK Accounts Login Page..");
			homeB.logout();
			Assert.assertTrue(AccHkB.verifyAccountsLoginPageLoaded(), "Accounts Page failed to load..");
			test.log(LogStatus.INFO, "User logged out successfully..!!");
		}
		
		/**
		 * Test Forgot Password with Mobile number and OTP.
		 */		
		@Test(priority = 5, enabled=true)
		public void testForgotPasswordOtp(){
			System.out.println("## FORGOT PASSWORD TEST STARTS FROM ACCOUNTS PAGE with OTP..");
			test.log(LogStatus.INFO, "FORGOT PASSWORD TEST STARTS FROM ACCOUNTS PAGE with OTP..");
			goToAccountLoginPage();
			AccHkB.performForgotPassword(testdata.get("Login_User_Mobile"), testdata.get("Login_User_Mobile_Password"));
			Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
			test.log(LogStatus.PASS, "User successfully logged in after password reset..");
			homeB.logout();
			Assert.assertTrue(AccHkB.verifyAccountsLoginPageLoaded(), "Accounts Page failed to load..");
			test.log(LogStatus.INFO, "User logged out successfully..!!");
			
		}

}
