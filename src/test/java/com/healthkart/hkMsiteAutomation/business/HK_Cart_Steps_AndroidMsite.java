package com.healthkart.hkMsiteAutomation.business;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkMsiteAutomation.pages.HK_Cart_Page_AndroidMsite;
import io.appium.java_client.android.AndroidDriver;

public class HK_Cart_Steps_AndroidMsite {
	
	AndroidDriver<?> androidDriver;
	HK_Cart_Page_AndroidMsite cartPage;
	CommonFunctions comnFunc;
	
	
	public HK_Cart_Steps_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		cartPage = new HK_Cart_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
	}
	
	public boolean verifyCartPageLoaded()
	{
		return cartPage.verifyUserCartPageLoaded();
	}
	
	public void performMakeCartEmptyForUser()
	{
		cartPage.makeCartEmpty();
	}

	public void performAddItems()
	{
		cartPage.clickAddItems();
	}
	
	public void performProceedToCheckout()
	{
		cartPage.clickProceedToCheckout();
	}
	
	/**
	 * Apply Prompt Offer and verify offer applied successfully.
	 */
	public boolean applyPromptOfferAndVerify() 
	{
		boolean flag = false;
		cartPage.clickApplyPromptOffer();
		comnFunc.staticWait(5);
		if(cartPage.verifyPromptOfferApplied()) 
		{
			if(cartPage.verifyPromoDiscountOnCart())
			{
				flag=true;
			}
		}
		System.out.println("prompt offer applied flag : " + flag);
		return flag;		
	}
	
	/**
	 * Apply Coupon Offer through coupon code and verify coupon applied successfully.
	 */
	public boolean applyCouponCodeAndVerify(String couponCode) {
		boolean flag = false;
		cartPage.applyCouponCode(couponCode);
		if(cartPage.verifyCouponOfferApplied()) {
			if(cartPage.verifyPromoDiscountOnCart())
				flag=true;
		}
		return flag;		
	}
	
	/**
	 * Apply HK Cash on cart.
	 */
	public boolean performApplyHKCashAndVerify() {
		boolean flag = false;
		cartPage.clickHKCashRedeem();
		if(cartPage.verifyHKCashRedeemSuccess())
			flag = true;
		return flag;
	}
	
	/**
	 * Verify BxGy offer Applied on cart.
	 */
	
	public boolean verifyBxGyOfferApplied()
	{
		return cartPage.verifyBxGyOfferApplied();
	}

	public boolean verifyCartMergePageLoaded() 
	{
		return cartPage.verifyCartMerge();
	}
}
