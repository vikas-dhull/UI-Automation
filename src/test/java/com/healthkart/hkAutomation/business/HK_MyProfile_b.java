package com.healthkart.hkAutomation.business;

import org.openqa.selenium.WebDriver;

import com.healthkart.hkAutomation.pages.HK_MyProfile_p;

public class HK_MyProfile_b extends HK_MyProfile_p {

	public HK_MyProfile_b(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Cancel Prepaid order from my Account.
	 */
	public void performCancelPreapaidOrder(String gatewayOrderId){
		clickMyOrders();
		clickCancelPrepaidOrder(gatewayOrderId);
		selectReasonForCancel();
		selectRefundTypeForCancel();
		clickSubmitCancelPrepaidOrder();		
	}
		
	/**
	 * Cancel COD order from my Account.
	 */
	public void performCancelCODOrder(String gatewayOrderId){
		clickMyOrders();
		clickCancelCODOrder(gatewayOrderId);
		selectReasonForCancel();
		clickSubmitCancelPrepaidOrder();		
	}
	
	/**
	 * Cancel Free-checkout order from my Account.
	 */
	public void performCancelFreeCheckoutOrder(String gatewayOrderId){
		clickMyOrders();
		clickCancelFreeCheckoutOrder(gatewayOrderId);
		selectReasonForCancel();
		clickSubmitCancelPrepaidOrder();		
	}

}
