package com.healthkart.hkAutomation.business;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.healthkart.hkAutomation.jdbc.GenericDbActions;
import com.healthkart.hkAutomation.pages.HK_HomePage_p;
import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.WebDriverUtil;

public class HK_HomePage_b extends HK_HomePage_p {
	
	public GenericDbActions dbActionsObj = new GenericDbActions();

	public HK_HomePage_b(WebDriver driver) {
		super(driver);
	}
	/**
	 * Login with mobile number and password.
	 * @param mob
	 * @param pswd
	 */
	public void performLoginWithMobilePassword(String mob,String pswd) {
		clickLogin();
		setMobileNumber(mob);
		setPassword(pswd);
		submitLogin();				
    }
	
	/**
	 * Verify if user is logged in successfully.
	 * @param username
	 * @return
	 */
	public boolean verifyUserLogin() {
		String logged_in_user = getLoggedInUser();
		System.out.println("logged in user name :" + logged_in_user);
		if(logged_in_user == null) {
			return false;
		} 
		return logged_in_user.contains("Hi");
	}
	
	/**
	 * Verify if user is signed-up successfully.
	 * @param signup_username
	 * @return
	 */
	
	public boolean verifyUserSignup() {
		WebDriverUtil.waitForElementToBeDisplayed(driver, 120, username);
		String logged_in_user = getLoggedInUser();
		System.out.println("logged in user name :" + logged_in_user);
		if(logged_in_user == null) {
			return false;
		} 
		return logged_in_user.contains("Hi");
	}
	
	/**
	 * Do user logout.
	 */
	public void userLogout() {
		//gotoHomePage();
		if(verifyHomePageloaded())
			logout();
		else {
			System.out.println("Home page not loaded successfully..");
		}
	}
	
	/**
	 * Verify If user is logged out successfully.
	 * @return
	 */
	public boolean verifyUserLogout() {
		WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);
		return isUserLoggedOut();			
	}
	
	/**
	 * Login with mobile number and OTP.
	 * @param mob
	 * @throws InterruptedException 
	 */
	public void performLoginWithMobileOtp(String mob) {
		clickLogin();
		setMobileNumber(mob);
		clickLoginViaOtp();
		waitForOtpSentText();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String otp = dbActionsObj.getOtp(mob,1);
		setMobileOtp(otp);
		clickSubmitEnterOtpDone();
    }
	
	/**
	 * Login with Google account.
	 * @throws InterruptedException
	 */
	public void performLoginWithGoogle(String gmailId, String gmailPswd) {
		clickLogin();
		String hkWinHndl = driver.getWindowHandle();
		
		WebDriverUtil.staticWait(4);		
		clickGoogleLoginBtn();		
		
		if(WebDriverUtil.waitForWindowCount(driver, 2)) {
		
			for (String allHandles : driver.getWindowHandles()) {
				driver.switchTo().window(allHandles);
				System.out.println("current window title :" + driver.getTitle());
				if(driver.getTitle().contains("Sign in")) {
					System.out.println("Switched to Google window..");
					WebElement gmailUserInpt = driver.findElement(By.xpath("//input[@id='identifierId']"));
					gmailUserInpt.clear();
					gmailUserInpt.sendKeys(gmailId);
					
					WebElement gmailIdentifierNext = driver.findElement(By.xpath("//div[@id='identifierNext']"));
					gmailIdentifierNext.click();
							
					WebDriverUtil.staticWait(5);
					
					WebElement gmailPswdInpt = driver.findElement(By.xpath("//input[@name='password']"));
					gmailPswdInpt.clear();
					gmailPswdInpt.sendKeys(gmailPswd);
					
					WebElement gmailPswdNext = driver.findElement(By.xpath(".//*[@id='passwordNext']"));
					gmailPswdNext.click();
					break;
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
	
	/**
	 * Login with Facebook account.
	 */
    public void performLoginWithFacebook(String fbMailId, String fbPswd) {
    	clickLogin();
    	String hkWinHndl = driver.getWindowHandle(); // current window handle.    	
    	WebDriverUtil.staticWait(4);    	
		clickFacebookLoginBtn();
		
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
					break;
				}
				else if(driver.getTitle().contains("Error")) {
					System.out.println("current window title :" + driver.getTitle());
					driver.close();
					break;
				}
			}			
		}
		WebDriverUtil.staticWait(10);
		driver.switchTo().window(hkWinHndl);
		System.out.println("current window title :" + driver.getTitle());
	}
    
    /**
     * Mobile Sign up.
     */
    public void performSignupWithMobileOtpAndPswd(String pswd){
    	clickSignUp();
    	String mob = "12345"+WebDriverUtil.getRandomNumberInRange(11111, 99999);
    	
    	while(dbActionsObj.verifyIfSignUpMobileExists(mob,1) != null) {
    		mob = "12345"+WebDriverUtil.getRandomNumberInRange(11111, 99999);    		
    	}   	
    	
    	System.out.println(mob);
    	enterSignupMob(mob);
    	clickSubmitSignUp();
    	WebDriverUtil.staticWait(5);
		String otp = dbActionsObj.getOtp(mob,1);
		enterSignupOtp(otp);
		enterSignupPswd(pswd);
		clickSubmitSignupDone();
    }
    
    /**
     * Test Forgot Password with OTP.
     * @throws InterruptedException 
     */
    public void performForgotPasswordWithOTP(String mob, String pswd){
    	clickLogin();
    	clickForgotPassword();
    	enterForgotPasswordMob(mob);
    	clickPasswordResetContinueDone();
    	//waitForEnterOtpLabel();
    		try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		String otp = dbActionsObj.getOtp(mob,1);
		setMobileOtp(otp);
		clickSubmitEnterOtpDone();
		enterNewPassword(pswd);
		confirmNewPassword(pswd);
		clickPasswordResetSubmitDone();
		setMobileNumber(mob);
		setPassword(pswd);
		submitLogin();		
    }
    
    /**
     * Retail Manager Assume Login for Retail Customer. 
     */
    public void performAssumeLoginForStoreManager(String custType, String mbNo) {
    	String mob=null;
    	if("New".equalsIgnoreCase(custType)) {
    		mob = "12345"+WebDriverUtil.getRandomNumberInRange(11111, 99999);
        	
        	while(dbActionsObj.verifyIfSignUpMobileExists(mob,1) != null) {
        		mob = "12345"+WebDriverUtil.getRandomNumberInRange(11111, 99999);    		
        	}  
        }else if("Existing".equalsIgnoreCase(custType)) {
        	mob = mbNo;
        }
    	
    	clickAssumeLoginPOS();
    	enterAssumeLoginMobileNo(mob);
    	submitAssumeLogin();
    	WebDriverUtil.staticWait(3);
    	enterAssumeLoginMobileNoOTP(mob);
    	submitAssumeLoginFinal();
    	
	}
    
    /**
     * Proceed To cart from home page if cart counter >0.
     */
    public void proceedToCart() {
    	clickProceedToCart();
    }
    
    /**
     * Visit Whey Protein category page.
     */
    public void visitCategoryPage() {
    	visitGoalWheyProteinCatPage();    	
    }
    
    /**
     * Visit Whey Muscleblaze Brand page.
     */
    public void visitBrandPage() {
    	visitGoalMuscleblazeBrandPage();    	
    }
    
    /**
     * Visit Whey Search page.
     */
    public void visitSearchPage(String srchQry) {
    	enterSearchText(srchQry);
    	submitSearchText();
    }
    
    public int getCartCountForUser() {
    	String cnt = getCartCounter();
    	if(cnt != null) {
    		int count = Integer.parseInt(cnt);
        	return count;
    	}
    	else {
    		System.out.println("cart count returned as NULL..");
    		return 1;
    	}    	    	
    }
    
    public void makeSearchOverlayVisible() {
    	displaySrchOverlay();
    }
    
    public boolean verifyRecentSrchCount() {
    	boolean flag = false;
    	int cnt = getRecentSearchCount();
    	if(cnt<=5) {
    		flag = true;
    	}
    	System.out.println("recent search count found as : "+cnt);
    	return flag;
    }
    
    public boolean verifyTrendingSrchCount() {
    	boolean flag = false;
    	int cnt = getTrendingSearchCount();
    	if(cnt == 10) {
    		flag = true;
    	}
    	System.out.println("trending search count found as : "+cnt);
    	return flag;
    }
    
    public boolean verifyTrendingSrchPrdctCount() {
    	boolean flag = false;
    	int cnt = getTrendingSearchProductCount();
    	if(cnt >= 4) {
    		flag = true;
    	}
    	System.out.println("trending search product count found as : "+cnt);
    	return flag;
    }
    
    public boolean verifyIAWidget(){
		return verifyIAWidgetsOnHomePage();
	}
    
	public boolean verifyShopConsultConnectWidgets() {
		return verifyShopConsultConnectWidgetsOnHomePage();
	}
	public boolean verifyTrendingWheyProteinWidget() {
		WebDriverUtil.staticWait(5);
		return verifyTrendingWheyProteinWidgetOnHomePage();
	}
	public boolean verifyAskAnExpertWidget() {
		return verifyAskAnExpertWidgetOnHomePage();
	}
	public boolean verifyShopByCategoryWidget() {
		return verifyShopByCategoryWidgetOnHomePage();
	}
	public boolean verifyDisclaimer() {
		return verifyDisclaimerOnHomePage();
	}
	public String getOmniChnlVariantId(String storeName) {
		return dbActionsObj.getOmniChnlHKVariantId(storeName);
	}
	public void openMyWishlist() 
	{
		clickMyWishlistButton();
	}
	public void clearWishListMenuAndContinueShopping() 
	{
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int counter = 0;
		while(!backToShoppingBtnPresent() || counter==5) {
			counter++;
//			if(verifyCartPageLoaded())
			WebDriverUtil.staticWait(2);
			if(GlobalVar.jenkinsEnvironment==null)        // Handle notify visitor overlay [For PROD ENV only.]
			{
				if("prod".equalsIgnoreCase(PropertyHelper.readProperty("env"))) {				
					WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);			
				}
			}
			else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
			{
				WebDriverUtil.clickCloseNotifyVisitorOverlay(driver);
			}
				clickRemoveWishlistItem();
				
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	public void addToCartFromWishlist() 
	{
		clickAddToCartfromWishlist();	
	}
	public void goToCartFromWishlist() 
	{
		clickGoToCartFromWishlist();
	}
}
