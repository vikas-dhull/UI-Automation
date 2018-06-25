package com.healthkart.hkMobileAutomation.regression;

import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_TestSignupMobileOtpPswd extends ExtentReportingBaseUtil{

	/**
	 * Test Signup with Mobile number and password.
	 */
	
	@Test(priority = 1)
	public void testSignupMobileOtpPswd() {
		comnFunc.reportLogAndPrintInConsole("## SIGNUP WITH MOBILE OTP TEST STARTS..");
		System.out.println("## Signup with Mobile number, Otp and password Test starts..");
		test.log(LogStatus.INFO, "Signup with Mobile number, Otp and password Test starts..");
		homeSteps.performSignupWithMobileOtpAndPswd("8059097875");
		homeSteps.verifySuccessfullLogin();
	}
}