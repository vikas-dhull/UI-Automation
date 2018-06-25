package com.mb.webAutomation.regression;

import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class MB_Test_Wallets extends ExtentReportingBaseUtil
{
  @Test(priority=1,enabled=true)
  public void testWallets() 
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
//	  paymentStepsMb.selectPaymentType("INTERNET BANKING");
	  paymentStepsMb.testWallets();
  }
}
