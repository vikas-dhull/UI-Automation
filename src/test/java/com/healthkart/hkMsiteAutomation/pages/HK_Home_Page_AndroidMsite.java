package com.healthkart.hkMsiteAutomation.pages;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class HK_Home_Page_AndroidMsite extends CommonFunctions
{
	AndroidDriver<?> androidDriver;
	
	private By dwnldAppNav = By.xpath("//div[contains(@class,'appdownloadMobile_closeclicked')]");
	private By hkVoiceDemoCloseBtn = By.xpath("//a[@class='hk-voice-demo-btn']");
	private By closeAllowButton = By.xpath("//div[@id='nvpush_cross']");
	private By closeMenuIcon = By.xpath("//span[@class='close-btn']");
	private By userIconProfile = By.xpath("//*[@class='user-icon']");     //img[@class='user-icon']
	private By kickStartText = By.xpath("//h2[contains(text(),'Kick-start your fitness journey')]");
	private By shopWidgetText = By.xpath("//a[contains(@class,'shop_topbar_home_guest')]");
	private By consultWidgetText = By.xpath("//a[contains(@class,'consult_topbar_home_guest')]");
	private By connectWidgetText = By.xpath("//a[contains(@class,'connect_topbar_home_guest')]");
	private By askAnExpertWidgetText = By.xpath("//div[contains(text(),'Get an expert view on health and fitness queries')]");
	private By ShopByCategoryText = By.xpath("//h2[contains(text(),'Shop by Category')]");
	private By disclaimerText = By.xpath("//div[contains(@class,'disclaimer-footer')]");
	
	private By userLoginButton = By.xpath("//*[@class='user-icon showLoginPopup']");    //img[@class='user-icon showLoginPopup']
	private By topLeftNavigationButton = By.xpath("//div[contains(@class,'header-sprite-menu')]");
	private By closeMsiteMenu = By.xpath("//span[@class='close-btn']");
	private By logInTextElement = By.xpath("//span[@class='user-name']");
	private By userlogoutButton = By.xpath("//a[@data-event='userLogout' and contains(@class,'logout_account_mobileMenu')]");
	private By userSignupButton = By.xpath("//li[@class='registeruser']//a[contains(@class,'showSignupPopup')]");
	
	private By cartCounter = By.xpath("//a[contains(@class,'Proceed_to_cart_header')]/span");
	private By proceedToCart = By.xpath("//a[contains(@class,'Proceed_to_cart_header')]");
	private By TrendingWheyText = By.xpath("//span[contains(text(),'Trending In Whey Protein')]");
	private By TrendingNowText = By.xpath("//span[contains(text(),'Trending Now')]");
	private By firstPVWheyProtein = By.xpath("//span[contains(text(),'Trending In Whey Protein')]/ancestor::div[2]//div[@class='pd_name']");
	private By footerConnectWithUs = By.xpath("//div[@class='contact-with-us']/h4");
	private By yourAccount = By.xpath("//div[@class='lets-help']//a[text()='Your Account']");
	
	private By menuProteinSupplements = By.xpath("//h3[@class='mobile_menu_p1_proteinsupplements']");
	private By menuProteins = By.xpath("//a[text()='Proteins']");
	private By menuWheyProteins = By.xpath("//a[text()='Whey Proteins']");
	
	private By searchBox = By.xpath("//input[contains(@class,'hk-search-box')]");
	private By searchSubmit = By.xpath("//input[@value='SEARCH']");
	private By chooseMBBrand = By.xpath("//a[contains(@href,'BR-539')]//span[text()='Muscleblaze']");
	private By searchOverlay = By.xpath("//div[@id='hkSearchBoxResult']/ul");
	private By recentSrchList = By.xpath("//li[contains(@class,'recent-srch-items')]");
	private By trendingSrchList = By.xpath("//li[contains(@class,'trnding-items')]");
	private By trendingSrchPrdctList = By.xpath("//li[@class='trnd-prod']");
	
	private By closeNotifyVisitorOverlay = By.xpath("//a[@id='nv_js-modal-close-button']");
	private By signInButtonLeftPanelBy = By.xpath("//a[@class='personaSignInClass']");
	
	public HK_Home_Page_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
	}
	
	/*public void navigateToHealthKart()
	{
		// androidDriver = BrowserFactory.getAndroidDriver();
		// androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		androidDriver.get("http://staging.healthkart.com/?view=mobile");
	}*/
	
	/**
	 * Handle notify visitor[For PROD ENV only.]
	 */
	public void clickCloseNotifyVisitorOverlay() {
	    /*if(comnFunc.isElementPresentBy(androidDriver, closeNotifyVisitorOverlay))
	       	comnFunc.clickBy(closeNotifyVisitorOverlay);*/
		WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);
	}
	
	public void getHomePage() 
	{
		androidDriver.get(GlobalVar.baseAppURL);
	}
	
	public void closeAllowButton()
	{
		/*if(isElementPresentBy(androidDriver,closeAllowButton))
		{
			click(androidDriver.findElement(closeAllowButton));
		}
		else
		{
			System.out.println("Close Allow button not present..");
			GlobalVar.test.log(LogStatus.INFO, "Close Allow button not present..");
		}
		
		if(isElementDisplayedBy(androidDriver,dwnldAppNav))
		{
			clickBy(dwnldAppNav);
		}
		else
		{
			System.out.println("Download App widget not Displayed..");
			GlobalVar.test.log(LogStatus.INFO, "Download App widget not Displayed..");
		}*/
		
		/*if(GlobalVar.jenkinsEnvironment==null)        // Handle notify visitor overlay [For PROD ENV only.]
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				//clickCloseNotifyVisitorOverlay();
				WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			//clickCloseNotifyVisitorOverlay();
			WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);
		}*/
		
		WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);

		/*Set<String> contexts = androidDriver.getContextHandles();
		for(String c :contexts)
		{
			System.out.println(c);
			if(c.contains("NATIVE"))
			{
			androidDriver.context(c);
			try
			{
				androidDriver.findElement(By.id("android:id/button1")).click();
			}
			catch(NoSuchElementException e)
			{
				System.out.println("allow button not displayed");
			}
			}
			else if(c.contains("CHROMIUM"))
			{
				androidDriver.context(c);
			}
		}*/
		
		
	}
	
	public void clickUserLoginButton()
	{
		click(androidDriver.findElement(userLoginButton));
	}
	
	public void clickUserSignupButton() 
	{
		clickBy(userSignupButton);
	}
	
	public void clickUserLogoutButton()
	{
		scrollToObject(androidDriver,androidDriver.findElement(userlogoutButton));
		clickBy(userlogoutButton);
		// waitForElementToBeInvisible(androidDriver, 30, userlogoutButton);
	}
	
	public void clickTopLeftNavigationButton()
	{
		waitForElementToBeDisplayedBy(androidDriver, 10, topLeftNavigationButton);
		clickBy(topLeftNavigationButton);
	}
	
	public boolean verifyLoggedIn()
	{
		try
		{
			String logInText = androidDriver.findElement(logInTextElement).getText();
			if(logInText.contains("Hi"))
				return true;
			else
				return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public boolean verifyLoggedOut() {
		
		try
		{
			String logInText = androidDriver.findElement(logInTextElement).getText();
			if(logInText.contains("Have an account ?"))
				return true;
			else
				return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public void clickCloseMenuIcon() 
	{
		click(androidDriver.findElement(closeMenuIcon));
	}
	
	public String getCartCounter() 
	{
		String cartCount = null;
		if(isElementPresentBy(androidDriver, cartCounter)) 
		{
			WebElement element = androidDriver.findElement(cartCounter);
			cartCount = element.getText();
		}
		return cartCount;
	}
	
	public void clickProceedToCart() 
	{
		waitForElementToBePresent(androidDriver, 20, proceedToCart);
		if(isElementPresentBy(androidDriver, firstPVWheyProtein)) 
		{
			clickThroughJavaScript(androidDriver, androidDriver.findElement(proceedToCart));
			//click(androidDriver.findElement(proceedToCart));
		}
		else
		{
			System.out.println("proceed to cart element not present on home page..");
		}
	}

	public void clickFirstPVWheyProtein() 
	{
		if(isElementPresentBy(androidDriver, firstPVWheyProtein)) 
		{
			scrollToObject(androidDriver, androidDriver.findElement(TrendingWheyText));
			click(androidDriver.findElement(firstPVWheyProtein));
		}
	}
	
	public void clickYourAccount() 
	{
		scrollToObject(androidDriver,androidDriver.findElement(footerConnectWithUs));
		staticWait(3);
		click(androidDriver.findElement(yourAccount));
	}

	public void clickMsiteMenuClose() 
	{
		click(androidDriver.findElement(closeMsiteMenu));
	}
	
	public boolean verifyShopConsultConnectWidgets() 
	{
		boolean flag = false;
		if(isElementPresentBy(androidDriver, kickStartText))
		{
			if(isElementPresentBy(androidDriver, shopWidgetText) && isElementPresentBy(androidDriver, consultWidgetText) 
					&& isElementPresentBy(androidDriver, connectWidgetText))
			{
				System.out.println("Shop | Consult | Connect widgets are present on home page..");
				flag = true;
			}
		}
		return flag;
	}
	
	public boolean verifyIAWidgets() 
	{
		return isElementPresentBy(androidDriver, TrendingNowText);
	}
	
	public boolean verifyTrendingWheyProteinWidget() 
	{
		return isElementPresentBy(androidDriver, TrendingWheyText);
	}

	public boolean verifyAskAnExpertWidget() 
	{
		return isElementPresentBy(androidDriver, askAnExpertWidgetText);
	}

	public boolean verifyShopByCategoryWidget() 
	{
		return isElementPresentBy(androidDriver, ShopByCategoryText);
	}

	public boolean verifyDisclaimer() 
	{
		return isElementPresentBy(androidDriver, disclaimerText);
	}

	public void OpenMyProfile() 
	{
		clickBy(userIconProfile);
	}

	public void waitForUserSignup() 
	{
		waitForElementToBeDisplayedBy(androidDriver, 90, userIconProfile);
	}

	public void chooseProteinSupplementsCategory() 
	{
		clickBy(menuProteinSupplements);
		clickBy(menuProteins);
		clickBy(menuWheyProteins);
	}

	public void enterSearchBrandName() 
	{
		//sendKeysThroughJavaScript(androidDriver, androidDriver.findElement(searchBox), "Muscleblaze");
		sendKeys(androidDriver.findElement(searchBox), "Muscleblaze");
		androidDriver.pressKeyCode(AndroidKeyCode.BACK);
	}

	public void chooseMuscleBlazeBrandFromSearch() 
	{
		clickBy(chooseMBBrand);
	}

	public void enterSearchPacks() {
		// sendKeysThroughJavaScript(androidDriver, androidDriver.findElement(searchBox), "Pack of 2");
		sendKeys(androidDriver.findElement(searchBox), "Pack of 2");
		androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		clickBy(searchSubmit);
	}
	
	public void displaySrchOverlay() {
		staticWait(3);
		if(isElementPresentBy(androidDriver, searchBox)) {
			androidDriver.findElement(searchBox).click();
			if(isElementPresentBy(androidDriver, hkVoiceDemoCloseBtn)) {
				androidDriver.findElement(hkVoiceDemoCloseBtn).click();
			}
			staticWait(1);
			androidDriver.findElement(searchBox).sendKeys(" ");
			staticWait(1);
			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		}
	}
	
	public boolean verifySrchOverlayDisplayedOnHomePage() {
		return isElementPresentBy(androidDriver, searchOverlay);
	}

	public int getRecentSearchCountOnHomePage() {
		int count=0;
		androidDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		count = androidDriver.findElements(recentSrchList).size();
		androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		return count;
	}

	public int getTrendingSearchCountOnHomePage() {
		int count=0;
		androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		for(WebElement element : androidDriver.findElements(trendingSrchList)) {
			if(isElementPresent(androidDriver, element)) {
				count++;
			}
		}
		androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		return count;
	}

	public int getTrendingSearchProductCountOnHomePage() {
		int count=0;
		androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		for(WebElement element : androidDriver.findElements(trendingSrchPrdctList)) {
			if(isElementPresent(androidDriver, element)) {
				count++;
			}
		}
		androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		return count;
	}

	public void clickSignInLeftPanel() 
	{
		clickBy(signInButtonLeftPanelBy);
	}
}
