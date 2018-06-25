package com.mb.webAutomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class MB_GuestLoginPage extends CommonFunctions
{

	public WebDriver driver;
	
	public MB_GuestLoginPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
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
		scrollToObject(driver, proceedButton);
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
		String hkWinHndl = driver.getWindowHandle();
		click(googleButton);		

		
		WebDriverUtil.staticWait(4);		
			
		
		if(WebDriverUtil.waitForWindowCount(driver, 2)) {
		
			for (String allHandles : driver.getWindowHandles()) {
				driver.switchTo().window(allHandles);
				System.out.println("current window title :" + driver.getTitle());
				if(driver.getTitle().contains("Sign in")) {
					System.out.println("Switched to Google window..");
					WebElement gmailUserInpt = driver.findElement(By.xpath("//input[@id='identifierId']"));
					gmailUserInpt.sendKeys(gmailId);
					
					WebElement gmailIdentifierNext = driver.findElement(By.xpath("//div[@id='identifierNext']"));
					gmailIdentifierNext.click();							
					WebDriverUtil.staticWait(4);					
					WebElement gmailPswdInpt = driver.findElement(By.xpath("//input[@name='password']"));
					gmailPswdInpt.sendKeys(gmailPswd);					
					WebElement gmailPswdNext = driver.findElement(By.xpath(".//*[@id='passwordNext']"));
					gmailPswdNext.click();
				}
				else if(driver.getTitle().contains("Error")) {
					System.out.println("current window title :" + driver.getTitle());
					driver.close();
					break;
				}
			}
		
		WebDriverUtil.staticWait(10);
		driver.switchTo().window(hkWinHndl);
		System.out.println("current window title :" + driver.getTitle());
		}
	}
	
	public void loginViaFacebook(String fbMailId, String fbPswd)
	{
		String hkWinHndl = driver.getWindowHandle();
		click(facebookButton);
		if(WebDriverUtil.waitForWindowCount(driver, 2)) {	
			
			for (String allHandles : driver.getWindowHandles()) { // traverse all window handles.
				driver.switchTo().window(allHandles);
				System.out.println("current window title :" + driver.getTitle());
				if(driver.getTitle().contains("Facebook")) {
					System.out.println("Switched to Facebook window..");
					
					WebElement fbUserInpt = driver.findElement(By.id("email"));
					fbUserInpt.sendKeys(fbMailId);					
					WebElement fbPswdInpt = driver.findElement(By.id("pass"));
					fbPswdInpt.sendKeys(fbPswd);					
					WebElement fbLoginSub = driver.findElement(By.id("u_0_0"));					
					fbLoginSub.click();
				}
				else if(driver.getTitle().contains("Error")) {
					System.out.println("current window title :" + driver.getTitle());
					driver.close();
					break;
				}
			}
		
		WebDriverUtil.staticWait(10);
		driver.switchTo().window(hkWinHndl);
		System.out.println("current window title :" + driver.getTitle());
		}
	}
}
