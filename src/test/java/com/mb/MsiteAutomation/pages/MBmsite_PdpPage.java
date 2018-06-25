package com.mb.MsiteAutomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.util.CommonFunctions;

import io.appium.java_client.android.AndroidDriver;

public class MBmsite_PdpPage extends CommonFunctions
{

	public AndroidDriver<WebElement> androidDriver;
	
	public MBmsite_PdpPage(AndroidDriver<WebElement> androidDriver) 
	{
		this.androidDriver = androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
		
	@FindBy(xpath="//input[contains(@class,'addToCart')]")
	private WebElement addToCartButton;
	
	public void clickAddToCart()
	{
		/*scrollToObject(driver, addToCartButton);
		addToCartButton.click();*/
		
		clickThroughJavaScript(androidDriver, addToCartButton);
	}
}
