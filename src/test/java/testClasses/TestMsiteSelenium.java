package testClasses;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

/*public class TestMsiteSelenium 
{
	public static void main(String args[]) throws MalformedURLException
	{
		DesiredCapabilities  capabilities = new DesiredCapabilities();
		 ChromeOptions options=new ChromeOptions();
		    options.setExperimentalOption("androidPackage", "com.android.chrome");
		    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	    capabilities.setCapability("device","Android");
	    capabilities.setCapability("app", "Chrome");
	    capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
	    capabilities.setCapability(CapabilityType.VERSION, "5.1");
	    capabilities.setCapability(CapabilityType.PLATFORM, "Android");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"my phone");
	    capabilities.setCapability("appPackage", "com.android.chrome");
	    capabilities.setCapability("app", "C:/Chrome.apk");

	    
	    AndroidDriver driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	    driver.get("http://staging.healthkart.com");
	    WebElement element = driver.findElement(By.xpath("//div[@id='nvpush_cross']"));
	    element.click();
		element = driver.findElement(By.xpath("//img[@class='user-icon showLoginPopup']"));
	    element.click();
	    element = driver.findElement(By.id("email"));
	    JavascriptExecutor executor = (JavascriptExecutor)driver;
	    executor.executeScript("document.getElementById('email').setAttribute('value', '7897897890')");
//	    element.sendKeys("7897897890");
	    element = driver.findElement(By.id("password"));
//	    element.sendKeys("vikas");
	    executor.executeScript("document.getElementById('password').setAttribute('value', 'vikas')");
	    element = driver.findElement(By.xpath("//div[@id='tab-1-content']//p[contains(text(),'Continue with')]"));
	    
	    Actions action = new Actions(driver);
	    action.moveToElement(element).build().perform();
	    element = driver.findElement(By.xpath("//div[@id='tab-1-content']//input[@class='submit-btn']"));
	    element.click();
	    element = driver.findElement(By.xpath("//h2[@class='my-account-title']"));
	    
	    System.out.println(element.isDisplayed());
		
		By xpath = By.xpath("asdf");
		Object ob = xpath;
		System.out.println(ob);
	}

}*/


public class TestMsiteSelenium {
	public static AndroidDriver<WebElement> driver;
	//public static AppiumDriver<WebElement> _driver;
	public static DesiredCapabilities cap = new DesiredCapabilities();
 
	@BeforeTest
	public void startAppium() throws MalformedURLException, InterruptedException{
		System.out.println("setUP() :: driver.AndroidDriver() executed");
		cap.setCapability("platformVersion","6.0.1");
		cap.setCapability("platformName","Android");
		cap.setCapability("deviceName","520320d9e83363f5");
		cap.setCapability("app","C:\\Users\\vikas.dhull\\Downloads\\Gmail.apk");
		cap.setCapability("appActivity", "com.google.android.gm.ConversationListActivityGmail");
		cap.setCapability("appPackage", "com.google.android.gm");
		cap.setCapability("automationName","uiautomator2");
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4721/wd/hub"),cap);
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	}
 
	@Test
	public void AppLogin() throws InterruptedException{
		System.out.println("AppLogin() :: driver.start() executed");
		By gotit = By.id("com.google.android.gm:id/welcome_tour_got_it");
		By takemetogamil = By.id("com.google.android.gm:id/action_done");
		By leftPaneBtn = By.xpath("	//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]");
		By sentBtn = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[8]/android.widget.LinearLayout");
		By targetMail = By.xpath("//android.view.View[contains(@content-desc,'Staging url')]");
		By url = By.xpath("//android.view.View[@text='staging.healthkart.com']");
		
		By webView = By.className("android.webkit.WebView");
		By title = By.id("android:id/title");
		
		driver.findElement(gotit).click();
		WebDriverUtil.staticWait(4);
		driver.findElement(takemetogamil).click();
		WebDriverUtil.staticWait(1);
		driver.findElement(leftPaneBtn).click();
		WebDriverUtil.staticWait(1);
		driver.findElement(sentBtn).click();
		WebDriverUtil.staticWait(1);
		driver.findElement(targetMail).click();
		driver.findElement(url).click();
		
		Set<String> availableContexts1 = driver.getContextHandles();
		System.out.println("Total No of Context Found Before reaching WebView = "+ availableContexts1.size());
		System.out.println("Context Name is "+ availableContexts1);
 
		//4.1 Navigate to a portion of your app where a web view is active
		driver.findElement(By.id("com.mkyong.android:id/buttonUrl")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(webView));
		// 4.2 Call getContext() method which will returns a list of contexts we can access, like 'NATIVE_APP' or 'WEBVIEW_1'
		Set<String> availableContexts = driver.getContextHandles();
		System.out.println("Total No of Context Found After we reach to WebView = "+ availableContexts.size());
		for(String context : availableContexts) {
			if(context.contains("WEBVIEW")){
				System.out.println("Context Name is " + context);
				// 4.3 Call context() method with the id of the context you want to access and change it to WEBVIEW_1
				//(This puts Appium session into a mode where all commands are interpreted as being intended for automating the web view)
				driver.context(context);
				break;
			}
		}
		String input_box_text = driver.findElement(By.id("name_input")).getAttribute("value");
		System.out.println("Pre written text inside text box is " + input_box_text);
		driver.findElement(By.id("name_input")).clear();
		driver.findElement(By.id("name_input")).sendKeys("Amit Jain"); System.out.println("No of dropdown on page "+ driver.findElements(By.xpath("//select")).size());
 
		/*int size=driver.findElements(By.xpath("//select")).get(0).findElements(By.xpath("//option")).size();
		System.out.println("No of Elements in dropdown "+ size);
		WebElement car = driver.findElement(By.name("car"));
		Select preferedCar=new Select(car);
		preferedCar.selectByIndex(2);
		System.out.println("Button Value is : " + driver.findElement(By.xpath("/html/body/form/div/input[2]")).getAttribute("value"));*/
		//Key code constant: Back key.
		//Constant Value: 4 (0x00000004)
		//driver.sendKeyEvent(4);
		// 4.4 To stop automating in the web view context we can simply call the context again with id NATIVE_APP.
		/*for(String context : availableContexts) {
			if(context.contains("NATIVE")){
			System.out.println("We are Back to " + context);
			driver.context(context);
				if (driver.findElement(title).getText().equals("WebViewApp"))
				System.out.println("Context Switched");
			}
		}*/
	}
 
	/*@AfterTest(alwaysRun= true)
	public void tearDown(){
		driver.quit();
		System.out.println("tearDown() :: driver.quit() executed");
	}*/
} // end of class
