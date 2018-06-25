package com.healthkart.hkMobileAutomation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.healthkart.hkAutomation.browser.BrowserFactory;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class HK_Home_Page_Android extends CommonFunctions
{
	AndroidDriver<?> androidDriver;
	
	private By loginButton = By.id("com.healthkart.healthkart:id/loginBtnOB");
	private By tourPage = By.id("com.healthkart.healthkart:id/indicator");
	private By topLeftNavigationButton = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");
	private By loginButtonInLeftNavigation = By.id("com.healthkart.healthkart:id/hmm_login");
	private By loggedInText = By.id("com.healthkart.healthkart:id/hmm_user_name");
	private By firstTrendingProductBy = By.xpath("//android.widget.TextView[@text='Trending in Whey Protein']/parent::android.widget.LinearLayout/following-sibling::android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[@index=0]");
	private By firstSearchedProductBy = By.xpath("//android.support.v7.widget.RecyclerView//android.widget.LinearLayout");
	private By firstSearchedProductSearchListBy = By.xpath("//android.widget.ListView/android.widget.RelativeLayout[@index=0]");
	private By myAccountBy = By.id("com.healthkart.healthkart:id/hmm_my_account");
	private By signUpBy = By.id("com.healthkart.healthkart:id/hmm_signup");
	private By cartButtonBy = By.xpath("//android.widget.RelativeLayout[@resource-id='com.healthkart.healthkart:id/cart_view']");
	private By searchButtonBy = By.id("com.healthkart.healthkart:id/action_search");
	private By searchFieldBy = By.id("com.healthkart.healthkart:id/search_field");
	private By firstBrandNameBy = By.xpath("//android.widget.TextView[@resource-id='com.healthkart.healthkart:id/trendingSearchHeading']/following-sibling::android.widget.ListView/android.widget.RelativeLayout[@index=0]");
	private By trendingSearchListBy = By.xpath("//android.widget.ListView[@resource-id='com.healthkart.healthkart:id/trendingSearchList']/android.widget.RelativeLayout");
	private By trendingProductListBy = By.xpath("//android.widget.ListView[@resource-id='com.healthkart.healthkart:id/trendingProductsList']/android.widget.RelativeLayout");
	private By trendingProductsBy = By.id("com.healthkart.healthkart:id/trendingProductsHeading");
	private By trendingNowLabelBy = By.xpath("//android.widget.TextView[@text='Trending in Whey Protein']");
	private By relatedProductsBy = By.id("com.healthkart.healthkart:id/trendingProductsHeading");
	private By logoutButtonBy = By.xpath("//android.widget.Button[@text='Logout']");
	private By clearRecentSearchButtonBy = By.id("com.healthkart.healthkart:id/clearAllSearches");
	private By recentSearchListBy = By.xpath("//android.widget.ListView[@resource-id='com.healthkart.healthkart:id/recentSearchList']/android.widget.RelativeLayout");
	private By myWishlistBy = By.xpath("//android.widget.TextView[@text='My Wishlist']");
	private By wishlistItemRemoveBy = By.xpath("//android.widget.ImageView[@resource-id='com.healthkart.healthkart:id/item_cross']");
	private By continueShoppingBy = By.id("com.healthkart.healthkart:id/continue_shopping");
	//	private By searchedProduct = By
	
	public HK_Home_Page_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
	}
	
	public void swipeNavigationPage()
	{
		androidDriver = BrowserFactory.getAndroidDriver();
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(androidDriver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(tourPage));
		scrollAndSwipeByElementAndroid(androidDriver, loginButton, "left");
		/*while(androidDriver.findElements(loginButton).isEmpty())
		{		
			Dimension size = androidDriver.manage().window().getSize();
			int startx = (int) (size.width * 0.70);
			int endx = (int) (size.width * 0.01);
			int starty = size.height / 2;
		  	System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);
		  	androidDriver.swipe(startx, starty, endx, starty, 3000);
		} */ 	
	}
	
	public void clickTopLeftNavigationButton()
	{
//		androidDriver.tap(1, androidDriver.findElement(topLeftNavigationButton), 1);
		
//		System.out.println(androidDriver.getPageSource());
		
		clickBy(topLeftNavigationButton);
	}
	
	public void clickLoginButton()
	{
		WebDriverWait wait = new WebDriverWait(androidDriver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(loginButtonInLeftNavigation));
		staticWait(4);
		//androidDriver.tap(1, androidDriver.findElement(loginButtonInLeftNavigation), 1);
		clickBy(loginButtonInLeftNavigation);
	}
	
	public boolean verifyLoggedIn()
	{
		try
		{
			return androidDriver.findElement(loggedInText).isDisplayed();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public void clickFirstProduct() 
	{
		clickBy(searchButtonBy);
		sendKeys(androidDriver.findElement(searchFieldBy), "MuscleBlaze");
//		androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
		staticWait(10);
		WebElement mbSearchResult = androidDriver.findElement(By.xpath("//android.widget.ListView/android.widget.RelativeLayout"));
		click(mbSearchResult);
		/*CommonFunctions.moveToElementAppium(androidDriver, "Trending in Whey Protein");
		waitForElementToBeDisplayedBy(androidDriver, 30, trendingNowLabelBy);*/
		click(androidDriver.findElementByAndroidUIAutomator("textContains(\"MuscleBlaze Whey Gold\")"));
//		click(androidDriver.findElement(firstTrendingProductBy));
	}

	public void swipeToMyAccount() 
	{
		CommonFunctions.moveToElementAppium(androidDriver, "My Account");
	}

	public void clickMyAccount() 
	{
		clickBy(loggedInText);
	}
	
	public void clickMyAccountLogin() 
	{
		click(androidDriver.findElement(myAccountBy));		
	}

	public void clickSignUp() 
	{
		clickTopLeftNavigationButton();
		click(androidDriver.findElement(signUpBy));
	}

	public void clickCartButton() 
	{
		waitForElementToBeDisplayedBy(androidDriver, 30, cartButtonBy);
		click(androidDriver.findElement(cartButtonBy));
	}

	public void clickSearchButton() 
	{
		staticWait(5);
		clickBy(searchButtonBy);
	}

	public void searchProduct(String hkPromptOfferVariantName) 
	{
		staticWait(5);
		sendKeys(androidDriver.findElement(searchFieldBy), hkPromptOfferVariantName);
	}

	public void clickSearchedProduct(String hkPromptOfferVariantName) 
	{
/*		CommonFunctions.moveToElementAppium(androidDriver, hkPromptOfferVariantName);
		click(androidDriver.findElement(firstTrendingProduct));*/
		moveToElementAppium(androidDriver, "RELATED PRODUCTS");
//		androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
		androidDriver.pressKeyCode(AndroidKeyCode.BACK);
//		verticalScrollToByElementAndroid(androidDriver, relatedProductsBy);
		staticWait(4);
		clickBy(firstSearchedProductSearchListBy);
		
	}

	public void visitGoalWheyProteinCatPage() 
	{
		
	}

	public void selectCategory(String categoryName) 
	{
		String categoryXpath = "//android.widget.TextView[@text='" + categoryName + "']";
		staticWait(3);
		WebElement category = androidDriver.findElement(By.xpath(categoryXpath));
		waitForElementToBeClicked(androidDriver, 30, category);
		waitForElementToBePresent(androidDriver, 30, By.xpath(categoryXpath));
		
		click(category);
	}

	public void selectSubCategory(String subCategory, String productType) 
	{
		String subCategoryXpath = "//android.widget.TextView[@text='" + subCategory + "']";
		staticWait(3);
		WebElement subCategoryElement = androidDriver.findElement(By.xpath(subCategoryXpath));
		waitForElementToBeClicked(androidDriver, 30, subCategoryElement);
		click(subCategoryElement);
		
		String productTypeXpath = "//android.widget.TextView[@text='" + productType + "']";
		staticWait(3);
		WebElement productTypeElement = androidDriver.findElement(By.xpath(productTypeXpath));
		waitForElementToBeClicked(androidDriver, 30, productTypeElement);
		click(productTypeElement);
	}

	public void selectBrandFromSearchResult() 
	{
		waitForElementToBeClicked(androidDriver, 30, firstBrandNameBy);
		staticWait(3);
		scrollAndSwipeByElementAndroid(androidDriver, firstBrandNameBy, "up");
		clickBy(firstBrandNameBy);
	}
	
	public void clickFirstProductFromSearchResult()
	{
		waitForElementToBeClicked(androidDriver, 30, firstSearchedProductBy);
		click(androidDriver.findElement(firstSearchedProductBy));
	}

	public int getCountOfTrendingSearchItems() 
	{
		int count = androidDriver.findElements(trendingSearchListBy).size();
		reportLogAndPrintInConsole("Trending search count ::" + count);
		return count;
	}

	public int getCountOfTrendingProducts() 
	{
		int count = androidDriver.findElements(trendingProductListBy).size();
		reportLogAndPrintInConsole("Trending products count ::" + count);
		return count;
	}

	public void scrollToTrendingProducts() 
	{
		scrollAndSwipeByElementAndroid(androidDriver, trendingProductsBy, "up");
		
		Dimension size = androidDriver.manage().window().getSize();
		int startx = size.width / 2;
		int starty = (int) (size.height * 0.70);
		int endy = (int) (size.height * 0.02);					
	  	System.out.println("startx = " + startx + " ,starty = " + starty + " , endy = " + endy);
	  	TouchAction action = new TouchAction(androidDriver);
		action.press(startx, starty).waitAction(java.time.Duration.ofMillis(1000)).moveTo(startx, endy).release().perform();
		
	}

	public void clickRichMilkVariant() 
	{
		click(androidDriver.findElementByAndroidUIAutomator("textContains(\"MuscleBlaze\").textContains(\"Rich\")"));
	}

	public void swipeToLogoutButton() 
	{
		//CommonFunctions.moveToElementAppium(androidDriver, "Logout");
		scrollAndSwipeByElementAndroid(androidDriver, logoutButtonBy, "up");
	}

	public void clickLogoutButton() 
	{
		clickBy(logoutButtonBy);
	}

	public void clearRecentSearches() 
	{
		clickBy(clearRecentSearchButtonBy);
	}

	public int getCountOfRecentSearchItems() 
	{
		int count = androidDriver.findElements(trendingSearchListBy).size();
		reportLogAndPrintInConsole("Trending search count ::" + count);
		return count;
	}

	public void clickMyWishlist() 
	{
		scrollAndSwipeByElementAndroid(androidDriver, myWishlistBy, "up");
		clickBy(myWishlistBy);
	}

	public void emptyWishlist() 
	{
		boolean flag = false;
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		while(flag!=true)
		{
			try
			{
				androidDriver.findElement(continueShoppingBy);
				flag=true;
			}
			catch(NoSuchElementException e)
			{
				click(androidDriver.findElement(wishlistItemRemoveBy));
//				staticWait(4);
				flag=false;
			}
		}
		androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
	}

	public void clickContinueShopping() 
	{
		clickBy(continueShoppingBy);
	}

}
