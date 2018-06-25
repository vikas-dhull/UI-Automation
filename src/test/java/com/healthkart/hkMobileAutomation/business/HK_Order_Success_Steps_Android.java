package com.healthkart.hkMobileAutomation.business;

import java.util.List;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.OrderDataItems;
import com.healthkart.hkMobileAutomation.pages.HK_Account_Page_Android;
import com.healthkart.hkMobileAutomation.pages.HK_Order_Success_Page_Android;
import com.healthkart.hkMobileAutomation.pages.HK_Payment_Page_Android;

import io.appium.java_client.android.AndroidDriver;

public class HK_Order_Success_Steps_Android 
{
	AndroidDriver<?> androidDriver;
	HK_Order_Success_Page_Android orderPage;
	GenericDbActions dbActions;
	CommonFunctions common;
	
	
	public HK_Order_Success_Steps_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		orderPage = new HK_Order_Success_Page_Android(androidDriver);
		dbActions = new GenericDbActions();
		common = new CommonFunctions();
	}


	
	public boolean performValidationForOrder() {
		String gatewayOrderId = orderPage.getGatewayOrderId();
		System.out.println("gateway order id placed : " + gatewayOrderId);
		boolean flag = false;
		common.staticWait(10);
		List<OrderDataItems> orderDataItemsList = dbActions.verifyOrderUsingGatewayID(gatewayOrderId);
		
		try {
			for(OrderDataItems orderDataItems : orderDataItemsList) {
				
				System.out.println("gateway order id : " + orderDataItems.getGatewayOrderId());
				System.out.println("order amount : " + orderDataItems.getAmount());
				System.out.println("opr id : " + orderDataItems.getOprId());
				System.out.println("opr li id : " + orderDataItems.getOprLiId());
				
				if(orderDataItems.getGatewayOrderId() != null && orderDataItems.getAmount() != null && orderDataItems.getOprId() != null && orderDataItems.getOprLiId() != null) {
					if(gatewayOrderId.equals(orderDataItems.getGatewayOrderId())) {	
						flag = true;
						System.out.println("Gateway order ID : " + gatewayOrderId + " ; Order Amount : " 
											+ orderDataItems.getAmount() + " ; OPR : " + orderDataItems.getOprId()
											+ " ; OPR line items : " + orderDataItems.getOprLiId());
					}	
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();			
		}
		
		return flag;
		
	}



	public void cancelOrderViaViewOrder() 
	{
		orderPage.clickViewOrder();
		orderPage.clickCancelOrder();
		orderPage.selectCancelReason("Ordered By Mistake");
		orderPage.clickCancelOrderPopUp();
	}



	public boolean verifyOrderCancelled() 
	{
		boolean flag = false;
		try
		{
			flag = orderPage.cancelOrderStatus().equalsIgnoreCase("cancelled");
		}
		catch(Exception e)
		{
			flag = false;
			System.out.println("Cancelled was not displayed");
		}
		
		return flag;
	}



	public void cancelOrderViaViewOrder(String card) 
	{
		orderPage.clickViewOrder();
		orderPage.clickCancelOrder();
		orderPage.selectCancelReason("Ordered By Mistake");
		orderPage.selectRefundMethod("Bank Transfer");
		orderPage.clickCancelOrderPopUp();
	}
	



	

}
