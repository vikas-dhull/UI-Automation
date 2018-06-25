package com.healthkart.hkAutomation.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	 
	private int count = 0;
    private static int maxTry = 1;
 
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {                      //Check if test not succeed
            if (count < maxTry) {                            //Check if maxtry count is reached
                count++;                                     //Increase the maxTry count by 1
            //    iTestResult.setStatus(ITestResult.SKIP);     //Mark test as skip
                System.out.println("Retrying test with "+iTestResult.getName()+" with status "+ getResultStatusName(iTestResult.getStatus()));
                return true;                                 //Tells TestNG to re-run the test
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }
    
    public String getResultStatusName(int status){

        String resultName = null;

        if(status==1)

            resultName ="SUCCESS";

        if(status==2)

            resultName = "FAILURE";

        if(status==3)

            resultName = "SKIP";

        return resultName;

    }
}

