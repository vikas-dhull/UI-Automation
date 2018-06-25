package com.healthkart.hkMsiteAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_Visit_Cateory_Pages_AndroidMsite extends ExtentReportingBaseUtil
{
	/**
	 * Visit whey protein Cat page and apply filter and sorting.
	 */
	@Test(priority = 1, enabled = true)
	public void testCategoryListingPageWithSortAndFilter() {
		System.out.println("## VISIT CATAGORY PAGES TEST STARTS..");
		test.log(LogStatus.INFO, "VISIT CATAGORY PAGES TEST STARTS..");
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performLoginWithMobilePassword(testdata.get("Login_User_Mobile_Msite")
				, testdata.get("Login_User_Mobile_Password_Msite"));
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password..");
		comnFunc.staticWait(5);
		homeStep.visitCategoryPage();
		Assert.assertTrue(catSteps.verifyCatPageLoaded(), "Fail to load Category page..");
		test.log(LogStatus.INFO, "Category page loaded..");
		// catLsB.handleCatPageHltr();						// handle cat page halter.
		catSteps.selectSortByPriceLH(); 					// Sorting price low to high.
		comnFunc.staticWait(5);
		Assert.assertTrue(catSteps.verifySortByPriceLH(), "Variant Price not sorted..");
		test.log(LogStatus.INFO, "Category page loaded with sorted results by Price:Low_To_High..");
		catSteps.selectBrandFilter();
		comnFunc.staticWait(5);
		test.log(LogStatus.INFO, "Category page loaded with sorted results by Price:Low_To_High and filter applied for Brand:Muscleblaze..");
		//Assert.assertTrue(catLsB.verifyFilterByBrand(), "Variant Brand not found in filtered criteria..");
		catSteps.goToFirstVariantPage();
		Assert.assertTrue(pdpStep.verifyPdpPageLoaded(), "PDP page not loaded properly from category page..");
		test.log(LogStatus.PASS, "Product details page loaded from Category page..");				
	}
	
	/**
	 * Visit Muscleblaze Brands page.
	 */
	@Test(priority = 2, enabled = true)
	public void testBrandsPage() {
		System.out.println("## VISIT BRANDS PAGE TEST STARTS..");
		test.log(LogStatus.INFO, "VISIT BRANDS PAGE TEST STARTS..");	
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performLoginWithMobilePassword(testdata.get("Login_User_Mobile_Msite")
				, testdata.get("Login_User_Mobile_Password_Msite"));
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password..");
		homeStep.visitBrandPage();
		Assert.assertTrue(brndSteps.verifyBrndPageLoaded(), "Fail to load Brand page..");
		test.log(LogStatus.INFO, "Brand page loaded..");
		brndSteps.selectSortByPriceLH(); 					// Sorting price low to high.
		comnFunc.staticWait(5);
		Assert.assertTrue(brndSteps.verifySortByPriceLH(), "Variant Price not sorted..");
		test.log(LogStatus.INFO, "Brand page loaded with sorted results by Price:Low_To_High..");
		comnFunc.staticWait(5);
		brndSteps.goToFirstVariantPage();
		Assert.assertTrue(pdpStep.verifyPdpPageLoaded(), "PDP page not loaded properly from Brand page..");
		test.log(LogStatus.PASS, "Product details page loaded from Brand page..");		
	}
	
	/**
	 * Visit Search page for packs.
	 */	
	@Test(priority = 3, enabled = true)
	public void testSearchPage() {
		System.out.println("## VISIT PACK PAGE THROUGH SEARCH PAGE TEST STARTS..");
		test.log(LogStatus.INFO, "VISIT PACK PAGE THROUGH SEARCH PAGE TEST STARTS..");
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to open login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performLoginWithMobilePassword(testdata.get("Login_User_Mobile_Msite")
				, testdata.get("Login_User_Mobile_Password_Msite"));
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password..");
		homeStep.visitSearchPage();		
		Assert.assertTrue(srchSteps.verifySrchPageLoaded(), "Fail to load Search page..");
		test.log(LogStatus.INFO, "Search page loaded..");
		
		comnFunc.staticWait(5);
		brndSteps.goToFirstVariantPage();
		Assert.assertTrue(pdpStep.verifyPdpPageLoaded(), "PDP page not loaded properly from Search page..");
		test.log(LogStatus.PASS, "Product details page loaded from Search page..");		
	}
	
	/**
	 * Load more. 
	 */
	@Test(priority = 4, enabled = true)
	public void testLoadMoreOnCatPage() {
		System.out.println("## LOAD MORE ON CATAGORY PAGES TEST STARTS..");
		test.log(LogStatus.INFO, "LOAD MORE ON CATAGORY PAGES TEST STARTS..");
		homeStep.closeAllowButton();
		homeStep.visitCategoryPage();
		Assert.assertTrue(catSteps.verifyCatPageLoaded(), "Fail to load Category page..");
		test.log(LogStatus.INFO, "Category page loaded..");
		Assert.assertTrue(catSteps.verifyLoadMoreOnCatPage(), "Fail to load more variants on Category page..");
		test.log(LogStatus.INFO, "Category page loaded with load more variants..");
	}
}
