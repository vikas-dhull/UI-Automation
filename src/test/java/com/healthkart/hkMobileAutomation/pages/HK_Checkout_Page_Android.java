package com.healthkart.hkMobileAutomation.pages;

import org.openqa.selenium.By;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidDriver;

public class HK_Checkout_Page_Android extends CommonFunctions
{
	AndroidDriver<?> androidDriver;
	public HK_Checkout_Page_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
	}
	
	private By mobileNumberElement = By.id("com.healthkart.healthkart:id/fsu_number");
	private By continueButton = By.xpath("//android.widget.Button[@text='Continue']");
	private By passwordField = By.id("com.healthkart.healthkart:id/fsu_password");
	private By loginButtonBy = By.xpath("//android.widget.Button[@text='Log in']");
	private By addAddressButtonBy = By.id("com.healthkart.healthkart:id/newAddressT");
	private By loginViaOtp = By.id("com.healthkart.healthkart:id/fsu_login_via_otp");
	private By enterOtp = By.id("com.healthkart.healthkart:id/fsu_otp");
	private By doneButtonBy = By.xpath("//android.widget.Button[@text='Done']");
	//private By firstAddressBy = By.xpath("//android.widget.TextView[@resource-id='com.healthkart.healthkart:id/name']");
	private By firstAddressBy = By.id("com.healthkart.healthkart:id/name");
	private By proceedToPayBy = By.xpath("//android.widget.LinearLayout[@resource-id='com.healthkart.healthkart:id/checkoutT']/android.widget.Button");

	public void enterMobileNumber(String mobileNumber)
	{
		androidDriver.findElement(mobileNumberElement).sendKeys(mobileNumber);
	}
	
	public void clickContinueButton()
	{
		click(androidDriver.findElement(continueButton));
	}

	public void enterPassword(String password) 
	{
		click(androidDriver.findElement(passwordField));
		androidDriver.getKeyboard().sendKeys(password);
		androidDriver.hideKeyboard();
	}

	public void clickLoginButton() 
	{
		click(androidDriver.findElement(loginButtonBy));
	}

	public boolean verifySelectAddressPageDisplayed() 
	{
		waitForElementToBeDisplayedBy(androidDriver, 90, addAddressButtonBy);
		return androidDriver.findElement(addAddressButtonBy).isDisplayed();
	}

	public void clickLoginViaOtp() 
	{
		click(androidDriver.findElement(loginViaOtp));	
	}


	public void setOtp(String otp) 
	{
		androidDriver.findElement(enterOtp).sendKeys(otp);
	}

	public void clickDoneButton() 
	{
		WebDriverUtil.staticWait(2);
		//androidDriver.hideKeyboard();
		/*androidDriver.scrollToExact("Done");*/
		scrollAndSwipeByElementAndroid(androidDriver, doneButtonBy, "up");
		click(androidDriver.findElement(doneButtonBy));
	}

	public void selectAddress() 
	{
		click(androidDriver.findElement(firstAddressBy));
	}

	public void proceedToPay() 
	{
		while(androidDriver.findElement(proceedToPayBy).isDisplayed())
		{
			staticWait(2);
			click(androidDriver.findElement(proceedToPayBy));
			break;
		}
	}

	public void waitForLoginWindow() 
	{
		waitForElementToBeDisplayedBy(androidDriver, 30, doneButtonBy);
	}

	
		
}
