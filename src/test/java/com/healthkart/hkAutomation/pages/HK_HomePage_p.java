package com.healthkart.hkAutomation.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.healthkart.hkAutomation.property.PropertyHelper;
//import com.healthkart.hkAutomation.util.ExcelUtil;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;


public class HK_HomePage_p{
	
	public WebDriver driver;
		
	public HK_HomePage_p(WebDriver driver) {		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * home page link
	 */
	@FindBy(xpath = ".//*[@id='header']/div[1]/div/div/div[1]/a/img")
	private WebElement hkHome;
	@FindBy(xpath="//h2[contains(text(),'Trending In Whey Protein')]")
	private WebElement trendingWheyProteinWidget;
	@FindBy(xpath = "//h2[text()='Trending In Whey Protein']/ancestor::div[2]//div[@class='variant-name' or @class='variant-tile']/div[@class='product-title']/a/span")
	//h2[text()='Trending In Whey Protein']/ancestor::div[2]//div[@class='product-title']//span
	private WebElement homePageVariant;	
	
	public void gotoHomePage() {
		driver.get(GlobalVar.baseAppURL);    	
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
	
	public boolean verifyHomePageloaded() {
		
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
		
		return WebDriverUtil.isElementPresent(trendingWheyProteinWidget, driver, 20);
					
	}
	
	public void scrollToHomePageVariant() {
		if(WebDriverUtil.isElementPresent(trendingWheyProteinWidget, driver,20))
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+(trendingWheyProteinWidget.getLocation().y-10)+")");
	}
	
	public void clickHomePageVariant() {
		scrollToHomePageVariant();
		WebDriverUtil.click(driver, homePageVariant, "homePageVariant");
	}
	
	/**
	 * Home Page Search text.
	 */
	@FindBy(xpath = "//form[@id='globalSearch']/input")
	private WebElement searchInput;
	
	public void enterSearchText(String searchString) {
		if(WebDriverUtil.isElementPresent(searchInput, driver, 20)) {
			searchInput.clear();
			searchInput.sendKeys(searchString);
		}
	}
	
	/**
	 * Home Page Search - 'SUBMIT'
	 */
	@FindBy(xpath = "//div[@id='search']/span")
	//div[@id='search']//span[contains(text(),'Search')]
	private WebElement searchSubmit;
	
	public void submitSearchText() {
		WebDriverUtil.mouseHoverAndClick(driver, searchSubmit, "searchSubmit");
	}
	
	/**
	 * Home Page Widget - 'SHOP'
	 */
	@FindBy(xpath = "//h3[text()='Shop']")
	private WebElement shopWidget;
	
	public boolean isShopWidgetPresent() {
		return WebDriverUtil.isElementPresent(shopWidget, driver,20);
	}
	
	/**
	 * Home Page Widget - 'CONSULT'
	 */
	@FindBy(xpath = "//h3[text()='Consult']")
	private WebElement consultWidget;
	
	public boolean isConsultWidgetPresent() {
		return WebDriverUtil.isElementPresent(consultWidget, driver,20);
	}
	
	/**
	 * Home Page Widget - 'CONNECT'
	 */
	@FindBy(xpath = "//h3[text()='Connect']")
	private WebElement ConnectWidget;
	
	public boolean isConnectWidgetWidgetPresent() {
		return WebDriverUtil.isElementPresent(ConnectWidget, driver,20);
	}
	
	/**
	 * Home Page Widget - 'TRENDING NOW'
	 */
	@FindBy(xpath = "//h2[text()='Trending Now']")
	private WebElement trendingNowWidget;
	
	public boolean isTrendingNowWidgetPresent() {
		return WebDriverUtil.isElementPresent(trendingNowWidget, driver,20);
	}
	
	/**
	 * Home Page Widget - 'TRENDING IN WHEY PROTEIN'
	 */
	@FindBy(xpath = "//h2[text()='Trending In Whey Protein']")
	private WebElement trendingWheyWidget;
	
	public boolean isTrendingWheyWidgetPresent() {
		return WebDriverUtil.isElementPresent(trendingWheyWidget, driver,20);
	}
	
	/**
	 * Home Page Header - Goal -> Whey Protein
	 */
	@FindBy(xpath="//section[@class='hkheader-nav hidden-xs' or @class='header-menu']//div[@id='dropDownbox1' or @class='desktop-menu']/ul/li[1]//span")
	//section[@class='hkheader-nav hidden-xs']//div[@id='dropDownbox1']/ul/li[1]/span
	private WebElement headerGoal;
	
	@FindBy(xpath="//section[@class='hkheader-nav hidden-xs' or @class='header-menu']//div[@id='dropDownbox1' or @class='desktop-menu']//ul[@class='main-menu']//a[text()='Whey Protein']")
	//section[@class='hkheader-nav hidden-xs']//div[@id='dropDownbox1']//ul[@class='main-menu']//a[text()='Whey Protein']
	private WebElement headerGoalWheyProtein;
	
	public void visitGoalWheyProteinCatPage() {
		WebDriverUtil.mouseHoverAndClick(driver, headerGoal, "headerGoal");
		WebDriverUtil.mouseHoverAndClick(driver, headerGoalWheyProtein, "headerGoalWheyProtein");
	}

	/*if(WebDriverUtil.isElementPresent(headerGoal, driver)) {
			Actions action = new Actions(driver);	 
	        action.moveToElement(headerGoal).build().perform();
			headerGoal.click();
			if(WebDriverUtil.isElementClickable(headerGoalWheyProtein, driver))
				headerGoalWheyProtein.click();
			
			Actions action = new Actions(driver);	 
	        action.moveToElement(headerGoal).build().perform();
	        System.out.println("Goal : " + headerGoal.getText());
	        if(WebDriverUtil.isElementClickable(headerGoal, driver))
	        	headerGoal.click();
	        action.moveToElement(headerGoalWheyProtein).build().perform();
	        System.out.println("Whey : " + headerGoalWheyProtein.getText());
	        if(WebDriverUtil.isElementClickable(headerGoalWheyProtein, driver))
	        	headerGoalWheyProtein.click();*/

	
	/**
	 * Home Page Header - Goal -> MuscleBlaze.
	 */	
	@FindBy(xpath="//section[@class='hkheader-nav hidden-xs' or @class='header-menu']//div[@id='dropDownbox1' or @class='desktop-menu']//ul[@class='main-menu']//a[text()='MuscleBlaze']")
	//section[@class='hkheader-nav hidden-xs']//div[@id='dropDownbox1']//ul[@class='main-menu']//a[text()='MuscleBlaze']
	private WebElement headerGoalMuscleblaze;
	
	public void visitGoalMuscleblazeBrandPage() {
		WebDriverUtil.mouseHoverAndClick(driver, headerGoal, "headerGoal");
		WebDriverUtil.mouseHoverAndClick(driver, headerGoalMuscleblaze, "headerGoalMuscleblaze");
	}

		/*	if(WebDriverUtil.isElementPresent(headerGoal, driver)) {
			Actions action = new Actions(driver);	 
	        action.moveToElement(headerGoal).build().perform();
			headerGoal.click();
			if(WebDriverUtil.isElementClickable(headerGoalMuscleblaze, driver))
				headerGoalMuscleblaze.click();
			
			Actions action = new Actions(driver);	 
	        action.moveToElement(headerGoal).build().perform();
	        System.out.println("Goal : " + headerGoal.getText());
	        if(WebDriverUtil.isElementClickable(headerGoal, driver))
	        	headerGoal.click();
	        action.moveToElement(headerGoalMuscleblaze).build().perform();
	        System.out.println("Brand : " + headerGoalMuscleblaze.getText());
	        if(WebDriverUtil.isElementClickable(headerGoalMuscleblaze, driver))
	        	headerGoalMuscleblaze.click();	        	        
	    }*/
	
	/**
	 * Get cart counter from home page.
	 */
	@FindBy(xpath = "//span[@data-role='cart-counter']")
    private WebElement cartCounter;
	
	public String getCartCounter() {
		String cartCount = null;
		if(WebDriverUtil.isElementPresent(cartCounter, driver,20)) {
			cartCount = cartCounter.getText();
		}
		return cartCount;
	}
	
	/**
	 * Proceed To cart from home page.
	 */	
	@FindBy(linkText = "Proceed to Cart")
    private WebElement proceedToCart;
	
	public void clickProceedToCart() {
		WebDriverUtil.mouseHoverAndClick(driver, cartCounter, "cartCounter");
		WebDriverUtil.staticWait(2);
		WebDriverUtil.mouseHoverAndClick(driver, proceedToCart, "proceedToCart");
	}
	
	/**
	 * login.
	 */
	@FindBy(linkText = "Login")
    private WebElement login;
	
	public void clickLogin() {
		WebDriverUtil.click(driver, login, "login");
	}
	
	public boolean isUserLoggedOut() { // to verify if user is logged out.
		return WebDriverUtil.isElementPresent(login, driver, 20);
    }
	
	/**
	 * input mobile number
	 */
	
	@FindBy(xpath = ".//*[@id='email']")
	private WebElement mobileLoginInput;
	
	public void setMobileNumber(String mob) {
		if(WebDriverUtil.isElementPresent(mobileLoginInput, driver,20)) {
			mobileLoginInput.clear();
			mobileLoginInput.sendKeys(mob);
		}
	}
	
	/**
	 * input password
	 */
	@FindBy(xpath = "//*[@class='login-form' or @class='logInForm']//input[@name='password']")
	private WebElement mobilePasswordInput;
	
	public void setPassword(String pswd) {
		if(WebDriverUtil.isElementPresent(mobilePasswordInput, driver,20)) {
		    	mobilePasswordInput.clear();
		    	mobilePasswordInput.sendKeys(pswd);
		}
	}
	
	/**
	 * login via OTP.
	 */
	@FindBy(xpath = ".//*[@id='loginViaOTP']")
	private WebElement mobileLoginOtp;
	
	public void clickLoginViaOtp() {
		
		WebDriverUtil.click(driver, mobileLoginOtp, "mobileLoginOtp");
		
		/*if(WebDriverUtil.isElementPresent(mobileLoginOtp, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(mobileLoginOtp).build().perform();
	        if(WebDriverUtil.isElementClickable(mobileLoginOtp, driver))
	        	mobileLoginOtp.click();
		}*/
    }
	
	/**
	 * OTP sent meassage
	 */
	@FindBy(xpath = "//div[@class='otpHead']//h3[contains(@class,'loginVerifiedCase') and text()='Please enter the OTP sent on your number']")
	private WebElement OtpSentText;
	
	public void waitForOtpSentText() {
		WebDriverUtil.waitForElementToBeDisplayed(driver, 45, OtpSentText);
    }
	    
	/**
	 * input OTP.
	 */
    @FindBy(xpath = "//input[@id='otp']")
	private WebElement enterMobileOtp;
    
    @FindBy(xpath = "//input[@id='otpInput']") 
    //input[@id='otp']  (@name='otpLoginPopup' and contains(@class,'inputVal'))
	private WebElement enterMobileOtpReact;
    
    @FindBy(xpath = "//form[@class='forgotPasswordForm']//input[@name='otpLoginPopup']")
	private WebElement enterMobileOtpForgotPswdReact;	
    
    public void setMobileOtp(String otp) {
    	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    	if(WebDriverUtil.isElementPresent(enterMobileOtpReact, driver, 2))
    		enterMobileOtpReact.sendKeys(otp);
    	else if(WebDriverUtil.isElementPresent(enterMobileOtpForgotPswdReact, driver, 2))
    		enterMobileOtpForgotPswdReact.sendKeys(otp);
    	else if(WebDriverUtil.isElementPresent(enterMobileOtp, driver, 2))
    		enterMobileOtp.sendKeys(otp);
    	driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
    }
	
    /**
     * edit mobile number
     */
    @FindBy(xpath = ".//*[@id='editMobileNumber']/span")
    private WebElement editMobile;
    
    public void clickEditMobile() {
    	
    		WebDriverUtil.click(driver, editMobile, "editMobile");
    	
	    	/*if(WebDriverUtil.isElementPresent(editMobile, driver)) {
	    		Actions action = new Actions(driver);		 
	            action.moveToElement(editMobile).build().perform();
	            if(WebDriverUtil.isElementClickable(editMobile, driver))
	            	editMobile.click();
	    	}*/
    }
    
    /**
     * Resend OTP.
     */
    @FindBy(xpath = ".//*[@id='resendOtp']")
    private WebElement resendOtp;
    
    public void clickResendOtp() {
    	
    		WebDriverUtil.click(driver, resendOtp, "resendOtp");
    	
    	/*if(WebDriverUtil.isElementPresent(resendOtp, driver)) {
    		Actions action = new Actions(driver);		 
            action.moveToElement(resendOtp).build().perform();
            if(WebDriverUtil.isElementClickable(resendOtp, driver))
            	resendOtp.click();
    	}*/
    }
    
    /**
     * Submit OTP Done.
     */
    @FindBy(xpath = "//div[@id='enterOTP']/div/form/div[5]/input[contains(@class,'enterotpotp_done')]")
    private WebElement submitEnterOtpDone;
    @FindBy(xpath = "//div[@id='loginByOtp']/div/form/div[3]/input[contains(@class,'enterotpotp_done')]")
    private WebElement submitEnterOtpDoneReact;
    @FindBy(xpath = "//input[@id='forgotPasswordOTP_done']")
    private WebElement submitEnterOtpDoneForgotPswdReact;
    
    
    //*[@id='enterOTP']/div/form/div[5]/input
    //private WebElement submitEnterOtpDone;
    
    public void clickSubmitEnterOtpDone() {
    	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    	if(WebDriverUtil.isElementPresent(submitEnterOtpDone, driver, 5))
    		WebDriverUtil.click(driver, submitEnterOtpDone, "submitEnterOtpDone");
    	else if(WebDriverUtil.isElementPresent(submitEnterOtpDoneReact, driver, 5))
    		WebDriverUtil.click(driver, submitEnterOtpDoneReact, "submitEnterOtpDoneReact");
    	else if(WebDriverUtil.isElementPresent(submitEnterOtpDoneForgotPswdReact, driver, 5))
    		WebDriverUtil.click(driver, submitEnterOtpDoneForgotPswdReact, "submitEnterOtpDoneForgotPswdReact");
    	//WebDriverUtil.click(driver, submitEnterOtpDone, "submitEnterOtpDone");
    	driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
    }
    
    /**
     * login submit button.
     */
	@FindBy(xpath = ".//*[@class='login-form' or @id='logInForm']//input[@type='submit']")
	private WebElement loginSubmit;
	
	public void submitLogin() {
		
		WebDriverUtil.click(driver, loginSubmit, "loginSubmit");
		
		/*if(WebDriverUtil.isElementPresent(loginSubmit, driver)) {
			Actions action = new Actions(driver);		 
            action.moveToElement(loginSubmit).build().perform();
            if(WebDriverUtil.isElementClickable(loginSubmit, driver))
            	loginSubmit.click();
		}*/
	}
	
	/**
	 * forgot password.
	 */
	@FindBy(xpath = "//*[@class='forgotPass']")
	//*[@id='logInForm']/div[3]/div[2]/span
	private WebElement forgotPassword;
	
	public void clickForgotPassword() {
		
		WebDriverUtil.click(driver, forgotPassword, "forgotPassword");
		
		/*if(WebDriverUtil.isElementPresent(forgotPassword, driver)) {
			Actions action = new Actions(driver);		 
            action.moveToElement(forgotPassword).build().perform();
            if(WebDriverUtil.isElementClickable(forgotPassword, driver))
            	forgotPassword.click();
		}*/
	}
	
	/**
	 * Input forgot password mobile No.
	 */
	@FindBy(xpath = ".//div[@id='forgotPassword']//input[@id='userName']")
	private WebElement forgotPasswordMobInput;
	
	public void enterForgotPasswordMob(String mobile) {
		if(WebDriverUtil.isElementPresent(forgotPasswordMobInput, driver, 20))
			forgotPasswordMobInput.sendKeys(mobile);		
	}	
	
	/**
	 * Forgot Password continue done.
	 */
	@FindBy(xpath = "//*[@id='forgotPassword']//input[@value='Continue']")
	//*[@id='forgotPassword']/div/form/input
	private WebElement passwordResetContinueDone;
	
	public void clickPasswordResetContinueDone() {
		
		WebDriverUtil.click(driver, passwordResetContinueDone, "passwordResetContinueDone");
		
		/*if(WebDriverUtil.isElementPresent(passwordResetContinueDone, driver)) {
			Actions action = new Actions(driver);		 
            action.moveToElement(passwordResetContinueDone).build().perform();
            if(WebDriverUtil.isElementClickable(passwordResetContinueDone, driver))
            	passwordResetContinueDone.click();
		}*/		
	}
	
	/**
	 * enter mobile OTP.
	 */
	@FindBy(xpath = "//label[@for='otp' and text()='Enter OTP']")
	private WebElement enterOtpLabel;
	
	public void waitForEnterOtpLabel() {
		WebDriverUtil.waitForElementToBeDisplayed(driver, 10, enterOtpLabel);
    }
	
	/**
	 * Input New password.
	 */
	@FindBy(xpath = ".//*[@id='newPassword']")
	private WebElement newPasswordInput;
	@FindBy(xpath = "//div[@id='changePassword']//form[@class='forgotPasswordForm']//input[@name='number']")
	private WebElement newPasswordInputForgotPswdReact;
	
	public void enterNewPassword(String pswd) {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		if(WebDriverUtil.isElementPresent(newPasswordInput, driver,5))
			newPasswordInput.sendKeys(pswd);
		else if(WebDriverUtil.isElementPresent(newPasswordInputForgotPswdReact, driver,5))
			newPasswordInputForgotPswdReact.sendKeys(pswd);
		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
	}
	
	/**
	 * Confirm New password.
	 */
	@FindBy(xpath = ".//*[@id='confirmNewPassword']")
	private WebElement confirmNewPasswordInput;
	@FindBy(xpath = "//div[@id='changePassword']//form[@class='forgotPasswordForm']//input[@name='password']")
	private WebElement confirmNewPasswordInputForgotPswdReact;
	
	public void confirmNewPassword(String pswd) {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		if(WebDriverUtil.isElementPresent(confirmNewPasswordInput, driver,5))
			confirmNewPasswordInput.sendKeys(pswd);
		else if(WebDriverUtil.isElementPresent(confirmNewPasswordInputForgotPswdReact, driver,5))
			confirmNewPasswordInputForgotPswdReact.sendKeys(pswd);
		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
	}
	
	/**
	 * Reset Password confirmation done.
	 */
	@FindBy(xpath = ".//*[@id='passwordResetMailDiv']/div/form/input")
	private WebElement passwordResetSubmitDone;
	
	@FindBy(xpath = "//div[@id='changePassword']//form[@class='forgotPasswordForm']//input[@class='submit-btn']")
	private WebElement passwordResetSubmitDoneReact;
	
	public void clickPasswordResetSubmitDone() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		if(WebDriverUtil.isElementPresent(passwordResetSubmitDone, driver,5))
			WebDriverUtil.click(driver, passwordResetSubmitDone, "passwordResetSubmitDone");
		else if(WebDriverUtil.isElementPresent(passwordResetSubmitDoneReact, driver,5))
			WebDriverUtil.click(driver, passwordResetSubmitDoneReact, "passwordResetSubmitDoneReact");
		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
	}
	
	
	/**
	 * Google Login.
	 */
	@FindBy(xpath = "//form[@class='login-form' or @class='logInForm']/following-sibling::div[@class='loginIntegration']//div[@id='customBtnLogin']/span[text()='Google']")	
	//div[@id='tab-1-content']//div[@id='customBtnLogin']/span[text()='Google']
	private WebElement googleLoginBtn;
	
	public void clickGoogleLoginBtn() {
		WebDriverUtil.mouseHoverAndClick(driver, googleLoginBtn, "googleLoginBtn");
	}
	
	/**
	 * Facebook Login.
	 */
	@FindBy(xpath = "//form[@class='login-form' or @class='logInForm']/following-sibling::div[@class='loginIntegration']//div[@class='facebookDiv']/span[text()='Facebook']") 
	//div[@id='tab-1-content']//span[@class='fb']
	private WebElement facebookLoginBtn;
	
	public void clickFacebookLoginBtn() {
		WebDriverUtil.mouseHoverAndClick(driver, facebookLoginBtn, "facebookLoginBtn");
	}
	
	/**
	 * Retail manager Assume Login
	 */
	@FindBy(xpath = "//div/a[contains(text(),'Assume Login')]")
    private WebElement doAssumeLoginPOS;
	
	public void clickAssumeLoginPOS() {		
		WebDriverUtil.click(driver, doAssumeLoginPOS, "doAssumeLoginPOS");
	}
	
	@FindBy(xpath = "//input[@id='SPMobileId']")
    private WebElement enterAssumeLoginMob;
	
	public void enterAssumeLoginMobileNo(String mob) {		
		enterAssumeLoginMob.sendKeys(mob);
	}
	
	@FindBy(xpath = "//input[@id='SPOTPId']")
    private WebElement enterAssumeLoginMobOTP;
	
	public void enterAssumeLoginMobileNoOTP(String mob) {		
		enterAssumeLoginMobOTP.sendKeys(WebDriverUtil.dbActionsObj.getOtp(mob, 1));
	}
	
	@FindBy(xpath = "//input[@id='sendOtpAL']")
    private WebElement submitAssumeLoginForOtp;
	
	public void submitAssumeLogin() {		
		WebDriverUtil.click(driver, submitAssumeLoginForOtp, "submitAssumeLoginForOtp");
	}
	
	@FindBy(xpath = "//input[@id='verifyOtpAL']")
    private WebElement submitAssumeLoginFinal;
	
	public void submitAssumeLoginFinal() {		
		WebDriverUtil.click(driver, submitAssumeLoginFinal, "submitAssumeLoginFinal");
	}
	
	@FindBys(@FindBy(xpath="//div/a[contains(text(),'Release Assumed Login')]"))
	private WebElement releaseAssumeLoginLink;
	
	public void releaseAssumeLogin() {
		WebDriverUtil.click(driver, releaseAssumeLoginLink, "releaseAssumeLoginLink");
	}
	
	@FindBys(@FindBy(xpath="//div/a[contains(text(),'Assume Login')]"))
	private WebElement AssumeLoginLink;
	
	public boolean verifyAssumeLoginPresentForSM() {
		return WebDriverUtil.isElementPresent(AssumeLoginLink, driver, 5);
	}
	
	/**
	 * sign up.
	 */
	@FindBy(linkText = "Signup")
    private WebElement signup;
	
	public void clickSignUp() {
		
		WebDriverUtil.click(driver, signup, "signup");
		
		/*if(WebDriverUtil.isElementPresent(signup, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(signup).build().perform();
	        if(WebDriverUtil.isElementClickable(signup, driver))
	        	signup.click();
		}*/
	}
	
	/**
	 * Input mobile number for Sign up.
	 */
	@FindBy (xpath = ".//*[@id='contactNumber']")
	private WebElement inputSignupMob;
	
	public void enterSignupMob(String mob) {
		if(WebDriverUtil.isElementPresent(inputSignupMob, driver,20)) {
			inputSignupMob.clear();
			inputSignupMob.sendKeys(mob);
		}
	}
	
	/**
	 * Submit Sign up.
	 */
	@FindBy (xpath = ".//input[@value='Sign Up']")
	private WebElement submitSignupMob;
	
	public void clickSubmitSignUp() {
		
		WebDriverUtil.mouseHoverAndClick(driver, submitSignupMob, "submitSignupMob");
		
		/*if(WebDriverUtil.isElementPresent(submitSignupMob, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(submitSignupMob).build().perform();
	        if(WebDriverUtil.isElementClickable(submitSignupMob, driver))
	        	submitSignupMob.click();
		}*/
	}
	
	/**
	 * Edit Mobile while Sign up.
	 */
	@FindBy(xpath = ".//*[@id='editMobileNumber']/span")
	private WebElement editSignupMobNum;
	
	public void clickEditSignupMobNum() {
		
		WebDriverUtil.mouseHoverAndClick(driver, editSignupMobNum, "editSignupMobNum");
		
		/*if(WebDriverUtil.isElementPresent(editSignupMobNum, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(editSignupMobNum).build().perform();
	        if(WebDriverUtil.isElementClickable(editSignupMobNum, driver))
	        	editSignupMobNum.click();
		}*/
	}
	
	/**
	 * Enter OTP while Sign up.
	 */
	@FindBy(xpath = ".//*[@id='otp']")
	private WebElement enterSignupOtp;
	
	public void enterSignupOtp(String otp) {
		if(WebDriverUtil.isElementPresent(enterSignupOtp, driver,20)) {
			enterSignupOtp.clear();
			enterSignupOtp.sendKeys(otp);
		}
	}
	
	/**
	 * Resends OTP while Sign up.
	 */
	@FindBy(xpath = ".//*[@id='editMobileNumber']/span")
	private WebElement resendSignupOtp;
	
	public void clickResendSignupOtp() {
		
		WebDriverUtil.click(driver, resendSignupOtp, "resendSignupOtp");
		
		/*if(WebDriverUtil.isElementPresent(resendSignupOtp, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(resendSignupOtp).build().perform();
	        if(WebDriverUtil.isElementClickable(resendSignupOtp, driver))
	        	resendSignupOtp.click();
		}*/	
	}
	
	/**
	 * Set Sign up password.
	 */
	@FindBy(xpath = "//form[@class='OTPForm']//input[(@id='password' or @name='password') and @tabindex='0']")
	private WebElement enterSignupPswd;
	
	public void enterSignupPswd(String pswd) {
		if(WebDriverUtil.isElementPresent(enterSignupPswd, driver,20)) {
			enterSignupPswd.clear();
			enterSignupPswd.sendKeys(pswd);
		}
	}	
	
	/**
	 * Sign Up Done.
	 */
	@FindBy(xpath = ".//input[@class='submit-btn enterotpotp_done'][@value='Done']")
	private WebElement submitSignupDone;
	
	public void clickSubmitSignupDone() {
		
		WebDriverUtil.click(driver, submitSignupDone, "submitSignupDone");
		
		/*if(WebDriverUtil.isElementPresent(submitSignupDone, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(submitSignupDone).build().perform();
	        if(WebDriverUtil.isElementClickable(submitSignupDone, driver))
	        	submitSignupDone.click();
		}*/
	}
	
	/**
	 * visible user name
	 */
	@FindBy(xpath = "//div[@class='accoutn-dropdown-section']//span[contains(@class,'header-icons-text')]")
	protected WebElement username;
	
	public String getLoggedInUser() {
		String usr=null;
		if(WebDriverUtil.isElementPresent(username, driver,20)) 
			usr = username.getText();
		return usr;
	}
	
	/**
	 * My Profile.
	 */

	@FindBy(xpath = "//div[@class='accoutn-dropdown-section']")
    private WebElement acctDropdownSection;
	@FindBy(xpath = "//div[@class='accoutn-dropdown-section']//li[@class='profile_account_header']/a")
    private WebElement profile;
	
	public void openMyProfile() {
		WebDriverUtil.mouseHoverAndClick(driver, username, "username");
		WebDriverUtil.click(driver, profile, "profile");
		
		/*if(WebDriverUtil.isElementPresent(username, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(username).build().perform();
		    if(WebDriverUtil.isElementClickable(profile, driver))
		    	profile.click();
		}*/
    }	
	
	/**
	 * logout.
	 */
	@FindBy(linkText = "Logout")
    private WebElement logout;
	
	public void logout() {
		WebDriverUtil.mouseHoverAndClick(driver, username, "username");
		WebDriverUtil.click(driver, logout, "logout");
		
		/*if(WebDriverUtil.isElementPresent(username, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(username).build().perform();
		    if(WebDriverUtil.isElementClickable(logout, driver))
		        logout.click();
		}*/
    }
	
	/**
	 * Home Page widgets.
	 */
	@FindBy(xpath = "//h2[contains(text(),'Trending Now')]")
    private WebElement trendingNowText;
	@FindBy(xpath = "//h2[contains(text(),'Kick-start your fitness journey')]")
    private WebElement kickStartText;
	@FindBy(xpath = "//li/a[contains(@class,'shop_topbar_home_guest')]")
    private WebElement shopWidgetDiv;
	@FindBy(xpath = "//li/a[contains(@class,'consult_topbar_home_guest')]")
    private WebElement consultWidgetDiv;
	@FindBy(xpath = "//li/a[contains(@class,'connect_topbar_home_guest')]")
    private WebElement connectWidgetDiv;
	@FindBy(xpath = "//div[contains(text(),'Get an expert view on health and fitness queries')]")
    private WebElement askAnExpertWidgetText;
	@FindBy(xpath = "//div[@class='postquestion-bottom']")
    private WebElement QnASection;
	@FindBy(xpath = "//h2[contains(text(),'Shop by Category')]")
    private WebElement ShopByCategoryText;
	@FindBy(xpath = "//div[contains(@class,'about-hk')]")
    private WebElement disclaimerDiv;
	
	public boolean verifyIAWidgetsOnHomePage() {		
		return WebDriverUtil.isElementPresent(trendingNowText, driver, 20);
	}

	public boolean verifyShopConsultConnectWidgetsOnHomePage() 
	{
		boolean flag = false;
		if(WebDriverUtil.isElementPresent(kickStartText, driver, 20))
		{
			if(WebDriverUtil.isElementPresent(shopWidgetDiv, driver,20) && WebDriverUtil.isElementPresent(consultWidgetDiv, driver,20) 
					&& WebDriverUtil.isElementPresent(connectWidgetDiv, driver,20))
			{
				System.out.println("Shop | Consult | Connect widgets are present on home page..");
				flag = true;
			}
		}
		return flag;
	}
	
	public boolean verifyTrendingWheyProteinWidgetOnHomePage() {
		return WebDriverUtil.isElementPresent(trendingWheyProteinWidget, driver,20);
	}
	
	public boolean verifyAskAnExpertWidgetOnHomePage() {
		return WebDriverUtil.isElementPresent(askAnExpertWidgetText, driver,20);
	}
	
	public boolean verifyShopByCategoryWidgetOnHomePage() {
		WebDriverUtil.scrollToObject(driver, QnASection);
		WebDriverUtil.staticWait(2);
		return WebDriverUtil.isElementPresent(ShopByCategoryText, driver,20);
	}
	
	public boolean verifyDisclaimerOnHomePage() {
		return WebDriverUtil.isElementPresent(disclaimerDiv, driver,20);
	}
	
	/**
	 * Search results
	 * @return
	 */
	@FindBy(xpath="//div[@class='hero' or @id='hk-search-box-result']")
	//div[@class='hero']
	private WebElement SrchOverlay;
	@FindBys(@FindBy(xpath="//div[contains(@class,'recent-srch-items')]"))
	private List<WebElement> recentSrchList;
	@FindBys(@FindBy(xpath="//div[contains(@class,'trnding-items')]"))
	private List<WebElement> trendingSrchList;
	@FindBys(@FindBy(xpath="//div[@class='trnd-prod']"))
	private List<WebElement> trendingSrchPrdctList;
	
	public void displaySrchOverlay(){
		WebDriverUtil.staticWait(2);
		WebDriverUtil.mouseHoverAndClick(driver, searchInput, "searchInput");
		searchInput.clear();
		searchInput.sendKeys(" ");
		WebDriverUtil.staticWait(2);
		
	/*	if(WebDriverUtil.isElementPresent(searchInput, driver)) {
			
			Actions action = new Actions(driver);		 
	        action.moveToElement(searchInput).build().perform();

		if(WebDriverUtil.isElementPresent(searchInput, driver,30)) {

			searchInput.clear();
			searchInput.click();
			searchInput.sendKeys("");
		} */
	}
	
	public boolean verifySrchOverlayDisplayed() {
		
		return WebDriverUtil.isElementPresent(SrchOverlay, driver,20);
		/*boolean flag = false;
		if(WebDriverUtil.isElementPresent(SrchOverlay, driver,30)) {
			flag = true;
		}
		return flag; */
	}
	
	public int getRecentSearchCount() {
		int count=0;
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		count = recentSrchList.size();
		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		return count;
	}
	
	public int getTrendingSearchCount() {
		int count=0;
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		for(WebElement element : trendingSrchList) {
			if(WebDriverUtil.isElementPresent(element, driver,20)) {
				count++;
			}
		}
		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		return count;
	}
	
	public int getTrendingSearchProductCount() {
		int count=0;
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		for(WebElement element : trendingSrchPrdctList) {
			if(element.isDisplayed()) {
				count++;
			}
		}
		driver.manage().timeouts().implicitlyWait(GlobalVar.waitTime, TimeUnit.SECONDS);
		return count;
	}
	
	@FindBy(xpath="//a[text()='My Wishlist']")
	private WebElement myWishlist;
	
	public void clickMyWishlistButton()
	{
		WebDriverUtil.mouseHoverAndClick(driver, username, "username");
		WebDriverUtil.click(driver, myWishlist, "My Wishlist");
	}
	
	@FindBy(xpath="//a[text()='Remove']")
	private WebElement removeWishlistButton;
	
	public void clickRemoveWishlistItem()
	{
		WebDriverUtil.click(driver, removeWishlistButton, "Remove wishlist button");
	}
	
	@FindBy(xpath = "//a[text()='Continue Shopping']")
	private WebElement continueToShopping;
	
	public boolean backToShoppingBtnPresent() {
		return (WebDriverUtil.isElementClickable(continueToShopping, driver,2));
	}
	
	public void clickBackToShopping() {
		WebDriverUtil.click(driver, continueToShopping, "continueToShopping");
		
		/*if(WebDriverUtil.isElementPresent(backToShopping, driver)) {
			Actions action = new Actions(driver);		 
	        action.moveToElement(backToShopping).build().perform();
	        if(WebDriverUtil.isElementClickable(backToShopping, driver))
	        	backToShopping.click();
		}*/
	}
	
	@FindBy(xpath="//button[text()='Add to Cart']")
	private WebElement addToCartFromWishlist;
	
	public void clickAddToCartfromWishlist()
	{
		WebDriverUtil.click(driver, addToCartFromWishlist, "Add to cart from Wishlist");
	}
	
	@FindBy(xpath="//button[text()='Go To Cart']")
	private WebElement goToCart;
	
	public void clickGoToCartFromWishlist()
	{
		WebDriverUtil.click(driver, goToCart, "Go To Cart");
	}
	
}
