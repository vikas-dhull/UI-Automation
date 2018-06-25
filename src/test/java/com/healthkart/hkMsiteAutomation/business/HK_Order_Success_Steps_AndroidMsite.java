package com.healthkart.hkMsiteAutomation.business;

import java.util.List;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.OrderDataItems;
import com.healthkart.hkMsiteAutomation.pages.HK_Order_Success_Page_AndroidMsite;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

public class HK_Order_Success_Steps_AndroidMsite 
{
	AndroidDriver<?> androidDriver;
	HK_Order_Success_Page_AndroidMsite orderSuccessPage;
	CommonFunctions comnFunc;
	GenericDbActions dbActionsObj = new GenericDbActions();
	
	public HK_Order_Success_Steps_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		orderSuccessPage = new HK_Order_Success_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
	}
	
	public boolean verifyOrderSuccessPageLoaded() 
	{
		return orderSuccessPage.verifyOrderSuccessPageLoaded();
	}
	
	public String getGatewayOrderIdPlaced() 
	{
		return orderSuccessPage.getGatewayOrderId();		
	}
	
	/**
	 * GO - Continue Shopping.
	 */
	public void performContinueShopping() {
		orderSuccessPage.clickContinueShopping();
	}
	
	/**
	 * Validate from database - order Id, order amount, opr and opr line items.
	 */
	public boolean performValidationForOrder(String orderId) {
		String gatewayOrderId = orderId;
		System.out.println("gateway order id placed : " + gatewayOrderId);
		boolean flag = false;
		
		comnFunc.staticWait(5);
		
		List<OrderDataItems> orderDataItemsList = dbActionsObj.verifyOrderUsingGatewayID(gatewayOrderId);
		
		try {
			for(OrderDataItems orderDataItems : orderDataItemsList) {
				
				System.out.println("gateway order id : " + orderDataItems.getGatewayOrderId());
				System.out.println("order amount : " + orderDataItems.getAmount());
				System.out.println("opr id : " + orderDataItems.getOprId());
				System.out.println("opr li id : " + orderDataItems.getOprLiId());
				
				if(orderDataItems.getGatewayOrderId() != null && orderDataItems.getAmount() != null && orderDataItems.getOprId() != null 
					&& orderDataItems.getOprLiId() != null) {
					if(gatewayOrderId.equals(orderDataItems.getGatewayOrderId())) {	
						flag = true;
						System.out.println("Gateway order ID : " + gatewayOrderId + " ; Order Amount : " 
											+ orderDataItems.getAmount() + " ; OPR : " + orderDataItems.getOprId()
											+ " ; OPR line items : " + orderDataItems.getOprLiId());
					}else {
						System.out.println("Gateway order id from order success screen: " + gatewayOrderId +
								" did not match with DB Gateway order id : " + orderDataItems.getGatewayOrderId());
						ExtentReportingBaseUtil.test.log(LogStatus.INFO, "Gateway order id from order success screen: " + gatewayOrderId + 
								" did not match with DB Gateway order id : " + orderDataItems.getGatewayOrderId());
					}
				}else {
					System.out.println("One of the following order item found to be null :"
							+ "Gateway order ID : " + gatewayOrderId + " ; Order Amount : " 
							+ orderDataItems.getAmount() + " ; OPR : " + orderDataItems.getOprId()
							+ " ; OPR line items : " + orderDataItems.getOprLiId());
					
					ExtentReportingBaseUtil.test.log(LogStatus.INFO, "One of the following order item found to be null :"
							+ "Gateway order ID : " + gatewayOrderId + " ; Order Amount : " 
							+ orderDataItems.getAmount() + " ; OPR : " + orderDataItems.getOprId()
							+ " ; OPR line items : " + orderDataItems.getOprLiId());
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();			
		}
		
		return flag;
		
	}

	public boolean verifyOverlayPopupPresent() 
	{
		return orderSuccessPage.verifyOverlayPopupPresentOnOrderSuccessPage();
	}

	public void closeOverlayPopup() {
		orderSuccessPage.clickCloseOverlayPopupOnOrderSuccessPage();
	}

}
