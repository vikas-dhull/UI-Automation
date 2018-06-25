package com.healthkart.hkAutomation.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;
import org.testng.Reporter;
import com.gargoylesoftware.htmlunit.WebConsole.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;*/
import com.healthkart.hkAutomation.browser.BrowserFactory;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class CommonFunctions {

//    public WebDriver driver = null;
    public Properties or = null;
    public AndroidDriver<?> androidDriver;
    
    public XSSFSheet getValuesFromExcel(String filePath, int sheetnum)throws Exception 
    {
        InputStream inputStr = null;
        inputStr = new FileInputStream(filePath);
        System.out.println(filePath);
        System.out.println(inputStr);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStr);
        XSSFSheet sheet = workbook.getSheetAt(sheetnum);
        return sheet;
    }
    
    public Properties propertyFile(String FileName, String fileLocation ) throws IOException{
        
//        String filepath = new java.io.File(".").getCanonicalPath()+fileLocation+FileName;
    	String filepath = System.getProperty("user.dir") + "/" + FileName;
    	System.out.println("Web Properties path ::" + filepath);
        FileInputStream inputStr = null;
         Properties properties = new Properties();
         inputStr = new FileInputStream(filepath);
         properties.load(inputStr);
         return properties;
    }

    public String dateAfterDay(String date, int day) throws ParseException{
		String pattern = "E dd MMM yyyy";
		String finalDate = null;
		try{
			SimpleDateFormat format = new SimpleDateFormat(pattern,Locale.US);
			Date dd = format.parse(date);
			long epoch = dd.getTime();
			System.out.println(epoch);
			long daymilisec = day*24*60*60*1000;
			long epocafteradd = daymilisec+	epoch;
			System.out.println("daymilisec::"+daymilisec);
			Date dateAdd = new Date(epocafteradd);
			finalDate = dateAdd.toString();

		}catch(Exception e){
					e.printStackTrace();
				}
				return finalDate;
	}
    
    public String dateForUi(String date){
		
		String finalDate = null;
		try{
					
			DateFormat dayFormat = new SimpleDateFormat("E, dd MMM yyyy",Locale.ENGLISH);
			DateFormat draftFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
						 finalDate = dayFormat.format(draftFormat.parse(date));
			System.out.println(finalDate);
		}catch(Exception e){
					e.printStackTrace();
				}
				return finalDate;
	}
    
 
    
    public String getSplitDate(String value,String date){
		String splitValue = null;
		String[] startdatearr = date.split("T", 2);
		
		if(value.equalsIgnoreCase("year")){
		splitValue = startdatearr[0].split("-", 3)[0];}
		else if(value.equalsIgnoreCase("month")){
			splitValue = startdatearr[0].split("-", 3)[1];
			}else if(value.equalsIgnoreCase("date")){
				splitValue = startdatearr[0].split("-", 3)[2];	
			}
		
		return splitValue;
	}
    
	public static String getSplitTime(String value,String date){
		String splitValue = null;
		String[] startdatearr = date.split("T", 2);
		
		if(value.equalsIgnoreCase("hh")){
		splitValue = startdatearr[1].split(":", 3)[0];}
		else if(value.equalsIgnoreCase("mm")){
			splitValue = startdatearr[1].split(":", 3)[1];
			}
		
		return splitValue;
	}

    public void captureScreenshot(String folderName, String screenName,WebDriver driver)
    {
        String screenShotPath = System.getProperty("user.dir")+ "/test-output/Screenshots/";
        screenShotPath = screenShotPath + folderName + "/"+ screenName + ".jpg";

        File scrFile =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(screenShotPath));
        } catch (IOException e) {
            System.out.println("Taking screenshot failed due to " + e);
            e.printStackTrace();
        }
    }
    
    public int dateDiffrenceinDay(String oldDate, String newDate)
 			 {
 		String pattern = "E, dd MMM yyyy";
 		int days = 0;
 		try {
 			SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.US);
 			Date oldd = format.parse(oldDate);
 			long oldepoch = oldd.getTime();
 			System.out.println(oldepoch);
 			Date newd = format.parse(newDate);
 			long newepoch = newd.getTime();

 			days = (int) ((newepoch - oldepoch) / (24 * 60 * 60 * 1000));

 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return days;
 	}
    
  /*  public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }*/
    
    public Properties getOr() {
        return or;
    }

    public void setOr(Properties or) {
        this.or = or;
    }
    
    public void staticWait(int seconds)
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
    
    public void waitForElementToBeClicked(WebDriver driver, int seconds, WebElement element)
    {
    	WebDriverWait wait = new WebDriverWait(driver, seconds);
    	wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public void waitForElementToBeClicked(WebDriver driver, int seconds, By by)
    {
    	WebDriverWait wait = new WebDriverWait(driver, seconds);
    	//wait.until(ExpectedConditions.elementToBeClickable(by));
    	wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(by)));
    	
    }
    
    public void waitForElementToBePresent(WebDriver driver, int seconds, By by)
    {
    	WebDriverWait wait = new WebDriverWait(driver, seconds);
    	wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    
    public void waitForElementToBeDisplayedBy(WebDriver driver, int seconds, By by)
    {
    	WebDriverWait wait = new WebDriverWait(driver, seconds);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    
    public void waitForElementToBeDisplayed(WebDriver driver, int seconds, WebElement element)
    {
    	WebDriverWait wait = new WebDriverWait(driver, seconds);
    	wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public void waitForElementToBeInvisible(WebDriver driver, int seconds, By by)
    {
    	WebDriverWait wait = new WebDriverWait(driver, seconds);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    
    public void scrollDownTillEnd(WebDriver driver)
    {
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("window.scrollTo(0, document.body.scrollHeight)", "");
    	reportLogAndPrintInConsole("scrolling to page end..");
    }
    
    public void scrollToObject(WebDriver driver, WebElement element)
    {
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].scrollIntoView(true);",element);
    	reportLogAndPrintInConsole("scrolling to element :: " + element);
    }
    
    public void scrollToObjectWithMargin(WebDriver driver, WebElement element, int margin)
    {
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("window.scrollTo(0,"+(element.getLocation().y-margin)+")");
    	reportLogAndPrintInConsole("scrolling to element :: " + element);
    }
    
    public void scrollTillTop(WebDriver driver)
    {
    	 JavascriptExecutor jse = (JavascriptExecutor) driver;
    	 jse.executeScript("window.scrollBy(0,-250)", "");
    	 reportLogAndPrintInConsole("scrolling to page top..");
    }
    
    public void reportLogAndPrintInConsole(String message)
    {
    	System.out.println(message);
//    	Reporter.log(message);
    	GlobalVar.test.log(LogStatus.INFO, message);
    }
    
    public void fetchHTTPResponse(String url)
    {
//    	final String USER_AGENT = "Mozilla/5.0";
    	int responseCode;
    	try 
    	{
			URL urlObj = new  URL(url);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
//			con.setRequestProperty("User-Agent", USER_AGENT);
			
			responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());
			System.out.println(responseCode);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
    	
    }
    
    public void waitForLoader(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='commonLoader']")));
	}
    
    public boolean isElementPresent(WebDriver driver, WebElement element)
	{
		try{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			element.getText();
			driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
			return true;
		}
		catch(NoSuchElementException e)
		{
			driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
			return false;
		}
	}
    
    public boolean isElementPresentXpath(WebDriver driver, String xpath)
    {
    	try
    	{
    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		driver.findElement(By.xpath(xpath));
    		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
    		return true;
    	}
    	catch(NoSuchElementException e)
    	{
    		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
    		return false;
    	}
    }
    
    public int getRandomNumberInRange(int min, int max) {

    	if (min >= max) {
    		throw new IllegalArgumentException("max must be greater than min");
    	}

    	Random r = new Random();
    	return r.nextInt((max - min) + 1) + min;
    }
	 
	 public String convertGMTToIST(String uiTime)
	 {
		 String newTime = null;
		 try
		 {
			 SimpleDateFormat df = new SimpleDateFormat("HH:mm");
			 Date d = df.parse(uiTime); 
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(d);
			 cal.add(Calendar.HOUR_OF_DAY, 5);
			 cal.add(Calendar.MINUTE, 30);
			 newTime = df.format(cal.getTime());
			 reportLogAndPrintInConsole("Successfully converted GMT to IST");
		 }
		 catch(Exception e)
		 {
			 reportLogAndPrintInConsole("Failed to convert GMT to IST");
			 e.printStackTrace();
		 }
		 return newTime;
	 }
	 
	 public String addTime(String time1, String time2)
	 {
		 String resultTime = null;
		 return resultTime;
		 
	 }
	 
	 public boolean timeGreaterThan24hrs(String time)
	 {
		 return true;
	 }
	 
	 public String getDate(String date)
	 {
			String startdatearr = StringUtils.substringBetween(date, "", "T");
			return startdatearr;
	 }
	 
	 public int dateDiffrence(String firstDate, String lastDate) {
			int days = 0;
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date1 = myFormat.parse(firstDate);
				Date date2 = myFormat.parse(lastDate);
				long diff = date2.getTime() - date1.getTime();
				days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				System.out.println("Days: "
						+ TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return days;
		}

	 public static String convirtGmtToistDate(String date){
			
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";
			String finalDate = null;
			try{
				SimpleDateFormat format = new SimpleDateFormat(pattern,Locale.US);
				Date dd = format.parse(date);
				long epoch = dd.getTime();
				System.out.println(epoch);
				long daymilisec = 330000*60;
				long epocafteradd = daymilisec+	epoch;
				Date dateAdd = new Date(epocafteradd);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //The "a" is the AM/PM marker
				 finalDate = formatter.format(dateAdd);

			}catch(Exception e){
						e.printStackTrace();
					}
			
			return finalDate;
		}
	 
	 public boolean isTimeGreaterThan(String firstTime, String secondTime) 
	 {
		 boolean flag = false;
		 try {
		 SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
		 Date first = parser.parse(firstTime);
		 Date second = parser.parse(secondTime);
		 if (first.after(second))
		 flag = true;
		 } 
		 catch (Exception e) 
		 {
			 e.printStackTrace();
		 }
		 return flag;
	}
	 
	 public void click(WebElement element)
	 {
		 WebDriver driver = BrowserFactory.getWebDriver();
		 waitForElementToBeDisplayed(driver, 30, element);
		 waitForElementToBeClicked(driver, 30, element);
//		 staticWait(2);
		 reportLogAndPrintInConsole("Clicking WebElement :: " + element );
		 element.click();
	 }
	 
	 public static void captureScreenshotForMobile(AndroidDriver<?> driver, String outputlocation ) 
	 {
		 try
		 {
			System.out.println("Capturing the snapshot of the page ");
			File srcFiler=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFiler, new File(outputlocation));
		 }
		 catch(IOException e)
		 {
			 System.out.println("Failed IO");
		 }
		 catch(Exception e)
		 {
			 System.out.println("Other than IO Exception");
		 }
	}
	 
	 public static void moveToElementAppium(AndroidDriver<?> androidDriver, String elementString)
	 {
		 androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+elementString+"\").instance(0))");
	 }
	 
	 public void sendKeysThroughJavaScript(WebDriver driver, WebElement element,String string)
	    {
	    	JavascriptExecutor js = (JavascriptExecutor)driver;
	    	js.executeScript("arguments[0].setAttribute('value','" + string +"')",element);
	    	reportLogAndPrintInConsole("typing input value: "+ string + " for element: "+ element);
	    }
	 
	 public void clickThroughJavaScript(WebDriver driver, WebElement element)
	    {
	    	JavascriptExecutor js = (JavascriptExecutor)driver;
	    	js.executeScript("arguments[0].click();", element );
	    }

	public String getText(WebElement findElement) 
	{
		String text = findElement.getText();
		reportLogAndPrintInConsole(text);
		return text;
	}

	public void clickBy(By elementName) 
	{
		androidDriver = BrowserFactory.getAndroidDriver();
		waitForElementToBeClicked(androidDriver, 30, elementName);
		reportLogAndPrintInConsole("Clicking WebElement :: " + elementName.toString() );
		androidDriver.findElement(elementName).click();
	}

	public void sendKeys(WebElement element, String string) 
	{
		reportLogAndPrintInConsole("typing input value: "+ string + " for element: "+ element);
		element.clear();
		element.sendKeys(string);
	}
	
	public boolean isElementPresentBy(WebDriver driver, By by)
    {
    	try
    	{
    		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    		driver.findElement(by);
    		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    		return true;
    	}
    	catch(NoSuchElementException e)
    	{
    		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    		return false;
    	}
    }
	
	public boolean isElementDisplayedBy(WebDriver driver, By by)
    {
		boolean flag = false;
    	try
    	{
    		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    		flag = driver.findElement(by).isDisplayed();
    		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    		return flag;
    	}
    	catch(NoSuchElementException e)
    	{
    		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    		return flag;
    	}
    }
	
	/*public void verticalScrollToByElementAndroid(AndroidDriver<?> androidDriver, By byElement)
	{
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		while(true)
		{
			try
			{
				List<?> elements = androidDriver.findElements(byElement);
				System.out.println(elements.size());
				System.out.println(elements);
				
				if(elements.size()!=0)
				{
					break;
				}
				Dimension size = androidDriver.manage().window().getSize();
				int starty = (int) (size.height * 0.90);
				int endy = (int) (size.height * 0.01);
				int startx = size.width / 2,endx = size.width / 2;
			  	System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);
			  	androidDriver.swipe(startx, starty, endx, endy, 3000);
			}
			catch(NoSuchElementException e)
			{
				reportLogAndPrintInConsole("Scrolling again..");
			}
		}
		androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
	}*/
	
	public boolean isAlertPresent(WebDriver driver) {
		boolean foundAlert = false;
	    WebDriverWait wait = new WebDriverWait(driver, 3);
	    try {
	        wait.until(ExpectedConditions.alertIsPresent());
	        foundAlert = true;
	    } catch (Exception e) {
	        foundAlert = false;
	    }
	    return foundAlert;
	}
	
	public Map<String,String> cardDetails()
    {
		HK_Data_Provider dataPrvdr = new HK_Data_Provider();
		Map<String, String> cardDetails = dataPrvdr.getCardDetailMap();
		return cardDetails;
    }
	
	public void scrollAndSwipeByElementAndroid(AndroidDriver<?> androidDriver, By byElement, String direction) {
		
		String elementText = null;
		int counter = 0;
		while(elementText == null && counter!=8)
		{
			counter++;
			try
			{		
				androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				elementText = androidDriver.findElement(byElement).getText();
			}
			catch(Exception e)
			
			{
				if("left".equalsIgnoreCase(direction)) {
					Dimension size = androidDriver.manage().window().getSize();
					int startx = (int) (size.width * 0.80);
					int endx = (int) (size.width * 0.01);
					int starty = size.height / 2;
				  	System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);
				  	TouchAction action = new TouchAction(androidDriver);
					action.press(startx, starty).waitAction(java.time.Duration.ofMillis(1000)).moveTo(endx, starty).release().perform();
				}
				else if("right".equalsIgnoreCase(direction)) {
					Dimension size = androidDriver.manage().window().getSize();
					int endx = (int) (size.width * 0.80);
					int startx = (int) (size.width * 0.01);
					int starty = size.height / 2;
				  	System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);
				  	TouchAction action = new TouchAction(androidDriver);
					action.press(startx, starty).waitAction(java.time.Duration.ofMillis(1000)).moveTo(endx, starty).release().perform();
				}
				else if("up".equalsIgnoreCase(direction)) {
					Dimension size = androidDriver.manage().window().getSize();
					int startx = size.width / 2;
					int starty = (int) (size.height * 0.80);
					int endy = (int) (size.height * 0.20);					
				  	System.out.println("startx = " + startx + " ,starty = " + starty + " , endy = " + endy);
				  	TouchAction action = new TouchAction(androidDriver);
					action.press(startx, starty).waitAction(java.time.Duration.ofMillis(1000)).moveTo(startx, endy).release().perform();
				}
				else if("down".equalsIgnoreCase(direction)) {
					Dimension size = androidDriver.manage().window().getSize();
					int startx = size.width / 2;
					int starty = (int) (size.height * 0.80);
					int endy = (int) (size.height * 0.02);										
				  	System.out.println("startx = " + startx + " ,starty = " + starty + " , endy = " + endy);
				  	TouchAction action = new TouchAction(androidDriver);
					action.press(startx, starty).waitAction(java.time.Duration.ofMillis(1000)).moveTo(startx, endy).release().perform();
				}
			}
			finally {
				androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
			}
		}
	}

	
	
}
