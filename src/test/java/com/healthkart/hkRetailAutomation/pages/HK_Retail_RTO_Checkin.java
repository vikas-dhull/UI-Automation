package com.healthkart.hkRetailAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Retail_RTO_Checkin {
	
public WebDriver driver;	
	
	public HK_Retail_RTO_Checkin(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h3/a[text()='RPWarehouse Checkedin']")
	private WebElement posReturnCheckin;
	@FindBy(xpath="//input[@name='reversePickupId']")
	private WebElement posInptReversePickupId;
	@FindBy(xpath="//input[@name='search']")
	private WebElement posSearchReverseOrderId;
	@FindBy(xpath="//input[@name='rpLineItems[0].itemBarcode']")
	private WebElement posInptReturnBarcode;
	@FindBy(xpath="//select[@name='rpLineItems[0].warehouseReceivedCondition']")
	private WebElement posSelectReturnItemWHCndtn;
	@FindBy(xpath="//select[@name='rpLineItems[0].warehouseComment']")
	private WebElement posSelectReturnItemWHCmnt;
	@FindBy(xpath="//textarea[@name='rpLineItems[0].warehouseRemark']")
	private WebElement posReturnItemWHRemarks;
	@FindBy(xpath="//a[@class='save-rp']")
	private WebElement posSaveReturnItemCheckin;
	@FindBy(xpath="//span[contains(text(),'Close & Mark RP as CheckedIn')]")
	private WebElement posCloseMarkRpCheckIn;
	
	public void clickReturnWHCheckin() {
		WebDriverUtil.click(driver, posReturnCheckin, "posReturnCheckin");
	}
	public void inptReversePickupId(String rpId) {
		posInptReversePickupId.sendKeys(rpId);
	}
	public void clickSearchRPID() {
		WebDriverUtil.click(driver, posSearchReverseOrderId, "posSearchReverseOrderId");
	}
	public void inptReturnBarcode(String barcode) {
		posInptReturnBarcode.sendKeys(barcode);
	}
	public void selectWHCndtnForReturn() {
		Select select = new Select(posSelectReturnItemWHCndtn);
		select.selectByValue("900");
	}
	public void selectWHCmntForReturn() {
		Select select = new Select(posSelectReturnItemWHCmnt);
		select.selectByValue("10");
	}
	public void inptWHRmrkForReturn() {
		posReturnItemWHRemarks.sendKeys("Test Order ..");
	}
	public void clickSaveReturnWHCheckin() {
		WebDriverUtil.click(driver, posSaveReturnItemCheckin, "posSaveReturnItemCheckin");
	}
	public void clickCloseReturnWHCheckin() {
		WebDriverUtil.click(driver, posCloseMarkRpCheckIn, "posCloseMarkRpCheckIn");
	}
}
