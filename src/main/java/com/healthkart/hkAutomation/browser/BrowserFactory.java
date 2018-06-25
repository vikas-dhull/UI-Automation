package com.healthkart.hkAutomation.browser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidDriver;

public class BrowserFactory{
	
	private static WebDriver driver;
	private static AndroidDriver<WebElement> androidDriver;
	private static DesiredCapabilities desiredCapabilities;
	private static String jenkinsbrowserOrDevice;	
	private static Properties masterProp = null;
	private static String appiumURL = null;
	
	public static WebDriver launchBrowser(String flavour,String browserOrDevice) throws IOException 
	{
			if(flavour.equalsIgnoreCase("web") || flavour.equalsIgnoreCase("MBWeb"))
			{
				jenkinsbrowserOrDevice = System.getProperty("browserOrDevice");
				if(jenkinsbrowserOrDevice!=null)
				{
					browserOrDevice = jenkinsbrowserOrDevice;
					System.out.println("Browser Name from Jenkins is ::" + browserOrDevice);
				}
				
				if(browserOrDevice.equalsIgnoreCase("chrome"))
		        {
					ChromeOptions ops = new ChromeOptions();
					
		            ops.addArguments("--disable-notifications");
		            ops.addArguments("disable-infobars");
		            ops.addArguments("start-maximized");
		            
		            
		            
		            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ PropertyHelper.readProperty("chromeDriver"));
		            LoggingPreferences logPrefs = new LoggingPreferences();
		            logPrefs.enable(LogType.BROWSER, Level.ALL);
		            
		            ops.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		            try
		            {
		            	driver = new ChromeDriver(ops);
		            }
		            catch(WebDriverException e)
		            {
		            	System.out.println("WebDriver re-initiated");
		            	driver = new ChromeDriver(ops);
		            }
		    		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		    		driver.manage().deleteAllCookies();
		    		//driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		            //driver.manage().window().maximize();
		        }
				else if(browserOrDevice.equalsIgnoreCase("firefox"))
		        {

		        	
		        	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ PropertyHelper.readProperty("geckodriver"));
		        	System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
		        	System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
		        	
		        	FirefoxProfile ffPrfl = new FirefoxProfile();
		        	ffPrfl.setPreference("dom.webnotifications.enabled", false);
		        	FirefoxOptions ffOpt = new FirefoxOptions();
			        ffOpt.setProfile(ffPrfl);
		        	
		        	driver = new FirefoxDriver(ffOpt);
		    		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		    		//driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
//		            driver.manage().window().maximize();
		            
		        }
				else if(browserOrDevice.equalsIgnoreCase("safari"))
		        {
		            
					/*DesiredCapabilities desiredCapabilities = DesiredCapabilities.safari();
		            SafariOptions safariOptions = new SafariOptions();*/
		            
		            
					driver = new SafariDriver();
		            driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		           // driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		            driver.manage().window().maximize();
		            //driver.manage().deleteAllCookies();
		         //   String[] cmd = { "osascript", "-e",   "tell app \"Safari\" to activate" };
		        //    Process process = Runtime.getRuntime().exec(cmd);		            
					
					/*System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ PropertyHelper.readProperty("ieDriver"));
		            DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		            cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		            driver = new InternetExplorerDriver();
		    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		            driver.manage().window().maximize();*/

		        }
			}	
		
			else if(flavour.equalsIgnoreCase("msite") || flavour.equalsIgnoreCase("MBmsite"))
			{
			
				if(browserOrDevice.equalsIgnoreCase("chrome"))
				{
					loadMasterProperties(flavour,"chrome");
		        	loadDesiredCapabilities("chrome");
		        	try
		        	{
			        	androidDriver = new AndroidDriver<WebElement>(
			                    new URL(appiumURL), 
			                    desiredCapabilities);
			        	setAndroidDriver(androidDriver);
			        	driver = androidDriver;
			        	androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
			        	//androidDriver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		        	}
		        	catch(Exception e)
		        	{
		        		e.printStackTrace();
		        		System.out.println("Unable to launch appium session");
		        	}
				}
			
				
		        	
				else if(browserOrDevice.equalsIgnoreCase("androidWebView"))
				{

					
			        	loadMasterProperties(flavour,"androidWebView");
			        	loadDesiredCapabilities("androidWebView");
//			        	desiredCapabilities = new DesiredCapabilities();
			        	/*String path = System.getProperty("user.dir") + "/app/healthkart_prod.apk";
			        	desiredCapabilities.setCapability("deviceName", "Nexus 5");
			        	desiredCapabilities.setCapability("platformVersion", "7.1.1");
			        	desiredCapabilities.setCapability("app", path);
			        	desiredCapabilities.setCapability("platformName", "Android");
			        	desiredCapabilities.setCapability("appPackage", "com.healthkart.healthkart");
			        	desiredCapabilities.setCapability("appActivity", "com.healthkart.healthkart.Splash");*/
			        	try
			        	{
				        	androidDriver = new AndroidDriver<WebElement>(
				                    new URL(appiumURL), 
				                    desiredCapabilities);
				        	setAndroidDriver(androidDriver);
				        	androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
				        	driver = androidDriver;
			        	}
			        	catch(Exception e)
			        	{
			        		e.printStackTrace();
			        		System.out.println("Unable to launch appium session");
			        	}
			        	
			        }					
	
				else
				{
		        		System.out.println("Browser name other than chrome or uc");
				}
			}

				if(flavour.equalsIgnoreCase("androidApp"))
				{
			        if(browserOrDevice.equalsIgnoreCase("MI-A1"))
			        {			        	
			        	loadMasterProperties(flavour,browserOrDevice);
			        	loadDesiredCapabilitiesApp();
			        	try
			        	{
				        	androidDriver = new AndroidDriver<WebElement>(
				                    new URL(appiumURL), 
				                    desiredCapabilities);
				        	setAndroidDriver(androidDriver);
				        	androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
				        	driver = androidDriver;
			        	}
			        	catch(Exception e)
			        	{
			        		e.printStackTrace();
			        		System.out.println("Unable to launch appium session");
			        	}
			        }
			        
			        if(browserOrDevice.equalsIgnoreCase("Samsung-J5"))
			        {			        	
			        	loadMasterProperties(flavour,browserOrDevice);
			        	loadDesiredCapabilitiesApp();
			        	try
			        	{
				        	androidDriver = new AndroidDriver<WebElement>(
				                    new URL(appiumURL), 
				                    desiredCapabilities);
				        	setAndroidDriver(androidDriver);
				        	androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
				        	driver = androidDriver;
			        	}
			        	catch(Exception e)
			        	{
			        		e.printStackTrace();
			        		System.out.println("Unable to launch appium session");
			        	}
			        }
				}
				
				if(flavour.equalsIgnoreCase("RetailWeb"))
				{
					jenkinsbrowserOrDevice = System.getProperty("browserOrDevice");
					if(jenkinsbrowserOrDevice!=null)
					{
						browserOrDevice = jenkinsbrowserOrDevice;
						System.out.println("Browser Name from Jenkins is ::" + browserOrDevice);
					}
					
					if(browserOrDevice.equalsIgnoreCase("chrome"))
			        {
						ChromeOptions ops = new ChromeOptions();
						
			            ops.addArguments("--disable-notifications");
			            ops.addArguments("disable-infobars");
			            ops.addArguments("start-maximized");
			            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ PropertyHelper.readProperty("chromeDriver"));
			            LoggingPreferences logPrefs = new LoggingPreferences();
			            logPrefs.enable(LogType.BROWSER, Level.ALL);
			            
			            ops.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			            try
			            {
			            	driver = new ChromeDriver(ops);
			            }
			            catch(WebDriverException e)
			            {
			            	System.out.println("WebDriver re-initiated");
			            	driver = new ChromeDriver(ops);
			            }
			    		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
			    		driver.manage().deleteAllCookies();
			    		//driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			            //driver.manage().window().maximize();
			        }
				}
				
		return driver;
	}
	
	public static void setAndroidDriver(AndroidDriver<WebElement> androidDriver)
	{
		androidDriver = BrowserFactory.androidDriver;
	}
	
	public static AndroidDriver<WebElement> getAndroidDriver()
	{
		return androidDriver;
	}
	
	public static WebDriver getWebDriver()
	{
		return driver;
	}
	
	public static void loadDesiredCapabilitiesApp() {
		try {
			desiredCapabilities = new DesiredCapabilities();
			appiumURL = masterProp.getProperty("baseURL");
			String androidAppVersion = null;  					// master or master_prev
			String platformVersion = null;    					// v6 or v7
			String androidAppEnv = null;      					// stag or prod or local
			
			if(GlobalVar.jenkinsAndroidAppVersion != null)
			{
				androidAppVersion = GlobalVar.jenkinsAndroidAppVersion;
				System.out.println("Android App version from Jenkins is ::" + androidAppVersion);
			}
			else 
			{
				androidAppVersion = masterProp.getProperty("androidAppVersion");
				System.out.println("Android App version is ::" + androidAppVersion);
			}
			
			
			if(GlobalVar.jenkinsAndroidPlatformVersion !=null)
			{
				platformVersion = GlobalVar.jenkinsAndroidPlatformVersion;
				System.out.println("Android Platform Version from Jenkins is ::" + platformVersion);
			}
			else 
			{
				platformVersion = masterProp.getProperty("cap_platformVersion");
				System.out.println("Android Platform version is ::" + platformVersion);
			}
			
			
			if(GlobalVar.jenkinsEnvironment !=null)
			{
				if("qa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
					androidAppEnv="stag";
				if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
					androidAppEnv="prod";
				if("local".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
					androidAppEnv="local";
				System.out.println("Android App Environment from Jenkins is ::" + androidAppEnv);
			}
			else
			{
				if("qa".equalsIgnoreCase(PropertyHelper.readProperty("env")))
					androidAppEnv = "stag";
				if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env")))
					androidAppEnv = "prod";
				if("local".equalsIgnoreCase(PropertyHelper.readProperty("env")))
					androidAppEnv = "local";				
				System.out.println("Android App Environment is ::" + androidAppEnv);
			}
			
			Enumeration<?> e = masterProp.propertyNames();
			while (e.hasMoreElements()) {
				String prop = (String) e.nextElement();
				if (prop.startsWith("cap_")) {

					String capName = prop.replaceAll("cap_", "");
					String capVal = masterProp.getProperty(prop);

					if (capName.equals("app")) {
						capVal = System.getProperty("user.dir") + "/app/"
								+ capVal + "_" + androidAppEnv + "_" + androidAppVersion + ".apk";
						WebDriverUtil.reportLogAndPrintInConsole("Android App under testing ::" + "healthkart_"+ androidAppEnv + "_" + androidAppVersion + ".apk");
					}
					
					if (capName.equals("platformVersion"))
						capVal = platformVersion;

					if (capVal.equals("<NULL>"))
						capVal = "";
					
					if(capVal.contains("chrome"))
					{
						ChromeOptions options = new ChromeOptions();
						options.addArguments("--disable-notifications");
						desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
					}
					desiredCapabilities.setCapability(capName, capVal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadDesiredCapabilities(String flavour) {
		try {
			desiredCapabilities = new DesiredCapabilities();
			appiumURL = masterProp.getProperty("baseURL");
			
			String platformVersion = null;
			if(GlobalVar.jenkinsAndroidPlatformVersion !=null)
			{
				platformVersion = GlobalVar.jenkinsAndroidPlatformVersion;
				System.out.println("Android Platform Version from Jenkins is ::" + platformVersion);
			}
			else 
			{
				platformVersion = masterProp.getProperty("cap_platformVersion");
			}
			
			Enumeration<?> e = masterProp.propertyNames();
			while (e.hasMoreElements()) {
				String prop = (String) e.nextElement();
				if (prop.startsWith("cap_")) {

					String capName = prop.replaceAll("cap_", "");
					String capVal = masterProp.getProperty(prop);

					if (capName.equals("app"))
						capVal = System.getProperty("user.dir") + "/app/"
								+ capVal;
					
					if (capName.equals("platformVersion"))
						capVal = platformVersion;

					if (capVal.equals("<NULL>"))
						capVal = "";
					
					if(flavour.contains("chrome"))
					{
						ChromeOptions options = new ChromeOptions();
					    /*options.addArguments("--disable-extensions", "test-type",
					            "no-default-browser-check", "ignore-certificate-errors",
					            "--disable-notifications",
					            "--disable-offer-store-unmasked-wallet-cards",
					            "--disable-autofill-keyboard-accessory-view");*/
						options.addArguments("--disable-notifications", "--disable-extensions", "ignore-certificate-errors");
					    options.addArguments("--disable-translate");
					    options.addArguments("--disable-geolocation");
					    //options.addArguments("--enable-strict-powerful-feature-restrictions");
						
						desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
					}
					desiredCapabilities.setCapability(capName, capVal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadMasterProperties(String flavour, String browserOrDevice) {
		masterProp = new Properties();
		String fileLoc = System.getProperty("user.dir") + "/Capabilities/" + flavour + "_" + browserOrDevice
				+ ".properties";
		try {
			File f = new File(fileLoc);
			FileInputStream fis = new FileInputStream(f);
			masterProp.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
