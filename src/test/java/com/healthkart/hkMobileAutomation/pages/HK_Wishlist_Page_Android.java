package com.healthkart.hkMobileAutomation.pages;

import org.openqa.selenium.By;

import com.healthkart.hkAutomation.util.CommonFunctions;

import io.appium.java_client.android.AndroidDriver;

public class HK_Wishlist_Page_Android extends CommonFunctions
{
	AndroidDriver<?> androidDriver;
	public HK_Wishlist_Page_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
	}
	
	private By addToCartBy = By.id("com.healthkart.healthkart:id/add_cart");
	private By goToCartButtonBy = By.xpath("//android.widget.TextView[contains(@text,'Go to Cart')]");
	
	public void clickAddtoCartButton() 
	{
		clickBy(addToCartBy);
	}

	public void clickGoToCart()
	{
		clickBy(goToCartButtonBy);
	}
}
