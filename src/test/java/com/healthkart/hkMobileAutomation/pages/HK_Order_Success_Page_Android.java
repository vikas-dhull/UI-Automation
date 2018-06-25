package com.healthkart.hkMobileAutomation.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;

import io.appium.java_client.android.AndroidDriver;

public class HK_Order_Success_Page_Android 
{
	AndroidDriver<?> androidDriver;
	CommonFunctions common;
	
	private static By laterRateHKButtonBy = By.id("com.healthkart.healthkart:id/later");
	private static By orderIdBy = By.id("com.healthkart.healthkart:id/orderId");
	private static By viewOrderBy = By.xpath("//android.widget.Button[@text='View Order']");
	private static By cancelSpinnerBy = By.id("com.healthkart.healthkart:id/cancel_spinner");
//	private static By cancelOrderBy = By.id("com.healthkart.healthkart:id/cancel_button");
	private static By cancelOrderBy = By.id("com.healthkart.healthkart:id/cancel_order_inside");
	private static By cancelOrderPopUpBy = By.id("com.healthkart.healthkart:id/cancel_button");
	private static By cancelOrderTextBy = By.xpath("//android.widget.TextView[@resource-id='com.healthkart.healthkart:id/line_item_status']");
	
	public HK_Order_Success_Page_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		common = new CommonFunctions();
	}

	public void clickLaterButtonOnRateHK()
	{
		common.click(androidDriver.findElement(laterRateHKButtonBy));
	}

	public String getGatewayOrderId() 
	{
		return common.getText(androidDriver.findElement(orderIdBy));
	}

	public void clickViewOrder() 
	{
//		CommonFunctions.moveToElementAppium(androidDriver, "View Order");
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		/*while(true)
		{
			try
			{
				List<?> elements = androidDriver.findElements(viewOrderBy);
				System.out.println(elements.size());
				System.out.println(elements);
				
				if(elements.size()!=0)
				{
					break;
				}
				Dimension size = androidDriver.manage().window().getSize();
				int starty = (int) (size.height * 0.90);
				int endy = (int) (size.height * 0.01);
				int startx = size.width / 2,endx = size.width / 2;
			  	System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);
			  	androidDriver.swipe(startx, starty, endx, endy, 3000);
			}
			catch(NoSuchElementException e)
			{
				
			}
			androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
			
		}*/
		common.scrollAndSwipeByElementAndroid(androidDriver, viewOrderBy, "up");
		common.click(androidDriver.findElement(viewOrderBy));
	}

	public void selectCancelReason(String reason) 
	{
		common.click(androidDriver.findElement(cancelSpinnerBy));
		WebElement reasonElement = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='" + reason + "']"));
		common.click(reasonElement);
	}

	public void clickCancelOrderPopUp() 
	{
		common.clickBy(cancelOrderPopUpBy);
	}

	public void clickCancelOrder() 
	{
		common.waitForElementToBeDisplayedBy(androidDriver, 90, cancelOrderBy);
		common.click(androidDriver.findElement(cancelOrderBy));
	}

	public String cancelOrderStatus() 
	{
		common.waitForElementToBeDisplayedBy(androidDriver, 90, cancelOrderTextBy);
		return common.getText(androidDriver.findElement(cancelOrderTextBy));
	}

	public void selectRefundMethod(String refundMethod) 
	{
		common.click(androidDriver.findElementByAndroidUIAutomator(".textContains(\"refund method\")"));
		String accessibilityId = ".textContains(\"" + refundMethod + "\")";
		common.click(androidDriver.findElementByAndroidUIAutomator(accessibilityId));
	}
	
	
}
