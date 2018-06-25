package com.healthkart.hkRetailAutomation.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Retail_POS_Page {
	
	public WebDriver driver;
	
	public HK_Retail_POS_Page(WebDriver driver) {		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Verify POS Screen
	 */
	@FindBy(xpath = "//legend[contains(text(),'Scan Barcode')]")
	private WebElement posScanBarcodeText;
	@FindBy(xpath = "//b[text()='Customer']")
	private WebElement posClickPoint;
	
	public boolean verifyPOSScreen() {
		WebDriverUtil.staticWait(2);
		WebDriverUtil.scrollTillTop(driver);	
		return WebDriverUtil.isElementPresent(posScanBarcodeText, driver, 10);
	}
	
	/**
	 * Enter customer phone number
	 */
	@FindBy(xpath = "//input[@id='userPhone']")
	private WebElement posCustomerMobInpt;

	public void enterCustomerMobileNo(String mob) {
		posCustomerMobInpt.clear();
		posCustomerMobInpt.sendKeys(mob);
		posClickPoint.click();
		WebDriverUtil.staticWait(3);
	}
	
	/**
	 * Enter customer name
	 */
	@FindBy(xpath = "//input[@name='name']")
	private WebElement posCustomerNameInpt;
	
	public void enterCustomerName() {
		posCustomerNameInpt.click();
		String custName = posCustomerNameInpt.getAttribute("value");
		if("".equals(custName) || "NA".equals(custName)) {
			posCustomerNameInpt.clear();
			posCustomerNameInpt.sendKeys("Vikas");
			posClickPoint.click();
		}		
	}
	
	/**
	 * Enter customer Gym
	 */
	@FindBy(xpath = "//input[@id='gymAutoComp']")
	private WebElement posCustomerGymInpt;
	@FindBy(xpath = "//li[@role='menuitem']")
	private WebElement posCustomerGymInptList;

	public void enterCustomerGym() {  	
		posCustomerGymInpt.click();
		String gymName = posCustomerGymInpt.getAttribute("value");
		if("".equals(gymName) || "NA".equals(gymName)) {
			posCustomerGymInpt.clear();
			posCustomerGymInpt.sendKeys("ARMS");
			posClickPoint.click();			
		}			
	}
	
	/**
	 * enter customer address
	 */
	@FindBy(xpath = "//input[@id='line1']")
	private WebElement posCustomerAddLine1;
	@FindBy(xpath = "//input[@id='line2']")
	private WebElement posCustomerAddLine2;
	@FindBy(xpath = "//input[@id='pincode']")
	private WebElement posCustomerAddPin;

	public void enterCustomerAddDetails() {  	
		posCustomerAddLine1.click();
		String addLine1Text = posCustomerAddLine1.getAttribute("value");
		WebDriverUtil.common.reportLogAndPrintInConsole("ADDRESS Text: " + addLine1Text);
		if("".equals(addLine1Text) || "NA".equals(addLine1Text)) {
			posCustomerAddLine1.clear();
			posCustomerAddLine1.sendKeys("Test Line 1");
			posClickPoint.click();
			posCustomerAddLine2.clear();
			posCustomerAddLine2.sendKeys("Test Line 2");
			posClickPoint.click();
			posCustomerAddPin.clear();
			posCustomerAddPin.sendKeys("110074");
			posClickPoint.click();
			WebDriverUtil.common.staticWait(1);
		}			
	}
	
	/**
	 * update customer Info
	 */
	@FindBy(xpath = "//input[@id='updateCustomerInfo']")
	private WebElement posUpdtCstmrInfoBtn;
	
	public void clickCustomerInfoUpdate(){
		WebDriverUtil.click(driver,posUpdtCstmrInfoBtn,"posUpdtCstmrInfoBtn");
	}
	
	public boolean isCustomerInfoUpdateBtnPresent()
	{
		return WebDriverUtil.common.isElementPresent(driver, posUpdtCstmrInfoBtn);
	}
	
	/**
	 * handle new user alert
	 */
	public boolean handleNewUserPOSAlert(int countRec){
		boolean flag=false;
		if(WebDriverUtil.common.isAlertPresent(driver)) {
			Alert alert = driver.switchTo().alert();
			if(alert.getText().contains("undefined")) {
				WebDriverUtil.common.staticWait(2);
				alert.accept();
				clickCustomerInfoUpdate();
				if(countRec>0) {
					countRec--;
					flag=handleNewUserPOSAlert(countRec);
				}
			}
			else if(alert.getText().contains("Customer Info updated")) {
				WebDriverUtil.common.staticWait(2);
				alert.accept();
				flag=true;
			}			
		}
		return flag;
	}
	
	/**
	 * Scan barcode
	 */
	@FindBy(xpath = "//input[@name='productVariantBarcode']")
	private WebElement posScanBarcodeInpt;
	private String barcode = null;

	public void enterBarcode(String variantNUTId, String store) {
		barcode = WebDriverUtil.dbActionsObj.getBarcodeForPOS(variantNUTId, store);
		posScanBarcodeInpt.clear();
		posScanBarcodeInpt.sendKeys(barcode);
		posClickPoint.click();
		WebDriverUtil.common.staticWait(5);
	}
	
	public String getBarcodeScannedForOrder() {
		return barcode;
	}
	
	/**
	 * verify item added
	 */
	@FindBy(xpath = "//div[@class='alert messages']/ul/li[text()='Valid Barcode']")
	private WebElement posScanBarcodeSuccessText;
	@FindBy(xpath = "//tbody[@id='orderTable']/tr[contains(@class,'lineItemRow')]")
	private WebElement posScanBarcodeAddedToCart;
	
	public boolean verifyItemAddedToCart() {
		WebDriverUtil.common.waitForElementToBeDisplayed(driver, 10, posScanBarcodeSuccessText);
		return (WebDriverUtil.common.isElementPresent(driver, posScanBarcodeSuccessText) && 
				WebDriverUtil.common.isElementPresent(driver, posScanBarcodeAddedToCart));
	}
	
	/**
	 * Add freebie item
	 */
	@FindBy(xpath = "//input[@class='addfreebie']")
	private WebElement posAddFreebie;
	@FindBy(xpath = "//div[@class='alert messages']//ul//li[contains(text(),'We will scan barcode for your freebie')]")
	private WebElement posFreebieAddedText;
	
	public void addFreebie() {
		WebDriverUtil.common.waitForElementToBeDisplayed(driver, 10, posAddFreebie);
		if(WebDriverUtil.common.isElementPresent(driver, posAddFreebie))
			WebDriverUtil.click(driver, posAddFreebie, "posAddFreebie");
		else
			WebDriverUtil.common.reportLogAndPrintInConsole("add freebie checkbox not visible on POS..!!");
	}
	
	public boolean verifyAddFreebie() {
		return (WebDriverUtil.common.isElementPresent(driver,posFreebieAdded) && WebDriverUtil.common.isElementPresent(driver,posFreebieAddedText));
	}
	
	
	/**
	 * Apply prompt Offer
	 */	
	@FindBy(xpath = "//input[@class='offerID' and @value='12549']")
	private WebElement posPromptOffer;
	@FindBy(xpath = "//div[@class='alert messages']//ul//li[contains(text(),'Offer Applied')]")
	private WebElement posOfferAppliedText;  
	@FindBy(xpath = "//div[text()=' MuscleBlaze Shaker']/ancestor::tr[1]//input[@class='freebie' and @checked='checked']")
	private WebElement posFreebieAdded;
	@FindBy(xpath = "//div[text()=' MuscleBlaze Shaker']/ancestor::tr[1]//input[@class='offerprice']")
	private WebElement posOfferFreebiePriceVal;
	
	@FindBy(xpath = "//div[@class='removeapplyOffer']")
	private WebElement posPromptOfferRemove;
	@FindBy(xpath = "//div[@class='alert messages']//ul//li[contains(text(),'Offer has been removed succesfully')]")
	private WebElement posPromptOfferRemovedText;
	
	private double posOfferFreebiePrice;
	
	public void applyPromptOffer() {
		WebDriverUtil.common.waitForElementToBeDisplayed(driver, 10, posPromptOffer);
		WebDriverUtil.common.click(posPromptOffer);
	}
	
	/**
	 * Apply Offer with coupon code
	 */
	@FindBy(xpath = "//input[@id='applycoupn']")
	private WebElement posInputCouponCode;
	@FindBy(xpath = "//input[@id='apply-couponcode']")
	private WebElement posApplyCoupon;
	
	public void applyOfferWithCouponCode(String cpnCode) {
		WebDriverUtil.common.waitForElementToBeDisplayed(driver, 10, posInputCouponCode);
		posInputCouponCode.sendKeys(cpnCode);
		WebDriverUtil.common.click(posApplyCoupon);
	}
	
	public double posGetFreebieOfferPrice() {
		WebDriverUtil.common.scrollToObjectWithMargin(driver, posAvailableHKCashVal, 100);
		posOfferFreebiePrice = Double.parseDouble(posOfferFreebiePriceVal.getAttribute("value"));
		return posOfferFreebiePrice;
	}
	
	/**
	 * Verify Offer and Discount Applied
	 * @return
	 */	
	public boolean verifyOfferApplied() {
		return (WebDriverUtil.common.isElementPresent(driver,posOfferAppliedText) &&
				WebDriverUtil.common.isElementPresent(driver,posFreebieAdded) && WebDriverUtil.common.isElementPresent(driver,posPromptOfferRemove));
	}	
	
	/**
	 * Remove Offer
	 */	
	public void removePromptOffer() {
		WebDriverUtil.common.waitForElementToBeDisplayed(driver, 10, posPromptOfferRemove);
		WebDriverUtil.common.click(posPromptOfferRemove);
	}
	
	public boolean verifyPromptOfferRemoved() {
		return (WebDriverUtil.common.isElementPresent(driver,posPromptOfferRemovedText) &&
				!WebDriverUtil.common.isElementPresent(driver,posFreebieAdded));
	}
	
	/**
	 * Get Available HK-Cash
	 */
	@FindBy(xpath = "//input[@id='hkCash']")
	private WebElement posAvailableHKCashVal;
	@FindBy(xpath = "//input[@id='finalPayable']")
	private WebElement posOrderAmntVal;
	
	private double availableHKCash;
	private double orderAmount;
	
	public boolean posVerifyAvailableHKCash(String hkCashType) {
		boolean flag = false;
		WebDriverUtil.common.staticWait(2);
		WebDriverUtil.common.scrollToObjectWithMargin(driver, posAvailableHKCashVal, 100);
		WebDriverUtil.common.staticWait(1);
		try {
			availableHKCash= Double.parseDouble(posAvailableHKCashVal.getAttribute("value"));
			orderAmount = Double.parseDouble(posOrderAmntVal.getAttribute("value"));
		} catch (NumberFormatException e) {
			WebDriverUtil.common.reportLogAndPrintInConsole("NumberFormatException occures while capturing availableHKCash / orderAmount .. please check..!!");
			//e.printStackTrace();
		}
		if(availableHKCash > 0) {
			if("FULL".equalsIgnoreCase(hkCashType)) {
				if(availableHKCash >= orderAmount)
					flag=true;
				else
					WebDriverUtil.common.reportLogAndPrintInConsole("Sufficient HK-Cash NOT present for FULL HK-Cash payment..!!");
			}			
			else if("PARTIAL".equalsIgnoreCase(hkCashType)) {
				if(availableHKCash < orderAmount)
					flag=true;
				else
					WebDriverUtil.common.reportLogAndPrintInConsole("Sufficient HK-Cash NOT present for PARTIAL HK-Cash payment..!!");
			}
			else {
				WebDriverUtil.common.reportLogAndPrintInConsole("HK-Cash type payment not specified..!!");
			}
		}
		else {
			WebDriverUtil.common.reportLogAndPrintInConsole("HK-Cash is NOT present in customer account..!!");
		}
		return flag;
	}
	
	public double posGetAvailableHKCash() {
		availableHKCash= Double.parseDouble(posAvailableHKCashVal.getAttribute("value"));
		return availableHKCash;
	}
	public double posGetOrderAmount() {
		orderAmount = Double.parseDouble(posOrderAmntVal.getAttribute("value"));
		return orderAmount;
	}
	
	/**
	 * COD and Ship charges
	 */
	@FindBy(xpath = "//input[@id='codCharge']")
	private WebElement posCodChrg;
	@FindBy(xpath = "//input[@id='shippingCharge']")
	private WebElement posShipChrg;
	
	public double posGetCodChrg() {
		double codChrg=0.0;
		if(WebDriverUtil.isElementPresent(posCodChrg, driver, 2)) 
		{
			codChrg= Double.parseDouble(posCodChrg.getAttribute("value"));
		}
		return codChrg;
	}
	public double posGetShipChrg() {
		double shipChrg=0.0;
		if(WebDriverUtil.isElementPresent(posShipChrg, driver, 2)) 
		{
			shipChrg= Double.parseDouble(posShipChrg.getAttribute("value"));
		}
		return shipChrg;
	}
	
	/**
	 * Total Offer Price
	 */
	@FindBy(xpath = "//input[@name='posLineItems[0].total']")
	private WebElement posOffrPrice;
	private By totAmnt= By.xpath("//td[@class='totAmnt']");
	
	public double posGetTotalOffrPrice() {
		WebElement posOffrPrice;
		double TotalOfferPrice=0.0;
		int count = driver.findElements(totAmnt).size() -1;
		while(count >= 0) {
			posOffrPrice = driver.findElement(By.xpath("//input[@name='posLineItems["+count+"].total']"));
			TotalOfferPrice = TotalOfferPrice + Double.parseDouble(posOffrPrice.getAttribute("value"));
			count --;
		}		
		
		return TotalOfferPrice;
	}
	
	/**
	 * Apply HK-Cash
	 */
	@FindBy(xpath = "//input[@id='hkCashUsed']")
	private WebElement posApplyHkCash;
	
	public void posApplyHkCash() {
		WebDriverUtil.common.click(posApplyHkCash);
	}
	
	/**
	 * Verify HK-Cash Applied
	 */
	@FindBy(xpath = "//input[@id='discount']")
	private WebElement posDiscount;
	
	private double appliedDiscount;
	
	public double posGetDiscountApplied() {
		appliedDiscount = Double.parseDouble(posDiscount.getAttribute("value"));
		return appliedDiscount;
	}
	
	/**
	 * make payment
	 */
	@FindBy(xpath = "//label[@id='paymentLabelID']")
	private WebElement posPymntLabel;
	@FindBy(xpath = "//select[@id='paymentMode']")
	private WebElement posCounterCashPymnt;	
	@FindBy(xpath = "//input[@id='receivePayment']")
	private WebElement posReceivePayment;
	@FindBy(xpath = "//input[@id='receivePayment1']")
	private WebElement posReceiveSplitPayment;
	
	@FindBy(xpath = "//input[@id='addID']")
	private WebElement posAddPaymentMode;
	@FindBy(xpath = "//select[@id='select-0']")
	private WebElement posSplitPaymentModeCounterCash;
	@FindBy(xpath = "//select[@id='select-1']")
	private WebElement posSplitPaymentModeCard;
	@FindBy(xpath = "//input[@id='paymentReferenceNo_split']")
	private WebElement posPaymentReferenceNo;
	@FindBy(xpath = "//input[@id='cardNo_split']")
	private WebElement posCardNo;
	@FindBy(xpath = "//input[@id='bankDetails_split']")
	private WebElement posBankName;
	@FindBy(xpath = "//input[@name='paymentDTOList[1].amount']")
	private WebElement posCardPaymentAmnt;
	@FindBy(xpath = "//input[@name='paymentDTOList[0].amount']")
	private WebElement posCounterCashAmnt;
	
	public boolean isPaymentOptionAvailable()
	{
		boolean flag = false;
		Select pymntMode = new Select(posCounterCashPymnt);
		if(pymntMode.getOptions().size()==4)
			flag = true;
		return flag;
	}
	
	public boolean isPaymentOptionAvailableForOrder()
	{
		return WebDriverUtil.common.isElementPresent(driver, posCounterCashPymnt);
		
	}
	
	public void makePaymentForOrder(String pymntType) {
		WebDriverUtil.common.staticWait(2);
		WebDriverUtil.common.scrollDownTillEnd(driver);
		WebDriverUtil.common.staticWait(1);
		
		if("Counter Cash".equalsIgnoreCase(pymntType)) {
			if(WebDriverUtil.isElementPresent(posCounterCashPymnt, driver,20)) {
				Select pymntMode = new Select(posCounterCashPymnt);
				pymntMode.selectByVisibleText(pymntType);
			}
			WebDriverUtil.common.staticWait(2);
			WebDriverUtil.common.scrollDownTillEnd(driver);
			WebDriverUtil.common.staticWait(1);
			WebDriverUtil.common.click(posReceivePayment);
		}
		
		if("Offline Credit/Debit Card".equalsIgnoreCase(pymntType)) {
			if(WebDriverUtil.isElementPresent(posCounterCashPymnt, driver,20)) {
				Select pymntMode = new Select(posCounterCashPymnt);
				pymntMode.selectByVisibleText(pymntType);
			}
			WebDriverUtil.common.staticWait(2);
			WebDriverUtil.common.scrollDownTillEnd(driver);
			WebDriverUtil.common.staticWait(1);
			WebDriverUtil.common.click(posReceivePayment);
		}
		
		if("Offline Paytm".equalsIgnoreCase(pymntType)) {
			if(WebDriverUtil.isElementPresent(posCounterCashPymnt, driver,20)) {
				Select pymntMode = new Select(posCounterCashPymnt);
				pymntMode.selectByVisibleText(pymntType);
			}
			WebDriverUtil.common.staticWait(2);
			WebDriverUtil.common.scrollDownTillEnd(driver);
			WebDriverUtil.common.staticWait(1);
			WebDriverUtil.common.click(posReceivePayment);
		}
		
		if("Split Payment".equalsIgnoreCase(pymntType)) {
			if(WebDriverUtil.isElementPresent(posCounterCashPymnt, driver,20)) {
				Select pymntMode = new Select(posCounterCashPymnt);
				pymntMode.selectByVisibleText(pymntType);
			}			
			if(WebDriverUtil.isElementPresent(posSplitPaymentModeCounterCash, driver,20)) {
				Select pymntMode = new Select(posSplitPaymentModeCounterCash);
				pymntMode.selectByVisibleText("Counter Cash");
				WebDriverUtil.common.staticWait(2);
				posCounterCashAmnt.sendKeys("1000");				
			}
			WebDriverUtil.common.click(posPymntLabel); // to focus out and enable calculations..
			WebDriverUtil.common.staticWait(2);
			WebDriverUtil.common.scrollToObjectWithMargin(driver, posAddPaymentMode, 50);
			WebDriverUtil.common.click(posAddPaymentMode);			
			if(WebDriverUtil.isElementPresent(posSplitPaymentModeCard, driver,20)) {
				Select pymntMode = new Select(posSplitPaymentModeCard);
				pymntMode.selectByVisibleText("Offline Credit Debit");
				WebDriverUtil.common.staticWait(2);
				posPaymentReferenceNo.sendKeys("12345");
				posCardNo.sendKeys("3456");
				posBankName.sendKeys("HDFC");
				posCardPaymentAmnt.sendKeys(Double.toString(Double.parseDouble(posOrderAmntVal.getAttribute("value"))-1000));
			}
			WebDriverUtil.common.click(posPymntLabel); // to focus out and enable calculations..
			WebDriverUtil.common.staticWait(2);
			WebDriverUtil.common.scrollDownTillEnd(driver);
			WebDriverUtil.common.staticWait(1);
			WebDriverUtil.common.click(posReceiveSplitPayment);
		}
		
		if("Prepaid".equalsIgnoreCase(pymntType)) {
			WebDriverUtil.common.scrollDownTillEnd(driver);
			WebDriverUtil.common.staticWait(1);
			WebDriverUtil.common.click(posReceivePayment);
		}
		
	}
	
	/**
	 * verify payment success
	 */
	@FindBy(xpath = "//li[contains(text(),'Order processed successfully') or contains(text(),'Order shipped successfully')]")	
	private WebElement posOrderSuccessText;
	
	public boolean verifyPaymentSuccess(){
		WebDriverUtil.common.staticWait(3);
		boolean flag = false;
		if(WebDriverUtil.common.isElementPresent(driver, posOrderSuccessText)) {
			flag=true;
		}
		return flag;
	}
	
	/**
	 * get gateway order id
	 */	
	@FindBy(xpath = "//div[@class='gateway-id']/strong")
	private WebElement posGatewayOrderId;
	
	public String getGatewayOrderId() {
		return posGatewayOrderId.getText();
	}
	
	/**
	 * get base order id
	 */	
	@FindBy(xpath = "//div/b[text()='Order ID: ']/following-sibling::span[1]")
	private WebElement posBaseOrderId;
	
	public String getBaseOrderId() {
		return posBaseOrderId.getText();
	}
	
	/**
	 * Generate daily sales/return reports.
	 */

	@FindBy(xpath = "//li/a[text()='Store Manager']")
	private WebElement posStoreManager;
	@FindBy(xpath = "//h3/a[text()='Reports']")
	private WebElement posReports;
	@FindBy(xpath = "//li/input[@name='generateDailySalesReport']")
	private WebElement posDailySalesReports;
	@FindBy(xpath = "//li/input[@name='generateDailyReturnReport']")
	private WebElement posDailyReturnReports;
	
	public void openPosStoreManager() {
		WebDriverUtil.click(driver, posStoreManager, "posStoreManager");
	}
	public void openPOSReports() {
		WebDriverUtil.click(driver, posReports, "posReports");
	}
	public void generateDailySaleReports() {
		WebDriverUtil.click(driver, posDailySalesReports, "posDailySalesReports");
	}
	public void generateDailyReturnReports() {
		WebDriverUtil.click(driver, posDailyReturnReports, "posDailyReturnReports");
	}
	
	/**
	 * Generate POS sales/return reports by date.
	 */
	@FindBy(xpath = "//legend[text()='Sales Reports']/ancestor::form[1]//input[@name='startDate']/following-sibling::a")
	private WebElement posSaleReportStartDateByDate;
	@FindBy(xpath = "//div[@class='calendar' and contains(@style,'block')]//td[@class='day selected today']/preceding-sibling::td[1]")
	private WebElement posSaleReportStartDateCalendarByDate;
	@FindBy(xpath = "//legend[text()='Sales Reports']/ancestor::form[1]//input[@name='endDate']/following-sibling::a")
	private WebElement posSaleReportEndDateByDate;	
	@FindBy(xpath = "//div[@class='calendar' and contains(@style,'block')]//td[@class='day selected today']")
	private WebElement posSaleReportEndDateCalendarByDate;
	
	@FindBy(xpath = "//legend[text()='Return Reports']/ancestor::form[1]//input[@name='startDate']/following-sibling::a")
	private WebElement posReturnReportStartDateByDate;
	@FindBy(xpath = "//div[@class='calendar' and contains(@style,'block')]//td[@class='day selected today']/preceding-sibling::td[1]")
	private WebElement posReturnReportStartDateCalendarByDate;
	@FindBy(xpath = "//legend[text()='Return Reports']/ancestor::form[1]//input[@name='endDate']/following-sibling::a")
	private WebElement posReturnReportEndDateByDate;	
	@FindBy(xpath = "//div[@class='calendar' and contains(@style,'block')]//td[@class='day selected today']")
	private WebElement posReturnReportEndDateCalendarByDate;
	
	
	@FindBy(xpath = "//input[@name='generateSalesReportByDate']")
	private WebElement posDailySalesReportsByDate;
	@FindBy(xpath = "//input[@name='generateReturnReportByDate']")
	private WebElement posDailyReturnReportsByDate;
	
	public void generateDailySaleReportsByDate() {
		WebDriverUtil.click(driver, posSaleReportStartDateByDate, "posSaleReportStartDateByDate");
		WebDriverUtil.click(driver, posSaleReportStartDateCalendarByDate, "posSaleReportStartDateCalendarByDate");
		WebDriverUtil.click(driver, posSaleReportEndDateByDate, "posSaleReportEndDateByDate");
		WebDriverUtil.click(driver, posSaleReportEndDateCalendarByDate, "posSaleReportEndDateCalendarByDate");
		WebDriverUtil.click(driver, posDailySalesReportsByDate, "posDailySalesReportsByDate");
	}
	public void generateDailyReturnReportsBtDate() {
		WebDriverUtil.click(driver, posReturnReportStartDateByDate, "posReturnReportStartDateByDate");
		WebDriverUtil.click(driver, posReturnReportStartDateCalendarByDate, "posReturnReportStartDateCalendarByDate");
		WebDriverUtil.click(driver, posReturnReportEndDateByDate, "posReturnReportEndDateByDate");
		WebDriverUtil.click(driver, posReturnReportEndDateCalendarByDate, "posReturnReportEndDateCalendarByDate");
		WebDriverUtil.click(driver, posDailyReturnReportsByDate, "posDailyReturnReportsByDate");
	}
	
	/**
	 * verify POS order in report.
	 */
	
	public boolean verifyOrderPresentInReport(String saleOrReturn, String orderId) {
		boolean flag=false;
		WebDriverUtil.staticWait(1);
		WebDriverUtil.scrollDownTillEnd(driver);
		WebDriverUtil.staticWait(1);
		if("SALE".equalsIgnoreCase(saleOrReturn) || "SALE BY DATE".equalsIgnoreCase(saleOrReturn))
			flag= WebDriverUtil.isElementPresent(driver.findElement(By.xpath("//td/a[contains(text(),'"+orderId+"')]")), driver, 5);
		else if("RETURN".equalsIgnoreCase(saleOrReturn) || "RETURN BY DATE".equalsIgnoreCase(saleOrReturn))
			flag= WebDriverUtil.isElementPresent(driver.findElement(By.xpath("//td[contains(text(),'"+orderId+"')]")), driver, 5);
		return flag;			
	}
	
}
