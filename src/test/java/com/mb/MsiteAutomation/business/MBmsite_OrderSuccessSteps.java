package com.mb.MsiteAutomation.business;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.browser.BrowserFactory;
import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.mb.MsiteAutomation.pages.MBmsite_OrderSuccessPage;

import io.appium.java_client.android.AndroidDriver;

public class MBmsite_OrderSuccessSteps extends MBmsite_OrderSuccessPage
{
	AndroidDriver<WebElement> androidDriver;
	GenericDbActions dbActions = new GenericDbActions();
	MBmsite_HomeSteps homeStepsMb;
	MBmsite_MyAccountSteps accountStepsMb;
	String orderId;
	
	public MBmsite_OrderSuccessSteps(AndroidDriver<WebElement> androidDriver)
	{
		super(androidDriver);
		homeStepsMb = new MBmsite_HomeSteps(androidDriver);
		accountStepsMb = new MBmsite_MyAccountSteps(androidDriver);
	}

	public boolean verifyOrderId() 
	{
		orderId = getOrderId();
		return performValidationForOrder(orderId);
	}

	public void cancelOrderPlaced(String reason, boolean orderType) 
	{
		BrowserFactory.getWebDriver().navigate().to(GlobalVar.baseAppURL);
		homeStepsMb.clickLoggedInText();
		homeStepsMb.selectCheckYourProfile();
		accountStepsMb.selectSideTab("Orders");
		accountStepsMb.cancelOrder(reason, orderType);
	}

	public boolean verifyOrderCancelled() 
	{
		return BrowserFactory.getWebDriver().findElement(By.xpath("//*[contains(text(),'" + orderId +"')]//ancestor::div[@class='hk-collapse-content js-collapse']//div[contains(text(),'Order Status: Cancelled')]")).isDisplayed();
	}

}
