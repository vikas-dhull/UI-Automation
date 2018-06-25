package com.mb.MsiteAutomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidDriver;

public class MBmsite_GuestLoginPage extends CommonFunctions
{

	public AndroidDriver<WebElement> androidDriver;
	
	public MBmsite_GuestLoginPage(AndroidDriver<WebElement> androidDriver) 
	{
		this.androidDriver = androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
		
	@FindBy(xpath="//div[@class='row']//input[@name='email' and contains(@placeholder,'Enter')]")
	private WebElement mobileNumberField;
	
	@FindBy(xpath="//input[@value='Proceed']")
	private WebElement proceedButton;
	
	@FindBy(xpath="//form[@id='signInFormMine']//input[@id='password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//form[contains(@class,'guest') and contains(@id,'signIn')]//input[@type='submit']")
	private WebElement loginButton;
	
	@FindBy(xpath="//form[contains(@class,'guest')]//input[contains(@class,'OTP') and @type='checkbox']")
	private WebElement loginViaOtpButton;
	
	@FindBy(xpath="//input[@placeholder='Enter OTP']")
	private WebElement otpField;
	
	@FindBy(xpath="//input[@type='submit' and @value='Done']")
	private WebElement submitButton;
	
	@FindBy(xpath="//span[text()='Signin with google']")
	private WebElement googleButton;
	
	@FindBy(xpath="//span[text()='Signin with Facebook ']")
	private WebElement facebookButton;
	
	public void enterMobileNumber(String mobileNumber) 
	{
		scrollToObject(androidDriver, proceedButton);
		sendKeys(mobileNumberField, mobileNumber);
	}
	
	public void clickProceedButton()
	{
		click(proceedButton);
	}
	
	public void enterPassword(String password)
	{
		staticWait(5);
		sendKeys(passwordField, password);		
	}
	
	public void clickLogin()
	{
		click(loginButton);
	}
	
	public void clickLoginViaOtp()
	{
		click(loginViaOtpButton);
	}
	
	public void enterOtp(String otp)
	{
		staticWait(5);
		sendKeys(otpField, otp);
	}
	
	public void submitOtp()
	{
		click(submitButton);
	}
	
	public void loginViaGoogle(String gmailId, String gmailPswd) 
	{
		String hkWinHndl = androidDriver.getWindowHandle();
		click(googleButton);		

		
		WebDriverUtil.staticWait(4);		
			
		
		if(WebDriverUtil.waitForWindowCount(androidDriver, 2)) {
		
			for (String allHandles : androidDriver.getWindowHandles()) {
				androidDriver.switchTo().window(allHandles);
				System.out.println("current window title :" + androidDriver.getTitle());
				if(androidDriver.getTitle().contains("Sign in")) {
					System.out.println("Switched to Google window..");
					WebElement gmailUserInpt = androidDriver.findElement(By.xpath("//input[@id='identifierId']"));
					gmailUserInpt.sendKeys(gmailId);
					
					WebElement gmailIdentifierNext = androidDriver.findElement(By.xpath("//div[@id='identifierNext']"));
					gmailIdentifierNext.click();							
					WebDriverUtil.staticWait(4);					
					WebElement gmailPswdInpt = androidDriver.findElement(By.xpath("//input[@name='password']"));
					gmailPswdInpt.sendKeys(gmailPswd);					
					WebElement gmailPswdNext = androidDriver.findElement(By.xpath(".//*[@id='passwordNext']"));
					gmailPswdNext.click();
				}
				else if(androidDriver.getTitle().contains("Error")) {
					System.out.println("current window title :" + androidDriver.getTitle());
					androidDriver.close();
					break;
				}
			}
		
		WebDriverUtil.staticWait(10);
		androidDriver.switchTo().window(hkWinHndl);
		System.out.println("current window title :" + androidDriver.getTitle());
		}
	}
	
	public void loginViaFacebook(String fbMailId, String fbPswd)
	{
		String hkWinHndl = androidDriver.getWindowHandle();
		click(facebookButton);
		if(WebDriverUtil.waitForWindowCount(androidDriver, 2)) {	
			
			for (String allHandles : androidDriver.getWindowHandles()) { // traverse all window handles.
				androidDriver.switchTo().window(allHandles);
				System.out.println("current window title :" + androidDriver.getTitle());
				if(androidDriver.getTitle().contains("Facebook")) {
					System.out.println("Switched to Facebook window..");
					
					WebElement fbUserInpt = androidDriver.findElement(By.id("m_login_email"));
					fbUserInpt.sendKeys(fbMailId);					
					WebElement fbPswdInpt = androidDriver.findElement(By.id("m_login_password"));
					fbPswdInpt.sendKeys(fbPswd);					
					WebElement fbLoginSub = androidDriver.findElement(By.id("u_0_5"));					
					fbLoginSub.click();
				}
				else if(androidDriver.getTitle().contains("Error")) {
					System.out.println("current window title :" + androidDriver.getTitle());
					androidDriver.close();
					break;
				}
			}
		
		WebDriverUtil.staticWait(10);
		androidDriver.switchTo().window(hkWinHndl);
		System.out.println("current window title :" + androidDriver.getTitle());
		}
	}
}
