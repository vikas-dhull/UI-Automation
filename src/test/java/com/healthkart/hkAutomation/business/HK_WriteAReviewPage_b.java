package com.healthkart.hkAutomation.business;

import org.openqa.selenium.WebDriver;
import com.healthkart.hkAutomation.pages.HK_WriteAReviewPage_p;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_WriteAReviewPage_b extends HK_WriteAReviewPage_p {
	
	public HK_WriteAReviewPage_b(WebDriver driver) {
		super(driver);
	}
	
	public void performWriteAReview() {
		WebDriverUtil.staticWait(3);
		fillOverallRating();
		fillFeatureRating();
		scrollToFeatureRating();
		fillReviewTitle();
		fillReviewDescription();
		publishReview();
	}

	public boolean verifyReviewPublished() {
		WebDriverUtil.staticWait(5);
		return isReviewPublished();
	}

}
