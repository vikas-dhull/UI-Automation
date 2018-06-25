package com.mb.webAutomation.regression;

import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

import junit.framework.Assert;

public class MB_Test_HomePage extends ExtentReportingBaseUtil
{
  @Test(enabled=false, priority=1)
  public void testMBAuthenticity()
  {
	  Assert.assertTrue(homeStepsMb.verifyAuthenticationDiv());
	  homeStepsMb.verifyAuthenticationCode();
  }
  
  @Test(enabled=true, priority=2)
  public void testProteinLabCertificateButton()
  {
	  Assert.assertTrue(homeStepsMb.verifyProteinLabCertification());
  }
}
