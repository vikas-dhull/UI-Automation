package testClasses;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestMsite2 {
	WebDriver driver;
	WebDriverWait wait;
	String AppURL = "http://m.facebook.com";

	@BeforeTest
	public void setup() throws MalformedURLException {

		// Create an object for Desired Capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();

		// Name of mobile web browser to automate. ‘Safari’ for iOS and ‘Chrome’
		// or ‘Browser’ for Android
		capabilities.setCapability("browsername", "chrome");

		// The kind of mobile device or emulator to use - iPad Simulator, iPhone
		// Retina 4-inch, Android Emulator, Galaxy S4 etc
		capabilities.setCapability("deviceName", "Android");

		// Which mobile OS platform to use - iOS, Android, or FirefoxOS
		capabilities.setCapability("platformName", "Android");

		// Java package of the Android app you want to run- Ex:
		// com.example.android.myApp
		capabilities.setCapability("appPackage", "com.android.chrome");

		// Activity name for the Android activity you want to launch from your
		// package
		capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");

		// Initialize the driver object with the URL to Appium Server and
		// passing the capabilities
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		wait = new WebDriverWait(driver, 5);
	}

	@Test
	public void testSearchAppium() {
		driver.get(AppURL);
		driver.findElement(By.name("email")).sendKeys("mukulchuttani");
		driver.findElement(By.name("pass")).sendKeys("pass");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}