package com.healthkart.hkAutomation.business;

import org.openqa.selenium.WebDriver;

import com.healthkart.hkAutomation.pages.HK_SearchPage_p;

public class HK_SearchPage_b extends HK_SearchPage_p{	
	
	public HK_SearchPage_b(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * go to PDP page for first variant.
	 */
	public void visitPDPPageForFirstVariant() {
		clickfirstPVLink();
		// verify PDP page loaded successfully ?
	}
}
