package com.healthkart.hkMsiteAutomation.business;

import org.testng.Assert;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkMsiteAutomation.pages.HK_My_Profile_Page_AndroidMsite;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

public class HK_My_Profile_Steps_AndroidMsite 
{
	AndroidDriver<?> androidDriver;
	HK_My_Profile_Page_AndroidMsite profPage;
	CommonFunctions comnFunc;
	
	public HK_My_Profile_Steps_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		profPage = new HK_My_Profile_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
	}

	public boolean verifyProfilePageLoaded() 
	{
		return profPage.verifyMyProfileLoaded();
	}
	
	public boolean verifyMyOrdersLoaded() 
	{
		return profPage.verifyMyOrdersLoaded();
	}
	
	public boolean verifyCancelOrderPopupLoaded() 
	{
		return profPage.verifyCancelOrderPopupLoaded();
	}
	
	public boolean verifyCancelOrderStatus(String gatewayOrderId) 
	{
		Assert.assertTrue(profPage.verifyMyOrdersLoaded(), "My Orders not loaded suuccessfully after cancellation..");
		GlobalVar.test.log(LogStatus.INFO, "My Orders loaded after cancellation..");
		return profPage.verifyCancelOrderStatus(gatewayOrderId);
	}

	public void performCancelCODOrder(String gatewayOrderId) 
	{
		profPage.goToMyOrders();
		Assert.assertTrue(profPage.verifyMyOrdersLoaded(), "My Orders not loaded suuccessfully..");
		GlobalVar.test.log(LogStatus.INFO, "My Orders loaded..");
		profPage.cancelCodOrder(gatewayOrderId);
		Assert.assertTrue(profPage.verifyCancelOrderPopupLoaded(), "Cancel Order Popup not Loaded successfully..");
		GlobalVar.test.log(LogStatus.INFO, "Cancel Order Popup Loaded successfully..");
		profPage.chooseCancelReason();
		profPage.submitCancelOrder();
	}
	
	public void performCancelPrepaidOrder(String gatewayOrderId) 
	{
		profPage.goToMyOrders();
		Assert.assertTrue(profPage.verifyMyOrdersLoaded(), "My Orders not loaded suuccessfully..");
		GlobalVar.test.log(LogStatus.INFO, "My Orders loaded..");
		profPage.cancelPrepaidOrder(gatewayOrderId);
		Assert.assertTrue(profPage.verifyCancelOrderPopupLoaded(), "Cancel Order Popup not Loaded successfully..");
		GlobalVar.test.log(LogStatus.INFO, "Cancel Order Popup Loaded successfully..");
		profPage.chooseCancelReason();
		profPage.chooseRefundType();
		profPage.submitCancelOrder();
	}
	
	public void performCancelFreeCheckoutOrder(String gatewayOrderId) 
	{
		profPage.goToMyOrders();
		Assert.assertTrue(profPage.verifyMyOrdersLoaded(), "My Orders not loaded suuccessfully..");
		GlobalVar.test.log(LogStatus.INFO, "My Orders loaded..");
		profPage.cancelFreeCheckoutOrder(gatewayOrderId);
		Assert.assertTrue(profPage.verifyCancelOrderPopupLoaded(), "Cancel Order Popup not Loaded successfully..");
		GlobalVar.test.log(LogStatus.INFO, "Cancel Order Popup Loaded successfully..");
		profPage.chooseCancelReason();
		profPage.submitCancelOrder();
	}
}
