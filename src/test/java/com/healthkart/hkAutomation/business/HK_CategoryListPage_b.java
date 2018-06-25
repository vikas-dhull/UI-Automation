package com.healthkart.hkAutomation.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.pages.HK_CategoryListPage_p;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_CategoryListPage_b extends HK_CategoryListPage_p{

	public HK_CategoryListPage_b(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Handle cat page halter text - "Select filters to make your search easy and quick"
	 */
	public void handleCatPageHltr() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if(verifyHltrPresent()) {
			clickCheckboxBrandFilter();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	/**
	 * go to PDP page for first variant.
	 */
	public void visitPDPPageForFirstVariant() {
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
        return assertFlag;        
	}
	
	/**
	 * verify sort by Price High to Low.
	 */
	public boolean verifySortByPriceHL() {
		
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
            if (!(variantPriceSorted.get(i).equals(variantPrice.get(variantPrice.size()-(i+1))))) {
            	System.out.println("Variant Price not sorted: " + i);
            	assertFlag = false;
                break;
            }
        }        
        return assertFlag;        
	}
	
	/**
	 * verify sort by Discount Low To High.
	 */
	public boolean verifySortByDiscountLH() {
		
		boolean assertFlag = true;
		
		List<Float> variantDiscountSorted = new ArrayList<Float>();
        List<Float> variantDiscount = new ArrayList<Float>();
        
        
        for (WebElement element : getVariantTilesListForDiscount()) {
        	System.out.println(Float.parseFloat(element.getText()));
        	variantDiscountSorted.add(Float.parseFloat(element.getText().substring(0,1)));
        	variantDiscount.add(Float.parseFloat(element.getText().substring(0,1)));        	
        }
        
        Collections.sort(variantDiscountSorted);
        
        for (int i=0;i<variantDiscountSorted.size();i++) {
            System.out.println("Variant Price from Cat page: " + variantDiscount.get(i)  +  "--Variant Price from sorted input: " + variantDiscountSorted.get(i));
            if (!(variantDiscountSorted.get(i).equals(variantDiscount.get(i)))) {
            	System.out.println("Variant Price not sorted: " + i);
            	assertFlag = false;
                break;
            }
        }        
        return assertFlag;        
	}
	
	/**
	 * verify sort by Discount High to Low.
	 */
public boolean verifySortByDiscountHL() {
		
		boolean assertFlag = true;
		
		List<Float> variantDiscountSorted = new ArrayList<Float>();
        List<Float> variantDiscount = new ArrayList<Float>();
        
        
        for (WebElement element : getVariantTilesListForDiscount()) {
        	System.out.println(Float.parseFloat(element.getText()));
        	variantDiscountSorted.add(Float.parseFloat(element.getText().substring(0,1)));
        	variantDiscount.add(Float.parseFloat(element.getText().substring(0,1)));        	
        }
        
        Collections.sort(variantDiscountSorted);
        
        for (int i=0;i<variantDiscountSorted.size();i++) {
            System.out.println("Variant Price from Cat page: " + variantDiscount.get(i)  +  "--Variant Price from sorted input: " + variantDiscountSorted.get(i));
            if (!(variantDiscountSorted.get(i).equals(variantDiscount.get(variantDiscount.size()-(i+1))))) {
            	System.out.println("Variant Price not sorted: " + i);
            	assertFlag = false;
                break;
            }
        }        
        return assertFlag;        
	}
	
	/**
	 * verify filter by Brand
	 */
	public boolean verifyFilterByBrand() {
		
		boolean assertFlag = true;
        
        for (WebElement element : getcatPageVariantNames()) {
        	System.out.println(element.getText());
        	if(!element.getText().contains("MuscleBlaze")) {
        		System.out.println("Wrong Brand shown in filtered results.. " + element.getText());
            	assertFlag = false;
                break;
        	}        		
        }
        return assertFlag;		
	}
	
	public void handleCatPageHltrForLoadMore() {
		if(verifyHltrPresent()) {
			clickResetAllFilters();
		}		
		WebDriverUtil.staticWait(3);
	}

	public boolean verifyLoadMoreOnCatPage() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean assertFlag = false;
		while(!isPage4LoadedWithVariants()) {
			scrollTillLoadMoreBtn();
		}		
		scrollTillLoadMoreBtn();
		WebDriverUtil.staticWait(3);		
		clickLoadMore();
		WebDriverUtil.staticWait(3);
		if(isPage5LoadedWithVariants()) {
			assertFlag = true;
		}
		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		return assertFlag;
	}

}
