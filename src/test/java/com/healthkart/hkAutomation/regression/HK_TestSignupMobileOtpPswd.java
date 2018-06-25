package com.healthkart.hkAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_TestSignupMobileOtpPswd extends ExtentReportingBaseUtil{

	/**
	 * Test Login with Mobile number and password.
	 * @throws InterruptedException
	 */
	@Test(priority = 1, enabled=true)
	public void testSignupMobileOtpPswd() {	
		System.out.println("## Signup with Mobile number, Otp and password Test starts..");
		test.log(LogStatus.INFO, "Signup with Mobile number, Otp and password Test starts..");
		homeB.performSignupWithMobileOtpAndPswd(testdata.get("Login_User_Mobile_Password"));
		// WebDriverUtil.staticWait(90);
		Assert.assertTrue(homeB.verifyUserSignup(), "User failed to Login");
		test.log(LogStatus.PASS, "User successfully creted new account..");
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User logged out successfully..!!");
	}
}