package com.healthkart.hkRetailAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Retail_Base_Order {

	public WebDriver driver;	
	
	public HK_Retail_Base_Order(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Open Base Order Serach page.
	 */
	@FindBy(xpath = "//li/a[text()='Search BO']")
	private WebElement posBOSerachlink;
	
	public void openBaseOrderSearchScreen() {
		WebDriverUtil.scrollTillTop(driver);
		WebDriverUtil.click(driver, posBOSerachlink, "posBOSerachlink");
	}
	
	/**
	 * Verify Base Order Screen
	 */
	@FindBy(xpath = "//input[@name='gatewayOrderId']")
	private WebElement posBOInpt;
	@FindBy(xpath = "//input[@id='searchSubmit']")
	private WebElement posSearchOrders;		
	
	public void inputGtwyOrderId(String gtwyOrdrId) {
		posBOInpt.sendKeys(gtwyOrdrId);
	}	
	public void clickSearchOrder() {
		WebDriverUtil.click(driver, posSearchOrders, "posSearchOrders");
	}
	
	/**
	 * get SO gateway Order ID
	 */
	@FindBy(xpath="//td[contains(text(),'GatewayId')]/a")
	private WebElement posShippingOrderLink;
	
	public String getSOGtwyOrdrId() {
		return posShippingOrderLink.getText();
	}
}
