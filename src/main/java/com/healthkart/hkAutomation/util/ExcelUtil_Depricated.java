package com.healthkart.hkAutomation.util;

import java.util.HashMap;
import java.util.HashSet;

import com.healthkart.hkAutomation.property.PropertyHelper;


// This class is not used anymore in the project..

public class ExcelUtil_Depricated {
	
	    private static ExcelReading excelRead;
	    private static String sheetName;

	    static{
	        try {
	            System.out.println(System.getProperty("user.dir") + "/DataSheets/HKData.xlsx");
	            excelRead = new ExcelReading(System.getProperty("user.dir") + "/DataSheets/HKData.xlsx");
	            if(GlobalVar.jenkinsEnvironment==null)
	            {
		            if("prod".equals(PropertyHelper.readProperty("env"))) {
		            	sheetName = "config_prod";
		            }
		            else {
		            	sheetName = "config_qa";
		            }
	            }
	            else
	            {
	            	if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
	            	{
	            		sheetName = "config_prod";
	            	}
	            	else
	            	{
	            		sheetName = "config_qa";
	            	}
	            }
	            
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Login and Sign up data hkweb
	     */
	    public static String getBrowserName() { return excelRead.getCellDataAsString(sheetName,0,1); }
	    public static String getUrl() { return excelRead.getCellDataAsString(sheetName,1,1); }
	    
	    public static String getMobileNumber() { return excelRead.getCellDataNumberString(sheetName,13,1); }
	    public static String getPassword() { return excelRead.getCellDataAsString(sheetName,14,1); }
	    public static String getUserName() { return excelRead.getCellDataAsString(sheetName,13,2); }
	    
	    public static String getGoogleUserEmail() { return excelRead.getCellDataAsString(sheetName,15,1); }
	    public static String getGooglePassword() { return excelRead.getCellDataAsString(sheetName,16,1); }
	    public static String getGoogleUserName() { return excelRead.getCellDataAsString(sheetName,15,2); }
	    
	    public static String getGoogleMobileNo() { return excelRead.getCellDataAsString(sheetName,15,3); }
	    
	    public static String getFacebookUserEmail() { return excelRead.getCellDataAsString(sheetName,17,1); }
	    public static String getFacebookPassword() { return excelRead.getCellDataAsString(sheetName,18,1); }
	    public static String getFacebookUserName() { return excelRead.getCellDataAsString(sheetName,17,2); }
	    
	    public static String getFacebookMobileNo() { return excelRead.getCellDataAsString(sheetName,17,3); }
	    
	    public static String getSignupMobileNumber() { return excelRead.getCellDataNumberString(sheetName,8,1); }
	    public static String getSignupPassword() { return excelRead.getCellDataAsString(sheetName,8,2); }
	    public static String getSignupUserName() { return excelRead.getCellDataAsString(sheetName,8,3); }
	    
	    /**
	     * Login and Sign up data MobileSite
	     */
	    public static String getMsiteMobileNumber() {return excelRead.getCellDataNumberString(sheetName,64,1); }
	    public static String getMsitePassword() { return excelRead.getCellDataAsString(sheetName,65,1); }
	    public static String getMsiteGoogleUserEmail() {return excelRead.getCellDataAsString(sheetName,67,1); }
	    public static String getMsiteGooglePassword() { return excelRead.getCellDataAsString(sheetName,68,1); }
	    public static String getMsiteGoogleMobileNo() {return excelRead.getCellDataNumberString(sheetName,67,2); }
	    public static String getMsiteFacebookUserEmail() { return excelRead.getCellDataAsString(sheetName,69,1); }
	    public static String getMsiteFacebookPassword() {return excelRead.getCellDataAsString(sheetName,70,1); }
	    public static String getMsiteFacebookMobileNo() { return excelRead.getCellDataNumberString(sheetName,69,2); }
	    
	    /**
	     * Login and Sign up data AndroidApp
	     */
	    public static String getAppMobileNumber() {return excelRead.getCellDataNumberString(sheetName,72,1); }
	    public static String getAppPassword() { return excelRead.getCellDataAsString(sheetName,73,1); }
	    public static String getAppGoogleUserEmail() {return excelRead.getCellDataAsString(sheetName,74,1); }
	    public static String getAppGooglePassword() { return excelRead.getCellDataAsString(sheetName,75,1); }
	    public static String getAppGoogleMobileNo() {return excelRead.getCellDataNumberString(sheetName,74,2); }
	    public static String getAppFacebookUserEmail() { return excelRead.getCellDataAsString(sheetName,76,1); }
	    public static String getAppFacebookPassword() {return excelRead.getCellDataAsString(sheetName,77,1); }
	    public static String getAppFacebookMobileNo() { return excelRead.getCellDataNumberString(sheetName,76,2); }
	    
	    /**
	     * Search data
	     */
	    
	    public static String getSearchString() { return excelRead.getCellDataAsString(sheetName,37,1); }
	    
	    /**
	     * Address data
	     */
	    
	    public static String getNameForAddress() { return excelRead.getCellDataAsString(sheetName,40,2); }
	    public static String getMobileNoForAddress() { return excelRead.getCellDataAsString(sheetName,41,2); }
	    public static String getLine1ForAddress() { return excelRead.getCellDataAsString(sheetName,42,2); }
	    public static String getLandmarkForAddress() { return excelRead.getCellDataAsString(sheetName,43,2); }
	    public static String getPincodeForAddress() { return excelRead.getCellDataAsString(sheetName,44,2); }
	    public static String getEmailIdForAddress() { return excelRead.getCellDataAsString(sheetName,45,2); }
	    
	    /**
	     * Offers/coupons/variant/HK-cash data
	     */
	    public static String getPrefixUrl() { return excelRead.getCellDataAsString(sheetName,28,1); }
	    public static String getBxGyVariantId() { return excelRead.getCellDataAsString(sheetName,35,1); }
	    public static String getCouponVariantId() { return excelRead.getCellDataAsString(sheetName,34,1); }
	    public static String getPromptOfferVariantId() { return excelRead.getCellDataAsString(sheetName,33,1); }
	    public static String getCouponCodeForCart() { return excelRead.getCellDataAsString(sheetName,34,3); }
	    public static String getHKCashVariant() { return excelRead.getCellDataAsString(sheetName,9,2); }
	    public static String getHKPromptOfferVariantName() {return excelRead.getCellDataAsString(sheetName,62,1);}
	    public static String getPrepaidVariantId() {return excelRead.getCellDataAsString(sheetName,66,1);}
	    public static String getCompareVariantId() {return excelRead.getCellDataAsString(sheetName,36,1);}
	    
	    /**
	     * Card Payment data
	     */
	    
	    public static String getCardNumber() { return excelRead.getCellDataAsString(sheetName,50,1); }
	    public static String getExpiryMonth() { return excelRead.getCellDataAsString(sheetName,51,1); }
	    public static String getExpiryYear() { return excelRead.getCellDataAsString(sheetName,51,2); }
	    public static String getCvvNumber() { return excelRead.getCellDataAsString(sheetName,52,1); }
	    public static String getCardName() { return excelRead.getCellDataAsString(sheetName,49,1); }
	    public static String getCardPin() { return excelRead.getCellDataAsString(sheetName,53,1); }
	    
	    /**
	     * Wallets' payment data
	     */
	    
	    public static String getPayTmNumber() { return excelRead.getCellDataAsString(sheetName,58,1); }
	    public static String getMobiKwikNumber() { return excelRead.getCellDataAsString(sheetName,59,1); }
	    public static String getPayUNumber() { return excelRead.getCellDataAsString(sheetName,60,2); }
	    public static String getFreeChargeNumber() { return excelRead.getCellDataAsString(sheetName,61,1); }

	    public static HashMap<String,String> cardDetails()
	    {
		    HashMap<String, String> cardDetails = new HashMap<String, String>();
		    cardDetails.put("cardNumber", getCardNumber());
		    cardDetails.put("expiryMonth", getExpiryMonth());
		    cardDetails.put("expiryYear", getExpiryYear());
		    cardDetails.put("cvvNumber", getCvvNumber());
		    cardDetails.put("cardName", getCardName());
		    cardDetails.put("pin", getCardPin());
			return cardDetails;
	    }		
}
