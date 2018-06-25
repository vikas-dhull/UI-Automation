package com.mb.webAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.util.CommonFunctions;

public class MB_SelectAddressPage extends CommonFunctions
{

	public WebDriver driver;
	
	public MB_SelectAddressPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[contains(text(),'Select delivery address')]")
	private WebElement selectDeliveryAddressLabel;
	
	@FindBy(xpath="//a[text()='DELIVER TO THIS ADDRESS']")
	private WebElement savedAddress;
	
	public boolean isAddressPageDisplayed()
	{
		return selectDeliveryAddressLabel.isDisplayed();
	}
	
	public void selectAddress()
	{
		staticWait(4);
		scrollToObject(driver, savedAddress);
		click(savedAddress);
	}
}