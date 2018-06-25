package com.healthkart.hkAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_SecurePaytmWallet_p {
	
public WebDriver driver;
	
	public HK_SecurePaytmWallet_p(WebDriver driver) {		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Is PAYTM WALLET page loaded successfully?
	 */
	@FindBy(xpath="//form[@ng-submit='requestOtp()']")
	private WebElement requestOtpForm;
	
	public boolean verifyPayTmPageLoaded() {
		boolean  flag = false;
		System.out.println("current url : " + driver.getCurrentUrl());
		if(driver.getCurrentUrl().contains("secure.paytm.in")){
			driver.switchTo().frame("test");
			//if(WebDriverUtil.isElementPresent(inputMobileNumber, driver))
				flag = true;
		}
		return flag;
	}
	
	/**
	 * Enter Mobile Number to receive OTP
	 */
	
	@FindBy(xpath="//form[contains(@class,'ng-pristine')]//input[1]")  //input[@name='username' and contains(@placeholder,'Enter Mobile Number')]
	private WebElement inputMobileNumber;
	
	public void enterMobileNumber() {
		if(WebDriverUtil.isElementPresent(inputMobileNumber, driver,20)) {
			inputMobileNumber.clear();
			inputMobileNumber.sendKeys("8448211227");
		}
	}
	
	/**
	 * Request Paytm OTP.
	 */
	@FindBy(xpath="//form[contains(@class,'ng-pristine')]//button")  //button[text()='Request OTP']
	private WebElement requestOtp;
	
	public void clickRequestOtp() {
		WebDriverUtil.click(driver, requestOtp, "requestOtp");
		/*if(WebDriverUtil.isElementPresent(requestOtp, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(requestOtp).build().perform();
	        if(WebDriverUtil.isElementClickable(requestOtp, driver))
	        	requestOtp.click();
		}*/		
	}
	
	/**
	 * Enter Paytm OTP
	 */
	
	@FindBy(xpath="//input[@name='username' and @placeholder='Enter OTP']")
	private WebElement inputPaytmOtp;
	
	public void enterPaytmOtp(String otp) {
		if(WebDriverUtil.isElementPresent(inputPaytmOtp, driver,20)) {
			inputPaytmOtp.clear();
			inputPaytmOtp.sendKeys(otp);
		}
	}
	
	/**
	 * Submit Paytm OTP.
	 */
	@FindBy(xpath="//button[contains(@class,'myBtnLogin') and text()='Login']")
	private WebElement submitOtp;
	
	public void clickSubmitOtp() {
		WebDriverUtil.click(driver, submitOtp, "submitOtp");
		/*if(WebDriverUtil.isElementPresent(requestOtp, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(requestOtp).build().perform();
	        if(WebDriverUtil.isElementClickable(requestOtp, driver))
	        	requestOtp.click();
		}*/		
	}
	
	@FindBy(xpath="//h2[text()='YOUR PAYMENT WAS NOT SUCCESSFUL']")
	private WebElement paymentNotSuccessfull;
	
	public boolean verifyPaymentNotSuccessfullDisplayed()
	{
		return paymentNotSuccessfull.isDisplayed();
	}

}
