package testClasses;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.healthkart.hkAutomation.browser.BrowserFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class HK_TestLoginWithGmail_AndroidApp
{
	WebDriver driver;
	AndroidDriver<WebElement> androidDriver;
	HK_NavigationPage_AndroidApp_b navigationB;
	HK_HomePage_AndroidApp_b homeB;
	HK_LoginPage_AndroidApp_b loginB;
    
	@Parameters({"flavour","browserOrDevice"})
	@BeforeTest
	public void setup(String flavour, String browserOrDevice) throws IOException 
	{
		System.out.println("Flavour ::" + flavour + " Device ::" + browserOrDevice);
		driver = BrowserFactory.launchBrowser(flavour,browserOrDevice);
		androidDriver = BrowserFactory.getAndroidDriver();
		navigationB = new HK_NavigationPage_AndroidApp_b(androidDriver);	
		homeB = new HK_HomePage_AndroidApp_b(androidDriver);
		loginB = new HK_LoginPage_AndroidApp_b(androidDriver);
	}
	
	@Parameters({"browserOrDevice"})
	@Test()
	public void testLoginWithMobilePassword(String browserOrDevice)
	{	
		homeB.clickTopLeftNavigationButton();
	}

	
		
	}


