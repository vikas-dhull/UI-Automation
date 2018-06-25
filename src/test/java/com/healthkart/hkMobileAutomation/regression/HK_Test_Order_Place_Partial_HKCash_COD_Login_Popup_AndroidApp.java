package com.healthkart.hkMobileAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Test_Order_Place_Partial_HKCash_COD_Login_Popup_AndroidApp extends ExtentReportingBaseUtil
{
	
	@Test(priority=1,enabled=true)
	public void testOrderPlacePartialHKCashCODWithLoginPopup()
	{	
		comnFunc.reportLogAndPrintInConsole("## ORDER PLACE COD WITH PARTIAL HK-CASH TEST STARTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLogin(mobileNumber, password);
		homeSteps.verifySuccessfullLogin();
		homeSteps.proceedToCart();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
		homeSteps.selectProductCategories("Sports Nutrition", "Proteins", "Whey Proteins");
		homeSteps.selectFirstProductFromSearchResult();
		WebDriverUtil.addHKCashForUser(mobileNumber, "partial",1);
		pdpSteps.clickAddToCart();
		WebDriverUtil.staticWait(1);
		cartSteps.proceedToCheckout();
		WebDriverUtil.staticWait(1);
		checkout.selectAddress();
		checkout.proceedToPay();
		Assert.assertTrue(WebDriverUtil.verifyHKCashPresentForUser(mobileNumber, "partial",1),"Sufficient HKCASH not present for this user cart..");
		paymentSteps.useHKCash();
		paymentSteps.selectPaymentOption("CASH ON DELIVERY",testdata);
    	//paymentSteps.placeOrder();
		paymentSteps.handleRateHealthkart();
		WebDriverUtil.staticWait(3);
		Assert.assertTrue(orderSteps.performValidationForOrder());
		orderSteps.cancelOrderViaViewOrder();
		Assert.assertTrue(orderSteps.verifyOrderCancelled());
		
/*		Assert.assertTrue(pdpSteps.verifyPdpPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpSteps.addToCart();
		Assert.assertTrue(cartSteps.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		Assert.assertTrue(cartSteps.performApplyHKCashAndVerify(), "HK-CASH not applied..");
		test.log(LogStatus.INFO, "HK-CASH applied successfully..");
		cartSteps.performProceedToCheckout();
		Assert.assertTrue(addSteps.verifyAddressPageLoaded(), "Fail to load select address page..");
		test.log(LogStatus.INFO, "select address page loaded successfully..");
		addSteps.selectAddressForOrder();
		Assert.assertTrue(pymntSteps.verifyPaymentPageLoaded(), "Fail to load Payment page..");
		test.log(LogStatus.INFO, "Payment page loaded successfully..");
		pymntSteps.performCodPayment();
		Assert.assertTrue(orderSuccessSteps.verifyOrderSuccessPageLoaded(), "Fail to load Payment page..");
		test.log(LogStatus.INFO, "Payment page loaded successfully..");
		gatewayOrderId = orderSuccessSteps.getGatewayOrderIdPlaced();
		Assert.assertTrue(orderSuccessSteps.performValidationForOrder(gatewayOrderId), "DB Validations failed for order placed..");
		test.log(LogStatus.PASS, "Order success DB validations verified..");
	}
	
	
	 * Test Cancel order( depends on success of order placement.)
	 
	@Test(dependsOnMethods=( "testOrderPlacePartialHKCashCODWithLoginPopup"))
	public void cancelPartialHKCashCODOrder() 
	{
		if(androidDriver.getCurrentUrl().contains("www.healthkart.com")) 
		{			
			System.out.println("Current environment for execution not supported, please try with QA environment..");
			test.log(LogStatus.FAIL, "Environment for execution not supported, please try with QA environment..");
		}			
		else
		{
			System.out.println("## CANCEL PARTIAL HK-CASH COD ORDER Test starts..");
			test.log(LogStatus.INFO, "CANCEL PARTIAL HK-CASH COD ORDER Test starts..");
			
			homeSteps.proceedToLogin();
			Assert.assertTrue(loginSteps.verifyLoginPopupPresent(), "Failed to opem login popup");
			test.log(LogStatus.INFO, "Loging popup displayed..");
			loginSteps.performLoginWithMobilePassword(ExcelUtil.getMsiteMobileNumber(), ExcelUtil.getMsitePassword());
			Assert.assertTrue(homeSteps.verifySuccessfullLogin(),"User failed to Login");
			test.log(LogStatus.INFO, "User successfully logged in with mobile and password..");
			homeSteps.openProfilePage();
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
		}*/
	}	
}