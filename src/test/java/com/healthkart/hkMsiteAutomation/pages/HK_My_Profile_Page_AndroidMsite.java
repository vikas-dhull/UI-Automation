package com.healthkart.hkMsiteAutomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

public class HK_My_Profile_Page_AndroidMsite 
{
	AndroidDriver<?> androidDriver;
	CommonFunctions comnFunc;
	
	private By myProfileText = By.xpath("//h2[text()='Account']");
	private By myAcctNavDiv = By.xpath("//ul[@class='account-nav']");
	private By myOrders = By.xpath("//a[text()='Orders ']");
	private By myOrdersText = By.xpath("//h2[text()='Orders']");
	private By cancelOrderPopup = By.xpath("//div[contains(@class,'popup-content')]//label[text()='I want to cancel, because']");
	private By cancelReasonSelect = By.xpath("//div[contains(@class,'popup-content')]//select[@name='reason']");
	private By refundTypeSelect = By.xpath("//div[contains(@class,'popup-content')]//select[contains(@class,'refund-type')]");
	private By cancelOrder = By.xpath("//div[contains(@class,'popup-content')]//button[contains(text(),'Cancel Order')]");
	private By orderIdText = By.xpath("//span[contains(text(),'Order Id:')]");
	
	// private By closeNotifyVisitorOverlay = By.xpath("//a[@id='nv_js-modal-close-button']");
	private By closeNotifyVisitorOverlay = By.xpath("//a[contains(@id,'nv_js-box-close-button')][1]");
	
	public HK_My_Profile_Page_AndroidMsite(AndroidDriver<?> androidDriver) 
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
	
	public boolean verifyMyProfileLoaded()
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
		
		comnFunc.waitForElementToBeDisplayedBy(androidDriver, 30, myProfileText);
		return comnFunc.isElementPresentBy(androidDriver, myProfileText);
	}
	
	public void goToMyOrders()
	{
		comnFunc.scrollToObject(androidDriver, androidDriver.findElement(myAcctNavDiv));
		comnFunc.clickBy(myOrders);
	}
	
	public boolean verifyMyOrdersLoaded()
	{
		comnFunc.waitForElementToBeClicked(androidDriver, 20, orderIdText);		
		return comnFunc.isElementPresentBy(androidDriver, myOrdersText);
	}
	
	public void cancelCodOrder(String orderId)
	{
		By cancelCodOrder = By.xpath("//a[@data-action='cancel' and @data-orderid='"+orderId+"' and @data-refund='false']");
		comnFunc.clickBy(cancelCodOrder);
	}
	
	public void cancelPrepaidOrder(String orderId)
	{
		By cancelCodOrder = By.xpath("//a[@data-action='cancel' and @data-orderid='"+orderId+"' and @data-refund='true']");
		comnFunc.clickBy(cancelCodOrder);
	}
	
	public void cancelFreeCheckoutOrder(String orderId)
	{
		By cancelCodOrder = By.xpath("//a[@data-action='cancel' and @data-orderid='"+orderId+"' and @data-refund='false']");
		comnFunc.clickBy(cancelCodOrder);
	}
	
	public boolean verifyCancelOrderPopupLoaded()
	{
		comnFunc.waitForElementToBeDisplayedBy(androidDriver, 20, cancelOrderPopup);
		return comnFunc.isElementPresentBy(androidDriver, cancelOrderPopup);
	}
	
	public void chooseCancelReason()
	{
		Select chooseBy = new Select(androidDriver.findElement(cancelReasonSelect));
		chooseBy.selectByVisibleText("Ordered By Mistake");
	}
	
	public void chooseRefundType()
	{		
		Select chooseBy = new Select(androidDriver.findElement(refundTypeSelect));
		chooseBy.selectByVisibleText("Bank Transfer");
	}
	
	public void submitCancelOrder()
	{
		comnFunc.clickBy(cancelOrder);
	}
	
	public boolean verifyCancelOrderStatus(String gatewayOrderId) {
		
		boolean flag = false;
		comnFunc.staticWait(5);
		By cancelStatusByElement = By.xpath("//span[contains(text(),'"+gatewayOrderId+"')]/ancestor::div[3]//span[text()='Cancelled']");
		comnFunc.waitForElementToBeDisplayedBy(androidDriver, 90, cancelStatusByElement);
		if(comnFunc.isElementPresentBy(androidDriver,cancelStatusByElement )) 
		{
			flag = true;
			System.out.println("order status found cancelled..");
			GlobalVar.test.log(LogStatus.PASS, "order status found cancelled..");
		}		
		return flag;
	}
}
