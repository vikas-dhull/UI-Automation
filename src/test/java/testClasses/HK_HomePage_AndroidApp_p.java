package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.util.CommonFunctions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class HK_HomePage_AndroidApp_p extends CommonFunctions
{
	public AndroidDriver<WebElement> androidDriver;
	
	public HK_HomePage_AndroidApp_p(AndroidDriver<WebElement> androidDriver) 
	{	
		this.androidDriver = androidDriver;
	}


	private By topLeftNavigationButton = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");
	private By loginButtonInLeftNavigation = By.id("com.healthkart.healthkart:id/hmm_login");
	private By mobileNumberField = By.id("com.healthkart.healthkart:id/hmm_login");
	
	
	public void clickTopLeftNavigationButton()
	{
		click(androidDriver.findElement(topLeftNavigationButton));
	}
	
	public void clickLoginButton()
	{
		click(androidDriver.findElement(loginButtonInLeftNavigation));
	}
	
	public void enterMobileNumber(String mobileNumber)
	{
		androidDriver.findElement(mobileNumberField).sendKeys(mobileNumber);
	}
	
	
}
