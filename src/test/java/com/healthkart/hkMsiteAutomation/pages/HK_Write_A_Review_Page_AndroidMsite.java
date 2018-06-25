package com.healthkart.hkMsiteAutomation.pages;

import org.openqa.selenium.By;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidDriver;

public class HK_Write_A_Review_Page_AndroidMsite 
{
	AndroidDriver<?> androidDriver;
	CommonFunctions comnFunc;
	
	private By writeAreviewText = By.xpath("//h2[text()='Write a Review']");
	private By overallRating = By.xpath("//h3[text()='Overall Rating']/ancestor::div[1]//li[@title='Rate 5 stars']");
	private By tasteFeatureRating = By.xpath("//span[text()=' Taste']/ancestor::div[1]/following-sibling::div[1]//li[@title='Rate 4 stars']");
	private By mixabilityFeatureRating = By.xpath("//span[text()=' Mixability']/ancestor::div[1]/following-sibling::div[1]//li[@title='Rate 5 stars']");
	private By efficacyFeatureRating = By.xpath("//span[text()=' Efficacy']/ancestor::div[1]/following-sibling::div[1]//li[@title='Rate 4 stars']");
	private By valueForMoneyFeatureRating = By.xpath("//span[text()=' Value for money']/ancestor::div[1]/following-sibling::div[1]//li[@title='Rate 5 stars']");
	private By reviewTitle = By.xpath("//p[text()='Title']/following-sibling::input");
	private By reviewDescription = By.xpath("//div[@class='review-txt-contnr']/textarea");
	private By reviewPublish = By.xpath("//input[@value='Publish Review']");
	private By reviewPublishSuccessText = By.xpath("//div[contains(@class,'review-accordion')]//div[contains(text(),'Your review has been submitted successfully.')]");
	
	private By closeNotifyVisitorOverlay = By.xpath("//a[@id='nv_js-modal-close-button']");
	
	public HK_Write_A_Review_Page_AndroidMsite(AndroidDriver<?> androidDriver) 
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

	public boolean verifyWriteAReviewPageLoaded() 
	{
		
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
		
		return comnFunc.isElementPresentBy(androidDriver, writeAreviewText);
	}

	public void fillOverallRating() 
	{
		comnFunc.clickBy(overallRating);
	}

	public void scrollToFeatureRating() 
	{
		comnFunc.scrollToObject(androidDriver, androidDriver.findElement(overallRating));		
	}
	
	public void fillFeatureRating() 
	{
		comnFunc.clickBy(tasteFeatureRating);
		comnFunc.clickBy(mixabilityFeatureRating);
		comnFunc.clickBy(efficacyFeatureRating);
		comnFunc.clickBy(valueForMoneyFeatureRating);
	}

	public void scrollToReviewTitle() 
	{
		comnFunc.scrollToObject(androidDriver, androidDriver.findElement(efficacyFeatureRating));		
	}

	public void fillReviewTitle() 
	{
		comnFunc.sendKeys(androidDriver.findElement(reviewTitle), "Sample Review Title");		
	}

	public void fillReviewDescription() 
	{
		comnFunc.sendKeys(androidDriver.findElement(reviewDescription), "Sample Review Description");
	}

	public void publishReview() 
	{
		comnFunc.clickBy(reviewPublish);
	}

	public boolean isReviewPublished() 
	{
		return comnFunc.isElementPresentBy(androidDriver, reviewPublishSuccessText);
	}
	
	
}
