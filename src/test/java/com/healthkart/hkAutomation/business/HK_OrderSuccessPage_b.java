package com.healthkart.hkAutomation.business;


import java.util.List;

import org.openqa.selenium.WebDriver;
import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.pages.HK_OrderSuccessPage_p;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.OrderDataItems;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_OrderSuccessPage_b extends HK_OrderSuccessPage_p{
	
	public GenericDbActions dbActionsObj = new GenericDbActions();

	public HK_OrderSuccessPage_b(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * GO - Continue Shopping.
	 */
	public void performContinueShopping() {
		clickContinueShopping();
	}
	
	/**
	 * Validate from database - order Id, order amount, opr and opr line items.
	 */
	public boolean performValidationForOrder(String orderId) {
		String gatewayOrderId = orderId;
		System.out.println("gateway order id placed : " + gatewayOrderId);
		boolean flag = false;
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
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
						
						WebDriverUtil.reportLogAndPrintInConsole("Gateway order id from order success screen: " + gatewayOrderId +
								" did not match with DB Gateway order id : " + orderDataItems.getGatewayOrderId());
					}
				}else {
					WebDriverUtil.reportLogAndPrintInConsole("One of the following order item found to be null :"
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
	
	public boolean performValidationForOrderTypeOmniChnl(String orderId) {
		String gatewayOrderId = orderId;
		boolean flag = false;
		List<OrderDataItems> orderDataItemsList = dbActionsObj.verifyOrderUsingGatewayID(gatewayOrderId);
		
		try {
			for(OrderDataItems orderDataItems : orderDataItemsList) {
				
				System.out.println("gateway order id : " + orderDataItems.getGatewayOrderId());
				System.out.println("order amount : " + orderDataItems.getAmount());				
				
				if(orderDataItems.getGatewayOrderId() != null ) {
					if(gatewayOrderId.equals(orderDataItems.getGatewayOrderId())){
						if(orderDataItems.getOrderType() == 1) {
							WebDriverUtil.reportLogAndPrintInConsole(" Order routed to warehouse..");
							flag = true;
						}else if(orderDataItems.getOrderType() ==7) {
							WebDriverUtil.reportLogAndPrintInConsole(" Order routed to Retail..");
						}
					}
				} else {
					WebDriverUtil.reportLogAndPrintInConsole("Following order item found to be null :"
							+ "Gateway order ID : " + gatewayOrderId );
				}
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();			
		}
		
		return flag;
		
	}
	
}