package com.healthkart.hkAutomation.business;

import org.openqa.selenium.WebDriver;
import com.healthkart.hkAutomation.pages.HK_SelectAddress_p;

public class HK_SelectAddress_b extends HK_SelectAddress_p {

	public HK_SelectAddress_b(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Select a delivery address.
	 */
	public void selectDeliveryAddress() {
		if(verifyAddressPageLoaded()) {
			if(getUserHasAddressCount()>0) {
				if(isTextSelectAddressPresent()) {
					clickFirstSelectAddressBtn();
				}
			}
			else if(isTextAddNewAddressPresent()){
				setAddressName("Test User");
				setAddressMobileNo("9999999999");
				setAddressLine1("Sector 14");
				setAddressLandmark("Motorola Building");
				setAddressPincode("122001");
				setAddressEmailId("test@brightlifecare.com");
				uncheckForSubscription();
				submitAddressSave();			
			}
		}
		else {
			System.out.println("Something went wrong on Address page..");
		}		
	}
	
	/**
	 * Add a new address.
	 */
	public void addNewAddressForUser() {
		if(verifyAddressPageLoaded() && isTextSelectAddressPresent() && getUserHasAddressCount()>0) {
			clickAddNewAddressLink();
			setAddressName("Test User");
			setAddressMobileNo("9999999999");
			setAddressLine1("Sector 14");
			setAddressLandmark("Motorola Building");
			setAddressPincode("122001");
			setEmailOnAddressPage("test@brightlifecare.com");
			submitAddressSave();			
		}		
	}

	/**
	 * Delete an address.
	 */
	public void deleteAddressForUser() {
		if(verifyAddressPageLoaded() && isTextSelectAddressPresent() && getUserHasAddressCount()>0) {
			clickDeleteFirstAddress();			
		}
	}
	
	/**
	 * Edit an address.
	 */
	public void editAddressForUser() {
		if(verifyAddressPageLoaded() && isTextSelectAddressPresent() && getUserHasAddressCount()>0) {
			clickEditAddress();
			setAddressLandmark("Motorola Building");
			submitAddressSave();
		}		
	}
	
	/**
	 * add/edit an email for address.
	 */
	public void addEmailForUserAddress() {
			clickEditEmailLinkOnAddressPage();
			setEmailOnAddressPage("test@brightlifecare.com");
			clickSaveEmailOnAddressPage();
	}
}
