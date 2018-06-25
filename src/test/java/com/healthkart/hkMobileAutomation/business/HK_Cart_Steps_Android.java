package com.healthkart.hkMobileAutomation.business;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkMobileAutomation.pages.HK_Cart_Page_Android;

import io.appium.java_client.android.AndroidDriver;

public class HK_Cart_Steps_Android
{
	AndroidDriver<?> androidDriver;
	HK_Cart_Page_Android cartPage;
	String otp;
	GenericDbActions dbActions;
	
	public HK_Cart_Steps_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		cartPage = new HK_Cart_Page_Android(androidDriver);
		dbActions = new GenericDbActions();
	}


	public void proceedToCheckout() 
	{
		cartPage.clickProceedButton();
	}


	public void makeCartEmpty() 
	{
		cartPage.makeCartEmpty();
	}


	public void clickStartShopping() 
	{
		cartPage.clickStartShopping();
	}


	public void applyPromptOfferAndVerify(String offerName) 
	{
		cartPage.selectOffer(offerName);
	}


	public void clickApplyCouponsAndOffers() 
	{
		cartPage.clickApplyCouponsAndOffers();
	}


	public boolean verifyCouponApplied() 
	{
		if(cartPage.getCouponText().contains("Applied"))
				return true;
		else
			return false;
	}


	public boolean verifyBXGYApplied() 
	{
		if(cartPage.getBXGYText().contains("Buy 1 Get 1"))
			return true;
		else
			return false;
	}


	public void applyCoupon(String couponCode) 
	{
		cartPage.applyCoupon(couponCode);
		
	}


	public void clickWishlistButton() 
	{
		cartPage.clickWishListPage();
	}


	public void moveToWishlist() 
	{
		cartPage.clickMoveToWishlist();
	}

}
