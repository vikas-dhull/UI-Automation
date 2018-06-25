package testClasses;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class HK_HomePage_Msite_b extends HK_HomePage_Msite_p
{
	public HK_HomePage_Msite_b(AndroidDriver<WebElement> androidDriver)
	{
		super(androidDriver);
	}
	
	public void login()
	{
		navigateToHealthKart();
		closeAllowButton();
	}
	
}
