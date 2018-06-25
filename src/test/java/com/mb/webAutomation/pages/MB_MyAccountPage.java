package com.mb.webAutomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.mb.webAutomation.business.MB_HomeSteps;

public class MB_MyAccountPage extends CommonFunctions
{

	public WebDriver driver;
	public GenericDbActions dbActions;
	public MB_HomeSteps homeSteps;
	
	public MB_MyAccountPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		dbActions = new GenericDbActions();
		homeSteps = new MB_HomeSteps(driver);
	}
	
	@FindBy(xpath="//div[contains(@class,'col-xs')]//button[@data-action='cancel'][1]")
	private WebElement cancelOrderButton;
		
	@FindBy(xpath="//div[@class='popup-root']//select[@name='reason']")
	private WebElement selectCancelReason;
	
	@FindBy(xpath="//div[@class='popup-root']//button[contains(text(),'Cancel Order')]")
	private WebElement CancelOrderPopup;
	
	public void clickSideTab(String sideTab)
	{
//		scrollToObjectWithMargin(driver, driver.findElement(By.xpath("//a[text()='" + sideTab + "']")), 200);
		homeSteps.closeVideoBanner();
		click(driver.findElement(By.xpath("//a[text()='" + sideTab + "']")));
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
}
