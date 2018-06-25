package com.mb.webAutomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class MB_HomePage extends CommonFunctions
{

	public WebDriver driver;
	
	public MB_HomePage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='New User?']")
	private WebElement newUserButton;
	
	@FindBy(xpath="//span[text()='Login with Google']")
	private WebElement googleButton;

	@FindBy(id="identifierId")
	private WebElement emailField;
	
	@FindBy(id="identifierNext")
	private WebElement nextButton;
	
	@FindBy(name="password")
	private WebElement passwordField;
	
	@FindBy(id="passwordNext")
	private WebElement nextPasswordButton;
	
	@FindBy(xpath="//span[@class='userIcon']/following-sibling::span[text()='Hi']")
	private WebElement loggedInText;
	
	@FindBy(linkText="Log Out")
	private WebElement logOutButton;
	
	@FindBy(xpath="//span[contains(text(),'Login with Facebook')]")
	private WebElement facebookButton;
	
	@FindBy(xpath="//input[contains(@class,'OTP') and @type='checkbox']")
	private WebElement loginViaOtp;
	
	@FindBy(xpath="//input[@name='email' and contains(@placeholder,'Enter mobile')]")
	private WebElement mobileNumberField;
	
	@FindBy(xpath="//input[@name='login' and @type='button']")
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
	
	@FindBy(xpath="//div[@class='cart-pop-container']")
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
	
	@FindBy(xpath="//div[not(contains(@class,'userMobl'))]/a[text()='Check Your Profile']")
	private WebElement clickCheckYourProfileButton;
	
	@FindBy(xpath="//span[@class='close-video-banner']")
	private WebElement closeVideoBannerButton;
	
	public void clickNewUserButton() 
	{
		click(newUserButton);
	}

	public void loginViaGoogle(String gmailId, String gmailPswd) 
	{
		String hkWinHndl = driver.getWindowHandle();
		click(googleButton);		

		
		WebDriverUtil.staticWait(4);		
			
		
		if(WebDriverUtil.waitForWindowCount(driver, 2)) {
		
			for (String allHandles : driver.getWindowHandles()) {
				driver.switchTo().window(allHandles);
				System.out.println("current window title :" + driver.getTitle());
				if(driver.getTitle().contains("Sign in")) {
					System.out.println("Switched to Google window..");
					WebElement gmailUserInpt = driver.findElement(By.xpath("//input[@id='identifierId']"));
					gmailUserInpt.sendKeys(gmailId);
					
					WebElement gmailIdentifierNext = driver.findElement(By.xpath("//div[@id='identifierNext']"));
					gmailIdentifierNext.click();							
					WebDriverUtil.staticWait(4);					
					WebElement gmailPswdInpt = driver.findElement(By.xpath("//input[@name='password']"));
					gmailPswdInpt.sendKeys(gmailPswd);					
					WebElement gmailPswdNext = driver.findElement(By.xpath(".//*[@id='passwordNext']"));
					gmailPswdNext.click();
				}
				else if(driver.getTitle().contains("Error")) {
					System.out.println("current window title :" + driver.getTitle());
					driver.close();
					break;
				}
			}
		
		WebDriverUtil.staticWait(10);
		driver.switchTo().window(hkWinHndl);
		System.out.println("current window title :" + driver.getTitle());
		}
	}

	public boolean checkUserLoggedIn()
	{
		return loggedInText.isDisplayed();
	}
	
	public void clickLogout()
	{
		click(loggedInText);
		click(logOutButton);
	}
	
	public void clickLoggedInText()
	{
		click(loggedInText);
	}
	
	public void loginViaFacebook(String fbMailId, String fbPswd)
	{
		String hkWinHndl = driver.getWindowHandle();
		click(facebookButton);
		if(WebDriverUtil.waitForWindowCount(driver, 2)) {	
			
			for (String allHandles : driver.getWindowHandles()) { // traverse all window handles.
				driver.switchTo().window(allHandles);
				System.out.println("current window title :" + driver.getTitle());
				if(driver.getTitle().contains("Facebook")) {
					System.out.println("Switched to Facebook window..");
					
					WebElement fbUserInpt = driver.findElement(By.id("email"));
					fbUserInpt.sendKeys(fbMailId);					
					WebElement fbPswdInpt = driver.findElement(By.id("pass"));
					fbPswdInpt.sendKeys(fbPswd);					
					WebElement fbLoginSub = driver.findElement(By.id("u_0_0"));					
					fbLoginSub.click();
				}
				else if(driver.getTitle().contains("Error")) {
					System.out.println("current window title :" + driver.getTitle());
					driver.close();
					break;
				}
			}
		
		WebDriverUtil.staticWait(10);
		driver.switchTo().window(hkWinHndl);
		System.out.println("current window title :" + driver.getTitle());
		}
	}
	
	public boolean checkUserLoggedOut()
	{
		return newUserButton.isDisplayed();
	}
	
	public void clickLoginViaOTP()
	{
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
		scrollToObject(driver, continueButton);
		click(continueButton);
	}
	
	public void enterPassword(String password)
	{
		sendKeys(passwordField, password);
	}
	
	public void selectVariant(String variant)
	{
		WebDriverUtil.mouseHoverAndClick(driver, shopLink, "shopLink");
		String xpathExpression = "//ul[@role='menu']//span[text()='" + variant + "']";
		WebDriverUtil.mouseHoverAndClick(driver, driver.findElement(By.xpath(xpathExpression)), variant);
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
		click(proceedToCartButton);
	}
	
	public boolean isAuthenticationDivDisplayed()
	{
		scrollToObject(driver, authenticationDiv);
		return authenticationDiv.isDisplayed();
	}
	
	public void enterAuthenticationCode(String code)
	{
		char ch[] = code.toCharArray();
		for(int i=0; i<ch.length;i++)
		{
			int j=i+1;
			String xpathExpression = "//div[@class='form-group couponcode']/input[" + j + "]";
			driver.findElement(By.xpath(xpathExpression)).sendKeys(ch[i]+"");
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
}
