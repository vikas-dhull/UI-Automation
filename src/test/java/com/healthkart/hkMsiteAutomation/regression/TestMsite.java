package com.healthkart.hkMsiteAutomation.regression;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.hkAutomation.property.PropertyHelper;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestMsite 
{
	
	@Test
	public void Test3()
	{
		Assert.assertEquals(true, true);
	}
 
	@Test
	public void Test4()
	{
		Assert.assertEquals(true, true);
	}
	
	/*public static void main(String args[]) throws MalformedURLException
	{
		
		// Create object of  DesiredCapabilities class and specify android platform
		DesiredCapabilities capabilities=DesiredCapabilities.android();
		 
		 
		// set the capability to execute test in chrome browser
		 capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
		 
		// set the capability to execute our test in Android Platform
		   capabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);
		 
		// we need to define platform name
		  capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		 
		// Set the device name as well (you can give any name)
		 capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"my phone");
		 
		 // set the android version as well 
		   capabilities.setCapability(MobileCapabilityType.VERSION,"5.0.1");
		   
           System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ PropertyHelper.readProperty("chromeDriver"));
       	
		   ChromeOptions options = new ChromeOptions();
		   Map<String, Object> prefs = new HashMap<String, Object>();
		   prefs.put("credentials_enable_service", false);
		   prefs.put("profile.password_manager_enabled", false);
		   options.setExperimentalOption("prefs", prefs);
		 
		   capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		   
		 // Create object of URL class and specify the appium server address
		 URL url= new URL("http://127.0.0.1:4723/wd/hub");
		 
		// Create object of  AndroidDriver class and pass the url and capability that we created
		 WebDriver driver = new AndroidDriver(url, capabilities);
		 
		// Open url
		  driver.get("http://staging.healthkart.com");
		  
		  Set<String> contexts = driver.getContextHandles();
		  for(String context :contexts)
		  {
			  if(context.equals("NATIVE_APP"))
				  androidDriver.context(context);
		  }
		  MobileElement element = driver.findElement(By.id("com.android.chrome:id/button_primary"));
		  element.tap(1, 1);
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("browserName", "chrome");
		desiredCapabilities.setCapability("platformVersion", "5.1");
		desiredCapabilities.setCapability("platformName", "ANDROID");
		desiredCapabilities.setCapability("app", "C:/Chrome.apk");
		desiredCapabilities.setCapability("appActivity", "com.google.android.apps.chrome");
		desiredCapabilities.setCapability("appPackage", "com.android.chrome");
		desiredCapabilities.setCapability("deviceName", "ANDROID");
		
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-notifications");
		options.addArguments("----disable-popup-blocking");
		
		desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		AndroidDriver<MobileElement> androidDriver = new AndroidDriver<MobileElement>(
                new URL("http://127.0.0.1:4723/wd/hub"), 
                desiredCapabilities);
		androidDriver.get("http://healthkart.com");
	}*/

}
//browserName=chrome
//platformVersion=5.1
//platformName=ANDROID
//app=Chrome.apk
//appActivity=com.google.android.apps.chrome
//appPackage=com.android.chrome
//deviceName=ANDROID
//newCommandTimeout=250
//baseURL=http://127.0.0.1:4723/wd/hub
//defaultWaitTime=60
//#url=http://m.gofro.com
//url=http://healthkart.com
//fullReset=false
//noReset=false
//disable-popup-blocking=true
//autoDismissAlerts=true
//locationServicesAuthorized=true
//autoAcceptAlerts=true