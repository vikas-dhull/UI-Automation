package com.mb.MsiteAutomation.regression;

import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class MBmsite_Test_Wallets extends ExtentReportingBaseUtil
{
  @Test(priority=1,enabled=true)
  public void testWallets() 
  {
	  homeStepsMbMsite.proceedToLogin();
	  homeStepsMbMsite.performMobileLogin(mobileNumber, password);
	  homeStepsMbMsite.verifyUserLoggedIn();
	  homeStepsMbMsite.openCartPage();
	  cartStepsMbMsite.makeCartEmpty();
	  homeStepsMbMsite.selectVariantFromLeftPanel("Whey Gold");
	  pdpStepsMbMsite.addToCart();
	  cartStepsMbMsite.proceedToCheckout();
	  selectAddressStepsMbMsite.verifyAddressPageDisplayed();
	  selectAddressStepsMbMsite.clickSavedAddress();
//	  paymentStepsMbMsite.selectPaymentType("INTERNET BANKING");
	  paymentStepsMbMsite.testWallets();
  }
}
