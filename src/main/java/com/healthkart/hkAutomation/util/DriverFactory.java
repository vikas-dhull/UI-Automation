package com.healthkart.hkAutomation.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class DriverFactory {

	private Properties masterProp = null;
	private WebDriver driver = null;
	private DesiredCapabilities driverCapabilities = null;
	private WebDriverWait driverWait = null;
	private String flavour = null;


	public DriverFactory(String flavour) {
		this.flavour = flavour;
		loadMasterProperties(flavour);
		loadDesiredCapabilities();
		quitDriver();
		initDriver();
	}

	private void quitDriver() {
   try
   {
	   driver=DriverSession.getLastExecutionDriver();
	   if(driver!=null)
		   driver.quit();
	}
 catch(Exception e)
{
	 e.printStackTrace();
  }
	}

	void initDriver() {
		try {
			if (flavour.equals("iosApp") || flavour.equals("androidApp")|| flavour.equals("WAP")) {
				AndroidDriver<MobileElement> appDriver = new AndroidDriver<MobileElement>(new URL(
						"http://127.0.0.1:4723/wd/hub"), driverCapabilities);;
						DriverSession.setAppDriver(appDriver);
						driver=appDriver;
			}
			else
			{
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")
								+ "/drivers/chromedriver.exe");
				driver = new ChromeDriver();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void loadDesiredCapabilities() {
		driverCapabilities = new DesiredCapabilities();
		try {
			Enumeration<?> e = masterProp.propertyNames();
			while (e.hasMoreElements()) {
				String prop = (String) e.nextElement();
				if (prop.startsWith("cap_")) {

					String capName = prop.replaceAll("cap_", "");
					String capVal = masterProp.getProperty(prop);

					if (capName.equals("app"))
						capVal = System.getProperty("user.dir") + "/app/"
								+ capVal;

					if (capVal.equals("<NULL>"))
						capVal = "";
					driverCapabilities.setCapability(capName, capVal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void loadMasterProperties(String flavour) {
		masterProp = new Properties();
		String fileLoc = System.getProperty("user.dir") + "/" + flavour
				+ ".properties";
		try {
			File f = new File(fileLoc);
			FileInputStream fis = new FileInputStream(f);
			masterProp.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriverWait getWait() {
		return driverWait;
	}

	public String getUrl() {
		return masterProp.getProperty("url");
	}
}
