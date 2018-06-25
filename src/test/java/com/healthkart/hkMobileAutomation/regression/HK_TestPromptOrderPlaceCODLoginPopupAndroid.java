package com.healthkart.hkMobileAutomation.regression;

import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import junit.framework.Assert;

public class HK_TestPromptOrderPlaceCODLoginPopupAndroid extends ExtentReportingBaseUtil
{
	@Test(priority = 1)
	public void testPromptOrderPlaceCODWithLoginPopup() 
	{	
		comnFunc.reportLogAndPrintInConsole("## ORDER PLACE COD WITH PROMPT OFFER LOGIN FROM POPUP TEST STARTS..");
		
		homeSteps.proceedToLogin();
		loginSteps.performLoginWithMobileOtp(mobileNumber);
		homeSteps.verifySuccessfullLogin();
		homeSteps.proceedToCart();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
//		homeSteps.searchProduct(ExcelUtil.getHKPromptOfferVariantName());
/*		homeSteps.searchProduct("Lean");
		homeSteps.selectProductFromSearchResults("Lean");*/
		homeSteps.selectProductCategories("Sports Nutrition", "Gainers", "Mass Gainers");
		categorySteps.selectProduct("Lean Mass Gainer", "6.6 lb", "Chocolate");
		pdpSteps.clickAddToCart();
		cartSteps.clickApplyCouponsAndOffers();		
		cartSteps.applyPromptOfferAndVerify("Extra 10% off on MuscleBlaze High Protein Gainer");
		WebDriverUtil.staticWait(1);
		Assert.assertTrue(cartSteps.verifyCouponApplied());
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
