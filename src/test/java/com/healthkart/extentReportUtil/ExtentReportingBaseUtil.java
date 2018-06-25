package com.healthkart.extentReportUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.healthkart.hkAutomation.browser.BrowserFactory;
import com.healthkart.hkAutomation.business.HK_AccountsLoginPage_b;
import com.healthkart.hkAutomation.business.HK_BrandsPage_b;
import com.healthkart.hkAutomation.business.HK_CartPage_b;
import com.healthkart.hkAutomation.business.HK_CategoryListPage_b;
import com.healthkart.hkAutomation.business.HK_CompareVariantPage_b;
import com.healthkart.hkAutomation.business.HK_GuestLoginPage_b;
import com.healthkart.hkAutomation.business.HK_HomePage_b;
import com.healthkart.hkAutomation.business.HK_MyProfile_b;
import com.healthkart.hkAutomation.business.HK_OrderSuccessPage_b;
import com.healthkart.hkAutomation.business.HK_PaymentPage_b;
import com.healthkart.hkAutomation.business.HK_PdpPage_b;
import com.healthkart.hkAutomation.business.HK_SearchPage_b;
import com.healthkart.hkAutomation.business.HK_SecurePaytmWallet_b;
import com.healthkart.hkAutomation.business.HK_SelectAddress_b;
import com.healthkart.hkAutomation.business.HK_WriteAReviewPage_b;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.HK_Data_Provider;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.healthkart.hkMobileAutomation.business.HK_Account_Steps_Android;
import com.healthkart.hkMobileAutomation.business.HK_Cart_Steps_Android;
import com.healthkart.hkMobileAutomation.business.HK_Category_Steps_Android;
import com.healthkart.hkMobileAutomation.business.HK_Checkout_Steps_Android;
import com.healthkart.hkMobileAutomation.business.HK_Home_Steps_Android;
import com.healthkart.hkMobileAutomation.business.HK_Login_Steps_Android;
import com.healthkart.hkMobileAutomation.business.HK_NavigationSteps_Android;
import com.healthkart.hkMobileAutomation.business.HK_Order_Success_Steps_Android;
import com.healthkart.hkMobileAutomation.business.HK_PDP_Steps_Android;
import com.healthkart.hkMobileAutomation.business.HK_Payment_Steps_Android;
import com.healthkart.hkMobileAutomation.business.HK_Wishlist_Steps_Android;
import com.healthkart.hkMsiteAutomation.business.HK_Accounts_Login_Steps_AndroidMsite;
import com.healthkart.hkMsiteAutomation.business.HK_Address_Steps_AndroidMsite;
import com.healthkart.hkMsiteAutomation.business.HK_Brands_Steps_AndroidMsite;
import com.healthkart.hkMsiteAutomation.business.HK_Cart_Steps_AndroidMsite;
import com.healthkart.hkMsiteAutomation.business.HK_Category_Steps_AndroidMsite;
import com.healthkart.hkMsiteAutomation.business.HK_Compare_Variant_Steps_AndroidMsite;
import com.healthkart.hkMsiteAutomation.business.HK_Guest_Login_Steps_AndroidMsite;
import com.healthkart.hkMsiteAutomation.business.HK_Home_Steps_AndroidMsite;
import com.healthkart.hkMsiteAutomation.business.HK_Login_Steps_AndroidMsite;
import com.healthkart.hkMsiteAutomation.business.HK_My_Profile_Steps_AndroidMsite;
import com.healthkart.hkMsiteAutomation.business.HK_Order_Success_Steps_AndroidMsite;
import com.healthkart.hkMsiteAutomation.business.HK_PDP_Steps_AndroidMsite;
import com.healthkart.hkMsiteAutomation.business.HK_Payment_Steps_AndroidMsite;
import com.healthkart.hkMsiteAutomation.business.HK_Search_Steps_AndroidMsite;
import com.healthkart.hkMsiteAutomation.business.HK_Write_A_Review_Steps_AndroidMsite;
import com.healthkart.hkRetailAutomation.business.HK_Retail_Base_Order_Steps;
import com.healthkart.hkRetailAutomation.business.HK_Retail_Home_Steps;
import com.healthkart.hkRetailAutomation.business.HK_Retail_Login_Steps;
import com.healthkart.hkRetailAutomation.business.HK_Retail_Omni_Chnl_Orders_Steps;
import com.healthkart.hkRetailAutomation.business.HK_Retail_POS_Steps;
import com.healthkart.hkRetailAutomation.business.HK_Retail_RTO_Checkin_Steps;
import com.healthkart.hkRetailAutomation.business.HK_Retail_RTO_Steps;
import com.healthkart.hkRetailAutomation.business.HK_Retail_Shipping_Order_Steps;
import com.mb.MsiteAutomation.business.MBmsite_CartSteps;
import com.mb.MsiteAutomation.business.MBmsite_GuestLoginSteps;
import com.mb.MsiteAutomation.business.MBmsite_HomeSteps;
import com.mb.MsiteAutomation.business.MBmsite_OrderSuccessSteps;
import com.mb.MsiteAutomation.business.MBmsite_PaymentSteps;
import com.mb.MsiteAutomation.business.MBmsite_PdpSteps;
import com.mb.MsiteAutomation.business.MBmsite_SelectAddressSteps;
import com.mb.webAutomation.business.MB_CartSteps;
import com.mb.webAutomation.business.MB_GuestLoginSteps;
import com.mb.webAutomation.business.MB_HomeSteps;
import com.mb.webAutomation.business.MB_OrderSuccessSteps;
import com.mb.webAutomation.business.MB_PaymentSteps;
import com.mb.webAutomation.business.MB_PdpSteps;
import com.mb.webAutomation.business.MB_SelectAddressSteps;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;

public class ExtentReportingBaseUtil {
	
    public static ExtentReports extentReports;
	public static ExtentTest test;
	protected Map<String,String> testdata;
	private HK_Data_Provider data;
	protected CommonFunctions comnFunc;
	protected WebDriver driver;
	protected AndroidDriver<WebElement> androidDriver;
	
	/*
	 * Desktop Web variables
	 */
	protected HK_HomePage_b homeB;
	protected HK_MyProfile_b myProfB;
	protected HK_GuestLoginPage_b guestLoginB;
	protected HK_AccountsLoginPage_b AccHkB;
	protected HK_CategoryListPage_b catLsB;
	protected HK_BrandsPage_b brndB;
	protected HK_SearchPage_b srchB;
	protected HK_PdpPage_b pdpB;
	protected HK_CompareVariantPage_b compareVarB;
	protected HK_WriteAReviewPage_b warB;
	protected HK_CartPage_b cartB;
	protected HK_SelectAddress_b addB;
	protected HK_PaymentPage_b pymntB;
	protected HK_SecurePaytmWallet_b paytmB;
	protected HK_OrderSuccessPage_b ordrSuccB;
	
	/*
	 * MB Desktop Web variables
	 */
	
	protected MB_HomeSteps homeStepsMb;
	protected MB_PdpSteps pdpStepsMb;
	protected MB_CartSteps cartStepsMb;
	protected MB_GuestLoginSteps guestLoginStepsMb;
	protected MB_SelectAddressSteps selectAddressStepsMb;
	protected MB_PaymentSteps paymentStepsMb;
	protected MB_OrderSuccessSteps orderSuccessStepsMb;
	
	/*
	 * MB Msite variables
	 */
	
	protected MBmsite_HomeSteps homeStepsMbMsite;
	protected MBmsite_PdpSteps pdpStepsMbMsite;
	protected MBmsite_CartSteps cartStepsMbMsite;
	protected MBmsite_GuestLoginSteps guestLoginStepsMbMsite;
	protected MBmsite_SelectAddressSteps selectAddressStepsMbMsite;
	protected MBmsite_PaymentSteps paymentStepsMbMsite;
	protected MBmsite_OrderSuccessSteps orderSuccessStepsMbMsite;
	
	/*
	 * Android app variables
	 */
	protected HK_Home_Steps_Android homeSteps;
	protected HK_Login_Steps_Android loginSteps;
	protected String mobileNumber;
	protected HK_PDP_Steps_Android pdpSteps;
	protected HK_Cart_Steps_Android cartSteps;
	protected HK_Checkout_Steps_Android checkout;
	protected HK_Payment_Steps_Android paymentSteps;
	protected HK_Order_Success_Steps_Android orderSteps;
	protected String password;
	protected String fbUserName;
	protected String fbUserMobile;
	protected String fbPassword;
	protected String googleUserName;
	protected String googlePassword;
	protected HK_NavigationSteps_Android navigationSteps;
	protected HK_Category_Steps_Android categorySteps;
	protected HK_Account_Steps_Android accountSteps;
	protected HK_Wishlist_Steps_Android wishListSteps;
	
	/*
	 * Android msite variables
	 */
	protected HK_Login_Steps_AndroidMsite loginStep;
	protected HK_Accounts_Login_Steps_AndroidMsite AccLoginSteps;
	protected HK_Guest_Login_Steps_AndroidMsite guestLoginSteps;
	protected HK_Category_Steps_AndroidMsite catSteps;
	protected HK_Brands_Steps_AndroidMsite brndSteps;
	protected HK_Search_Steps_AndroidMsite srchSteps;	
	protected HK_Home_Steps_AndroidMsite homeStep;
	protected HK_My_Profile_Steps_AndroidMsite profSteps;
	protected HK_PDP_Steps_AndroidMsite pdpStep;
	protected HK_Compare_Variant_Steps_AndroidMsite cmprSteps;
	protected HK_Write_A_Review_Steps_AndroidMsite reviewSteps;
	protected HK_Cart_Steps_AndroidMsite cartStep;
	protected HK_Address_Steps_AndroidMsite addSteps;
	protected HK_Payment_Steps_AndroidMsite pymntSteps;
	protected HK_Order_Success_Steps_AndroidMsite orderSuccessSteps;
	
	/*
	 * Retail POS variables
	 */
	protected HK_Retail_Login_Steps posLoginSteps;
	protected HK_Retail_Home_Steps posHomeSteps;
	protected HK_Retail_POS_Steps posSteps;
	protected HK_Retail_Omni_Chnl_Orders_Steps omniChnlOrdrSteps;
	protected HK_Retail_Base_Order_Steps posBOSteps;
	protected HK_Retail_Shipping_Order_Steps posSOSteps;
	protected HK_Retail_RTO_Steps posRTOSteps;
	protected HK_Retail_RTO_Checkin_Steps posRTOChknSteps;
	
	File dir;
    String zipDirName;
	
    @Parameters({"flavour","browserOrDevice"})
	@BeforeSuite
	public void beforeSuite(String flavour, String browserOrDevice){
    	
    	
    	
    	String extentReportPath = null;
    	if("web".equalsIgnoreCase(flavour)) {
    		if("chrome".equalsIgnoreCase(browserOrDevice)) {
        		extentReportPath = PropertyHelper.readProperty("extentReport_ChromeWeb");
        		dir = new File(System.getProperty("user.dir")+ PropertyHelper.readProperty("extentReport_ChromeWeb"));
        		zipDirName = System.getProperty("user.dir")+"\\target\\extentReport_ChromeWeb.zip";
        	}
        	if("firefox".equalsIgnoreCase(browserOrDevice)) {
        		extentReportPath = PropertyHelper.readProperty("extentReport_FirefoxWeb");
        		dir = new File(System.getProperty("user.dir")+ PropertyHelper.readProperty("extentReport_FirefoxWeb"));
        		zipDirName = System.getProperty("user.dir")+"\\target\\extentReport_FirefoxWeb.zip";
        	}
    	}
    	if("androidApp".equalsIgnoreCase(flavour)) {
    		if("Samsung-J5".equalsIgnoreCase(browserOrDevice)) {
        		extentReportPath = PropertyHelper.readProperty("extentReport_AndroidAppV6");
        		dir = new File(System.getProperty("user.dir")+ PropertyHelper.readProperty("extentReport_AndroidAppV6"));
        		zipDirName = System.getProperty("user.dir")+"\\target\\extentReport_AndroidAppV6.zip";
        	}
        	if("MI-A1".equalsIgnoreCase(browserOrDevice)) {
        		extentReportPath = PropertyHelper.readProperty("extentReport_AndroidAppV7");
        		dir = new File(System.getProperty("user.dir")+ PropertyHelper.readProperty("extentReport_AndroidAppV7"));
        		zipDirName = System.getProperty("user.dir")+"\\target\\extentReport_AndroidAppV7.zip";
        	}
    	}
    	if("msite".equalsIgnoreCase(flavour)) 
    	{
    		if("chrome".equalsIgnoreCase(browserOrDevice)) 
    		{
        		extentReportPath = PropertyHelper.readProperty("extentReport_ChromeMsite");
        		dir = new File(System.getProperty("user.dir")+ PropertyHelper.readProperty("extentReport_ChromeMsite"));
        		zipDirName = System.getProperty("user.dir")+"\\target\\extentReport_ChromeMsite.zip";
        	}
    	}
    		
    		if("MBweb".equalsIgnoreCase(flavour)) {
        		if("chrome".equalsIgnoreCase(browserOrDevice)) {
            		extentReportPath = PropertyHelper.readProperty("MBextentReport_ChromeWeb");
            		dir = new File(System.getProperty("user.dir")+ PropertyHelper.readProperty("MBextentReport_ChromeWeb"));
            		zipDirName = System.getProperty("user.dir")+"\\target\\MBextentReport_ChromeWeb.zip";
            	}
        	}
    		
    		if("MBmsite".equalsIgnoreCase(flavour)) 
        	{
        		if("chrome".equalsIgnoreCase(browserOrDevice)) 
        		{
            		extentReportPath = PropertyHelper.readProperty("extentReport_MBMsite");
            		dir = new File(System.getProperty("user.dir")+ PropertyHelper.readProperty("extentReport_MBMsite"));
            		zipDirName = System.getProperty("user.dir")+"\\target\\extentReport_MBMsite.zip";
            	}
        	}
    		
    	
    	if("RetailWeb".equalsIgnoreCase(flavour)) {
    		if("chrome".equalsIgnoreCase(browserOrDevice)) {
        		extentReportPath = PropertyHelper.readProperty("extentReport_RetailWeb");
        		dir = new File(System.getProperty("user.dir")+ PropertyHelper.readProperty("extentReport_RetailWeb"));
        		zipDirName = System.getProperty("user.dir")+"\\target\\extentReport_RetailWeb.zip";
        	}    		
		}
    	
		try {
			FileUtils.cleanDirectory(new File(System.getProperty("user.dir")+ extentReportPath+ "/Screenshots"));
			
		} catch (IOException e) {
			System.out.println("IO Exception caught while cleaning screenshots..!!");
		}
		catch (IllegalArgumentException e) {
			System.out.println("IllegalArgument Exception caught while cleaning screenshots..!!");
		}
		extentReports = new ExtentReports(System.getProperty("user.dir")+ extentReportPath+ "/HKExtentReports.html",true);
		extentReports.loadConfig(new File (System.getProperty("user.dir")+ PropertyHelper.readProperty("extentConfig")));
	}
		
    /**
     * Open browser and visit Application URL.
     * @throws IOException 
     */
	@Parameters({"flavour","browserOrDevice"})
	@BeforeMethod
	public void setup(Method method, String flavour, String browserOrDevice) throws IOException 
	{
		test = extentReports.startTest((this.getClass().getSimpleName() + "::" + method.getName()), method.getName());
    	comnFunc = new CommonFunctions();
		data=new HK_Data_Provider();
		data.loadDataMap();
		testdata=data.getDataMap();
		GlobalVar.test = test;
		GlobalVar.browserName = browserOrDevice;		
		driver = BrowserFactory.launchBrowser(flavour,browserOrDevice);
		
		if("web".equalsIgnoreCase(flavour)) {
			
			homeB = PageFactory.initElements(driver,HK_HomePage_b.class);
			myProfB = PageFactory.initElements(driver,HK_MyProfile_b.class);
			guestLoginB = PageFactory.initElements(driver,HK_GuestLoginPage_b.class);
			AccHkB = PageFactory.initElements(driver,HK_AccountsLoginPage_b.class);
			catLsB = PageFactory.initElements(driver,HK_CategoryListPage_b.class);
			brndB = PageFactory.initElements(driver,HK_BrandsPage_b.class);
			srchB = PageFactory.initElements(driver,HK_SearchPage_b.class);
			pdpB = PageFactory.initElements(driver,HK_PdpPage_b.class);
			compareVarB = PageFactory.initElements(driver,HK_CompareVariantPage_b.class);
			warB = PageFactory.initElements(driver,HK_WriteAReviewPage_b.class);
			cartB = PageFactory.initElements(driver,HK_CartPage_b.class);
			addB = PageFactory.initElements(driver,HK_SelectAddress_b.class);		
			pymntB = PageFactory.initElements(driver,HK_PaymentPage_b.class);
			paytmB = PageFactory.initElements(driver,HK_SecurePaytmWallet_b.class);
			ordrSuccB = PageFactory.initElements(driver,HK_OrderSuccessPage_b.class);
			
			if(GlobalVar.jenkinsEnvironment==null)
			{
	            if("prod".equals(PropertyHelper.readProperty("env")))
	            {
	            	GlobalVar.baseAppURL=testdata.get("Prod_Url");	            	
	            }
	            else
	            {
	            	GlobalVar.baseAppURL=testdata.get("Stag_Url");
	            	System.out.println(testdata.get("Stag_Url"));
	            }
	         }
			else
			{
	        	if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
				{
					if(GlobalVar.jenkinsApplicationURL != null) 
					{
						GlobalVar.baseAppURL=GlobalVar.jenkinsApplicationURL;
					}
					else
					{
						GlobalVar.baseAppURL=testdata.get("Prod_Url");
					}	
				}
				else if("qa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
				{
					if(GlobalVar.jenkinsApplicationURL != null)
					{
						GlobalVar.baseAppURL=GlobalVar.jenkinsApplicationURL;
					}
					else
					{
						GlobalVar.baseAppURL=testdata.get("Stag_Url");
					}
				}
	         }
			System.out.println("Application under testing URL : "+GlobalVar.baseAppURL);
	    	test.log(LogStatus.INFO, "Application under testing URL : "+GlobalVar.baseAppURL);	    	
	    	try 
	    	{
	    		driver.get(GlobalVar.baseAppURL);
	    		WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);
	    	}
	    	
			catch (org.openqa.selenium.TimeoutException e) 
	    	{
				System.out.println("@@@@..PAGE LOAD TIMEOUT EXCEPTION OCCURED..@@@@");
				test.log(LogStatus.INFO, "@@@@..PAGE LOAD TIMEOUT EXCEPTION OCCURED..@@@@");
				//((JavascriptExecutor)driver).executeScript("window.stop();");
			}
	    	System.out.println("Browser Launched successfully..");
			test.log(LogStatus.INFO, "Browser Launched successfully..");
			
		}
		
		if("MBWeb".equalsIgnoreCase(flavour))
		{
			homeStepsMb = PageFactory.initElements(driver,MB_HomeSteps.class);
			pdpStepsMb = PageFactory.initElements(driver, MB_PdpSteps.class);
			cartStepsMb = PageFactory.initElements(driver, MB_CartSteps.class);
			guestLoginStepsMb = PageFactory.initElements(driver, MB_GuestLoginSteps.class);
			selectAddressStepsMb = PageFactory.initElements(driver, MB_SelectAddressSteps.class);
			paymentStepsMb = PageFactory.initElements(driver, MB_PaymentSteps.class);
			orderSuccessStepsMb = PageFactory.initElements(driver, MB_OrderSuccessSteps.class);
			mobileNumber=testdata.get("Login_User_Mobile");
			password=testdata.get("Login_User_Mobile_Password");
			fbUserName=testdata.get("Facebook_Login_EmailId");
			fbUserMobile=testdata.get("Facebook_User_App_Mobile");
			fbPassword=testdata.get("Facebook_Login_Password");
			googleUserName=testdata.get("Google_Login_Email_Id");
			googlePassword=testdata.get("Google_Login_Password");
			if(GlobalVar.jenkinsEnvironment==null)
			{
	            if("prod".equals(PropertyHelper.readProperty("env")))
	            {
	            	GlobalVar.baseAppURL=testdata.get("MBProd_Url");	            	
	            }
	            else
	            {
	            	GlobalVar.baseAppURL=testdata.get("MBStag_Url");
	            }
	         }
			else
			{
	        	if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
				{
					if(GlobalVar.jenkinsApplicationURL != null) 
					{
						GlobalVar.baseAppURL=GlobalVar.jenkinsApplicationURL;
					}
					else
					{
						GlobalVar.baseAppURL=testdata.get("MBProd_Url");
					}	
				}
				else if("qa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
				{
					if(GlobalVar.jenkinsApplicationURL != null)
					{
						GlobalVar.baseAppURL=GlobalVar.jenkinsApplicationURL;
					}
					else
					{
						GlobalVar.baseAppURL=testdata.get("MBStag_Url");
					}
				}
	         }
			System.out.println("Application under testing URL : "+GlobalVar.baseAppURL);
	    	test.log(LogStatus.INFO, "Application under testing URL : "+GlobalVar.baseAppURL);	    	
	    	try 
	    	{
	    		driver.get(GlobalVar.baseAppURL);
	    	}
	    	
			catch (org.openqa.selenium.TimeoutException e) 
	    	{
				System.out.println("@@@@..PAGE LOAD TIMEOUT EXCEPTION OCCURED..@@@@");
				test.log(LogStatus.INFO, "@@@@..PAGE LOAD TIMEOUT EXCEPTION OCCURED..@@@@");
				//((JavascriptExecutor)driver).executeScript("window.stop();");
			}
	    	System.out.println("Browser Launched successfully..");
			test.log(LogStatus.INFO, "Browser Launched successfully..");
		}
		
		if("MBmsite".equalsIgnoreCase(flavour))
		{
			androidDriver = BrowserFactory.getAndroidDriver();
			homeStepsMbMsite = new MBmsite_HomeSteps(androidDriver); // .initElements(androidDriver,MBmsite_HomeSteps.class);
			pdpStepsMbMsite = new MBmsite_PdpSteps(androidDriver); // PageFactory.initElements(androidDriver, MBmsite_PdpSteps.class);
			cartStepsMbMsite = new MBmsite_CartSteps(androidDriver); //.initElements(androidDriver, MBmsite_CartSteps.class);
			guestLoginStepsMbMsite = new MBmsite_GuestLoginSteps(androidDriver); //.initElements(androidDriver, MBmsite_GuestLoginSteps.class);
			selectAddressStepsMbMsite = new MBmsite_SelectAddressSteps(androidDriver); //.initElements(androidDriver, MBmsite_SelectAddressSteps.class);
			paymentStepsMbMsite = new MBmsite_PaymentSteps(androidDriver); //.initElements(androidDriver, MBmsite_PaymentSteps.class);
			orderSuccessStepsMbMsite = new MBmsite_OrderSuccessSteps(androidDriver);
			mobileNumber=testdata.get("Login_User_Mobile");
			password=testdata.get("Login_User_Mobile_Password");
			fbUserName=testdata.get("Facebook_Login_EmailId");
			fbUserMobile=testdata.get("Facebook_User_App_Mobile");
			fbPassword=testdata.get("Facebook_Login_Password");
			googleUserName=testdata.get("Google_Login_Email_Id");
			googlePassword=testdata.get("Google_Login_Password");
			if(GlobalVar.jenkinsEnvironment==null)
			{
	            if("prod".equals(PropertyHelper.readProperty("env")))
	            {
	            	GlobalVar.baseAppURL=testdata.get("MBProd_Url");	            	
	            }
	            else
	            {
	            	GlobalVar.baseAppURL=testdata.get("MBStag_Url");
	            }
	         }
			else
			{
	        	if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
				{
					if(GlobalVar.jenkinsApplicationURL != null) 
					{
						GlobalVar.baseAppURL=GlobalVar.jenkinsApplicationURL;
					}
					else
					{
						GlobalVar.baseAppURL=testdata.get("MBProd_Url");
					}	
				}
				else if("qa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
				{
					if(GlobalVar.jenkinsApplicationURL != null)
					{
						GlobalVar.baseAppURL=GlobalVar.jenkinsApplicationURL;
					}
					else
					{
						GlobalVar.baseAppURL=testdata.get("MBStag_Url");
					}
				}
	         }
			System.out.println("Application under testing URL : "+GlobalVar.baseAppURL);
	    	test.log(LogStatus.INFO, "Application under testing URL : "+GlobalVar.baseAppURL);	    	
	    	try 
	    	{
	    		driver.get(GlobalVar.baseAppURL);
	    	}
	    	
			catch (org.openqa.selenium.TimeoutException e) 
	    	{
				System.out.println("@@@@..PAGE LOAD TIMEOUT EXCEPTION OCCURED..@@@@");
				test.log(LogStatus.INFO, "@@@@..PAGE LOAD TIMEOUT EXCEPTION OCCURED..@@@@");
				//((JavascriptExecutor)driver).executeScript("window.stop();");
			}
	    	System.out.println("Browser Launched successfully..");
			test.log(LogStatus.INFO, "Browser Launched successfully..");
		}
		
		if("androidApp".equalsIgnoreCase(flavour)) {
			
			androidDriver = BrowserFactory.getAndroidDriver();
			homeSteps = new HK_Home_Steps_Android(androidDriver);
			loginSteps = new HK_Login_Steps_Android(androidDriver);
			pdpSteps = new HK_PDP_Steps_Android(androidDriver);
			cartSteps = new HK_Cart_Steps_Android(androidDriver);
			checkout = new HK_Checkout_Steps_Android(androidDriver);
			paymentSteps = new HK_Payment_Steps_Android(androidDriver);
			orderSteps = new HK_Order_Success_Steps_Android(androidDriver);
			navigationSteps = new HK_NavigationSteps_Android(androidDriver);
			categorySteps = new HK_Category_Steps_Android(androidDriver);
			accountSteps = new HK_Account_Steps_Android(androidDriver);
			wishListSteps = new HK_Wishlist_Steps_Android(androidDriver);
			mobileNumber=testdata.get("Login_User_Mobile_APP");
			password=testdata.get("Login_User_Mobile_Password_App");
			fbUserName=testdata.get("Login_User_Facebook_App");
			fbUserMobile=testdata.get("Facebook_User_App_Mobile");
			fbPassword=testdata.get("Login_User_Facebook_Password_App");
			
			navigationSteps.swipeNavigationPage();
			test.log(LogStatus.PASS, "App Launched successfully..");
		}
		
		if("msite".equalsIgnoreCase(flavour)) {
			
			androidDriver = BrowserFactory.getAndroidDriver();
			loginStep = new HK_Login_Steps_AndroidMsite(androidDriver);
			AccLoginSteps = new HK_Accounts_Login_Steps_AndroidMsite(androidDriver);
			guestLoginSteps = new HK_Guest_Login_Steps_AndroidMsite(androidDriver);
			catSteps = new HK_Category_Steps_AndroidMsite(androidDriver);
			brndSteps = new HK_Brands_Steps_AndroidMsite (androidDriver);
			srchSteps = new HK_Search_Steps_AndroidMsite (androidDriver);			
			homeStep = new HK_Home_Steps_AndroidMsite(androidDriver);
			profSteps = new HK_My_Profile_Steps_AndroidMsite(androidDriver);
			pdpStep = new HK_PDP_Steps_AndroidMsite(androidDriver);
			cmprSteps = new HK_Compare_Variant_Steps_AndroidMsite(androidDriver);
			reviewSteps = new HK_Write_A_Review_Steps_AndroidMsite(androidDriver);
			cartStep = new HK_Cart_Steps_AndroidMsite(androidDriver);		
			addSteps = new HK_Address_Steps_AndroidMsite(androidDriver);
			pymntSteps = new HK_Payment_Steps_AndroidMsite(androidDriver);
			orderSuccessSteps = new HK_Order_Success_Steps_AndroidMsite(androidDriver);
			
			if(GlobalVar.jenkinsEnvironment==null)
			{
	            if("prod".equals(PropertyHelper.readProperty("env")))
	            {
	            	GlobalVar.baseAppURL=testdata.get("Prod_Url");	            	
	            }
	            else
	            {
	            	GlobalVar.baseAppURL=testdata.get("Stag_Url");
	            }
	         }
			else
			{
	        	if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
				{
					if(GlobalVar.jenkinsApplicationURL != null) 
					{
						GlobalVar.baseAppURL=GlobalVar.jenkinsApplicationURL;
					}
					else
					{
						GlobalVar.baseAppURL=testdata.get("Prod_Url");
					}	
				}
				else if("qa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
				{
					if(GlobalVar.jenkinsApplicationURL != null)
					{
						GlobalVar.baseAppURL=GlobalVar.jenkinsApplicationURL;
					}
					else
					{
						GlobalVar.baseAppURL=testdata.get("Stag_Url");
					}
				}
	         }
			System.out.println("Application under testing URL : "+GlobalVar.baseAppURL);
	    	test.log(LogStatus.INFO, "Application under testing URL : "+GlobalVar.baseAppURL);	    	
	    	try 
	    	{
	    		driver.get(GlobalVar.baseAppURL);
	    	}
	    	
			catch (org.openqa.selenium.TimeoutException e) 
	    	{
				System.out.println("@@@@..PAGE LOAD TIMEOUT EXCEPTION OCCURED..@@@@");
				test.log(LogStatus.INFO, "@@@@..PAGE LOAD TIMEOUT EXCEPTION OCCURED..@@@@");
				//((JavascriptExecutor)driver).executeScript("window.stop();");
			}
	    	System.out.println("Browser Launched successfully..");
			test.log(LogStatus.INFO, "Browser Launched successfully..");
		}
		
		if("RetailWeb".equalsIgnoreCase(flavour)) {
			
			posLoginSteps=PageFactory.initElements(driver,HK_Retail_Login_Steps.class);
			posHomeSteps=PageFactory.initElements(driver,HK_Retail_Home_Steps.class);
			posSteps=PageFactory.initElements(driver,HK_Retail_POS_Steps.class);
			omniChnlOrdrSteps=PageFactory.initElements(driver,HK_Retail_Omni_Chnl_Orders_Steps.class);
			posBOSteps=PageFactory.initElements(driver,HK_Retail_Base_Order_Steps.class);
			posSOSteps=PageFactory.initElements(driver,HK_Retail_Shipping_Order_Steps.class);
			posRTOSteps=PageFactory.initElements(driver,HK_Retail_RTO_Steps.class);
			posRTOChknSteps=PageFactory.initElements(driver,HK_Retail_RTO_Checkin_Steps.class);
			
			if(GlobalVar.jenkinsEnvironment==null)
			{
	            if("prod".equals(PropertyHelper.readProperty("env")))
	            {
	            	GlobalVar.baseAppURL=testdata.get("Retail_Prod_Url");	            	
	            }
	            else
	            {
	            	GlobalVar.baseAppURL=testdata.get("Retail_Stag_Url");
	            	WebDriverUtil.dbActionsObj.addInventoryOnPos(testdata.get("POS_Store_Name"));
	            }
	         }
			else
			{
	        	if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
				{
					if(GlobalVar.jenkinsApplicationURL != null) 
					{
						GlobalVar.baseAppURL=GlobalVar.jenkinsRetailApplicationURL;
					}
					else
					{
						GlobalVar.baseAppURL=testdata.get("Retail_Prod_Url");
					}	
				}
				else if("qa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
				{
					WebDriverUtil.dbActionsObj.addInventoryOnPos(testdata.get("POS_Store_Name"));
					
					if(GlobalVar.jenkinsApplicationURL != null)
					{
						GlobalVar.baseAppURL=GlobalVar.jenkinsRetailApplicationURL;
					}
					else
					{
						GlobalVar.baseAppURL=testdata.get("Retail_Stag_Url");
					}
				}
	         }
			System.out.println("Application under testing URL : "+GlobalVar.baseAppURL);
	    	test.log(LogStatus.INFO, "Application under testing URL : "+GlobalVar.baseAppURL);	    	
	    	try 
	    	{
	    		driver.get(GlobalVar.baseAppURL);
	    	}
	    	
			catch (org.openqa.selenium.TimeoutException e) 
	    	{
				System.out.println("@@@@..PAGE LOAD TIMEOUT EXCEPTION OCCURED..@@@@");
				test.log(LogStatus.INFO, "@@@@..PAGE LOAD TIMEOUT EXCEPTION OCCURED..@@@@");
				//((JavascriptExecutor)driver).executeScript("window.stop();");
			}
	    	System.out.println("Browser Launched successfully..");
			test.log(LogStatus.INFO, "Browser Launched successfully..");
		}
	}
    
    public void logTestResult(ITestResult testResult, String flvr, String browserOrDevice, String paymentType)
    {
        int testngStatus = testResult.getStatus();
        if ( testngStatus == ITestResult.SUCCESS ) {
            String passed = "Test passed.";
            test.log(LogStatus.PASS, passed);
            
        } 
        else if ( testngStatus == ITestResult.FAILURE ) {
        	String relativeScreenShotPath=null;
        	try {
        		relativeScreenShotPath = WebDriverUtil.captureScreenshot(this.getClass().getSimpleName(), testResult.getMethod().getMethodName(), driver, flvr, browserOrDevice, paymentType);
        	} 
        	catch (Exception e) {
        		System.out.println("@@@@..SCREENSHOT COULD NOT BE CAPTURED DUE TO EXCEPTION..@@@@");
				test.log(LogStatus.INFO, "@@@@..SCREENSHOT COULD NOT BE CAPTURED DUE TO EXCEPTION..@@@@");
				System.out.println(e.getMessage());
			}
        	
			test.log(LogStatus.FAIL, testResult.getThrowable());
			test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(relativeScreenShotPath));
			String failed = "Test failed.";
			test.log(LogStatus.FAIL, failed);
        } 
        else if ( testngStatus == ITestResult.SKIP ) {
            String skipped = "Test skipped.";
            test.log(LogStatus.SKIP, skipped);            
        } 
        else {
            String error = "Test error.";
            test.log(LogStatus.ERROR, error);            
        }
    }
    
    @Parameters({"flavour","browserOrDevice","payment"})
    @AfterMethod
    public void afterMethod(ITestResult result, String flavour, String browserOrDevice, @Optional("N/A") String paymentType) {
    	try
    	{
    	logTestResult(result, flavour,browserOrDevice,paymentType);
    	String status = StringUtils.substringBefore(StringUtils.substringAfter(result.toString(), "status="), " ");
    	System.out.println(status);
    	if(status.equalsIgnoreCase("failure") && (flavour.equalsIgnoreCase("msite") || browserOrDevice.equalsIgnoreCase("chrome")))
    	{
    		WebDriverUtil.analyzeLogs();
    	}
    	
    	driver.quit();
		test.log(LogStatus.INFO, "driver and browser closed successfully..!!");
		extentReports.endTest(test);
		extentReports.flush();
    	}
    	catch(Exception e)
    	{
    		comnFunc.reportLogAndPrintInConsole("Failed to load WebDriver");
    	}
    }
    
   /* @AfterClass(alwaysRun=true)
	public void endTest() {
		extentReports.flush();    	
	}*/
    
    @AfterSuite
    public void endSuite() {
    	extentReports.close();
    	ZipFilesUtil zipFiles = new ZipFilesUtil();
        zipFiles.zipDirectory(dir, zipDirName);
    }
}
