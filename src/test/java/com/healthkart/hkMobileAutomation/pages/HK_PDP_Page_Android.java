package com.healthkart.hkMobileAutomation.pages;
import org.openqa.selenium.By;
import com.healthkart.hkAutomation.util.CommonFunctions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class HK_PDP_Page_Android extends CommonFunctions
{
	AndroidDriver<?> androidDriver;
	public HK_PDP_Page_Android(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
	}

	private By buyNowButtonBy = By.id("com.healthkart.healthkart:id/buy_now_btn");
	private By addToCartButtonBy = By.id("com.healthkart.healthkart:id/add_cart_btn");
	private By pdpImageForWaitBy = By.id("com.healthkart.healthkart:id/photo_pager");
	private By compareNowButtonBy = By.id("com.healthkart.healthkart:id/pl_compare_btn");
	private By buyNowMuscleBlazeBy = By.id("com.healthkart.healthkart:id/button2");
	private By writeAReviewButtonBy = By.id("com.healthkart.healthkart:id/app_write_review_btn");
	private By similarProductTextBy = By.id("com.healthkart.healthkart:id/similar_title");
	private By ratingBarProductBy = By.id("com.healthkart.healthkart:id/rating_bar_product");
	private By ratingTasteFeatureBy = By.xpath("//android.widget.TextView[@text='Taste']//following-sibling::android.widget.RelativeLayout/android.widget.RatingBar");
	private By ratingMixabilityFeatureBy = By.xpath("//android.widget.TextView[@text='Mixability']//following-sibling::android.widget.RelativeLayout/android.widget.RatingBar");
	private By ratingEfficacyFeatureBy = By.xpath("//android.widget.TextView[@text='Efficacy']//following-sibling::android.widget.RelativeLayout/android.widget.RatingBar");
	private By ratingValueForMoneyFeatureBy = By.xpath("//android.widget.TextView[@text='Value for money']//following-sibling::android.widget.RelativeLayout/android.widget.RatingBar");
	private By reviewTitleBy = By.id("com.healthkart.healthkart:id/title");
	private By reviewDescBy = By.id("com.healthkart.healthkart:id/description");
	private By reviewSubmitBy = By.id("com.healthkart.healthkart:id/submit_review");
	private By reviewSuccessDismissBy = By.xpath("//android.widget.Button[@text='DISMISS']");
	private By wishListButton = By.id("com.healthkart.healthkart:id/content_product_wishlist_image");
	
	
	public void clickBuyNow() 
	{
		staticWait(4);
		waitForElementToBePresent(androidDriver, 30, pdpImageForWaitBy);
		click(androidDriver.findElement(buyNowButtonBy));
	}
	
	public void clickAddToCart()
	{
		staticWait(4);
		waitForElementToBePresent(androidDriver, 30, addToCartButtonBy);
		click(androidDriver.findElement(addToCartButtonBy));
	}



	public void scrollToCompareNow() 
	{
		scrollAndSwipeByElementAndroid(androidDriver,compareNowButtonBy,"up");
	}



	public void clickCompareNow() 
	{
		clickBy(compareNowButtonBy);
	}

	public boolean verifyTwoBuyNowButtonsDisplayed() 
	{
		int buttons = androidDriver.findElementsByAndroidUIAutomator(".textContains(\"Buy Now\")").size();
		System.out.println(buttons);
		if (buttons==2) 
			return true;
		else
			return false;
	}

	public void clickBuyNowForMB() 
	{
		clickBy(buyNowMuscleBlazeBy);
	}

	public void writeAReview(String review, String description) 
	{	
		scrollAndSwipeByElementAndroid(androidDriver, writeAReviewButtonBy,"up");
		clickBy(writeAReviewButtonBy);
		clickBy(ratingBarProductBy);
		clickBy(ratingTasteFeatureBy);
		clickBy(ratingMixabilityFeatureBy);
		clickBy(ratingEfficacyFeatureBy);
		clickBy(ratingValueForMoneyFeatureBy);
		scrollAndSwipeByElementAndroid(androidDriver, reviewSubmitBy,"up");
		sendKeys(androidDriver.findElement(reviewTitleBy), review);
		androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		sendKeys(androidDriver.findElement(reviewDescBy), description);
		androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		clickBy(reviewSubmitBy);
		clickBy(reviewSuccessDismissBy);
	}



	public void clickWishlistButton() 
	{
		click(androidDriver.findElement(wishListButton));
	}
		

}
