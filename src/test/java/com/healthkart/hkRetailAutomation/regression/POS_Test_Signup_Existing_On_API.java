package com.healthkart.hkRetailAutomation.regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.healthkart.extentReportUtil.ExtentReportingBaseUtil;

public class POS_Test_Signup_Existing_On_API extends ExtentReportingBaseUtil
{
	@Test(priority=1,enabled=true)
	public void createNewUserPOSFromExistingAPIUser() {
		comnFunc.reportLogAndPrintInConsole("## CREATE A NEW USER ON POS, TEST STARTS..");
		posLoginSteps.performLoginWithMobilePassword(testdata.get("POS_App_Login_User"), testdata.get("POS_App_Login_Pswd"));
		Assert.assertTrue(posHomeSteps.verifyUserLoginPOS(), "User failed to Login");
		comnFunc.reportLogAndPrintInConsole("User successfully logged in with mobile and password");
		posHomeSteps.saveRetailStorePOS(testdata.get("POS_Store_Name"));
		posHomeSteps.openRetailStorePOS();
		Assert.assertTrue(posSteps.verifyScreenPOS(), "Failed to open POS Screen..!!");
		comnFunc.reportLogAndPrintInConsole("POS order taking screen open successfully..");
		posSteps.clearUserDataForRetailSignup(testdata.get("POS_Customer_Mobile_New"),1,"POS");
		Assert.assertTrue(posSteps.verifyIfSignupMobileExists(testdata.get("POS_Customer_Mobile_New"),1,"POS"),"POS user Still Exists after deletion..!!");
		comnFunc.reportLogAndPrintInConsole("POS user not exists..");
		posSteps.inputCustomerDetails(testdata.get("POS_Customer_Mobile_New"));
		Assert.assertTrue(posSteps.updateCustomerInfoPOS(),"Customer info NOT updated..!!");
		comnFunc.reportLogAndPrintInConsole("Customer info updated successfully..");
		comnFunc.staticWait(2);
		Assert.assertTrue(posSteps.verifyNewUserSyncOnAPI(testdata.get("POS_Customer_Mobile_New"),1),"User not synced through POS/API..!!");
		comnFunc.reportLogAndPrintInConsole("User details successfully synced on POS..");		
	}
}
