package com.healthkart.hkMobileAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Test_Order_Place_DebitCard_Android extends ExtentReportingBaseUtil {
	
	/**
	 * Test Login with Mobile number and password and Order Place with CC.
	 */
	@Test(priority = 1)
	public void testOrderPlaceDebitCardLoginPopup() 
	{	
		comnFunc.reportLogAndPrintInConsole("## ORDER PLACE PREPAID WITH DEBIT CARD TEST STARTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLoginWithGoogle();
		homeSteps.verifySuccessfullLogin();
		homeSteps.proceedToCart();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
		homeSteps.selectProductCategories("Sports Nutrition", "Proteins", "Whey Proteins");
		homeSteps.selectFirstProductFromSearchResult();
		pdpSteps.clickAddToCart();
		WebDriverUtil.staticWait(1);
		cartSteps.proceedToCheckout();
		WebDriverUtil.staticWait(1);
		checkout.selectAddress();
		checkout.proceedToPay();
		paymentSteps.selectPaymentOption("DEBIT CARD",testdata);
		paymentSteps.handleRateHealthkart();
		WebDriverUtil.staticWait(3);
		Assert.assertTrue(orderSteps.performValidationForOrder());

		// Cancellation of order
		
		orderSteps.cancelOrderViaViewOrder("Card");
		Assert.assertTrue(orderSteps.verifyOrderCancelled());

	}
}
