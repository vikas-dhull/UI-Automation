package com.healthkart.hkMsiteAutomation.business;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.healthkart.hkMsiteAutomation.pages.HK_Write_A_Review_Page_AndroidMsite;
import io.appium.java_client.android.AndroidDriver;

public class HK_Write_A_Review_Steps_AndroidMsite 
{
	AndroidDriver<?> androidDriver;
	HK_Write_A_Review_Page_AndroidMsite reviewPage;
	CommonFunctions comnFunc;
	
	public HK_Write_A_Review_Steps_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		reviewPage = new HK_Write_A_Review_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
	}	
	
	public boolean verifyReviewPageLoaded()
	{		
		return reviewPage.verifyWriteAReviewPageLoaded();
	}
	
	public void performWriteAReview() {
		reviewPage.fillOverallRating();
		reviewPage.scrollToFeatureRating();
		reviewPage.fillFeatureRating();
		reviewPage.scrollToReviewTitle();
		reviewPage.fillReviewTitle();
		reviewPage.fillReviewDescription();
		reviewPage.publishReview();
	}

	public boolean verifyReviewPublished() {
		WebDriverUtil.staticWait(5);
		return reviewPage.isReviewPublished();
	}
}
