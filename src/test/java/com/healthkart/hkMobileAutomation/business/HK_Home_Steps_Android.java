package com.healthkart.hkMobileAutomation.business;

import org.testng.Reporter;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.healthkart.hkMobileAutomation.pages.HK_Home_Page_Android;
import com.healthkart.hkMobileAutomation.pages.HK_Login_Page_Android;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;

public class HK_Home_Steps_Android
{
	AndroidDriver<?> androidDriver;
	HK_Home_Page_Android homePage;
	GenericDbActions dbActions;
	HK_Login_Page_Android loginPage;
	HK_Category_Steps_Android categorySteps;
	CommonFunctions common;

	public HK_Home_Steps_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		homePage = new HK_Home_Page_Android(androidDriver);
		loginPage = new HK_Login_Page_Android(androidDriver);
		dbActions = new GenericDbActions();
		categorySteps = new HK_Category_Steps_Android(androidDriver);
		common = new CommonFunctions();
	}
	
	public void proceedToLogin() 
	{
		homePage.clickTopLeftNavigationButton();
		homePage.clickLoginButton();
	}

	public void verifySuccessfullLogin() 
	{
		homePage.clickTopLeftNavigationButton();
		Assert.assertTrue(homePage.verifyLoggedIn());
	}

	public void selectFirstProduct() 
	{
		homePage.clickFirstProduct();
		/*searchProduct("MuscleBlaze");
		selectBrandFromSearchResult("Muscleblaze");
		categorySteps.selectProduct("MuscleBlaze Whey Protein");*/
		
	}

	public void openMyAccountPage() 
	{
		homePage.clickTopLeftNavigationButton();
		//homePage.swipeToMyAccount();
		homePage.clickMyAccount();
		WebDriverUtil.staticWait(2);
	}

	public void openMyAccountLoginPage() 
	{
		homePage.clickTopLeftNavigationButton();
		homePage.swipeToMyAccount();
		homePage.clickMyAccountLogin();
		WebDriverUtil.staticWait(2);
	}
	
	public void performSignupWithMobileOtpAndPswd(String mobileNumber) 
	{
    	dbActions.deleteSignUpUserData(mobileNumber,1);
		homePage.clickSignUp();
    	loginPage.enterMobileNumber(mobileNumber);
    	loginPage.clickSubmitSignUp();
    	loginPage.waitForSignUpButton();
		String otp = dbActions.getOtp(mobileNumber,1);
		loginPage.setOtp(otp);
		loginPage.enterPassword("Password123");
		loginPage.clickSubmitSignUp();
		loginPage.waitForTopLeftNavigationButton();
	}

	public void proceedToCart() 
	{
		androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		homePage.clickCartButton();
	}

	public void searchProduct(String hkPromptOfferVariantName) 
	{
		homePage.clickSearchButton();
		homePage.searchProduct(hkPromptOfferVariantName);
		
	}
	
	public void visitPageWithLessProducts()
	{
		homePage.clickTopLeftNavigationButton();
		homePage.selectCategory("Sports Nutrition");
		homePage.selectSubCategory("Gainers","Herbal Weight Gainers");
	}

	public void selectProductFromSearchResults(String hkPromptOfferVariantName) 
	{
		homePage.clickSearchedProduct(hkPromptOfferVariantName);
	}

	public void selectBrandFromSearchResult(String brandName) 
	{
		homePage.selectBrandFromSearchResult();
	}

	public void selectFirstProductFromSearchResult() 
	{
		homePage.clickFirstProductFromSearchResult();
	}

	public void makeSearchOverlayVisible() 
	{
		homePage.clickSearchButton();	
	}

	public boolean verifyRecentSrchCount() 
	{
		int count = 0;
		try
		{
			count = homePage.getCountOfRecentSearchItems();
			if(count>=0)
			{
				common.reportLogAndPrintInConsole("Recent Search items displayed");
			}
			else 
			{
				common.reportLogAndPrintInConsole("Recent search Items not displayed");
			}
		}
		catch(Exception e)
		{
			common.reportLogAndPrintInConsole("Recent search items not displayed");
		}
		return true;
	}

	public boolean verifyTrendingSrchCount() 
	{
		WebDriverUtil.staticWait(2);
		androidDriver.hideKeyboard();
		try
		{
			homePage.clearRecentSearches();
		}
		catch(Exception e)
		{
			common.reportLogAndPrintInConsole("Clear recent search button not displayed");
		}
		int count = homePage.getCountOfTrendingSearchItems();
		if(count>=9)
			return true;
		else
			return false;
	}

	public boolean verifyTrendingSrchPrdctCount() 
	{
/*		CommonFunctions.moveToElementAppium(androidDriver, "TRENDING PRODUCTS");
		CommonFunctions.moveToElementAppium(androidDriver, "TRENDING PRODUCTS");*/
//		CommonFunctions.moveToElementAppium(androidDriver, "299");
		WebDriverUtil.staticWait(2);
		homePage.scrollToTrendingProducts();
		int count = homePage.getCountOfTrendingProducts();
		if(count>=4)
			return true;
		else
			return false;
	}

	public void selectRichMilkVariant() 
	{
		homePage.clickRichMilkVariant();
	}

	public void selectProductCategories(String category, String subCategory, String subSubCategory) 
	{
		homePage.clickTopLeftNavigationButton();
		homePage.selectCategory(category);
		homePage.selectSubCategory(subCategory,subSubCategory);
	}

	public void logout() 
	{
		openMyAccountPage();
		homePage.swipeToLogoutButton();
		homePage.clickLogoutButton();
		
	}
	
	public void clickCartButton()
	{
		homePage.clickCartButton();
	}

	public void clearWishListMenuAndContinueShopping() 
	{
		homePage.clickTopLeftNavigationButton();
		homePage.clickMyWishlist();
		homePage.emptyWishlist();
		homePage.clickContinueShopping();
	}
	
	

}
