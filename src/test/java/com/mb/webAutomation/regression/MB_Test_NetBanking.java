package com.mb.webAutomation.regression;

import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class MB_Test_NetBanking extends ExtentReportingBaseUtil
{
  @Test(enabled=true, priority=1)
  public void testNetBankingAccountRedirections() 
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
	  paymentStepsMb.testNetBankingAccounts();
  }
}
