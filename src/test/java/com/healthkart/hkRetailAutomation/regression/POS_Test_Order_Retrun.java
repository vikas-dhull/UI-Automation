package com.healthkart.hkRetailAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class POS_Test_Order_Retrun extends ExtentReportingBaseUtil
{
	private String gatewayOrderId=null;
	private String baseOrderId=null;
	private String barcode=null;
	
	@Test(priority=1,enabled=true)
	public void testOrderPlacePOSCashOnCounterAndReturn() {
		comnFunc.reportLogAndPrintInConsole("## POS ORDER PLACE TEST WITH COUNTER CASH STARTS..");
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
		barcode=posSteps.getBarcodeScannedForOrder();
		Assert.assertTrue(posSteps.performPayments("Counter Cash"),"Payment was not successfull..!!");
		comnFunc.reportLogAndPrintInConsole("Payment done successfully..");		
		gatewayOrderId = posSteps.getGatewayOrderId();
		baseOrderId=posSteps.getBaseOrderId();
		Assert.assertTrue(posSteps.performValidationForOrder(gatewayOrderId), "DB Validations failed for order placed..");
		comnFunc.reportLogAndPrintInConsole("Order success DB validations verified..");
		Assert.assertTrue(posSOSteps.searchShippingOrderPOS(posBOSteps.searchBaseOrderPOS(gatewayOrderId)),"Create Reverse Booking failed for order..!!");
		comnFunc.reportLogAndPrintInConsole("Reverse Booking screen opened successfully..");
		posRTOChknSteps.performReturnCheckin(barcode,posRTOSteps.createReverseOrderForShippingOrder());
		Assert.assertTrue(posRTOChknSteps.performDBValidationForBarcodeCheckin(barcode), "Reverse Booking barcode : " +barcode+ " is not found CHECKED-IN");
		comnFunc.reportLogAndPrintInConsole("Reverse Booking barcode : " +barcode+" is found as CHECKED-IN" );
		Assert.assertTrue(posSteps.verifyPOSOrderInSalesReport("RETURN",baseOrderId), "Order validation failed for POS sales reports..!!");
		comnFunc.reportLogAndPrintInConsole("Order validation verified for POS sales reports.." );
	}
	
	@Test(priority=2,enabled=true)
	public void testGenerateReturnPOSReportByDate() {
		comnFunc.reportLogAndPrintInConsole("## POS RETURN REPORT GENERATE BY DATE TEST STARTS..");
		posLoginSteps.performLoginWithMobilePassword(testdata.get("POS_App_Login_User"), testdata.get("POS_App_Login_Pswd"));
		Assert.assertTrue(posHomeSteps.verifyUserLoginPOS(), "User failed to Login");
		comnFunc.reportLogAndPrintInConsole("User successfully logged in with mobile and password");
		posHomeSteps.saveRetailStorePOS(testdata.get("POS_Store_Name"));
		Assert.assertTrue(posSteps.verifyPOSOrderInSalesReport("RETURN BY DATE",baseOrderId), "Order validation failed for POS sales reports..!!");
		comnFunc.reportLogAndPrintInConsole("Order validation verified for POS sales reports.." );
	}
}