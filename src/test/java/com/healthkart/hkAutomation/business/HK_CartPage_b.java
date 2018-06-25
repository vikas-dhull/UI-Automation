package com.healthkart.hkAutomation.business;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import com.healthkart.hkAutomation.pages.HK_CartPage_p;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_CartPage_b extends HK_CartPage_p {

	public HK_CartPage_b(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * make cart empty.
	 */
	public void makeCartEmpty() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int counter = 0;
		while(!backToShoppingBtnPresent() && counter!=5) {
			counter++;
//			if(verifyCartPageLoaded())
			staticWait(2);
			if(GlobalVar.jenkinsEnvironment==null)        // Handle notify visitor overlay [For PROD ENV only.]
			{
				if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
					WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);			
				}
			}
			else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
			{
				WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);
			}
				clickRemoveFirstItem();
				
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	/**
	 * Apply Coupon Offer through coupon code and verify coupon applied successfully.
	 */
	public boolean applyCouponCodeAndVerify(String coupon) {
		boolean flag = false;
		setCouponCode(coupon);
		clickApplyCouponCode();
		if(verifyCouponCodeApplied()) {
			if(verifyPromoDiscountOnCart())
				flag=true;
		}
		return flag;		
	}
	
	/**
	 * remove Coupon and verify offer removed successfully.
	 */
	public boolean removeCouponCodeAndVerify() {
		boolean flag = false;
		clickRemoveCouponCode();
			if(!verifyPromoDiscountOnCart())
				flag=true;
		return flag;		
	}
	
	/**
	 * Apply Prompt Offer and verify offer applied successfully.
	 */
	public boolean applyPromptOfferAndVerify() {
		boolean flag = false;
		clickApplyPromptOffer();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(verifyPromptOfferApplied()) {
			if(verifyPromoDiscountOnCart())
				flag=true;
		}
		System.out.println("prompt offer applied flag : " + flag);
		return flag;		
	}
	
	/**
	 * remove Prompt Offer and verify offer removed successfully.
	 */
	public boolean removePromptOffer() {
		boolean flag = false;
		clickRemovePromptOffer();
			if(!verifyPromoDiscountOnCart())
				flag=true;
		return flag;		
	}
	
	/**
	 * Apply HK Cash on cart.
	 */
	public boolean performApplyHKCash() {
		boolean flag = false;
		clickHKCashRedeem();
		if(verifyHKCashRedeemSuccess())
			flag = true;
		return flag;
	}
	
	/**
	 * Remove HK Cash on cart.
	 */
	public boolean performRemoveHKCash() {
		boolean flag = false;
		clickRemoveHKCashRedeemed();
		if(verifyHKCashRemovedSuccess())
			flag = true;
		return flag;
	}

	public void removeBxGyOfferIfApplied() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		if(verifyBxGyOfferApplied()) {
			clickRemoveBxGyOffer();
			if(!verifyBxGyOfferApplied()) 
				WebDriverUtil.reportLogAndPrintInConsole("BxGY offer found Applied on Cart and removed successfully..");
		}
		else {
			WebDriverUtil.reportLogAndPrintInConsole("BxGY offer not found Applied on Cart..");
		}
		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
	}

	public void moveToWishlist() 
	{
		clickMoveToWishlist();
	}

}
