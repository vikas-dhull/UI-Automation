package com.healthkart.hkMsiteAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_Order_Place_Full_HKCash_Login_Popup_AndroidMsite extends ExtentReportingBaseUtil
{
	private int cartCount;
	private String gatewayOrderId=null;
	
	@Test(priority=1,enabled=true)
	public void testOrderPlaceFullHKCashWithLoginPopup()
	{	
		System.out.println("## ORDER PLACE FULL HK-CASH WITH LOGIN POPUP Test starts..");
		test.log(LogStatus.INFO, "ORDER PLACE FULL HK-CASH WITH LOGIN POPUP Test starts..");
		/*
		 * This is to make user's cart empty before order placement.
		 */
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to open login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performLoginWithMobileOtp(testdata.get("Facebook_User_Msite_Mobile"));
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
		WebDriverUtil.addHKCashForUser(testdata.get("Facebook_User_Msite_Mobile"), "full",1);
		driver.get(GlobalVar.baseAppURL+testdata.get("Variant_URL")+testdata.get("Msite_Prepaid_Variant"));
		Assert.assertTrue(pdpStep.verifyPdpPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpStep.addToCart();
		Assert.assertTrue(cartStep.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		Assert.assertTrue(WebDriverUtil.verifyHKCashPresentForUser(testdata.get("Facebook_User_Msite_Mobile"), "full",1),"Sufficient HKCASH not present for this user cart..");
		Assert.assertTrue(cartStep.performApplyHKCashAndVerify(), "HK-CASH not applied..");
		test.log(LogStatus.INFO, "HK-CASH applied successfully..");
		cartStep.performProceedToCheckout();
		Assert.assertTrue(addSteps.verifyAddressPageLoaded(), "Fail to load select address page..");
		test.log(LogStatus.INFO, "select address page loaded successfully..");
		addSteps.selectAddressForOrder();
		Assert.assertTrue(pymntSteps.verifyPaymentPageLoaded(), "Fail to load Payment page..");
		test.log(LogStatus.INFO, "Payment page loaded successfully..");
		pymntSteps.performHKCashPayment();
		Assert.assertTrue(orderSuccessSteps.verifyOrderSuccessPageLoaded(), "Fail to load Payment page..");
		test.log(LogStatus.INFO, "Payment page loaded successfully..");
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
			System.out.println("## CANCEL FULL HK-CASH PREPAID ORDER Test starts..");
			test.log(LogStatus.INFO, "CANCEL FULL HK-CASH PREPAID ORDER Test starts..");
			homeStep.openProfilePage();
			Assert.assertTrue(profSteps.verifyProfilePageLoaded(), "profile page not loaded suuccessfully..");
			test.log(LogStatus.INFO, "profile page loaded..");
			if(gatewayOrderId != null) 
			{
				profSteps.performCancelFreeCheckoutOrder(gatewayOrderId);
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
	 
	@Test(dependsOnMethods=( "testOrderPlaceFullHKCashWithLoginPopup"))
	public void cancelPrepaidFullHKCashOrder() 
	{
		if(androidDriver.getCurrentUrl().contains("www.healthkart.com")) 
		{			
			System.out.println("Current environment for execution not supported, please try with QA environment..");
			test.log(LogStatus.FAIL, "Environment for execution not supported, please try with QA environment..");
		}			
		else
		{
			System.out.println("## CANCEL FULL HK-CASH PREPAID ORDER Test starts..");
			test.log(LogStatus.INFO, "CANCEL FULL HK-CASH PREPAID ORDER Test starts..");
			
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
				profSteps.performCancelFreeCheckoutOrder(gatewayOrderId);
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
