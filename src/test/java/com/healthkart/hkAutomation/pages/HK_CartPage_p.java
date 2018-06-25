package com.healthkart.hkAutomation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_CartPage_p extends CommonFunctions{
	
	public WebDriver driver;
	
	public HK_CartPage_p(WebDriver driver) {		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Handle notify visitor[For PROD ENV only.]
	 */	
	/*@FindBy(xpath = "//a[@id='nv_js-modal-close-button']")
	private WebElement closeNotifyVisitorOverlay;
	
	public void clickCloseNotifyVisitorOverlay() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if(WebDriverUtil.isElementClickable(closeNotifyVisitorOverlay, driver,1))
	       	closeNotifyVisitorOverlay.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebDriverUtil.click(driver, closeNotifyVisitorOverlay, "closeNotifyVisitorOverlay");
	    if(WebDriverUtil.isElementClickable(closeNotifyVisitorOverlay, driver,20))
	       	closeNotifyVisitorOverlay.click();
	}*/

	/**
	 * Is cart page loaded successfully
	 */
	@FindBy(xpath="//div[@id='cart-page-variants-list0']")
	private WebElement variantListIncart;
		
	public boolean verifyCartPageLoaded() {
		boolean  flag = false;
		staticWait(5);
		/*if(GlobalVar.jenkinsEnvironment==null)        // Handle notify visitor overlay [For PROD ENV only.]
		{
			if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
				WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);			
			}
		}
		else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
		{
			WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);
		}*/
		
		WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);
		
		if(WebDriverUtil.isElementPresent(variantListIncart, driver,20)) {
			if(driver.getCurrentUrl().contains("Cart.action"))
				flag = true;
		}			
		System.out.println("verify cart page flag : " + flag);
		return flag;		
	}
	
	/**
	 * Is BxGy cart page loaded successfully
	 */
	@FindBy(xpath="//div[contains(@class,'bxgy-freebie')]")
	private WebElement bXgYOfferVariant;
	
	@FindBy(xpath="//div[contains(@class,'freebie applied')]")
	private WebElement bXgYOfferApplied;
	
	@FindBy(xpath="//div[contains(@class,'bxgy-freebie')]//div[contains(@class,'price')]/a[contains(@class,'close-bxgy')]")
	private WebElement bXgYOfferVariantRemoveBtn;
	
		
	public boolean verifyBxGyOfferApplied() {
		boolean  flag = false;
		if(WebDriverUtil.isElementPresent(bXgYOfferVariant, driver,3) && WebDriverUtil.isElementPresent(bXgYOfferApplied, driver,3) ) {
			if(driver.getCurrentUrl().contains("Cart.action"))
				flag = true;
		}			
		System.out.println("verify cart page flag for BxGy offer : " + flag);
		return flag;		
	}
	
	/**
	 * Remove BxGy Offer If Applied
	 */
	public void clickRemoveBxGyOffer() {
			WebDriverUtil.click(driver, bXgYOfferVariantRemoveBtn, "bXgYOfferVariantRemoveBtn");
	}
	
	/*public boolean verifyIfBxGyOfferRemoved(){
		boolean  flag = false;
		if(WebDriverUtil.isElementPresent(bXgYOfferVariant, driver,2) && WebDriverUtil.isElementPresent(bXgYOfferApplied, driver,2) ) {
			if(driver.getCurrentUrl().contains("Cart.action"))
				flag = true;
		}			
		System.out.println("verify cart page flag for BxGy offer : " + flag);
		return flag;
	}*/
	
	/**
	 * HK Cash Redeem
	 */
	@FindBy(xpath="//div[contains(@class,'visible-xs continue-shopping')]/following-sibling::div//span[text()='(Redeem)']")
	private WebElement hkCashRedeem;
	
	public void clickHKCashRedeem() {
		WebDriverUtil.click(driver, hkCashRedeem, "hkCashRedeem");
		
		/*if(WebDriverUtil.isElementPresent(hkCashRedeem, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(hkCashRedeem).build().perform();
	        if(WebDriverUtil.isElementClickable(hkCashRedeem, driver))
	        	hkCashRedeem.click();
		}*/
	}
	
	/**
	 * HK cash Redeemed successfully Text   
	 */
	@FindBy(xpath="//div[contains(@class,'visible-xs continue-shopping')]/following-sibling::div//span[text()=' HK Cash applied']")
	private WebElement hkCashRedeemSuccess;
	
	public boolean verifyHKCashRedeemSuccess() {
		boolean  flag = false;
		if(WebDriverUtil.isElementPresent(hkCashRedeemSuccess, driver,20)) {
			if(driver.getCurrentUrl().contains("Cart.action"))
				flag = true;
		}			
		return flag;		
	}
	
	/**
	 * Remove HK Cash Redeemed.
	 */
	@FindBy(xpath="//div[contains(@class,'cart-sum-tbl hidden-xs')]//span[text()='(Remove)']")
	private WebElement removeHKCashRedeemed;
	
	public void clickRemoveHKCashRedeemed() {
		WebDriverUtil.click(driver, removeHKCashRedeemed, "removeHKCashRedeemed");
		
		/*if(WebDriverUtil.isElementPresent(removeHKCashRedeemed, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(removeHKCashRedeemed).build().perform();
	        if(WebDriverUtil.isElementClickable(removeHKCashRedeemed, driver))
	        	removeHKCashRedeemed.click();
		}*/
	}
	
	/**
	 * HK cash Removed successfully Text   
	 */
	@FindBy(xpath="//div[contains(@class,'cart-sum-tbl hidden-xs')]//span[text()='(Redeem)']")
	private WebElement hkCashRemovedSuccess;
	
	public boolean verifyHKCashRemovedSuccess() {
		boolean  flag = false;
		if(WebDriverUtil.isElementPresent(hkCashRemovedSuccess, driver,20)) {
			if(driver.getCurrentUrl().contains("Cart.action"))
				flag = true;
		}			
		return flag;		
	}
	
	/**
	 * Proceed To Checkout
	 */
	@FindBy(linkText="Proceed to checkout")
	private WebElement proceedToCheckout;
	
	public void clickProceedToCheckout() {
		WebDriverUtil.click(driver, proceedToCheckout, "proceedToCheckout");
		
		/*if(WebDriverUtil.isElementPresent(proceedToCheckout, driver) && WebDriverUtil.isElementClickable(proceedToCheckout, driver))
			proceedToCheckout.click();*/
	}
	
	/**
	 * increase 1 quantity of a given variant on cart
	 */	
	public void qtyIncreaseBy1(String vrntName) {
		WebElement qtyInc=driver.findElement(By.xpath("//a[text()='" + vrntName + "']/div/a[@id='plus']"));
		WebDriverUtil.click(driver, qtyInc, "qtyInc");
		
		/*if(WebDriverUtil.isElementPresent(qtyInc, driver) && WebDriverUtil.isElementClickable(qtyInc, driver))
			qtyInc.click();	*/	
	}
	
	/**
	 * decrease 1 quantity of a given variant on cart
	 */
	
	public void qtyDecreaseBy1(String vrntName) {
		WebElement qtyDec=driver.findElement(By.xpath("//a[text()='" + vrntName + "']/div/a[@id='minus']"));
		WebDriverUtil.click(driver, qtyDec, "qtyDec");
		
		/*if(WebDriverUtil.isElementPresent(qtyDec, driver) && WebDriverUtil.isElementClickable(qtyDec, driver))
			qtyDec.click();	*/	
	}
	
	/**
	 * delete given variant from cart
	 */	
	public void deleteVrntFromCart(String vrntName) {
		WebElement delVrnt=driver.findElement(By.xpath("//a[text()='" + vrntName + "']/div[2]/a[@data-role='item-remove']"));
		WebDriverUtil.click(driver, delVrnt, "delVrnt");
		/*if(WebDriverUtil.isElementPresent(delVrnt, driver) && WebDriverUtil.isElementClickable(delVrnt, driver))
			delVrnt.click();*/		
	}
	
	/**
	 * Get current added quantity of a variant in the cart.
	 */
	public void getCurrentAddedQtyForVrnt(String vrntName) {
		WebElement totalVrntQtyAvlbl=driver.findElement(By.xpath("//a[text()='" + vrntName + "']/div/input[@id='edit']"));
		WebDriverUtil.click(driver, totalVrntQtyAvlbl, "totalVrntQtyAvlbl");
		/*if(WebDriverUtil.isElementPresent(totalVrntQtyAvlbl, driver) && WebDriverUtil.isElementClickable(totalVrntQtyAvlbl, driver))
			totalVrntQtyAvlbl.getText();*/
	}
	
	/**
	 * delete all quantity for first variant.
	 */
	@FindBy(xpath="//a[@data-role='item-remove']")
	private WebElement removeFirstItem;
	
	@FindBy(xpath="//a[text()='Remove']")
	private WebElement removeItem;
	
	public void clickRemoveFirstItem() {
//		WebDriverUtil.click(driver, removeFirstItem, "removeFirstItem");
		WebDriverUtil.click(driver, removeItem, "removeItem");
		
		/*if(WebDriverUtil.isElementPresent(removeFirstItem, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(removeFirstItem).build().perform();
	        if(WebDriverUtil.isElementClickable(removeFirstItem, driver))
	        	removeFirstItem.click();
		}*/
	}
	
	/**
	 * Promo discount on cart   
	 */
	@FindBy(xpath = "//div[contains(@class,'visible-xs continue-shopping')]/following-sibling::div//span[@data-role='promo-discount']")
	private WebElement promoDiscount;
	
	public boolean verifyPromoDiscountOnCart() {
		boolean flag = false;
		if(WebDriverUtil.isElementPresent(promoDiscount, driver,20)) {
			if(Integer.parseInt(promoDiscount.getText()) > 0)
				flag = true;
		}
		System.out.println("promo discount flag : " + flag);
		return flag;		
	}
	
	/**
	 * Input Coupon Code for offer.
	 */
	@FindBy(xpath = "//input[@name='couponCode']")
	private WebElement inputCouponCode;
	
	@FindBy(xpath = "//a[contains(@class,'apply-coupon')]")
	private WebElement applyCouponCode;
	
	public void setCouponCode(String couponCode) {
		if(WebDriverUtil.isElementPresent(inputCouponCode, driver,20)) {
			inputCouponCode.clear();
			inputCouponCode.sendKeys(couponCode);
		}
	}
	
	public void clickApplyCouponCode() {
		WebDriverUtil.click(driver, applyCouponCode, "applyCouponCode");
		
		/*if(WebDriverUtil.isElementPresent(applyCouponCode, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(applyCouponCode).build().perform();
	        if(WebDriverUtil.isElementClickable(applyCouponCode, driver))
	        	applyCouponCode.click();
		}*/
	}
	
	/**
	 * verify If Coupon code applied successfully
	 */
	@FindBy(xpath = "//div[contains(text(),'Coupon applied successfully')]")
	private WebElement couponCodeApplied;
	
	public boolean verifyCouponCodeApplied() {
		return (WebDriverUtil.isElementPresent(couponCodeApplied, driver,20));
	}
	
	/**
	 * remove coupon button.
	 */
	@FindBy(xpath = "//a[contains(@class,'remove-coupon')]")
	private WebElement couponCodeRemove;	
	
	public void clickRemoveCouponCode() {
		WebDriverUtil.click(driver, couponCodeRemove, "couponCodeRemove");
		
		/*if(WebDriverUtil.isElementPresent(couponCodeRemove, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(couponCodeRemove).build().perform();
	        if(WebDriverUtil.isElementClickable(couponCodeRemove, driver))
	        	couponCodeRemove.click();
		}*/
	}
	
	/**
	 * Apply prompt offer 'Extra 10% off on MuscleBlaze High Protein Gainer'
	 */
	@FindBy(xpath = "//span[contains(text(),'Extra 10% off on MuscleBlaze High Protein Gainer')]")
	private WebElement promptOfferApply;	
	
	public void clickApplyPromptOffer() {
		WebDriverUtil.click(driver, promptOfferApply, "promptOfferApply");
		
		/*if(WebDriverUtil.isElementPresent(promptOfferApply, driver)) {
			WebDriverUtil.scrollToObject(driver, promptOfferApply);
	        if(WebDriverUtil.isElementClickable(promptOfferApply, driver))
	        	promptOfferApply.click();
		}*/
		WebDriverUtil.scrollTillTop(driver);
	}
	
	/**
	 * verify If Prompt offer applied successfully
	 */
	@FindBy(xpath = "//div[text()='Offer applied successfully']")
	private WebElement promptOfferApplied;
	
	public boolean verifyPromptOfferApplied() {
		return (WebDriverUtil.isElementPresent(promptOfferApplied, driver,20));
	}
	
	/**
	 * remove offer button.
	 */
	@FindBy(xpath = "//a[contains(@class,'remove-offer')]")
	private WebElement promptOfferRemove;	
	
	public void clickRemovePromptOffer() {
		WebDriverUtil.click(driver, promptOfferRemove, "promptOfferRemove");
		
		/*if(WebDriverUtil.isElementPresent(promptOfferRemove, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(promptOfferRemove).build().perform();
	        if(WebDriverUtil.isElementClickable(promptOfferRemove, driver))
	        	promptOfferRemove.click();
		}*/
	}

	/**
	 * Get total payable amount on cart.
	 */
	@FindBy(xpath = "//div[contains(@class,'cart-sum-tbl hidden-xs')]//div[contains(@class,'totlpayrow')]//span[@data-role='total-payable']")
	private WebElement totalPayableCartAmnt;
	
	public long getTotalPayableCartAmnt() {
		long totalCartPayableAmnt = Long.parseLong(totalPayableCartAmnt.getText());		
		return totalCartPayableAmnt;		
	}
	
	
	/**
	 * Back To Shopping
	 */
	@FindBy(xpath = "//a[contains(text(),'BACK TO SHOPPING')]")
	private WebElement backToShopping;
	
	public boolean backToShoppingBtnPresent() {
		return (WebDriverUtil.isElementClickable(backToShopping, driver,2));
	}
	
	public void clickBackToShopping() {
		WebDriverUtil.click(driver, backToShopping, "backToShopping");
		
		/*if(WebDriverUtil.isElementPresent(backToShopping, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(backToShopping).build().perform();
	        if(WebDriverUtil.isElementClickable(backToShopping, driver))
	        	backToShopping.click();
		}*/
	}
	
	@FindBy(xpath="//a[text()='Move to Wishlist']")
	private WebElement moveToShoppingButton;
	
	public void clickMoveToWishlist()
	{
		click(moveToShoppingButton);
	}
	
}
