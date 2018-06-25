package com.healthkart.hkMobileAutomation.regression;

import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_TestVerifyPDPChecks_Android extends ExtentReportingBaseUtil
{
	
	/**
	 * Verify Compare widget Test.
	 */
	@Test(priority = 1, enabled = true)
	public void testCompareWidgetOnPDP() 
	{
		comnFunc.reportLogAndPrintInConsole("## VERIFY COMPARE WIDGET ON PDP TEST STARTS..");
		test.log(LogStatus.INFO, "VERIFY COMPARE WIDGET ON PDP TEST STARTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLogin(mobileNumber, password);
		/*homeSteps.searchProduct("Ultimate Nutrition");
		homeSteps.selectProductFromSearchResults("Ultimate Nutrition");*/
		homeSteps.selectProductCategories("Sports Nutrition", "Top Brands", "Ultimate Nutrition");
		homeSteps.selectFirstProductFromSearchResult();
		
//		driver.get(GlobalVar.baseAppURL+testdata.get("Variant_URL")+pdpStep.getCompareVariant());
		
		pdpSteps.selectCompareNow();
		pdpSteps.verifyComparePageIsDisplayed();
		pdpSteps.addMBVariantToCartFromComparePage();
		cartSteps.proceedToCheckout();
		WebDriverUtil.staticWait(1);
		checkout.selectAddress();		
	}
	
	@Test(priority = 2, enabled = true)
	public void testWriteAReviewOnPDP() 
	{
		comnFunc.reportLogAndPrintInConsole("## WRITE A REVIEW FOR A PRODUCT TEST STARTS..");
		test.log(LogStatus.INFO, "WRITE A REVIEW FOR A PRODUCT TEST STARTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLogin(mobileNumber, password);
/*		homeSteps.searchProduct("Dymatize");
		homeSteps.selectProductFromSearchResults("Dymatize");
		homeSteps.verifySuccessfullLogin();
		homeSteps.proceedToCart();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();*/
		homeSteps.selectProductCategories("Sports Nutrition", "Top Brands", "Dymatize");
		homeSteps.selectFirstProductFromSearchResult();
		pdpSteps.writeAReview("Test Review", "Test description");
	}

}
