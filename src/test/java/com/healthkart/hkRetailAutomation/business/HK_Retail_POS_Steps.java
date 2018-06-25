package com.healthkart.hkRetailAutomation.business;

import java.util.List;
import org.openqa.selenium.WebDriver;
import com.healthkart.hkAutomation.util.OrderDataItems;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.healthkart.hkRetailAutomation.pages.HK_Retail_POS_Page;

public class HK_Retail_POS_Steps extends HK_Retail_POS_Page {
	
	public HK_Retail_POS_Steps(WebDriver driver) {
		super(driver);
	}

	/**
	 * Verify POS screen ready to take order.
	 * @return
	 */
	public boolean verifyScreenPOS() {
		return verifyPOSScreen();
	}
	
	/**
	 * Enter customer details on POS.
	 * @param mob
	 */
	public void inputCustomerDetails(String mob) {
		enterCustomerMobileNo(mob);
		enterCustomerName();
		enterCustomerGym();
		enterCustomerAddDetails();
	}
	
	/**
	 * Scan barcode on POS for NUT-ID
	 * @param variantNUTId
	 * @param store
	 * @return
	 */
	public boolean inputScanBarcode(String variantNUTId, String store) {
		enterBarcode(variantNUTId, store);
		return verifyItemAddedToCart();
	}
	
	/**
	 * Scan barcode on POS for OmniChnl Order
	 * @param variantNUTId
	 * @param store
	 * @return
	 */
	public boolean inputScanBarcode(long CstmrOrderId, String store) {
		enterBarcode(WebDriverUtil.dbActionsObj.getOmniChnlCustomerOrderItems(CstmrOrderId), store);
		return verifyItemAddedToCart();
	}
	
	/**
	 * Apply Prompt Offer on POS and verify discounts.
	 * @return
	 */
	public boolean performApplyAndVerifyPromtOffer() {
		boolean flag = false;
		double discountBefore = posGetDiscountApplied();
		applyPromptOffer();
		double discountAfter = posGetDiscountApplied();
		double freebieOfferPrice = posGetFreebieOfferPrice();
		
		if(verifyOfferApplied()){
			if(freebieOfferPrice==discountAfter-discountBefore)
				flag=true;
			else
				WebDriverUtil.common.reportLogAndPrintInConsole("There are discount calculation mismatch after applying Coupon/Offer..!!");
		}
		else {
			WebDriverUtil.common.reportLogAndPrintInConsole("There are Issues while applying Coupon/Offer..!!");
		}
		return flag;
	}
	
	/**
	 * Apply coupon on POS and verify discounts.
	 * @param cpnCode
	 * @return
	 */
	public boolean performApplyAndVerifyCouponOffer(String cpnCode) {
		boolean flag = false;
		double discountBefore = posGetDiscountApplied();
		applyOfferWithCouponCode(cpnCode);
		double discountAfter = posGetDiscountApplied();
		double freebieOfferPrice = posGetFreebieOfferPrice();
		
		if(verifyOfferApplied()){
			if(freebieOfferPrice==discountAfter-discountBefore)
				flag=true;
			else
				WebDriverUtil.common.reportLogAndPrintInConsole("There are discount calculation mismatch after applying Coupon/Offer..!!");
		}
		else {
			WebDriverUtil.common.reportLogAndPrintInConsole("There are Issues while applying Coupon/Offer..!!");
		}
		return flag;
	}
	
	/**
	 * Apply freebie on POS and verify discounts.
	 * @return
	 */
	public boolean posAddAndVerifyFreebie() {
		boolean flag = false;
		double discountBefore = posGetDiscountApplied();
		addFreebie();
		double discountAfter = posGetDiscountApplied();
		double freebieOfferPrice = posGetFreebieOfferPrice();
		
		if(verifyAddFreebie()){
			if(freebieOfferPrice==discountAfter-discountBefore)
				flag=true;
			else
				WebDriverUtil.common.reportLogAndPrintInConsole("There are discount calculation mismatch after applying Freebie..!!");
		}
		else {
			WebDriverUtil.common.reportLogAndPrintInConsole("There are Issues while applying Freebie..!!");
		}
		return flag;
	}
	

	/**
	 * Make payment on POS.
	 * @param pymntType
	 * @return
	 */
	public boolean performPayments(String pymntType) {
		makePaymentForOrder(pymntType);		
		return verifyPaymentSuccess();
	}
	
	/**
	 * Apply and verify HK-CASH on user cart on POS.
	 * @param hkCashType
	 * @return
	 */
	public boolean applyHkCashForCust(String hkCashType) {
		boolean flag = false;
		if(posVerifyAvailableHKCash(hkCashType)) {
			double availableHKCash = posGetAvailableHKCash();
			double discountBefore = posGetDiscountApplied();
			posApplyHkCash();
			double discountAfter = posGetDiscountApplied();
			
			if(availableHKCash>=(discountAfter-discountBefore)) {
				WebDriverUtil.common.reportLogAndPrintInConsole("HK-Cash Available : "+ availableHKCash);
				WebDriverUtil.common.reportLogAndPrintInConsole("HK-Cash applied : "+ (discountAfter-discountBefore));
				flag=true;
			}
			else {
				WebDriverUtil.common.reportLogAndPrintInConsole("There are discount calculation mismatch after applying HK-Cash..!!");
			}
		}		
		return flag;
	}
	
	/**
	 * Verify Final Payment Amount
	 */
	public boolean verifyFinalPaymentOrderAmount()
	{
		boolean flag=false;
		
		double finalPyblAmnt=posGetOrderAmount();
		double codChrg=posGetCodChrg();
		double shipChrg=posGetShipChrg();
		double totalOffrPrice=posGetTotalOffrPrice();
		double expectedFinalAmnt = totalOffrPrice + codChrg + shipChrg;
		
		WebDriverUtil.common.reportLogAndPrintInConsole("Total Offer Price on POS : " + totalOffrPrice);
		WebDriverUtil.common.reportLogAndPrintInConsole("Cod Charge on POS : " + codChrg);
		WebDriverUtil.common.reportLogAndPrintInConsole("Shipping Charge on POS : " + shipChrg);		
		
		if((finalPyblAmnt == expectedFinalAmnt))
		{
			WebDriverUtil.common.reportLogAndPrintInConsole("Total Offer Price + Cod Chrg + Ship Chrg, on POS :: " + expectedFinalAmnt);
			WebDriverUtil.common.reportLogAndPrintInConsole("Final Payable Amount Verified Successfully on POS : " + finalPyblAmnt);
			flag=true;			
		}
		else
		{
			WebDriverUtil.common.reportLogAndPrintInConsole("Total Offer Price + Cod Chrg + Ship Chrg, on POS :: " + expectedFinalAmnt);
			WebDriverUtil.common.reportLogAndPrintInConsole("Final Payable Amount Verification is UnSuccessfull on POS : " + finalPyblAmnt);
			
		}
		
		return flag;
	}

	/**
	 * Delete users on POS/POS&API before Sign-up.
	 * @param mobileNumber
	 * @param storeId
	 * @param targetApp
	 */
	public void clearUserDataForRetailSignup(String mobileNumber, int storeId, String targetApp ) {
		if("POS".equalsIgnoreCase(targetApp)) {
			if(WebDriverUtil.dbActionsObj.verifyIfSignUpMobileExistsPOS(mobileNumber, storeId) != null)
				WebDriverUtil.dbActionsObj.deleteSignUpUserDataForRetail(mobileNumber, storeId);
			else 
				WebDriverUtil.common.reportLogAndPrintInConsole("User Id not found on API/POS to be deleted..!!");
		}
		else if("POS&API".equalsIgnoreCase(targetApp)){ 
			if(WebDriverUtil.dbActionsObj.verifyIfSignUpMobileExistsAPI(mobileNumber, storeId) != null 
				|| WebDriverUtil.dbActionsObj.verifyIfSignUpMobileExistsPOS(mobileNumber, storeId) != null ) {
				WebDriverUtil.dbActionsObj.deleteSignUpUserDataForRetail(mobileNumber, storeId);
				WebDriverUtil.dbActionsObj.deleteSignUpUserDataForAPI(mobileNumber, storeId);
			}
		}			
	}
	
	/**
	 * Verify If Mobile number used for signup, already exists for POS / POS&API
	 * @param mobileNumber
	 * @param storeId
	 * @param targetApp
	 * @return
	 */
	public boolean verifyIfSignupMobileExists(String mobileNumber, int storeId, String targetApp) {
		boolean flag=false;
		if("POS".equalsIgnoreCase(targetApp)) {
			if(WebDriverUtil.dbActionsObj.verifyIfSignUpMobileExistsPOS(mobileNumber, storeId) == null)
				flag=true;
			else 
				WebDriverUtil.common.reportLogAndPrintInConsole("User Id found on POS, expected condition for test fails..!!");
		}
		else if("POS&API".equalsIgnoreCase(targetApp)){ 
			if(WebDriverUtil.dbActionsObj.verifyIfSignUpMobileExistsAPI(mobileNumber, storeId) == null 
				&& WebDriverUtil.dbActionsObj.verifyIfSignUpMobileExistsPOS(mobileNumber, storeId) == null )
					flag=true;
			else
				WebDriverUtil.common.reportLogAndPrintInConsole("User Id found on POS or API, expected condition for test fails..!!");
		}
		return flag;
	}

	/**
	 * Verify new user created on POS is successfully synced on API
	 * @param mobileNumber
	 * @param storeId
	 * @return
	 */
	public boolean verifyNewUserSyncOnAPI(String mobileNumber, int storeId) {
		boolean flag = false;
		String userIdPOS = WebDriverUtil.dbActionsObj.verifyIfSignUpMobileExistsPOS(mobileNumber,storeId);
		String userIdAPI = WebDriverUtil.dbActionsObj.verifyIfSignUpMobileExistsAPI(mobileNumber,storeId);
		if(userIdPOS != null) {
			if(userIdAPI != null)
				flag=true;
			else
				WebDriverUtil.common.reportLogAndPrintInConsole("User Id not created on API..!!");
		}
		else {
			WebDriverUtil.common.reportLogAndPrintInConsole("User Id not created on POS..!!");
		}		
		return flag;
	}

	/**
	 * update and verify customer info updated
	 * @return
	 */
	public boolean updateCustomerInfoPOS() {
		boolean flag=false;
		clickCustomerInfoUpdate();
		if(handleNewUserPOSAlert(1)) 
			flag=true;
		return flag;
	}
	
	/**
	 * Verify POS Screen Elements
	 */
	public boolean verifyPOSScreenElements(String pymntType) 
	{
		boolean flag=false;
		if("COD".equalsIgnoreCase(pymntType) && !isCustomerInfoUpdateBtnPresent() && isPaymentOptionAvailable()) 
		{
			flag=true;
		}
		else if("PREPAID".equalsIgnoreCase(pymntType) && !isCustomerInfoUpdateBtnPresent() && !isPaymentOptionAvailableForOrder()) 
		{
			flag=true;
		}	
		
		return flag;
	}

	/**
	 * Validate from database - order Id, order amount, line items.
	 */
	public boolean performValidationForOrder(String orderId) {
		String gatewayOrderId = orderId;
		System.out.println("gateway order id placed : " + gatewayOrderId);
		boolean flag = false;
		WebDriverUtil.common.staticWait(5);
		
		List<OrderDataItems> orderDataItemsList = WebDriverUtil.dbActionsObj.verifyRetailOrderUsingGatewayID(gatewayOrderId);
		
		try {
			for(OrderDataItems orderDataItems : orderDataItemsList) {
				
				System.out.println("gateway order id : " + orderDataItems.getGatewayOrderId());
				System.out.println("order amount : " + orderDataItems.getAmount());
				System.out.println("order cart line item ids : " + orderDataItems.getCliIds());
				//System.out.println("opr id : " + orderDataItems.getOprId());
				//System.out.println("opr li id : " + orderDataItems.getOprLiId());
				
				if(orderDataItems.getGatewayOrderId() != null && orderDataItems.getAmount() != null && orderDataItems.getCliIds() != null) 
				{
					if(gatewayOrderId.equals(orderDataItems.getGatewayOrderId())) 
					{	
						flag = true;
						System.out.println("Gateway order ID : " + gatewayOrderId + " ; Order Amount : " 
											+ orderDataItems.getAmount() + " ; Cart line Item ids : " + orderDataItems.getCliIds());
					}
					else 
					{
						WebDriverUtil.common.reportLogAndPrintInConsole("Gateway order id from order success screen: " + gatewayOrderId +
								" did not match with DB Gateway order id : " + orderDataItems.getGatewayOrderId());
					}
				}
				else 
				{
					WebDriverUtil.common.reportLogAndPrintInConsole("One of the following order item found to be null :"
							+ "Gateway order ID : " + gatewayOrderId + " ; Order Amount : " 
							+ orderDataItems.getAmount() + " ; Cart line Item ids : " + orderDataItems.getCliIds());
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();			
		}		
		return flag;		
	}
	
	/**
	 * verify order visible in POS sales/return report.
	 */
	
	public boolean verifyPOSOrderInSalesReport(String saleOrReturn, String orderId) {
		boolean flag = false;
		int windowCount=0;
		openPosStoreManager();
		openPOSReports();
		String parentWindow = driver.getWindowHandle();
		
		if("SALE".equalsIgnoreCase(saleOrReturn)) {
			windowCount=2;
			generateDailySaleReports();
		}	
		else if("RETURN".equalsIgnoreCase(saleOrReturn)) {
			windowCount=3;
			generateDailyReturnReports();
		}
		else if("SALE BY DATE".equalsIgnoreCase(saleOrReturn)) {
			windowCount=2;
			generateDailySaleReportsByDate();
		}
		else if("RETURN BY DATE".equalsIgnoreCase(saleOrReturn)) {
			windowCount=2;
			generateDailyReturnReportsBtDate();
		}
		
		WebDriverUtil.staticWait(3);
		if(WebDriverUtil.waitForWindowCount(driver, windowCount)) {			
			for (String winHandle : driver.getWindowHandles()) {
				if(!winHandle.equals(parentWindow)) {
					driver.switchTo().window(winHandle);
					if(driver.getCurrentUrl().contains("POSReport.action")) {
						System.out.println("current window title :" + driver.getTitle());
						System.out.println("Switched to POS Report Window..");
						break;
					}
				}
			}
		}else {
			WebDriverUtil.reportLogAndPrintInConsole("Expected Window count not found..!! ");
		}
		
		if(verifyOrderPresentInReport(saleOrReturn,orderId))
			flag=true;
		
		return flag;
	}
	
}

