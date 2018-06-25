package com.healthkart.hkAutomation.pages;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_PaymentPage_p extends CommonFunctions{
	
	public WebDriver driver;
	public GenericDbActions dbActionsObj;
	
	public HK_PaymentPage_p(WebDriver driver) 
	{		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		dbActionsObj = new GenericDbActions();
	}
	
	/**
	 * Handle notify visitor[For PROD ENV only.]
	 */	
	/*@FindBy(xpath = "//a[@id='nv_js-modal-close-button']")
	private WebElement closeNotifyVisitorOverlay;
	
	public void clickCloseNotifyVisitorOverlay() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if(WebDriverUtil.isElementClickable(closeNotifyVisitorOverlay, driver,1))
	       	closeNotifyVisitorOverlay.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebDriverUtil.click(driver, closeNotifyVisitorOverlay, "closeNotifyVisitorOverlay");
	    if(WebDriverUtil.isElementClickable(closeNotifyVisitorOverlay, driver,20))
	       	closeNotifyVisitorOverlay.click();
	}*/
	
	/**
	 * Is payment page loaded successfully?
	 */
	public boolean verifyPaymentPageLoaded() {
		boolean  flag = false;
		
		/*if(GlobalVar.jenkinsEnvironment==null)        // Handle notify visitor overlay [For PROD ENV only.]
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);			
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);
		}*/
		WebDriverUtil.staticWait(5);
		if(driver.getTitle().contains("Select Payment Mode") && driver.getCurrentUrl().contains("SelectPaymentMode.action")){
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			if(WebDriverUtil.isElementPresent(chooseCODPymnt, driver,10) || WebDriverUtil.isElementPresent(confirmHKCashOrder, driver,5))
				flag = true;
			driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		}
		return flag;
	}
	
	/**
	 * Choose COD option for Payment.
	 */
	@FindBy(xpath="//ul[@id='nav']/li[contains(text(),'CASH ON DELIVERY')]")
	private WebElement chooseCODPymnt;
	
	public void clickChooseCODPymnt() {
		WebDriverUtil.click(driver, chooseCODPymnt, "chooseCODPymnt");
		
		/*if(WebDriverUtil.isElementPresent(chooseCODPymnt, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(chooseCODPymnt).build().perform();
	        if(WebDriverUtil.isElementClickable(chooseCODPymnt, driver))
	        	chooseCODPymnt.click();
		}*/		
	}
	
	/**
	 * Read Captcha.
	 */	
	@FindBy(xpath="//img[@id='js-captcha-img']")
	private WebElement readCaptcha;
	
	public boolean verifyCaptchaPresent() {
		return (WebDriverUtil.isElementPresent(readCaptcha, driver,20));
	}
	
	/**
	 * Place Order Button.
	 */
	@FindBy(xpath="//input[@data-cod='999']")
	private WebElement placeOrder;
	
	public boolean verifyPlaceOrderButtonPresent() {
		return (WebDriverUtil.isElementPresent(placeOrder, driver,20));
	}
	
	public void clickPlaceOrder() {
		WebDriverUtil.click(driver, placeOrder, "placeOrder");
		
		/*if(WebDriverUtil.isElementPresent(placeOrder, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(placeOrder).build().perform();
	        if(WebDriverUtil.isElementClickable(placeOrder, driver))
	        	placeOrder.click();
		}*/		
	}
	
	/**
	 * Choose PAY AT STORE
	 */
	@FindBy(xpath="//div/ul/li[contains(text(),'PAY AT STORE')]")
	private WebElement choosePayAtStorePymnt;
	
	public void clickChoosePayAtStorePymnt() {
		WebDriverUtil.click(driver, choosePayAtStorePymnt, "choosePayAtStorePymnt");
	}
	
	/**
	 * Enter Split payment of cash @ PAY AT STORE
	 */
	@FindBy(xpath="//input[@id='payAtStoreId']")
	private WebElement cashPayAtStorePymnt;
	
	public void enterCashAmnt() {
		cashPayAtStorePymnt.sendKeys("500");
	}
	
	/**
	 * Enter secret key for PAY AT STORE
	 */
	@FindBy(xpath="//input[@name='secretKey']")
	private WebElement secretKeyPayAtStorePymnt;
	
	public void enterSecretKey() {
		secretKeyPayAtStorePymnt.sendKeys("623529");
	}
	
	/**
	 * Place Order PAY AT STORE
	 */
	@FindBy(xpath="//input[@id='splitPaymentButton']")
	private WebElement placeOrderPayAtStorePymnt;
	
	public void clickPlaceOrderPayAtStorePymnt() {
		WebDriverUtil.click(driver, placeOrderPayAtStorePymnt, "placeOrderPayAtStorePymnt");
	}
	
	/**
	 * Choose Debit card option for Payment.
	 */
	@FindBy(xpath="//ul[@id='nav']/li[contains(text(),'DEBIT CARD')]")
	private WebElement chooseDCPymnt;
	
	public void clickChooseDCPymnt() {
		WebDriverUtil.click(driver, chooseDCPymnt, "chooseDCPymnt");
		/*if(WebDriverUtil.isElementPresent(chooseDCPymnt, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(chooseDCPymnt).build().perform();
	        if(WebDriverUtil.isElementClickable(chooseDCPymnt, driver))
	        	chooseDCPymnt.click();
		}*/		
	}
	
	/**
	 * Choose Credit card option for Payment.
	 */
	@FindBy(xpath="//ul[@id='nav']/li[contains(text(),'CREDIT CARD')]")
	private WebElement chooseCCPymnt;
	
	public void clickChooseCCPymnt() {
		WebDriverUtil.click(driver, chooseCCPymnt, "chooseCCPymnt");
		/*if(WebDriverUtil.isElementPresent(chooseCCPymnt, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(chooseCCPymnt).build().perform();
	        if(WebDriverUtil.isElementClickable(chooseCCPymnt, driver))
	        	chooseCCPymnt.click();
		}*/		
	}
	
	/**
	 * Credit card inputs - card number.  
	 */
	@FindBy(xpath="//div[@id='v-nav']//form[contains(@class,'CC')]//input[@name='ccnum']")
	private WebElement ccCardNum;
	
	public void setCcCardNum(String ccnum) {
		if(WebDriverUtil.isElementPresent(ccCardNum, driver,20)) {
			ccCardNum.clear();
			ccCardNum.sendKeys(ccnum);
		}
	}	
	
	/**
	 * Debit card inputs - card number.  
	 */
	@FindBy(xpath="//div[@id='v-nav']//form[contains(@class,'DC')]//input[@name='ccnum']")
	private WebElement dcCardNum;
	
	public void setDcCardNum(String ccnum) {
		if(WebDriverUtil.isElementPresent(dcCardNum, driver,20)) {
			dcCardNum.clear();
			dcCardNum.sendKeys(ccnum);
		}
	}
	
	/**
	 * Credit card inputs - card expiry month.  
	 */
	@FindBy(xpath="//div[@id='v-nav']//form[contains(@class,'CC')]//select[@name='ccexpmon']")
	private WebElement ccCardExpMonth;
	
	public void setCcCardExpMonth(String expMonth) {
		if(WebDriverUtil.isElementPresent(ccCardExpMonth, driver,20)) {
			Select sortBy = new Select(ccCardExpMonth);
			sortBy.selectByVisibleText(expMonth);
		}
	}
	
	/**
	 * Debit card inputs - card expiry month.  
	 */
	@FindBy(xpath="//div[@id='v-nav']//form[contains(@class,'DC')]//select[@name='ccexpmon']")
	private WebElement dcCardExpMonth;
	
	public void setDcCardExpMonth(String expMonth) {
		if(WebDriverUtil.isElementPresent(dcCardExpMonth, driver,20)) {
			Select sortBy = new Select(dcCardExpMonth);
			sortBy.selectByVisibleText(expMonth);
		}
	}
	
	/**
	 * Credit card inputs - card expiryYear.  
	 */
	@FindBy(xpath="//div[@id='v-nav']//form[contains(@class,'CC')]//select[@name='ccexpyr']")
	private WebElement ccCardExpYear;
	
	public void setCcCardExpYear(String expYear) {
		if(WebDriverUtil.isElementPresent(ccCardExpYear, driver,20)) {
			Select sortBy = new Select(ccCardExpYear);
			sortBy.selectByVisibleText(expYear);
		}
	}
	
	/**
	 * Debit card inputs - card expiryYear.  
	 */
	@FindBy(xpath="//div[@id='v-nav']//form[contains(@class,'DC')]//select[@name='ccexpyr']")
	private WebElement dcCardExpYear;
	
	public void setDcCardExpYear(String expYear) {
		if(WebDriverUtil.isElementPresent(dcCardExpYear, driver,20)) {
			Select sortBy = new Select(dcCardExpYear);
			sortBy.selectByVisibleText(expYear);
		}
	}
	
	/**
	 * Credit card inputs - card CVV number.
	 */
	@FindBy(xpath="//div[@id='v-nav']//form[contains(@class,'CC')]//input[@name='ccvv']")
	private WebElement ccCardCVV;
	
	public void setCcCardCVV(String cardcvv) {
		if(WebDriverUtil.isElementPresent(ccCardCVV, driver,20)) {
			ccCardCVV.clear();
			ccCardCVV.sendKeys(cardcvv);
		}
	}
	
	/**
	 * Debit card inputs - card CVV number.
	 */
	@FindBy(xpath="//div[@id='v-nav']//form[contains(@class,'DC')]//input[@name='ccvv']")
	private WebElement dcCardCVV;
	
	public void setDcCardCVV(String cardcvv) {
		if(WebDriverUtil.isElementPresent(dcCardCVV, driver,20)) {
			dcCardCVV.clear();
			dcCardCVV.sendKeys(cardcvv);
		}
	}
	
	/**
	 * Credit card inputs - Name on card.
	 */
	@FindBy(xpath="//div[@id='v-nav']//form[contains(@class,'CC')]//input[@name='ccname']")
	private WebElement ccNameOnCard;
	
	public void setCcNameOnCard(String name) {
		if(WebDriverUtil.isElementPresent(ccNameOnCard, driver,20)) {
			ccNameOnCard.clear();
			ccNameOnCard.sendKeys(name);
		}
	}
	
	/**
	 * Debit card inputs - Name on card.
	 */
	@FindBy(xpath="//div[@id='v-nav']//form[contains(@class,'DC')]//input[@name='ccname']")
	private WebElement dcNameOnCard;
	
	public void setDcNameOnCard(String name) {
		if(WebDriverUtil.isElementPresent(dcNameOnCard, driver,20)) {
			dcNameOnCard.clear();
			dcNameOnCard.sendKeys(name);
		}
	}
	
	/**
	 * Credit card inputs - save card.
	 */
	@FindBy(xpath="//div[@id='v-nav']//form[contains(@class,'CC')]//input[@class='js-save-card']")
	private WebElement ccSaveCard;
	
	public void uncheckCcSaveCard() {
		if(WebDriverUtil.isElementPresent(ccSaveCard, driver,20)) {
			if(ccSaveCard.getAttribute("checked").contains("checked")) {
				WebDriverUtil.click(driver, ccSaveCard, "ccSaveCard");
				
				/*Actions action = new Actions(driver);		 
		        action.moveToElement(ccSaveCard).build().perform();
		        if(WebDriverUtil.isElementClickable(ccSaveCard, driver))
		        	ccSaveCard.click();*/
			}
		}		
	}	
	
	/**
	 * Debit card inputs - save card.
	 */
	@FindBy(xpath="//div[@id='v-nav']//form[contains(@class,'DC')]//input[@class='js-save-card']")
	private WebElement dcSaveCard;
	
	public void uncheckDcSaveCard() {
		if(WebDriverUtil.isElementPresent(dcSaveCard, driver,20)) {
			if(dcSaveCard.getAttribute("checked").contains("checked")) {
				WebDriverUtil.click(driver, dcSaveCard, "dcSaveCard");
				/*Actions action = new Actions(driver);		 
		        action.moveToElement(dcSaveCard).build().perform();
		        if(WebDriverUtil.isElementClickable(dcSaveCard, driver))
		        	dcSaveCard.click();*/
			}    
		}		
	}	
	
	/**
	 * Credit card inputs - card name.  
	 */
	@FindBy(xpath="//div[@id='v-nav']//form[contains(@class,'CC')]//input[@name='Enter card name']")
	private WebElement ccCardName;
	
	public void setCcCardName(String name) {
		if(WebDriverUtil.isElementPresent(ccCardName, driver,20)) {
			ccCardName.clear();
			ccCardName.sendKeys(name);
		}
	}
	
	/**
	 * Debit card inputs - card name.  
	 */
	@FindBy(xpath="//div[@id='v-nav']//form[contains(@class,'DC')]//input[@name='Enter card name']")
	private WebElement dcCardName;
	
	public void setDcCardName(String name) {
		if(WebDriverUtil.isElementPresent(dcCardName, driver,20)) {
			dcCardName.clear();
			dcCardName.sendKeys(name);
		}
	}
	
	
	
	/**
	 * Choose Net Banking option for Payment.
	 */
	@FindBy(xpath="//ul[@id='nav']/li[contains(text(),'INTERNET BANKING')]")
	private WebElement chooseNetBankPymnt;
	
	public void clickChooseNetBankPymnt() {
		WebDriverUtil.click(driver, chooseNetBankPymnt, "chooseNetBankPymnt");
		/*if(WebDriverUtil.isElementPresent(chooseNetBankPymnt, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(chooseNetBankPymnt).build().perform();
	        if(WebDriverUtil.isElementClickable(chooseNetBankPymnt, driver))
	        	chooseNetBankPymnt.click();
		}*/		
	}
	
	/**
	 * Net Banking - choose ICICI bank.
	 */
	@FindBy(xpath="//div[contains(@class,'pmt-provider')]/span/img[@alt='ICICI Bank']")
	private WebElement chooseNetBankPymntICICI;
	
	public void clickChooseNetBankPymntICICI() {
		WebDriverUtil.click(driver, chooseNetBankPymntICICI, "chooseNetBankPymntICICI");
		/*if(WebDriverUtil.isElementPresent(chooseNetBankPymntICICI, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(chooseNetBankPymntICICI).build().perform();
	        if(WebDriverUtil.isElementClickable(chooseNetBankPymntICICI, driver))
	        	chooseNetBankPymntICICI.click();
		}*/		
	}
	
	/**
	 * Net Banking - choose HDFC bank.
	 */
	@FindBy(xpath="//div[contains(@class,'pmt-provider')]/span/img[@alt='HDFC Bank']")
	private WebElement chooseNetBankPymntHDFC;
	
	public void clickChooseNetBankPymntHDFC() {
		WebDriverUtil.click(driver, chooseNetBankPymntHDFC, "chooseNetBankPymntHDFC");
		/*if(WebDriverUtil.isElementPresent(chooseNetBankPymntHDFC, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(chooseNetBankPymntHDFC).build().perform();
	        if(WebDriverUtil.isElementClickable(chooseNetBankPymntHDFC, driver))
	        	chooseNetBankPymntHDFC.click();
		}*/		
	}
	
	/** ## INNER WALLET PAYMENT OPTION :-
	 * Choose Wallet option for Payment.
	 */
	@FindBy(xpath="//ul[@id='nav']/li[contains(text(),'Wallet')]")
	private WebElement chooseWalletPymnt;
	
	public void clickChooseWalletPymnt() {
		WebDriverUtil.click(driver, chooseWalletPymnt, "chooseWalletPymnt");
		/*if(WebDriverUtil.isElementPresent(chooseWalletPymnt, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(chooseWalletPymnt).build().perform();
	        if(WebDriverUtil.isElementClickable(chooseWalletPymnt, driver))
	        	chooseWalletPymnt.click();
		}*/		
	}
	
	/**
	 * Wallet option - Paytm wallet.
	 */
	@FindBy(xpath="//div[contains(@class,'pmt-provider')]/span/img[@alt='Paytm']")
	private WebElement chooseWalletPymntPAYTM;
	
	public void clickChooseWalletPymntPAYTM() {
		WebDriverUtil.click(driver, chooseWalletPymntPAYTM, "chooseWalletPymntPAYTM");
		/*if(WebDriverUtil.isElementPresent(chooseWalletPymntPAYTM, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(chooseWalletPymntPAYTM).build().perform();
	        if(WebDriverUtil.isElementClickable(chooseWalletPymntPAYTM, driver))
	        	chooseWalletPymntPAYTM.click();
		}*/		
	}
	
	/**
	 * Proceed To Payment for Paytm inner Wallet.
	 */
	@FindBy(xpath="//img[@alt='Paytm']/ancestor::div[contains(@class,'pmt-provider')]/following-sibling::p/input[@value='Proceed To Payment']")
	private WebElement proceedToInnerPaytmPayment;
	
	public void clickProceedToInnerPaytmPayment() {
		WebDriverUtil.click(driver, proceedToInnerPaytmPayment, "proceedToInnerPaytmPayment");
		/*if(WebDriverUtil.isElementPresent(proceedToInnerPaytmPayment, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(proceedToInnerPaytmPayment).build().perform();
	        if(WebDriverUtil.isElementClickable(proceedToInnerPaytmPayment, driver))
	        	proceedToInnerPaytmPayment.click();
		}*/		
	}
	
	/**
	 * Wallet option - MobiKwik wallet.
	 */
	@FindBy(xpath="//div[contains(@class,'pmt-provider')]/span/img[@alt='Mobikwik Wallet']")
	private WebElement chooseWalletPymntMOBIKWIK;
	
	public void clickChooseWalletPymntMOBIKWIK() {
		WebDriverUtil.click(driver, chooseWalletPymntMOBIKWIK, "chooseWalletPymntMOBIKWIK");
		/*if(WebDriverUtil.isElementPresent(chooseWalletPymntMOBIKWIK, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(chooseWalletPymntMOBIKWIK).build().perform();
	        if(WebDriverUtil.isElementClickable(chooseWalletPymntMOBIKWIK, driver))
	        	chooseWalletPymntMOBIKWIK.click();
		}*/		
	}
	
	/**
	 * Proceed To Payment for MobiKwik inner Wallet.
	 */
	@FindBy(xpath="//img[@alt='Mobikwik Wallet']/ancestor::div[contains(@class,'pmt-provider')]/following-sibling::p/input[@value='Proceed To Payment']")
	private WebElement proceedToInnerMobiKwikPayment;
	
	public void clickProceedToInnerMobiKwikPayment() {
		WebDriverUtil.click(driver, proceedToInnerMobiKwikPayment, "proceedToInnerMobiKwikPayment");
		/*if(WebDriverUtil.isElementPresent(proceedToInnerMobiKwikPayment, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(proceedToInnerMobiKwikPayment).build().perform();
	        if(WebDriverUtil.isElementClickable(proceedToInnerMobiKwikPayment, driver))
	        	proceedToInnerMobiKwikPayment.click();
		}*/		
	}
	
	/**
	 * Wallet option - PAYU Money wallet.
	 */
	@FindBy(xpath="//div[contains(@class,'pmt-provider')]/span/img[@alt='PayU Money']")
	private WebElement chooseWalletPymntPAYU;
	
	public void clickChooseWalletPymntPAYUMONEY() {
		WebDriverUtil.click(driver, chooseWalletPymntPAYU, "chooseWalletPymntPAYU");
		/*if(WebDriverUtil.isElementPresent(chooseWalletPymntPAYU, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(chooseWalletPymntPAYU).build().perform();
	        if(WebDriverUtil.isElementClickable(chooseWalletPymntPAYU, driver))
	        	chooseWalletPymntPAYU.click();
		}*/		
	}
	
	/**
	 * Proceed To Payment for PAYU Money inner Wallet.
	 */
	@FindBy(xpath="//img[@alt='PayU Money']/ancestor::div[contains(@class,'pmt-provider')]/following-sibling::p/input[@value='Proceed To Payment']")
	private WebElement proceedToInnerPayuMoneyPayment;
	
	public void clickProceedToInnerPayUMoneyPayment() {
		WebDriverUtil.click(driver, proceedToInnerPayuMoneyPayment, "proceedToInnerPayuMoneyPayment");
		/*if(WebDriverUtil.isElementPresent(proceedToInnerPayuMoneyPayment, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(proceedToInnerPayuMoneyPayment).build().perform();
	        if(WebDriverUtil.isElementClickable(proceedToInnerPayuMoneyPayment, driver))
	        	proceedToInnerPayuMoneyPayment.click();
		}*/		
	}
	
	/**
	 * Wallet option - Freecharge wallet.
	 */
	@FindBy(xpath="//div[contains(@class,'pmt-provider')]/span/img[@alt='FreeCharge']")
	private WebElement chooseWalletPymntFreecharge;
	
	public void clickChooseWalletPymntFreecharge() {
		WebDriverUtil.click(driver, chooseWalletPymntFreecharge, "chooseWalletPymntFreecharge");
		/*if(WebDriverUtil.isElementPresent(chooseWalletPymntFreecharge, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(chooseWalletPymntFreecharge).build().perform();
	        if(WebDriverUtil.isElementClickable(chooseWalletPymntFreecharge, driver))
	        	chooseWalletPymntFreecharge.click();
		}*/		
	}
	
	/**
	 * Proceed To Payment for FreeCharge inner Wallet.
	 */
	@FindBy(xpath="//img[@alt='FreeCharge']/ancestor::div[contains(@class,'pmt-provider')]/following-sibling::p/input[@value='Proceed To Payment']")
	private WebElement proceedToInnerFreeChargePayment;
	
	public void clickProceedToInnerFreeChargePayment() {
		WebDriverUtil.click(driver, proceedToInnerFreeChargePayment, "proceedToInnerFreeChargePayment");
		/*if(WebDriverUtil.isElementPresent(proceedToInnerFreeChargePayment, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(proceedToInnerFreeChargePayment).build().perform();
	        if(WebDriverUtil.isElementClickable(proceedToInnerFreeChargePayment, driver))
	        	proceedToInnerFreeChargePayment.click();
		}*/		
	}
	
	/** ## MAIN PAYMENT WALLET OPTION :-
	 * Choose Mobikwik Wallet option for Main Payment.
	 */
	@FindBy(xpath="//ul[@id='nav']/li[contains(text(),'MOBIKWIK WALLET')]")
	private WebElement chooseMobiKwikWalletPymnt;
	
	public boolean isMobikwikAvailableInMainPayment() {
		return (WebDriverUtil.isElementPresent(chooseMobiKwikWalletPymnt, driver,20));

	}
	
	public void clickChooseMobiKwikWalletPymnt() {
		WebDriverUtil.click(driver, chooseMobiKwikWalletPymnt, "chooseMobiKwikWalletPymnt");
		/*if(WebDriverUtil.isElementPresent(chooseMobiKwikWalletPymnt, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(chooseMobiKwikWalletPymnt).build().perform();
	        if(WebDriverUtil.isElementClickable(chooseMobiKwikWalletPymnt, driver))
	        	chooseMobiKwikWalletPymnt.click();
		}*/		
	}
	
	/**
	 * Proceed To Payment.
	 */
	@FindBy(xpath="//img[@alt='Mobikwik Wallet']/ancestor::div[2]/following-sibling::p/input[@value='Proceed To Payment']")
	private WebElement proceedToOuterMobikwikPayment;
	
	public void clickProceedToMainMobikwikPayment() {
		
		WebDriverUtil.click(driver, proceedToOuterMobikwikPayment, "proceedToOuterMobikwikPayment");
		/*if(WebDriverUtil.isElementPresent(proceedToOuterMobikwikPayment, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(proceedToOuterMobikwikPayment).build().perform();
	        if(WebDriverUtil.isElementClickable(proceedToOuterMobikwikPayment, driver))
	        	proceedToOuterMobikwikPayment.click();
		}*/		
	}
	
	/**
	 * Choose PayTm Wallet option for Main Payment.
	 */
	@FindBy(xpath="//ul[@id='nav']/li[contains(text(),'PAYTM WALLET')]")
	private WebElement choosePayTmWalletPymnt;
	
	public boolean isPayTmAvailableInMainPayment() {
		return (WebDriverUtil.isElementPresent(choosePayTmWalletPymnt, driver,20));

	}
	
	public void clickChoosePayTmWalletPymnt() {
		WebDriverUtil.click(driver, choosePayTmWalletPymnt, "choosePayTmWalletPymnt");
		/*if(WebDriverUtil.isElementPresent(choosePayTmWalletPymnt, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(choosePayTmWalletPymnt).build().perform();
	        if(WebDriverUtil.isElementClickable(choosePayTmWalletPymnt, driver))
	        	choosePayTmWalletPymnt.click();
		}*/		
	}
	
	/**
	 * Proceed To Payment.
	 */
	@FindBy(xpath="//img[@alt='Paytm']/ancestor::div[2]/following-sibling::p/input[@value='Proceed To Payment']")
	private WebElement proceedToOuterPayTmPayment;
	
	public void clickProceedToMainPayTmPayment() {
		WebDriverUtil.click(driver, proceedToOuterPayTmPayment, "proceedToOuterPayTmPayment");
		/*if(WebDriverUtil.isElementPresent(proceedToOuterPayTmPayment, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(proceedToOuterPayTmPayment).build().perform();
	        if(WebDriverUtil.isElementClickable(proceedToOuterPayTmPayment, driver))
	        	proceedToOuterPayTmPayment.click();
		}*/		
	}
	
	/**
	 * Choose PayU Money Wallet option for Main Payment.
	 */
	@FindBy(xpath="//ul[@id='nav']/li[contains(text(),'PAYU MONEY')]")
	private WebElement choosePayUMoneyWalletPymnt;
	
	public boolean isPayUMoneyAvailableInMainPayment() {
		return (WebDriverUtil.isElementPresent(choosePayUMoneyWalletPymnt, driver,20));

	}
	
	public void clickChoosePayUMoneyWalletPymnt() {
		WebDriverUtil.click(driver, choosePayUMoneyWalletPymnt, "choosePayUMoneyWalletPymnt");
		/*if(WebDriverUtil.isElementPresent(choosePayUMoneyWalletPymnt, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(choosePayUMoneyWalletPymnt).build().perform();
	        if(WebDriverUtil.isElementClickable(choosePayUMoneyWalletPymnt, driver))
	        	choosePayUMoneyWalletPymnt.click();
		}*/		
	}
	
	/**
	 * Proceed To Payment.
	 */
	@FindBy(xpath="//img[@alt='PayU Money']/ancestor::div[2]/following-sibling::p/input[@value='Proceed To Payment']")
	private WebElement proceedToMainPayUMoneyPayment;
	
	public void clickProceedToMainPayUMoneyPayment() {
		WebDriverUtil.click(driver, proceedToMainPayUMoneyPayment, "proceedToMainPayUMoneyPayment");
		/*if(WebDriverUtil.isElementPresent(proceedToMainPayUMoneyPayment, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(proceedToMainPayUMoneyPayment).build().perform();
	        if(WebDriverUtil.isElementClickable(proceedToMainPayUMoneyPayment, driver))
	        	proceedToMainPayUMoneyPayment.click();
		}*/		
	}
	
	/**
	 * Confirm Order for Free checkout (Order placed with HK Cash for full payment).
	 */
	
	@FindBy(xpath="//a[text()='Confirm Order']")
	private WebElement confirmHKCashOrder;
	
	public void clickConfirmHKCashOrder() {
		WebDriverUtil.click(driver, confirmHKCashOrder, "confirmHKCashOrder");
		/*if(WebDriverUtil.isElementPresent(confirmHKCashOrder, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(confirmHKCashOrder).build().perform();
	        if(WebDriverUtil.isElementClickable(confirmHKCashOrder, driver))
	        	confirmHKCashOrder.click();
		}*/		
	}
	
	@FindBy(xpath="//form[@class='savedCardForm'][1]//input[@name='cardSelection']")	
	private WebElement firstSavedCard;
	
	public void clickSavedCard()
	{
		clickThroughJavaScript(driver, firstSavedCard);
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
		WebDriverUtil.click(driver, makePaymentButton, "makePaymentButton");
	}
	
	public By payUPageBy = By.xpath("//div[@id='payu_logo']");
	
	public boolean verifyRedirectedToPayuPage()
	{
		return isElementDisplayedBy(driver,payUPageBy);
	}
	
	public By invalidCVVBy = By.xpath("//*[contains(text(),'Invalid CVV')]");
	public boolean verifyInavlidCVVDisplayed()
	{
		return isElementDisplayedBy(driver, invalidCVVBy);
	}
	
	@FindBy(xpath="//div[@class='tabs hide' and @style='display: block;']//input[@name='makePayment']")
	public WebElement proceedToPaymentButton;
	
	public void clickProceedToPayment()
	{
		WebDriverUtil.click(driver, proceedToPaymentButton, "proceedToPaymentButton");
	}
	
	@FindBy(xpath="//span[@class='img-box']/img")
	public WebElement bankAccounts;
	
	public boolean selectNetBankingAccounts()
	{
		clickChooseNetBankPymnt();
//		boolean displayFlag = true;
		boolean testFlag = true;
		Set<String> issuerIds = dbActionsObj.getActiveIssuerIds();
		for(String s : issuerIds)
		{
			clickChooseNetBankPymnt();
			String issuerIDXpath = "//input[@value='" + s + "']/parent::div";
			String bankNameXpath = "//input[@value='" + s + "']/following-sibling::*/img";
			String bankName = driver.findElement(By.xpath(bankNameXpath)).getAttribute("alt");
			reportLogAndPrintInConsole("Verifying "+bankName+" Internet Banking..");
			click(driver.findElement(By.xpath(issuerIDXpath)));
			String bankNameOnBankPagexpath = "//*[contains(text(),'" + bankName +"')]";
			clickProceedToPayment();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			WebDriverUtil.staticWait(4);
//			displayFlag = driver.findElement(By.xpath(bankNameOnBankPagexpath)).isDisplayed();
			String title = driver.getTitle();
			String dom = driver.getPageSource();			
			if(!(StringUtils.containsIgnoreCase(dom, bankName)))
			{
				if(!StringUtils.containsIgnoreCase(title, bankName))
				{
					if(!isElementPresentBy(driver, By.xpath(bankNameOnBankPagexpath)))
					{
						testFlag = false;
						reportLogAndPrintInConsole("Failed to verify title ::" + title + "### bankName ::" + bankName) ;
						break;
					}
				}
			}
			else
			{
				reportLogAndPrintInConsole("BankName :: " + bankName + " verified");
				driver.navigate().back();
			}
		}
//		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		return testFlag;
	}
	
	@FindBy(xpath="//div[contains(@class,'cart-summary-details')]//div[@class='itm-nm']")
	List<WebElement> cartItems;
	
	public int orderSummaryItemCount()
	{
		reportLogAndPrintInConsole("Items in cart ::  " + cartItems.size());
		return cartItems.size();
	}
	
	@FindBy(xpath="//div[contains(@class,'col-sm-4 hidden-xs checkoutright')]//input[@placeholder='Enter Promo Code']")
	WebElement promocodePlaceholder;
	
	public void setCouponCode(String couponCode)
	{
		sendKeys(promocodePlaceholder, couponCode);
	}
	
	@FindBy(xpath="//div[contains(@class,'col-sm-4 hidden-xs checkoutright')]//input[@value='Apply']")
	WebElement applyButton;
	
	public void clickApplyCouponCode()
	{
		click(applyButton);
	}
	
	@FindBy(xpath="//div[contains(@class,'hidden-xs')]//span[contains(text(),'Coupon Applied')]")
	WebElement couponCodeApplied;
	
	public boolean verifyCouponCodeApplied()
	{
		return couponCodeApplied.isDisplayed();
	}
	
	@FindBy(xpath="//div[contains(@class,'hidden-xs')]//span[@data-role='promo-discount']")
	WebElement promoDiscount;
	
	public boolean verifyPromoDiscountOnCart() {
		boolean flag = false;
		if(WebDriverUtil.isElementPresent(promoDiscount, driver,20)) {
			if(Integer.parseInt(promoDiscount.getText()) > 0)
				flag = true;
		}
		System.out.println("promo discount flag : " + flag);
		return flag;		
	}
	
	
}
