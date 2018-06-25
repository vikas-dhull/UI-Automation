package com.healthkart.hkAutomation.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.pages.HK_BrandsPage_p;

public class HK_BrandsPage_b extends HK_BrandsPage_p{

	public HK_BrandsPage_b(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * go to PDP page for first variant.
	 */
	public void visitPDPPageForFirstVariant() {
		System.out.println("About to click first PV link..");
		scroolWindowToPvView();
		clickfirstPVLink();
	}

	/**
	 * verify sort by Price Low To High.
	 */
	public boolean verifySortByPriceLH() {
		
		boolean assertFlag = true;
		
		List<Float> variantPriceSorted = new ArrayList<Float>();
        List<Float> variantPrice = new ArrayList<Float>();
        
        
        for (WebElement element : getVariantTilesListForPrice()) {
        	System.out.println(Float.parseFloat(element.getText().substring(4).replaceAll(",","")));
        	variantPriceSorted.add(Float.parseFloat(element.getText().substring(4).replaceAll(",","")));
        	variantPrice.add(Float.parseFloat(element.getText().substring(4).replaceAll(",","")));        	
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
        System.out.println("verify sort flag price low to high : " + assertFlag);
        return assertFlag;        
	}
}
