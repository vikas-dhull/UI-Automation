package com.healthkart.hkMsiteAutomation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidDriver;

public class HK_Order_Success_Page_AndroidMsite 
{
	AndroidDriver<?> androidDriver;	
	CommonFunctions comnFunc;
	
	private By orderSuccessText = By.xpath("//div[text()='Your order is placed successfully!']");
	private By gatewayOrderId = By.xpath("//p[contains(@class,'order-id')]");
	private By OrderTotalAmount = By.xpath("//div[@class='cart-summary-details']//span[@data-role='total-payable']");
	private By closeOverlayPopup = By.xpath("//div[@id='tnp_js-modal-overlay' and @style='visibility: visible;']/preceding-sibling::div[@id='tnp_js-modal']//a[@id='tnp_js-modal-close-button']");
	private By continueShopping = By.xpath("//div[contains(@class,'continue-shopping')]//a[contains(text(),'CONTINUE SHOPPING')]");

	private By closeNotifyVisitorOverlay = By.xpath("//a[@id='nv_js-modal-close-button']");
	private By inviteFrndNoThankYouBtn = By.xpath("//div[@class='thank-btn']");
	
	public HK_Order_Success_Page_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		comnFunc = new CommonFunctions();
	}
	
	/**
	 * Handle notify visitor[For PROD ENV only.]
	 */
	public void clickCloseNotifyVisitorOverlay() {
	    /*if(comnFunc.isElementPresentBy(androidDriver, closeNotifyVisitorOverlay))
	       	comnFunc.clickBy(closeNotifyVisitorOverlay);*/
		WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);
	}
	
	public boolean verifyOrderSuccessPageLoaded() 
	{
		/*if(GlobalVar.jenkinsEnvironment==null)        // Handle notify visitor overlay [For PROD ENV only.]
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				//clickCloseNotifyVisitorOverlay();
				WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);			
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			//clickCloseNotifyVisitorOverlay();
			WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);
		}*/
		
		WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);
		
		return comnFunc.isElementPresentBy(androidDriver, orderSuccessText);
	}
	
	public void clickContinueShopping() 
	{
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(comnFunc.isElementPresentBy(androidDriver, inviteFrndNoThankYouBtn))
			comnFunc.clickBy(inviteFrndNoThankYouBtn);
		androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		comnFunc.scrollToObject(androidDriver, androidDriver.findElement(continueShopping));
		comnFunc.clickBy(continueShopping);
	}

	public String getGatewayOrderId()
	{
		String orderId=null;
		int substringIndex= 0;
		if(comnFunc.isElementPresentBy(androidDriver, gatewayOrderId)) {
			orderId=androidDriver.findElement(gatewayOrderId).getAttribute("innerText");
			System.out.println("Gateway orderId element text : " + orderId);			
			
		       char[] array= orderId.toCharArray();
		       int arrayIndex = 0;
		       for(char c: array){		    	   
		           System.out.print(c);
		           if(c=='H') {
		        	   substringIndex=arrayIndex;
		        	   System.out.println(" ...Sub String Index captured: " + substringIndex);
		        	   break;
		           }
		           arrayIndex++;
		       }
		}
		System.out.println("final GatewayOrderId returned :"+ orderId.substring(substringIndex));
		return orderId.substring(substringIndex);		
	}
	
	public String getOrderAmount()
	{
		String orderAmnt=null;
		if(comnFunc.isElementPresentBy(androidDriver, OrderTotalAmount)) {
			orderAmnt=androidDriver.findElement(OrderTotalAmount).getAttribute("innerText");
			System.out.println(orderAmnt);
		}
		return orderAmnt;		
	}

	public boolean verifyOverlayPopupPresentOnOrderSuccessPage() {
		return comnFunc.isElementPresentBy(androidDriver, closeOverlayPopup);
	}

	public void clickCloseOverlayPopupOnOrderSuccessPage() {
		comnFunc.clickBy(closeOverlayPopup);
	}
}
