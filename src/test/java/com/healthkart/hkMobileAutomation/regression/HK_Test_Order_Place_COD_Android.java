package com.healthkart.hkMobileAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Test_Order_Place_COD_Android extends ExtentReportingBaseUtil
{
	@Test(priority = 1)
	public void testPromptOrderPlaceCODWithLoginPopup() 
	{
		System.out.println("## ORDER PLACE COD LOGIN FROM POPUP TEST STARTS..");
		homeSteps.proceedToLogin();
		loginSteps.performLogin(mobileNumber, password);
		homeSteps.verifySuccessfullLogin();
		homeSteps.proceedToCart();
		cartSteps.makeCartEmpty();
		cartSteps.clickStartShopping();
		homeSteps.selectProductCategories("Sports Nutrition", "Proteins", "Whey Proteins");
		homeSteps.selectFirstProductFromSearchResult();
		pdpSteps.clickAddToCart();
		WebDriverUtil.staticWait(1);
		cartSteps.proceedToCheckout();
		WebDriverUtil.staticWait(1);
		checkout.selectAddress();
		checkout.proceedToPay();
		if(GlobalVar.jenkinsEnvironment==null)
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				paymentSteps.clickChooseCODPymnt();			
			}
			else {
				paymentSteps.selectPaymentOption("CASH ON DELIVERY",testdata);
//				paymentSteps.placeOrder();
				paymentSteps.handleRateHealthkart();
				WebDriverUtil.staticWait(3);
				Assert.assertTrue(orderSteps.performValidationForOrder());
				orderSteps.cancelOrderViaViewOrder();
				Assert.assertTrue(orderSteps.verifyOrderCancelled());
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			paymentSteps.clickChooseCODPymnt();
		}
		
		else if("qa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			paymentSteps.selectPaymentOption("CASH ON DELIVERY",testdata);
//			paymentSteps.placeOrder();
			paymentSteps.handleRateHealthkart();
			WebDriverUtil.staticWait(3);
			Assert.assertTrue(orderSteps.performValidationForOrder());
			orderSteps.cancelOrderViaViewOrder();
			Assert.assertTrue(orderSteps.verifyOrderCancelled());
		}	
		
	}

}
