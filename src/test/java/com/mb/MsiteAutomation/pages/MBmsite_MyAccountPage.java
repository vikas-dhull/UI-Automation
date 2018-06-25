package com.mb.MsiteAutomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.mb.webAutomation.business.MB_HomeSteps;

import io.appium.java_client.android.AndroidDriver;

public class MBmsite_MyAccountPage extends CommonFunctions
{

	public AndroidDriver<?> androidDriver;
	public GenericDbActions dbActions;
	public MB_HomeSteps homeSteps;
	
	public MBmsite_MyAccountPage(AndroidDriver<?> androidDriver) 
	{
		this.androidDriver = androidDriver;
		PageFactory.initElements(androidDriver, this);
		dbActions = new GenericDbActions();
		homeSteps = new MB_HomeSteps(androidDriver);
	}
	
	@FindBy(xpath="//div[contains(@class,'col-xs')]//button[@data-action='cancel'][1]")
	private WebElement cancelOrderButton;
		
	@FindBy(xpath="//div[@class='popup-root']//select[@name='reason']")
	private WebElement selectCancelReason;
	
	@FindBy(xpath="//div[@class='popup-root']//button[contains(text(),'Cancel Order')]")
	private WebElement CancelOrderPopup;
	
	@FindBy(xpath="//div[@class='popup-root']//select[contains(@class,'refund-type')]")
	private WebElement refundType;
	
	public void clickSideTab(String sideTab)
	{
//		scrollToObjectWithMargin(androidDriver, androidDriver.findElement(By.xpath("//a[text()='" + sideTab + "']")), 200);
		homeSteps.closeVideoBanner();
		click(androidDriver.findElement(By.xpath("//a[text()='" + sideTab + "']")));
	}
	
	public void clickCancelOrder()
	{
		click(cancelOrderButton);
	}
	
	public void selectReason(String reason)
	{
		Select select =  new Select(selectCancelReason);
		select.selectByIndex(4);
	}
	
	public void clickCancelOrderPopup()
	{
		click(CancelOrderPopup);
	}
	
	public void selectRefundType()
	{
		Select select = new Select(refundType);
		select.selectByIndex(1);
	}
}
