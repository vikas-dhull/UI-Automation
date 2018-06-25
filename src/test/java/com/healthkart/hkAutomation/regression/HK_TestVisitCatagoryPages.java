package com.healthkart.hkAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
//import com.healthkart.hkAutomation.util.ExcelUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_TestVisitCatagoryPages extends ExtentReportingBaseUtil{
	
	/**
	 * Visit whey protein Cat page and apply filter and sorting.
	 */
	@Test(priority = 1, enabled = true)
	public void testCategoryListingPageWithSortAndFilter() {
		System.out.println("## VISIT CATAGORY PAGES TEST STARTS..");
		test.log(LogStatus.INFO, "VISIT CATAGORY PAGES TEST STARTS..");		
		WebDriverUtil.staticWait(3);		
		homeB.visitCategoryPage();
		Assert.assertTrue(catLsB.verifyCatPageLoaded(), "Fail to load Category page..");
		test.log(LogStatus.INFO, "Category page loaded..");
		// catLsB.handleCatPageHltr();						// handle cat page halter.
		catLsB.selectSortByPriceLH(); 					// Sorting price low to high.
		WebDriverUtil.staticWait(3);
		Assert.assertTrue(catLsB.verifySortByPriceLH(), "Variant Price not sorted..");
		test.log(LogStatus.INFO, "Category page loaded with sorted results by Price:Low_To_High..");
		catLsB.scroolWindowToBrandFilter();		
		WebDriverUtil.staticWait(3);		
		catLsB.clickCheckboxBrandFilter(); 				// Filter results for brand - MuscleBlaze.		
		WebDriverUtil.staticWait(3);
		catLsB.scroolWindowToPvView();
		test.log(LogStatus.INFO, "Category page loaded with sorted results by Price:Low_To_High and filter applied for Brand:Muscleblaze..");
		//Assert.assertTrue(catLsB.verifyFilterByBrand(), "Variant Brand not found in filtered criteria..");
		catLsB.clickfirstPVLink();
		//WebDriverUtil.staticWait(3);
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "PDP page not loaded properly from category page..");
		test.log(LogStatus.PASS, "Product details page loaded from Category page..");
				
	}
	
	/**
	 * Visit Muscleblaze Brands page.
	 */
	@Test(priority = 2, enabled = true)
	public void testBrandsPage() {
		System.out.println("## VISIT BRANDS PAGE TEST STARTS..");
		test.log(LogStatus.INFO, "VISIT BRANDS PAGE TEST STARTS..");	
		//homeB.gotoHomePage();
		Assert.assertTrue(homeB.verifyHomePageloaded(),"Home page load failed for brands..");
		test.log(LogStatus.INFO, "Home page loaded..");
		homeB.visitBrandPage();
		WebDriverUtil.staticWait(3);
		Assert.assertTrue(brndB.verifyBrndPageLoaded(), "Fail to load Brand page..");
		test.log(LogStatus.PASS, "Brand page loaded..");
		brndB.selectSortByPriceLH();
		WebDriverUtil.staticWait(3);		
		Assert.assertTrue(brndB.verifySortByPriceLH(), "Fail to Sort results on Brands page..");
		test.log(LogStatus.INFO, "Sorted by Price low to high results loaded on Brands page..");
	} 
	
	/**
	 * Visit Search page for packs.
	 */	
	@Test(priority = 3, enabled = true)
	public void testSearchPage() {
		System.out.println("## VISIT PACK PAGE TEST STARTS..");
		test.log(LogStatus.INFO, "VISIT PACK PAGE TEST STARTS..");
		//homeB.gotoHomePage();
		Assert.assertTrue(homeB.verifyHomePageloaded(),"Home page load failed for search..");
		test.log(LogStatus.INFO, "Home page loaded..");
		homeB.enterSearchText(testdata.get("Search_String"));		
		homeB.submitSearchText();
		WebDriverUtil.staticWait(3);		
		Assert.assertTrue(srchB.verifySrchPageLoaded(), "Fail to load Search page..");
		test.log(LogStatus.PASS, "Search page loaded..");		
	}
	
	/**
	 * Load more button 
	 */
	@Test(priority = 4, enabled = true)
	public void testLoadMoreOnCatPage() {
		System.out.println("## LOAD MORE ON CATAGORY PAGES TEST STARTS..");
		test.log(LogStatus.INFO, "LOAD MORE ON CATAGORY PAGES TEST STARTS..");		
		WebDriverUtil.staticWait(3);		
		homeB.visitCategoryPage();
		Assert.assertTrue(catLsB.verifyCatPageLoaded(), "Fail to load Category page..");
		test.log(LogStatus.INFO, "Category page loaded..");
		//catLsB.handleCatPageHltrForLoadMore();
		Assert.assertTrue(catLsB.verifyLoadMoreOnCatPage(), "Fail to load more variants on Category page..");
		test.log(LogStatus.INFO, "Category page loaded with load more variants..");
	}
}