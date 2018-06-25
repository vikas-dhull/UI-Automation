package com.healthkart.hkAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.property.PropertyHelper;
//import com.healthkart.hkAutomation.util.ExcelUtil;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_TestOrderPlacePartialHKCashCODLoginPopup extends ExtentReportingBaseUtil{

	private String gatewayOrderId=null;
	private int cartCount;
	
	/**
	 * Test Login with Facebook from login pop up, Order place with HK Cash.
	 */
	@Test(priority = 1)
	public void testOrderPlacePartialHKCashCODLoginPopup() {	
		System.out.println("## ORDER PLACE WITH PARTIAL HK-CASH Test starts..");
		test.log(LogStatus.INFO, "ORDER PLACE WITH PARTIAL HK-CASH Test starts..");
		/*
		 * This is to make user's cart empty before order placement.
		 */
		homeB.performLoginWithMobilePassword(testdata.get("Login_User_Mobile"), testdata.get("Login_User_Mobile_Password"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in to check user's cart empty..??");
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
		WebDriverUtil.addHKCashForUser(testdata.get("Login_User_Mobile"), "partial",1);		
		homeB.visitCategoryPage();
		Assert.assertTrue(catLsB.verifyCatPageLoaded(), "Fail to load Category page..");
		test.log(LogStatus.INFO, "Category page loaded..");
		//catLsB.handleCatPageHltr();
		//test.log(LogStatus.INFO, "halter handled on Category page..");
		catLsB.visitPDPPageForFirstVariant();
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		
		
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
		pdpB.addVariantToCart();
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		Assert.assertTrue(WebDriverUtil.verifyHKCashPresentForUser(testdata.get("Login_User_Mobile"), "partial",1),"Sufficient HKCASH not present for this user cart..");
		Assert.assertTrue(cartB.performApplyHKCash(), "Failed to apply HK Cash on cart page..");
		test.log(LogStatus.PASS, "HK-Cash Applied successfully..");
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
				Assert.assertTrue(pymntB.verifyCaptchaPresent(), "failed to load COD Captcha..");
				test.log(LogStatus.INFO, "Captcha verified on payment page..");				
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
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			pymntB.clickChooseCODPymnt();
			Assert.assertTrue(pymntB.verifyCaptchaPresent(), "failed to load COD Captcha..");
			test.log(LogStatus.INFO, "Captcha verified on payment page..");
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
	}	
	
	/*
	 * Test Cancel order( depends on success of order placement.)
	 
	@Test(dependsOnMethods=( "testOrderPlacePartialHKCashCODLoginPopup"))
	public void cancelPrepaidHKCashOrder() {
		
		if(driver.getCurrentUrl().contains("www.healthkart.com")) 
		{			
			System.out.println("Current environment for execution not supported, please try with QA environment..");
			test.log(LogStatus.INFO, "Environment for execution not supported, please try with QA environment..");
		}
		else
		{
		
			System.out.println("## CANCEL PREPAID ORDER (HK CASH FULL) Test starts..");
			test.log(LogStatus.INFO, "CANCEL PREPAID ORDER (HK CASH FULL) Test starts..");
			
			homeB.performLoginWithMobilePassword(testdata.get("Login_User_Mobile"), testdata.get("Login_User_Mobile_Password"));
			Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
			test.log(LogStatus.INFO, "User successfully logged in with facebook account to cancel order..");
			
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
	}*/
}
