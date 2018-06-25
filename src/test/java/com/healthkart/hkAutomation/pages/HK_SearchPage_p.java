package com.healthkart.hkAutomation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_SearchPage_p {
	
	public WebDriver driver;
	
	public HK_SearchPage_p(WebDriver driver) {		
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
	 * Is Search page loaded successfully
	 */
	public boolean verifySrchPageLoaded() {
		boolean  flag = false;
		
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
		WebDriverUtil.staticWait(3);
		WebDriverUtil.waitForLoad(driver, 60);
		if(driver.getTitle().contains("HealthKart") && driver.getCurrentUrl().contains("Search.action"))
			flag = true;
		return flag;
	}	
	
	/**
	 * First product variant in search results.
	 */
	@FindBy(xpath="//div[@id='variantResultView']/div[1]/div[1]/a/img")
	private WebElement firstPVLink;
	
	public void scroolWindowToPvView() {		
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+firstPVLink.getLocation().y+")");
	}
	
	public void clickfirstPVLink() {
		
		WebDriverUtil.click(driver, firstPVLink, "firstPVLink");
		
		/*if(WebDriverUtil.isElementPresent(firstPVLink, driver))
			firstPVLink.click();*/
	}

}
