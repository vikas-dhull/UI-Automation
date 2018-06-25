package com.healthkart.hkMsiteAutomation.business;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkMsiteAutomation.pages.HK_Home_Page_AndroidMsite;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

public class HK_Home_Steps_AndroidMsite
{
	AndroidDriver<?> androidDriver;
	HK_Home_Page_AndroidMsite homePage;
	CommonFunctions comnFunc;
	
	
	public HK_Home_Steps_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		homePage = new HK_Home_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
	}
	
	public void goToHomePage() 
	{
		homePage.getHomePage();
	}
	
	public void proceedToLogin() 
	{
		//homePage.navigateToHealthKart();
		homePage.closeAllowButton();
		homePage.clickUserLoginButton();
		comnFunc.staticWait(3);
	}
	
	public void proceedToSignup() 
	{
		//homePage.navigateToHealthKart();
		homePage.closeAllowButton();
		homePage.clickUserSignupButton();
	}
	
	public void closeAllowButton()
	{
		homePage.closeAllowButton();
	}
	
	public void openMsiteMenu() 
	{
		homePage.clickTopLeftNavigationButton();
	}
	
	public void closeMsiteMenu() 
	{
		homePage.clickMsiteMenuClose();		
	}
	
	public void performLogout() 
	{
		/*comnFunc.staticWait(5);
		homePage.navigateToHealthKart();*/
		homePage.clickTopLeftNavigationButton();
		homePage.clickUserLogoutButton();
		comnFunc.staticWait(3);
	}

	public boolean verifySuccessfullLogin() 
	{
		comnFunc.staticWait(2);
		boolean flag = false;		
		homePage.clickTopLeftNavigationButton();
		comnFunc.staticWait(2);
		flag = homePage.verifyLoggedIn();
		homePage.clickMsiteMenuClose();
		return flag;		
	}
	
	public boolean verifySuccessfullLogout() 
	{
		comnFunc.staticWait(2);
		boolean flag = false;		
		homePage.clickTopLeftNavigationButton();
		comnFunc.staticWait(2);
		flag = homePage.verifyLoggedOut();	
		homePage.clickMsiteMenuClose();
		return flag;		
	}
	
	public boolean verifyUserSignup() {
		homePage.waitForUserSignup();
		boolean flag = false;		
		homePage.clickTopLeftNavigationButton();
		flag = homePage.verifyLoggedIn();
		homePage.clickMsiteMenuClose();
		return flag;
	}
	
	public void waitContinueForSignup()
	{
		homePage.waitForUserSignup();
	}
	
	public void goToFirstPVWheyProteinPDP() {
		homePage.closeAllowButton();
		homePage.clickFirstPVWheyProtein();
	}
	
	public void goToHKAccountsLoginPage() {
		homePage.closeAllowButton();
		homePage.clickYourAccount();
	}
	
	public int getCartCountForUser() {
		comnFunc.staticWait(3);
		String cnt = homePage.getCartCounter();
		System.out.println("user cart count found as : "+cnt);
    	if(cnt != null) {
    		int count = Integer.parseInt(cnt);
        	return count;
    	}
    	else {
    		System.out.println("cart count returned as NULL..");
    		return 1;
    	}    	
    }
	
	public void proceedToCart()
	{
		homePage.clickProceedToCart();
	}

	public boolean verifyShopConsultConnectWidgets() 
	{
		return homePage.verifyShopConsultConnectWidgets();		
	}

	public boolean verifyIAWidget() 
	{
		return homePage.verifyIAWidgets();
	}

	public boolean verifyTrendingWheyProteinWidget() 
	{
		return homePage.verifyTrendingWheyProteinWidget();
	}

	public boolean verifyAskAnExpertWidget() 
	{
		return homePage.verifyAskAnExpertWidget();
	}

	public boolean verifyShopByCategoryWidget() 
	{
		return homePage.verifyShopByCategoryWidget();
	}

	public boolean verifyDisclaimer() 
	{
		return homePage.verifyDisclaimer();
	}

	public void openProfilePage() 
	{
		homePage.OpenMyProfile();
	}

	public void visitCategoryPage() 
	{
		homePage.clickTopLeftNavigationButton();
		homePage.chooseProteinSupplementsCategory();
	}

	public void visitBrandPage() 
	{
		homePage.enterSearchBrandName();
		homePage.chooseMuscleBlazeBrandFromSearch();
	}
	
	public void visitSearchPage() 
	{
		homePage.enterSearchPacks();
	}
	
	public void makeSearchOverlayVisible() {
		homePage.displaySrchOverlay();
    }
    
    public boolean verifyRecentSrchCount() {
    	boolean flag = false;
    	int cnt = getRecentSearchCount();
    	if(cnt<=5) {
    		flag = true;
    	}
    	System.out.println("recent search count found as : "+cnt);
    	GlobalVar.test.log(LogStatus.INFO, "recent search count found as : "+cnt);
    	return flag;
    }
    
    public boolean verifyTrendingSrchCount() {
    	boolean flag = false;
    	int trendCnt = getTrendingSearchCount();
    	int recentCnt = getRecentSearchCount();
    	int totalSrchCnt = trendCnt + recentCnt;
    	
    	if(totalSrchCnt >= 10) {
    		flag = true;
    	}
    	System.out.println("Total search count found as : "+ totalSrchCnt + "recent search count : " + recentCnt + "trending search count : " + trendCnt);
    	GlobalVar.test.log(LogStatus.INFO, "Total search count found as : "+ totalSrchCnt + "recent search count : " + recentCnt + "trending search count : " + trendCnt);
    	return flag;
    }
    
    public boolean verifyTrendingSrchPrdctCount() {
    	boolean flag = false;
    	int cnt = getTrendingSearchProductCount();
    	if(cnt >= 4) {
    		flag = true;
    	}
    	System.out.println("trending search product count found as : "+cnt);
    	GlobalVar.test.log(LogStatus.INFO, "trending search product count found as : "+cnt);
    	return flag;
    }
    
    public boolean verifySrchOverlayDisplayed() {
		return homePage.verifySrchOverlayDisplayedOnHomePage();
	}

	public int getRecentSearchCount() {
		return homePage.getRecentSearchCountOnHomePage();
	}

	public int getTrendingSearchCount() {
		return homePage.getTrendingSearchCountOnHomePage();
	}

	public int getTrendingSearchProductCount() {
		return homePage.getTrendingSearchProductCountOnHomePage();
	}

	public void proceedToLoginInLeftPanel() 
	{
		openMsiteMenu();
		homePage.clickSignInLeftPanel();
	}
}
