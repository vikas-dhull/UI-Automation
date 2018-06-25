package com.healthkart.hkAutomation.regression;


import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
//import com.healthkart.hkAutomation.util.ExcelUtil;
import com.healthkart.hkAutomation.util.WebDriverUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_TestLoginFromGuestPage extends ExtentReportingBaseUtil {
	
	private int cartCount;
	
	/**
	 * Test Login with Mobile number and password.
	 */
	@Test(priority = 1, enabled=true)
	public void testLoginWithMobilePasswordFromGuestPage() throws TimeoutException{	
		System.out.println("## LOGIN TEST STARTS FROM GUEST PAGE with mobile and password..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM GUEST PAGE with mobile and password..");
		/*
		 * This is to make user's cart empty before Guest Login.
		 */
		homeB.performLoginWithMobilePassword(testdata.get("Login_User_Mobile"), testdata.get("Login_User_Mobile_Password"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password to check user's cart empty..??");
		cartCount = homeB.getCartCountForUser();
		if(cartCount != 0) {
			test.log(LogStatus.INFO, "There are items in user's cart..");
			homeB.proceedToCart();
			Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
			test.log(LogStatus.INFO, "cart page loaded..");
			cartB.makeCartEmpty();
			cartB.clickBackToShopping();
			Assert.assertTrue(homeB.verifyHomePageloaded(), "home page not loaded..");
			test.log(LogStatus.INFO, "user cart is empty now..");
		}else {
			test.log(LogStatus.INFO, "User cart is found empty..");
		}
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User successfully logged out..!!");
		WebDriverUtil.staticWait(2);
		/*
		 * Guest Login flow starts.
		 */	
		homeB.visitCategoryPage();
		Assert.assertTrue(catLsB.verifyCatPageLoaded(), "Fail to load Category page..");
		test.log(LogStatus.INFO, "Category page loaded..");
		//catLsB.handleCatPageHltr();
		test.log(LogStatus.INFO, "halter handled on Category page..");
		catLsB.clickfirstPVLink();
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpB.addVariantToCart();
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		cartB.clickProceedToCheckout();
		Assert.assertTrue(guestLoginB.verifyGuestLoginPageLoaded(), "Fail to load Guest Login page..");
		test.log(LogStatus.INFO, "Guest login page loaded..");
		guestLoginB.performLoginWithMobilePassword(testdata.get("Login_User_Mobile"), testdata.get("Login_User_Mobile_Password"));
		Assert.assertTrue(addB.verifyAddressPageLoaded(), "Fail to load select address page..");	
		test.log(LogStatus.PASS, "User successfully logged in with mobile and password from Guest page..!!");
		/*
		 * Logout after Guest Login.
		 
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User logged out successfully..!!");*/
	}
	
	/**
	 * Test Login with Mobile number and otp.
	 */
	@Test(priority = 2, enabled=true)
	public void testLoginWithMobileOtpFromGuestPage() {	
		System.out.println("## LOGIN TEST STARTS FROM GUEST PAGE with mobile and otp..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM GUEST PAGE with mobile and otp..");
		/*
		 * This is to make user's cart empty before Guest Login.
		 */
		homeB.performLoginWithMobilePassword(testdata.get("Login_User_Mobile"), testdata.get("Login_User_Mobile_Password"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and passwword to check user's cart empty..??");
		cartCount = homeB.getCartCountForUser();
		if(cartCount != 0) {
			test.log(LogStatus.INFO, "There are items in user's cart..");
			homeB.proceedToCart();
			Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
			test.log(LogStatus.INFO, "cart page loaded..");
			cartB.makeCartEmpty();
			cartB.clickBackToShopping();
			Assert.assertTrue(homeB.verifyHomePageloaded(), "home page not loaded..");
			test.log(LogStatus.INFO, "user cart is empty now..");
		}else {
			test.log(LogStatus.INFO, "User cart is found empty..");
		}
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User successfully logged out..!!");
		WebDriverUtil.staticWait(2);
		
		/*
		 * Guest Login flow starts.
		 */	
			
		homeB.visitCategoryPage();
		Assert.assertTrue(catLsB.verifyCatPageLoaded(), "Fail to load Category page..");
		test.log(LogStatus.INFO, "Category page loaded..");
		//catLsB.handleCatPageHltr();	
		test.log(LogStatus.INFO, "halter handled on Category page..");
		catLsB.clickfirstPVLink();
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpB.addVariantToCart();
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		cartB.clickProceedToCheckout();
		Assert.assertTrue(guestLoginB.verifyGuestLoginPageLoaded(), "Fail to load Guest Login page..");
		test.log(LogStatus.INFO, "Guest login page loaded..");
		guestLoginB.performLoginWithMobileOtp(testdata.get("Login_User_Mobile"));
		Assert.assertTrue(addB.verifyAddressPageLoaded(), "Fail to load select address page..");	
		test.log(LogStatus.PASS, "User successfully logged in with mobile and otp from Guest page..!!");
		/*
		 * Logout after Guest Login.
		 	
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User logged out successfully..!!");*/
	}
	
	/**
	 * Test Login with Google.
	 */
	@Test(priority = 3, enabled=true)
	public void testLoginWithGoogleFromGuestPage() {	
		System.out.println("## LOGIN TEST STARTS FROM GUEST PAGE with Google Account..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM GUEST PAGE with Google Account..");
		/*
		 * This is to make user's cart empty before Guest Login.
		 */

		homeB.performLoginWithMobileOtp(testdata.get("Google_User_Mobile"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and passwword to check user's cart empty..??");
		cartCount = homeB.getCartCountForUser();
		if(cartCount != 0) {
			test.log(LogStatus.INFO, "There are items in user's cart..");
			homeB.proceedToCart();
			Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
			test.log(LogStatus.INFO, "cart page loaded..");
			cartB.makeCartEmpty();
			cartB.clickBackToShopping();
			Assert.assertTrue(homeB.verifyHomePageloaded(), "home page not loaded..");
			test.log(LogStatus.INFO, "user cart is empty now..");
		}else {
			test.log(LogStatus.INFO, "User cart is found empty..");
		}
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User successfully logged out..!!");
		WebDriverUtil.staticWait(2);
	
		/*
		 * Guest Login flow starts.
		 */	
		
		homeB.visitCategoryPage();
		Assert.assertTrue(catLsB.verifyCatPageLoaded(), "Fail to load Category page..");
		test.log(LogStatus.INFO, "Category page loaded..");
		//catLsB.handleCatPageHltr();	
		test.log(LogStatus.INFO, "halter handled on Category page..");
		catLsB.clickfirstPVLink();
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpB.addVariantToCart();
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		cartB.clickProceedToCheckout();
		Assert.assertTrue(guestLoginB.verifyGuestLoginPageLoaded(), "Fail to load Guest Login page..");
		test.log(LogStatus.INFO, "Guest login page loaded..");
		guestLoginB.performLoginWithGoogle(testdata.get("Google_Login_Email_Id"), testdata.get("Google_Login_Password"));
		Assert.assertTrue(addB.verifyAddressPageLoaded(), "Fail to load select address page..");	
		test.log(LogStatus.PASS, "User successfully logged in with Google Account from Guest page..!!");
		/*
		 * Logout after Guest Login.
		 	
		
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User logged out successfully..!!");*/
	}
	
	/**
	 * Test Login with Facebook.
	 */
	@Test(priority = 4, enabled=true)
	public void testLoginWithFacebookFromGuestPage(){	
		System.out.println("## LOGIN TEST STARTS FROM GUEST PAGE with Facebook Account..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM GUEST PAGE with Facebook Account..");
		/*
		 * This is to make user's cart empty before Guest Login.
		 */
		homeB.performLoginWithMobileOtp(testdata.get("Facebook_User_Mobile"));
		Assert.assertTrue(homeB.verifyUserLogin(), "User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and passwword to check user's cart empty..??");
		cartCount = homeB.getCartCountForUser();
		if(cartCount != 0) {
			test.log(LogStatus.INFO, "There are items in user's cart..");
			homeB.proceedToCart();
			Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
			test.log(LogStatus.INFO, "cart page loaded..");
			cartB.makeCartEmpty();
			cartB.clickBackToShopping();
			Assert.assertTrue(homeB.verifyHomePageloaded(), "home page not loaded..");
			test.log(LogStatus.INFO, "user cart is empty now..");
		}else {
			test.log(LogStatus.INFO, "User cart is found empty..");
		}
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User successfully logged out..!!");
		WebDriverUtil.staticWait(2);
		
		/*
		 * Guest Login flow starts.
		 */
		
		WebDriverUtil.staticWait(5);
		homeB.visitCategoryPage();
		Assert.assertTrue(catLsB.verifyCatPageLoaded(), "Fail to load Category page..");
		test.log(LogStatus.INFO, "Category page loaded..");
		//catLsB.handleCatPageHltr();	
		test.log(LogStatus.INFO, "halter handled on Category page..");
		catLsB.clickfirstPVLink();
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpB.addVariantToCart();
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		cartB.clickProceedToCheckout();
		Assert.assertTrue(guestLoginB.verifyGuestLoginPageLoaded(), "Fail to load Guest Login page..");
		test.log(LogStatus.INFO, "Guest login page loaded..");
		guestLoginB.performLoginWithFacebook(testdata.get("Facebook_Login_EmailId"), testdata.get("Facebook_Login_Password"));
		Assert.assertTrue(addB.verifyAddressPageLoaded(), "Fail to load select address page..");	
		test.log(LogStatus.PASS, "User successfully logged in with Facebook Account from Guest page..!!");
		/*
		 * Logout after Guest Login.
		 			
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User logged out successfully..!!");*/
	}
	
	/**
	 * Test Forgot Password with Mobile number and OTP.
	 
	@Test(priority = 5, enabled = false)
	public void testForgotPasswordOtp(){	
		System.out.println("## FORGOT PASSWORD TEST STARTS FROM GUEST LOGIN PAGE with OTP..");
		test.log(LogStatus.INFO, "FORGOT PASSWORD TEST STARTS FROM GUEST LOGIN PAGE with OTP..");
		/*
		 * This is to make user's cart empty before Guest Login.
		 */
	/*	homeB.performLoginWithMobilePassword(ExcelUtil.getMobileNumber(), ExcelUtil.getPassword());
		Assert.assertTrue(homeB.verifyUserLogin(ExcelUtil.getUserName()), "User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password to check user's cart empty..??");
		cartCount = homeB.getCartCountForUser();
		if(cartCount != 0) {
			test.log(LogStatus.INFO, "There are items in user's cart..");
			homeB.proceedToCart();
			Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
			test.log(LogStatus.INFO, "cart page loaded..");
			cartB.makeCartEmpty();
			cartB.clickBackToShopping();
			Assert.assertTrue(homeB.verifyHomePageloaded(), "home page not loaded..");
			test.log(LogStatus.INFO, "user cart is empty now..");
		}else {
			test.log(LogStatus.INFO, "User cart is found empty..");
		}
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User successfully logged out..!!");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		/*
		 * Guest Login flow starts.
		 */
	/*	homeB.visitCategoryPage();
		Assert.assertTrue(catLsB.verifyCatPageLoaded(), "Fail to load Category page..");
		test.log(LogStatus.INFO, "Category page loaded..");
		catLsB.handleCatPageHltr();	
		test.log(LogStatus.INFO, "halter handled on Category page..");
		catLsB.clickfirstPVLink();
		Assert.assertTrue(pdpB.verifyPDPPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpB.addVariantToCart();
		Assert.assertTrue(cartB.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		cartB.clickProceedToCheckout();
		Assert.assertTrue(guestLoginB.verifyGuestLoginPageLoaded(), "Fail to load Guest Login page..");
		test.log(LogStatus.INFO, "Guest login page loaded..");
		guestLoginB.performForgotPasswordOnGuestPage(ExcelUtil.getMobileNumber(), ExcelUtil.getPassword());
		Assert.assertTrue(addB.verifyAddressPageLoaded(), "Fail to load select address page..");	
		test.log(LogStatus.PASS, "User successfully logged in with Facebook Account from Guest page..!!");
		/*
		 * Logout after Guest Login.
		 			
		homeB.userLogout();
		Assert.assertTrue(homeB.verifyUserLogout(),"User failed to logout");
		test.log(LogStatus.INFO, "User logged out successfully..!!"); 
	}*/
	
}
