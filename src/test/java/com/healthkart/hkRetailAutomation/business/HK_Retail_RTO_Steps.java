package com.healthkart.hkRetailAutomation.business;

import org.openqa.selenium.WebDriver;
import com.healthkart.hkRetailAutomation.pages.HK_Retail_RTO;

public class HK_Retail_RTO_Steps extends HK_Retail_RTO{

	public HK_Retail_RTO_Steps(WebDriver driver) {
		super(driver);
	}
	
	public String createReverseOrderForShippingOrder() {
		String RPID = null;
		clickCheckForReturn();
		selectReasonForReturn();
		selectCSActionOnReturn();
		enterCmntForReturn();
		selectBookingTypeForReturn();
		selectHKMngdCurForReturn();
		selectCurNameForReturn();
		selectReturnPickupTime();
		posSaveReturn();
		RPID = getReverseOrderId();
		posOpenWarehouseTab();
		return RPID;
	}
	
}
