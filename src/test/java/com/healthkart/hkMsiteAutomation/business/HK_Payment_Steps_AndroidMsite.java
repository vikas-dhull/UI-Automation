package com.healthkart.hkMsiteAutomation.business;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkMsiteAutomation.pages.HK_Payment_Page_AndroidMsite;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;

public class HK_Payment_Steps_AndroidMsite 
{

	AndroidDriver<?> androidDriver;
	HK_Payment_Page_AndroidMsite pymntPage;
	CommonFunctions comnFunc;
	
	public HK_Payment_Steps_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		pymntPage = new HK_Payment_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
	}
	
	public boolean verifyPaymentPageLoaded() 
	{
		return pymntPage.verifyPaymentPageLoaded();
	}
	
	public void performCodPayment() 
	{
		pymntPage.chooseCodPayment();
	}
	
	public void performCreditCardPayment(String ccNo,String ccExpMnth,String ccExpYr,String ccCvv,String ccCrdName) 
	{
		pymntPage.chooseCreditCardPayment(ccNo,ccExpMnth,ccExpYr,ccCvv,ccCrdName);
	}
	
	public void performDebitCardPayment(String dcNo,String dcExpMnth,String dcExpYr,String dcCvv,String dcCrdName) 
	{
		pymntPage.chooseDebitCardPayment(dcNo,dcExpMnth,dcExpYr,dcCvv,dcCrdName);
	}

	public void performHKCashPayment() 
	{
		pymntPage.chooseHKCashPayment();
	}

	public void clickChooseCODPymnt()
	{
		pymntPage.clickCodPayment();
	}

	public boolean verifyCaptchaPresent() {
		return pymntPage.verifyCaptchaOnPymntPage();
	}
	
	public boolean verifyPlaceOrderButtonCODPresent() {
		return pymntPage.verifyPlaceOrderButtonCODPymnt();
	}

	public void verifySavedCards(String cvv) 
	{
		pymntPage.clickSavedCard();
		pymntPage.clickMakePayment();
		Assert.assertTrue(pymntPage.verifyInavlidCVVDisplayed());
		pymntPage.enterCVV(cvv);
		pymntPage.clickMakePayment();
		Assert.assertTrue(pymntPage.verifyRedirectedToPayuPage());
	}
}

