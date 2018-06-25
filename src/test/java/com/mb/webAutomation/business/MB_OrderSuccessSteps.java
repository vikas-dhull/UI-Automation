package com.mb.webAutomation.business;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.healthkart.hkAutomation.browser.BrowserFactory;
import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.mb.webAutomation.pages.MB_OrderSuccessPage;

public class MB_OrderSuccessSteps extends MB_OrderSuccessPage
{
	WebDriver driver;
	GenericDbActions dbActions = new GenericDbActions();
	MB_HomeSteps homeStepsMb;
	MB_MyAccountSteps accountStepsMb;
	String orderId;
	
	public MB_OrderSuccessSteps(WebDriver driver)
	{
		super(driver);
		homeStepsMb = new MB_HomeSteps(driver);
		accountStepsMb = new MB_MyAccountSteps(driver);
	}

	public boolean verifyOrderId() 
	{
		orderId = getOrderId();
		return performValidationForOrder(orderId);
	}

	public void cancelOrderPlaced(String reason) 
	{
		BrowserFactory.getWebDriver().navigate().to(GlobalVar.baseAppURL);
		homeStepsMb.clickLoggedInText();
		homeStepsMb.selectCheckYourProfile();
		accountStepsMb.selectSideTab("Orders");
		accountStepsMb.cancelOrder(reason);
	}

	public boolean verifyOrderCancelled() 
	{
		return BrowserFactory.getWebDriver().findElement(By.xpath("//*[contains(text(),'" + orderId +"')]//ancestor::div[@class='hk-collapse-content js-collapse']//div[contains(text(),'Order Status: Cancelled')]")).isDisplayed();
	}

}
