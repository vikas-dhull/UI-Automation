package com.mb.MsiteAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class MBmsite_Test_Coupon_Order extends ExtentReportingBaseUtil
{
  @Test
  public void couponOrder() 
  {
	  homeStepsMbMsite.proceedToLogin();
	  homeStepsMbMsite.performMobileLogin(mobileNumber, password);
	  homeStepsMbMsite.verifyUserLoggedIn();
	  homeStepsMbMsite.openCartPage();
	  cartStepsMbMsite.makeCartEmpty();
	  homeStepsMbMsite.selectVariantFromLeftPanel("Whey Gold");
	  pdpStepsMbMsite.addToCart();
	  cartStepsMbMsite.applyCouponCode("ZIDDIPUNE"); // pass coupon code or leave blank to apply existing coupon code from DB
	  Assert.assertTrue(cartStepsMbMsite.verifyCouponApplied());
  }
}
