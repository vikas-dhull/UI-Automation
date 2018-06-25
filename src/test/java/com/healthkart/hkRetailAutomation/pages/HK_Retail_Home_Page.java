package com.healthkart.hkRetailAutomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Retail_Home_Page {
	
	public WebDriver driver;
	public CommonFunctions comnFunc;
	
	public HK_Retail_Home_Page(WebDriver driver) 
	{		
		this.driver = driver;
		comnFunc = new CommonFunctions();
		PageFactory.initElements(driver, this);
	}

	/**
	 * Verify User Login
	 */	
	@FindBy(xpath = "//span[contains(text(),'Hi')]")
	private WebElement loggedInUser;
	
	public boolean isUserLoggedIn() 
	{
		return WebDriverUtil.isElementPresent(loggedInUser, driver,20);
	} 
	
	/**
	 * Select POS Store
	 */
	@FindBy(xpath="//select[@name='setWarehouse']")
	private WebElement storeSelect;
	
	public void selectPOSStore(String storeName) 
	{
		if(WebDriverUtil.isElementPresent(storeSelect, driver,20)) {
			Select store = new Select(storeSelect);
			store.selectByVisibleText(storeName);
		}
	}
	
	/**
	 * Save POS Store
	 */
	@FindBy(xpath = "//input[@name='bindUserWithWarehouse']")
	private WebElement storeSave;
	
	public void savePOSStore() 
	{
		WebDriverUtil.click(driver, storeSave, "storeSave");
	}
	
	/**
	 * Open POS Screen
	 */
	@FindBy(xpath = "//table//a[text()='Store']")
	private WebElement posScreenLink;
	
	public void openPOSScreen() 
	{
		WebDriverUtil.click(driver, posScreenLink, "posScreenLink");
	}
	/**
	 * Open HK orders on Retail
	 */
	@FindBy(xpath = "//a[contains(text(),'HK.com Orders')]")
	private WebElement posViewOmniChnlOrders;
	
	public void viewOmniChnlOrders() 
	{
		WebDriverUtil.click(driver, posViewOmniChnlOrders, "posViewOmniChnlOrders");
	}
}
