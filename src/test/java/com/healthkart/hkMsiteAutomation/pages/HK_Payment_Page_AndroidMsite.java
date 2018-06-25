package com.healthkart.hkMsiteAutomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class HK_Payment_Page_AndroidMsite {
	
	AndroidDriver<?> androidDriver;	
	CommonFunctions comnFunc;
	
	private By paymentOptionstext = By.xpath("//div[@class='card']/div[contains(text(),'choose a payment type')]");
	
	private By cashOnDelivery = By.xpath("//div[contains(text(),'CASH ON DELIVERY')]");
	private By placeCodOrderBtn = By.xpath("//input[@data-cod='999']");
	private By codCaptcha = By.xpath("//div[@class='captcha-cont']");
	
	private By creditCard = By.xpath("//div[contains(text(),'CREDIT CARD')]");
	private By creditCardNumberFeild = By.xpath("//form[@class='hkNativePaymentForm CC ']//input[contains(@class,'card-number')]");
	private By creditCardExpMnthFeild = By.xpath("//form[@class='hkNativePaymentForm CC ']//select[@name='ccexpmon']");
	private By creditCardExpYearFeild = By.xpath("//form[@class='hkNativePaymentForm CC ']//select[@name='ccexpyr']");
	private By creditCardCVVFeild = By.xpath("//form[@class='hkNativePaymentForm CC ']//input[@name='ccvv']");
	private By nameOnCreditCardFeild = By.xpath("//form[@class='hkNativePaymentForm CC ']//input[@name='ccname']");
	private By saveCreditCardCheckbox = By.xpath("//form[@class='hkNativePaymentForm CC ']//input[@name='storeCard']");
	private By creditCardNameFeild = By.xpath("//form[@class='hkNativePaymentForm CC ']//input[@class='js-save-card-name']");
	private By creditCardProceedToPaymentBtn = By.xpath("//form[@class='hkNativePaymentForm CC ']//input[@value='Proceed To Payment']");
	
	private By debitCard = By.xpath("//div[contains(text(),'DEBIT CARD')]");
	private By debitCardNumberFeild = By.xpath("//form[@class='hkNativePaymentForm  DC']//input[contains(@class,'card-number')]");
	private By debitCardExpMnthFeild = By.xpath("//form[@class='hkNativePaymentForm  DC']//select[@name='ccexpmon']");
	private By debitCardExpYearFeild = By.xpath("//form[@class='hkNativePaymentForm  DC']//select[@name='ccexpyr']");
	private By debitCardCVVFeild = By.xpath("//form[@class='hkNativePaymentForm  DC']//input[@name='ccvv']");
	private By nameOnDebitCardFeild = By.xpath("//form[@class='hkNativePaymentForm  DC']//input[@name='ccname']");
	private By saveDebitCardCheckbox = By.xpath("//form[@class='hkNativePaymentForm  DC']//input[@name='storeCard']");
	private By debitCardNameFeild = By.xpath("//form[@class='hkNativePaymentForm  DC']//input[@class='js-save-card-name']");
	private By debitCardProceedToPaymentBtn = By.xpath("//form[@class='hkNativePaymentForm  DC']//input[@value='Proceed To Payment']");
	
	private By wallets = By.xpath("//div[contains(text(),'Wallet')]");
	private By mobikwikWallet = By.xpath("//input[@value='PG670']");
	private By payuWallet = By.xpath("//input[@value='PG660']");
	private By freechargeWallet = By.xpath("//input[@value='PG1400']");
	private By paytmWallet = By.xpath("//input[@value='PG680']");
	private By walletProceedToPayment = By.xpath("");
	
	private By hkCashConfirmOrderBtn = By.xpath("//a[contains(text(),'Confirm Order')]");
	
	private By paytmWalletMain = By.xpath("//div[text()='PAYTM WALLET']");
	private By mobikwikWalletMain = By.xpath("//div[text()='MOBIKWIK WALLET']");
	private By payuMoneyWalletMain = By.xpath("//div[text()='PayUmoney']");
	private By freechargeWalletMain = By.xpath("//div[text()='FREECHARGE WALLET']");	
	
	private By closeNotifyVisitorOverlay = By.xpath("//a[@id='nv_js-modal-close-button']");
	
	public HK_Payment_Page_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		comnFunc = new CommonFunctions();
	}
	
	/**
	 * Handle notify visitor[For PROD ENV only.]
	 */
	public void clickCloseNotifyVisitorOverlay() {
	    /*if(comnFunc.isElementPresentBy(androidDriver, closeNotifyVisitorOverlay))
	       	comnFunc.clickBy(closeNotifyVisitorOverlay);*/
		WebDriverUtil.clickCloseNotifyVisitorOverlay(androidDriver);
	}

	public boolean verifyPaymentPageLoaded() 
	{		
		/*if(GlobalVar.jenkinsEnvironment==null)        // Handle notify visitor overlay [For PROD ENV only.]
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				clickCloseNotifyVisitorOverlay();			
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			clickCloseNotifyVisitorOverlay();
		}*/
		
		return comnFunc.isElementPresentBy(androidDriver, paymentOptionstext);
	}

	public void chooseCodPayment()
	{
		comnFunc.scrollToObject(androidDriver,androidDriver.findElement(cashOnDelivery));
		comnFunc.clickBy(cashOnDelivery);
		comnFunc.clickBy(placeCodOrderBtn);
	}
	
	public void clickCodPayment() 
	{
		comnFunc.scrollToObject(androidDriver,androidDriver.findElement(cashOnDelivery));
		comnFunc.clickBy(cashOnDelivery);
	}
	
	public boolean verifyCaptchaOnPymntPage() {
		return comnFunc.isElementPresentBy(androidDriver, codCaptcha);
	}
	public boolean verifyPlaceOrderButtonCODPymnt() {
		return comnFunc.isElementPresentBy(androidDriver, placeCodOrderBtn);
	}
	
	public void chooseCreditCardPayment(String ccNo,String ccExpMnth,String ccExpYr,String ccCvv,String ccCrdName)
	{
		Select chooseBy;
		comnFunc.clickBy(creditCard);
		comnFunc.sendKeys(androidDriver.findElement(creditCardNumberFeild), ccNo);
		chooseBy = new Select(androidDriver.findElement(creditCardExpMnthFeild));
		chooseBy.selectByVisibleText(ccExpMnth);
		chooseBy = new Select(androidDriver.findElement(creditCardExpYearFeild));
		chooseBy.selectByVisibleText(ccExpYr);
		comnFunc.sendKeys(androidDriver.findElement(creditCardCVVFeild), ccCvv);		
		comnFunc.sendKeys(androidDriver.findElement(nameOnCreditCardFeild), ccCrdName);
		//comnFunc.sendKeys(androidDriver.findElement(creditCardNameFeild), ccCrdName);
		androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		comnFunc.clickBy(creditCardProceedToPaymentBtn);		
	}
	
	public void chooseDebitCardPayment(String dcNo,String dcExpMnth,String dcExpYr,String dcCvv,String dcCrdName)
	{
		Select chooseBy;
		comnFunc.clickBy(debitCard);
		comnFunc.sendKeys(androidDriver.findElement(debitCardNumberFeild), dcNo);
		chooseBy = new Select(androidDriver.findElement(debitCardExpMnthFeild));
		chooseBy.selectByVisibleText(dcExpMnth);
		chooseBy = new Select(androidDriver.findElement(debitCardExpYearFeild));
		chooseBy.selectByVisibleText(dcExpYr);
		comnFunc.sendKeys(androidDriver.findElement(debitCardCVVFeild), dcCvv);		
		comnFunc.sendKeys(androidDriver.findElement(nameOnDebitCardFeild), dcCrdName);
		//comnFunc.sendKeys(androidDriver.findElement(debitCardNameFeild), dcCrdName);
		androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		comnFunc.clickBy(debitCardProceedToPaymentBtn);
	}
	
	public void chooseWalletPaymentPayTm()
	{
		comnFunc.clickBy(wallets);
		comnFunc.clickBy(paytmWallet);
		//comnFunc.clickBy();
	}
	
	public void chooseWalletPaymentMobikwik()
	{
		comnFunc.clickBy(wallets);
		comnFunc.clickBy(mobikwikWallet);
	}

	public void chooseHKCashPayment() 
	{
		comnFunc.scrollDownTillEnd(androidDriver);
		comnFunc.clickBy(hkCashConfirmOrderBtn);
	}

	@FindBy(xpath="//form[@class='savedCardForm'][1]//input[@name='cardSelection']")	
	private WebElement firstSavedCard;
	
	public void clickSavedCard()
	{
		comnFunc.click(firstSavedCard);
	}
	
	@FindBy(name="ccvv")
	private WebElement cvvField;
	
	public void enterCVV(String cvv)
	{
		cvvField.sendKeys(cvv);
	}
	
	@FindBy(xpath="//input[@type='button' and @value='Make Payment']")
	public WebElement makePaymentButton;
	
	public void clickMakePayment()
	{
		comnFunc.click(makePaymentButton);
	}
	
	public By payUPageBy = By.xpath("//div[@id='payu_logo']");
	
	public boolean verifyRedirectedToPayuPage()
	{
		return comnFunc.isElementDisplayedBy(androidDriver, payUPageBy);
	}
	
	public By invalidCVVBy = By.xpath("//*[contains(text(),'Invalid CVV')]");
	public boolean verifyInavlidCVVDisplayed()
	{
		return comnFunc.isElementDisplayedBy(androidDriver, invalidCVVBy);
	}
}
