package com.healthkart.hkAutomation.business;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.healthkart.hkAutomation.pages.HK_PaymentPage_p;
import com.healthkart.hkAutomation.util.WebDriverUtil;


public class HK_PaymentPage_b extends HK_PaymentPage_p {

	public HK_PaymentPage_b(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Perform payment through COD.
	 */
	public void performCODPayment() {
		if(verifyPaymentPageLoaded()){
			clickChooseCODPymnt();
			clickPlaceOrder();
		}
		else {
			System.out.println("Something went wrong on Payment page..");
		}
	}
	
	/**
	 * Perform payment through PAY AT STORE.
	 */
	public void performPayAtStorePayment() {
		if(verifyPaymentPageLoaded()){
			clickChoosePayAtStorePymnt();
			enterCashAmnt();
			enterSecretKey();
			clickPlaceOrderPayAtStorePymnt();
		}
		else {
			System.out.println("Something went wrong on Payment page..");
		}
	}
	
	/**
	 * Perform payment through Credit Card.
	 */
	public void performCreditCardPayment(String ccNo,String ccExpMnth,String ccExpYr,String ccCvv,String ccCrdName) {
		if(verifyPaymentPageLoaded()){
			clickChooseCCPymnt();
			setCcCardNum(ccNo);
			WebDriverUtil.staticWait(2);
			setCcCardExpMonth(ccExpMnth);
			setCcCardExpYear(ccExpYr);
			setCcCardCVV(ccCvv);
			setCcNameOnCard(ccCrdName);
			//uncheckCcSaveCard();
			
			WebElement cCProceedToPymnt = driver.findElement(By.xpath("//div[@id='v-nav']//form[contains(@class,'CC')]//input[@value='Proceed To Payment']"));
			if(WebDriverUtil.isElementClickable(cCProceedToPymnt, driver,30))
				cCProceedToPymnt.click();
			
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
		}
		else {
			System.out.println("Something went wrong on Payment page..");
		}
		
		if(driver instanceof FirefoxDriver) {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
			catch (NoAlertPresentException e) 
			{ 
			    System.out.println("No Alert Present");
			}  
			
		}
	}
	
	/**
	 * Perform payment through Debit Card.
	 */
	public void performDebitCardPayment(String dcNo,String dcExpMnth,String dcExpYr,String dcCvv,String dcCrdName) {
		if(verifyPaymentPageLoaded()){
			clickChooseDCPymnt();
			setDcCardNum(dcNo);
			WebDriverUtil.staticWait(2);
			setDcCardExpMonth(dcExpMnth);
			setDcCardExpYear(dcExpYr);
			setDcCardCVV(dcCvv);
			setDcNameOnCard(dcCrdName);
			// uncheckDcSaveCard();
			
			WebElement dCProceedToPymnt = driver.findElement(By.xpath("//div[@id='v-nav']//form[contains(@class,'DC')]//input[@value='Proceed To Payment']"));
			if(WebDriverUtil.isElementClickable(dCProceedToPymnt, driver,30))
				dCProceedToPymnt.click();
			
				WebDriverUtil.staticWait(5);
		}
		else {
			System.out.println("Something went wrong on Payment page..");
		}
		
		if(driver instanceof FirefoxDriver) {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
			catch (NoAlertPresentException e) 
			{ 
			    System.out.println("No Alert Present");
			}  
			
		}
	}
	
	/**
	 * Perform payment through Net banking.
	 */
	public void performNetBankingPaymentICICI() {
		if(verifyPaymentPageLoaded()){
			clickChooseNetBankPymnt();
			clickChooseNetBankPymntICICI();
			// clickProceedToPayment();			
		}
		else {
			System.out.println("Something went wrong on Payment page..");
		}
	}
	
	/**
	 * Perform payment through HDFC Net banking.
	 */
	public void performNetBankingPayment() 
	{
		if(verifyPaymentPageLoaded())
		{
			clickChooseNetBankPymnt();
			selectNetBankingAccounts();
			clickProceedToPayment();			
		}
		else 
		{
			System.out.println("Something went wrong on Payment page..");
		}
	}
	
	/**
	 * Perform payment through PayTm Wallet.
	 */
	public void performWalletPaymentPayTm() {
		if(verifyPaymentPageLoaded()){
			if(isPayTmAvailableInMainPayment()) {
				clickChoosePayTmWalletPymnt();
				clickProceedToMainPayTmPayment();
			}
			else {
				clickChooseWalletPymnt();
				clickChooseWalletPymntPAYTM();
				clickProceedToInnerPaytmPayment();				
			}
		}
		else {
			System.out.println("Something went wrong on Payment page..");
		}
	}

	/**
	 * Perform payment through PayU Money Wallet.
	 */
	public void performWalletPaymentPayUMoney() {
		if(verifyPaymentPageLoaded()){
			if(isPayUMoneyAvailableInMainPayment()) {
				clickChoosePayUMoneyWalletPymnt();
				clickProceedToMainPayUMoneyPayment();
			}
			else {
				clickChooseWalletPymnt();
				clickChooseWalletPymntPAYUMONEY();
				clickProceedToInnerPayUMoneyPayment();				
			}
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		else {
			System.out.println("Something went wrong on Payment page..");
		}
	}
	
	/**
	 * Perform payment through FreeCharge Wallet.
	 */
	public void performWalletPaymentFreeCharge() {
		if(verifyPaymentPageLoaded()){
			clickChooseWalletPymnt();
			clickChooseWalletPymntFreecharge();
			clickProceedToInnerFreeChargePayment();			
		}
		else {
			System.out.println("Something went wrong on Payment page..");
		}
	}
	
	/**
	 * Perform payment through MobiKwik Wallet.
	 */
	public void performWalletPaymentMobiKwik() {
		if(verifyPaymentPageLoaded()){			
			if(isMobikwikAvailableInMainPayment()) {
				clickChooseMobiKwikWalletPymnt();
				WebElement proceedToMobiPymnt = driver.findElement(By.xpath("//ul[@id='nav']/li[contains(text(),'MOBIKWIK WALLET')]/ancestor::div[2]/div[2]//img[@alt='Mobikwik Wallet']/ancestor::div[3]/p/input[@value='Proceed To Payment']"));
				if(WebDriverUtil.isElementClickable(proceedToMobiPymnt, driver,30))
					proceedToMobiPymnt.click();
			}
			else {
				clickChooseWalletPymnt();
				clickChooseWalletPymntMOBIKWIK();
				WebElement proceedToMobiPymnt = driver.findElement(By.xpath("//div[contains(@class,'pmt-provider')]/span/img[@alt='Mobikwik Wallet']/ancestor::div[2]/p/input[@value='Proceed To Payment']"));
				if(WebDriverUtil.isElementClickable(proceedToMobiPymnt, driver,30))
					proceedToMobiPymnt.click();
			}
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			driver.findElement(By.xpath("//input[@id='logininput']")).clear();
			driver.findElement(By.xpath("//input[@id='logininput']")).sendKeys("9999900000");
			driver.findElement(By.xpath("//input[@id='pwdsignininput']")).clear();
			driver.findElement(By.xpath("//input[@id='pwdsignininput']")).sendKeys("test@123");
			driver.findElement(By.xpath("//input[@id='signinFrm']")).click(); 
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.findElement(By.xpath("//input[@value='Pay Now']")).click();
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		else {
			System.out.println("Something went wrong on Payment page..");
		}
	}

	public void verifySavedCards(String cvv) 
	{
		clickSavedCard();
		clickMakePayment();
		Assert.assertTrue(verifyInavlidCVVDisplayed());
		enterCVV(cvv);
		clickMakePayment();
		Assert.assertTrue(verifyRedirectedToPayuPage());
	}

	public boolean verifyBXGYapplied() 
	{
		return (orderSummaryItemCount()==2);
	}

	public boolean applyCouponCodeAndVerify(String couponCode) 
	{
		setCouponCode(couponCode);
		clickApplyCouponCode();
		staticWait(5);
		Assert.assertTrue(orderSummaryItemCount()==1);
		if(verifyCouponCodeApplied()) 
			return verifyPromoDiscountOnCart();
		else
			return false;
	}
	
}
