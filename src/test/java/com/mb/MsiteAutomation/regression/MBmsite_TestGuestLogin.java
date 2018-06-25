package com.mb.MsiteAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class MBmsite_TestGuestLogin extends ExtentReportingBaseUtil
{
	
	@Test(priority=1,enabled=true)
	public void testMobileLogin()
	{
		homeStepsMbMsite.selectVariantFromLeftPanel("Whey Gold");
		pdpStepsMbMsite.addToCart();
		cartStepsMbMsite.proceedToCheckout();
		guestLoginStepsMbMsite.performMobileLogin(mobileNumber,password);
		Assert.assertTrue(selectAddressStepsMbMsite.verifyAddressPageDisplayed());
	}
	
	@Test(priority=2,enabled=true)
	public void testLoginViaOTP()
	{
		homeStepsMbMsite.selectVariantFromLeftPanel("Whey Gold");
		pdpStepsMbMsite.addToCart();
		cartStepsMbMsite.proceedToCheckout();
		guestLoginStepsMbMsite.performLoginViaOtp(mobileNumber);
		Assert.assertTrue(selectAddressStepsMbMsite.verifyAddressPageDisplayed());
	}
	
	@Test(priority=3,enabled=true)
	public void testLoginViaGoogle()
	{
		homeStepsMbMsite.selectVariantFromLeftPanel("Whey Gold");
		pdpStepsMbMsite.addToCart();
		cartStepsMbMsite.proceedToCheckout();
		guestLoginStepsMbMsite.performLoginViaGoogle(googleUserName, googlePassword);
	}
	
	@Test(priority=4, enabled=true)
	public void testLoginViaFacebook()
	{
		homeStepsMbMsite.selectVariantFromLeftPanel("Whey Gold");
		pdpStepsMbMsite.addToCart();
		cartStepsMbMsite.proceedToCheckout();
		guestLoginStepsMbMsite.performLoginViaFacebook(fbUserName,fbPassword);
	}
}
