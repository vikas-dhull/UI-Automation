package com.healthkart.hkRetailAutomation.business;

import org.openqa.selenium.WebDriver;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.healthkart.hkRetailAutomation.pages.HK_Retail_Omni_Chnl_Orders_Page;

public class HK_Retail_Omni_Chnl_Orders_Steps extends HK_Retail_Omni_Chnl_Orders_Page {
	
	public HK_Retail_Omni_Chnl_Orders_Steps(WebDriver driver) {
		super(driver);
	}
	
	public boolean verifyCodShippingCharges(String pymntMode) 
	{
		double codCahrge = getCodCharges();
		double shippingCharge = getShippingCharges();
		if("COD".equalsIgnoreCase(pymntMode) && codCahrge > 0.0 && shippingCharge > 0.0)
			return true;
		else if("PREPAID".equalsIgnoreCase(pymntMode) && shippingCharge > 0.0)
			return true;
		else
			return false;
	}
	
	public boolean acceptOrderAndMarkForStorePickup() 
	{
		boolean flag=false;
		String pymntMode = getPymntModeForOrder();
		acceptOmniChnlOrder();
		if(verifyCodShippingCharges(pymntMode))
		{
			markOmniChnlOrderForStorePickup();
			if(verifySMActionAndStatus("STORE_PICKUP"))
			{
				if("COD".equalsIgnoreCase(pymntMode)) {
					if(!verifyCodShippingCharges(pymntMode)) {
						flag= true;
					}
					else {
						flag= false;
						comnFunc.reportLogAndPrintInConsole("COD OR Shipping Charges still exists after marking order as store pickup..");
					}
				}
				if("PREPAID".equalsIgnoreCase(pymntMode)) {
					flag= true;
					comnFunc.reportLogAndPrintInConsole("COD OR Shipping Charges doesn't matter if order is prepaid..");
				}
				
			}
			else
			{
				comnFunc.reportLogAndPrintInConsole("Order status not found as : Customer pick-up");
			}
		}
		else
		{
			comnFunc.reportLogAndPrintInConsole("COD OR Shipping Charges are found as ZERO");
			flag=false;		
		}
		return flag;
	}
	
	public boolean acceptOrderAndMarkForDeliverBySM() 
	{
		boolean flag=false;
		String pymntMode = getPymntModeForOrder();
		acceptOmniChnlOrder();
		if (verifyCodShippingCharges(pymntMode)) 
		{
			markOmniChnlOrderForDeliveryBySM();
			if(verifySMActionAndStatus("DELIVER_SM"))
			{
				if("COD".equalsIgnoreCase(pymntMode)) {
					if(verifyCodShippingCharges(pymntMode)) {
						flag= true;
					}
					else {
						flag= false;
						comnFunc.reportLogAndPrintInConsole("COD OR Shipping Charges still exists after marking order as store pickup..");
					}
				}
				if("PREPAID".equalsIgnoreCase(pymntMode)) {
					flag= true;
					comnFunc.reportLogAndPrintInConsole("COD OR Shipping Charges doesn't matter if order is prepaid..");
				}
			}
			else
			{
				comnFunc.reportLogAndPrintInConsole("Order status not found as : Deliver By SM");
			}
		}
		else
		{
			comnFunc.reportLogAndPrintInConsole("COD OR Shipping Charges are found as ZERO");
			flag=false;
		}
		return flag;
	}
	
	public boolean acceptOrderAndMarkForProcessBackToHK() 
	{
		boolean flag=false;
		acceptOmniChnlOrder();
		markOmniChnlOrderForProcessBackToHK();
		if(verifySMActionAndStatus("PROCESS_BACKTOHK"))
		{
			flag=true;
		}
		else
		{
			comnFunc.reportLogAndPrintInConsole("Order status not found as : Accepted then Rejected");
		}
		return flag;
	}
	
	public boolean rejectOrder() 
	{
		boolean flag=false;
		rejectOmniChnlOrder();
		
		if(verifySMActionAndStatus("REJECTED"))
		{
			flag=true;
		}
		else
		{
			comnFunc.reportLogAndPrintInConsole("Order status not found as : Rejected");
		}
		return flag;
	}
	
	public boolean acceptOrder() 
	{
		boolean flag=false;
		acceptOmniChnlOrder();
		
		if(verifySMActionAndStatus("ACCEPTED"))
		{
			flag=true;
		}
		else
		{
			comnFunc.reportLogAndPrintInConsole("Order status not found as : Accepted");
		}
		return flag;
	}
	
	public void checkoutOmniChnlOrder() 
	{
		comnFunc.staticWait(3);
		checkoutOrder();	
		comnFunc.staticWait(3);
		if(WebDriverUtil.waitForWindowCount(driver, 2)) {
			
			for (String allHandles : driver.getWindowHandles()) {
				driver.switchTo().window(allHandles);
				System.out.println("current window title :" + driver.getTitle());
				if(driver.getCurrentUrl().contains("POS.action?")) {
					driver.switchTo().window(allHandles);
					System.out.println("Switched to POS page window..");
					break;
				}
			}
		}
	}

	public boolean verifyOmniChnlOrderPageLoaded() 
	{
		return verifyOmniChnlOrderPage();
	}

	public boolean verifyOrderItemsAvailableAtStore(long cstmrOrdrId, String store) 
	{
		boolean flag = false;
		String NutIds = dbAction.getOmniChnlCustomerOrderItems(cstmrOrdrId);
		for(String pvId:NutIds.trim().split(",")) 
		{
			if(dbAction.getBarcodeForPOS(pvId, store) != null) {
				flag=true;
			}
			else {
				comnFunc.reportLogAndPrintInConsole("PV Id inventory not available on POS "+pvId);
			}
		}
		return flag;		
	}

	public long getCustomerOrderId() 
	{
		return getCstmrOrdrId();
	}

	/*public List<OrderDataItems> getOmniChnlOrderCancelParams() {
		return(retrieveOrderDataItems());
	}*/

}
