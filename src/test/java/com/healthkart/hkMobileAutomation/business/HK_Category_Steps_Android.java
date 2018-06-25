package com.healthkart.hkMobileAutomation.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkMobileAutomation.pages.HK_Category_Page_Android;
import com.healthkart.hkMobileAutomation.pages.HK_PDP_Page_Android;

import io.appium.java_client.android.AndroidDriver;

public class HK_Category_Steps_Android
{
	AndroidDriver<?> androidDriver;
	HK_Category_Page_Android categoryPage;
	String otp;
	GenericDbActions dbActions;
	
	public HK_Category_Steps_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		categoryPage = new HK_Category_Page_Android(androidDriver);
		dbActions = new GenericDbActions();
	}

	public void sortListing(String sortByType)
	{
		categoryPage.clickSortListing(sortByType);
	}

	public boolean verifySorting(String sortType) 
	{
		
			boolean assertFlag = true;
			
			List<Float> variantPriceSorted = new ArrayList<Float>();
	        List<Float> variantPrice = new ArrayList<Float>();
	        
	        
	        for (WebElement element : getVariantTilesListForPrice()) {
	        	System.out.println(Float.parseFloat(element.getText().substring(4).replaceAll(",","").replaceAll("Rs.", "")));
	        	variantPriceSorted.add(Float.parseFloat(element.getText().substring(4).replaceAll(",","").replaceAll("Rs.", "")));
	        	variantPrice.add(Float.parseFloat(element.getText().substring(4).replaceAll(",","")));        	
	        }
	        
	        Collections.sort(variantPriceSorted);
	        if(sortType.contains("Low To High")) 
	        {
	        	for (int i=0;i<variantPriceSorted.size();i++) {
		            System.out.println("Variant Price from Cat page: " + variantPrice.get(i)  +  "--Variant Price from sorted input: " + variantPriceSorted.get(i));
		            if (!(variantPriceSorted.get(i).equals(variantPrice.get(i)))) {
		            	System.out.println("Variant Price not sorted: " + i);
		            	assertFlag = false;
		                break;
		            }
		        }
	        }
	        else if(sortType.contains("High To Low"))
	        {
	        	Collections.sort(variantPriceSorted,Collections.reverseOrder());
	        	for (int i=0;i<variantPriceSorted.size();i++) {
		            System.out.println("Variant Price from Cat page: " + variantPrice.get(i)  +  "--Variant Price from sorted input: " + variantPriceSorted.get(i));
		            if (!(variantPriceSorted.get(i).equals(variantPrice.get(i)))) {
		            	System.out.println("Variant Price not sorted: " + i);
		            	assertFlag = false;
		                break;
		            }
		        }
	        }
	                
	        return assertFlag;        
		
	}

	private List<WebElement> getVariantTilesListForPrice() 
	{
		List<WebElement> elements = categoryPage.listOfPrice();
		
		return elements;
	}

	public void selectProduct(String productName, String size, String Flavour) 
	{
		categoryPage.selectProduct(productName,size, Flavour);
	}

	public void applyFilter(String filterName) 
	{
		categoryPage.clickFilterButton();
		categoryPage.selectFilter(filterName);
	}

	public void applySubFilter(String subFilter) 
	{
		categoryPage.clickSubFilter(subFilter);
	}

	public void clickApplyFilter() 
	{
		categoryPage.clickApplyFiter();
	}
	
	
	
	
	
	

}
