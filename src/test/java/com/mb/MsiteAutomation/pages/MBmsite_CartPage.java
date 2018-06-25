package com.mb.MsiteAutomation.pages;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidDriver;

public class MBmsite_CartPage extends CommonFunctions
{

	public AndroidDriver<WebElement> androidDriver;
	public GenericDbActions dbActions;
	
	public MBmsite_CartPage(AndroidDriver<WebElement> androidDriver) 
	{
		this.androidDriver = androidDriver;
		PageFactory.initElements(androidDriver, this);
		dbActions = new GenericDbActions();
	}
		
	@FindBy(xpath="//div[contains(@class,'visible-xs')]//a[text()='Proceed to checkout']")
	private WebElement proceedToCheckoutButton;
	
	@FindBy(xpath = "//div[contains(@class,'cart-parent visible-xs')]//a[contains(text(),'Start Shopping')]")
	private WebElement backToShopping;
	
	@FindBy(xpath="//span[@data-role='item-remove']")
	private WebElement removeItem;
	
	@FindBy(xpath="//img[contains(@id,'captcha')]")
	private WebElement captcha;
	
	@FindBy(xpath="//div[@style='display: block;']//input[@placeholder='Card Number']")
	private WebElement cardNumber;
	
	@FindBy(xpath="//div[@style='display: block;']//select[@name='ccexpmon']")
	private WebElement ccExpiryMonth;
	
	@FindBy(xpath="//div[@style='display: block;']//select[@name='ccexpyr']")
	private WebElement ccExpiryYear;
	
	@FindBy(xpath="//div[@style='display: block;']//input[@placeholder='CVV']")
	private WebElement cvvField;
	
	@FindBy(xpath="//div[@style='display: block;']//input[@placeholder='Name on card']")
	private WebElement cardName;
	
	@FindBy(xpath="//div[@style='display: block;']//input[@value='Proceed To Payment']")
	private WebElement proceedToPaymentButton;
	
	@FindBy(xpath="//h2[text()='YOUR PAYMENT WAS NOT SUCCESSFUL']")
	private WebElement paymentNotSuccessfull;
	
	@FindBy(name="couponCode")
	private WebElement couponCodeField;
	
	@FindBy(xpath="//a[text()='Apply']")
	private WebElement applyCouponButton;
	
	@FindBy(xpath="//span[@data-role='promo-discount']")
	private WebElement promoDiscount;
	
	@FindBy(xpath="//input[@data-cod='999']")
	private WebElement placeCodOrderButton;
	
	public void clickProceedToCheckout()
	{
		click(proceedToCheckoutButton);
	}
	
	
	public boolean backToShoppingBtnPresent() 
	{
		boolean flag = false;
		try
		{
			flag = backToShopping.isDisplayed();
		}
		catch(Exception e)
		{
			flag=false;
		}
		return flag;
	}

	public void clickRemoveFirstItem() 
	{
		scrollToObject(androidDriver, removeItem);
		WebDriverUtil.click(androidDriver, removeItem, "removeItem");
	}
	
	public void clickPaymentType(String paymentType)
	{
		String xpathExpression = "//div[contains(text(),'" + paymentType + "')]";
		if(paymentType.contains("WALLET"))
		{
			xpathExpression = "//div[contains(text(),'Wallet')]";
		}
		scrollToObjectWithMargin(androidDriver, androidDriver.findElement(By.xpath(xpathExpression)), 100);
		click(androidDriver.findElement(By.xpath(xpathExpression)));
	}
	
	public boolean isCaptchaDisplayed()
	{
		waitForElementToBeDisplayed(androidDriver, 30, captcha);
		return captcha.isDisplayed();
	}
	
	public void fillCardDetails(Map<String, String> cardDetails)
	{
		sendKeys(cardNumber, cardDetails.get("CC_Number"));
		sendKeys(cardName, cardDetails.get("CC_Name"));
		Select select = new Select(ccExpiryMonth);
		select.selectByValue(cardDetails.get("CC_Expiry_Month"));
		Select selectYear = new Select(ccExpiryYear);
		selectYear.selectByIndex(3);
		sendKeys(cvvField, cardDetails.get("CC_CVV"));
	}
	
	public void clickProceedToPayment()
	{
		click(proceedToPaymentButton);
	}
	
	public boolean isPaymentNotSuccessfullDisplayed()
	{
		return paymentNotSuccessfull.isDisplayed();
	}
	
	public boolean selectNetBankingAccounts()
	{
		scrollToObject(androidDriver, androidDriver.findElement(By.xpath("//div[contains(text(),'INTERNET BANKING')]")));
//		clickPaymentType("INTERNET BANKING");
//		boolean displayFlag = true;
		boolean testFlag = true;
		Set<String> issuerIds = dbActions.getActiveIssuerIds();
		for(String s : issuerIds)
		{
			clickPaymentType("INTERNET BANKING");
			String issuerIDXpath = "//input[@value='" + s + "']/parent::div/input";
			String bankNameXpath = "//input[@value='" + s + "']/following-sibling::*/img";
			String bankName = androidDriver.findElement(By.xpath(bankNameXpath)).getAttribute("alt");
			click(androidDriver.findElement(By.xpath(issuerIDXpath)));
			String bankNameOnBankPagexpath = "//*[contains(text(),'" + bankName +"')]";
			clickProceedToPayment();
			androidDriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
//			displayFlag = androidDriver.findElement(By.xpath(bankNameOnBankPagexpath)).isDisplayed();
			String title = androidDriver.getTitle();
			String dom = androidDriver.getPageSource();
			if(!(StringUtils.containsIgnoreCase(dom, bankName)))
			{
				if(!StringUtils.containsIgnoreCase(title, bankName))
				{
					if(!isElementPresentBy(androidDriver, By.xpath(bankNameOnBankPagexpath)))
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
				androidDriver.navigate().back();
			}
		}
//		androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		return testFlag;
	}
	
	public boolean selectWalletAccounts()
	{
//		clickPaymentType("WALLET");
//		boolean displayFlag = true;
		boolean testFlag = true;
		Set<String> issuerIds = dbActions.getActiveIssuerIdsWallet();
		for(String s : issuerIds)
		{
			clickPaymentType("Wallet");
			String issuerIDXpath = "//input[@value='" + s + "']/parent::div";
			String bankNameXpath = "//input[@value='" + s + "']/following-sibling::*/img";
			String walletName = androidDriver.findElement(By.xpath(bankNameXpath)).getAttribute("alt");
			click(androidDriver.findElement(By.xpath(issuerIDXpath)));
			String bankNameOnBankPagexpath = "//*[contains(text(),'" + walletName +"')]";
			clickProceedToPayment();
			androidDriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
//			displayFlag = androidDriver.findElement(By.xpath(bankNameOnBankPagexpath)).isDisplayed();
			String title = androidDriver.getTitle();
			String dom = androidDriver.getPageSource();
			if(!(StringUtils.containsIgnoreCase(dom, walletName)))
			{
				if(!StringUtils.containsIgnoreCase(title, walletName))
				{
					if(!isElementPresentBy(androidDriver, By.xpath(bankNameOnBankPagexpath)))
					{
						testFlag = false;
						reportLogAndPrintInConsole("Failed to verify title ::" + title + "### walletName ::" + walletName) ;
						break;
					}
				}
			}
			else
			{
				reportLogAndPrintInConsole("walletName :: " + walletName + " verified");
				androidDriver.navigate().back();
			}
		}
//		androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		return testFlag;
	}
	
	public void enterCouponCode(String code)
	{
		sendKeys(couponCodeField, code);
	}
	
	public void submitCouponCode()
	{
		click(applyCouponButton);
	}
	
	public boolean isCouponApplied()
	{
		String discount = promoDiscount.getText();
		float discount_int = Float.parseFloat(discount);
		reportLogAndPrintInConsole(discount);
		return (discount_int>0);
	}
	
	public void clickPlaceOrderButton()
	{
		click(placeCodOrderButton);
	}
	
	public void clickStartShoppingButton()
	{
		click(backToShopping);
	}
}
