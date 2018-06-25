package com.healthkart.hkRetailAutomation.business;

import org.openqa.selenium.WebDriver;
import com.healthkart.hkRetailAutomation.pages.HK_Retail_Login_Page;

public class HK_Retail_Login_Steps extends HK_Retail_Login_Page {
	
	public HK_Retail_Login_Steps(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Login with mobile number and password.
	 * @param mob
	 * @param pswd
	 */
	public void performLoginWithMobilePassword(String userName,String pswd) {
		enterRetailUserLogin(userName);
		enterRetailLoginPswd(pswd);
		clickSubmitLogin();
    }

}
