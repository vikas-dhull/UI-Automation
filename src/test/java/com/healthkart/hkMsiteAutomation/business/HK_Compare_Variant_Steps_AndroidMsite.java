package com.healthkart.hkMsiteAutomation.business;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkMsiteAutomation.pages.HK_Compare_Variant_Page_AndroidMsite;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

public class HK_Compare_Variant_Steps_AndroidMsite 
{
	AndroidDriver<?> androidDriver;
	HK_Compare_Variant_Page_AndroidMsite cmprPage;
	CommonFunctions comnFunc;
	
	public HK_Compare_Variant_Steps_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		cmprPage = new HK_Compare_Variant_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
	}
	
	public boolean verifyComparePageLoaded()
	{
		return cmprPage.verifyCompareVariantPageLoaded();
	}

	public void performAddToCartFromCompareVariantPage() 
	{
		if(cmprPage.verifyCompareVariantPageLoaded()) 
		{
			cmprPage.click_Buy_Now();
		}
		else
		{
			System.out.println("Buy Now button not present on Compare page..");
			GlobalVar.test.log(LogStatus.FAIL, "Buy Now button not present on Compare page..");
		}
	}
}
