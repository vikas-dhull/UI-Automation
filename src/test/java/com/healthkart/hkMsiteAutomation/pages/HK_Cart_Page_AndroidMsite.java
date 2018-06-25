package com.healthkart.hkMsiteAutomation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidDriver;

public class HK_Cart_Page_AndroidMsite {
	
	AndroidDriver<?> androidDriver;	
	CommonFunctions comnFunc;
	
	private By myCartText = By.xpath("//div[contains(text(),'My Cart')]");
	private By proceedToCheckoutBtn = By.xpath("//a[text()='Proceed to Checkout']");
//	private By removeItemFromCartBtn = By.xpath("//div[contains(@class,'lineH1_8')]//a[@data-role='item-remove']");
	private By removeItemFromCartBtn = By.linkText("Remove");
	private By removeItemFromCartBtnNew = By.xpath("//div[contains(@class,'wishlist')]//a[@data-role='item-remove']");
	private By shoppingCartEmptyText = By.xpath("//div[contains(text(),'Your shopping cart is empty')]");
	private By addItems = By.xpath("//a[contains(text(),'Add items')]");
	private By applyOfferText = By.xpath("//div[@class='offertitle']");
	private By promptOfferApplyText = By.xpath("//span[contains(text(),'Extra 10% off on MuscleBlaze High Protein Gainer')]");
	private By OfferAppliedSuccessText = By.xpath("//div[text()='Offer applied successfully']");
	private By applyCouponFeild = By.xpath("//input[@name='couponCode']");
	private By applyCouponBtn = By.xpath("//a[contains(@class,'apply-coupon')]");
	private By applyCouponSuccessText = By.xpath("//div[contains(text(),'Coupon applied successfully')]");
	private By promoDiscount = By.xpath("//div[contains(@class,'cart-prdct-tbl')]//span[@data-role='promo-discount']");
	private By bXgYOfferVariant = By.xpath("//div[contains(@class,'bxgy-freebie')]");
	private By bXgYOfferApplied = By.xpath("//div[contains(@class,'freebie applied')]");
	private By hkCashRedeem = By.xpath("//div[contains(@class,'cart-prdct-tbl')]//span[text()='(Redeem)']");
	private By hkCashRedeemSuccess = By.xpath("//div[contains(@class,'cart-prdct-tbl')]//div[contains(@class,'cart-sum-tblinner')]//span[text()=' HK Cash applied']");
	private By cartMergeText = By.xpath("//p[text()='The items in your cart has been updated. Click on proceed to checkout']");
	
	private By closeNotifyVisitorOverlay = By.xpath("//a[contains(@id,'-close-button')]");
	
	public HK_Cart_Page_AndroidMsite(AndroidDriver<?> androidDriver) 
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
	 * Verify User Cart Page loaded successfully.
	 * @return
	 */
	public boolean verifyUserCartPageLoaded() 
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
		
		WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);
		
		return comnFunc.isElementPresentBy(androidDriver, myCartText);
	}
	
	/**
	 * remove item from user cart.
	 */
	public void clickItemRemoveFromCart()
	{
		comnFunc.click(androidDriver.findElement(removeItemFromCartBtn));
	}
	
	public void makeCartEmpty()
	{
		boolean flag = false;
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		while(flag!=true)
		{
			try
			{
				androidDriver.findElement(shoppingCartEmptyText);
				flag=true;
			}
			catch(NoSuchElementException e)
			{
				try
				{
				comnFunc.click(androidDriver.findElement(removeItemFromCartBtn));
				}
				catch(NoSuchElementException e1)
				{
					comnFunc.click(androidDriver.findElement(removeItemFromCartBtnNew));
				}
			}			
		}
		androidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void clickProceedToCheckout()
	{
		comnFunc.click(androidDriver.findElement(proceedToCheckoutBtn));
	}
	
	public void clickAddItems() 
	{
		comnFunc.click(androidDriver.findElement(addItems));
	}

	public void clickApplyPromptOffer() 
	{
		if(comnFunc.isElementPresentBy(androidDriver, promptOfferApplyText))
		{
			comnFunc.scrollToObject(androidDriver, androidDriver.findElement(applyCouponBtn));
			comnFunc.clickBy(promptOfferApplyText);
		}
		
	}

	public boolean verifyPromptOfferApplied()
	{
		return comnFunc.isElementPresentBy(androidDriver, OfferAppliedSuccessText);
	}

	public boolean verifyPromoDiscountOnCart() 
	{
		boolean flag = false;
		comnFunc.scrollTillTop(androidDriver);
		if(comnFunc.isElementPresentBy(androidDriver,promoDiscount)) {
			if(Integer.parseInt(androidDriver.findElement(promoDiscount).getText()) > 0)
				flag = true;
		}
		System.out.println("promo discount flag : " + flag);
		return flag;
	}
	
	public void applyCouponCode(String couponCode) {
		if(comnFunc.isElementPresentBy(androidDriver, applyCouponBtn))
		{
			comnFunc.scrollToObject(androidDriver, androidDriver.findElement(applyOfferText));
			comnFunc.sendKeysThroughJavaScript(androidDriver, androidDriver.findElement(applyCouponFeild), couponCode);
			comnFunc.clickBy(applyCouponBtn);
		}
	}
	
	public boolean verifyCouponOfferApplied()
	{
		return comnFunc.isElementPresentBy(androidDriver, applyCouponSuccessText);
	}
	
	public boolean verifyBxGyOfferApplied() {
		boolean  flag = false;
		if(comnFunc.isElementPresentBy(androidDriver,bXgYOfferVariant) && comnFunc.isElementPresentBy(androidDriver, bXgYOfferApplied) ) 
		{
			flag = true;
		}			
		System.out.println("verify cart page flag for BxGy offer : " + flag);
		return flag;		
	}

	public void clickHKCashRedeem() 
	{
		comnFunc.scrollDownTillEnd(androidDriver);
		comnFunc.clickBy(hkCashRedeem);
	}

	public boolean verifyHKCashRedeemSuccess() 
	{
		return comnFunc.isElementPresentBy(androidDriver, hkCashRedeemSuccess);
	}

	public boolean verifyCartMerge() 
	{
		return comnFunc.isElementPresentBy(androidDriver, cartMergeText);
	}

}
