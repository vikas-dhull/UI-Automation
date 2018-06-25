package com.mb.MsiteAutomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.WebDriverUtil;

import io.appium.java_client.android.AndroidDriver;

public class MBmsite_HomePage extends CommonFunctions
{

	public AndroidDriver<WebElement> androidDriver;
	
	public MBmsite_HomePage(AndroidDriver<WebElement> androidDriver) 
	{
		this.androidDriver = androidDriver;
		PageFactory.initElements(androidDriver, this);
	}
	
	@FindBy(xpath="//a[contains(@href,'/auth/Login.action')]")
	private WebElement newUserButton;
	
	@FindBy(xpath="//span[text()='Login with google']")
	private WebElement googleButton;

	@FindBy(id="identifierId")
	private WebElement emailField;
	
	@FindBy(id="identifierNext")
	private WebElement nextButton;
	
	@FindBy(xpath="//form[@id='signInFormMine']//input[@name='password']")
	private WebElement passwordField;
	
	@FindBy(id="passwordNext")
	private WebElement nextPasswordButton;
	
	@FindBy(xpath="//span[@class='userIcon']/following-sibling::span[text()='Hi']")
	private WebElement loggedInText;
	
	@FindBy(linkText="Log Out")
	private WebElement logOutButton;
	
	@FindBy(xpath="//div[@id='loginInitialDiv']//span[contains(text(),'Login with Facebook ')]")
	private WebElement facebookButton;
	
	@FindBy(xpath="//form[@id='signInFormMine']//input[contains(@class,'OTP') and @type='checkbox']")
	private WebElement loginViaOtp;
	
	@FindBy(xpath="//form[@id='signInFormMine']//input[@name='email']")
	private WebElement mobileNumberField;
	
	@FindBy(xpath="//form[@id='signInFormMine']//input[@value='Log In']")
	private WebElement loginButton;
	
	@FindBy(xpath="//form[@id='verifyMobileNumberFormId']//input[@placeholder='Enter OTP']")
	private WebElement enterOTP;
	
	@FindBy(xpath="//form[@id='verifyMobileNumberFormId']//input[@value='CONTINUE']")
	private WebElement continueButton;
	
	@FindBy(xpath="//a[contains(text(),'shop')]")
	private WebElement shopLink;
	
	@FindBy(linkText="Sign Up Now")
	private WebElement signUpButton;
	
	@FindBy(xpath="//form[@id='signInFormMine']//span[contains(text(),'new customer')]/preceding-sibling::input")
	private WebElement newCustomer;
	
	@FindBy(xpath="//form[@id='signUpForm']//input[@name='email' and contains(@placeholder,'Enter Mobile')]")
	private WebElement signupMobileNumberField;
	
	@FindBy(xpath="//form[@id='otpVerificationFormSignup']//input[@placeholder='Enter OTP']")
	private WebElement signupOtpField;
	
	@FindBy(xpath="//form[@id='signUpForm']//input[@value='CONTINUE']")
	private WebElement continueSignup;
	
	@FindBy(xpath="//form[@id='otpVerificationFormSignup']//input[@placeholder='Password']")
	private WebElement signupPassword;
	
	@FindBy(id="otpSignupButtonId")
	private WebElement otpSignUpButton;
	
	@FindBy(xpath="//a[contains(@href,'Cart.action') and contains(@class,'xs')]")
	private WebElement cartButton;
	
	@FindBy(xpath="//div[@id='cartPop']//a")
	private WebElement proceedToCartButton;
	
	@FindBy(id="auth-form")
	private WebElement authenticationDiv;
	
	@FindBy(id="phone")
	private WebElement authenticationMobileNumber;
	
	@FindBy(xpath="//button[text()='Check Now']")
	private WebElement checkNowAuthentication;
	
	@FindBy(xpath="//img[@alt='Lab Certificate']")
	private WebElement proteinLabCertification;
	
	@FindBy(xpath="//h2[contains(text(),'Lab Report')]")
	private WebElement labReportHeading;
	
	@FindBy(xpath="//*[contains(text(),'You have a genuine product')]")
	private WebElement genuineProductText;
	
	@FindBy(xpath="//div[contains(@class,'userMobl')]/a[text()='Check Your Profile']")
	private WebElement clickCheckYourProfileButton;
	
	@FindBy(xpath="//span[@class='close-video-banner']")
	private WebElement closeVideoBannerButton;
	
	@FindBy(xpath="//a[contains(@class,'loggedInUser')]")
	private WebElement userIcon;
	
	@FindBy(xpath="//button[contains(@class,'pull-left menu-btn')]")
	private WebElement leftPanelButton;
	
	@FindBy(xpath="//a[contains(text(),'shop')]")
	private WebElement shopButton;
	
	public void clickNewUserButton() 
	{
		click(newUserButton);
	}

	public void loginViaGoogle(String gmailId, String gmailPswd) 
	{
		String hkWinHndl = androidDriver.getWindowHandle();
		click(googleButton);		

		
		WebDriverUtil.staticWait(4);		
			
		
		if(WebDriverUtil.waitForWindowCount(androidDriver, 2)) {
		
			for (String allHandles : androidDriver.getWindowHandles()) {
				androidDriver.switchTo().window(allHandles);
				System.out.println("current window title :" + androidDriver.getTitle());
				if(androidDriver.getTitle().contains("Sign in")) {
					System.out.println("Switched to Google window..");
					WebElement gmailUserInpt = androidDriver.findElement(By.xpath("//input[@id='identifierId']"));
					gmailUserInpt.sendKeys(gmailId);
					
					WebElement gmailIdentifierNext = androidDriver.findElement(By.xpath("//div[@id='identifierNext']"));
					gmailIdentifierNext.click();							
					WebDriverUtil.staticWait(4);					
					WebElement gmailPswdInpt = androidDriver.findElement(By.xpath("//input[@name='password']"));
					gmailPswdInpt.sendKeys(gmailPswd);					
					WebElement gmailPswdNext = androidDriver.findElement(By.xpath(".//*[@id='passwordNext']"));
					gmailPswdNext.click();
				}
				else if(androidDriver.getTitle().contains("Error")) {
					System.out.println("current window title :" + androidDriver.getTitle());
					androidDriver.close();
					break;
				}
			}
		
		WebDriverUtil.staticWait(10);
		androidDriver.switchTo().window(hkWinHndl);
		System.out.println("current window title :" + androidDriver.getTitle());
		}
	}

	public boolean checkUserLoggedIn()
	{
		staticWait(5);
		click(userIcon);
		return logOutButton.isDisplayed();
	}
	
	public void clickLogout()
	{
//		click(loggedInText);
		click(logOutButton);
	}
	
	public void clickLoggedInText()
	{
		click(userIcon);
	}
	
	public void loginViaFacebook(String fbMailId, String fbPswd)
	{
		String hkWinHndl = androidDriver.getWindowHandle();
//		click(facebookButton);
		facebookButton.click();
		if(WebDriverUtil.waitForWindowCount(androidDriver, 2)) {	
			
			for (String allHandles : androidDriver.getWindowHandles()) { // traverse all window handles.
				androidDriver.switchTo().window(allHandles);
				System.out.println("current window title :" + androidDriver.getTitle());
				if(androidDriver.getTitle().contains("Facebook")) {
					System.out.println("Switched to Facebook window..");
					
					WebElement fbUserInpt = androidDriver.findElement(By.id("m_login_email"));
					fbUserInpt.sendKeys(fbMailId);					
					WebElement fbPswdInpt = androidDriver.findElement(By.id("m_login_password"));
					fbPswdInpt.sendKeys(fbPswd);					
					WebElement fbLoginSub = androidDriver.findElement(By.id("u_0_5"));					
					fbLoginSub.click();
				}
				else if(androidDriver.getTitle().contains("Error")) {
					System.out.println("current window title :" + androidDriver.getTitle());
					androidDriver.close();
					break;
				}
			}
		
		WebDriverUtil.staticWait(10);
		androidDriver.switchTo().window(hkWinHndl);
		System.out.println("current window title :" + androidDriver.getTitle());
		}
	}
	
	public boolean checkUserLoggedOut()
	{
		return newUserButton.isDisplayed();
	}
	
	public void clickLoginViaOTP()
	{
		scrollToObject(androidDriver, loginViaOtp);
		click(loginViaOtp);
	}
	
	public void enterMobileNumber(String mobileNumber)
	{
		sendKeys(mobileNumberField, mobileNumber);
	}
	
	public void clickLoginButton()
	{
		click(loginButton);
	}
	
	public void enterOTP(String otp)
	{
		sendKeys(enterOTP, otp);
	}
	
	public void clickContinueButton()
	{
		scrollToObject(androidDriver, continueButton);
		click(continueButton);
	}
	
	public void enterPassword(String password)
	{
		sendKeys(passwordField, password);
	}
	
	public void selectVariant(String variant)
	{
		WebDriverUtil.mouseHoverAndClick(androidDriver, shopLink, "shopLink");
		String xpathExpression = "//ul[@role='menu']//span[text()='" + variant + "']";
		WebDriverUtil.mouseHoverAndClick(androidDriver, androidDriver.findElement(By.xpath(xpathExpression)), variant);
	}
	
	public void clickSignUpButton()
	{
		click(signUpButton);
	}
	
	public void checkNewCustomer()
	{
		click(newCustomer);
	}
	
	public void enterMobileNumberForSignup(String mobileNumber)
	{
		sendKeys(signupMobileNumberField, mobileNumber);
	}
	
	public void clickContinueSignUpButton()
	{
		click(continueSignup);
	}
	
	public void enterSignUpOtp(String otp)
	{
		sendKeys(signupOtpField, otp);
	}
	
	public void enterSignUpPassword(String password)
	{
		sendKeys(signupPassword, password);
	}
	
	public void clickSignUpOtpButton()
	{
		click(otpSignUpButton);
	}
	
	public void clickCartIcon()
	{
		click(cartButton);
//		click(proceedToCartButton);
	}
	
	public boolean isAuthenticationDivDisplayed()
	{
		scrollToObject(androidDriver, authenticationDiv);
		return authenticationDiv.isDisplayed();
	}
	
	public void enterAuthenticationCode(String code)
	{
		char ch[] = code.toCharArray();
		for(int i=0; i<ch.length;i++)
		{
			int j=i+1;
			String xpathExpression = "//div[@class='form-group couponcode']/input[" + j + "]";
			androidDriver.findElement(By.xpath(xpathExpression)).sendKeys(ch[i]+"");
		}
	}
	
	public void submitAuthenticationCode(String mobile)
	{
		sendKeys(authenticationMobileNumber, mobile);
		click(checkNowAuthentication);
	}
	
	public void clickProteinLabCertificateButton()
	{
		click(proteinLabCertification);
	}
	
	public boolean verifyLabReportDisplayed()
	{
		return labReportHeading.isDisplayed();
	}
	
	public boolean verifyGenuineProductDisplayed()
	{
		return genuineProductText.isDisplayed();
	}
	
	public void clickCheckYourProfile()
	{
		click(clickCheckYourProfileButton);
	}
	
	public void clickCloseVideoBanner()
	{
		click(closeVideoBannerButton);
	}
	
	public void clickLeftPanel()
	{
		click(leftPanelButton);
	}
	
	public void clickShopButton()
	{
		click(shopButton);
	}
	
	public void clickVariantFromLeftPanel(String variantName)
	{
		clickBy(By.xpath("//span[contains(text(),'" + variantName + "')]"));
	}
}
