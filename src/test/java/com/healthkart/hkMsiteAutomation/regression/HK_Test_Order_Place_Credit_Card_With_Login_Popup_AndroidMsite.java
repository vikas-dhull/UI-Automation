package com.healthkart.hkMsiteAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_Order_Place_Credit_Card_With_Login_Popup_AndroidMsite extends ExtentReportingBaseUtil
{
	private int cartCount;
	private String gatewayOrderId=null;
	
	@Test(priority=1,enabled=true)
	public void testOrderPlaceCreditCardWithLoginPopup()
	{	
		System.out.println("## ORDER PLACE CREDIT CARD WITH LOGIN POPUP Test starts..");
		test.log(LogStatus.INFO, "ORDER PLACE CREDIT CARD WITH LOGIN POPUP Test starts..");
		/*
		 * This is to make user's cart empty before order placement.
		 */
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performLoginWithMobilePassword(testdata.get("Login_User_Mobile_Msite")
				, testdata.get("Login_User_Mobile_Password_Msite"));
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password..");
		cartCount=homeStep.getCartCountForUser();
		if(cartCount != 0) {
			test.log(LogStatus.INFO, "There are items in user's cart..");
			homeStep.proceedToCart();
			Assert.assertTrue(cartStep.verifyCartPageLoaded(), "Fail to load Cart page..");
			test.log(LogStatus.INFO, "cart page loaded..");
			cartStep.performMakeCartEmptyForUser();
			cartStep.performAddItems();
			Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "home page not loaded..");
			test.log(LogStatus.INFO, "user cart is empty now..");
		}else {
			test.log(LogStatus.INFO, "User cart is found empty..");
		}
		driver.get(GlobalVar.baseAppURL+testdata.get("Variant_URL")+testdata.get("Msite_Prepaid_Variant"));		
		Assert.assertTrue(pdpStep.verifyPdpPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpStep.addToCart();
		Assert.assertTrue(cartStep.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		cartStep.performProceedToCheckout();
		Assert.assertTrue(addSteps.verifyAddressPageLoaded(), "Fail to load select address page..");
		test.log(LogStatus.INFO, "select address page loaded successfully..");
		addSteps.selectAddressForOrder();
		Assert.assertTrue(pymntSteps.verifyPaymentPageLoaded(), "Fail to load Payment page..");
		test.log(LogStatus.INFO, "Payment page loaded successfully..");
		pymntSteps.performCreditCardPayment(testdata.get("CC_Number"),testdata.get("CC_Expiry_Month")
				,testdata.get("CC_Expiry_Year"),testdata.get("CC_CVV"),testdata.get("CC_Name"));
		Assert.assertTrue(orderSuccessSteps.verifyOrderSuccessPageLoaded(), "Fail to load Order Success page..");
		test.log(LogStatus.INFO, "Order Success page loaded successfully..");
		gatewayOrderId = orderSuccessSteps.getGatewayOrderIdPlaced();
		Assert.assertTrue(orderSuccessSteps.performValidationForOrder(gatewayOrderId), "DB Validations failed for order placed..");
		test.log(LogStatus.PASS, "Order success DB validations verified..");
		
		orderSuccessSteps.performContinueShopping();
		Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "home page not loaded..");
		test.log(LogStatus.PASS, "Home Page loaded successfully..");
		if(androidDriver.getCurrentUrl().contains("www.healthkart.com")) 
		{			
			System.out.println("Current environment for execution not supported, please try with QA environment..");
			test.log(LogStatus.FAIL, "Environment for execution not supported, please try with QA environment..");
		}			
		else
		{
			System.out.println("## CANCEL CREDIT CARD PREPAID ORDER Test starts..");
			test.log(LogStatus.INFO, "CANCEL CREDIT CARD PREPAID ORDER Test starts..");
			homeStep.openProfilePage();
			Assert.assertTrue(profSteps.verifyProfilePageLoaded(), "profile page not loaded suuccessfully..");
			test.log(LogStatus.INFO, "profile page loaded..");
			if(gatewayOrderId != null) 
			{
				profSteps.performCancelPrepaidOrder(gatewayOrderId);
				Assert.assertTrue(profSteps.verifyCancelOrderStatus(gatewayOrderId), "Order "+gatewayOrderId+" not found Cancelled for some reason, plz check..");
				test.log(LogStatus.PASS, "order has been cancelled successfully..!!");			
			}
			else 
			{
				System.out.println("gateway order id found to be NULL..");
				test.log(LogStatus.FAIL, "order can't be cancelled, order id found NULL..!!");
			}
		}
	}
	
	/*
	 * Test Cancel order( depends on success of order placement.)
	 
	@Test(dependsOnMethods=( "testOrderPlaceCreditCardWithLoginPopup"))
	public void cancelPrepaidCCOrder() 
	{
		if(androidDriver.getCurrentUrl().contains("www.healthkart.com")) 
		{			
			System.out.println("Current environment for execution not supported, please try with QA environment..");
			test.log(LogStatus.FAIL, "Environment for execution not supported, please try with QA environment..");
		}			
		else
		{
			System.out.println("## CANCEL CREDIT CARD PREPAID ORDER Test starts..");
			test.log(LogStatus.INFO, "CANCEL CREDIT CARD PREPAID ORDER Test starts..");
			
			homeStep.proceedToLogin();
			Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
			test.log(LogStatus.INFO, "Loging popup displayed..");
			loginStep.performLoginWithMobilePassword(testdata.get("Login_User_Mobile_Msite")
					, testdata.get("Login_User_Mobile_Password_Msite"));
			Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
			test.log(LogStatus.INFO, "User successfully logged in with mobile and password..");
			homeStep.openProfilePage();
			Assert.assertTrue(profSteps.verifyProfilePageLoaded(), "profile page not loaded suuccessfully..");
			test.log(LogStatus.INFO, "profile page loaded..");
			if(gatewayOrderId != null) 
			{
				profSteps.performCancelPrepaidOrder(gatewayOrderId);
				Assert.assertTrue(profSteps.verifyCancelOrderStatus(gatewayOrderId), "Order "+gatewayOrderId+" not found Cancelled for some reason, plz check..");
				test.log(LogStatus.PASS, "order has been cancelled successfully..!!");			
			}
			else 
			{
				System.out.println("gateway order id found to be NULL..");
				test.log(LogStatus.FAIL, "order can't be cancelled, order id found NULL..!!");
			}
		}
	}	*/
}