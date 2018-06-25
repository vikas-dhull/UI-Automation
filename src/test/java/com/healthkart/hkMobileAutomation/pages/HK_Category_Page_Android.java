package com.healthkart.hkMobileAutomation.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class HK_Category_Page_Android extends CommonFunctions
{
	AndroidDriver<?> androidDriver;
	public HK_Category_Page_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
	}

	private By buyNowButton = By.id("com.healthkart.healthkart:id/buy_now_btn");
	private By pricesBy = By.xpath("//android.widget.TextView[@resource-id='com.healthkart.healthkart:id/price']");
	private By sortButtonBy = By.id("com.healthkart.healthkart:id/fl_sort_layout");
	private By filterButtonBy = By.id("com.healthkart.healthkart:id/fl_filter_layout");
//	private By

	public void clickBuyNow() 
	{
		click(androidDriver.findElement(buyNowButton));
	}



	public void clickSortListing(String sortByType) 
	{
		clickBy(sortButtonBy);
		String xpathSortType = "//android.widget.TextView[@text='" + sortByType + "']";
		WebElement sortElement = androidDriver.findElement(By.xpath(xpathSortType));
		click(sortElement);
	}



	@SuppressWarnings("unchecked")
	public List<WebElement> listOfPrice() 
	{
		String xpath;
		String productName="a",lastProductName="x";
		
		boolean flag = false;
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		while(!productName.equals(lastProductName))
		{
			try
			{
				/*flag = androidDriver.findElement(By.xpath(xpath)).isDisplayed();
				flag=true;*/
				
				xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.healthkart.healthkart:id/fl_recyclerView']//android.widget.LinearLayout[@index=3]//android.widget.TextView[@resource-id='com.healthkart.healthkart:id/name']";
				List<?> elements = androidDriver.findElements(By.xpath(xpath));
				System.out.println(elements.size());
				System.out.println(elements);
				
				if(elements.size()==0)
				{
					break;
				}
				WebElement element = androidDriver.findElement(By.xpath(xpath));
				productName = element.getText();
				Dimension size = androidDriver.manage().window().getSize();
				int starty = (int) (size.height * 0.90);
				int endy = (int) (size.height * 0.01);
				int startx = size.width / 2,endx = size.width / 2;
			  	System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty + ", end y = " + endy);
			  	
			  	TouchAction action = new TouchAction(androidDriver);
				action.press(startx, starty).waitAction(java.time.Duration.ofMillis(1000)).moveTo(startx, endy).release().perform();
			  	
			  /*	androidDriver.swipe(startx, starty, endx, endy, 3000);*/
			  	element = androidDriver.findElement(By.xpath(xpath));
			  	lastProductName = element.getText();
			  	flag=false;
			}
			catch(NoSuchElementException e)
			{
				
//				staticWait(4);
				flag=true;
			}
			
				androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
			
		}
		return (List<WebElement>) androidDriver.findElements(pricesBy);
	}



	public void selectProduct(String productName, String size, String Flavour) 
	{
		/*WebElement sourceElement = androidDriver.findElementByAndroidUIAutomator("textContains(\"MuscleBlaze Whey Gold\")");
		WebElement targetElement = androidDriver.findElementByAndroidUIAutomator("textContains(\"MuscleBlaze Whey Protein,\")");
		int x = sourceElement.getLocation().getX();
		int y = sourceElement.getLocation().getY();
		int x1 = targetElement.getLocation().getX();
		int y1 = targetElement.getLocation().getY();
		TouchAction action = new TouchAction(androidDriver);
		action.press(x1, y1).waitAction(java.time.Duration.ofMillis(1000)).moveTo(x, y).release().perform();
		androidDriver.findElementByAndroidUIAutomator("textContains(\"MuscleBlaze Whey Protein,\")").click();*/
		staticWait(5);
		By targetPrdctElementBy = By.xpath("//android.widget.TextView[contains(@text,'"+productName+"') and contains(@text,'"+size+"') and contains(@text,'"+Flavour+"')]");
		scrollAndSwipeByElementAndroid(androidDriver, targetPrdctElementBy, "up");
		clickBy(targetPrdctElementBy);
	}



	public void clickFilterButton() 
	{
		clickBy(filterButtonBy);
	}



	public void selectFilter(String filterName) 
	{
		String filterXpath = "//android.widget.TextView[@resource-id='com.healthkart.healthkart:id/fnl_name' and @text='" + filterName + "']";
		clickBy(By.xpath(filterXpath));
		
	}



	public void clickSubFilter(String subFilter) 
	{
		click(androidDriver.findElementByAccessibilityId(subFilter));
	}



	public void clickApplyFiter() 
	{
//		click(androidDriver.findElement());
	}
		

}
