package com.healthkart.hkAutomation.business;

import org.openqa.selenium.WebDriver;
import com.healthkart.hkAutomation.pages.HK_CompareVariantPage_p;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_CompareVariantPage_b extends HK_CompareVariantPage_p {
	
	public HK_CompareVariantPage_b(WebDriver driver) {
		super(driver);
	}
	
	public void performAddToCartFromCompareVariantPage() {
		clickBuyNowFromComparePage();
		WebDriverUtil.staticWait(5);
	}

}
