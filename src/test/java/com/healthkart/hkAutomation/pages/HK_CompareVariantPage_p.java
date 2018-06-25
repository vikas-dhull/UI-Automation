package com.healthkart.hkAutomation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_CompareVariantPage_p extends CommonFunctions{
	
	public WebDriver driver;
	
	public HK_CompareVariantPage_p(WebDriver driver) {		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Handle notify visitor[For PROD ENV only.]
	 */	
	/*@FindBy(xpath = "//a[@id='nv_js-modal-close-button']")
	private WebElement closeNotifyVisitorOverlay;
	
	public void clickCloseNotifyVisitorOverlay() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if(WebDriverUtil.isElementClickable(closeNotifyVisitorOverlay, driver,1))
	       	closeNotifyVisitorOverlay.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebDriverUtil.click(driver, closeNotifyVisitorOverlay, "closeNotifyVisitorOverlay");
	    if(WebDriverUtil.isElementClickable(closeNotifyVisitorOverlay, driver,20))
	       	closeNotifyVisitorOverlay.click();
	}*/
	
	/**
	 * Is Compare Variant page loaded successfully
	 */
	@FindBy(xpath="//h1[text()=' Compare Products']")
	private WebElement compareProductsText;
		
	public boolean verifyCompareVariantPageLoaded() {
		staticWait(10);
		/*if(GlobalVar.jenkinsEnvironment==null)        // Handle notify visitor overlay [For PROD ENV only.]
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);			
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);
		}*/
		
		WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);
		
		return ( WebDriverUtil.isElementPresent(compareProductsText, driver,20) &&
				driver.getCurrentUrl().contains("StoreVariantCompare.action?"));

	}
	
	/**
	 * BUY NOW from compare variant page
	 */
	@FindBy(xpath="//table[@class='cmpre-varnts-tbl']//input[@vendorid='291']")
	private WebElement buyNowFromCompare;
	
	public void clickBuyNowFromComparePage() {
		WebDriverUtil.click(driver, buyNowFromCompare, "buyNowFromCompare");
		
		/*if(WebDriverUtil.isElementPresent(buyNowFromCompare, driver) && WebDriverUtil.isElementClickable(buyNowFromCompare, driver))
			buyNowFromCompare.click();*/
	}

}
