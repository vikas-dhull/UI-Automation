package com.healthkart.hkAutomation.business;

import org.openqa.selenium.WebDriver;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.pages.HK_SecurePaytmWallet_p;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_SecurePaytmWallet_b extends HK_SecurePaytmWallet_p {
	
	public GenericDbActions dbActionsObj = new GenericDbActions(); 

	public HK_SecurePaytmWallet_b(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Perform PayTm transaction. 
	 */
	public void performPayTmTransaction() {
		
		if(verifyPayTmPageLoaded()){
			enterMobileNumber();
			clickRequestOtp();
			
			String paytmOtp = null;
			int counter = 0;
			WebDriverUtil.staticWait(10);			
			paytmOtp = dbActionsObj.getWalletOtp("paytm");
			while(paytmOtp == null && counter <= 12) {
				WebDriverUtil.staticWait(10);
				paytmOtp = dbActionsObj.getWalletOtp("paytm");
				counter = counter+1;
				System.out.println("otp attempt counter now: " + counter);
				GlobalVar.test.log(LogStatus.INFO, "otp attempt counter now: " + counter);
			}			
			enterPaytmOtp(paytmOtp);
			clickSubmitOtp();
			WebDriverUtil.staticWait(20);
		}
		else {
			System.out.println("Something went wrong on Payment page..");
			GlobalVar.test.log(LogStatus.ERROR, "Something went wrong on PAYTM Payment page..");
		}
	}

	public boolean verifyPaymentNotSuccessfull() 
	{
		return verifyPaymentNotSuccessfullDisplayed();
	}

}
