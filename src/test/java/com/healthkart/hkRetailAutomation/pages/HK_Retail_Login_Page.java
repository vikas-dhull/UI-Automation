package com.healthkart.hkRetailAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Retail_Login_Page {

	public WebDriver driver;
	
	public HK_Retail_Login_Page(WebDriver driver) {		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * login page web-elements
	 */
	@FindBy(id = "loginName")
	private WebElement hkRetailUser;
	@FindBy(xpath="//div[@class='login']//input[@name='password']")
	private WebElement hkRetailPswd;
	@FindBy(xpath = "//input[@name='login']")
	private WebElement hkRetailLoginSubmit;
	
	public void enterRetailUserLogin(String userName) {
		if(WebDriverUtil.isElementPresent(hkRetailUser, driver, 20)) {
			hkRetailUser.clear();
			hkRetailUser.sendKeys(userName);
		}
	}
	
	public void enterRetailLoginPswd(String pswd) {
		if(WebDriverUtil.isElementPresent(hkRetailPswd, driver, 20)) {
			hkRetailPswd.clear();
			hkRetailPswd.sendKeys(pswd);
		}
	}
	
	public void clickSubmitLogin() {
		WebDriverUtil.click(driver, hkRetailLoginSubmit, "hkRetailLoginSubmit");
	}
}
