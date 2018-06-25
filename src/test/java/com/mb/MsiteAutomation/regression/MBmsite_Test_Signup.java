package com.mb.MsiteAutomation.regression;

import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class MBmsite_Test_Signup extends ExtentReportingBaseUtil
{
  @Test(priority=1,enabled=true)
  public void testSignup() 
  {
	  homeStepsMbMsite.proceedToLogin();
	  homeStepsMbMsite.performSignUp("8059097875");
  }
}
