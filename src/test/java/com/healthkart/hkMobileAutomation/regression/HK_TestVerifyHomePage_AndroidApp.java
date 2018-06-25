package com.healthkart.hkMobileAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_TestVerifyHomePage_AndroidApp extends ExtentReportingBaseUtil{
	
	@Test(priority=1,enabled=false)
	public void testVerifyHomePage()
	{
/*		System.out.println("## VERIFY HOME PAGE TEST STARTS..");
		test.log(LogStatus.INFO, "VERIFY HOME PAGE TEST STARTS..");
//		Assert.assertTrue(homeSteps.verifyHomePageloaded(), "home page not loaded..");
//		test.log(LogStatus.PASS, "Home Page loaded successfully..");
		Assert.assertTrue(homeSteps.verifyShopConsultConnectWidgets(), "Shop | Consult | Connect widgets are NOT present on home page..");
		test.log(LogStatus.PASS, "Shop | Consult | Connect widgets are present on home page..");
		Assert.assertTrue(homeSteps.verifyTrendingWheyProteinWidget(), "Trending Whey Protien widget is NOT present on home page..");
		test.log(LogStatus.PASS, "Trending Whey Protien widget is present on home page..");
		Assert.assertTrue(homeSteps.verifyAskAnExpertWidget(), "Ask An expert widget is NOT present on home page..");
		test.log(LogStatus.PASS, "Ask An expert widget is present on home page..");
		Assert.assertTrue(homeSteps.verifyShopByCategoryWidget(), "Shop By Category widget is NOT present on home page..");
		test.log(LogStatus.PASS, "Shop By Category widget is present on home page..");
		Assert.assertTrue(homeSteps.verifyDisclaimer(), "Home Page Disclaimer is NOT present on home page..");
		test.log(LogStatus.PASS, "Home Page Disclaimer is present on home page..");
		
		if(GlobalVar.jenkinsEnvironment==null)
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				Assert.assertTrue(homeSteps.verifyIAWidget(), "IA widgets are NOT present on home page..");
				test.log(LogStatus.PASS, "IA widgets are present on home page..");
			}
			else {				
				test.log(LogStatus.INFO, "IA widgets test only applicable for prod env..");						
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			Assert.assertTrue(homeSteps.verifyIAWidget(), "IA widgets are NOT present on home page..");
			test.log(LogStatus.PASS, "IA widgets are present on home page..");			
		}
		
		else if("qa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{	
			test.log(LogStatus.INFO, "IA widgets test only applicable for prod env..");
		}*/
	}
	
	@Test(priority = 2, enabled = true)
	public void testSearchResultsForGuestUser() 
	{
		comnFunc.reportLogAndPrintInConsole("## VERIFY SEARCH RESULTS FOR GUEST USER TEST STARTS..");
		homeSteps.makeSearchOverlayVisible();
//		Assert.assertTrue(homeSteps.verifySrchOverlayDisplayed(), "Search Overlay NOT present on home page..");
		test.log(LogStatus.PASS, "Search Overlay present on home page..");
		Assert.assertTrue(homeSteps.verifyRecentSrchCount());
//		test.log(LogStatus.PASS, "Recent search count is found as.. " + homeB.getRecentSearchCount());
		Assert.assertTrue(homeSteps.verifyTrendingSrchCount());
//		test.log(LogStatus.PASS, "Trending search count is found as.. " + homeB.getTrendingSearchCount());
		Assert.assertTrue(homeSteps.verifyTrendingSrchPrdctCount());
//		test.log(LogStatus.PASS, "Trending search product count is found as.. " + homeB.getTrendingSearchProductCount());		
	}
	
	@Test(priority = 3, enabled = true)
	public void testSearchResultsForLoggedInUser() 
	{
		comnFunc.reportLogAndPrintInConsole("## VERIFY SEARCH RESULTS FOR LOGGED IN USER TEST STARTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLoginWithGoogle();
//		homeSteps.verifySuccessfullLogin();
		homeSteps.makeSearchOverlayVisible();
		Assert.assertTrue(homeSteps.verifyRecentSrchCount());
		Assert.assertTrue(homeSteps.verifyTrendingSrchCount());
		Assert.assertTrue(homeSteps.verifyTrendingSrchPrdctCount());
	}

}
