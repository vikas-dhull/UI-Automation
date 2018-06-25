package com.mb.webAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.util.CommonFunctions;

public class MB_PdpPage extends CommonFunctions
{

	public WebDriver driver;
	
	public MB_PdpPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath="//input[contains(@class,'addToCart')]")
	private WebElement addToCartButton;
	
	public void clickAddToCart()
	{
		/*scrollToObject(driver, addToCartButton);
		addToCartButton.click();*/
		
		clickThroughJavaScript(driver, addToCartButton);
	}
}
