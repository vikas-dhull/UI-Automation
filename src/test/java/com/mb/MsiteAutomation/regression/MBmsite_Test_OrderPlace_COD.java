package com.mb.MsiteAutomation.regression;

import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;

import junit.framework.Assert;

public class MBmsite_Test_OrderPlace_COD extends ExtentReportingBaseUtil
{
  @Test(priority=1,enabled=true)
  public void orderPlaceCOD() 
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
	  paymentStepsMbMsite.selectPaymentType("CASH ON DELIVERY");
	  paymentStepsMbMsite.verifyCaptchDisplayed();
	  if((GlobalVar.jenkinsEnvironment==null && "MBmsiteqa".equalsIgnoreCase(PropertyHelper.readProperty("env"))) || "MBmsiteqa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
	  {
		  paymentStepsMbMsite.placeCODOrder();
		  Assert.assertTrue(orderSuccessStepsMbMsite.verifyOrderId());
		  orderSuccessStepsMbMsite.cancelOrderPlaced("Ordered By Mistake",false);
		  Assert.assertTrue(orderSuccessStepsMbMsite.verifyOrderCancelled());
	  }
  }
}
