package com.healthkart.hkAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;
//import com.healthkart.hkAutomation.util.ExcelUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_Wishlist extends ExtentReportingBaseUtil{
	
	private String gatewayOrderId=null;
	private int cartCount;
	
	/**
	 * Test Login with Mobile number and password.
	 */
	@Test(priority = 1, enabled=true)
	public void cartPageWishlist() 
	{	
		System.out.println("ORDER PLACE PREPAID WITH NET BANKING Test starts..");
		test.log(LogStatus.INFO, "ORDER PLACE PREPAID WITH NET BANKING Test starts..");
		/*
		 * This is to make user's cart empty before order placement.
		 */
		homeB.performLoginWithMobilePassword("9552360289", "hk@12345");
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password to check user's cart empty..??");
		homeB.openMyWishlist();
		homeB.clearWishListMenuAndContinueShopping();
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
		homeB.visitBrandPage();
		Assert.assertTrue(brndB.verifyBrndPageLoaded(), "Fail to load Brand page..");
		test.log(LogStatus.INFO, "Category page loaded..");
		
		brndB.visitPDPPageForFirstVariant();
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded for online payment..");
		pdpB.addVariantToCart();
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
			
		cartB.moveToWishlist();
		
		cartB.clickBackToShopping();
		
		homeB.openMyWishlist();
		homeB.addToCartFromWishlist();
		homeB.goToCartFromWishlist();
		
		
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		System.out.println("cart page verified");
		test.log(LogStatus.INFO, "Product added to cart..");
		cartB.clickProceedToCheckout();
		Assert.assertTrue(addB.verifyAddressPageLoaded(), "Fail to load select address page..");
		test.log(LogStatus.INFO, "Address page loaded..");
		addB.selectDeliveryAddress();
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
	
	
	@Test(priority = 2, enabled=true)
	public void pdpWishlist() 
	{	
		System.out.println("ORDER PLACE PREPAID WITH NET BANKING Test starts..");
		test.log(LogStatus.INFO, "ORDER PLACE PREPAID WITH NET BANKING Test starts..");
		/*
		 * This is to make user's cart empty before order placement.
		 */
		homeB.performLoginWithMobilePassword("9552360289", "hk@12345");
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password to check user's cart empty..??");
		homeB.openMyWishlist();
		homeB.clearWishListMenuAndContinueShopping();
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
		homeB.visitBrandPage();
		Assert.assertTrue(brndB.verifyBrndPageLoaded(), "Fail to load Brand page..");
		test.log(LogStatus.INFO, "Category page loaded..");
		
		brndB.visitPDPPageForFirstVariant();
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded for online payment..");
		

		pdpB.wishlistItemFromIcon();
		homeB.openMyWishlist();
		homeB.addToCartFromWishlist();
		homeB.goToCartFromWishlist();
		
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		System.out.println("cart page verified");
		test.log(LogStatus.INFO, "Product added to cart..");
		cartB.clickProceedToCheckout();
		Assert.assertTrue(addB.verifyAddressPageLoaded(), "Fail to load select address page..");
		test.log(LogStatus.INFO, "Address page loaded..");
		addB.selectDeliveryAddress();
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
}