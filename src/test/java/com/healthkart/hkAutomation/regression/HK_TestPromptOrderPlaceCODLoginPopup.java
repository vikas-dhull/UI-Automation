package com.healthkart.hkAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
//import com.healthkart.hkAutomation.util.ExcelUtil;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_TestPromptOrderPlaceCODLoginPopup extends ExtentReportingBaseUtil{
	
	private String gatewayOrderId=null;
	private int cartCount;
	
	/**
	 * Test Order placement COD with Prompt Offer.
	 */
	@Test(priority = 1)
	public void testPromptOrderPlaceCODWithLoginPopup() {	
		System.out.println("## ORDER PLACE COD(PROMPT OFFER) LOGIN FROM POPUP TEST STARTS..");
		test.log(LogStatus.INFO, "ORDER PLACE COD(PROMPT OFFER) LOGIN FROM POPUP TEST STARTS..");		
		/*
		 * This is to make user's cart empty before order placement.
		 */
		homeB.performLoginWithMobilePassword(testdata.get("Login_User_Mobile"), testdata.get("Login_User_Mobile_Password"));
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
		WebDriverUtil.staticWait(3);
		driver.get(GlobalVar.baseAppURL+testdata.get("Variant_URL")+testdata.get("Offer_VariantId"));
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load Prompt Offer PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded for Prompt offer..");		
		pdpB.addVariantToCart();
		System.out.println("verifying cart page");
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		Assert.assertTrue(cartB.applyPromptOfferAndVerify(), "Fail to Apply Prompt Offer on Cart page..");
		System.out.println("cart page verified");
		test.log(LogStatus.INFO, "Prompt offer applied and discount verified on cart page..");		
		cartB.clickProceedToCheckout();
		Assert.assertTrue(addB.verifyAddressPageLoaded(), "Fail to load select address page..");
		test.log(LogStatus.INFO, "Address page loaded..");
		addB.selectDeliveryAddress();
		Assert.assertTrue(pymntB.verifyPaymentPageLoaded(), "Fail to load Payment page..");
		test.log(LogStatus.INFO, "Payment page loaded..");
		pymntB.performCODPayment();
		Assert.assertTrue(ordrSuccB.verifyOrderSuccessPageLoaded(), "Fail to load Order Success page..");
		test.log(LogStatus.PASS, "Order success page loaded..");
		gatewayOrderId = ordrSuccB.getGatewayOrderId();
		Assert.assertTrue(ordrSuccB.performValidationForOrder(gatewayOrderId), "DB Validations failed for order placed..");
		test.log(LogStatus.PASS, "Order success DB validations verified..");
		ordrSuccB.performContinueShopping();
		Assert.assertTrue(homeB.verifyHomePageloaded(), "home page not loaded..");
		test.log(LogStatus.PASS, "Home Page loaded successfully..");
		
		System.out.println("## CANCEL COD ORDER(PROMPT OFFER) Test starts..");
		test.log(LogStatus.INFO, "CANCEL COD ORDER(PROMPT OFFER) Test starts..");
		homeB.openMyProfile();
		Assert.assertTrue(myProfB.verifyProfilePageLoaded(), "profile page not loaded suuccessfully..");
		test.log(LogStatus.INFO, "profile page loaded..");
		if(gatewayOrderId != null) {
			myProfB.performCancelCODOrder(gatewayOrderId);
			WebDriverUtil.staticWait(5);
		Assert.assertTrue(myProfB.verifyCancelOrderStatus(gatewayOrderId), "Order "+gatewayOrderId+" not found Cancelled for some reason, plz check..");
		test.log(LogStatus.PASS, "order has been cancelled successfully..!!");			
		}else {
			System.out.println("gateway order id found to be NULL..");
			test.log(LogStatus.FAIL, "order can't be cancelled, order id found NULL..!!");
		}
	}
	
	/*
	 * Test Cancel order( depends on success of order placement.)
	 
	@Test(dependsOnMethods=("testPromptOrderPlaceCODWithLoginPopup"))
	public void cancelCodPromptOrder() {
		System.out.println("## CANCEL COD ORDER(PROMPT OFFER) Test starts..");
		test.log(LogStatus.INFO, "CANCEL COD ORDER(PROMPT OFFER) Test starts..");
		
		homeB.performLoginWithMobilePassword(testdata.get("Login_User_Mobile"), testdata.get("Login_User_Mobile_Password"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password to cancel order..");
		
		homeB.openMyProfile();
		Assert.assertTrue(myProfB.verifyProfilePageLoaded(), "profile page not loaded suuccessfully..");
		test.log(LogStatus.INFO, "profile page loaded..");
		if(gatewayOrderId != null) {
			myProfB.performCancelCODOrder(gatewayOrderId);
			WebDriverUtil.staticWait(5);
		Assert.assertTrue(myProfB.verifyCancelOrderStatus(gatewayOrderId), "Order "+gatewayOrderId+" not found Cancelled for some reason, plz check..");
		test.log(LogStatus.PASS, "order has been cancelled successfully..!!");			
		}else {
			System.out.println("gateway order id found to be NULL..");
			test.log(LogStatus.FAIL, "order can't be cancelled, order id found NULL..!!");
		}
	}*/
}
