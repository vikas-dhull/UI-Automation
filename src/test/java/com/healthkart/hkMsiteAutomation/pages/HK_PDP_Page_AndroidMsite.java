package com.healthkart.hkMsiteAutomation.pages;

import org.openqa.selenium.By;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidDriver;

public class HK_PDP_Page_AndroidMsite {
	
	AndroidDriver<?> androidDriver;
	CommonFunctions comnFunc;
	
	private By buyNow = By.xpath("//div[contains(@class,'add-to-cart')]//a");	
	private By buyNowExpressCheckout = By.xpath("//div[contains(@class,'add-to-cart')]//a[contains(text(),'Buy Now')]");
	private By productReviwsText = By.xpath("//div[contains(@class,'review-inner-wrapper')]/h2[contains(text(),'Reviews')]");
	private By writeAreview = By.xpath("//div[contains(@class,'review-inner-wrapper')]//a[contains(text(),'Write a review')]");
	private By compareNowBtn = By.xpath("//a[contains(text(),'Compare now')]");
	
	private By closeNotifyVisitorOverlay = By.xpath("//a[@id='nv_js-modal-close-button']");
	private By wishlistIconButton = By.xpath("//div[contains(@class,'wishlist--pdp-icon')]");
	
	public HK_PDP_Page_AndroidMsite(AndroidDriver<?> androidDriver) 
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
	
	public boolean verify_Pdp_Loaded() 
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
		
		return comnFunc.isElementPresentBy(androidDriver, buyNow);
	}
	
	public void click_Buy_Now()
	{
		comnFunc.click(androidDriver.findElement(buyNow));
	}
	
	public void click_Buy_Now_ExpressCheckout()
	{
		comnFunc.click(androidDriver.findElement(buyNowExpressCheckout));
	}
	
	public void click_Write_A_review()
	{
		comnFunc.scrollToObject(androidDriver, androidDriver.findElement(productReviwsText));
		comnFunc.click(androidDriver.findElement(writeAreview));
	}

	public boolean isVariantCompareEligible() 
	{
		comnFunc.scrollToObjectWithMargin(androidDriver, androidDriver.findElement(compareNowBtn),300);
		return comnFunc.isElementPresentBy(androidDriver, compareNowBtn);
	}

	public void clickCompareBtn() 
	{
		comnFunc.clickBy(compareNowBtn);
	}

	public void clickWishlistImageButton() 
	{
		comnFunc.clickBy(wishlistIconButton);
	}

}
