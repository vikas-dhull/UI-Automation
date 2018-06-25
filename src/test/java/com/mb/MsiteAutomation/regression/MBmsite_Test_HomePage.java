package com.mb.MsiteAutomation.regression;

import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

import junit.framework.Assert;

public class MBmsite_Test_HomePage extends ExtentReportingBaseUtil
{
  @Test(enabled=false, priority=1)
  public void testMBAuthenticity()
  {
	  Assert.assertTrue(homeStepsMbMsite.verifyAuthenticationDiv());
	  homeStepsMbMsite.verifyAuthenticationCode();
  }
  
  @Test(enabled=true, priority=2)
  public void testProteinLabCertificateButton()
  {
	  Assert.assertTrue(homeStepsMbMsite.verifyProteinLabCertification());
  }
}
