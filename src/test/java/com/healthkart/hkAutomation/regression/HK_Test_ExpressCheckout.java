package com.healthkart.hkAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;
//import com.healthkart.hkAutomation.util.ExcelUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_ExpressCheckout extends ExtentReportingBaseUtil{
	
	private String gatewayOrderId=null;
	private int cartCount;
	
	/**
	 * Test Login with Mobile number and password.
	 */
	@Test(priority = 1, enabled=true)
	public void bxgyChecksWithLoggedInUser() 
	{	
		System.out.println("ORDER PLACE PREPAID WITH NET BANKING Test starts..");
		test.log(LogStatus.INFO, "ORDER PLACE PREPAID WITH NET BANKING Test starts..");
		/*
		 * This is to make user's cart empty before order placement.
		 */
		homeB.performLoginWithMobilePassword("9552360289", "hk@12345");
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password to check user's cart empty..??");
		cartCount = homeB.getCartCountForUser();
		if(cartCount != 0) {
			test.log(LogStatus.INFO, "There are items in user's cart..");
			homeB.proceedToCart();
			Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
			test.log(LogStatus.INFO, "cart page loaded..");
			cartB.makeCartEmpty();
			cartB.clickBackToShopping();
			Assert.assertTrue(homeB.verifyHomePageloaded(), "home page not loaded..");
			test.log(LogStatus.INFO, "user cart is empty now..");
		}else {
			test.log(LogStatus.INFO, "User cart is found empty..");
		}
		WebDriverUtil.staticWait(5);		
		
		driver.get(GlobalVar.baseAppURL+testdata.get("Variant_URL")+testdata.get("BxGy_VariantId"));
		
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded for online payment..");
		pdpB.buyNowExpressCheckout();
		
		Assert.assertTrue(pymntB.verifyBXGYapplied());
		
		pymntB.applyCouponCodeAndVerify(testdata.get("Coupon_Code"));
		
		Assert.assertTrue(pymntB.verifyPaymentPageLoaded(), "Fail to load Payment page..");
		test.log(LogStatus.INFO, "Payment page loaded..");

		if(GlobalVar.jenkinsEnvironment==null)
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				pymntB.clickChooseCODPymnt();
				/*Assert.assertTrue(pymntB.verifyPlaceOrderButtonPresent(), "Place order button not present on payment page..");
				test.log(LogStatus.INFO, "Place order button present on payment page..");*/				
			}
			else {				
				pymntB.performCODPayment();
				/*if(ordrSuccB.verifyOverlayPopupPresent())
					ordrSuccB.clickCloseOverlayPopup();*/
				Assert.assertTrue(ordrSuccB.verifyOrderSuccessPageLoaded(), "Fail to load Order Success page..");
				test.log(LogStatus.PASS, "Order success page loaded..");		
				gatewayOrderId = ordrSuccB.getGatewayOrderId();
				Assert.assertTrue(ordrSuccB.performValidationForOrder(gatewayOrderId), "DB Validations failed for order placed..");
				test.log(LogStatus.PASS, "Order success DB validations verified..");
				
				ordrSuccB.performContinueShopping();
				Assert.assertTrue(homeB.verifyHomePageloaded(), "home page not loaded..");
				test.log(LogStatus.PASS, "Home Page loaded successfully..");
				if(driver.getCurrentUrl().contains("www.healthkart.com")) 
				{			
					System.out.println("Current environment for execution not supported, please try with QA environment..");
					test.log(LogStatus.INFO, "Environment for execution not supported, please try with QA environment..");
				}
				else
				{
					System.out.println("## CANCEL COD ORDER Test starts..");
					test.log(LogStatus.INFO, "CANCEL COD ORDER Test starts..");
					homeB.openMyProfile();
					Assert.assertTrue(myProfB.verifyProfilePageLoaded(), "profile page not loaded suuccessfully..");
					test.log(LogStatus.INFO, "profile page loaded..");
					if(gatewayOrderId != null) {
						myProfB.performCancelCODOrder(gatewayOrderId);
						WebDriverUtil.staticWait(5);
					Assert.assertTrue(myProfB.verifyCancelOrderStatus(gatewayOrderId), "Order "+gatewayOrderId+" not found Cancelled for some reason, plz check..");
					test.log(LogStatus.PASS, "order has been cancelled successfully..!!");			
					}
					else {
						System.out.println("gateway order id found to be NULL..");
						test.log(LogStatus.FAIL, "order can't be cancelled, order id found NULL..!!");
					}
				}
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			pymntB.clickChooseCODPymnt();
			/*Assert.assertTrue(pymntB.verifyPlaceOrderButtonPresent(), "Place order button not present on payment page..");
			test.log(LogStatus.INFO, "Place order button present on payment page..");*/
		}
		
		else if("qa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{	
			pymntB.performCODPayment();
			/*if(ordrSuccB.verifyOverlayPopupPresent())
				ordrSuccB.clickCloseOverlayPopup();*/
			Assert.assertTrue(ordrSuccB.verifyOrderSuccessPageLoaded(), "Fail to load Order Success page..");
			test.log(LogStatus.PASS, "Order success page loaded..");		
			gatewayOrderId = ordrSuccB.getGatewayOrderId();
			Assert.assertTrue(ordrSuccB.performValidationForOrder(gatewayOrderId), "DB Validations failed for order placed..");
			test.log(LogStatus.PASS, "Order success DB validations verified..");
			
			ordrSuccB.performContinueShopping();
			Assert.assertTrue(homeB.verifyHomePageloaded(), "home page not loaded..");
			test.log(LogStatus.PASS, "Home Page loaded successfully..");
			if(driver.getCurrentUrl().contains("www.healthkart.com")) 
			{			
				System.out.println("Current environment for execution not supported, please try with QA environment..");
				test.log(LogStatus.INFO, "Environment for execution not supported, please try with QA environment..");
			}
			else
			{
				System.out.println("## CANCEL COD ORDER Test starts..");
				test.log(LogStatus.INFO, "CANCEL COD ORDER Test starts..");				
				homeB.openMyProfile();
				Assert.assertTrue(myProfB.verifyProfilePageLoaded(), "profile page not loaded suuccessfully..");
				test.log(LogStatus.INFO, "profile page loaded..");
				if(gatewayOrderId != null) {
					myProfB.performCancelCODOrder(gatewayOrderId);
					WebDriverUtil.staticWait(5);
				Assert.assertTrue(myProfB.verifyCancelOrderStatus(gatewayOrderId), "Order "+gatewayOrderId+" not found Cancelled for some reason, plz check..");
				test.log(LogStatus.PASS, "order has been cancelled successfully..!!");			
				}
				else {
					System.out.println("gateway order id found to be NULL..");
					test.log(LogStatus.FAIL, "order can't be cancelled, order id found NULL..!!");
				}
			}
		}	
	}
	
	@Test(priority=2, enabled=true)
	public void testOrderPlaceHKCashLoginPopup() {	
		System.out.println("## ORDER PLACE WITH HK-CASH Test starts..");
		test.log(LogStatus.INFO, "ORDER PLACE WITH HK-CASH Test starts..");
		/*
		 * This is to make user's cart empty before order placement.
		 */
		homeB.performLoginWithMobileOtp(testdata.get("Facebook_User_Mobile"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with facebook account to check user's cart empty..??");
		cartCount = homeB.getCartCountForUser();
		if(cartCount != 0) {
			test.log(LogStatus.INFO, "There are items in user's cart..");
			homeB.proceedToCart();
			Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
			test.log(LogStatus.INFO, "cart page loaded..");
			cartB.makeCartEmpty();
			cartB.clickBackToShopping();
			Assert.assertTrue(homeB.verifyHomePageloaded(), "home page not loaded..");
			test.log(LogStatus.INFO, "user cart is empty now..");
		}else {
			System.out.println("User cart is found empty..");
			test.log(LogStatus.INFO, "User cart is found empty..");
		}	
		WebDriverUtil.staticWait(3);
		WebDriverUtil.addHKCashForUser(testdata.get("Facebook_User_Mobile"), "full",1);
		homeB.visitBrandPage();
		Assert.assertTrue(brndB.verifyBrndPageLoaded(), "Fail to load Brand page..");
		test.log(LogStatus.INFO, "Category page loaded..");
		brndB.selectSortByPriceLH();
		WebDriverUtil.staticWait(3);		
		Assert.assertTrue(brndB.verifySortByPriceLH(), "Fail to Sort results on Brands page..");
		test.log(LogStatus.INFO, "Sorted by Price low to high results loaded on Brands page..");
		brndB.visitPDPPageForFirstVariant();
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded for HK-CASH payment..");		
		if(GlobalVar.jenkinsEnvironment==null)
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) 
			{
				driver.get("https://www.healthkart.com/sv/SP-33716?&navKey=VRNT-"+testdata.get("HK_Cash_VariantId"));
				Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load HK Cash PDP page..");
				test.log(LogStatus.INFO, "Product details page loaded for HK-CASH payment..");
			}
		}
		else {
				if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment)){
					driver.get("https://www.healthkart.com/sv/SP-33716?&navKey=VRNT-"+testdata.get("HK_Cash_VariantId"));
					Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load HK Cash PDP page..");
					test.log(LogStatus.INFO, "Product details page loaded for HK-CASH payment..");
				}
		}		
		pdpB.buyNowExpressCheckout();
		

		pymntB.clickConfirmHKCashOrder();		
		/*WebDriverUtil.staticWait(5);		
		if(ordrSuccB.verifyOverlayPopupPresent())
			ordrSuccB.clickCloseOverlayPopup();*/
		
		Assert.assertTrue(ordrSuccB.verifyOrderSuccessPageLoaded(), "Fail to load Order Success page..");
		test.log(LogStatus.PASS, "Order success page loaded..");		
		gatewayOrderId = ordrSuccB.getGatewayOrderId();
		Assert.assertTrue(ordrSuccB.performValidationForOrder(gatewayOrderId), "DB Validations failed for order placed..");
		test.log(LogStatus.PASS, "Order success DB validations verified..");
		
		ordrSuccB.performContinueShopping();
		Assert.assertTrue(homeB.verifyHomePageloaded(), "home page not loaded..");
		test.log(LogStatus.PASS, "Home Page loaded successfully..");
		
		System.out.println("## CANCEL PREPAID ORDER (HK CASH FULL) Test starts..");
		test.log(LogStatus.INFO, "CANCEL PREPAID ORDER (HK CASH FULL) Test starts..");
		homeB.openMyProfile();
		if(gatewayOrderId != null) {
			myProfB.performCancelCODOrder(gatewayOrderId);
			WebDriverUtil.staticWait(5);
		Assert.assertTrue(myProfB.verifyCancelOrderStatus(gatewayOrderId), "Order "+gatewayOrderId+" not found Cancelled for some reason, plz check..");
		test.log(LogStatus.PASS, "order has been cancelled successfully..!!");
			
		}
		else {
			System.out.println("gateway order id found to be NULL..");
			test.log(LogStatus.FAIL, "order can't be cancelled, order id found NULL..!!");
		}
	}	
}