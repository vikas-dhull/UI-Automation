package com.mb.webAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class MB_TestGuestLogin extends ExtentReportingBaseUtil
{
	
	@Test(priority=1,enabled=true)
	public void testMobileLogin()
	{
		homeStepsMb.selectVariantFromTopSellerMenu("Whey Gold");
		pdpStepsMb.addToCart();
		cartStepsMb.proceedToCheckout();
		guestLoginStepsMb.performMobileLogin(mobileNumber,password);
		Assert.assertTrue(selectAddressStepsMb.verifyAddressPageDisplayed());
	}
	
	@Test(priority=2,enabled=true)
	public void testLoginViaOTP()
	{
		homeStepsMb.selectVariantFromTopSellerMenu("Whey Gold");
		pdpStepsMb.addToCart();
		cartStepsMb.proceedToCheckout();
		guestLoginStepsMb.performLoginViaOtp(mobileNumber);
		Assert.assertTrue(selectAddressStepsMb.verifyAddressPageDisplayed());
	}
	
	@Test(priority=3,enabled=true)
	public void testLoginViaGoogle()
	{
		homeStepsMb.selectVariantFromTopSellerMenu("Whey Gold");
		pdpStepsMb.addToCart();
		cartStepsMb.proceedToCheckout();
		guestLoginStepsMb.performLoginViaGoogle(googleUserName, googlePassword);
	}
	
	@Test(priority=4, enabled=true)
	public void testLoginViaFacebook()
	{
		homeStepsMb.selectVariantFromTopSellerMenu("Whey Gold");
		pdpStepsMb.addToCart();
		cartStepsMb.proceedToCheckout();
		guestLoginStepsMb.performLoginViaFacebook(fbUserName,fbPassword);
	}
}
