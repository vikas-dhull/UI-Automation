package com.healthkart.hkRetailAutomation.business;

import org.openqa.selenium.WebDriver;

import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.healthkart.hkRetailAutomation.pages.HK_Retail_Base_Order;

public class HK_Retail_Base_Order_Steps extends HK_Retail_Base_Order{

	public HK_Retail_Base_Order_Steps(WebDriver driver) {
		super(driver);
	}
	
	public String searchBaseOrderPOS(String gtwyOrdrId) {
		openBaseOrderSearchScreen();
		inputGtwyOrderId(gtwyOrdrId);
		clickSearchOrder();
		WebDriverUtil.scrollDownTillEnd(driver);
		return getSOGtwyOrdrId();
	}

}
