package com.mb.webAutomation.business;

import org.openqa.selenium.WebDriver;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.mb.webAutomation.pages.MB_HomePage;
import com.mb.webAutomation.pages.MB_PdpPage;

public class MB_PdpSteps extends MB_PdpPage
{
	WebDriver driver;
	GenericDbActions dbActions = new GenericDbActions();
	
	public MB_PdpSteps(WebDriver driver)
	{
		super(driver);
	}

	public void addToCart() 
	{
		clickAddToCart();
	}
	

}
