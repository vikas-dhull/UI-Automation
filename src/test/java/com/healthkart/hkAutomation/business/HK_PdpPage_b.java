package com.healthkart.hkAutomation.business;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.pages.HK_PdpPage_p;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_PdpPage_b extends HK_PdpPage_p{

	public GenericDbActions dbActionsObj;
	
	public HK_PdpPage_b(WebDriver driver) {
		super(driver);
		dbActionsObj = new GenericDbActions();
	}
	
	public void addVariantToCart() {
		addToCart();
	}
	
	public void buyNowExpressCheckout()
	{
		buyNow();
	}
	
	public String getCompareVariant() {
		return dbActionsObj.getInStockCompareVariant();
	}
	
	public void performCompareVariants() {
		clickCompareBtn();
		
		if(WebDriverUtil.waitForWindowCount(driver, 2)) {
			
			for (String allHandles : driver.getWindowHandles()) {
				driver.switchTo().window(allHandles);
				System.out.println("current window title :" + driver.getTitle());
				if(driver.getCurrentUrl().contains("StoreVariantCompare.action?")) {
					driver.switchTo().window(allHandles);
					System.out.println("Switched to Compare Variant page window..");
				}
			}
		}	
		
	}
	
	public void visitReviewPage() {
		scrollToBuyNow();
		clickWriteAReviewBtn();
		
		if(WebDriverUtil.waitForWindowCount(driver, 2)) {
			
			for (String allHandles : driver.getWindowHandles()) {
				driver.switchTo().window(allHandles);
				System.out.println("current window title :" + driver.getTitle());
				if(driver.getCurrentUrl().contains("healthkart.com/reviews")) {
					driver.switchTo().window(allHandles);
					System.out.println("Switched to Compare Variant page window..");
				}
			}
		}
	}

	public boolean verifyAllLinksPDPWeights() {
		boolean flag = false;
		List <WebElement> pdpWghts = getAllLinksPDPWeights();
		for(int i=1;i<=pdpWghts.size();i++) {
			WebElement link = driver.findElement(By.xpath(".//div[contains(@class,'Weight')]/a["+i+"]"));
			String atr = link.getAttribute("attribute-name");
			String vrnt = link.getAttribute("default-variant");
			WebDriverUtil.click(driver, link, atr+"::"+vrnt);			
			if(!verifyWeightLabel(atr)) {
				flag=false;
				break;
			}
			else {
				flag=true;
			}
		}
		return flag;
	}

	public boolean verifyAllLinksPDPFlavours() {
		boolean flag = false;
		List <WebElement> pdpFlvrs = getAllLinksPDPFlavours();
		for(int i=1;i<=pdpFlvrs.size();i++) {
			WebElement link = driver.findElement(By.xpath(".//div[contains(@class,'Flavour')]/a["+i+"]"));
			String atr = link.getAttribute("attribute-name");
			String vrnt = link.getAttribute("default-variant");
			WebDriverUtil.click(driver, link, atr+"::"+vrnt);
			if(!verifyFlavourLabel(atr)) {
				flag=false;
				break;
			}
			else {
				flag=true;
			}
		}
		return flag;
	}

	public void wishlistItemFromIcon() 
	{
		clickWishlistButtonOnProductImage();
	}

}
