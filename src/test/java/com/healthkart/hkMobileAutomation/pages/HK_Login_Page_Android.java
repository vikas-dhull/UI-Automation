package com.healthkart.hkMobileAutomation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;

import io.appium.java_client.android.AndroidDriver;

public class HK_Login_Page_Android extends CommonFunctions
{
	AndroidDriver<?> androidDriver;
	
	private By mobileNumberField = By.id("com.healthkart.healthkart:id/fsu_number");
	private By passwordField = By.id("com.healthkart.healthkart:id/fsu_password");
	private By loginButton = By.id("com.healthkart.healthkart:id/fsu_button");
	private By loginViaOtp = By.id("com.healthkart.healthkart:id/fsu_login_via_otp");
	private By enterOtp = By.id("com.healthkart.healthkart:id/fsu_otp");
	private By googleAccount = By.id("com.google.android.gms:id/account_display_name");
	private By facebookAccount = By.id("com.healthkart.healthkart:id/fsu_facebook");
	private By facebookContinue = By.xpath("//android.widget.Button[@content-desc=\"Continue \"]");
	
	private By facebookUsernameBy = By.id("m_login_email");
	private By facebookPasswordBy = By.id("m_login_password");
	private By signUpButtonBy = By.xpath("//android.widget.Button[@text='Sign Up']");
	private By trueCallerButtonBy = By.xpath("//android.widget.TextView[@text='Truecaller']");
	private By topLeftNavigationButton = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");
	
	//private By truecallerContBy= By.xpath("//android.widget.TextView[@text='Continue']");
	private By truecallerVerifyBy=By.id("com.truecaller:id/confirm");
	
	
	public HK_Login_Page_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
	}
	
	
	public void enterMobileNumber(String mobileNumber)
	{
		//androidDriver.tap(1, androidDriver.findElement(mobileNumberField), 1);
		clickBy(mobileNumberField);
		androidDriver.getKeyboard().sendKeys(mobileNumber);
		reportLogAndPrintInConsole("Mobile Numeber for signup ::" + mobileNumber);
	}


	public void enterPassword(String password) 
	{
	//	androidDriver.tap(1, androidDriver.findElement(passwordField), 1);
		clickBy(passwordField);
		androidDriver.getKeyboard().sendKeys(password);
		androidDriver.hideKeyboard();
	}


	public void submitLogin() 
	{
//		androidDriver.hideKeyboard();
		//androidDriver.tap(1, androidDriver.findElement(loginButton), 1);	
		clickBy(loginButton);
	}


	public void clickLoginViaOtp() 
	{
		click(androidDriver.findElement(loginViaOtp));
		waitForElementToBeDisplayedBy(androidDriver, 30, loginButton);
		System.out.println("wait over");
		staticWait(3);
	}


	public void setOtp(String otp) 
	{
		androidDriver.findElement(enterOtp).sendKeys(otp);	
	}


	public void clickGoogleButton() 
	{
		click(androidDriver.findElement(By.id("com.healthkart.healthkart:id/fsu_google")));
	}


	public void selectGoogleAccount() 
	{
		click(androidDriver.findElement(googleAccount));
	}


	public void clickFacebookButton() 
	{
		click(androidDriver.findElement(facebookAccount));
	}

	public void enterFacebookIdPassword(String fbUserName, String fbPassword) 
	{
		try
		{
			androidDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			WebElement facebookLogo = androidDriver.findElementByAccessibilityId("facebook");
			if(facebookLogo.isDisplayed())
			{
				sendKeys(androidDriver.findElement(facebookUsernameBy), fbUserName);
				androidDriver.hideKeyboard();
				sendKeys(androidDriver.findElement(facebookPasswordBy), fbPassword);
				androidDriver.hideKeyboard();
				WebElement fbLoginButton = androidDriver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Log In \"]"));
				click(fbLoginButton);
				confirmFacebookLogin();
			}
		}
		catch(Exception e)
		{
			androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
			System.out.println("Facebook Already logged in");
		}
		finally
		{
			androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		}
		
	}
	
	public void confirmFacebookLogin() 
	{
		androidDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try
		{
		if(androidDriver.findElement(facebookContinue).isDisplayed())
			click(androidDriver.findElement(facebookContinue));
		}
		catch(Exception e)
		{
			System.out.println("Continue was not displayed");
		}
		finally
		{
			androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		}
	}


	public void clickSubmitSignUp() 
	{
		click(androidDriver.findElement(signUpButtonBy));
	}


	public void clickTrueCallerButton() 
	{
		clickBy(trueCallerButtonBy);
	}


	public void continueTrueCaller() 
	{
		clickBy(truecallerVerifyBy);		
	}


	public void waitForSignUpButton() 
	{
		waitForElementToBeDisplayedBy(androidDriver, 30, passwordField);
	}
	
	public void waitForTopLeftNavigationButton()
	{
		waitForElementToBePresent(androidDriver, 90, topLeftNavigationButton);
	}
}
