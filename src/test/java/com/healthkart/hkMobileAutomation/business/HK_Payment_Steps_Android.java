package com.healthkart.hkMobileAutomation.business;

import java.util.Map;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.healthkart.hkMobileAutomation.pages.HK_Payment_Page_Android;
import io.appium.java_client.android.AndroidDriver;

public class HK_Payment_Steps_Android 
{
	AndroidDriver<?> androidDriver;
	HK_Payment_Page_Android paymentsPage;
	
	public HK_Payment_Steps_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		paymentsPage = new HK_Payment_Page_Android(androidDriver);
	}

	public void selectPaymentOption(String paymentOption, Map<String,String> cardDetails) 
	{
		paymentsPage.selectPaymentOption(paymentOption, cardDetails);
	}
	
	public void selectPaymentOptionExpressCheckout(String paymentOption, Map<String,String> cardDetails)
	{
		paymentsPage.selectPaymentOptionExpressCheckout(paymentOption, cardDetails);
	}
	
	public void handleRateHealthkart() 
	{
		paymentsPage.clickLaterOnRateHealthKart();
		paymentsPage.handleReferAndEarn();
	}
	
	public void waitForPymntPage() {
		paymentsPage.waitForPymntOptions();
	}

	public boolean selectWalletAndVerify(String wallet) 
	{
		paymentsPage.selectWallet(wallet);
		WebDriverUtil.staticWait(2);
		return paymentsPage.verifyWalet(wallet);
	}

	public void useHKCash() 
	{
		paymentsPage.clickUseHKCash();
	}

	public void clickPlaceOrderButtonForHKCash() 
	{
		paymentsPage.clickPlaceOrderButtonForHKCash();
	}

	public void clickChooseCODPymnt() {
		paymentsPage.chooseCODPymnt();
	}

	public boolean verifyPayUPageDisplayed() 
	{
		return paymentsPage.verifyPayUPageDisplayed();
	}
	

}
