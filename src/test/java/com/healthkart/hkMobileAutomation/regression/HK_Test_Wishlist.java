package com.healthkart.hkMobileAutomation.regression;

import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class HK_Test_Wishlist extends ExtentReportingBaseUtil 
{
	@Test(priority = 1)
	public void testWishlistOnPDP() 
	{	
		comnFunc.reportLogAndPrintInConsole("## Test Wishlist from PDP page");
		homeSteps.proceedToLogin();
		loginSteps.performLogin(mobileNumber, password);
		homeSteps.clearWishListMenuAndContinueShopping();
		homeSteps.logout();
		homeSteps.selectProductCategories("Sports Nutrition", "Proteins", "Whey Proteins");
		homeSteps.selectFirstProductFromSearchResult();
		pdpSteps.wishlistItem();
		loginSteps.performLogin(mobileNumber, password);
		pdpSteps.clickAddToCart();
		cartSteps.clickWishlistButton();
		wishListSteps.addToCart();
	}
	
	@Test(priority=2)
	public void testWishlistOnCart()
	{
		comnFunc.reportLogAndPrintInConsole("## Test Wishlist from Cart page");
		homeSteps.proceedToLogin();
		loginSteps.performLogin(mobileNumber, password);
		homeSteps.verifySuccessfullLogin();
		homeSteps.proceedToCart();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
		homeSteps.selectProductCategories("Sports Nutrition", "Proteins", "Whey Proteins");
		homeSteps.selectFirstProductFromSearchResult();
		pdpSteps.clickAddToCart();
		cartSteps.moveToWishlist();
	}
}