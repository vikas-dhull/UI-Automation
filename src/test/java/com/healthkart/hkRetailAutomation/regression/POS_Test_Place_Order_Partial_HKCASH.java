package com.healthkart.hkRetailAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class POS_Test_Place_Order_Partial_HKCASH extends ExtentReportingBaseUtil
{
	private String gatewayOrderId=null;
	
	@Test(priority=1,enabled=true)
	public void testOrderPlacePOSPartialHKCash() {
		comnFunc.reportLogAndPrintInConsole("## POS ORDER PLACE TEST WITH PARTIAL HK-CASH STARTS..");
		posLoginSteps.performLoginWithMobilePassword(testdata.get("POS_App_Login_User"), testdata.get("POS_App_Login_Pswd"));
		Assert.assertTrue(posHomeSteps.verifyUserLoginPOS(), "User failed to Login");
		comnFunc.reportLogAndPrintInConsole("User successfully logged in with mobile and password");
		WebDriverUtil.addHKCashForUser(testdata.get("POS_Customer_Mobile"), "partial", 1);
		posHomeSteps.saveRetailStorePOS(testdata.get("POS_Store_Name"));
		posHomeSteps.openRetailStorePOS();
		Assert.assertTrue(posSteps.verifyScreenPOS(), "Failed to open POS Screen..!!");
		comnFunc.reportLogAndPrintInConsole("POS order taking screen open successfully..");
		posSteps.inputCustomerDetails(testdata.get("POS_Customer_Mobile"));
		Assert.assertTrue(posSteps.inputScanBarcode(testdata.get("POS_NUT_ID"), testdata.get("POS_Store_Name")),"Item not added..");
		comnFunc.reportLogAndPrintInConsole("Item successfully added to cart..");
		Assert.assertTrue(posSteps.applyHkCashForCust("PARTIAL"),"HK-Cash is NOT applied for the order..!!");
		comnFunc.reportLogAndPrintInConsole("HK-Cash is successfully applied for the order..");
		Assert.assertTrue(posSteps.performPayments("Counter Cash"),"Payment was not successfull..!!");
		comnFunc.reportLogAndPrintInConsole("Payment done successfully..");		
		gatewayOrderId = posSteps.getGatewayOrderId();
		Assert.assertTrue(posSteps.performValidationForOrder(gatewayOrderId), "DB Validations failed for order placed..");
		comnFunc.reportLogAndPrintInConsole("Order success DB validations verified..");
		Assert.assertTrue(posSteps.verifyPOSOrderInSalesReport("SALE",gatewayOrderId), "Order validation failed for POS sales reports..!!");
		comnFunc.reportLogAndPrintInConsole("Order validation verified for POS sales reports.." );
	}
}
