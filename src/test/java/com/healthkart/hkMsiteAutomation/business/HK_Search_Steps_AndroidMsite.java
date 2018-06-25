package com.healthkart.hkMsiteAutomation.business;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkMsiteAutomation.pages.HK_Search_Page_AndroidMsite;
import io.appium.java_client.android.AndroidDriver;

public class HK_Search_Steps_AndroidMsite 
{
	AndroidDriver<?> androidDriver;
	HK_Search_Page_AndroidMsite srchPage;
	CommonFunctions comnFunc;
	
	
	public HK_Search_Steps_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		srchPage = new HK_Search_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
	}
	
	public boolean verifySrchPageLoaded()
	{
		return srchPage.verifySearchPage();
	}
	
	public void goToFirstVariantPage()
	{
		srchPage.chooseFirstVariant();
	}
}
