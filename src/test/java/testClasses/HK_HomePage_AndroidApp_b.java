package testClasses;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class HK_HomePage_AndroidApp_b extends HK_HomePage_AndroidApp_p
{
	public HK_HomePage_AndroidApp_b(AndroidDriver<WebElement> androidDriver)
	{
		super(androidDriver);
	}
	

	public void performLoginWithMobilePassword(String mobileNumber, String password) 
	{
		clickTopLeftNavigationButton();
		clickLoginButton();
	}
	
}
