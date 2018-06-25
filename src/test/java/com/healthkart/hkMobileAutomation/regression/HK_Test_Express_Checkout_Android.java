package com.healthkart.hkMobileAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Test_Express_Checkout_Android extends ExtentReportingBaseUtil
{
	@Test(priority = 1, enabled=true)
	public void bxgyChecksWithLoggedInUser() 
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
		pdpSteps.clickBuyNow();
/*		WebDriverUtil.staticWait(1);
		Assert.assertTrue(cartSteps.verifyBXGYApplied());
		WebDriverUtil.staticWait(1);
		cartSteps.proceedToCheckout();
		WebDriverUtil.staticWait(1);
		checkout.selectAddress();
		checkout.proceedToPay();*/
		paymentSteps.selectPaymentOptionExpressCheckout("CASH ON DELIVERY",testdata);
//		paymentSteps.placeOrder();
		paymentSteps.handleRateHealthkart();
		WebDriverUtil.staticWait(3);
		Assert.assertTrue(orderSteps.performValidationForOrder());
		orderSteps.cancelOrderViaViewOrder();
		Assert.assertTrue(orderSteps.verifyOrderCancelled());
		
	}
	
	@Test(priority = 2, enabled=true)
	public void testOrderPlaceHKCashLoginPopup() {
		comnFunc.reportLogAndPrintInConsole("## ORDER PLACE PREPAID WITH FULL HK-CASH TEST STARTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLogin(mobileNumber, password);
		homeSteps.verifySuccessfullLogin();
		homeSteps.proceedToCart();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
		homeSteps.selectProductCategories("Sports Nutrition", "Top Brands", "MuscleBlaze");
		categorySteps.sortListing("Price : Low To High");
		homeSteps.selectFirstProductFromSearchResult();
		WebDriverUtil.addHKCashForUser(mobileNumber, "full",1);
		pdpSteps.clickBuyNow();
		/*WebDriverUtil.staticWait(1);
		cartSteps.proceedToCheckout();
		WebDriverUtil.staticWait(1);
		checkout.selectAddress();
		checkout.proceedToPay();
		Assert.assertTrue(WebDriverUtil.verifyHKCashPresentForUser(fbUserMobile, "full",1),"Sufficient HKCASH not present for this user cart..");
		paymentSteps.useHKCash();*/
		paymentSteps.clickPlaceOrderButtonForHKCash();
		paymentSteps.handleRateHealthkart();
		WebDriverUtil.staticWait(3);
		Assert.assertTrue(orderSteps.performValidationForOrder());

		orderSteps.cancelOrderViaViewOrder();
		Assert.assertTrue(orderSteps.verifyOrderCancelled());
	}

}
