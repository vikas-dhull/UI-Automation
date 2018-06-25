package com.healthkart.hkMsiteAutomation.business;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkMsiteAutomation.pages.HK_PDP_Page_AndroidMsite;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

public class HK_PDP_Steps_AndroidMsite 
{	
	AndroidDriver<?> androidDriver;
	HK_PDP_Page_AndroidMsite pdpPage;
	CommonFunctions comnFunc;
	GenericDbActions dbActionsObj;
	
	public HK_PDP_Steps_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		pdpPage = new HK_PDP_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
		dbActionsObj = new GenericDbActions();
	}
	
	public boolean verifyPdpPageLoaded()
	{
		return pdpPage.verify_Pdp_Loaded();
	}

	public void addToCart() 
	{
		if(pdpPage.verify_Pdp_Loaded()) 
		{
			pdpPage.click_Buy_Now();
		}
		else
		{
			System.out.println("Buy Now button not present on PDP page..");
			GlobalVar.test.log(LogStatus.FAIL, "Buy Now button not present on PDP page..");
		}
	}
	
	public void buyNowExpressCheckout()
	{
		if(pdpPage.verify_Pdp_Loaded()) 
		{
			pdpPage.click_Buy_Now_ExpressCheckout();
		}
		else
		{
			System.out.println("Buy Now button not present on PDP page..");
			GlobalVar.test.log(LogStatus.FAIL, "Buy Now button not present on PDP page..");
		}
	}
	
	
	public void proceedToWriteAreviewPage()
	{
		pdpPage.click_Write_A_review();
	}

	public boolean verifyCompareWidgetPresent() 
	{
		return pdpPage.isVariantCompareEligible();
	}

	public void performCompareVariants() 
	{
		pdpPage.clickCompareBtn();
	}

	public String getCompareVariant() {
		return dbActionsObj.getInStockCompareVariant();
	}

	public void wishlistItem() 
	{
		pdpPage.clickWishlistImageButton();
	}
}
