package com.healthkart.hkRetailAutomation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.OrderDataItems;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_Retail_Omni_Chnl_Orders_Page {

	public WebDriver driver;
	public GenericDbActions dbAction;
	public CommonFunctions comnFunc;
	public String gatewayOrderId;
	
	private By acceptOrder;
	private By pymntMode;
	private By codCharges;
	private By shippingCharges;
	private By orderStatus;
	private By storePickUp;
	private By deliveryBySM;
	private By processBackToHK;
	private By rejectOrder;
	private By selectRejectReason;
	private By submitRejectOrder;
	private By submitProcessBackToHK;
	private By checkoutOrder;
	
	public HK_Retail_Omni_Chnl_Orders_Page(WebDriver driver) {	
		dbAction = new GenericDbActions();
		comnFunc = new CommonFunctions();
		gatewayOrderId = GlobalVar.OmniChnlOrderId;
		this.driver = driver;
		
		acceptOrder = By.xpath("//a[ contains(@href,'gatewayOrderId="+gatewayOrderId+"') and contains(text(),'Accept')]");
		pymntMode = By.xpath("//td[text()='"+gatewayOrderId+"']/following-sibling::td[6]");
		codCharges = By.xpath("//td[text()='"+gatewayOrderId+"']/following-sibling::td[5]");
		shippingCharges = By.xpath("//td[text()='"+gatewayOrderId+"']/following-sibling::td[4]");
		orderStatus = By.xpath("//td[text()='"+gatewayOrderId+"']/following-sibling::td[9]");
		storePickUp = By.xpath("//a[ contains(@href,'gatewayOrderId="+gatewayOrderId+"') and contains(text(),'Store pick-up')]");
		deliveryBySM = By.xpath("//a[ contains(@href,'gatewayOrderId="+gatewayOrderId+"') and contains(text(),'Delivery by SM')]");
		processBackToHK = By.xpath("//a[ contains(@href,'gatewayOrderId="+gatewayOrderId+"')]/following-sibling::a[@class='rejectClick']");
		rejectOrder = By.xpath("//a[ contains(@href,'gatewayOrderId="+gatewayOrderId+"')]/following-sibling::a[@class='rejectClick']");
		selectRejectReason = By.xpath("//input[@value='"+gatewayOrderId+"']/parent::div//select[@name='reason']");
		submitRejectOrder = By.xpath("//input[@value='"+gatewayOrderId+"']/ancestor::div[2]//input[@id='rejectOrder-submit']");
		submitProcessBackToHK = By.xpath("//input[@value='"+gatewayOrderId+"']/ancestor::div[2]//input[@id='processOrderToHk-submit']");
		checkoutOrder = By.xpath("//a[ contains(@href,'gatewayOrderId="+gatewayOrderId+"') and contains(text(),'Checkout')]");
		
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Get Payment mode for order
	 */
	
	public String getPymntModeForOrder()
	{
		return driver.findElement(pymntMode).getText();
	}
	
	/**
	 * Omni-Channel Orders Actions
	 */	
	public void acceptOmniChnlOrder()
	{
		
		WebDriverUtil.click(driver, driver.findElement(acceptOrder), "acceptOrder");
		WebDriverUtil.staticWait(1);
		comnFunc.reportLogAndPrintInConsole("Order status : " + driver.findElement(orderStatus).getText().trim());
		
	}
	
	public double getCodCharges()
	{
		return Double.parseDouble(driver.findElement(codCharges).getText());
	}
	
	public double getShippingCharges()
	{
		return Double.parseDouble(driver.findElement(shippingCharges).getText());
	}
	
	public void markOmniChnlOrderForStorePickup() 
	{
		if(driver.findElement(orderStatus).getText().trim().contains("Accepted")) 
		{
			WebDriverUtil.click(driver, driver.findElement(storePickUp), "storePickUp");
			WebDriverUtil.staticWait(1);
			comnFunc.reportLogAndPrintInConsole("Order status : " + driver.findElement(orderStatus).getText().trim());
		}
		else
		{
			comnFunc.reportLogAndPrintInConsole("Order status not found as :: Accepted");
		}
	}
	
	public void markOmniChnlOrderForDeliveryBySM() 
	{
		if(driver.findElement(orderStatus).getText().trim().contains("Accepted")) 
		{
			WebDriverUtil.click(driver, driver.findElement(deliveryBySM), "deliveryBySM");
			WebDriverUtil.staticWait(1);
			comnFunc.reportLogAndPrintInConsole("Order status : " + driver.findElement(orderStatus).getText().trim());
		}
		else
		{
			comnFunc.reportLogAndPrintInConsole("Order status not found as :: Accepted");
		}
	}
	
	public void markOmniChnlOrderForProcessBackToHK() 
	{
		if(driver.findElement(orderStatus).getText().trim().contains("Accepted")) 
		{
			WebDriverUtil.click(driver, driver.findElement(processBackToHK), "processBackToHK");
			WebDriverUtil.staticWait(1);
			Select rejectReason = new Select(driver.findElement(selectRejectReason));
			rejectReason.selectByVisibleText("Single staff available at store, delivery not possible");
			WebDriverUtil.staticWait(1);
			WebDriverUtil.click(driver, driver.findElement(submitProcessBackToHK), "submitProcessBackToHK");
			WebDriverUtil.staticWait(1);
			comnFunc.reportLogAndPrintInConsole("Order status : " + driver.findElement(orderStatus).getText().trim());
		}
		else
		{
			comnFunc.reportLogAndPrintInConsole("Order status not found as :: Accepted");
		}
	}
	
	public boolean verifySMActionAndStatus(String smAction)
	{
		boolean flag=false;
		
		if("STORE_PICKUP".equalsIgnoreCase(smAction) && driver.findElement(orderStatus).getText().trim().contains("Customer pick-up"))
			flag=true;
		if("DELIVER_SM".equalsIgnoreCase(smAction) && driver.findElement(orderStatus).getText().trim().contains("Deliver By SM"))
			flag=true;
		if("PROCESS_BACKTOHK".equalsIgnoreCase(smAction) && driver.findElement(orderStatus).getText().trim().contains("Accepted then Rejected"))
			flag=true;
		if("REJECTED".equalsIgnoreCase(smAction) && driver.findElement(orderStatus).getText().trim().contains("Rejected"))
			flag=true;
		if("ACCEPTED".equalsIgnoreCase(smAction) && driver.findElement(orderStatus).getText().trim().contains("Accepted"))
			flag=true;
		if("CANCELLED_BY_USER".equalsIgnoreCase(smAction) && driver.findElement(orderStatus).getText().trim().contains("Cancelled By User"))
			flag=true;
		
		return flag;		
	}
	
	public void rejectOmniChnlOrder()
	{
		WebDriverUtil.click(driver, driver.findElement(rejectOrder), "rejectOrder");
		WebDriverUtil.staticWait(1);
		Select rejectReason = new Select(driver.findElement(selectRejectReason));
		rejectReason.selectByVisibleText("Single staff available at store, delivery not possible");
		WebDriverUtil.staticWait(1);
		WebDriverUtil.click(driver, driver.findElement(submitRejectOrder), "submitRejectOrder");
		WebDriverUtil.staticWait(1);
		comnFunc.reportLogAndPrintInConsole("Order status : " + driver.findElement(orderStatus).getText().trim());		
	}
	
	public void checkoutOrder() 
	{
		WebDriverUtil.click(driver, driver.findElement(checkoutOrder), "checkoutOrder");
		WebDriverUtil.staticWait(1);
	}
	
	@FindBy(xpath = "//h1[text()='HK Order Details']")
	private WebElement omniChnlOrderPage;
	
	public boolean verifyOmniChnlOrderPage()
	{
		return(WebDriverUtil.isElementPresent(omniChnlOrderPage, driver, 10));
	}
	
	public long getCstmrOrdrId()
	{
		return(dbAction.getOmniChnlCustomerOrder(gatewayOrderId));
	}
	
	public List<OrderDataItems> retrieveOrderDataItems(){
		return(dbAction.verifyOrderUsingGatewayID(gatewayOrderId));
	}
}
