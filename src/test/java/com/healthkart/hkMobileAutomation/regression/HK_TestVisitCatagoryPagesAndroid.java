package com.healthkart.hkMobileAutomation.regression;

import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class HK_TestVisitCatagoryPagesAndroid extends ExtentReportingBaseUtil
{
	@Test(priority=1, enabled=true)
	public void testCategoryListingPageWithSortAndFilter() 
	{
		comnFunc.reportLogAndPrintInConsole("## HK_TestVisitCatagoryPagesAndroid");
		homeSteps.visitPageWithLessProducts();	
		WebDriverUtil.staticWait(3);
		categorySteps.sortListing("Price : Low To High");
		WebDriverUtil.staticWait(3);
		Assert.assertTrue(categorySteps.verifySorting("Price : Low To High"));
		
		categorySteps.sortListing("Price : High To Low");
		WebDriverUtil.staticWait(3);
		Assert.assertTrue(categorySteps.verifySorting("Price : High To Low"));
		/*
		categorySteps.applyFilter("Brands");
		WebDriverUtil.staticWait(2);
		categorySteps.applySubFilter("Ayurwin");
		WebDriverUtil.staticWait(2);
		categorySteps.clickApplyFilter(); */
	}
	
	@Test(priority=2, enabled=false)
	public void testBrandPageWithSortAndFilter() 
	{
		comnFunc.reportLogAndPrintInConsole("## HK_TestVisitCatagoryPagesAndroid");
		homeSteps.selectProductCategories("Sports Nutrition", "Top Brands", "MuscleBlaze");
		categorySteps.sortListing("Price : Low To High");
		WebDriverUtil.staticWait(3);		
		Assert.assertTrue(categorySteps.verifySorting("Price : Low To High"));
		
		categorySteps.sortListing("Price : High To Low");
		WebDriverUtil.staticWait(3);
		Assert.assertTrue(categorySteps.verifySorting("Price : High To Low"));
		/*
		categorySteps.applyFilter("Brands");
		WebDriverUtil.staticWait(2);
		categorySteps.applySubFilter("Ayurwin");
		WebDriverUtil.staticWait(2);
		categorySteps.clickApplyFilter(); */
	}
	
	@Test(priority = 3, enabled = true)
	public void testLoadMoreOnCatPage() 
	{
		comnFunc.reportLogAndPrintInConsole("## LOAD MORE ON CATAGORY PAGES TEST STARTS..");
		test.log(LogStatus.INFO, "LOAD MORE ON CATAGORY PAGES TEST STARTS..");		
		homeSteps.selectProductCategories("Sports Nutrition", "Proteins", "Whey Proteins");
//		categorySteps.scrollDownTillLoadMoreIsDisplayed();
	}
	
}