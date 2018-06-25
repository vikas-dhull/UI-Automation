package com.healthkart.hkAutomation.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_SelectAddress_p {
	public WebDriver driver;
	
	public HK_SelectAddress_p(WebDriver driver) {		
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
	 * Is Address Page loaded successfully?
	 */
	public boolean verifyAddressPageLoaded() {
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
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		if (isTextSelectAddressPresent()|| isTextAddNewAddressPresent()) {
			if(driver.getTitle().contains("Select Delivery Address") && driver.getCurrentUrl().contains("SelectAddress.action"))
				flag = true;
		}
		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		return flag;
	}
	
	/**
	 * 'SELECT ADDRESS' text present ?
	 */
	@FindBy(xpath="//span[contains(@class,'address-header hidden-xs')][text()='Select address']")
	private WebElement textSelectAddress;
	
	public boolean isTextSelectAddressPresent() {
		boolean flag = false;
		if(WebDriverUtil.isElementPresent(textSelectAddress, driver,2)) {
			flag=true;
		}		
		return flag;		
	}
	
	/**
	 * 'ADD A NEW ADDRESS' text present ?
	 */
	@FindBy(xpath="//span[@id='addNewAddress']")
	private WebElement textAddNewAddress;
	
	public boolean isTextAddNewAddressPresent() {
		boolean flag = false;
		if(WebDriverUtil.isElementPresent(textAddNewAddress, driver,2)) {
			flag=true;
		}		
		return flag;		
	}
	
	/**
	 * Number of addresses present for user.
	 */
	@FindBys(@FindBy(xpath = "//div[contains(@class,'usr-add-cntnr')]"))
	private List<WebElement> userHasAddressCount;
	@FindBy(xpath = "//div[contains(@class,'usr-add-cntnr')]")
	private WebElement userHasAddressCountCheck;
	
	public int getUserHasAddressCount() {
		int userAddCount=0;
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		if(WebDriverUtil.isElementPresent(userHasAddressCountCheck, driver,2))
			userAddCount=userHasAddressCount.size();
		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		return userAddCount;
	}
	
	/**
	 * Click 'Select Address' button.
	 */
	@FindBy(xpath="//a[contains(@href,'selectedAddressId')][text()='Select Address']")
	private WebElement firstSelectAddressBtn;
	
	public void clickFirstSelectAddressBtn() {
		WebDriverUtil.click(driver, firstSelectAddressBtn, "firstSelectAddressBtn");
	}
	
	/**
	 * Add a new address link.
	 */
	@FindBy(xpath="//div[contains(@class,'add-new-address-link')]")
	private WebElement addNewAddressLink;
	
	public void clickAddNewAddressLink() {
		WebDriverUtil.click(driver, addNewAddressLink, "addNewAddressLink");
	}
	
	/**
	 * Add a new address fields. -- Name.
	 */
	
	@FindBy(xpath="//input[@id='user-name']")
	private WebElement addressName;
	
	public void setAddressName(String name) {
		if(WebDriverUtil.isElementPresent(addressName, driver,20)) {
			addressName.clear();
			addressName.sendKeys(name);
		}
	}
	
	/**
	 * Add a new address fields. -- contact no.
	 */
	
	@FindBy(xpath="//input[@id='contactNo']")
	private WebElement addressMobileNo;
	
	public void setAddressMobileNo(String mob) {
		if(WebDriverUtil.isElementPresent(addressMobileNo, driver,20)) {
			addressMobileNo.clear();
			addressMobileNo.sendKeys(mob);
		}
	}
	
	/**
	 * Add a new address fields. -- Add line 1.
	 */
	
	@FindBy(xpath="//input[@id='hkac']")
	private WebElement addressLine1;
	
	public void setAddressLine1(String line1) {
		if(WebDriverUtil.isElementPresent(addressLine1, driver,20)) {
			addressLine1.clear();
			addressLine1.sendKeys(line1);
		}
	}
	
	/**
	 * Add a new address fields. -- landmark.
	 */
	
	@FindBy(xpath="//input[@id='landmark']")
	private WebElement addressLandmark;
	
	public void setAddressLandmark(String landmark) {
		if(WebDriverUtil.isElementPresent(addressLandmark, driver,20)) {
			addressLandmark.clear();
			addressLandmark.sendKeys(landmark);
		}
	}
	
	/**
	 * Add a new address fields. -- Pincode.  
	 */
	
	@FindBy(xpath="//input[@id='pincode']")
	private WebElement addressPincode;
	
	public void setAddressPincode(String pincode) {
		if(WebDriverUtil.isElementPresent(addressPincode, driver,20)) {
			addressPincode.clear();
			addressPincode.sendKeys(pincode);
		}
		WebDriverUtil.click(driver,textAddNewAddress,"textAddNewAddress");
	}
	
	/**
	 * Add a new address fields. -- EmailID.  
	 */
	
	@FindBy(xpath="//input[@id='emailID']")
	private WebElement addressEmailId;
	
	public void setAddressEmailId(String emailId) {
		if(WebDriverUtil.isElementPresent(addressEmailId, driver,20)) {
			addressEmailId.clear();
			addressEmailId.sendKeys(emailId);
		}
	}
	
	/**
	 * un-check 
	 */
	@FindBy(xpath="//input[@id='subscribe']")
	private WebElement addressUncheckSubscription;
	
	public void uncheckForSubscription() {
		WebDriverUtil.click(driver, addressUncheckSubscription, "addressUncheckSubscription");
	}
	
	/**
	 * Save address.
	 */
	
	@FindBy(xpath="//input[@id='addAddressForUser']")
	private WebElement addressSave;
	
	public void submitAddressSave() {
		WebDriverUtil.click(driver, addressSave, "addressSave");	
	}	
	
	/**
	 * Edit an existing address.
	 */
	
	@FindBy(xpath="//a[contains(@class,'edit-address')]")
	private WebElement editAddress;
	
	public void clickEditAddress() {
		WebDriverUtil.click(driver, editAddress, "editAddress");	
	}	
	
	/**
	 * Delete an address.
	 */
	@FindBy(xpath="//a[contains(@href,'deleteAddressForUser')]/span")
	private WebElement deleteFirstAddress;
	
	@FindBy(xpath="//div[@class='popup-content popup-theme-orange']/div[3]/a[1]")
	private WebElement deleteAddressPopup;	
	
	public void clickDeleteFirstAddress() {
		
		WebDriverUtil.click(driver, deleteFirstAddress, "deleteFirstAddress");
		WebDriverUtil.click(driver, deleteAddressPopup, "deleteAddressPopup");	
	}
		
	/**
	 * Edit an email address.
	 */
	@FindBy(xpath="//*[@id='editEmailAddress']")
	private WebElement editEmailLinkOnAddressPage;
	
	@FindBy(xpath="//input[@id='emailField']")
	private WebElement editEmailOnAddressPage;
	
	@FindBy(xpath="//span[@class='saveButton']")
	private WebElement saveEmailOnAddressPage;
	
	public void clickEditEmailLinkOnAddressPage() {
		WebDriverUtil.click(driver, editEmailLinkOnAddressPage, "editEmailLinkOnAddressPage");
	}
	
	public void setEmailOnAddressPage(String emailId) {
		if(WebDriverUtil.isElementPresent(editEmailOnAddressPage, driver,20)) {
			editEmailOnAddressPage.clear();
			editEmailOnAddressPage.sendKeys(emailId);
		}
	}
	
	public void clickSaveEmailOnAddressPage() {
		WebDriverUtil.click(driver, saveEmailOnAddressPage, "saveEmailOnAddressPage");
	}
	
	/**
	 * Get email id from address page for order updates.
	 */
	@FindBy(xpath=".//*[@id='emailValId']")
	private WebElement emailId;
	
	public String getEmailFromAddressPage() {
		String email = null;
		if(WebDriverUtil.isElementPresent(emailId, driver,20)) {
			email=emailId.getText();
		}
		return email;
	}
}
