package com.mb.webAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class MB_Test_Coupon_Order extends ExtentReportingBaseUtil
{
  @Test
  public void couponOrder() 
  {
	  homeStepsMb.proceedToLogin();
	  homeStepsMb.performMobileLogin(mobileNumber, password);
	  homeStepsMb.verifyUserLoggedIn();
	  homeStepsMb.openCartPage();
	  cartStepsMb.makeCartEmpty();
	  homeStepsMb.selectVariantFromTopSellerMenu("Whey Gold");
	  pdpStepsMb.addToCart();
	  if(cartStepsMb.applyCouponCode()) // pass coupon code or leave blank to apply existing coupon code from DB
		  Assert.assertTrue(cartStepsMb.verifyCouponApplied());
  }
}
