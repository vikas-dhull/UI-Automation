package testClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.browser.BrowserFactory;
import com.healthkart.hkAutomation.util.CommonFunctions;

import io.appium.java_client.android.AndroidDriver;


public class HK_HomePage_Msite_p extends CommonFunctions
{
	public AndroidDriver<WebElement> androidDriver;
	public WebDriver driver;
	
	public HK_HomePage_Msite_p(AndroidDriver<WebElement> androidDriver) 
	{	
		this.androidDriver = androidDriver;
	}


	private By closeAllowButton = By.xpath("//div[@id='nvpush_cross']");
	private By userLoginButton = By.xpath("//img[@class='user-icon showLoginPopup']");
	
	public void navigateToHealthKart()
	{
		androidDriver = BrowserFactory.getAndroidDriver();
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		androidDriver.get("http://healthkart.com");
	}
	
	
	public void closeAllowButton()
	{
		
		click(androidDriver.findElement(closeAllowButton));
	}
	
	public void clickUserLoginButton()
	{
		click(androidDriver.findElement(userLoginButton));
	}
	
	
}
