package com.healthkart.hkRetailAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.GlobalVar;

public class Omni_Channel_Order_Prepaid_Rejected extends ExtentReportingBaseUtil
{	
	@Test(priority=1,enabled=true)
	public void testPrepaidOrderPlacePOSRejected() {
		comnFunc.reportLogAndPrintInConsole("## HK OMNI CHANNEL ORDER PLACE PREPAID, REJECTED AT STORE..");
		posLoginSteps.performLoginWithMobilePassword(testdata.get("POS_App_Login_User"), testdata.get("POS_App_Login_Pswd"));
		Assert.assertTrue(posHomeSteps.verifyUserLoginPOS(), "User failed to Login");
		comnFunc.reportLogAndPrintInConsole("User successfully logged in with mobile and password");
		posHomeSteps.saveRetailStorePOS(testdata.get("POS_Store_Name"));		
		posHomeSteps.viewOmniChnlOrders();
		Assert.assertTrue(omniChnlOrdrSteps.verifyOmniChnlOrderPageLoaded(), "Failed to open HK Orders Screen..!!");
		comnFunc.reportLogAndPrintInConsole("HK Orders Screen loaded..");
		Assert.assertTrue(omniChnlOrdrSteps.rejectOrder(), "Failed to mark order status as Rejected..!!");
		comnFunc.reportLogAndPrintInConsole("order status found as Rejected..");		
	}
	
}
