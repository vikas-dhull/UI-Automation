package com.healthkart.hkMobileAutomation.regression;

import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import junit.framework.Assert;

public class HK_TestBXGYOrderPlaceCODLoginPopupAndroid extends ExtentReportingBaseUtil
{
	@Test(priority = 1)
	public void OrderPlace_BXGY() 
	{	
		comnFunc.reportLogAndPrintInConsole("## ORDER PLACE COD WITH BXGY OFFER TEST STARTS..");		
		homeSteps.proceedToLogin();
		loginSteps.performLoginWithMobileOtp(mobileNumber);
		homeSteps.verifySuccessfullLogin();
		homeSteps.proceedToCart();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
		/*homeSteps.searchProduct("stress");
		homeSteps.selectProductFromSearchResults("stress");*/
		homeSteps.selectProductCategories("Health Food & Drinks", "Healthy Drinks", "Apple Cider Vinegar");
		categorySteps.selectProduct("HealthViva", "0.5 L", "Unflavoured");
		pdpSteps.clickAddToCart();
		WebDriverUtil.staticWait(1);
		Assert.assertTrue(cartSteps.verifyBXGYApplied());
		WebDriverUtil.staticWait(1);
		cartSteps.proceedToCheckout();
		WebDriverUtil.staticWait(1);
		checkout.selectAddress();
		checkout.proceedToPay();
		paymentSteps.selectPaymentOption("CASH ON DELIVERY",testdata);
//		paymentSteps.placeOrder();
		paymentSteps.handleRateHealthkart();
		WebDriverUtil.staticWait(3);
		Assert.assertTrue(orderSteps.performValidationForOrder());
		orderSteps.cancelOrderViaViewOrder();
		Assert.assertTrue(orderSteps.verifyOrderCancelled());
		
	}

}
