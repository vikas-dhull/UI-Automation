package com.healthkart.hkAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_Saved_Cards extends ExtentReportingBaseUtil
{
	private int cartCount;
	@Test(enabled=true)
	public void testSavedCards()
	{
		homeB.performLoginWithMobilePassword(testdata.get("Login_User_Saved_Card"), testdata.get("Login_User_Password_Saved_Card"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password to check user's cart empty..??");
		cartCount = homeB.getCartCountForUser();
		if(cartCount != 0) {
			test.log(LogStatus.INFO, "There are items in user's cart..");
			homeB.proceedToCart();
//			Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
//			test.log(LogStatus.INFO, "cart page loaded..");
			cartB.makeCartEmpty();
			cartB.clickBackToShopping();
			Assert.assertTrue(homeB.verifyHomePageloaded(), "home page not loaded..");
			test.log(LogStatus.INFO, "user cart is empty now..");
		}else {
			test.log(LogStatus.INFO, "User cart is found empty..");
		}
		WebDriverUtil.staticWait(5);
		homeB.visitBrandPage();
		Assert.assertTrue(brndB.verifyBrndPageLoaded(), "Fail to load Brands page..");
		test.log(LogStatus.INFO, "Brands page loaded..");
		brndB.selectSortByPriceLH();
		WebDriverUtil.staticWait(3);
		
		Assert.assertTrue(brndB.verifySortByPriceLH(), "Fail to Sort results on Brands page..");
		test.log(LogStatus.INFO, "Sorted by Price low to high results loaded on Brands page..");
		brndB.visitPDPPageForFirstVariant();
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded for online payment..");
		pdpB.addVariantToCart();
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
			
		cartB.clickProceedToCheckout();
		Assert.assertTrue(addB.verifyAddressPageLoaded(), "Fail to load select address page..");
		test.log(LogStatus.INFO, "Address page loaded..");
		addB.selectDeliveryAddress();
		Assert.assertTrue(pymntB.verifyPaymentPageLoaded(), "Fail to load Payment page..");
		test.log(LogStatus.INFO, "Payment page loaded..");
		pymntB.verifySavedCards(testdata.get("Saved_Card_CVV"));
	}
}
