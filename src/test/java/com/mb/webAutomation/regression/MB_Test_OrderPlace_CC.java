package com.mb.webAutomation.regression;

import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

import junit.framework.Assert;

public class MB_Test_OrderPlace_CC extends ExtentReportingBaseUtil
{
  @Test(priority=1,enabled=true)
  public void orderPlaceCC() 
  {
	  homeStepsMb.proceedToLogin();
	  homeStepsMb.performMobileLogin(mobileNumber, password);
	  homeStepsMb.verifyUserLoggedIn();
	  homeStepsMb.openCartPage();
	  cartStepsMb.makeCartEmpty();
	  homeStepsMb.selectVariantFromTopSellerMenu("Whey Gold");
	  pdpStepsMb.addToCart();
	  cartStepsMb.proceedToCheckout();
	  selectAddressStepsMb.verifyAddressPageDisplayed();
	  selectAddressStepsMb.clickSavedAddress();
	  paymentStepsMb.selectPaymentType("CREDIT CARD");
	  paymentStepsMb.enterCardDetails(testdata);
	  paymentStepsMb.proceedToPayment();
	  Assert.assertTrue(paymentStepsMb.verifyPaymentNotSuccessfullDisplayed());
	  
	  
  }
}
