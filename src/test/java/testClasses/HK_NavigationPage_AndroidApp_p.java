package testClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.healthkart.hkAutomation.browser.BrowserFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class HK_NavigationPage_AndroidApp_p 
{
	public AndroidDriver<WebElement> androidDriver;
	
	public HK_NavigationPage_AndroidApp_p(AndroidDriver<WebElement> androidDriver) 
	{	
		this.androidDriver = androidDriver;
	}


	private By loginButton = By.id("com.healthkart.healthkart:id/loginBtnOB");
	private By tourPage = By.id("com.healthkart.healthkart:id/indicator");

	public void swipeNavigationPage()
	{
		androidDriver = BrowserFactory.getAndroidDriver();
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(androidDriver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(tourPage));
		
		while(androidDriver.findElements(loginButton).isEmpty())
		{		
			Dimension size = androidDriver.manage().window().getSize();
			int startx = (int) (size.width * 0.70);
			int endx = (int) (size.width * 0.01);
			int starty = size.height / 2;
		  	System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);
		 // 	androidDriver.swipe(startx, starty, endx, starty, 3000);
		}  	
//		androidDriver.findElement(loginButton).click();
	
}
}
