package com.healthkart.hkRetailAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_Place_Order_For_Omni_Channel_NPL extends ExtentReportingBaseUtil{
	
	private int cartCount;

	/**
	 * Test Order placement COD.
	 */
	@Test(priority = 1)
	@Parameters({"payment"})
	public void testOrderPlaceCODWithLoginPopup(String payment) 
	{    	
		System.out.println("## ORDER PLACE PL + NPL COD WITH LOGIN POPUP Test starts..");
		test.log(LogStatus.INFO, "ORDER PLACE PL + NPL COD WITH LOGIN POPUP Test starts..");
		/*
		 * This is to make user's cart empty before order placement.
		 */
		homeB.performLoginWithMobilePassword(testdata.get("POS_Customer_Mobile"), testdata.get("POS_Customer_Mobile_Password"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password to check user's cart empty..??");
		cartCount = homeB.getCartCountForUser();
		if(cartCount != 0) {
			test.log(LogStatus.INFO, "There are items in user's cart..");
			homeB.proceedToCart();
			Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
			test.log(LogStatus.INFO, "cart page loaded..");
			cartB.makeCartEmpty();
			cartB.clickBackToShopping();
			Assert.assertTrue(homeB.verifyHomePageloaded(), "home page not loaded..");
			test.log(LogStatus.INFO, "user cart is empty now..");
		}else {
			test.log(LogStatus.INFO, "User cart is found empty..");
		}
		WebDriverUtil.staticWait(2);
		// Add PL product to user cart.
		driver.get(GlobalVar.baseAppURL+testdata.get("Variant_URL")+homeB.getOmniChnlVariantId(testdata.get("POS_Store_Name")));
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpB.addVariantToCart();
		System.out.println("verifying cart page");
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");		
		cartB.removeBxGyOfferIfApplied();
		// Add NPL product to user cart.
		driver.get(GlobalVar.baseAppURL+testdata.get("Variant_URL")+pdpB.getCompareVariant());
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpB.addVariantToCart();
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		// order checkout 
		cartB.clickProceedToCheckout();
		Assert.assertTrue(addB.verifyAddressPageLoaded(), "Fail to load select address page..");
		test.log(LogStatus.INFO, "Address page loaded..");
		addB.selectDeliveryAddress();
		Assert.assertTrue(pymntB.verifyPaymentPageLoaded(), "Fail to load Payment page..");
		test.log(LogStatus.INFO, "Payment page loaded..");
		
		if("COD".equalsIgnoreCase(payment)) {				
			pymntB.performCODPayment();
			/*if(ordrSuccB.verifyOverlayPopupPresent())
				ordrSuccB.clickCloseOverlayPopup();*/
			Assert.assertTrue(ordrSuccB.verifyOrderSuccessPageLoaded(), "Fail to load Order Success page..");
			test.log(LogStatus.PASS, "Order success page loaded..");		
			GlobalVar.OmniChnlOrderId = ordrSuccB.getGatewayOrderId();
			Assert.assertTrue(ordrSuccB.performValidationForOrder(GlobalVar.OmniChnlOrderId), "DB Validations failed for order placed..");
			test.log(LogStatus.PASS, "Order success DB validations verified..");				
		}
		else if("Credit Card".equalsIgnoreCase(payment))
		{				
			pymntB.performCreditCardPayment(testdata.get("CC_Number"),testdata.get("CC_Expiry_Month")
					,testdata.get("CC_Expiry_Year"),testdata.get("CC_CVV"),testdata.get("CC_Name"));
			/*if(ordrSuccB.verifyOverlayPopupPresent())
				ordrSuccB.clickCloseOverlayPopup();*/
			Assert.assertTrue(ordrSuccB.verifyOrderSuccessPageLoaded(), "Fail to load Order Success page..");
			test.log(LogStatus.PASS, "Order success page loaded..");		
			GlobalVar.OmniChnlOrderId = ordrSuccB.getGatewayOrderId();
			Assert.assertTrue(ordrSuccB.performValidationForOrder(GlobalVar.OmniChnlOrderId), "DB Validations failed for order placed..");
			test.log(LogStatus.PASS, "Order success DB validations verified..");				
		}
		
		Assert.assertTrue(ordrSuccB.performValidationForOrderTypeOmniChnl(GlobalVar.OmniChnlOrderId), "Issue with routing order, please check..");
		test.log(LogStatus.PASS, "Order successfully routed to warehouse..");	
		
	}

}
