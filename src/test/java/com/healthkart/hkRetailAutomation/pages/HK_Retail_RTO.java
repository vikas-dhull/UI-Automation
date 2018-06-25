package com.healthkart.hkRetailAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Retail_RTO {
	
	public WebDriver driver;	
	
	public HK_Retail_RTO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='checkBoxIndexList[0]']")
	private WebElement posCheckForReturn;
	@FindBy(xpath="//select[contains(@name,'ReasonForReturn')]")
	private WebElement posSelectReasonForReturn;
	@FindBy(xpath="//select[contains(@name,'actionTaken')]")
	private WebElement posSelectCSActionOnReturn;
	@FindBy(xpath="//textarea[contains(@name,'customerComment')]")
	private WebElement posCmntForReturn;
	@FindBy(xpath="//select [contains(@name,'reversePickupOrder')]")
	private WebElement posSelectBookingTypeForReturn;
	@FindBy(xpath="//input[@type='radio' and @value=10]")
	private WebElement posHKMngdCurForReturn;
	@FindBy(xpath="//select[@class='selected-courier-name']")
	private WebElement posSelectCurNameForReturn;
	@FindBy(xpath="//input[@name='pickupDate']/following-sibling::a")
	private WebElement posReturnPickupTime;  
	@FindBy(xpath="//td[contains(@class,'day selected today')]")
	private WebElement posReturnPickupTimeInpt;
	@FindBy(xpath="//input[contains(@name,'createReversePickUp')]")
	private WebElement posReturnSave;
	@FindBy(xpath="//div[@class='alert messages']/ul/li[contains(text(),'Reverse Order Created')]")
	private WebElement posReverseOrderConfirmMsg;
	@FindBy(xpath="//li/a[text()='Warehouse']")
	private WebElement posWarehouseTab;
	
	public void clickCheckForReturn() {
		WebDriverUtil.click(driver, posCheckForReturn, "posCheckForReturn");
	}	
	public void selectReasonForReturn() {
		Select select = new Select(posSelectReasonForReturn);
		select.selectByValue("840");
	}
	
	public void selectCSActionOnReturn() {
		Select select = new Select(posSelectCSActionOnReturn);
		select.selectByValue("2110");
	}
	public void enterCmntForReturn() {
		posCmntForReturn.sendKeys("Test Order..");
	}	
	public void selectBookingTypeForReturn() {
		Select select = new Select(posSelectBookingTypeForReturn);
		select.selectByValue("10");
	}
	public void selectHKMngdCurForReturn() {
		WebDriverUtil.click(driver, posHKMngdCurForReturn, "posHKMngdCurForReturn");
	}
	public void selectCurNameForReturn() {
		Select select = new Select(posSelectCurNameForReturn);
		select.selectByValue("Delhivery");
	}
	public void selectReturnPickupTime() {
		WebDriverUtil.click(driver, posReturnPickupTime, "posReturnPickupTime");
		WebDriverUtil.click(driver, posReturnPickupTimeInpt, "posReturnPickupTimeInpt");
	}
	public void posSaveReturn() {
		WebDriverUtil.click(driver, posReturnSave, "posReturnSave");
	}
	public String getReverseOrderId() {
		return posReverseOrderConfirmMsg.getText().substring(24);
	}	
	public void posOpenWarehouseTab() {
		WebDriverUtil.click(driver, posWarehouseTab, "posWarehouseTab");
	}

}
