package com.healthkart.hkMsiteAutomation.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.healthkart.hkMsiteAutomation.pages.HK_Category_Page_AndroidMsite;
import io.appium.java_client.android.AndroidDriver;

public class HK_Category_Steps_AndroidMsite 
{
	AndroidDriver<?> androidDriver;
	HK_Category_Page_AndroidMsite catPage;
	CommonFunctions comnFunc;
	
	
	public HK_Category_Steps_AndroidMsite(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		catPage = new HK_Category_Page_AndroidMsite(androidDriver);
		comnFunc = new CommonFunctions();
	}
	
	public boolean verifyCatPageLoaded()
	{
		return catPage.verifyCategoryPage();
	}

	public void selectSortByPriceLH() 
	{
		catPage.clickSort();
		catPage.chooseSortByPriceLH();
	}
	
	public void selectBrandFilter()
	{
		catPage.chooseFilterByBrand();
	}
	
	public void goToFirstVariantPage()
	{
		catPage.chooseFirstVariant();
	}

	/**
	 * verify sort by Price Low To High.
	 */
	public boolean verifySortByPriceLH() 
	{		
		boolean assertFlag = true;
		
		List<Float> variantPriceSorted = new ArrayList<Float>();
        List<Float> variantPrice = new ArrayList<Float>();
        
        
        for (WebElement element : catPage.getVariantTilesListForPrice()) {
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

	public boolean verifyLoadMoreOnCatPage() {
		boolean assertFlag = false;
		int vrntTileCntBeforeLoadMore = catPage.getVariantTilesCount();		
		catPage.scrollTillLoadMoreBtn();
		WebDriverUtil.staticWait(5);		
		catPage.clickLoadMore();
		WebDriverUtil.staticWait(5);
		int vrntTileCntAfterLoadMore = catPage.getVariantTilesCount();	
		if(vrntTileCntAfterLoadMore > vrntTileCntBeforeLoadMore) {
			assertFlag = true;
		}		
		return assertFlag;
	}
}
