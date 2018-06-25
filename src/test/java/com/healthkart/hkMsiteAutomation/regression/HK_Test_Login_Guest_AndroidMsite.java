package com.healthkart.hkMsiteAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HK_Test_Login_Guest_AndroidMsite extends ExtentReportingBaseUtil{
	
	private int cartCount;
	
	/**
	 * Test Login with Mobile number and password.
	 */	
	@Test(priority=1,enabled=true)
	public void testLoginWithMobilePassword()
	{	
		/*
		 * Empty User cart before Guest Login.
		 */
		System.out.println("## LOGIN TEST STARTS FROM GUEST PAGE with mobile and password..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM GUEST PAGE with mobile and password..");
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performLoginWithMobilePassword(testdata.get("Login_User_Mobile_Msite")
				, testdata.get("Login_User_Mobile_Password_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and password..");
		cartCount=homeStep.getCartCountForUser();
		if(cartCount != 0) {
			test.log(LogStatus.INFO, "There are items in user's cart..");
			homeStep.proceedToCart();
			Assert.assertTrue(cartStep.verifyCartPageLoaded(), "Fail to load Cart page..");
			test.log(LogStatus.INFO, "cart page loaded..");
			cartStep.performMakeCartEmptyForUser();
			cartStep.performAddItems();
			Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "home page not loaded..");
			test.log(LogStatus.INFO, "user cart is empty now..");
		}else {
			test.log(LogStatus.INFO, "User cart is found empty..");
		}
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");
		comnFunc.staticWait(5);
		/*
		 * Guest Login flow starts.
		 */
		homeStep.goToFirstPVWheyProteinPDP();
		Assert.assertTrue(pdpStep.verifyPdpPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpStep.addToCart();
		Assert.assertTrue(cartStep.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		cartStep.performProceedToCheckout();
		Assert.assertTrue(guestLoginSteps.verifyGuestLoginPageLoaded(), "Fail to load Guest Login page..");
		test.log(LogStatus.INFO, "Guest login page loaded..");
		guestLoginSteps.performLoginWithMobilePassword(testdata.get("Login_User_Mobile_Msite")
				, testdata.get("Login_User_Mobile_Password_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(addSteps.verifyAddressPageLoaded(), "Fail to load select address page..");	
		test.log(LogStatus.PASS, "User successfully logged in with mobile and password from Guest page..!!");
		/*
		 * Logout after Guest Login.
		
		homeStep.goToHomePage();
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");		 */
	}
	
	/**
	 * Test Login with Mobile number and otp.
	 */
	@Test(priority=2,enabled=true)
	public void testLoginWithMobileOtp()
	{	
		/*
		 * Empty User cart before Guest Login.
		 */
		System.out.println("## LOGIN TEST STARTS FROM GUEST PAGE with mobile and Otp..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM GUEST PAGE with mobile and Otp..");
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performLoginWithMobilePassword(testdata.get("Login_User_Mobile_Msite")
				, testdata.get("Login_User_Mobile_Password_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in with mobile and Otp..");
		cartCount=homeStep.getCartCountForUser();
		if(cartCount != 0) {
			test.log(LogStatus.INFO, "There are items in user's cart..");
			homeStep.proceedToCart();
			Assert.assertTrue(cartStep.verifyCartPageLoaded(), "Fail to load Cart page..");
			test.log(LogStatus.INFO, "cart page loaded..");
			cartStep.performMakeCartEmptyForUser();
			cartStep.performAddItems();
			Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "home page not loaded..");
			test.log(LogStatus.INFO, "user cart is empty now..");
		}else {
			test.log(LogStatus.INFO, "User cart is found empty..");
		}
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");
		comnFunc.staticWait(5);
		/*
		 * Guest Login flow starts.
		 */
		homeStep.goToFirstPVWheyProteinPDP();
		Assert.assertTrue(pdpStep.verifyPdpPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpStep.addToCart();
		Assert.assertTrue(cartStep.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		cartStep.performProceedToCheckout();
		Assert.assertTrue(guestLoginSteps.verifyGuestLoginPageLoaded(), "Fail to load Guest Login page..");
		test.log(LogStatus.INFO, "Guest login page loaded..");
		guestLoginSteps.performLoginWithMobileOtp(testdata.get("Login_User_Mobile_Msite"));
		comnFunc.staticWait(2);
		Assert.assertTrue(addSteps.verifyAddressPageLoaded(), "Fail to load select address page..");	
		test.log(LogStatus.PASS, "User successfully logged in with mobile and otp from Guest page..!!");
		/*
		 * Logout after Guest Login.
		 
		homeStep.goToHomePage();
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");		*/
	}
	
	/**
	 * Test Login with Google.
	 */
	@Test(priority=3,enabled=true)
	public void testLoginWithGoogle()
	{	
		/*
		 * Empty User cart before Guest Login.
		 */
		System.out.println("## LOGIN TEST STARTS FROM GUEST PAGE with Google Account..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM GUEST PAGE with Google Account..");
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performLoginWithMobileOtp(testdata.get("Google_User_Msite_Mobile"));
		comnFunc.staticWait(2);
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in..");
		cartCount=homeStep.getCartCountForUser();
		if(cartCount != 0) {
			test.log(LogStatus.INFO, "There are items in user's cart..");
			homeStep.proceedToCart();
			Assert.assertTrue(cartStep.verifyCartPageLoaded(), "Fail to load Cart page..");
			test.log(LogStatus.INFO, "cart page loaded..");
			cartStep.performMakeCartEmptyForUser();
			cartStep.performAddItems();
			Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "home page not loaded..");
			test.log(LogStatus.INFO, "user cart is empty now..");
		}else {
			test.log(LogStatus.INFO, "User cart is found empty..");
		}
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");
		comnFunc.staticWait(5);
		/*
		 * Guest Login flow starts.
		 */
		homeStep.goToFirstPVWheyProteinPDP();
		Assert.assertTrue(pdpStep.verifyPdpPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpStep.addToCart();
		Assert.assertTrue(cartStep.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		cartStep.performProceedToCheckout();
		Assert.assertTrue(guestLoginSteps.verifyGuestLoginPageLoaded(), "Fail to load Guest Login page..");
		test.log(LogStatus.INFO, "Guest login page loaded..");
		guestLoginSteps.performLoginWithGoogle(testdata.get("Login_User_Google_Msite")
				,testdata.get("Login_User_Google_Password_Msite"));
		comnFunc.staticWait(5);
		Assert.assertTrue(addSteps.verifyAddressPageLoaded(), "Fail to load select address page..");	
		test.log(LogStatus.PASS, "User successfully logged in with Google from Guest page..!!");
		/*
		 * Logout after Guest Login.
		 
		homeStep.goToHomePage();
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");		*/
	}
	
	/**
	 * Test Login with Facebook.
	 */
	@Test(priority=4,enabled=true)
	public void testLoginWithFacebook()
	{	
		/*
		 * Empty User cart before Guest Login.
		 */
		System.out.println("## LOGIN TEST STARTS FROM GUEST PAGE with Facebook Account..");
		test.log(LogStatus.INFO, "LOGIN TEST STARTS FROM GUEST PAGE with Facebook Account..");
		homeStep.proceedToLogin();
		Assert.assertTrue(loginStep.verifyLoginPopupPresent(), "Failed to opem login popup");
		test.log(LogStatus.INFO, "Loging popup displayed..");
		loginStep.performLoginWithMobileOtp(testdata.get("Facebook_User_Msite_Mobile"));
		comnFunc.staticWait(2);
		Assert.assertTrue(homeStep.verifySuccessfullLogin(),"User failed to Login");
		test.log(LogStatus.INFO, "User successfully logged in..");
		cartCount=homeStep.getCartCountForUser();
		if(cartCount != 0) {
			test.log(LogStatus.INFO, "There are items in user's cart..");
			homeStep.proceedToCart();
			Assert.assertTrue(cartStep.verifyCartPageLoaded(), "Fail to load Cart page..");
			test.log(LogStatus.INFO, "cart page loaded..");
			cartStep.performMakeCartEmptyForUser();
			cartStep.performAddItems();
			Assert.assertTrue(homeStep.verifyTrendingWheyProteinWidget(), "home page not loaded..");
			test.log(LogStatus.INFO, "user cart is empty now..");
		}else {
			test.log(LogStatus.INFO, "User cart is found empty..");
		}
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");
		comnFunc.staticWait(5);
		/*
		 * Guest Login flow starts.
		 */
		homeStep.goToFirstPVWheyProteinPDP();
		Assert.assertTrue(pdpStep.verifyPdpPageLoaded(), "Fail to load PDP page..");
		test.log(LogStatus.INFO, "Product details page loaded..");
		pdpStep.addToCart();
		Assert.assertTrue(cartStep.verifyCartPageLoaded(), "Fail to load Cart page..");
		test.log(LogStatus.INFO, "Product added to cart..");
		cartStep.performProceedToCheckout();
		Assert.assertTrue(guestLoginSteps.verifyGuestLoginPageLoaded(), "Fail to load Guest Login page..");
		test.log(LogStatus.INFO, "Guest login page loaded..");
		guestLoginSteps.performLoginWithFacebook(testdata.get("Login_User_Facebook_Msite")
				,testdata.get("Login_User_Facebook_Password_Msite"));
		comnFunc.staticWait(5);
		Assert.assertTrue(addSteps.verifyAddressPageLoaded(), "Fail to load select address page..");	
		test.log(LogStatus.PASS, "User successfully logged in with Facebook from Guest page..!!");
		/*
		 * Logout after Guest Login.
		 
		homeStep.goToHomePage();
		homeStep.performLogout();
		Assert.assertTrue(homeStep.verifySuccessfullLogout(),"User failed to Logout");
		test.log(LogStatus.INFO, "User successfully logged Out..");		*/
	}

}
