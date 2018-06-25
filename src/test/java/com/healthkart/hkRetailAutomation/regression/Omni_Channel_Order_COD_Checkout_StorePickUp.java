package com.healthkart.hkRetailAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.GlobalVar;

public class Omni_Channel_Order_COD_Checkout_StorePickUp extends ExtentReportingBaseUtil
{
	private String gatewayOrderId=null;
	private long customerOrderId;
	
	@Test(priority=1,enabled=true)
	public void testCODOrderPlaceHKStorePickup() {
		comnFunc.reportLogAndPrintInConsole("## HK OMNI CHANNEL ORDER PLACE COD, ACCEPTED, STORE PICKUP AND CHECKOUT AT STORE..");
		posLoginSteps.performLoginWithMobilePassword(testdata.get("POS_App_Login_User"), testdata.get("POS_App_Login_Pswd"));
		Assert.assertTrue(posHomeSteps.verifyUserLoginPOS(), "User failed to Login");
		comnFunc.reportLogAndPrintInConsole("User successfully logged in with mobile and password");
		posHomeSteps.saveRetailStorePOS(testdata.get("POS_Store_Name"));		
		posHomeSteps.viewOmniChnlOrders();
		Assert.assertTrue(omniChnlOrdrSteps.verifyOmniChnlOrderPageLoaded(), "Failed to open HK Orders Screen..!!");
		comnFunc.reportLogAndPrintInConsole("HK Orders Screen loaded..");
		
		Assert.assertTrue(omniChnlOrdrSteps.acceptOrderAndMarkForStorePickup(), "Failed to mark order status as Customer Store Pickup..!!");
		comnFunc.reportLogAndPrintInConsole("order status found as Customer Store Pickup");
		comnFunc.staticWait(5);
		customerOrderId=omniChnlOrdrSteps.getCustomerOrderId();
		Assert.assertTrue(customerOrderId!=0, "Customer ID NOT Created on POS..!!");
		comnFunc.reportLogAndPrintInConsole("Customer ID Created Successfully.. "+ customerOrderId);
		Assert.assertTrue(omniChnlOrdrSteps.verifyOrderItemsAvailableAtStore(customerOrderId,testdata.get("POS_Store_Name")), "Order Items are not available at Store..!!");
		comnFunc.reportLogAndPrintInConsole("Order Items are available at Store..");
		omniChnlOrdrSteps.checkoutOmniChnlOrder();
		Assert.assertTrue(posSteps.verifyScreenPOS(), "Failed to open POS Screen..!!");
		comnFunc.reportLogAndPrintInConsole("POS order taking screen open successfully..");
		Assert.assertTrue(posSteps.verifyPOSScreenElements("COD"), "POS screen elements not verified..!!");
		comnFunc.reportLogAndPrintInConsole("POS screen elements verified successfully..");		
		Assert.assertTrue(posSteps.inputScanBarcode(customerOrderId,testdata.get("POS_Store_Name")),"Item not added..");
		comnFunc.reportLogAndPrintInConsole("Item successfully added to cart..");		
		/*Assert.assertTrue(posSteps.verifyFinalPaymentOrderAmount(),"Final Payment NOT verified successfully on POS..!!");
		comnFunc.reportLogAndPrintInConsole("Final Payment verified successfully on POS..");	*/	
		Assert.assertTrue(posSteps.performPayments("Counter Cash"),"Payment was not successfull..!!");
		comnFunc.reportLogAndPrintInConsole("Payment done successfully..");		
		gatewayOrderId = posSteps.getGatewayOrderId();
		Assert.assertTrue(posSteps.performValidationForOrder(gatewayOrderId), "DB Validations failed for order placed..");
		comnFunc.reportLogAndPrintInConsole("Order success DB validations verified..");
		
	}
}