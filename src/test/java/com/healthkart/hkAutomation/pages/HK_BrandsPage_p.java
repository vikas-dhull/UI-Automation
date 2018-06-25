package com.healthkart.hkAutomation.pages;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.healthkart.hkAutomation.util.CommonFunctions;

public class HK_BrandsPage_p extends CommonFunctions{
	
public WebDriver driver;
	
	public HK_BrandsPage_p(WebDriver driver) {		
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
	 * Is category page loaded successfully
	 */
	public boolean verifyBrndPageLoaded() {
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
		WebDriverUtil.staticWait(5);
		WebDriverUtil.waitForLoad(driver, 60);
		return (driver.getTitle().contains("MuscleBlaze") && driver.getCurrentUrl().contains("navKey=BR-"));
	}	
	
	/**
	 * First product variant in search results.
	 */
	@FindBy(xpath="//div[@id='variantResultView']/div[1]")
	private WebElement firstPVLinkScrollView;
	@FindBy(xpath="//div/a/h2[contains(@class,'variant-title')]")
	private WebElement firstPVLink;
	
	public void scroolWindowToPvView() {		
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+firstPVLinkScrollView.getLocation().y+")");		
	}
	
	public void clickfirstPVLink() {	
		WebDriverUtil.staticWait(5);
		scroolWindowToPvView();
		scrollToObject(driver, firstPVLinkScrollView);
		WebDriverUtil.click(driver, firstPVLink, "firstPVLink");
		
		/*if(WebDriverUtil.isElementPresent(firstPVLink, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(firstPVLink).build().perform();
            if(WebDriverUtil.isElementClickable(firstPVLink, driver))
            	firstPVLink.click();
    	}*/
	}
	
	/**
	 * Variant's HK Price on listing page
	 */
	@FindBys(@FindBy(xpath="//div[@class='price-container-new price-container']/span[@class='vrnt-price']"))
	private List<WebElement> variantTilesForPrice;
	
	public List<WebElement> getVariantTilesListForPrice() {
		return variantTilesForPrice;
	}
	
	/**
	 * Sort by Variants.
	 */
	
	@FindBy(xpath="//select[@id='sortByVariants']")
	private WebElement sortByVariants;
	
	/**
	 * Sort by Price Low to High.
	 */
	
	public void selectSortByPriceLH() {
		if(WebDriverUtil.isElementPresent(sortByVariants, driver,20)) {
			Select sortBy = new Select(sortByVariants);
			sortBy.selectByValue("priceLH");
		}
	}
	
	/**
	 * Sort by Price High to Low.
	 */
	
	public void selectSortByPriceHL() {
		if(WebDriverUtil.isElementPresent(sortByVariants, driver,20)) {
			Select sortBy = new Select(sortByVariants);
			sortBy.selectByValue("priceHL");
		}
	}	

}
