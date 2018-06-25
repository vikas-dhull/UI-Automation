package testClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.hkAutomation.property.PropertyHelper;

public class TestM_Desktop 
{
	public static void main(String args[])
	{
		WebDriver driver;
		ChromeOptions ops = new ChromeOptions();
	    ops.addArguments("--disable-notifications");
	 //   driver.manage().deleteAllCookies();
	    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ PropertyHelper.readProperty("chromeDriver"));
	    driver = new ChromeDriver(ops);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    driver.get("https://staging.healthkart.com/?view=mobile");
	    driver.findElement(By.xpath("//div[contains(@class,'header-sprite-user')]")).click();
	    
	}
	
	/*public static void main(String args[])
	{
		 String s="/test-output/ExtentReportsMsite/ChromeMsite/Screenshots/1/2";  
		   System.out.println(s.substring(s.indexOf("Screenshots")));
	}*/
	
	
	/*@Test
	public void Test1()
	{
		Assert.assertEquals(false, true);
	}
 
	@Test
	public void Test2()
	{
		Assert.assertEquals(true, true);
	}*/
	
}
