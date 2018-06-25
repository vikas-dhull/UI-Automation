package com.healthkart.hkMobileAutomation.regression;

import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import junit.framework.Assert;

public class HK_TestCouponOrderPlaceCODLoginPopup_Android extends ExtentReportingBaseUtil 
{
	/**
	 * 
	 */
	@Test(priority = 1)
	public void testCouponOrderPlaceCODWithLoginPopup() 
	{	
		comnFunc.reportLogAndPrintInConsole("## ORDER PLACE COD WITH COUPON OFFER TEST STARTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLogin(mobileNumber, password);
		homeSteps.verifySuccessfullLogin();
		homeSteps.proceedToCart();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();		
		homeSteps.selectProductCategories("Sports Nutrition", "Proteins", "Whey Proteins");
		categorySteps.selectProduct("MuscleBlaze Whey Protein","4.4 lb", "Rich Milk Chocolate");		
		pdpSteps.clickAddToCart();
		WebDriverUtil.staticWait(1);
		cartSteps.clickApplyCouponsAndOffers();
		cartSteps.applyCoupon("Deal15");
		WebDriverUtil.staticWait(1);
		Assert.assertTrue(cartSteps.verifyCouponApplied());
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
