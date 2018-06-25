package com.mb.webAutomation.regression;

import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;

import junit.framework.Assert;

public class MB_Test_OrderPlace_COD extends ExtentReportingBaseUtil
{
  @Test(priority=1,enabled=true)
  public void orderPlaceCOD() 
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
	  paymentStepsMb.selectPaymentType("CASH ON DELIVERY");
	  paymentStepsMb.verifyCaptchDisplayed();
	  if((GlobalVar.jenkinsEnvironment==null && "MBmsiteqa".equalsIgnoreCase(PropertyHelper.readProperty("env"))) || "MBmsiteqa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
	  {
		  paymentStepsMb.placeCODOrder();
		  Assert.assertTrue(orderSuccessStepsMb.verifyOrderId());
		  orderSuccessStepsMb.cancelOrderPlaced("Ordered By Mistake");
		  Assert.assertTrue(orderSuccessStepsMb.verifyOrderCancelled());
	  }
  }
}
