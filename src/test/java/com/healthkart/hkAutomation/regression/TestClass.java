package com.healthkart.hkAutomation.regression;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class TestClass{
	
/*	@Test
	public void test() throws MalformedURLException 
	{
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    	String path = System.getProperty("user.dir") + "/app/healthkart_prod.apk";
    	desiredCapabilities.setCapability("deviceName", "Android");
    	desiredCapabilities.setCapability("platformVersion", "7.1.1");
    	desiredCapabilities.setCapability("app", path);
    	desiredCapabilities.setCapability("platformName", "Android");
    	desiredCapabilities.setCapability("appPackage", "com.healthkart.healthkart");
    	desiredCapabilities.setCapability("appActivity", "com.healthkart.healthkart.Splash");
        	AndroidDriver<MobileElement> androidDriver = new AndroidDriver<MobileElement>(
                    new URL("http://127.0.0.1:4723/wd/hub"), 
                    desiredCapabilities);
        	TouchAction touch = new TouchAction(androidDriver);
    		Dimension dimensions = androidDriver.manage().window().getSize();
    		int width = dimensions.getWidth();
    		touch.longPress(androidDriver.findElementById("com.healthkart.healthkart:id/imageViewFragment")).moveTo(width, 0).release().perform();
} */
	
	/*@Test
	public void test() throws InterruptedException {		
		
		String orderId=null;
		int substringIndex=0;
		
			orderId="Order Id: HKD-04331-7059274";
			System.out.println("order Id element text : " + orderId);			
			
		       char[] array= orderId.toCharArray();
		       int arrayIndex = 0;
		       for(char c: array){
		    	   Thread.sleep(1000);
		           System.out.print(c);
		           if(c=='H') {
		        	   substringIndex=arrayIndex;
		        	   System.out.println("Sub String Index :" + substringIndex);
		        	   break;
		           }
		           arrayIndex++;
		       }
		
		System.out.println("final string :"+ orderId.substring(substringIndex));
		

	}*/
	
	@Test
	public void Test1()
	{
		Assert.assertEquals(true, true);
	}
 
	@Test
	public void Test2()
	{
		Assert.assertEquals(true, true);
	}

}
