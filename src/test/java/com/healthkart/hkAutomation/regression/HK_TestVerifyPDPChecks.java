package com.healthkart.hkAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
//import com.healthkart.hkAutomation.util.ExcelUtil;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.relevantcodes.extentreports.LogStatus;

public class HK_TestVerifyPDPChecks extends ExtentReportingBaseUtil{
	
	/**
	 * Verify Compare widget Test.
	 */
	@Test(priority = 1, enabled=true)
	public void testCompareWidgetOnPDP() {
		System.out.println("## VERIFY COMPARE WIDGET ON PDP TEST STARTS..");
		test.log(LogStatus.INFO, "VERIFY COMPARE WIDGET ON PDP TEST STARTS..");
		driver.get(GlobalVar.baseAppURL+testdata.get("Variant_URL")+pdpB.getCompareVariant());
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		
		Assert.assertTrue(pdpB.verifyCompareWidgetPresent(), "Fail to load Compare Widget on PDP page..");
		test.log(LogStatus.INFO, "Compare Widget loaded successfully on PDP page..");
		pdpB.performCompareVariants();
		Assert.assertTrue(compareVarB.verifyCompareVariantPageLoaded(), "Fail to load Compare variant page..");
		test.log(LogStatus.INFO, "Compare variant page loaded..");
		compareVarB.performAddToCartFromCompareVariantPage();
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
	}
	
	@Test(priority = 2, enabled=true)
	public void testWriteAReviewOnPDP() {
		System.out.println("## WRITE A REVIEW FROM PDP TEST STARTS..");
		test.log(LogStatus.INFO, "WRITE A REVIEW FROM PDP TEST STARTS..");
		driver.get(GlobalVar.baseAppURL+testdata.get("Variant_URL")+testdata.get("Coupon_VariantId"));
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		
		pdpB.visitReviewPage();
		Assert.assertTrue(AccHkB.verifyAccountsLoginPageLoaded(), "Accounts Page failed to load..");
		test.log(LogStatus.INFO, "HK Accounts Login Page loaded after logout from profile page..");
		AccHkB.performLoginWithMobilePassword(testdata.get("Login_User_Mobile"), testdata.get("Login_User_Mobile_Password"));
		Assert.assertTrue(warB.verifyWriteAReviewPageLoaded(),"Fail to load review page");
		test.log(LogStatus.PASS, "User successfully logged in with mobile and password from HK Accounts Login Page && Write A Review Page loaded..");
		warB.performWriteAReview();
		Assert.assertTrue(warB.verifyReviewPublished(),"Fail to publish review and ratings..");
		test.log(LogStatus.PASS, "Review and rating published successfully..");
	}
	
	@Test(priority = 3, enabled=true)
	public void testSizeAndFlavoursOnPDP() {
		System.out.println("## SIZE AND FLAVOUR ON PDP TEST STARTS..");
		test.log(LogStatus.INFO, "SIZE AND FLAVOUR ON PDP TEST STARTS..");
		homeB.clickHomePageVariant();
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		
		Assert.assertTrue(pdpB.verifyAllLinksPDPWeights(), "All PDP sizes verification FAILED..!!");
		comnFunc.reportLogAndPrintInConsole("All Sizes for PDP are verified Successfully..");
		Assert.assertTrue(pdpB.verifyAllLinksPDPFlavours(), "All PDP Flavours verification FAILED..!!");
		comnFunc.reportLogAndPrintInConsole("All Flavours for PDP are verified Successfully..");		
	}

}
