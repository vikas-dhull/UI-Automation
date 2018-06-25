package com.healthkart.hkRetailAutomation.business;

import org.openqa.selenium.WebDriver;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.healthkart.hkRetailAutomation.pages.HK_Retail_Shipping_Order;

public class HK_Retail_Shipping_Order_Steps extends HK_Retail_Shipping_Order{

	public HK_Retail_Shipping_Order_Steps(WebDriver driver) {
		super(driver);
	}
	
	public boolean searchShippingOrderPOS(String ShpOrdrId) {
		boolean flag=false;
		openShippingOrderSearchScreen();
		inputShippingOrderId(ShpOrdrId);
		clickSearchOrder();		
		//String hkWinHndl = driver.getWindowHandle();
		clickCreateBookingLink();
		WebDriverUtil.staticWait(3);		
		
		if(WebDriverUtil.waitForWindowCount(driver, 2)) {
		
			for (String allHandles : driver.getWindowHandles()) {
				driver.switchTo().window(allHandles);
				WebDriverUtil.common.reportLogAndPrintInConsole("current window title :" + driver.getTitle());
				if(driver.getTitle().contains("Create Update Booking")) {
					flag=true;
					WebDriverUtil.common.reportLogAndPrintInConsole("Switched to Create Update Booking Screen..");
				}
			}		
		}
		return flag;
	}

}