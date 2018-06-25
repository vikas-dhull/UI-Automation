package com.healthkart.hkAutomation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_OrderSuccessPage_p {
	
	public WebDriver driver;
	
	public HK_OrderSuccessPage_p(WebDriver driver) {		
		this.driver = driver;
		PageFactory.initElements(driver, this);
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
	 * Order placed successfully text.
	 */
	@FindBy(xpath="//div[text()='Your order is placed successfully!']")
	private WebElement orderPlaceSuccessText;
	
	public boolean verifyOrderSuccessText() {	
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
		WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);
		
		return (WebDriverUtil.isElementPresent(orderPlaceSuccessText, driver,10));

	}
	
	/**
	 * Is Order Success page loaded successfully?
	 */
	public boolean verifyOrderSuccessPageLoaded() {
		boolean  flag = false;
		WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);
		if(WebDriverUtil.isElementPresent(orderPlaceSuccessText, driver,10)) {
			if(driver.getTitle().contains("HealthKart.com") && driver.getCurrentUrl().contains("gateway/response"))
				flag = true;
		}			
		return flag;
	}
	
	/**
	 * Continue Shopping. 
	 */
	@FindBy(xpath="//a[contains(text(),'CONTINUE SHOPPING') and contains(@class,'btn-blue')]")
	private WebElement continueShopping;
	
	public void clickContinueShopping() {
		if(WebDriverUtil.isElementPresent(continueShopping, driver,10)) {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+continueShopping.getLocation().y+")");
			WebDriverUtil.click(driver, continueShopping, "continueShopping");
		}
	}
	
	/**
	 * Gateway Order ID.
	 */
	@FindBy(xpath="//p[contains(@class,'order-id')]")
	private WebElement gatewayOrderId;
	
	public String getGatewayOrderId() {
		String orderId=null;
		int substringIndex= 0;
		if(WebDriverUtil.isElementPresent(gatewayOrderId, driver,10)) {
			orderId=gatewayOrderId.getAttribute("innerText");
			System.out.println("order Id element text : " + orderId);			
			
		       char[] array= orderId.toCharArray();
		       int arrayIndex = 0;
		       for(char c: array){		    	   
		           System.out.print(c);
		           if(c=='H') {
		        	   substringIndex=arrayIndex;
		        	   System.out.println(" ...Sub String Index captured: " + substringIndex);
		        	   break;
		           }
		           arrayIndex++;
		       }
		}
		System.out.println("final GatewayOrderId returned :"+ orderId.substring(substringIndex));
		return orderId.substring(substringIndex);
	}
	
	/**
	 * Order Total payable amount.
	 */

	@FindBy(xpath="//div[@class='cart-summary-details']//span[@data-role='total-payable']")
	private WebElement orderTotalAmnt;
	
	public String getOrderTotalAmnt() {
		String orderId=null;
		if(WebDriverUtil.isElementPresent(orderTotalAmnt, driver,10)) {
			orderId=orderTotalAmnt.getText();
		}
		return orderId;
	}
	
	/**
	 * Order success overlay Pop-up - close;
	 */
	
	@FindBy(xpath="//div[@id='tnp_js-modal-overlay' and @style='visibility: visible;']/preceding-sibling::div[@id='tnp_js-modal']//a[@id='tnp_js-modal-close-button']")
	private WebElement closeOverlayPopup;
	
	public boolean verifyOverlayPopupPresent() {
		return (WebDriverUtil.isElementPresent(closeOverlayPopup, driver,10));		
	}
	
	public void clickCloseOverlayPopup() {
		if(WebDriverUtil.isElementPresent(closeOverlayPopup, driver,10)) {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+closeOverlayPopup.getLocation().y+")");
			WebDriverUtil.click(driver, closeOverlayPopup, "closeOverlayPopup");
		}
	}
	
	@FindBy(xpath="//div/a[contains(text(),'Logout User Account')]")
	private WebElement LogoutUserAccnt;
	
	public void releaseAssumeLoginOnOrdrSuccessPage() {
		WebDriverUtil.click(driver, LogoutUserAccnt, "LogoutUserAccnt");
	}
	
	@FindBy(xpath="//div[@class='myClass']")
	private WebElement closeReleaseAssumeLoginPopup;
	
	public void closeReleaseAssumeLoginPopup() {
		WebDriverUtil.click(driver, closeReleaseAssumeLoginPopup, "closeReleaseAssumeLoginPopup");
	}
	
	
	
}
