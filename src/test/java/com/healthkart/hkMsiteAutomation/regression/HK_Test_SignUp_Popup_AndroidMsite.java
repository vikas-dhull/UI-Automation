package com.healthkart.hkMsiteAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_SignUp_Popup_AndroidMsite extends ExtentReportingBaseUtil
{
	/**
	 * Test Login with Mobile number and password.
	 */
	@Test(priority = 1, enabled = true)
	public void testSignupMobileOtpPswd() 
	{	
		System.out.println("## Signup with Mobile number, Otp and password Test starts..");
		test.log(LogStatus.INFO, "Signup with Mobile number, Otp and password Test starts..");
		homeStep.proceedToSignup();		
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performSignupWithMobileNumber(testdata.get("Login_User_Mobile_Password_Msite"));
		homeStep.waitContinueForSignup();
		Assert.assertTrue(homeStep.verifyUserSignup(), "User failed to Signup");
		test.log(LogStatus.PASS, "User successfully created new account..");
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");
	}
}