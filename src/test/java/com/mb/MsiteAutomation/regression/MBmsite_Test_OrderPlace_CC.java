package com.mb.MsiteAutomation.regression;

import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;

import junit.framework.Assert;

public class MBmsite_Test_OrderPlace_CC extends ExtentReportingBaseUtil
{
  @Test(priority=1,enabled=true)
  public void orderPlaceCC() 
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
	  paymentStepsMbMsite.selectPaymentType("CREDIT CARD");
	  paymentStepsMbMsite.enterCardDetails(testdata);
	  paymentStepsMbMsite.proceedToPayment();
	  if((GlobalVar.jenkinsEnvironment==null && "MBmsiteqa".equalsIgnoreCase(PropertyHelper.readProperty("env"))) || "MBmsiteqa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
	  {
//		  paymentStepsMb.placeCODOrder();
		  Assert.assertTrue(orderSuccessStepsMbMsite.verifyOrderId());
		  orderSuccessStepsMbMsite.cancelOrderPlaced("Ordered By Mistake",true);
		  Assert.assertTrue(orderSuccessStepsMbMsite.verifyOrderCancelled());
	  }
  }
}
