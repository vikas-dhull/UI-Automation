package com.healthkart.hkMsiteAutomation.business;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkMsiteAutomation.pages.HK_Address_Page_AndroidMsite;
import io.appium.java_client.android.AndroidDriver;

public class HK_Address_Steps_AndroidMsite {
	
	AndroidDriver<?> androidDriver;
	HK_Address_Page_AndroidMsite addSteps;
	CommonFunctions comnFunc;
	
	public HK_Address_Steps_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		addSteps = new HK_Address_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
	}
	
	public boolean verifyAddressPageLoaded() {
		return addSteps.verifyAddressPageLoaded();
	}
	
	public void selectAddressForOrder()
	{
		addSteps.clickSelectAddress();
	}

}
