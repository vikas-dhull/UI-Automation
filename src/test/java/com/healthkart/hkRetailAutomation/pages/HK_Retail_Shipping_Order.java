package com.healthkart.hkRetailAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Retail_Shipping_Order {
	
public WebDriver driver;	
	
	public HK_Retail_Shipping_Order(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * open Shipping order search screen
	 */
	@FindBy(xpath = "//li/a[contains(text(),'Search SO')]")
	private WebElement posSearchSO;
	
	public void openShippingOrderSearchScreen() {
		WebDriverUtil.click(driver, posSearchSO, "posSearchSO");
	}
	/**
	 * Verify Base Order Screen
	 */
	@FindBy(xpath = "//input[@name='shippingOrderGatewayId']")
	private WebElement posSOInpt;
	@FindBy(xpath = "//input[@name='searchShippingOrder']")
	private WebElement posSearchOrdersSO;
	@FindBy(xpath="//div/a[contains(text(),'Create Booking')]")
	private WebElement posCreateBookingLink;

	public void inputShippingOrderId(String gtwyOrdrId) {
		posSOInpt.sendKeys(gtwyOrdrId);
	}
	
	public void clickSearchOrder() {
		WebDriverUtil.click(driver, posSearchOrdersSO, "posSearchOrdersSO");
	}
	
	public void clickCreateBookingLink() {
		WebDriverUtil.click(driver, posCreateBookingLink, "posCreateRTOLink");
	}
}
