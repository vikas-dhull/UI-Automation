package com.mb.MsiteAutomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.util.CommonFunctions;

import io.appium.java_client.android.AndroidDriver;

public class MBmsite_SelectAddressPage extends CommonFunctions
{

	public AndroidDriver<WebElement> androidDriver;
	
	public MBmsite_SelectAddressPage(AndroidDriver<WebElement> androidDriver) 
	{
		this.androidDriver = androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
	@FindBy(xpath="//*[contains(text(),'address')]")
	private WebElement selectDeliveryAddressLabel;
	
	@FindBy(xpath="//div[@class='selct-add-section']")
	private WebElement savedAddress;
	
	public boolean isAddressPageDisplayed()
	{
		String currentUrl = androidDriver.getCurrentUrl();
		return currentUrl.contains("Address");
//		return selectDeliveryAddressLabel.isDisplayed();
	}
	
	public void selectAddress()
	{
		staticWait(4);
		scrollToObject(androidDriver, savedAddress);
		click(savedAddress);
	}
}