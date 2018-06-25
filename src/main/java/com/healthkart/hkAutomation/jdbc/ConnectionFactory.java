package com.healthkart.hkAutomation.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.GlobalVar;

public class ConnectionFactory {
	//private static ConnectionFactory instance;
    private String URL;
    private String USER;
    private String PASSWORD;
    private String DRIVER_CLASS;
    
    public ConnectionFactory() {
    	if(GlobalVar.jenkinsEnvironment==null)
    	{
	    	if("prod".equals(PropertyHelper.readProperty("env")) || "MBprod".equals(PropertyHelper.readProperty("env")) || "MBmsiteprod".equals(PropertyHelper.readProperty("env"))) 
	    	{
	    		URL = PropertyHelper.readProperty("DB_PROD_URL");
	            USER = PropertyHelper.readProperty("DB_PROD_USER");
	            PASSWORD = PropertyHelper.readProperty("DB_PROD_PASS");
	            DRIVER_CLASS = "com.mysql.jdbc.Driver";
	    		
	        }
	        else 
	        {
	        	URL = PropertyHelper.readProperty("DB_QA_URL");
	            USER = PropertyHelper.readProperty("DB_QA_USER");
	            PASSWORD = PropertyHelper.readProperty("DB_QA_PASS");
	            DRIVER_CLASS = "com.mysql.jdbc.Driver";
	        }
    	}
    	else if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment) || "MBprod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment) || "MBprod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
    	{
    		URL = PropertyHelper.readProperty("DB_PROD_URL");
            USER = PropertyHelper.readProperty("DB_PROD_USER");
            PASSWORD = PropertyHelper.readProperty("DB_PROD_PASS");
            DRIVER_CLASS = "com.mysql.jdbc.Driver";
    	}
    	else if("qa".equalsIgnoreCase(GlobalVar.jenkinsEnvironment))
    	{
    		
    		URL = GlobalVar.jenkinsDBURL;
    		USER = PropertyHelper.readProperty("DB_QA_USER");
	        PASSWORD = PropertyHelper.readProperty("DB_QA_PASS");
	        DRIVER_CLASS = "com.mysql.jdbc.Driver";
    	}    	
    }
    
    public Connection getConnection() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.out.println("-----Driver Class not found---------");
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if(connection==null)
                System.out.println("Connection failed!");
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }
    
    public Connection getConnection(String dbName) {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.out.println("-----Driver Class not found---------");
            e.printStackTrace();
        }
        Connection connection = null;
        try {
        	URL = URL+"/" + dbName;
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if(connection==null)
                System.out.println("Connection failed!");
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }
}
