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

public class HK_WriteAReviewPage_p {
	
	public WebDriver driver;
	
	public HK_WriteAReviewPage_p(WebDriver driver) {		
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
	 * Is Write A Review page loaded successfully
	 */
	@FindBy(xpath="//h2[text()='Write a Review']")
	private WebElement writeAreviewText;
		
	public boolean verifyWriteAReviewPageLoaded() {
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
		
		WebDriverUtil.staticWait(5);
		if(WebDriverUtil.isElementPresent(writeAreviewText, driver,20)) {
			if(driver.getCurrentUrl().contains("healthkart.com/reviews"))
				flag = true;
		}
		return flag;
	}
	
	/**
	 * Fill overall Rating(5).
	 */
	@FindBy(xpath="//h3[text()='Overall Rating']/ancestor::div[1]//li[@title='Rate 5 stars']")
	private WebElement overallRating;
	
	public void fillOverallRating() {
		WebDriverUtil.click(driver, overallRating, "overallRating");
		/*if(WebDriverUtil.isElementPresent(overallRating, driver,20))
			overallRating.click();*/
	}
	
	/**
	 * Fill Feature Rating.
	 */
	@FindBy(xpath="//span[text()=' Taste']/ancestor::div[1]/following-sibling::div[1]//li[@title='Rate 4 stars']")
	private WebElement tasteFeatureRating;
	@FindBy(xpath="//span[text()=' Mixability']/ancestor::div[1]/following-sibling::div[1]//li[@title='Rate 5 stars']")
	private WebElement mixabilityFeatureRating;
	@FindBy(xpath="//span[text()=' Efficacy']/ancestor::div[1]/following-sibling::div[1]//li[@title='Rate 4 stars']")
	private WebElement efficacyFeatureRating;
	@FindBy(xpath="//span[text()=' Value for money']/ancestor::div[1]/following-sibling::div[1]//li[@title='Rate 5 stars']")
	private WebElement valueForMoneyFeatureRating;
	
	public void fillFeatureRating() {
		
		WebDriverUtil.click(driver, tasteFeatureRating, "tasteFeatureRating");
		WebDriverUtil.click(driver, mixabilityFeatureRating, "mixabilityFeatureRating");
		WebDriverUtil.click(driver, efficacyFeatureRating, "efficacyFeatureRating");
		WebDriverUtil.click(driver, valueForMoneyFeatureRating, "valueForMoneyFeatureRating");
		
		/*if(WebDriverUtil.isElementPresent(tasteFeatureRating, driver,10))
			tasteFeatureRating.click();
		if(WebDriverUtil.isElementPresent(mixabilityFeatureRating, driver,10))
			mixabilityFeatureRating.click();
		if(WebDriverUtil.isElementPresent(efficacyFeatureRating, driver,10))
			efficacyFeatureRating.click();
		if(WebDriverUtil.isElementPresent(valueForMoneyFeatureRating, driver,10))
			valueForMoneyFeatureRating.click();*/
	}
	
	public void scrollToFeatureRating() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+(valueForMoneyFeatureRating.getLocation().y-50)+")");
	}
	
	/**
	 * Fill Review Title.
	 */
	@FindBy(xpath="//p[text()='Title']/following-sibling::input")
	private WebElement reviewTitle;
	
	public void fillReviewTitle() {
		reviewTitle.sendKeys("Sample Review Title");
	}
	
	/**
	 * Fill Review Description.
	 */
	@FindBy(xpath="//div[@class='review-txt-contnr']/textarea")
	private WebElement reviewDescription;
	
	public void fillReviewDescription() {
		reviewDescription.sendKeys("Sample Review Description.");
	}
	
	/**
	 * Publish Review.
	 */
	@FindBy(xpath="//input[@value='Publish Review']")
	private WebElement reviewPublish;
	
	public void publishReview() {
		WebDriverUtil.click(driver, reviewPublish, "reviewPublish");
		/*if(WebDriverUtil.isElementPresent(reviewPublish, driver)) {
	        reviewPublish.click();
		}*/
			
	}
	
	/**
	 * verify review published.
	 */
	@FindBy(xpath="//div[contains(@class,'review-accordion')]//div[contains(text(),'Your review has been submitted successfully.')]")
	private WebElement reviewPublishSuccessText;
	
	public boolean isReviewPublished() {
		return (WebDriverUtil.isElementPresent(reviewPublishSuccessText, driver,20));

	}
}
