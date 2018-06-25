package com.healthkart.hkMobileAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Test_Guest_Login_Android extends ExtentReportingBaseUtil
{
	@Test(priority=1,enabled=true)
	public void testLoginWithMobilePasswordFromGuestPage()
	{	
		comnFunc.reportLogAndPrintInConsole("## LOGIN GUEST PAGE TEST STARTS WITH MOBILE AND PASSWORD..");
//		homeSteps.swipeNavigationPage();
		homeSteps.proceedToLogin();
		loginSteps.performLogin(mobileNumber,password);
		homeSteps.clickCartButton();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
		homeSteps.logout();
		WebDriverUtil.staticWait(3);
		homeSteps.selectProductCategories("Sports Nutrition", "Proteins", "Whey Proteins");
		homeSteps.selectFirstProductFromSearchResult();
		pdpSteps.clickAddToCart();
		cartSteps.proceedToCheckout();
		checkout.checkoutLoginWithMobileAndPassword(mobileNumber,password);	
		Assert.assertTrue(checkout.verifyLoggedInFromGuestPage());
	}
	
	@Test(priority=2,enabled=true)
	public void testLoginWithMobileOtpFromGuestPage()
	{
		comnFunc.reportLogAndPrintInConsole("## LOGIN GUEST PAGE TEST STARTS WITH MOBILE AND OTP..");
		homeSteps.proceedToLogin();
		loginSteps.performLoginWithMobileOtp(mobileNumber);
		homeSteps.clickCartButton();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
		homeSteps.logout();
		WebDriverUtil.staticWait(3);
		homeSteps.selectProductCategories("Sports Nutrition", "Proteins", "Whey Proteins");
		homeSteps.selectFirstProductFromSearchResult();
		pdpSteps.clickAddToCart();
		cartSteps.proceedToCheckout();
		checkout.checkoutLoginWithMobilOtp(mobileNumber);
		Assert.assertTrue(checkout.verifyLoggedInFromGuestPage());
	}
	
	@Test(priority=3,enabled=true)
	public void testLoginWithGoogleFromGuestPage()
	{
		comnFunc.reportLogAndPrintInConsole("## LOGIN GUEST PAGE TEST STARTS WITH GOOGLE ACCOUNTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLoginWithGoogle();
		homeSteps.clickCartButton();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
		homeSteps.logout();
		WebDriverUtil.staticWait(3);
		homeSteps.selectProductCategories("Sports Nutrition", "Proteins", "Whey Proteins");
		homeSteps.selectFirstProductFromSearchResult();
		pdpSteps.clickAddToCart();
		cartSteps.proceedToCheckout();
		loginSteps.performLoginWithGoogle();
		Assert.assertTrue(checkout.verifyLoggedInFromGuestPage());
		
	}

	@Test(priority=4,enabled=true)
	public void testLoginWithFacebookFromGuestPage() 
	{
		comnFunc.reportLogAndPrintInConsole("## LOGIN GUEST PAGE TEST STARTS WITH FACEBOOK ACCOUNTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLoginWithFacebook(fbUserName,fbPassword);
		homeSteps.clickCartButton();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
		homeSteps.logout();
		WebDriverUtil.staticWait(3);
		homeSteps.selectProductCategories("Sports Nutrition", "Proteins", "Whey Proteins");
		homeSteps.selectFirstProductFromSearchResult();
		pdpSteps.clickAddToCart();
		cartSteps.proceedToCheckout();
		loginSteps.performLoginWithFacebook(fbUserName,fbPassword);
		Assert.assertTrue(checkout.verifyLoggedInFromGuestPage());
	}

	@Test(priority=5, enabled=true)
	public void testLoginWithTruecaller()
	{
		comnFunc.reportLogAndPrintInConsole("## LOGIN GUEST PAGE TEST STARTS WITH TRUECALLER ACCOUNTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLoginWithTrueCaller();
		homeSteps.clickCartButton();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
		homeSteps.logout();
		WebDriverUtil.staticWait(3);
		homeSteps.selectProductCategories("Sports Nutrition", "Proteins", "Whey Proteins");
		homeSteps.selectFirstProductFromSearchResult();
		pdpSteps.clickAddToCart();
		cartSteps.proceedToCheckout();
		loginSteps.performLoginWithTrueCaller();
		WebDriverUtil.staticWait(1);
		//Assert.assertTrue(checkout.verifyLoggedInFromGuestPage());
	}
/*	@AfterMethod
	public void tearDown()
	{
		androidDriver.quit();
	}
*/
	//
}
