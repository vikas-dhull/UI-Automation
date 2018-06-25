package com.healthkart.hkMsiteAutomation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidDriver;

public class HK_Category_Page_AndroidMsite
{
	AndroidDriver<?> androidDriver;
	CommonFunctions comnFunc;
	
	private By wheyProteinsTab = By.xpath("//div[@class='leafNodeContainer']//a[text()='Whey Proteins']");
	private By sort = By.xpath("//span[text()='Sort']");
	private By sortByPriceLH = By.xpath("//li[contains(text(),'Price -- Low to High')]");
	private By filter = By.xpath("//span[@class='filter_text']");
	private By filterByBrand = By.xpath("//div[contains(text(),'Brand')]");
	private By filterByMuscleBlazeBrand = By.xpath("//span[@class='fltr-value' and contains(text(),'MuscleBlaze')]");
	private By applyFilter = By.xpath("//button[contains(text(),'Apply')]");
	private By variantTilesForPrice = By.xpath("//div[@class='variant-price-container']//div[@class='vt-price']");
	private By firstVariantTile = By.xpath("//a[contains(@class,'variant-tile')]");
	
	//private By closeNotifyVisitorOverlay = By.xpath("//a[@id='nv_js-modal-close-button']");
	private By LoadMoreBtn = By.xpath("//a[@data-role='load-more' and text()='Load more']");
	
	public HK_Category_Page_AndroidMsite(AndroidDriver<?> androidDriver) 
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
	
	public boolean verifyCategoryPage()
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
		
		comnFunc.waitForElementToBeDisplayedBy(androidDriver, 10, wheyProteinsTab);
		if(androidDriver.getCurrentUrl().contains("navKey=CL-"))
		{
			if(comnFunc.isElementPresentBy(androidDriver, wheyProteinsTab))
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
		comnFunc.clickBy(filterByBrand);
		comnFunc.clickBy(filterByMuscleBlazeBrand);
		comnFunc.clickBy(applyFilter);
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
	
	public int getVariantTilesCount() 
	{
		return (int) androidDriver.findElements(firstVariantTile).size();
	}

	public void scrollTillLoadMoreBtn() 
	{
		comnFunc.scrollToObjectWithMargin(androidDriver, androidDriver.findElement(LoadMoreBtn), 300);
	}

	public void clickLoadMore() 
	{
		comnFunc.clickBy(LoadMoreBtn);
	}
}
