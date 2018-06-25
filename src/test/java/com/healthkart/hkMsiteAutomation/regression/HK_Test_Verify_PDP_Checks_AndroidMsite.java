package com.healthkart.hkMsiteAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_Verify_PDP_Checks_AndroidMsite extends ExtentReportingBaseUtil
{
	/**
	 * Verify Compare widget Test.
	 */
	@Test(priority = 1, enabled = true)
	public void testCompareWidgetOnPDP() {
		System.out.println("## VERIFY COMPARE WIDGET ON PDP TEST STARTS..");
		test.log(LogStatus.INFO, "VERIFY COMPARE WIDGET ON PDP TEST STARTS..");
		homeStep.closeAllowButton();
		driver.get(GlobalVar.baseAppURL+testdata.get("Variant_URL")+pdpStep.getCompareVariant());
		Assert.assertTrue(pdpStep.verifyPdpPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");		
		Assert.assertTrue(pdpStep.verifyCompareWidgetPresent(), "Fail to load Compare Widget on PDP page..");
		test.log(LogStatus.INFO, "Compare Widget loaded successfully on PDP page..");
		pdpStep.performCompareVariants();
		Assert.assertTrue(cmprSteps.verifyComparePageLoaded(), "Fail to load Compare variant page..");
		test.log(LogStatus.INFO, "Compare variant page loaded..");
		cmprSteps.performAddToCartFromCompareVariantPage();
		Assert.assertTrue(cartStep.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
	}
	
	@Test(priority = 2, enabled = true)
	public void testWriteAReviewOnPDP() {
		System.out.println("## WRITE A REVIEW FROM PDP TEST STARTS..");
		test.log(LogStatus.INFO, "WRITE A REVIEW FROM PDP TEST STARTS..");
		homeStep.goToFirstPVWheyProteinPDP();
		Assert.assertTrue(pdpStep.verifyPdpPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		
		pdpStep.proceedToWriteAreviewPage();
		Assert.assertTrue(AccLoginSteps.verifyAccountsLoginPageLoaded(), "Accounts Page failed to load..");
		test.log(LogStatus.INFO, "HK Accounts Login Page loaded after logout from profile page..");
		AccLoginSteps.performLoginWithMobilePassword(testdata.get("Login_User_Mobile_Msite")
				, testdata.get("Login_User_Mobile_Password_Msite"));
		Assert.assertTrue(reviewSteps.verifyReviewPageLoaded(),"Fail to load review page");
		test.log(LogStatus.PASS, "User successfully logged in with mobile and password from HK Accounts Login Page && Write A Review Page loaded..");
		reviewSteps.performWriteAReview();
		Assert.assertTrue(reviewSteps.verifyReviewPublished(),"Fail to publish review and ratings..");
		test.log(LogStatus.PASS, "Review and rating published successfully..");
	}
}
