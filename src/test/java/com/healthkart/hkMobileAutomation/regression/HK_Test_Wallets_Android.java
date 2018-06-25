package com.healthkart.hkMobileAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidKeyCode;

public class HK_Test_Wallets_Android  extends ExtentReportingBaseUtil 
{
	@Test(priority = 1,enabled=true)
	public void testOrderPlaceMobiKwikWalletLoginPopup() 
	{	
		SoftAssert softAssert= new SoftAssert();
		
		comnFunc.reportLogAndPrintInConsole("## ORDER PLACE PREPAID WITH WALLETS TEST STARTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLogin(mobileNumber,password);
//		homeSteps.verifySuccessfullLogin();
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
		paymentSteps.waitForPymntPage();
		
		softAssert.assertTrue(paymentSteps.selectWalletAndVerify("Mobikwik"));
		softAssert.assertTrue(paymentSteps.selectWalletAndVerify("Paytm"));
		softAssert.assertTrue(paymentSteps.selectWalletAndVerify("Freecharge"));
		softAssert.assertAll();
	}
	
	@Test(priority = 2,enabled=false)
	public void testOrderPlaceFreechargeWalletLoginPopup() 
	{	
		comnFunc.reportLogAndPrintInConsole("## ORDER PLACE PREPAID WITH FREECHARGE WALLET TEST STARTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLogin(mobileNumber,password);
//		homeSteps.verifySuccessfullLogin();
		homeSteps.proceedToCart();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
		homeSteps.selectProductCategories("Sports Nutrition", "Top Brands", "MuscleBlaze");
		categorySteps.sortListing("Price : Low To High");
		homeSteps.selectFirstProductFromSearchResult();
		pdpSteps.clickAddToCart();
		WebDriverUtil.staticWait(1);
		cartSteps.proceedToCheckout();
		WebDriverUtil.staticWait(1);
		checkout.selectAddress();
		checkout.proceedToPay();
		Assert.assertTrue(paymentSteps.selectWalletAndVerify("Freecharge"));
	}
	
	@Test(priority = 3,enabled=false)
	public void testOrderPlacePaytmWalletLoginPopup() 
	{	
		comnFunc.reportLogAndPrintInConsole("## ORDER PLACE PREPAID WITH PAYTM WALLET TEST STARTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLogin(mobileNumber,password);
//		homeSteps.verifySuccessfullLogin();
		homeSteps.proceedToCart();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
		homeSteps.selectProductCategories("Sports Nutrition", "Top Brands", "MuscleBlaze");
		categorySteps.sortListing("Price : Low To High");
		homeSteps.selectFirstProductFromSearchResult();
		pdpSteps.clickAddToCart();
		WebDriverUtil.staticWait(1);
		cartSteps.proceedToCheckout();
		WebDriverUtil.staticWait(1);
		checkout.selectAddress();
		checkout.proceedToPay();
		Assert.assertTrue(paymentSteps.selectWalletAndVerify("Paytm"));
	}
	
	
}
