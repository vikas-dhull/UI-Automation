package com.healthkart.hkMobileAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_TestOrderPlaceHKCashLoginPopup_Android extends ExtentReportingBaseUtil 
{
	@Test(priority = 1)
	public void testOrderPlaceHKCashLoginPopup() {
		comnFunc.reportLogAndPrintInConsole("## ORDER PLACE PREPAID WITH FULL HK-CASH TEST STARTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLoginWithFacebook(fbUserName, fbPassword);
		homeSteps.verifySuccessfullLogin();
		homeSteps.proceedToCart();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
		homeSteps.searchProduct("MuscleBlaze");
		homeSteps.selectBrandFromSearchResult("Muscleblaze");
		categorySteps.sortListing("Price : Low To High");
		homeSteps.selectFirstProductFromSearchResult();
		WebDriverUtil.addHKCashForUser(fbUserMobile, "full",1);
		pdpSteps.clickAddToCart();
		WebDriverUtil.staticWait(1);
		cartSteps.proceedToCheckout();
		WebDriverUtil.staticWait(1);
		checkout.selectAddress();
		checkout.proceedToPay();
		Assert.assertTrue(WebDriverUtil.verifyHKCashPresentForUser(fbUserMobile, "full",1),"Sufficient HKCASH not present for this user cart..");
		paymentSteps.useHKCash();
		paymentSteps.clickPlaceOrderButtonForHKCash();
		paymentSteps.handleRateHealthkart();
		WebDriverUtil.staticWait(3);
		Assert.assertTrue(orderSteps.performValidationForOrder());

		orderSteps.cancelOrderViaViewOrder();
		Assert.assertTrue(orderSteps.verifyOrderCancelled());
	}
}
