package com.healthkart.hkRetailAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_Order_Retail_Manager_Assume_login extends ExtentReportingBaseUtil {

	/**
	 * Test Order placement for retail customer with store manager assume login.
	 */
	@Test(priority = 1)
	@Parameters({"payment","customer"})
	public void testOrderPlaceWithSMAssumeLoginPopup(String paymentType,String customerType) 
	{    	
		System.out.println("## ORDER PLACE COD FOR RETAIL CUSTOMER WITH STORE MANAGER ASSUME LOGIN Test starts..");
		test.log(LogStatus.INFO, "ORDER PLACE COD FOR RETAIL CUSTOMER WITH STORE MANAGER ASSUME LOGIN Test starts..");
		homeB.performLoginWithMobilePassword(testdata.get("POS_Customer_Mobile"), testdata.get("POS_Customer_Mobile_Password"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password to check user's cart empty..??");
		
		// assume login code here
		homeB.performAssumeLoginForStoreManager(customerType, testdata.get("POS_Customer_Mobile_New"));
		Assert.assertTrue(homeB.verifyUserSignup(), "User failed to Login");
		test.log(LogStatus.PASS, "User successfully creted new account..");
		
		homeB.clickHomePageVariant();
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpB.addVariantToCart();
		System.out.println("verifying cart page");
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");

		cartB.clickProceedToCheckout();
		Assert.assertTrue(addB.verifyAddressPageLoaded(), "Fail to load select address page..");
		test.log(LogStatus.INFO, "Address page loaded..");
		addB.selectDeliveryAddress();
		Assert.assertTrue(pymntB.verifyPaymentPageLoaded(), "Fail to load Payment page..");
		test.log(LogStatus.INFO, "Payment page loaded..");
		
		if("COD".equalsIgnoreCase(paymentType)) {				
			pymntB.performCODPayment();
			Assert.assertTrue(ordrSuccB.verifyOrderSuccessPageLoaded(), "Fail to load Order Success page..");
			test.log(LogStatus.PASS, "Order success page loaded..");		
			GlobalVar.OmniChnlOrderId = ordrSuccB.getGatewayOrderId();
			Assert.assertTrue(ordrSuccB.performValidationForOrder(GlobalVar.OmniChnlOrderId), "DB Validations failed for order placed..");
			test.log(LogStatus.PASS, "Order success DB validations verified..");
			ordrSuccB.releaseAssumeLoginOnOrdrSuccessPage();
			Assert.assertTrue(homeB.verifyAssumeLoginPresentForSM(), "Assume login release failed..!!");
			WebDriverUtil.reportLogAndPrintInConsole("Assume login released..");
		}
		else if("PAY AT STORE".equalsIgnoreCase(paymentType))
		{				
			pymntB.performPayAtStorePayment();
			Assert.assertTrue(ordrSuccB.verifyOrderSuccessPageLoaded(), "Fail to load Order Success page..");
			test.log(LogStatus.PASS, "Order success page loaded..");			
			GlobalVar.OmniChnlOrderId = ordrSuccB.getGatewayOrderId();
			Assert.assertTrue(ordrSuccB.performValidationForOrder(GlobalVar.OmniChnlOrderId), "DB Validations failed for order placed..");
			test.log(LogStatus.PASS, "Order success DB validations verified..");
			ordrSuccB.closeReleaseAssumeLoginPopup();
			ordrSuccB.performContinueShopping();
			homeB.releaseAssumeLogin();
			Assert.assertTrue(homeB.verifyAssumeLoginPresentForSM(), "Assume login release failed..!!");
			WebDriverUtil.reportLogAndPrintInConsole("Assume login released..");
		}
	}
}
