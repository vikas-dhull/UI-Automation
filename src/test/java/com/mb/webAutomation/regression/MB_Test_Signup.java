package com.mb.webAutomation.regression;

import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class MB_Test_Signup extends ExtentReportingBaseUtil
{
  @Test(priority=1,enabled=true)
  public void testSignup() 
  {
	  homeStepsMb.proceedToLogin();
	  homeStepsMb.performSignUp("8059097875");
  }
}
