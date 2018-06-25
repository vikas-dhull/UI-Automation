package testClasses;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestM {
	

		public static AndroidDriver<WebElement> driver;
		public static AppiumDriver<WebElement> _driver;

		@BeforeTest
		public void startAppium() throws MalformedURLException, InterruptedException{
			System.out.println("setUP() :: driver.AndroidDriver() executed");
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
			   
			driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4725/wd/hub"),capabilities);
		}

		@Test
		public void AppLogin() throws InterruptedException
		
		{
			System.out.println("AppLogin() :: driver.start() executed");
			By webView = By.className("android.webkit.WebView");
			By title = By.id("android:id/title");
			WebDriverWait wait = new WebDriverWait(driver,300);
			driver.findElement(title).getText();
			Set<String> availableContexts1 = driver.getContextHandles();
			System.out.println("Total No of Context Found Before reaching WebView = "+ availableContexts1.size());
			System.out.println("Context Name is "+ availableContexts1);

			//4.1 Navigate to a portion of your app where a web view is active
			driver.findElement(By.id("com.mkyong.android:id/buttonUrl")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(webView));
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

			int size=driver.findElements(By.xpath("//select")).get(0).findElements(By.xpath("//option")).size();
			System.out.println("No of Elements in dropdown "+ size);
			WebElement car = driver.findElement(By.name("car"));
			Select preferedCar=new Select(car);
			preferedCar.selectByIndex(2);
			System.out.println("Button Value is : " + driver.findElement(By.xpath("/html/body/form/div/input[2]")).getAttribute("value"));
			//Key code constant: Back key.
			//Constant Value: 4 (0x00000004)
//			driver.sendKeyEvent(4);
			// 4.4 To stop automating in the web view context we can simply call the context again with id NATIVE_APP.
			for(String context : availableContexts) {
				if(context.contains("NATIVE")){
				System.out.println("We are Back to " + context);
				driver.context(context);
					if (driver.findElement(title).getText().equals("WebViewApp"))
					System.out.println("Context Switched");
				}
			}
		}

		@AfterTest(alwaysRun= true)
		public void tearDown(){
			driver.quit();
			System.out.println("tearDown() :: driver.quit() executed");
		}
	} // end of class


