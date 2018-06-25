package com.healthkart.hkAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.property.PropertyHelper;
//import com.healthkart.hkAutomation.util.ExcelUtil;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.relevantcodes.extentreports.LogStatus;

public class HK_TestVerifyHomePage extends ExtentReportingBaseUtil{
	
	@Test(priority=1, enabled=true)
	public void testVerifyHomePage()
	{
		System.out.println("## VERIFY HOME PAGE TEST STARTS..");
		test.log(LogStatus.INFO, "VERIFY HOME PAGE TEST STARTS..");
		Assert.assertTrue(homeB.verifyHomePageloaded(), "home page not loaded..");
		test.log(LogStatus.PASS, "Home Page loaded successfully..");
		/*Assert.assertTrue(homeB.verifyShopConsultConnectWidgets(), "Shop | Consult | Connect widgets are NOT present on home page.."); depricated from  home page
		test.log(LogStatus.PASS, "Shop | Consult | Connect widgets are present on home page..");*/
		Assert.assertTrue(homeB.verifyTrendingWheyProteinWidget(), "Trending Whey Protien widget is NOT present on home page..");
		test.log(LogStatus.PASS, "Trending Whey Protien widget is present on home page..");
		Assert.assertTrue(homeB.verifyAskAnExpertWidget(), "Ask An expert widget is NOT present on home page..");
		test.log(LogStatus.PASS, "Ask An expert widget is present on home page..");
		/*Assert.assertTrue(homeB.verifyShopByCategoryWidget(), "Shop By Category widget is NOT present on home page..");
		test.log(LogStatus.PASS, "Shop By Category widget is present on home page..");*/
		Assert.assertTrue(homeB.verifyDisclaimer(), "Home Page Disclaimer is NOT present on home page..");
		test.log(LogStatus.PASS, "Home Page Disclaimer is present on home page..");
		
		if(GlobalVar.jenkinsEnvironment==null)
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				Assert.assertTrue(homeB.verifyIAWidget(), "IA widgets are NOT present on home page..");
				test.log(LogStatus.PASS, "IA widgets are present on home page..");
			}
			else {				
				test.log(LogStatus.INFO, "IA widgets test only applicable for prod env..");						
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			Assert.assertTrue(homeB.verifyIAWidget(), "IA widgets are NOT present on home page..");
			test.log(LogStatus.PASS, "IA widgets are present on home page..");			
		}
		
		else if("qa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{	
			test.log(LogStatus.INFO, "IA widgets test only applicable for prod env..");
		}
	}
	
	@Test(priority = 2, enabled=true)
	public void testSearchResultsForGuestUser() 
	{
		System.out.println("## VERIFY SEARCH RESULTS FOR GUEST USER TEST STARTS..");
		test.log(LogStatus.INFO, "VERIFY SEARCH RESULTS FOR GUEST USER TEST STARTS..");
		homeB.makeSearchOverlayVisible();
		Assert.assertTrue(homeB.verifySrchOverlayDisplayed(), "Search Overlay NOT present on home page..");
		test.log(LogStatus.PASS, "Search Overlay present on home page..");
		Assert.assertTrue(homeB.verifyRecentSrchCount(), "Recent search count is not found as.. " + homeB.getRecentSearchCount());
		test.log(LogStatus.PASS, "Recent search count is found as.. " + homeB.getRecentSearchCount());
		Assert.assertTrue(homeB.verifyTrendingSrchCount(), "Trending search count is not found as.. " + homeB.getTrendingSearchCount());
		test.log(LogStatus.PASS, "Trending search count is found as.. " + homeB.getTrendingSearchCount());
		Assert.assertTrue(homeB.verifyTrendingSrchPrdctCount(), "Trending search product count is not found as.. " + homeB.getTrendingSearchProductCount());
		test.log(LogStatus.PASS, "Trending search product count is found as.. " + homeB.getTrendingSearchProductCount());		
	}
	
	@Test(priority = 3, enabled = true)
	public void testSearchResultsForLoggedInUser() 
	{
		System.out.println("## VERIFY SEARCH RESULTS FOR LOGGED IN USER TEST STARTS..");
		test.log(LogStatus.INFO, "VERIFY SEARCH RESULTS FOR LOGGED IN USER TEST STARTS..");
		homeB.performLoginWithMobilePassword(testdata.get("Login_User_Mobile"), testdata.get("Login_User_Mobile_Password"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.PASS, "User successfully logged in with mobile and password");
		homeB.makeSearchOverlayVisible();
		Assert.assertTrue(homeB.verifySrchOverlayDisplayed(), "Search Overlay NOT present on home page..");
		test.log(LogStatus.PASS, "Search Overlay present on home page..");
		Assert.assertTrue(homeB.verifyRecentSrchCount(), "Recent search count is not found as.. " + homeB.getRecentSearchCount());
		test.log(LogStatus.PASS, "Recent search count is found as.. " + homeB.getRecentSearchCount());
		Assert.assertTrue(homeB.verifyTrendingSrchCount(), "Trending search count is not found as.. " + homeB.getTrendingSearchCount());
		test.log(LogStatus.PASS, "Trending search count is found as.. " + homeB.getTrendingSearchCount());
		Assert.assertTrue(homeB.verifyTrendingSrchPrdctCount(), "Trending search product count is not found as.. " + homeB.getTrendingSearchProductCount());
		test.log(LogStatus.PASS, "Trending search product count is found as.. " + homeB.getTrendingSearchProductCount());
	}

}
