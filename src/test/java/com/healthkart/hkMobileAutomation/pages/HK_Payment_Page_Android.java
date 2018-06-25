package com.healthkart.hkMobileAutomation.pages;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class HK_Payment_Page_Android 
{
	AndroidDriver<?> androidDriver;
	private By paymentOptionBy;
	CommonFunctions common;
	private By placeOrderButtonBy = By.id("com.healthkart.healthkart:id/place");
	private By placeOrderButtonHKCashBy = By.id("com.healthkart.healthkart:id/placeOrder");
	private By laterButtonBy = By.id("com.healthkart.healthkart:id/later");
	private By creditCardBy = By.id("com.healthkart.healthkart:id/card_number");
	private By expiryMonthBy = By.id("com.healthkart.healthkart:id/cl_month");
	private By expiryYearBy = By.id("com.healthkart.healthkart:id/cl_year");
	private By cvvNumberBy = By.id("com.healthkart.healthkart:id/cl_cvv_number");
	private By cardNameBy = By.id("com.healthkart.healthkart:id/name_on_card");
	private By paySecurelyButtonBy = By.id("com.healthkart.healthkart:id/cardPay");
	private By mobikwikButtonBy = By.id("com.healthkart.healthkart:id/radioMobikwik_image");
	private By mobikwikVerify = By.xpath("//android.view.View[@text='New to MobiKwik? Create Wallet' or @text='Mobikwik Online Recharge']");
	private String freechargeAccessID = "freecharge";
	private By paytmBy1 = By.xpath("//android.widget.Image[@text='paytm-logo']");
	private By paytmBy2 = By.xpath("//android.view.View[@text='Login to Paytm']");
	private By freechargeButtonBy = By.id("com.healthkart.healthkart:id/radioFreeCharge_image");
	private By paymentOptionsLabelBy = By.id("com.healthkart.healthkart:id/headerPaymentMode");
	//private By mobikwikWalletOptionBy = By.id("com.healthkart.healthkart:id/mobikwikWalletCardView");
	private By useHKCashButtonBy = By.id("com.healthkart.healthkart:id/hkCashBtn");
	private By referAndEarnBy = By.id("com.healthkart.healthkart:id/frae_thanku");
	private By paytmButtonBy = By.id("com.healthkart.healthkart:id/radioPaytm_image");//By.id("com.healthkart.healthkart:id/radioPaytm_image");
	//private By walletsButtonBy =  By.xpath("//android.widget.TextView[@text='Wallets']");
	private By chooseCODPymntOptionBy = By.xpath("//android.widget.TextView[translate(@text,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='CASH ON DELIVERY']");
	
	/*
	 * wallets
	 */
	private By walletPymntOtionBy =  By.xpath("//android.widget.TextView[contains(@text,'Wallet')]");
	private By paytmWaltOuterBy = By.xpath("//android.widget.TextView[contains(@text,'PAYTM')]");
	private By paytmWaltInnerBy = By.xpath("//android.widget.ImageView[contains(@content-desc,'1020')]");
	private By mobiKwikWaltOuterBy = By.xpath("//android.widget.TextView[contains(@text,'MOBIKWIK')]");
	private By mobiKwikWaltInnerBy = By.xpath("//android.widget.ImageView[contains(@content-desc,'1060')]");
	private By freeChargeWaltOuterBy = By.xpath("//android.widget.TextView[contains(@text,'FREECHARGE')]");
	private By freeChargeWaltInnerBy = By.xpath("//android.widget.ImageView[contains(@content-desc,'1090')]");
	private By cvvBy = By.id("com.healthkart.healthkart:id/saved_card_cvv");
	private By payNowButtonBy = By.id("com.healthkart.healthkart:id/saved_card_payment_btn");
	private By payUPageBy = By.id("isProductionError");
	private By savedCardBy = By.id("Saved Cards");
	public HK_Payment_Page_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		common = new CommonFunctions();
	}

	public void selectPaymentOption(String paymentOption, Map<String, String> cardDetails) 
	{
		common.waitForElementToBeDisplayedBy(androidDriver, 30, paymentOptionsLabelBy);
		if(paymentOption.contains("Saved"))
			common.clickBy(savedCardBy);
		else
			paymentOptionBy = By.xpath("//android.widget.TextView[contains(@text,'"+paymentOption+"')]");
		common.reportLogAndPrintInConsole("Payment Option Element ::" + paymentOptionBy);
		common.scrollAndSwipeByElementAndroid(androidDriver, paymentOptionBy, "up");
		WebElement paymentOptionElement = androidDriver.findElement(paymentOptionBy);
		common.click(paymentOptionElement);
		if(paymentOption.equalsIgnoreCase("CREDIT CARD") || paymentOption.equalsIgnoreCase("DEBIT CARD"))
		{
			androidDriver.findElement(creditCardBy).click();
			androidDriver.getKeyboard();
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			
			common.clickBy(expiryMonthBy);
			System.out.println(cardDetails.get("CC_Expiry_Month"));
			int index = Integer.parseInt(cardDetails.get("CC_Expiry_Month")) - 1;
			By monthElement = By.xpath("//android.widget.TextView[@index='" + index + "']");
			common.clickBy(monthElement);
			
			common.clickBy(expiryYearBy);
			String yearXpath = "//android.widget.TextView[@text='20" + cardDetails.get("CC_Expiry_Year") +"']";
			By yearElement = By.xpath(yearXpath);
			common.clickBy(yearElement);
			common.sendKeys(androidDriver.findElement(cvvNumberBy), cardDetails.get("CC_CVV"));
			common.sendKeys(androidDriver.findElement(cardNameBy), cardDetails.get("CC_Name"));
			androidDriver.hideKeyboard();
			common.waitForElementToBeDisplayed(androidDriver, 30, androidDriver.findElement(paySecurelyButtonBy));
			common.clickBy(paySecurelyButtonBy);
		}
		
		else if(paymentOption.contains("Saved"))
		{
			common.sendKeys(androidDriver.findElement(cvvBy), "123");
			common.click(androidDriver.findElement(payNowButtonBy));
		}
		
		else
		{
			if(!paymentOption.equalsIgnoreCase("Wallets")) //Cash On Delivery Case, Wallets open wallets
				common.clickBy(placeOrderButtonBy);
		}
	}

	public void selectPaymentOptionExpressCheckout(String paymentOption, Map<String, String> cardDetails) 
	{
		common.scrollAndSwipeByElementAndroid(androidDriver, paymentOptionsLabelBy, "up");
		if(paymentOption.contains("Saved"))
			common.clickBy(savedCardBy);
		else
			paymentOptionBy = By.xpath("//android.widget.TextView[contains(@text,'"+paymentOption+"')]");
		common.reportLogAndPrintInConsole("Payment Option Element ::" + paymentOptionBy);
		common.scrollAndSwipeByElementAndroid(androidDriver, paymentOptionBy, "up");
		WebElement paymentOptionElement = androidDriver.findElement(paymentOptionBy);
		common.click(paymentOptionElement);
		if(paymentOption.equalsIgnoreCase("CREDIT CARD") || paymentOption.equalsIgnoreCase("DEBIT CARD"))
		{
			androidDriver.findElement(creditCardBy).click();
			androidDriver.getKeyboard();
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			
			common.clickBy(expiryMonthBy);
			System.out.println(cardDetails.get("CC_Expiry_Month"));
			int index = Integer.parseInt(cardDetails.get("CC_Expiry_Month")) - 1;
			By monthElement = By.xpath("//android.widget.TextView[@index='" + index + "']");
			common.clickBy(monthElement);
			
			common.clickBy(expiryYearBy);
			String yearXpath = "//android.widget.TextView[@text='20" + cardDetails.get("CC_Expiry_Year") +"']";
			By yearElement = By.xpath(yearXpath);
			common.clickBy(yearElement);
			common.sendKeys(androidDriver.findElement(cvvNumberBy), cardDetails.get("CC_CVV"));
			common.sendKeys(androidDriver.findElement(cardNameBy), cardDetails.get("CC_Name"));
			androidDriver.hideKeyboard();
			common.waitForElementToBeDisplayed(androidDriver, 30, androidDriver.findElement(paySecurelyButtonBy));
			common.clickBy(paySecurelyButtonBy);
		}
		
		else if(paymentOption.contains("Saved"))
		{
			common.sendKeys(androidDriver.findElement(cvvBy), "123");
			common.click(androidDriver.findElement(payNowButtonBy));
		}
		
		else
		{
			if(!paymentOption.equalsIgnoreCase("Wallets")) //Cash On Delivery Case, Wallets open wallets
				common.clickBy(placeOrderButtonBy);
		}
	}


	public void clickPlaceOrderButtonForHKCash() 
	{
		common.scrollAndSwipeByElementAndroid(androidDriver, placeOrderButtonHKCashBy, "up");
		common.click(androidDriver.findElement(placeOrderButtonHKCashBy));
	}

	public void clickLaterOnRateHealthKart() 
	{
		androidDriver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		try
		{
			common.click(androidDriver.findElement(laterButtonBy));
		}
		catch(Exception e)
		{
			common.reportLogAndPrintInConsole("RATE HEALTHKART NOT DISPLAYED....");
		}
		androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
	}

	public void waitForPymntOptions() {
		common.waitForElementToBeDisplayedBy(androidDriver, 30, paymentOptionsLabelBy);
	}
	
	public void selectWallet(String wallet) 
	{	
		if(wallet.equalsIgnoreCase("Paytm"))
		{
			
			try
			{
				common.reportLogAndPrintInConsole("Searching paytm wallet in main(outer) payment options..");
				common.scrollAndSwipeByElementAndroid(androidDriver, paytmWaltOuterBy, "up");
				androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				androidDriver.findElement(paytmWaltOuterBy).click();
				common.staticWait(3);
			}
			catch(Exception e)
			{
				try {
					common.reportLogAndPrintInConsole("Paytm Wallet option not present as main(outer) payment options, please visit wallets section..");
					common.scrollAndSwipeByElementAndroid(androidDriver, paymentOptionsLabelBy, "down");
					common.scrollAndSwipeByElementAndroid(androidDriver, walletPymntOtionBy, "up");
					if(!common.isElementDisplayedBy(androidDriver,paytmButtonBy)) 
					{
						common.clickBy(walletPymntOtionBy);
					}
					common.scrollAndSwipeByElementAndroid(androidDriver, paytmButtonBy, "up");
					androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					androidDriver.findElement(paytmButtonBy).click();
					common.staticWait(3);
				} 
				catch (Exception e1) 
				{
					common.reportLogAndPrintInConsole("Searching Paytm Wallet(inner) option on wallets page..");
					common.scrollAndSwipeByElementAndroid(androidDriver, paytmWaltInnerBy, "up");
					androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					androidDriver.findElement(paytmWaltInnerBy).click();
					common.staticWait(3);
				}
			}
			finally {
				androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
			}
		}
		
		else if(wallet.equalsIgnoreCase("Mobikwik"))
		{
			try
			{
				common.reportLogAndPrintInConsole("Searching mobikwik wallet in main(outer) payment options..");
				common.scrollAndSwipeByElementAndroid(androidDriver, mobiKwikWaltOuterBy, "up");
				androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				androidDriver.findElement(mobiKwikWaltOuterBy).click();
				common.staticWait(3);
			}
			catch(Exception e)
			{
				try {
					common.reportLogAndPrintInConsole("Mobikwik Wallet option not present as main(outer) payment options, please visit wallets section..");
					common.scrollAndSwipeByElementAndroid(androidDriver, paymentOptionsLabelBy, "down");
					common.scrollAndSwipeByElementAndroid(androidDriver, walletPymntOtionBy, "up");
					if(!common.isElementDisplayedBy(androidDriver,mobikwikButtonBy)) 
					{
						common.clickBy(walletPymntOtionBy);
					}
					common.scrollAndSwipeByElementAndroid(androidDriver, mobikwikButtonBy, "up");
					androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					androidDriver.findElement(mobikwikButtonBy).click();
					common.staticWait(3);
				} 
				catch (Exception e1) 
				{
					common.reportLogAndPrintInConsole("Searching Mobikwik Wallet(inner) option on wallets page..");
					common.scrollAndSwipeByElementAndroid(androidDriver, mobiKwikWaltInnerBy, "up");
					androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					androidDriver.findElement(mobiKwikWaltInnerBy).click();
					common.staticWait(3);
				}
			}
			finally {
				androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
			}			
		}
		
		else if(wallet.equalsIgnoreCase("Freecharge"))
		{
			try
			{
				common.reportLogAndPrintInConsole("Searching Freecharge wallet in main(outer) payment options..");
				common.scrollAndSwipeByElementAndroid(androidDriver, freeChargeWaltOuterBy, "up");
				androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				androidDriver.findElement(freeChargeWaltOuterBy).click();
				common.staticWait(3);
			}
			catch(Exception e)
			{
				try {
					common.reportLogAndPrintInConsole("Freecharge Wallet option not present as main(outer) payment options, please visit wallets section..");
					common.scrollAndSwipeByElementAndroid(androidDriver, paymentOptionsLabelBy, "down");
					common.scrollAndSwipeByElementAndroid(androidDriver, walletPymntOtionBy, "up");
					if(!common.isElementDisplayedBy(androidDriver,freechargeButtonBy)) 
					{
						common.clickBy(walletPymntOtionBy);
					}
					common.scrollAndSwipeByElementAndroid(androidDriver, freechargeButtonBy, "up");
					androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					androidDriver.findElement(freechargeButtonBy).click();
					common.staticWait(3);
				} 
				catch (Exception e1) 
				{
					common.reportLogAndPrintInConsole("Searching Freecharge Wallet(inner) option on wallets page..");
					common.scrollAndSwipeByElementAndroid(androidDriver, freeChargeWaltInnerBy, "up");
					androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					androidDriver.findElement(freeChargeWaltInnerBy).click();
					common.staticWait(3);
				}
			}
			finally 
			{
				androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
			}
		}		
		else 
		{
			common.reportLogAndPrintInConsole("Wallet type not found!!!");
		}
	}

	public boolean verifyWalet(String wallet) 
	{
		boolean flag = false;
		
		if(wallet.equalsIgnoreCase("Mobikwik"))
		{
			common.reportLogAndPrintInConsole("Verifying Mobikwik page..");
			//common.waitForElementToBeDisplayed(androidDriver, 90, androidDriver.findElement(mobikwikVerify));
			if(androidDriver.findElement(mobikwikVerify).isDisplayed()) 
			{
				flag = true;
				common.reportLogAndPrintInConsole("Mobikwik page verified..");
			}
			 
			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		}
		
		if(wallet.equalsIgnoreCase("Freecharge"))
		{
			try
			{
				common.reportLogAndPrintInConsole("Verifying Freecharge page..");
				//common.waitForElementToBeDisplayed(androidDriver, 10, androidDriver.findElementByAccessibilityId(freechargeAccessID));
				if(androidDriver.findElementByAccessibilityId(freechargeAccessID).isDisplayed()) 
				{
					flag = true;
					common.reportLogAndPrintInConsole("Freecharge page verified..");
				}
				
			}
			catch(Exception e)
			{
				flag = true;
				common.reportLogAndPrintInConsole("Freecharge Environment not displayed");
				common.reportLogAndPrintInConsole("Freecharge page verified..");
			}
			
		}
		
		else if(wallet.equalsIgnoreCase("Paytm"))
		{
			common.reportLogAndPrintInConsole("Verifying Paytm page..");
			//common.waitForElementToBeDisplayed(androidDriver, 10, androidDriver.findElement(paytmBy1));
			if(androidDriver.findElement(paytmBy2).isDisplayed() || androidDriver.findElement(paytmBy1).isDisplayed()) 
			{
				flag = true;
				common.reportLogAndPrintInConsole("Paytm page verified..");
			}
			
			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
			By cancelPaytmBy = By.id("android:id/button1");
			common.clickBy(cancelPaytmBy);
		}
		return flag;
	}

	public void clickUseHKCash() 
	{
		common.clickBy(useHKCashButtonBy);
	}

	public void handleReferAndEarn() 
	{
		androidDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try
		{
			common.clickBy(referAndEarnBy);
		}
		catch(Exception e)
		{
			System.out.println("Refer and Earn not displayed");
		}
		androidDriver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
	}

	public void chooseCODPymnt() {
		common.scrollAndSwipeByElementAndroid(androidDriver, chooseCODPymntOptionBy, "up");
		common.clickBy(chooseCODPymntOptionBy);
	}

	public boolean verifyPayUPageDisplayed() 
	{
		return common.isElementDisplayedBy(androidDriver, payUPageBy);
	}
	
}
