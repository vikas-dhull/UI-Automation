package com.healthkart.hkAutomation.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_MyProfile_p extends CommonFunctions{
	
	public WebDriver driver;
	
	public HK_MyProfile_p(WebDriver driver) {		
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
	 * Is My Profile page loaded successfully
	 */
	public boolean verifyProfilePageLoaded() {
		boolean  flag = false;
		staticWait(1);
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
		
		WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);
		WebDriverUtil.staticWait(2);
		if(driver.getTitle().contains("HealthKart.com") && driver.getCurrentUrl().contains("MyAccount"))
			flag = true;
		return flag;
	}
	
	/**
	 * My account - Personal info card
	 */
	@FindBy(xpath="//li[contains(@class,'Personalinfo_card_click')]/a")
	private WebElement personalInfo;
	
	public void clickpersonalInfo(){
		WebDriverUtil.click(driver, personalInfo, "personalInfo");
		
		/*if(WebDriverUtil.isElementPresent(personalInfo, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(personalInfo).build().perform();
            if(WebDriverUtil.isElementClickable(personalInfo, driver))
            	personalInfo.click();
    	}*/
	}
	
	/**
	 * Personal Info - change name.
	 */
	@FindBy(xpath = ".//div[@id='name']")
	private WebElement modifyUserName;
	
	public void enterSignupPswd(String name) {
		if(WebDriverUtil.isElementPresent(modifyUserName, driver,20)) {
			modifyUserName.clear();
			modifyUserName.sendKeys(name);
		}
	}
	
	/**
	 * Save - update personal info.
	 */
	@FindBy(xpath="//input[@id='updateBasicInfo']")
	private WebElement savePersonalInfo;
	
	public void clickSavePersonalInfo(){
		WebDriverUtil.click(driver, savePersonalInfo, "savePersonalInfo");
		
		/*if(WebDriverUtil.isElementPresent(savePersonalInfo, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(savePersonalInfo).build().perform();
            if(WebDriverUtil.isElementClickable(savePersonalInfo, driver))
            	savePersonalInfo.click();
    	}*/
	}
	
	/**
	 *  My account - Change email ID.
	 */
	@FindBy(linkText="Change Email")
	private WebElement changeEmail;
	
	public void clickChangeEmail(){
		WebDriverUtil.click(driver, changeEmail, "changeEmail");
		
		/*if(WebDriverUtil.isElementPresent(changeEmail, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(changeEmail).build().perform();
            if(WebDriverUtil.isElementClickable(changeEmail, driver))
            	changeEmail.click();
    	}*/
	}

	/**
	 *  My account - Change Password.
	 */
	@FindBy(linkText="Change Password")
	private WebElement changePassword;
	
	public void clickChangePassword(){
		WebDriverUtil.click(driver, changePassword, "changePassword");
		
		/*if(WebDriverUtil.isElementPresent(changePassword, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(changePassword).build().perform();
            if(WebDriverUtil.isElementClickable(changePassword, driver))
            	changePassword.click();
    	}*/
	}
	
	/**
	 *  Change Password - old password.
	 */
	
	@FindBy(xpath="//input[@id='oPassword']")
	private WebElement OldPassword;
	
	public void inputOldPassword(String oldPswd){
		if(WebDriverUtil.isElementPresent(OldPassword, driver,20)) {
			OldPassword.clear();
			OldPassword.sendKeys(oldPswd);
		}
	}
	
	/**
	 *  Change Password - new password.
	 */
	
	@FindBy(xpath="//input[@id='nPassword']")
	private WebElement newPassword;
	
	public void inputNewPassword(String newPswd){
		if(WebDriverUtil.isElementPresent(newPassword, driver,20)) {
			newPassword.clear();
			newPassword.sendKeys(newPswd);
		}
	}
	
	/**
	 *  Change Password - confirm password.
	 */
	
	@FindBy(xpath="//input[@id='cPassword']")
	private WebElement confirmPassword;
	
	public void inputConfirmPassword(String cnfrmPswd){
		if(WebDriverUtil.isElementPresent(confirmPassword, driver,20)) {
			confirmPassword.clear();
			confirmPassword.sendKeys(cnfrmPswd);
		}
	}
	
	/**
	 *  Change Password - Update & save password.
	 */
	
	@FindBy(xpath="//input[@id='changePassword']")
	private WebElement updatePassword;
	
	public void clickUpdatePassword(){
		WebDriverUtil.click(driver, updatePassword, "updatePassword");
		
		/*if(WebDriverUtil.isElementPresent(updatePassword, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(updatePassword).build().perform();
            if(WebDriverUtil.isElementClickable(updatePassword, driver))
            	updatePassword.click();
    	}*/		
	}
	
	/**
	 *  My account - Addresses.
	 */
	@FindBy(linkText="Addresses")
	private WebElement manageAddresses;
	
	public void clickManageAddresses(){
		WebDriverUtil.click(driver, manageAddresses, "manageAddresses");
		
		/*if(WebDriverUtil.isElementPresent(manageAddresses, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(manageAddresses).build().perform();
            if(WebDriverUtil.isElementClickable(manageAddresses, driver))
            	manageAddresses.click();
    	}*/
	}
	
	/**
	 * Number of addresses present for user.
	 */
	@FindBys(@FindBy(xpath = "//div[contains(@class,'address-cntnr')]"))
	private List<WebElement> userHasAddressCount;
	@FindBy(xpath = "//div[contains(@class,'address-cntnr')]")
	private WebElement userHasAddressCountCheck;
	
	public int getUserHasAddressCount() {
		int userAddCount=0;
		if(WebDriverUtil.isElementPresent(userHasAddressCountCheck, driver,20))
			userAddCount=userHasAddressCount.size();
		return userAddCount;
	}
	
	/**
	 * Addresses - delete address.
	 */
	@FindBy(xpath="//a[contains(@class,'delete-address')]")
	private WebElement deleteAddress;
	
	@FindBy(xpath="//a[contains(@class,'popup-remove')][text()='yes']")
	private WebElement deleteAddressPopupYes;	
	
	public void clickDeleteAddress(){
		WebDriverUtil.click(driver, deleteAddress, "deleteAddress");
		
		/*if(WebDriverUtil.isElementPresent(deleteAddress, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(deleteAddress).build().perform();
            if(WebDriverUtil.isElementClickable(deleteAddress, driver))
            	deleteAddress.click();
    	}*/
		WebDriverUtil.mouseHoverAndClick(driver, deleteAddressPopupYes, "deleteAddressPopupYes");
		
		/*if(WebDriverUtil.isElementPresent(deleteAddressPopupYes, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(deleteAddressPopupYes).build().perform();
	        if(WebDriverUtil.isElementClickable(deleteAddressPopupYes, driver))
	        	deleteAddressPopupYes.click();			
		}*/
	}
	
	/**
	 * verify if address deleted successfully.
	 */	
	@FindBy(xpath="//div[contains(@class,'alert-messages')]/ul/li[contains(text(),'deleted successfully')]")
	private WebElement verifyAddressDeleted;	
	
	public boolean verifyIfAddressDeleted(){
		return (WebDriverUtil.isElementPresent(verifyAddressDeleted, driver,20));
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
	
	@FindBy(xpath="//input[@id='line1']")
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
	}
	
	/**
	 * Save address.
	 */
	
	@FindBy(xpath="//input[@id='addAddressForUser']")
	private WebElement addressSave;
	
	public void submitAddressSave() {
		WebDriverUtil.click(driver, addressSave, "addressSave");
		
		/*if(WebDriverUtil.isElementPresent(addressSave, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(addressSave).build().perform();
	        if(WebDriverUtil.isElementClickable(addressSave, driver))
	        	addressSave.click();
		}*/		
	}
	
	/**
	 * Addresses - Edit address.
	 */
	@FindBy(xpath="//span[contains(@class,'edit-address')]")
	private WebElement editAddress;
	
	public void clickEditAddress(){
		WebDriverUtil.click(driver, editAddress, "editAddress");
		
		/*if(WebDriverUtil.isElementPresent(editAddress, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(editAddress).build().perform();
            if(WebDriverUtil.isElementClickable(editAddress, driver))
            	editAddress.click();
    	}*/
	}
	
	/**
	 * Add new address.
	 */	
	
	/**
	 *  My account - HK Cash.
	 */
	@FindBy(linkText="HK Cash")
	private WebElement manageHkCash;
	
	public void clickManageHkCash(){
		WebDriverUtil.click(driver, manageHkCash, "manageHkCash");
		
		/*if(WebDriverUtil.isElementPresent(manageHkCash, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(manageHkCash).build().perform();
            if(WebDriverUtil.isElementClickable(manageHkCash, driver))
            	manageHkCash.click();
    	}*/
	}
	
	/**
	 * My orders
	 */
	@FindBy(linkText="My Orders")
	private WebElement myOrders;
	
	public void clickMyOrders(){
		WebDriverUtil.mouseHoverAndClick(driver, myOrders, "myOrders");
		
		/*if(WebDriverUtil.isElementPresent(myOrders, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(myOrders).build().perform();
            if(WebDriverUtil.isElementClickable(myOrders, driver))
            	myOrders.click();
    	}*/
	}
	
	/**
	 * Cancel an prepaid order.
	 */
	public void clickCancelPrepaidOrder(String gatewayOrderId) {
		WebElement cancelPrepaidOrder = driver.findElement(By.xpath("//button[@data-action='cancel' and @data-orderid='"+gatewayOrderId+"' and @data-refund='true']"));
		WebDriverUtil.click(driver, cancelPrepaidOrder, "cancelPrepaidOrder");
		
		/*if(WebDriverUtil.isElementPresent(cancelPrepaidOrder, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(cancelPrepaidOrder).build().perform();
            if(WebDriverUtil.isElementClickable(cancelPrepaidOrder, driver))
            	cancelPrepaidOrder.click();
    	}*/
	}
	
	/**
	 * Cancel an Free-Checkout order.
	 */
	public void clickCancelFreeCheckoutOrder(String gatewayOrderId) {
		WebElement cancelPrepaidOrder = driver.findElement(By.xpath("//button[@data-action='cancel' and @data-orderid='"+gatewayOrderId+"' and @data-refund='false']"));
		WebDriverUtil.click(driver, cancelPrepaidOrder, "cancelPrepaidOrder");
		
		/*if(WebDriverUtil.isElementPresent(cancelPrepaidOrder, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(cancelPrepaidOrder).build().perform();
            if(WebDriverUtil.isElementClickable(cancelPrepaidOrder, driver))
            	cancelPrepaidOrder.click();
    	}*/
	}
	
	/**
	 * Cancel an COD order.
	 */
	public void clickCancelCODOrder(String gatewayOrderId) {
		WebElement cancelCODOrder = driver.findElement(By.xpath("//button[@data-action='cancel' and @data-orderid='"+gatewayOrderId+"' and @data-refund='false']"));
		WebDriverUtil.click(driver, cancelCODOrder, "cancelCODOrder");
		
		/*if(WebDriverUtil.isElementPresent(cancelCODOrder, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(cancelCODOrder).build().perform();
            if(WebDriverUtil.isElementClickable(cancelCODOrder, driver))
            	cancelCODOrder.click();
    	}*/		
	}
	
	/**
	 * select reason for cancel prepaid order 
	 */
	@FindBy(xpath="//div[contains(@class,'popup-content')]//select[@name='reason']")
	private WebElement selectReasonForCancel;
	
	public void selectReasonForCancel() {
		if(WebDriverUtil.isElementPresent(selectReasonForCancel, driver,20)) {
			Select sortBy = new Select(selectReasonForCancel);
			sortBy.selectByVisibleText("Ordered By Mistake");
		}
	}
	
	/**
	 * select refund type for prepaid order.
	 */
	@FindBy(xpath="//div[contains(@class,'popup-content')]//select[contains(@class,'refund-type')]")
	private WebElement selectRefundTypeForCancel;
	
	public void selectRefundTypeForCancel() {
		if(WebDriverUtil.isElementPresent(selectRefundTypeForCancel, driver,20)) {
			Select sortBy = new Select(selectRefundTypeForCancel);
			sortBy.selectByVisibleText("Bank Transfer");
		}
	}
	
	/**
	 * Submit - Cancel prepaid order
	 */	
	@FindBy(xpath="//div[contains(@class,'popup-content')]//button[contains(text(),'Cancel Order')]")
	private WebElement submitCancelPrepaidOrder;
	
	public void clickSubmitCancelPrepaidOrder() {
		WebDriverUtil.click(driver, submitCancelPrepaidOrder, "submitCancelPrepaidOrder");
		
		/*if(WebDriverUtil.isElementPresent(submitCancelPrepaidOrder, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(submitCancelPrepaidOrder).build().perform();
            if(WebDriverUtil.isElementClickable(submitCancelPrepaidOrder, driver))
            	submitCancelPrepaidOrder.click();
    	}*/		
	}
	
	/**
	 * Verify order status - cancelled.
	 * @param gatewayOrderId
	 * @return
	 */
	
	@FindBy(xpath="//h1[text()='Orders']")
	private WebElement ordersText;
	
	public boolean verifyCancelOrderStatus(String gatewayOrderId) {
		
		boolean flag = false;
		
		By verifyCancelStatus = By.xpath("//strong[contains(text(),'"+gatewayOrderId+"')]/ancestor::div[5]//div[contains(text(),'Order Status: Cancelled')]");
		WebDriverUtil.waitForElementToBeDisplayedBy(driver, 90, verifyCancelStatus);
		WebDriverUtil.staticWait(4);
		if(WebDriverUtil.isElementPresentBy(verifyCancelStatus, driver)) 
		{
            	flag = true;
    			System.out.println("order status found cancelled..");
    	}
		return flag;
	}
}
