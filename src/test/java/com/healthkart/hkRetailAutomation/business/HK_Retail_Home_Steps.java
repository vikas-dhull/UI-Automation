package com.healthkart.hkRetailAutomation.business;

import org.openqa.selenium.WebDriver;
import com.healthkart.hkRetailAutomation.pages.HK_Retail_Home_Page;

public class HK_Retail_Home_Steps extends HK_Retail_Home_Page {	
	
	public HK_Retail_Home_Steps(WebDriver driver) {
		super(driver);
	}

	/**
	 * Verify if user is logged in successfully.
	 * @return
	 */
	public boolean verifyUserLoginPOS() {
		return isUserLoggedIn();
	}
	
	/**
	 * Save Store to work-on
	 * @param storeName
	 */
	public void saveRetailStorePOS(String storeName) {
		selectPOSStore(storeName);
		savePOSStore();
	}
	
	/**
	 * Open POS Screen
	 */
	public void openRetailStorePOS() {
		openPOSScreen();
	}

}
