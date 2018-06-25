package com.healthkart.hkMobileAutomation.business;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkMobileAutomation.pages.HK_PDP_Page_Android;

import io.appium.java_client.android.AndroidDriver;

public class HK_PDP_Steps_Android
{
	AndroidDriver<?> androidDriver;
	HK_PDP_Page_Android pdpPage;
	String otp;
	GenericDbActions dbActions;
	
	public HK_PDP_Steps_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		pdpPage = new HK_PDP_Page_Android(androidDriver);
		dbActions = new GenericDbActions();
	}

	public void clickBuyNow() 
	{
		pdpPage.clickBuyNow();
	}
	
	public void clickAddToCart()
	{
		pdpPage.clickAddToCart();
	}

	public void selectCompareNow() 
	{
		pdpPage.scrollToCompareNow();
		pdpPage.clickCompareNow();
	}

	public void verifyComparePageIsDisplayed() 
	{
		pdpPage.verifyTwoBuyNowButtonsDisplayed();
	}

	public void addMBVariantToCartFromComparePage() 
	{
		pdpPage.clickBuyNowForMB();
	}

	public void writeAReview(String review, String description) 
	{
		pdpPage.writeAReview(review,description);
	}

	public void wishlistItem() 
	{
		pdpPage.clickWishlistButton();
	}
	
	
	
	
	
	

}
