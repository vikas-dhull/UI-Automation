package com.healthkart.hkAutomation.pages;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_CategoryListPage_p extends CommonFunctions{
	
	public WebDriver driver;
	
	public HK_CategoryListPage_p(WebDriver driver) {		
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
	public boolean verifyCatPageLoaded() {
		boolean  flag = false;
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
		
		WebDriverUtil.staticWait(3);
		WebDriverUtil.waitForLoad(driver, 60);
		if(driver.getTitle().contains("Best Whey Protein Powder") && driver.getCurrentUrl().contains("navKey=CL-"))
			flag = true;
		return flag;
	}	
	
	/**
	 * Freebie available.
	 */
	@FindBy(xpath="//span[@class='bxgy-text'][text()='Freebie']/ancestor::div[2]/div/a/h2[@class='variant-title']")
	private WebElement availFreebieWithPV;
	
	public void scroolWindowToPvFreebieView() {		
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+availFreebieWithPV.getLocation().y+")");	
	}
	
	public void clickVariantWithFreebie(){
		
		if(WebDriverUtil.isElementPresent(availFreebieWithPV, driver,10)) {
			scroolWindowToPvFreebieView();
			WebDriverUtil.click(driver, availFreebieWithPV, "availFreebieWithPV");
		}
		
		/*if(WebDriverUtil.isElementPresent(availFreebieWithPV, driver)) {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+scrollToViewAvailFreebie.getLocation().y+")");
			Actions action = new Actions(driver);		 
            action.moveToElement(availFreebieWithPV).build().perform();
            if(WebDriverUtil.isElementClickable(availFreebieWithPV, driver))
            	availFreebieWithPV.click();
    	}*/
	}
	
	/**
	 * First product variant in search results.
	 */
	@FindBy(xpath="//div[@id='variantResultView']/div[1]")
	private WebElement firstPVLinkScrollView;
	
	@FindBy(xpath="//div/a/h2[contains(@class,'variant-title')]")
	private WebElement firstPVLink;
	
	@FindBy(xpath="//div[contains(@class,'variant-tile')][1]//a")
	private WebElement catPageHltr;
	
	public boolean verifyHltrPresent() {
		return WebDriverUtil.isElementPresent(catPageHltr, driver,3);
	}
	
	public void clickCatPageHltr() {
		if(WebDriverUtil.isElementClickable(catPageHltr, driver,10)) {
			WebDriverUtil.mouseHoverToElement(driver, catPageHltr);
			Actions action = new Actions(driver);		 
            action.click();
		}
		
	}
	
	public void scroolWindowToPvView() {		
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+(sortByVariants.getLocation().y)+")");	
	}
	
	public void clickfirstPVLink() {
		
//		if(WebDriverUtil.isElementPresent(firstPVLink, driver,15)) {
			scroolWindowToPvView();
			clickThroughJavaScript(driver, firstPVLink);
//		}
		
		/*if(WebDriverUtil.isElementPresent(firstPVLink, driver)) {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+(sortByVariants.getLocation().y)+")");
			if(WebDriverUtil.isElementClickable(firstPVLink, driver))
				firstPVLink.click();
			else
				System.out.println("element not found to be clicked.." + firstPVLink);
		}*/
			
	}
	
	/**
	 * Variant Tiles count on listing page
	 */
	@FindBys(@FindBy(xpath="//div[@class='variant-tile js-varnt-tile']"))
	private List<WebElement> variantTiles;
	
	public int getCountTotalVariantTiles() {
		int varTileCount = 0;
		if(WebDriverUtil.isElementPresent(firstPVLink, driver,10))
			varTileCount = variantTiles.size();
		return varTileCount;
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
		if(WebDriverUtil.isElementPresent(sortByVariants, driver,10)) {
			Select sortBy = new Select(sortByVariants);
			sortBy.selectByValue("priceLH");
		}
	}
	
	/**
	 * Sort by Price High to Low.
	 */
	
	public void selectSortByPriceHL() {
		if(WebDriverUtil.isElementPresent(sortByVariants, driver,10)) {
			Select sortBy = new Select(sortByVariants);
			sortBy.selectByValue("priceHL");
		}
	}	
	
	/**
	 * Variant's discount on listing page
	 */
	@FindBys(@FindBy(xpath="//div[@class='price-container-new price-container']/span[@class='vrnt-offer-new cont-rht']"))
	private List<WebElement> variantTilesForDiscount;
	
	public List<WebElement> getVariantTilesListForDiscount() {
		return variantTilesForPrice;
	}
	
	/**
	 * Sort by Discount Low to High
	 */	
	public void selectSortByDiscountLH() {
		if(WebDriverUtil.isElementPresent(sortByVariants, driver,10)) {
			Select sortBy = new Select(sortByVariants);
			sortBy.selectByValue("discLH");
		}
	}
	
	/**
	 * Sort by Discount High to Low
	 */	
	public void selectSortByDiscountHL() {
		if(WebDriverUtil.isElementPresent(sortByVariants, driver,10)) {
			Select sortBy = new Select(sortByVariants);
			sortBy.selectByValue("discHL");
		}
	}
		
	/**
	 * Variant's Ratings on listing page
	 
	@FindBys(@FindBy(xpath="//div[@class='variant-tile js-varnt-tile']"))
	private List<WebElement> variantTilesForRating;
	
	public List<WebElement> getVariantTilesListForRating() {
		return variantTilesForPrice;
	}*/
	
	/**
	 * Filter results by brand - ON.  
	 */
	@FindBy(xpath="//div[@id='accordion']/div[1]")
	private WebElement brandFilterScrollView;	
		
	public void scroolWindowToBrandFilter() {		
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+brandFilterScrollView.getLocation().y+")");	
	}
	
	@FindBy(xpath=".//ul[@id='brandFilterView']//li//a//span[text()='MuscleBlaze']")
	private WebElement checkBrandFilter;
	
	public void clickCheckboxBrandFilter() {
		
		WebDriverUtil.click(driver, checkBrandFilter, "checkBrandFilter");
		
		/*if(WebDriverUtil.isElementPresent(checkBrandFilter, driver)) {
			//Actions action = new Actions(driver);		 
            //action.moveToElement(checkBrandFilter).build().perform();
            if(WebDriverUtil.isElementClickable(checkBrandFilter, driver))
			checkBrandFilter.click();
		}*/
	}
	
	@FindBy(xpath="//span[@id='refresh-all']")
	private WebElement ResetAllFilters;
	
	public void clickResetAllFilters(){
		
		WebDriverUtil.click(driver, ResetAllFilters, "ResetAllFilters");
		
		/*if(WebDriverUtil.isElementPresent(ResetAllFilters, driver)) {
			Actions action = new Actions(driver);		 
            action.moveToElement(ResetAllFilters).build().perform();
            if(WebDriverUtil.isElementClickable(ResetAllFilters, driver))
            	ResetAllFilters.click();
		}*/
	}
	
	/**
	 * Grab the name of variants loaded on first page.
	 */
	@FindBys(@FindBy(xpath="//div[@class='variant-tile js-varnt-tile']//div[@class='']/a/h2"))
	private List<WebElement> catPageVariantNames;
	
	public List<WebElement> getcatPageVariantNames() {
		return catPageVariantNames;
	}
	
	/**
	 * Verify if 4th page loaded with variants.
	 */
	@FindBy(xpath="//div[contains(@class,'paginationBar') and text()='PAGE 4']/ancestor::div[1]/following-sibling::div[contains(@class,'variant-tile')]")
	private WebElement page4loadWithVariant;
	
	public boolean isPage4LoadedWithVariants(){		
		return (WebDriverUtil.isElementPresent(page4loadWithVariant, driver,2));		
	}
	
	/**
	 * Scroll till Load More button and hit load more results.
	 */
	@FindBy(xpath="//a[@data-role='load-more' and text()='Load more']")
	private WebElement LoadMoreBtn;
	
	public void scrollTillLoadMoreBtn() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+(LoadMoreBtn.getLocation().y-300)+")");
	}
	
	public void clickLoadMore(){		
		
		WebDriverUtil.click(driver, LoadMoreBtn, "LoadMoreBtn");
		
		/*if(WebDriverUtil.isElementPresent(LoadMoreBtn, driver)) {
			//Actions action = new Actions(driver);		 
            //action.moveToElement(LoadMoreBtn).build().perform();
            if(WebDriverUtil.isElementClickable(LoadMoreBtn, driver))
            	LoadMoreBtn.click();
		}*/
	}
	
	/**
	 * Verify if 5th page loaded with variants.
	 */
	@FindBy(xpath="//div[contains(@class,'paginationBar') and text()='PAGE 5']/ancestor::div[1]/following-sibling::div[contains(@class,'variant-tile')]")
	private WebElement page5loadWithVariant;
	
	public boolean isPage5LoadedWithVariants(){	
		
		return WebDriverUtil.isElementPresent(page5loadWithVariant, driver,2);
		
		/*boolean flag = false;
		if(WebDriverUtil.isElementPresent(page5loadWithVariant, driver,30)) {
			if(WebDriverUtil.isElementClickable(page5loadWithVariant, driver,30)) {
				WebDriverUtil.staticWait(5);
				flag = true;
			}				
		}
		return flag;*/		
	}

}
