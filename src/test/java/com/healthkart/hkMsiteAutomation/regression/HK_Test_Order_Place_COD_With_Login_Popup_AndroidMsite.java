package com.healthkart.hkMsiteAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_Order_Place_COD_With_Login_Popup_AndroidMsite extends ExtentReportingBaseUtil
{
	
	private int cartCount;
	private String gatewayOrderId=null;
	
	@Test(priority=1,enabled=true)
	public void testOrderPlaceCODWithLoginPopup()
	{	
		System.out.println("## ORDER PLACE COD WITH LOGIN POPUP Test starts..");
		test.log(LogStatus.INFO, "ORDER PLACE COD WITH LOGIN POPUP Test starts..");
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
		homeStep.goToFirstPVWheyProteinPDP();
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
		
		if(GlobalVar.jenkinsEnvironment==null)
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				pymntSteps.clickChooseCODPymnt();
				/*Assert.assertTrue(pymntSteps.verifyPlaceOrderButtonCODPresent(), "Place order button not present on payment page..");
				test.log(LogStatus.INFO, "Place order button present on payment page..");*/			
			}
			else {				
				pymntSteps.performCodPayment();
			/*	if(orderSuccessSteps.verifyOverlayPopupPresent())
					orderSuccessSteps.closeOverlayPopup(); */
				
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
					test.log(LogStatus.INFO, "Environment for execution not supported, please try with QA environment..");
				}
				else
				{
					System.out.println("## CANCEL COD ORDER Test starts..");
					test.log(LogStatus.INFO, "CANCEL COD ORDER Test starts..");
					homeStep.openProfilePage();
					Assert.assertTrue(profSteps.verifyProfilePageLoaded(), "profile page not loaded suuccessfully..");
					test.log(LogStatus.INFO, "profile page loaded..");
					if(gatewayOrderId != null) 
					{
						profSteps.performCancelCODOrder(gatewayOrderId);
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
		}
			
			else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
			{
				pymntSteps.clickChooseCODPymnt();
				/*Assert.assertTrue(pymntSteps.verifyPlaceOrderButtonCODPresent(), "Place order button not present on payment page..");
				test.log(LogStatus.INFO, "Place order button present on payment page..");*/
			}
			
			else if("qa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
			{	
				pymntSteps.performCodPayment();
			/*	if(orderSuccessSteps.verifyOverlayPopupPresent())
					orderSuccessSteps.closeOverlayPopup(); */
				
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
					test.log(LogStatus.INFO, "Environment for execution not supported, please try with QA environment..");
				}
				else
				{
					System.out.println("## CANCEL COD ORDER Test starts..");
					test.log(LogStatus.INFO, "CANCEL COD ORDER Test starts..");
					homeStep.openProfilePage();
					Assert.assertTrue(profSteps.verifyProfilePageLoaded(), "profile page not loaded suuccessfully..");
					test.log(LogStatus.INFO, "profile page loaded..");
					if(gatewayOrderId != null) 
					{
						profSteps.performCancelCODOrder(gatewayOrderId);
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
	}
	
	/*
	 * Test Cancel order( depends on success of order placement.)
	 
	@Test(dependsOnMethods=( "testOrderPlaceCODWithLoginPopup"))
	public void cancelCodOrder() 
	{
		if(androidDriver.getCurrentUrl().contains("www.healthkart.com")) 
		{			
			System.out.println("Current environment for execution not supported, please try with QA environment..");
			test.log(LogStatus.INFO, "Environment for execution not supported, please try with QA environment..");
		}
		else
		{
			System.out.println("## CANCEL COD ORDER Test starts..");
			test.log(LogStatus.INFO, "CANCEL COD ORDER Test starts..");
			
			homeStep.proceedToLogin();
			Assert.assertTrue(loginSteps.verifyLoginPopupPresent(), "Failed to opem login popup");
			test.log(LogStatus.INFO, "Loging popup displayed..");
			loginSteps.performLoginWithMobilePassword(testdata.get("Login_User_Mobile")
					, testdata.get("Login_User_Mobile_Password"));
			Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
			test.log(LogStatus.INFO, "User successfully logged in with mobile and password..");
			homeStep.openProfilePage();
			Assert.assertTrue(profSteps.verifyProfilePageLoaded(), "profile page not loaded suuccessfully..");
			test.log(LogStatus.INFO, "profile page loaded..");
			if(gatewayOrderId != null) 
			{
				profSteps.performCancelCODOrder(gatewayOrderId);
				Assert.assertTrue(profSteps.verifyCancelOrderStatus(gatewayOrderId), "Order "+gatewayOrderId+" not found Cancelled for some reason, plz check..");
				test.log(LogStatus.PASS, "order has been cancelled successfully..!!");			
			}
			else 
			{
				System.out.println("gateway order id found to be NULL..");
				test.log(LogStatus.FAIL, "order can't be cancelled, order id found NULL..!!");
			}
		}
	}*/

}
