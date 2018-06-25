package com.healthkart.hkMobileAutomation.pages;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import io.appium.java_client.android.AndroidDriver;

public class HK_NavigationPage_AndroidApp 
{
	public AndroidDriver<?> androidDriver;
	CommonFunctions common;
	
	public HK_NavigationPage_AndroidApp(AndroidDriver<?> androidDriver) 
	{	
		this.androidDriver = androidDriver;
		common = new CommonFunctions();
	}


	private By loginButton = By.id("com.healthkart.healthkart:id/loginBtnOB");
	private By tourPage = By.id("com.healthkart.healthkart:id/indicator");
	private By skipButtonBy = By.id("com.healthkart.healthkart:id/textSkip");
	private By topLeftNavigationButtonBy = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");

	public void swipeNavigationPage()
	{
		try
		{
			androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(androidDriver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(tourPage));
			boolean flag = true;
			try
			{
				flag = !androidDriver.findElement(topLeftNavigationButtonBy).isDisplayed();
			}
			catch(Exception eParent)
			{
				if(flag)
				{
					common.scrollAndSwipeByElementAndroid(androidDriver, loginButton, "left");
					androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
					try
					{
						androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						common.clickBy(skipButtonBy);
					}
					catch(Exception e)
					{
						common.reportLogAndPrintInConsole("Skip was not displayed");
					}
					finally
					{
						androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
					}
				}
			}
			
		}
		catch(Exception e)
		{
			common.reportLogAndPrintInConsole("Failed to swipe Navigation Page");
		}
}
}
