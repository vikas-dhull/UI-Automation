package com.healthkart.hkMsiteAutomation.pages;

import org.openqa.selenium.By;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidDriver;

public class HK_Address_Page_AndroidMsite {
	AndroidDriver<?> androidDriver;	
	CommonFunctions comnFunc;
	
	private By addNewAddressText = By.xpath("//a[contains(text(),'Add New Address')]");
	private By selectAddressBtn = By.xpath("//span[contains(@class,'btn-select-address')]");
	
	private By closeNotifyVisitorOverlay = By.xpath("//a[contains(@id,'-close-button')]");
	
	public HK_Address_Page_AndroidMsite(AndroidDriver<?> androidDriver) 
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
	
	/**
	 * Verify Address Page loaded successfully.
	 * @return
	 */
	public boolean verifyAddressPageLoaded() 
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
		
		return comnFunc.isElementPresentBy(androidDriver, addNewAddressText);
	}

	/**
	 * Select address for order.
	 */
	public void clickSelectAddress()
	{
		comnFunc.scrollToObject(androidDriver, androidDriver.findElement(addNewAddressText));
		comnFunc.click(androidDriver.findElement(selectAddressBtn));
	}
}
