package testClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class Test_Truecaller_multiple_accounts
{
	CommonFunctions common;
	static String email;
	static String password;
	
	static int counter = -1;
	public Test_Truecaller_multiple_accounts() 
	{
		common = new CommonFunctions();
	}
	static
	{
		email = "gofroautomation@gmail.com";
		password = "Automation123";
	}
	public static void main(String args[]) throws IOException
	{
		
		WebDriver driver;
//		Object obj[] = null;
		ChromeOptions ops = new ChromeOptions();
	    ops.addArguments("--disable-notifications");
	    String profileName = null;
	    String profileTag = null;
	    int counter = 0;
	    Test_Truecaller_multiple_accounts truecallerClass = new Test_Truecaller_multiple_accounts();
	    List<Map<String, String>> collection = new ArrayList<Map<String,String>>();
	    
	    BufferedReader bufReader = new BufferedReader(new FileReader("E:\\truecaller_data.txt"));
	    		ArrayList<String> listOfLines = new ArrayList<String>();
	    		String line = bufReader.readLine(); while (line != null)
	    		{
	    		    listOfLines.add(line);
	    		    line = bufReader.readLine();
	    		} 
	    		bufReader.close();
     
	    		HashMap<String, String> resultMap = new HashMap<String, String>();
 	    
	 //   driver.manage().deleteAllCookies();
	    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ PropertyHelper.readProperty("chromeDriver"));
	    try
	    {
		    driver = new ChromeDriver(ops);
			driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
		    driver.get("http://truecaller.com");
		    
		    
	 	
			for(String phoneNumber : listOfLines)
			{
				System.out.println(counter++);
				if(counter==1)
				{
					truecallerClass.loginToFirstGmailAccount(driver);
				}
				
				else if(counter==50 || counter==100 || counter==150)
				{
					System.out.println("Changing account");
					truecallerClass.changeAccount(driver);
				}
				
				else if(counter==350)
				{
					break;
				}
				
				driver.findElement(By.xpath("//input[@type='tel']")).sendKeys(phoneNumber);
				System.out.println(phoneNumber + " searched");
				driver.findElement(By.xpath("//button[@class='searchbar__submit']")).click();
				System.out.println("submit clicked");
				try
				{
					profileTag = driver.findElement(By.xpath("//div[contains(@class,'ProfileTag__Name')]")).getText() ;
					System.out.println("profileTag :: " + profileTag);
				}
				catch(Exception e)
				{
					try
					{
						if(StringUtils.containsIgnoreCase(profileName, "trainer") || StringUtils.containsIgnoreCase(profileName, "fitness") || StringUtils.containsIgnoreCase(profileName, "gym"))
						{
							profileTag = "GYM or Trainer";
						}
					}
					catch(Exception e2)
					{
						
					}
					profileTag = "Not found";
				}
	//			common.staticWait(3);
				WebDriverWait wait = new WebDriverWait(driver, 30);
				try {Thread.sleep(3000);}catch(Exception e) {}
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='searchbar__clear']")));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='searchbar__clear']")));
				driver.findElement(By.xpath("//button[@class='searchbar__clear']")).click();
				System.out.println("clear clicked");
				resultMap.put(phoneNumber, profileTag);
				collection.add(resultMap);
//				obj = collection.toArray();
//				System.out.println(obj[0]);
			}
			System.out.println("result ::" + resultMap);
	    }
	    catch(UnhandledAlertException e)
	    {
	    	System.out.println("Exception occcred");
	    }
	    finally {
			
	    	 try {
	             FileWriter writer = new FileWriter("E:\\result.txt", true);
	             writer.write(resultMap.toString());
	             writer.close();
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	  
	     }
	    	/*try {
	    		 XSSFWorkbook workbook = new XSSFWorkbook(); 
	    		 
	    		  //Create a blank sheet
	    		  XSSFSheet sheet = workbook.createSheet("truecaller_data");
	    	
	    		 int rownum = 0;
	    		  for (int i=0 ; i<collection.size() ;i++)
	    		  {
	    		   Row row = sheet.createRow(rownum++);
	    		 
	    		   int cellnum = 0;
	    		   for (int j=0 ; j<collection.size() ;j++)
	    		   {
	    		    Cell cell = row.createCell(cellnum++);
	    		    
	    		     cell.setCellValue(resultMap.get(j));
	    		    
	    		   }
	    		  }
	    		  try
	    		  {
	    		   //Write the workbook in file system
	    		   FileOutputStream out = new FileOutputStream(new File("E:\\truecaller_result.xlsx"));
	    		   workbook.write(out);
	    		   out.close();
	    		   System.out.println("xlsx has been created successfully");
	    		  } 
	    		  catch (Exception e) 
	    		  {
	    		   e.printStackTrace();
	    		  }
	    	}
	    	catch(Exception e2)
	    	{
	    		e2.printStackTrace();
	    	}*/
//	    	}
	    	
	    
	}
	
	
	   
	private void loginToFirstGmailAccount(WebDriver driver) 
	{
		String hkWinHndl = driver.getWindowHandle();
	/*	WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'TopNav__SignIn')]")));*/
		WebDriverUtil.staticWait(5);
		common.clickThroughJavaScript(driver, driver.findElement(By.xpath("//a[contains(@class,'TopNav__SignIn')]")));
//	    driver.findElement(By.xpath("//a[contains(@class,'TopNav__SignIn')]")).click();
	    driver.findElement(By.xpath("//div[contains(text(),'Sign in with Google')]")).click();
	    
	    // Google login handle
	    
//		click(googleButton);		
	   
		
		WebDriverUtil.staticWait(4);		
			
		if(WebDriverUtil.waitForWindowCount(driver, 1))
		{
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			common.staticWait(4);
			driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			common.staticWait(5);
		}
		else if(WebDriverUtil.waitForWindowCount(driver, 2)) {
		
			for (String allHandles : driver.getWindowHandles()) {
				driver.switchTo().window(allHandles);
				System.out.println("current window title :" + driver.getTitle());
				if(driver.getTitle().contains("Sign in")) {
					System.out.println("Switched to Google window..");
					
					WebElement gmailUserInpt = driver.findElement(By.xpath("//input[@id='identifierId']"));
					gmailUserInpt.sendKeys(email);
					
					WebElement gmailIdentifierNext = driver.findElement(By.xpath("//div[@id='identifierNext']"));
					gmailIdentifierNext.click();							
					WebDriverUtil.staticWait(4);					
					WebElement gmailPswdInpt = driver.findElement(By.xpath("//input[@name='password']"));
					gmailPswdInpt.sendKeys(password);					
					WebElement gmailPswdNext = driver.findElement(By.xpath(".//*[@id='passwordNext']"));
					gmailPswdNext.click();
//					common.staticWait(3);
					try
					{
						if(driver.findElement(By.xpath("//span[contains(text(),'ALLOW')]")).isDisplayed())
							driver.findElement(By.xpath("//span[contains(text(),'ALLOW')]")).click();
						if(driver.findElement(By.xpath("//span[contains(text(),'No thanks')]")).isDisplayed())
							driver.findElement(By.xpath("//span[contains(text(),'No thanks')]")).click();
					}
					catch(NoSuchWindowException e)
					{
						System.out.println("Gmail logged in, Unable to click ALLOW or No Thanks");
					}
					catch(NoSuchElementException noSuchException)
					{
						System.out.println("Allow, No Thanks was not found");
					}
					catch(Exception e)
					{
						System.out.println("Other exceptions");
					}
					WebDriverUtil.staticWait(3);
					driver.switchTo().window(hkWinHndl);
					System.out.println("current window title :" + driver.getTitle());
				}
				else if(driver.getTitle().contains("Error")) {
					System.out.println("current window title :" + driver.getTitle());
					driver.close();
					break;
				}
			}
		
		
		}
		
		
	}

	public void changeAccount(WebDriver driver)
	{
		 driver.manage().deleteAllCookies();
		 driver.navigate().to("http://gmail.com");
		 driver.manage().deleteAllCookies();
	     counter++;
	     driver.navigate().to("http://truecaller.com");
	     common.clickThroughJavaScript(driver, driver.findElement(By.xpath("//img[@class='TopNav__UserAvatar']")));
	     common.staticWait(2);
	     common.clickThroughJavaScript(driver, driver.findElement(By.xpath("//a[@class='TopNav__UserMenuSignOut']")));
	     ArrayList<String> accountList = new ArrayList<String>();
	   
	     accountList.add("healthkartmsite@gmail.com");
	     accountList.add("healthkartmsite.fb@gmail.com");
	     accountList.add("hkapp17automation@gmail.com");
	     accountList.add("healthkartautomation@gmail.com");
	     accountList.add("hkwebautomation.fb@gmail.com");
//	     accountList.add("hkapp17automation.fb@gmail.com");
	     
	     String userName = accountList.get(counter);
	     
	     WebDriverUtil.staticWait(4);		
	     common.clickThroughJavaScript(driver, driver.findElement(By.xpath("//a[contains(@class,'TopNav__SignIn')]")));
//		    driver.findElement(By.xpath("//a[contains(@class,'TopNav__SignIn')]")).click();
		    driver.findElement(By.xpath("//div[contains(text(),'Sign in with Google')]")).click();
	     common.staticWait(4);
	     String hkWinHndl = driver.getWindowHandle();
	     if(WebDriverUtil.waitForWindowCount(driver, 1))
			{
				driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				common.staticWait(4);
				driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				common.staticWait(5);
			}
			if(WebDriverUtil.waitForWindowCount(driver, 2)) {
			
				for (String allHandles : driver.getWindowHandles()) {
					driver.switchTo().window(allHandles);
					System.out.println("current window title :" + driver.getTitle());
					if(driver.getTitle().contains("Sign in")) {
						System.out.println("Switched to Google window..");
						if(common.isElementDisplayedBy(driver, By.xpath("//p[contains(text(),'Use another account')]")));
							driver.findElement(By.xpath("//p[contains(text(),'Use another account')]")).click();
							
							
							
						WebElement gmailUserInpt = driver.findElement(By.xpath("//input[@id='identifierId']"));
						gmailUserInpt.sendKeys(userName);
						
						WebElement gmailIdentifierNext = driver.findElement(By.xpath("//div[@id='identifierNext']"));
						gmailIdentifierNext.click();							
						WebDriverUtil.staticWait(4);					
						WebElement gmailPswdInpt = driver.findElement(By.xpath("//input[@name='password']"));
						gmailPswdInpt.sendKeys(password);					
						WebElement gmailPswdNext = driver.findElement(By.xpath(".//*[@id='passwordNext']"));
						gmailPswdNext.click();
						try
						{
							if(common.isElementDisplayedBy(driver, By.xpath("//span[contains(text(),'ALLOW')]")))
								driver.findElement(By.xpath("//span[contains(text(),'ALLOW')]")).click();
							if(common.isElementDisplayedBy(driver, By.xpath("//span[contains(text(),'No thanks')]")))
								driver.findElement(By.xpath("//span[contains(text(),'No thanks')]")).click();
						}
						catch(NoSuchWindowException e)
						{
							System.out.println("Gmail logged in, Unable to click ALLOW or No Thanks");
						}
						
						catch(Exception e)
						{
							System.out.println("Other exceptions..");
						}
						WebDriverUtil.staticWait(10);
						driver.switchTo().window(hkWinHndl);
						System.out.println("current window title :" + driver.getTitle());
					}
					else if(driver.getTitle().contains("Error")) {
						System.out.println("current window title :" + driver.getTitle());
						driver.close();
						break;
					}
				}
			
			
			}
	}
		
}