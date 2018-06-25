package com.healthkart.hkMobileAutomation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;

import io.appium.java_client.android.AndroidDriver;

public class HK_Cart_Page_Android extends CommonFunctions
{
	AndroidDriver<?> androidDriver;
	public HK_Cart_Page_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
	}

	private By proceedButton = By.id("com.healthkart.healthkart:id/checkoutT");
	private By startShoppingButton = By.id("com.healthkart.healthkart:id/btn_keep_shopping");
	private By removeItemButton = By.id("com.healthkart.healthkart:id/remove");
	private By applyCouponsAndOffersBy = By.id("com.healthkart.healthkart:id/applyCouponCard");
	private By couponDiscoutBy = By.id("com.healthkart.healthkart:id/applyCouponText");
	private By BXGYtextBy = By.id("com.healthkart.healthkart:id/bxgy_offer_apply");
	private By enterCouponCodeBy = By.id("com.healthkart.healthkart:id/couponE");
	private By applyCouponCodeBy = By.xpath("//android.widget.TextView[@text='APPLY COUPON CODE']");
	private By wishListButtonBy = By.id("com.healthkart.healthkart:id/wishlist_view");
	private By moveToWishlistBy = By.id("com.healthkart.healthkart:id/move_wishlist");
	
	public void clickProceedButton()
	{
		waitForElementToBeDisplayedBy(androidDriver, 30, proceedButton);
		click(androidDriver.findElement(proceedButton));
	}

	public void makeCartEmpty() 
	{
		boolean flag = false;
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		while(flag!=true)
		{
			try
			{
				androidDriver.findElement(startShoppingButton);
				flag=true;
			}
			catch(NoSuchElementException e)
			{
				click(androidDriver.findElement(removeItemButton));
//				staticWait(4);
				flag=false;
			}
		}
		androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
	}

	public void clickStartShopping() 
	{
		click(androidDriver.findElement(startShoppingButton));	
	}

	public void clickApplyCouponsAndOffers() 
	{
		clickBy(applyCouponsAndOffersBy);
	}

	public void selectOffer(String offerName) 
	{
		moveToElementAppium(androidDriver, offerName);
		String offerNameXpath = "//android.widget.TextView[@text='" + offerName + "']/following-sibling::android.widget.RelativeLayout";
		scrollAndSwipeByElementAndroid(androidDriver, By.xpath(offerNameXpath), "up");
		clickBy(By.xpath(offerNameXpath));
	}

	public String getCouponText() 
	{
		return getText(androidDriver.findElement(couponDiscoutBy));
	}

	public String getBXGYText() 
	{
		return getText(androidDriver.findElement(BXGYtextBy));
	}

	public void applyCoupon(String couponCode) 
	{
		sendKeys(androidDriver.findElement(enterCouponCodeBy), couponCode);
		clickBy(applyCouponCodeBy);
	}

	public void clickWishListPage() 
	{
		clickBy(wishListButtonBy);
		
	}

	public void clickMoveToWishlist() 
	{
		clickBy(moveToWishlistBy);
	}
		
}
