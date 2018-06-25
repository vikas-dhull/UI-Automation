package com.healthkart.hkAutomation.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HK_Data_Provider {

	private Properties masterDataProp = null;
	private Map<String,String> datamap = new HashMap<String,String>();
	private Map<String,String> cardDetail = new HashMap<String,String>();
	
	public HK_Data_Provider() {
		masterDataProp = new Properties();
		String fileLoc = System.getProperty("user.dir") + "/data_provider.properties";
		try {
			File f = new File(fileLoc);
			FileInputStream fis = new FileInputStream(f);
			masterDataProp.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadDataMap() {
		for (String key : masterDataProp.stringPropertyNames()) {
		    String value = masterDataProp.getProperty(key).trim();
		    datamap.put(key, value);
		    if(key.startsWith("CC_")) {
		    	cardDetail.put(key, value);
		    }
		}
	}
	
	public Map<String,String> getDataMap() {
		return datamap;
	}
	
	public Map<String,String> getCardDetailMap() {
		return cardDetail;
	}
	
	/*public static void main(String [] args) {
		HK_Data_Provider obj = new HK_Data_Provider();
		for (Map.Entry<String, String> entry : datamap.entrySet()) {
		    System.out.println(entry.getKey()+" : "+entry.getValue());
		}
	}*/
}
