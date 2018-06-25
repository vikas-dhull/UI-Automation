package com.healthkart.hkAutomation.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_PdpPage_p extends CommonFunctions{

	public WebDriver driver;
	
	public HK_PdpPage_p(WebDriver driver) {		
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
	 * Is PDP page loaded successfully
	 */
	@FindBy(xpath=".//*[@id='variant-page']//div[@class='hk-productcontent']/h1[@class='variant-name']")
	private WebElement variantName;
		
	public boolean verifyPDPPageLoaded() {
		
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

		return (WebDriverUtil.isElementPresent(variantName, driver,20) && driver.getCurrentUrl().contains("navKey=VRNT-"));
	}
	
	/**
	 * Is BxGy PDP page loaded successfully
	 */
	@FindBy(xpath="//div[@class='offers-data']//ul[contains(@class,'bxgy-head')]")
	private WebElement BxGyOffer;
		
	public boolean verifyBxGyPDPPageLoaded() {
		
		return (driver.getCurrentUrl().contains("navKey=VRNT-"));
		
		/*boolean  flag = false;
		if(driver.getCurrentUrl().contains("navKey=VRNT-") )
			flag = true;
		return flag;*/
	}
	
	/**
	 * Is Coupon enabled PDP page loaded successfully
	 */
	@FindBy(xpath="//div[@class='offers-data']//div[@class='offer-coupon']")
	private WebElement CouponOffer;
		
	public boolean verifyCouponOfferPDPPageLoaded() {
		
		return (driver.getCurrentUrl().contains("navKey=VRNT-"));
		
		/*boolean  flag = false;
		if(driver.getCurrentUrl().contains("navKey=VRNT-") )
			flag = true;
		return flag;*/
	}
	
	/**
	 * Is Prompt enabled PDP page loaded successfully
	 */
	@FindBy(xpath="//div[@class='offer-no-coupon']")
	private WebElement PromptOffer;
		
	public boolean verifyPromptOfferPDPPageLoaded() {
		
		return (driver.getCurrentUrl().contains("navKey=VRNT-"));
		
		/*boolean  flag = false;
		if(driver.getCurrentUrl().contains("navKey=VRNT-"))
			flag = true;
		return flag;*/
	}	
	
	/**
	 * return variant name
	 */
	public String getVariantName() {
		String  PvName = null;
		if(WebDriverUtil.isElementPresent(variantName, driver,20))
			PvName = variantName.getText();
		return PvName;
	}
	
	/**
	 * Add to cart.
	 */
	@FindBy(xpath="//div[@class='buy-now-container']/input[1]")
	private WebElement addToCart;
	
	public void scrollToBuyNow() {
		if(WebDriverUtil.isElementPresent(addToCart, driver,20))
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+(addToCart.getLocation().y-300)+")");
	}
	
	public void addToCart() {
		
		scrollToBuyNow();
		WebDriverUtil.staticWait(2);
		WebDriverUtil.click(driver, addToCart, "addToCart");
		
		/*if(WebDriverUtil.isElementPresent(buyNow, driver))
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+(buyNow.getLocation().y-100)+")");
			Actions action = new Actions(driver);		 
			action.moveToElement(buyNow).build().perform();
        	if(WebDriverUtil.isElementClickable(buyNow, driver))
        		buyNow.click();*/
	
	}
	
	@FindBy(xpath="//div[@class='buy-now-container']/input[2]")
	private WebElement buyNow;
	
	public void buyNow() {
		
		scrollToBuyNow();
		WebDriverUtil.staticWait(2);
		WebDriverUtil.click(driver, buyNow, "buyNow");
		
		/*if(WebDriverUtil.isElementPresent(buyNow, driver))
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+(buyNow.getLocation().y-100)+")");
			Actions action = new Actions(driver);		 
			action.moveToElement(buyNow).build().perform();
        	if(WebDriverUtil.isElementClickable(buyNow, driver))
        		buyNow.click();*/
	
	}
	
	/**
	 * Freebie Present
	 */
	@FindBy(xpath=".//*[contains(text(),'Freebie')]")
	private WebElement freebieAvailableWithPV;
	
	public boolean isFreebieAvailableWithPV() {
		
		return (WebDriverUtil.isElementPresent(freebieAvailableWithPV, driver, 20));
		
		/*boolean flag = false;
		if(WebDriverUtil.isElementPresent(freebieAvailableWithPV, driver))
			flag = true;
		return flag;*/
	}
	
	/**
	 * Buy now a combo.
	 */
	@FindBy(xpath=".//*[@id='variant-page']//div[@id='variantCombos']//input[@type='submit' and @value='Buy Now']")
	private WebElement buyNowCombo;
	
	public void addToCartCombo() {
		WebDriverUtil.staticWait(2);
		WebDriverUtil.click(driver, buyNowCombo, "buyNowCombo");
		
		/*if(WebDriverUtil.isElementPresent(buyNow, driver) && WebDriverUtil.isElementClickable(buyNow, driver))
			buyNowCombo.click();	*/	
	}
	
	/**
	 * Compare variants
	 */
	
	@FindBy(xpath="//section[@class='hk-productcomparewidget']")
	private WebElement compareSection;
	
	public boolean verifyCompareWidgetPresent() {
		
		return (WebDriverUtil.isElementPresent(compareSection, driver, 20));
		
		/*boolean flag = false;
		if(WebDriverUtil.isElementPresent(compareSection, driver))
			flag = true;
		return flag;*/
	}
	
	@FindBy(xpath="//section[@class='hk-productcomparewidget']//div[@class='cmpr-btn-cntnr']/a")
	private WebElement compareBtn;
	
	public void clickCompareBtn() {
		
		WebDriverUtil.staticWait(2);
		WebDriverUtil.click(driver, compareBtn, "compareBtn");
		
		/*if(WebDriverUtil.isElementPresent(compareBtn, driver) && WebDriverUtil.isElementClickable(compareBtn, driver))
			compareBtn.click();*/
	}
	
	/**
	 * Write a review
	 */
	@FindBy(xpath="//a[contains(@class,'writeReview')]")
	private WebElement writeAReviewBtn;
	
	public void clickWriteAReviewBtn() {
		WebDriverUtil.staticWait(2);
		WebDriverUtil.click(driver, writeAReviewBtn, "writeAReviewBtn");
		
		/*if(WebDriverUtil.isElementPresent(writeAReviewBtn, driver) && WebDriverUtil.isElementClickable(writeAReviewBtn, driver))
			writeAReviewBtn.click();*/
	}
	
	/**
	 * PDP sizes
	 */	
	@FindBy(xpath="//div[contains(@class,'Weight')]/a")
	private List<WebElement> pdpWeight;
	
	@FindBy(xpath="//label[@for='Weight']/span")
	private WebElement wghtLblTxt;
	
	public List<WebElement> getAllLinksPDPWeights(){
		return pdpWeight;
	}
	
	public boolean verifyWeightLabel(String wghtLbl) {
		boolean flag = false;
		if(WebDriverUtil.isElementPresent(wghtLblTxt, driver, 5)) {
			if(wghtLbl.equalsIgnoreCase(wghtLblTxt.getText().replaceAll(" ", "")))
				flag=true;
		}		
		return flag;
	}
	
	/**
	 * PDP flavours.
	 */
	@FindBy(xpath=".//div[contains(@class,'Flavour')]/a")
	private List<WebElement> pdpFlavours;
	
	@FindBy(xpath="//label[@for='Flavour']/span")
	private WebElement flvrLblTxt;
	
	public List<WebElement> getAllLinksPDPFlavours(){
		return pdpFlavours;
	}
	
	public boolean verifyFlavourLabel(String flvrLbl) {
		boolean flag = false;
		if(WebDriverUtil.isElementPresent(flvrLblTxt, driver, 5)) {
			if(flvrLbl.equalsIgnoreCase(flvrLblTxt.getText().replaceAll(" ", "").replaceAll("&", "")))
				flag=true;
		}		
		return flag;
	}
	
	@FindBy(xpath="//div[contains(@class,'wishlist--pdp-icon')]")
	private WebElement wishlistIcon;
	
	public void clickWishlistButtonOnProductImage()
	{
		WebDriverUtil.click(driver, wishlistIcon, "Wishlist Icon");
	}
}
