package com.healthkart.hkMobileAutomation.business;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkMobileAutomation.pages.HK_Checkout_Page_Android;
import com.healthkart.hkMobileAutomation.pages.HK_Wishlist_Page_Android;

import io.appium.java_client.android.AndroidDriver;

public class HK_Wishlist_Steps_Android
{
	AndroidDriver<?> androidDriver;
	HK_Wishlist_Page_Android wishlistPage;
	String otp;
	GenericDbActions dbActions;
	CommonFunctions common;
	
	public HK_Wishlist_Steps_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		wishlistPage = new HK_Wishlist_Page_Android(androidDriver);
		dbActions = new GenericDbActions();
		common = new CommonFunctions();
	}

	public void addToCart() 
	{
		wishlistPage.clickAddtoCartButton();
	}
	
	
}
