package com.healthkart.hkMobileAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Test_Saved_Cards_Android extends ExtentReportingBaseUtil 
{
	@Test(priority = 1)
	public void testOrderPlaceSavedCards() 
	{	
		comnFunc.reportLogAndPrintInConsole("## ORDER PLACE PREPAID WITH CREDIT CARD Test starts..");
		homeSteps.proceedToLogin();
		loginSteps.performLogin(testdata.get("Login_User_Saved_Card"), testdata.get("Login_User_Password_Saved_Card"));
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
		paymentSteps.selectPaymentOption("Saved Cards",testdata);
		Assert.assertTrue(paymentSteps.verifyPayUPageDisplayed());
	}
}