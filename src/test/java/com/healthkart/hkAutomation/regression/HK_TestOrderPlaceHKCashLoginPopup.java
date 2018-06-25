package com.healthkart.hkAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.property.PropertyHelper;
//import com.healthkart.hkAutomation.util.ExcelUtil;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_TestOrderPlaceHKCashLoginPopup extends ExtentReportingBaseUtil{

	private String gatewayOrderId=null;
	private int cartCount;
	
	/**
	 * Test Login with Facebook from login pop up, Order place with HK Cash.
	 */
	@Test(priority = 1)
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
		pdpB.addVariantToCart();
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		Assert.assertTrue(WebDriverUtil.verifyHKCashPresentForUser(testdata.get("Facebook_User_Mobile"), "full",1),"Sufficient HKCASH not present for this user cart..");
		Assert.assertTrue(cartB.performApplyHKCash(), "Failed to apply HK Cash on cart page..");
		test.log(LogStatus.PASS, "HK-Cash Applied successfully..");
		cartB.clickProceedToCheckout();		
		Assert.assertTrue(addB.verifyAddressPageLoaded(), "Fail to load select address page..");
		test.log(LogStatus.INFO, "Address page loaded..");
		addB.selectDeliveryAddress();
		Assert.assertTrue(pymntB.verifyPaymentPageLoaded(), "Fail to load Payment page..");
		test.log(LogStatus.INFO, "Payment page loaded..");
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
	
	/*
	 * Test Cancel order( depends on success of order placement.)
	 
	@Test(dependsOnMethods=( "testOrderPlaceHKCashLoginPopup"))
	public void cancelPrepaidHKCashOrder() {
		System.out.println("## CANCEL PREPAID ORDER (HK CASH FULL) Test starts..");
		test.log(LogStatus.INFO, "CANCEL PREPAID ORDER (HK CASH FULL) Test starts..");
		
		homeB.performLoginWithMobileOtp(testdata.get("Facebook_User_Mobile"));
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
	}*/
}
