package com.healthkart.hkAutomation.util;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.healthkart.hkAutomation.browser.BrowserFactory;
import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class WebDriverUtil {
	
	/**
	 * Handle notify visitor[For PROD ENV only.]
	 */
	public static GenericDbActions dbActionsObj = new GenericDbActions();
	public static CommonFunctions common = new CommonFunctions();
	//private static By closeNotifyVisitorOverlay = By.xpath("//a[@id='nv_js-modal-close-button']");
	//private static By closeNotifyVisitorOverlay = By.xpath("//a[contains(@id,'-close-button')][1]");
	
	private static By closeNotifyVisitorOverlay = By.xpath("//a[contains(@id,'-close-button') or contains(@id,'-notification-close-div')][1]");
		
	public static void clickCloseNotifyVisitorOverlay(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement element=null;
		String elementName = "closeNotifyVisitorOverlay";
		try {
			element = driver.findElement(closeNotifyVisitorOverlay);
			if(WebDriverUtil.isElementClickable(element, driver,2))
				element.click();
		} catch(Throwable e){
			System.out.println("WebElement not found : " +elementName);
			try {
				driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
				element = driver.findElement(closeNotifyVisitorOverlay);
				if(WebDriverUtil.isElementClickable(element, driver,2))
					element.click();
				
			}catch(Throwable e1) {
				System.out.println("WebElement not found in iframe : " +elementName);
			}
		}
		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		/*WebDriverUtil.click(driver, closeNotifyVisitorOverlay, "closeNotifyVisitorOverlay");
	    if(WebDriverUtil.isElementClickable(closeNotifyVisitorOverlay, driver,20))
	       	closeNotifyVisitorOverlay.click();*/
	}
	
	public static void waitForLoad(WebDriver driver, int seconds) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(pageLoadCondition);
    }
	
	public static void mouseHoverToElement(WebDriver driver, WebElement element) {
		
		try {
		
				if(!(driver instanceof SafariDriver)) {
					Actions builder = new Actions(driver);
					builder.moveToElement(element).build().perform();
				}
				else {
					
					Robot rbt = new Robot();
					
					int xto = (element.getLocation().x);
					int yto = (element.getLocation().y);
					
					System.out.println("x coordinates :" + xto + " y coordinates :" + yto);
					
					rbt.mouseMove(xto+5, yto + 70);
					rbt.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					rbt.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					
					String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',"
							+ "true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
							((JavascriptExecutor) driver).executeScript(mouseOverScript,element);
									
				}
		}
		catch (StaleElementReferenceException e) {
			System.out.println("Element with " + element
					+ "is not attached to the page document"
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM"
					+ e.getStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while hovering"
					+ e.getStackTrace());
		}
	}

	public static boolean isElementPresent(WebElement webElement, WebDriver drvr, int seconds) {
		boolean flag = false;
		try {
			waitForElementToBeDisplayed(drvr,seconds, webElement);
			if(webElement.isDisplayed())
				flag = true;
		}
		catch(Throwable e){
			System.out.println("WebElement not found : " +webElement + " : "  + e.getMessage());
		}
		return flag;
		
	}
	
	public static boolean isElementPresentBy(By by, WebDriver drvr) {
		boolean flag = false;
		Wait wait = new FluentWait(drvr)
				.withTimeout(20, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			if(drvr.findElement(by).isDisplayed())
				flag = true;
		}
		catch(Throwable e){
			System.out.println("WebElement not found : " +by + " : "  + e.getMessage());
		}
		return flag;
		
	}
	
	public static boolean isElementClickable(WebElement webElement, WebDriver drvr, int seconds) {
		boolean flag = false;
		
		try {
			waitForElementToBeClickable(drvr,seconds, webElement);
			if(webElement.isDisplayed())
				flag = true;
		}
		catch(Throwable e){
			System.out.println("WebElement not found to be clicked : " +webElement + " : "  + e.getMessage());
		}
		return flag;
		
	}
	
	public static boolean waitForWindowCount(WebDriver drvr, int count) {
		boolean flag = false;
		Wait wait = new FluentWait(drvr)
				.withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		try {
			 // wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			staticWait(4);
			if(count==drvr.getWindowHandles().size()) {
				System.out.println("Window count found as : " + drvr.getWindowHandles().size());
				flag = true;
			}
		}
		catch(Throwable e){
			System.out.println("Window count found as : " + drvr.getWindowHandles().size() + " : "  + e.getMessage());
		}
		return flag;
	}
	
	public static void mouseHoverAndClick(WebDriver driver, WebElement element, String elementName) {
		try {
			if(isElementPresent(element, driver, 20)) {
				mouseHoverToElement(driver,element);
				if(isElementClickable(element, driver, 5)) {
					reportLogAndPrintInConsole("Clicking WebElement :: " + elementName );
					element.click();
				}
				else {
					System.out.println("WebElement Not Found Clickable : "+elementName);
				}
			}
			else
			{
				System.out.println("WebElement not visible in DOM : "+elementName);
			}
		} catch (org.openqa.selenium.TimeoutException te) {
			reportLogAndPrintInConsole("@@@@..PAGE LOAD TIMEOUT EXCEPTION OCCURED AFTER MOUSE HOVER & CLICKING..@@@@" + elementName );
			//((JavascriptExecutor)driver).executeScript("window.stop();");
		}
		/*waitForElementToBeDisplayed(driver, 20, element);
		mouseHoverToElement(driver,element);
		waitForElementToBeClickable(driver, 5, element);
		reportLogAndPrintInConsole("Clicking WebElement :: " + elementName );
		element.click();*/
	}
	
	public static void click(WebDriver driver, WebElement element, String elementName) {
		try {
			if(isElementPresent(element, driver, 20)) {
				if(isElementClickable(element, driver, 5)) {
					reportLogAndPrintInConsole("Clicking WebElement :: " + elementName );
					element.click();
				}
				else {
					reportLogAndPrintInConsole("WebElement Not Found Clickable : "+elementName);
				}
			}
			else
			{
				reportLogAndPrintInConsole("WebElement not visible in DOM : "+elementName);
			}
		} catch (org.openqa.selenium.TimeoutException te) {
			reportLogAndPrintInConsole("@@@@..PAGE LOAD TIMEOUT EXCEPTION OCCURED AFTER CLICKING..@@@@" + elementName );
			//((JavascriptExecutor)driver).executeScript("window.stop();");
		}		
		/*waitForElementToBeDisplayed(driver, 20, element);
		waitForElementToBeClickable(driver, 5, element);
		reportLogAndPrintInConsole("Clicking WebElement :: " + elementName );
		element.click();
		common.click(element);*/
	}
	
	public static void reportLogAndPrintInConsole(String message)
    {
    	System.out.println(message);
    	GlobalVar.test.log(LogStatus.INFO, message);
    }
	
	public static void scrollDownTillEnd(WebDriver driver)
    {
		reportLogAndPrintInConsole("Scrolling window till end..");
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("window.scrollTo(0, document.body.scrollHeight)", "");
    }
    
    public static void scrollToObject(WebDriver driver, WebElement element)
    {
    	reportLogAndPrintInConsole("Scrolling window till object..");
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].scrollIntoView(true);",element);
    }
    
    public static void scrollTillTop(WebDriver driver)
    {
    	reportLogAndPrintInConsole("Scrolling window till top..");
    	 JavascriptExecutor jse = (JavascriptExecutor) driver;
    	 jse.executeScript("window.scrollBy(0,-250)", "");
    }
    
    public static void waitForElementToBeDisplayed(WebDriver driver, int seconds, WebElement element)
    {
    	WebDriverWait wait = new WebDriverWait(driver, seconds);
    	wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public static void waitForElementToBeClickable(WebDriver driver, int seconds, WebElement element)
    {
    	WebDriverWait wait = new WebDriverWait(driver, seconds);
    	wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public static void waitForElementToBeClickableBy(WebDriver driver, int seconds, By by)
    {
    	WebDriverWait wait = new WebDriverWait(driver, seconds);
    	wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    
    public static void waitForElementToBeDisplayedBy(WebDriver driver, int seconds, By by) 
	{
		WebDriverWait wait = new WebDriverWait(driver, seconds);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
    
    public static void staticWait(int seconds)
    {
    	try
    	{
    		Thread.sleep(seconds * 1000);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public static int getRandomNumberInRange(int min, int max) {

    	if (min >= max) {
    		throw new IllegalArgumentException("max must be greater than min");
    	}

    	Random r = new Random();
    	return r.nextInt((max - min) + 1) + min;
    }
    
    public static String captureScreenshot(String folderName, String screenName,WebDriver driver, String flavour, String browserOrDevice, String paymentType)
    {
        String screenShotPath = null;
        
        if(flavour.equalsIgnoreCase("web") && paymentType.equalsIgnoreCase("N/A"))
		{
        	if(browserOrDevice.equalsIgnoreCase("chrome")) {
        		screenShotPath = System.getProperty("user.dir")+ "/test-output/ExtentReportsWeb/ChromeWeb/Screenshots/";
        	}
        	if(browserOrDevice.equalsIgnoreCase("firefox")) {
        		screenShotPath = System.getProperty("user.dir")+ "/test-output/ExtentReportsWeb/FirefoxWeb/Screenshots/";
        	}
        	
		}
        if(flavour.equalsIgnoreCase("androidApp"))
        {
        	if(browserOrDevice.equalsIgnoreCase("Samsung-J5")) {
        		screenShotPath = System.getProperty("user.dir")+ "/test-output/ExtentReportsApp/AndroidAppV6/Screenshots/";
        	}
        	if(browserOrDevice.equalsIgnoreCase("MI-A1")) {
        		screenShotPath = System.getProperty("user.dir")+ "/test-output/ExtentReportsApp/AndroidAppV7/Screenshots/";
        	}
        }
        if(flavour.equalsIgnoreCase("msite"))
        {
        	screenShotPath = System.getProperty("user.dir")+ "/test-output/ExtentReportsMsite/ChromeMsite/Screenshots/";
        }
        if((flavour.equalsIgnoreCase("RetailWeb")) || (flavour.equalsIgnoreCase("web") && !paymentType.equalsIgnoreCase("N/A")) )
        {
        	screenShotPath = System.getProperty("user.dir")+ "/test-output/ExtentReportsWeb/RetailWeb/Screenshots/";
        }
        
        if(flavour.equalsIgnoreCase("MBWeb"))
		{
        	if(browserOrDevice.equalsIgnoreCase("chrome")) 
        	{
        		screenShotPath = System.getProperty("user.dir")+ "/test-output/ExtentReportsMBWeb/MB_ChromeWeb/Screenshots/";
        	}
        	
		}
  
        if(flavour.equalsIgnoreCase("MBMsite"))
		{
        	screenShotPath = System.getProperty("user.dir")+ "/test-output/ExtentReportsMBMsite/MB_Msite/Screenshots/";	
		}
        
        if(screenShotPath != null)
        {
        	screenShotPath = screenShotPath + folderName + "/"+ screenName + ".jpg";
        }
        else
        {
        	System.out.println("screenshotpath variable set as NULL..");
        	GlobalVar.test.log(LogStatus.ERROR, "screenshotpath variable set as NULL.." );
        }
        
        try {        	
        	File scrFile =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);       
            FileUtils.copyFile(scrFile, new File(screenShotPath));
            
        } catch (IOException e) {
            System.out.println("Taking screenshot failed due to " + e);
            e.printStackTrace();
        }
        
        String relativepath = screenShotPath.substring(screenShotPath.indexOf("Screenshots"));
        System.out.println("relative screenshot path returned :: " + relativepath);
        return relativepath;
        
        
    }
    
    public static boolean verifyHKCashPresentForUser(String mob, String hkCashType, int storeId ) {
    	return dbActionsObj.verifyHKCashPresentForUser(mob, hkCashType,storeId);    			
    }
    
    public static void addHKCashForUser(String mob, String hkCashType, int storeId) {
    	if(dbActionsObj.verifyHKCashPresentForUser(mob, hkCashType,storeId)) {
    		GlobalVar.test.log(LogStatus.INFO, "Sufficient HKCASH available for user cart..");
    	}
    	else {
    		GlobalVar.test.log(LogStatus.INFO, "Sufficient HKCASH not available for user cart, Adding HKCASH..");
    		dbActionsObj.setupHKCashDataForUser(mob, hkCashType,storeId);
    	}
    }

	public static String analyzeLogs() 
	{
		LogEntries logEntries = BrowserFactory.getWebDriver().manage().logs().get(LogType.BROWSER);
		StringBuffer completeLog = new StringBuffer();
        for (LogEntry entry : logEntries) 
        {
        	
        	String currentLineLog = new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage();
            System.out.println(currentLineLog);
            GlobalVar.test.log(LogStatus.INFO, currentLineLog);
//            completeLog.append(currentLineLog);
        }
        return completeLog.toString();
		
	}
	
}
