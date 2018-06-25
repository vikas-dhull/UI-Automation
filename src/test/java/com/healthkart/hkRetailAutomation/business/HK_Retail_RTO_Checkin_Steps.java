package com.healthkart.hkRetailAutomation.business;

import org.openqa.selenium.WebDriver;

import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.healthkart.hkRetailAutomation.pages.HK_Retail_RTO_Checkin;

public class HK_Retail_RTO_Checkin_Steps extends HK_Retail_RTO_Checkin {

	public HK_Retail_RTO_Checkin_Steps(WebDriver driver) {
		super(driver);
	}
	
	public void performReturnCheckin(String barcode, String rpId) {
		clickReturnWHCheckin();
		inptReversePickupId(rpId);
		clickSearchRPID();
		inptReturnBarcode(barcode);
		selectWHCndtnForReturn();
		selectWHCmntForReturn();
		inptWHRmrkForReturn();
		clickSaveReturnWHCheckin();
		clickCloseReturnWHCheckin();
	}
	
	public boolean performDBValidationForBarcodeCheckin(String barcode) {
		boolean flag = false;
		if(WebDriverUtil.dbActionsObj.validatePOSBarcodeReturnCheckin(barcode)==10)
			flag=true;
		return flag;
	}

}
