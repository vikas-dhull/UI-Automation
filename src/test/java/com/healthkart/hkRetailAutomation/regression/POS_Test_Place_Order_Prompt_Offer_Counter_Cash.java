package com.healthkart.hkRetailAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class POS_Test_Place_Order_Prompt_Offer_Counter_Cash extends ExtentReportingBaseUtil
{
	private String gatewayOrderId=null;
	
	@Test(priority=1,enabled=true)
	public void testOrderPlacePOSPromptOfferCash() {
		comnFunc.reportLogAndPrintInConsole("## POS ORDER PLACE TEST APPLYING PROMPT OFFER STARTS..");
		posLoginSteps.performLoginWithMobilePassword(testdata.get("POS_App_Login_User"), testdata.get("POS_App_Login_Pswd"));
		Assert.assertTrue(posHomeSteps.verifyUserLoginPOS(), "User failed to Login");
		comnFunc.reportLogAndPrintInConsole("User successfully logged in with mobile and password");
		posHomeSteps.saveRetailStorePOS(testdata.get("POS_Store_Name"));
		posHomeSteps.openRetailStorePOS();
		Assert.assertTrue(posSteps.verifyScreenPOS(), "Failed to open POS Screen..!!");
		comnFunc.reportLogAndPrintInConsole("POS order taking screen open successfully..");
		posSteps.inputCustomerDetails(testdata.get("POS_Customer_Mobile"));
		Assert.assertTrue(posSteps.inputScanBarcode(testdata.get("POS_NUT_ID"), testdata.get("POS_Store_Name")),"Item not added..");
		comnFunc.reportLogAndPrintInConsole("Item successfully added to cart..");		
		Assert.assertTrue(posSteps.performApplyAndVerifyPromtOffer(), "NOT able to apply Prompt Offer on POS order..!!");
		comnFunc.reportLogAndPrintInConsole("Prompt Offer applied successfully for POS order..");
		Assert.assertTrue(posSteps.performPayments("Counter Cash"),"Payment was not successfull..!!");
		comnFunc.reportLogAndPrintInConsole("Payment done successfully..");		
		gatewayOrderId = posSteps.getGatewayOrderId();
		Assert.assertTrue(posSteps.performValidationForOrder(gatewayOrderId), "DB Validations failed for order placed..");
		comnFunc.reportLogAndPrintInConsole("Order success DB validations verified..");
		Assert.assertTrue(posSteps.verifyPOSOrderInSalesReport("SALE",gatewayOrderId), "Order validation failed for POS sales reports..!!");
		comnFunc.reportLogAndPrintInConsole("Order validation verified for POS sales reports.." );
	}

}
