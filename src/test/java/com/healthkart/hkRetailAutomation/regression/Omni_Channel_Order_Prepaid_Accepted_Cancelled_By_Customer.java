package com.healthkart.hkRetailAutomation.regression;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.OrderDataItems;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Omni_Channel_Order_Prepaid_Accepted_Cancelled_By_Customer extends ExtentReportingBaseUtil
{	
	@Test(priority=1,enabled=true)
	public void testPrepaidOrderPlacePOSAcceptedCancelledByUser() {
		comnFunc.reportLogAndPrintInConsole("## HK OMNI CHANNEL ORDER PLACE PREPAID, ACCEPTED AT STORE BUT CANCELLED BY USER..");
		posLoginSteps.performLoginWithMobilePassword(testdata.get("POS_App_Login_User"), testdata.get("POS_App_Login_Pswd"));
		Assert.assertTrue(posHomeSteps.verifyUserLoginPOS(), "User failed to Login");
		comnFunc.reportLogAndPrintInConsole("User successfully logged in with mobile and password");
		posHomeSteps.saveRetailStorePOS(testdata.get("POS_Store_Name"));		
		posHomeSteps.viewOmniChnlOrders();
		Assert.assertTrue(omniChnlOrdrSteps.verifyOmniChnlOrderPageLoaded(), "Failed to open HK Orders Screen..!!");
		comnFunc.reportLogAndPrintInConsole("HK Orders Screen loaded..");		
		Assert.assertTrue(omniChnlOrdrSteps.acceptOrder(), "Failed to mark order status as Accepted..!!");
		comnFunc.reportLogAndPrintInConsole("order status found as Accepted..");
		
		OrderCancel(GlobalVar.OmniChnlOrderId);
		driver.navigate().refresh();
		
		Assert.assertTrue(omniChnlOrdrSteps.verifySMActionAndStatus("CANCELLED_BY_USER"), "Failed to mark order status as Cancelled by User on retail store..!!");
		comnFunc.reportLogAndPrintInConsole("order status found as Cancelled By User on retial store..");
				
	}
	
	
	public void OrderCancel(String gatewayOrderId)
	{		
		RestAssured.baseURI ="https://stagapi.healthkart.com";
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type","application/json");
		request.header("userid","21105324");
		request.header("Authorization","8a268ffb56f361e88ac61333064051128e7ec1c4eff37c716c9fc62528c47a7137da1042ffab7a0cf9391755c4b91759");
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("comments","Ordered By Mistake :");
		requestParams.put("userId","21105324");
		requestParams.put("storeId","1");
		requestParams.put("platformId","1");
		requestParams.put("gatewayOrderId", gatewayOrderId);
		requestParams.put("paymentResolutionMode","2");
		requestParams.put("apiAccessKey","123");
		
		request.body(requestParams.toJSONString());
		Response response = request.post("/api/order/v3/cancelMyAccount");
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		WebDriverUtil.staticWait(5);
		
	}
	
}
