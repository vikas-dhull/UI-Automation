package com.healthkart.hkMsiteAutomation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidDriver;

public class HK_Brands_Page_AndroidMsite 
{
	AndroidDriver<?> androidDriver;
	CommonFunctions comnFunc;
	
	private By MBText = By.xpath("//h1[text()='MuscleBlaze']");
	private By sort = By.xpath("//span[text()='Sort']");
	private By sortByPriceLH = By.xpath("//li[contains(text(),'Price -- Low to High')]");
	private By filter = By.xpath("//span[@class='filter_text']");
	private By filterByCategory = By.xpath("//div[contains(text(),'Categories')]");
	private By filterByWheyProteinCat = By.xpath("//span[contains(text(),'Whey Proteins')]");
	// private By applyFilter = By.xpath("//button[contains(text(),'Apply')]");
	private By variantTilesForPrice = By.xpath("//div[@class='variant-price-container']//div[@class='vt-price']");
	private By firstVariantTile = By.xpath("//a[contains(@class,'variant-tile')]//div[@class='vt-name']");
	
	//private By closeNotifyVisitorOverlay = By.xpath("//a[contains(@id,'-close-button')]");
	
	public HK_Brands_Page_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		comnFunc = new CommonFunctions();
	}
	
	/**
	 * Handle notify visitor[For PROD ENV only.]
	 */
	public void clickCloseNotifyVisitorOverlay() {
	    /*if(comnFunc.isElementPresentBy(androidDriver, closeNotifyVisitorOverlay))
	       	comnFunc.clickBy(closeNotifyVisitorOverlay);*/
		WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);
	}
	
	public boolean verifyBrandsPage()
	{
		boolean  flag = false;
		
		/*if(GlobalVar.jenkinsEnvironment==null)        // Handle notify visitor overlay [For PROD ENV only.]
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				//clickCloseNotifyVisitorOverlay();
				WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);			
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			//clickCloseNotifyVisitorOverlay();
			WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);
		}*/
		
		WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);
		
		comnFunc.waitForElementToBeDisplayedBy(androidDriver, 10, MBText);
		if(androidDriver.getCurrentUrl().contains("navKey=BR-"))
		{
			if(comnFunc.isElementPresentBy(androidDriver, MBText))
				flag = true;
		}
		return flag;
	}
	
	public void clickSort() 
	{
		comnFunc.clickBy(sort);
	}
	
	public void chooseSortByPriceLH()
	{
		comnFunc.clickBy(sortByPriceLH);
	}
	
	public void clickFilter() 
	{
		comnFunc.clickBy(filter);
	}
	
	public void chooseFilterByBrand()
	{
		comnFunc.clickBy(filter);
		comnFunc.clickBy(filterByCategory);
		comnFunc.clickBy(filterByWheyProteinCat);
	//	comnFunc.clickBy(applyFilter);
	}
	
	public void chooseFirstVariant()
	{
		comnFunc.clickBy(firstVariantTile);
	}
	
	@SuppressWarnings("unchecked")
	public List<WebElement> getVariantTilesListForPrice() 
	{
		return (List<WebElement>) androidDriver.findElements(variantTilesForPrice);
	}
}
