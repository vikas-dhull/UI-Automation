package com.mb.MsiteAutomation.pages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.OrderDataItems;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

public class MBmsite_OrderSuccessPage extends CommonFunctions
{

	public AndroidDriver<WebElement> androidDriver;
	public GenericDbActions dbActions;
	
	public MBmsite_OrderSuccessPage(AndroidDriver<WebElement> androidDriver) 
	{
		this.androidDriver = androidDriver;
		PageFactory.initElements(androidDriver, this);
		dbActions = new GenericDbActions();
	}
	
	@FindBy(xpath="//div[contains(@class,'row msg-container visible-xs')]//p[contains(text(),'Your Order HKM')]")
	private WebElement orderIdField;
	
	public String getOrderId()
	{
		String orderSuccessText = orderIdField.getText();
		String orderId = StringUtils.substringBetween(orderSuccessText, "Order ", " has");
		return orderId;
	}
	
	public boolean performValidationForOrder(String orderId) {
		String gatewayOrderId = orderId;
		System.out.println("gateway order id placed : " + gatewayOrderId);
		boolean flag = false;
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		List<OrderDataItems> orderDataItemsList = dbActions.verifyOrderUsingGatewayID(gatewayOrderId);
		
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
						GlobalVar.test.log(LogStatus.INFO, "Gateway order id from order success screen: " + gatewayOrderId + 
								" did not match with DB Gateway order id : " + orderDataItems.getGatewayOrderId());
					}
				}else {
					System.out.println("One of the following order item found to be null :"
							+ "Gateway order ID : " + gatewayOrderId + " ; Order Amount : " 
							+ orderDataItems.getAmount() + " ; OPR : " + orderDataItems.getOprId()
							+ " ; OPR line items : " + orderDataItems.getOprLiId());
					
					GlobalVar.test.log(LogStatus.INFO, "One of the following order item found to be null :"
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
}
