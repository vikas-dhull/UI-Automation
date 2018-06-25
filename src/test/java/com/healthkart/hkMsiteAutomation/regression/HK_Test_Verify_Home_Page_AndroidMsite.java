package com.healthkart.hkMsiteAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_Verify_Home_Page_AndroidMsite extends ExtentReportingBaseUtil
{
	@Test(priority=1,enabled=true)
	public void testVerifyHomePage()
	{
		System.out.println("## VERIFY HOME PAGE TEST STARTS..");
		test.log(LogStatus.INFO, "VERIFY HOME PAGE TEST STARTS..");
		homeStep.closeAllowButton();
		/*Assert.assertTrue(homeStep.verifyShopConsultConnectWidgets(), "Shop | Consult | Connect widgets are NOT present on home page..");
		test.log(LogStatus.PASS, "Shop | Consult | Connect widgets are present on home page..");*/
		Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "Trending Whey Protien widget is NOT present on home page..");
		test.log(LogStatus.PASS, "Trending Whey Protien widget is present on home page..");
		Assert.assertTrue(homeStep.verifyAskAnExpertWidget(), "Ask An expert widget is NOT present on home page..");
		test.log(LogStatus.PASS, "Ask An expert widget is present on home page..");
		/*Assert.assertTrue(homeStep.verifyShopByCategoryWidget(), "Shop By Category widget is NOT present on home page..");
		test.log(LogStatus.PASS, "Shop By Category widget is present on home page..");*/
		Assert.assertTrue(homeStep.verifyDisclaimer(), "Home Page Disclaimer is NOT present on home page..");
		test.log(LogStatus.PASS, "Home Page Disclaimer is present on home page..");

		if(GlobalVar.jenkinsEnvironment==null)
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				Assert.assertTrue(homeStep.verifyIAWidget(), "IA widgets are NOT present on home page..");
				test.log(LogStatus.PASS, "IA widgets are present on home page..");
			}
			else {				
				test.log(LogStatus.INFO, "IA widgets test only applicable for prod env..");						
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			Assert.assertTrue(homeStep.verifyIAWidget(), "IA widgets are NOT present on home page..");
			test.log(LogStatus.PASS, "IA widgets are present on home page..");			
		}
		
		else if("qa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{	
			test.log(LogStatus.INFO, "IA widgets test only applicable for prod env..");
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void testSearchResultsForGuestUser() 
	{
		System.out.println("## VERIFY SEARCH RESULTS FOR GUEST USER TEST STARTS..");
		test.log(LogStatus.INFO, "VERIFY SEARCH RESULTS FOR GUEST USER TEST STARTS..");
		homeStep.closeAllowButton();
		
		homeStep.makeSearchOverlayVisible();
		Assert.assertTrue(homeStep.verifySrchOverlayDisplayed(), "Search Overlay NOT present on home page..");
		test.log(LogStatus.PASS, "Search Overlay present on home page..");
		Assert.assertTrue(homeStep.verifyRecentSrchCount(), "Recent search count NOT verified successfully..");
		test.log(LogStatus.PASS, "Recent search count verified successfully..");
		Assert.assertTrue(homeStep.verifyTrendingSrchCount(), "Trending search count NOT verified successfully..");
		test.log(LogStatus.PASS, "Trending search count verified successfully..");
		Assert.assertTrue(homeStep.verifyTrendingSrchPrdctCount(), "Trending search product count NOT verified successfully..");
		test.log(LogStatus.PASS, "Trending search product count verified successfully..");
	}
	
	@Test(priority = 3, enabled = true)
	public void testSearchResultsForLoggedInUser() 
	{
		System.out.println("## VERIFY SEARCH RESULTS FOR LOGGED IN USER TEST STARTS..");
		test.log(LogStatus.INFO, "VERIFY SEARCH RESULTS FOR LOGGED IN USER TEST STARTS..");
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performLoginWithMobilePassword(testdata.get("Login_User_Mobile_Msite")
				, testdata.get("Login_User_Mobile_Password_Msite"));
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password..");
		
		homeStep.makeSearchOverlayVisible();
		Assert.assertTrue(homeStep.verifySrchOverlayDisplayed(), "Search Overlay NOT present on home page..");
		test.log(LogStatus.PASS, "Search Overlay present on home page..");
		Assert.assertTrue(homeStep.verifyRecentSrchCount(), "Recent search count NOT verified successfully..");
		test.log(LogStatus.PASS, "Recent search count verified successfully..");
		Assert.assertTrue(homeStep.verifyTrendingSrchCount(), "Trending search count NOT verified successfully..");
		test.log(LogStatus.PASS, "Trending search count verified successfully..");
		Assert.assertTrue(homeStep.verifyTrendingSrchPrdctCount(), "Trending search product count NOT verified successfully..");
		test.log(LogStatus.PASS, "Trending search product count verified successfully..");
	}

}
