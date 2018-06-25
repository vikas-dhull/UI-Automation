package com.healthkart.hkMsiteAutomation.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebElement;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkMsiteAutomation.pages.HK_Brands_Page_AndroidMsite;
import io.appium.java_client.android.AndroidDriver;

public class HK_Brands_Steps_AndroidMsite 
{
	AndroidDriver<?> androidDriver;
	HK_Brands_Page_AndroidMsite brndPage;
	CommonFunctions comnFunc;
	
	
	public HK_Brands_Steps_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		brndPage = new HK_Brands_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
	}
	
	public boolean verifyBrndPageLoaded()
	{
		return brndPage.verifyBrandsPage();
	}

	public void selectSortByPriceLH() 
	{
		brndPage.clickSort();
		brndPage.chooseSortByPriceLH();
	}
	
	public void selectBrandFilter()
	{
		brndPage.chooseFilterByBrand();
	}
	
	public void goToFirstVariantPage()
	{
		brndPage.chooseFirstVariant();
	}

	/**
	 * verify sort by Price Low To High.
	 */
	public boolean verifySortByPriceLH() 
	{		
		boolean assertFlag = true;
		
		List<Float> variantPriceSorted = new ArrayList<Float>();
        List<Float> variantPrice = new ArrayList<Float>();
        
        
        for (WebElement element : brndPage.getVariantTilesListForPrice()) {
        	float vrntPrice = Float.parseFloat(element.getText().replaceAll(",",""));
        	System.out.println(vrntPrice);
        	variantPriceSorted.add(vrntPrice);
        	variantPrice.add(vrntPrice);        	
        }
        
        Collections.sort(variantPriceSorted);
        
        for (int i=0;i<variantPriceSorted.size();i++) {
            System.out.println("Variant Price from Cat page: " + variantPrice.get(i)  +  "--Variant Price from sorted input: " + variantPriceSorted.get(i));
            if (!(variantPriceSorted.get(i).equals(variantPrice.get(i)))) {
            	System.out.println("Variant Price not sorted: " + i);
            	assertFlag = false;
                break;
            }
        }        
        return assertFlag;        
	}
}