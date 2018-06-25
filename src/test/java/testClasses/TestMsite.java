package testClasses;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.healthkart.hkAutomation.property.PropertyHelper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestMsite 
{
	public static void main(String args[]) throws MalformedURLException
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
           options.addArguments("--disable-notifications");
		   Map<String, Object> prefs = new HashMap<String, Object>();
		   prefs.put("credentials_enable_service", false);
		   prefs.put("profile.password_manager_enabled", false);
		   options.setExperimentalOption("prefs", prefs);
		 
		   capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		   
		 // Create object of URL class and specify the appium server address
		 URL url= new URL("http://127.0.0.1:4723/wd/hub");
		 
		// Create object of  AndroidDriver class and pass the url and capability that we created
		 WebDriver driver = new AndroidDriver(url, capabilities);
		 AndroidDriver androidDriver = (AndroidDriver) driver;
		 
		// Open url
		  driver.get("http://staging.healthkart.com");
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  
		  Set<String> contexts = androidDriver.getContextHandles();
		  for(String context :contexts)
		  {
			  if(context.equals("NATIVE_APP"))
				  androidDriver.context(context);
		  }
		  
//		  WebElement element = driver.findElement(By.id("com.android.chrome:id/button_primary"));
		  WebElement element = driver.findElement(By.xpath("//android.widget.Button[@text='Allow']"));
		  while(element!=null)
		  {
			  TouchAction action = new TouchAction(androidDriver);
			  action.tap(element).perform();
			  try
			  {
				  element = driver.findElement(By.xpath("//android.widget.Button[@text='Allow']"));
			  }
			  catch(NoSuchElementException e)
			  {
				  break;
			  }
		  }
		  
		  System.out.println("Finished");
		  
		  for(String context :contexts)
		  {
			  if(context.contains("CHROMIUM"))
				  androidDriver.context(context);
		  }
/*		  element = driver.findElement(By.xpath("//android.widget.Image[@content-desc='close']"));
		  element.click();*/
		  
		  
		  
	/*	  element = androidDriver.findElement(By.xpath("//div[@class='geoLocBoxInner']//img"));
		  element.click();*/
		  
		  /*List<WebElement> elements = driver.findElements(By.xpath("//div[@class='login-box-inner'][1]//div[@id='gSignInWrapper']"));
		  elements.get(1).click();*/
		  
		  
		  driver.findElement(By.id("userMobNoGeoLoc")).sendKeys("9552360289");
		  driver.findElement(By.id("getMobNoGeoLoc")).click();
		 
		  
		
		  	
		
		  
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
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
		androidDriver.get("http://healthkart.com");*/
	}

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