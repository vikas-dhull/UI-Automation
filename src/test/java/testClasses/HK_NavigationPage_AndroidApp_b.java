package testClasses;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class HK_NavigationPage_AndroidApp_b extends HK_NavigationPage_AndroidApp_p
{
	public HK_NavigationPage_AndroidApp_b(AndroidDriver<WebElement> androidDriver) 
	{
		super(androidDriver);
	}

	public void login()
	{
		swipeNavigationPage();
	}
	
}
