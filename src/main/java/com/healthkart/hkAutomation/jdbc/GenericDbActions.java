package com.healthkart.hkAutomation.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.healthkart.hkAutomation.property.PropertyHelper;
import com.healthkart.hkAutomation.util.CommonFunctions;
import com.healthkart.hkAutomation.util.GlobalVar;
import com.healthkart.hkAutomation.util.OrderDataItems;

public class GenericDbActions {
	private ConnectionFactory connFact;
	private Connection connection;
    private Statement statement;
    private String hkCatDB;
    private String hkAdminDB;
    private String hkAquaDB;
    private CommonFunctions comnFunc;

    public GenericDbActions(){
    	comnFunc=new CommonFunctions();

    	if(GlobalVar.jenkinsEnvironment==null)
    	{
	    	if("prod".equals(PropertyHelper.readProperty("env"))) 
	    	{
	    		hkCatDB="healthkart_catalog";
	    		hkAdminDB="healthkart_prod";
	    		hkAquaDB="aqua_prod";
	    		
	    	}
	    	else 
	    	{
	    		hkCatDB="hk_cat";
	    		hkAdminDB="hk_qa";
	    		hkAquaDB="aqua_prod";
	    	}
    	}
    	else
    	{
    		if("prod".equalsIgnoreCase(GlobalVar.jenkinsEnvironment)) 
	    	{
	    		hkCatDB="healthkart_catalog";
	    		hkAdminDB="healthkart_prod";
	    		hkAquaDB="aqua_prod";
	    		
	    	}
	    	else 
	    	{
	    		hkCatDB="hk_cat";
	    		hkAdminDB="hk_qa";
	    		hkAquaDB="aqua_prod";
	    	}
    	}
    	
    }
    
    /*
     * ## HK Methods ##
     */    
    
    public String getOtp(String mobileNumber, int storeId) {    	 	
        String query = "SELECT otp FROM "+hkCatDB+".user_otp WHERE store_id = "+storeId+" and mobile_number = '" + mobileNumber + "'" + " ORDER BY valid_till DESC;";
        String dbOtp = null;
        ResultSet rs = null;
        try {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();
            System.out.println("Query formed ::" + query);
            rs = statement.executeQuery(query);
            if(!rs.next())
                return "false";
            dbOtp = rs.getString("otp");
                
            System.out.println("Otp : "+ dbOtp);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return dbOtp;
    }
    
    public String getWalletOtp(String domain) {    	 	
        String query = "SELECT otp FROM "+hkCatDB+".read_otp WHERE domain = '"+domain+"' "
        		+ "AND create_dt = (SELECT MAX(create_dt) FROM "+hkCatDB+".read_otp) "
        		+ "AND TIMESTAMPDIFF(MINUTE, create_dt, CURRENT_TIMESTAMP) <1;";
        String WalletOtp = null;
        ResultSet rs = null;
        try {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();
            System.out.println("SQL Query formed ::" + query);
            rs = statement.executeQuery(query);
            if(!rs.next())
                return "false";
            WalletOtp = rs.getString("otp");
                
            System.out.println("Otp : "+ WalletOtp);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return WalletOtp;
    }    

    public List<OrderDataItems> verifyOrderUsingGatewayID(String orderID) {   	
    	
	    	String query = "SELECT bo.gateway_order_id,bo.amount,bo.order_type,oli.opr_id,GROUP_CONCAT(oli.id) AS opr_line_item_id "
	        		+ "FROM "+hkCatDB+".base_order bo JOIN "+hkCatDB+".opr o ON o.base_order_id = bo.id "
	        		+ "JOIN "+hkCatDB+".opr_li_status oli ON oli.opr_id = o.id "
	        		+ "WHERE bo.gateway_order_id = '"+orderID+"' GROUP BY 1;";
	        
	        ResultSet rs = null;
	        List<OrderDataItems> orderDataItemsList = new ArrayList<OrderDataItems>();
	    try {
	    	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();
            System.out.println("SQL Query formed ::" + query);
            rs = statement.executeQuery(query);            
            
            while(rs.next()) {
            	String gatewayOrderId = rs.getString("gateway_order_id");
            	String oprLiId = rs.getString("opr_line_item_id");
            	Long oprId = rs.getLong("opr_id");
            	Long amount = rs.getLong("amount");
            	String cliIds = null;
            	int orderType = Integer.parseInt(rs.getString("order_type"));
            	
            	OrderDataItems orderDataItems = new OrderDataItems(gatewayOrderId, amount, oprId, oprLiId,cliIds,orderType);
            	orderDataItemsList.add(orderDataItems);
            }
            
    	}catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
	    
	    return orderDataItemsList;	    
    }   
    
    public String verifyIfSignUpMobileExists(String mobileNumber, int storeId) { 
    	
        String query = "SELECT id FROM "+hkCatDB+".user WHERE store_id = "+storeId+" and is_number_verified = 1 and contact_number = '" + mobileNumber + "';";
        String userId = null;
        ResultSet rs = null;
        try {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if(!rs.next())
                return userId;
            userId = rs.getString("id");
            comnFunc.reportLogAndPrintInConsole("User ID Existed on API : "+ userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return userId;
    }

    public void deleteSignUpUserData(String mobileNumber, int storeId) 
	{
		String query1 = "DELETE FROM " + hkCatDB + ".user_auth WHERE user_id =(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "');";
		String query2 = "DELETE FROM " + hkCatDB + ".coupon_user WHERE user_id =(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "');";
		String query3 = "DELETE FROM " + hkCatDB + ".user_role WHERE user_id =(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "');";
		String query4 = "DELETE FROM " + hkCatDB + ".subscription WHERE user_id =(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "');";
		String query5 = "DELETE FROM " + hkCatDB + ".user_application WHERE user_id=(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "');";
		String query6 = "DELETE FROM " + hkCatDB + ".user_retail_location WHERE user_id=(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "');";
		String query7 = "DELETE FROM " + hkCatDB + ".shopping_cart_line_item WHERE shopping_cart_id=(SELECT id FROM " + hkCatDB + ".shopping_cart WHERE user_id=(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "'));"; 
		String query8 = "DELETE FROM " + hkCatDB + ".shopping_cart WHERE user_id=(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '"+mobileNumber+"');";
		String query9 = "DELETE FROM " + hkCatDB + ".user_answer WHERE user_id =(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "');";
		String query10 = "DELETE FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 and contact_number = '" + mobileNumber + "';";
		
		System.out.println(query1);
		System.out.println(query2);
		System.out.println(query3);
		System.out.println(query4);
		System.out.println(query5);
		System.out.println(query6);
		System.out.println(query7);
		System.out.println(query8);
		System.out.println(query9);
		System.out.println(query10);
		ResultSet rs = null;
        try {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection(hkCatDB);
            statement = connection.createStatement();
            statement.addBatch(query1);
            statement.addBatch(query2);
            statement.addBatch(query3);
            statement.addBatch(query4);
            statement.addBatch(query5);
            statement.addBatch(query6);
            statement.addBatch(query7);
            statement.addBatch(query8);
            statement.addBatch(query9);
            statement.addBatch(query10);
            System.out.println();
            System.out.println(statement.executeBatch());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        	}
    }
    
    public void setupHKCashDataForUser(String mobileNumber, String hkCashType, int storeId) 
	{
    	String query1= null;
    	String query2= null;
    	String query3= null;
    	String query4= null;
    	String query5= null;
    	
    	if("partial".equalsIgnoreCase(hkCashType))
    	{
    		query1 = "DELETE FROM " + hkCatDB + ".user_point WHERE value < 0 and user_id =(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "') ORDER BY parent_user_point_id DESC;";
    		query2 = "DELETE FROM " + hkCatDB + ".user_point WHERE user_id =(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "') ORDER BY parent_user_point_id DESC;";
    		query3 = "DELETE FROM " + hkCatDB + ".user_point_balance WHERE user_id =(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "')";
    		query4 = "INSERT IGNORE INTO " + hkCatDB + ".user_point (user_id, VALUE, user_point_type, source_entity_id, user_point_mode_id, user_point_status_id, "
    				+ "user_point_txn_type_id, parent_user_point_id, expiry_dt, update_dt, referred_user_id, approver_id, txn_dt, reason_id, from_store_id, to_store_id, "
    				+ "comment, hk_order_id, opr_li_status_id, version, approved_dt) "
    				+ "VALUES((SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "'),'100','10','HK_SOURCE_ENTITY','30','20','1',NULL,'2029-12-28 23:59:59',"
    						+ "CURRENT_TIMESTAMP,NULL,'15923131',CURRENT_TIMESTAMP,'90','1','1','test..',NULL,NULL,'1',CURRENT_TIMESTAMP);";
    		query5 = "INSERT IGNORE INTO " + hkCatDB + ".user_point_balance (user_id, reward_point_balance, loyalty_point_balance, store_id, version) "
    				+ "VALUES((SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "'),'100','0','1','1');";
    	}
		
    	if("full".equalsIgnoreCase(hkCashType))
    	{
    		query1 = "DELETE FROM " + hkCatDB + ".user_point WHERE value < 0 and user_id =(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "') ORDER BY parent_user_point_id DESC;";
    		query2 = "DELETE FROM " + hkCatDB + ".user_point WHERE user_id =(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "') ORDER BY parent_user_point_id DESC;";
    		query3 = "DELETE FROM " + hkCatDB + ".user_point_balance WHERE user_id =(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "');";
    		query4 = "INSERT IGNORE INTO " + hkCatDB + ".user_point (user_id, VALUE, user_point_type, source_entity_id, user_point_mode_id, user_point_status_id, "
    				+ "user_point_txn_type_id, parent_user_point_id, expiry_dt, update_dt, referred_user_id, approver_id, txn_dt, reason_id, from_store_id, to_store_id, "
    				+ "comment, hk_order_id, opr_li_status_id, version, approved_dt) "
    				+ "VALUES((SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "'),'10000','10','HK_SOURCE_ENTITY','30','20','1',NULL,'2029-12-28 23:59:59',"
    						+ "CURRENT_TIMESTAMP,NULL,'15923131',CURRENT_TIMESTAMP,'90','1','1','test..',NULL,NULL,'1',CURRENT_TIMESTAMP);";
    		query5 = "INSERT IGNORE INTO " + hkCatDB + ".user_point_balance (user_id, reward_point_balance, loyalty_point_balance, store_id, version) "
    				+ "VALUES((SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 AND contact_number = '" + mobileNumber + "'),'10000','0','1','1');";	    		
    	}
    	
		System.out.println(query1);
		System.out.println(query2);
		System.out.println(query3);
		System.out.println(query4);
		System.out.println(query5);
		ResultSet rs = null;
        try {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection(hkCatDB);
            statement = connection.createStatement();
            statement.addBatch(query1);
            statement.addBatch(query2);
            statement.addBatch(query3);
            statement.addBatch(query4);
            statement.addBatch(query5);
            System.out.println();
            System.out.println(statement.executeBatch());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
	}
    
    public boolean verifyHKCashPresentForUser(String mobileNumber, String hkCashType, int storeId) 
	{
    	boolean flag=false;
    	double hkCash;
    	String query = "SELECT reward_point_balance FROM " + hkCatDB + ".user_point_balance WHERE user_id ="
    			+ "(SELECT id FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and is_number_verified = 1 and contact_number = '" + mobileNumber + "');";
    	
    	System.out.println(query);
    	ResultSet rs = null;
        try {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            
            if(!rs.next())
                return flag;
            hkCash = rs.getDouble("reward_point_balance");
            System.out.println("reward point balance found for user: "+ hkCash);
            
            if("partial".equalsIgnoreCase(hkCashType) && hkCash > 10 && hkCash < 500)
        	{
            	flag=true;
        	}
    		
        	if("full".equalsIgnoreCase(hkCashType) && hkCash > 6000)
        	{
        		flag=true;  		
        	}        	
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        
        return flag;
	}

	public Set<String> getActiveIssuerIds() 
	{
		 String query = "SELECT issuer_identifier FROM hkpay.issuer WHERE starred = 1 AND active =1 AND issuer_type_id = 30 ;";
	        Set<String> set = new LinkedHashSet<String>();
	        ResultSet rs = null;
	        try 
	        {
	        	connFact = new ConnectionFactory();
	            connection = connFact.getConnection();
	            statement = connection.createStatement();
	            rs = statement.executeQuery(query);
	            while(rs.next())
	            {
	            	String issuerId = rs.getString("issuer_identifier");
	                set.add(issuerId);
	                System.out.println("IssuerId ::" + issuerId);
	            }
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        } 
	        finally 
	        {
	            DBUtil.close(rs);
	            DBUtil.close(statement);
	            DBUtil.close(connection);
	        }
	        return set;
	}
	
	public String getInStockCompareVariant() {
		String cmprVrnt = null;		
		String query = "SELECT t1.store_variant_id FROM " + 
				"(SELECT svc.id svcId1, svc.store_variant_id FROM " + hkCatDB + ".store_variant_compare_store_variant svc JOIN " + hkCatDB + ".store_variant sv ON svc.store_variant_id = sv.id " + 
				"WHERE sv.out_of_stock=0 AND sv.bool_bitset%43!=0 AND sv.name LIKE '%Ultimate Nutrition%')t1 JOIN " + 
				"(SELECT svc.id svcId2, svc.compare_store_variant_id FROM " + hkCatDB + ".store_variant_compare_store_variant svc JOIN " + hkCatDB + ".store_variant sv ON svc.compare_store_variant_id = sv.id " + 
				"WHERE sv.out_of_stock=0)t2 ON t1.svcId1 = t2.svcId2 LIMIT 1;";
        ResultSet rs = null;
        try {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();
            System.out.println("Query formed for compare variant ::" + query);
            rs = statement.executeQuery(query);
            if(!rs.next())
                return "false";
            cmprVrnt = rs.getString("store_variant_id");
                
            System.out.println("Compare variant ID : "+ cmprVrnt);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return cmprVrnt;
	}

    public Set<String> getActiveIssuerIdsWallet()
	{
		 String query = "SELECT issuer_identifier FROM hkpay.issuer WHERE starred = 1 AND active =1 AND issuer_type_id = 70 ;";
	        Set<String> set = new LinkedHashSet<String>();
	        ResultSet rs = null;
	        try 
	        {
	        	connFact = new ConnectionFactory();
	            connection = connFact.getConnection();
	            statement = connection.createStatement();
	            rs = statement.executeQuery(query);
	            while(rs.next())
	            {
	            	String issuerId = rs.getString("issuer_identifier");
	                set.add(issuerId);
	                System.out.println("IssuerId ::" + issuerId);
	            }
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        } 
	        finally 
	        {
	            DBUtil.close(rs);
	            DBUtil.close(statement);
	            DBUtil.close(connection);
	        }
	        return set;
	}
	
    /*
     * ## MB Methods ##
     */
    
    public void deleteSignUpUserDataMB(String mobileNumber) 
	{
		String query1 = "DELETE FROM " + hkCatDB + ".user_auth WHERE user_id =(SELECT id FROM " + hkCatDB + ".user WHERE is_number_verified=1 AND store_id=9 AND contact_number = '" + mobileNumber + "');";
		String query2 = "DELETE FROM " + hkCatDB + ".coupon_user WHERE user_id =(SELECT id FROM " + hkCatDB + ".user WHERE is_number_verified=1 AND store_id=9 AND contact_number = '" + mobileNumber + "');";
		String query3 = "DELETE FROM " + hkCatDB + ".user_role WHERE user_id =(SELECT id FROM " + hkCatDB + ".user WHERE is_number_verified=1 AND store_id=9 AND contact_number = '" + mobileNumber + "');";
		String query4 = "DELETE FROM " + hkCatDB + ".subscription WHERE user_id =(SELECT id FROM " + hkCatDB + ".user WHERE is_number_verified=1 AND store_id=9 AND contact_number = '" + mobileNumber + "');";
		String query5 = "DELETE FROM " + hkCatDB + ".user_application WHERE user_id=(SELECT id FROM " + hkCatDB + ".user WHERE is_number_verified=1 AND store_id=9 AND contact_number = '" + mobileNumber + "');";
		String query6 = "DELETE FROM " + hkCatDB + ".user_retail_location WHERE user_id=(SELECT id FROM " + hkCatDB + ".user WHERE is_number_verified=1 AND store_id=9 AND contact_number = '" + mobileNumber + "');";
		String query7 = "DELETE FROM " + hkCatDB + ".shopping_cart_line_item WHERE shopping_cart_id=(SELECT id FROM " + hkCatDB + ".shopping_cart WHERE user_id=(SELECT id FROM" + hkCatDB + ".user WHERE is_number_verified=1 AND store_id=9 AND contact_number = '" + mobileNumber + "');"; 
		String query8 = "DELETE FROM " + hkCatDB + ".shopping_cart WHERE user_id=(SELECT id FROM " + hkCatDB + ".user WHERE is_number_verified=1 AND store_id=9 AND contact_number = '"+mobileNumber+"');"; 
		String query9 = "DELETE FROM " + hkCatDB + ".user_answer WHERE user_id=(SELECT id FROM " + hkCatDB + ".user WHERE is_number_verified=1 AND store_id=9 AND contact_number = '"+mobileNumber+"');"; 
		String query10 = "DELETE FROM " + hkCatDB + ".user WHERE contact_number = '" + mobileNumber + "';";
		
		System.out.println(query1);
		System.out.println(query2);
		System.out.println(query3);
		System.out.println(query4);
		System.out.println(query5);
		System.out.println(query6);
		System.out.println(query7);
		System.out.println(query8);
		System.out.println(query9);
		System.out.println(query10);
		ResultSet rs = null;
		try {
			connFact = new ConnectionFactory();
	        connection = connFact.getConnection(hkCatDB);
	        statement = connection.createStatement();
	        statement.addBatch(query1);
	        statement.addBatch(query2);
	        statement.addBatch(query3);
	        statement.addBatch(query4);
	        statement.addBatch(query5);
	        statement.addBatch(query6);
	        statement.addBatch(query7);
	        statement.addBatch(query8);
	        statement.addBatch(query9);
	        statement.addBatch(query10);
	        System.out.println();
            System.out.println(statement.executeBatch());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        	}
    }
    
	public String getMBOtp(String mobileNumber) {    	 	
        String query = "SELECT otp FROM "+hkCatDB+".user_otp WHERE mobile_number = '" + mobileNumber + "' and store_id = '9'" + " ORDER BY valid_till DESC;";
        String dbOtp = null;
        ResultSet rs = null;
        try {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();
            System.out.println("Query formed ::" + query);
            rs = statement.executeQuery(query);
            if(!rs.next())
                return "false";
            dbOtp = rs.getString("otp");
                
            System.out.println("Otp : "+ dbOtp);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return dbOtp;
    }

	public String fetchCodeForMBAuthentication() 
	{
        String query = "SELECT * FROM bright_prod.auth_code_item WHERE tried_count = 0 AND status_id = 20 ORDER BY create_date LIMIT 1 ;";
        String productCode = null;
        ResultSet rs = null;
        try {

        	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();
            System.out.println("Query formed ::" + query);
            rs = statement.executeQuery(query);
            if(!rs.next())
            	return "false";
            productCode = rs.getString("code");
                
            System.out.println("productCode : "+ productCode);
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return productCode;	
	}
	
	public String getActiveCouponCode(String storeId) 
	{
		if("MBqa".equals(PropertyHelper.readProperty("env")) || "MBqa".equals(PropertyHelper.readProperty("env")) || "MBmsiteqa".equals(PropertyHelper.readProperty("env")))
		{
			String updateQuery = "UPDATE "+ hkCatDB +".offer SET end_dt = '2018-12-31 00:00:00' WHERE id = 17355;";
			try
			{
				connFact = new ConnectionFactory();
				connection = connFact.getConnection();
				statement = connection.createStatement();
				System.out.println("Update query for coupon code:: " + updateQuery);
				int updateQueryResult = statement.executeUpdate(updateQuery);
				System.out.println("update query result ::" + updateQueryResult);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally {
//	            DBUtil.close(rs);
	            DBUtil.close(statement);
	            DBUtil.close(connection);
			}
		}
		 String query = "SELECT CODE FROM "+ hkCatDB +".coupon WHERE offer_id IN (SELECT id FROM " + hkCatDB + ".offer WHERE offer_trigger_id = 4857 AND store_id = 9 AND end_dt > CURRENT_TIMESTAMP) AND allowed_times>1000;";
	        String couponCode = null;
	        ResultSet rs = null;
	        try {
	        	connFact = new ConnectionFactory();
	            connection = connFact.getConnection();
	            statement = connection.createStatement();
	            System.out.println("Query formed ::" + query);
	            rs = statement.executeQuery(query);
	            if(!rs.next())
	                return null;
	            couponCode = rs.getString("code");
	                
	            System.out.println("Otp : "+ couponCode);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBUtil.close(rs);
	            DBUtil.close(statement);
	            DBUtil.close(connection);
	        }
	        return couponCode;
	}

	/*
     * ## POS Retail Store Methods ##
     */
	
	public void addInventoryOnPos(String store) {
		String query = "UPDATE "+hkAquaDB+".sku_item si JOIN "+hkAquaDB+".sku_group sg JOIN "+hkAquaDB+".sku s " +  
				"ON s.id = sg.sku_id AND si.sku_group_id = sg.id " + 
				"AND s.warehouse_id =(SELECT id FROM "+hkAquaDB+".warehouse WHERE identifier = '"+store+"') AND si.update_dt >= DATE_SUB(CURDATE(), INTERVAL 2 MONTH) " + 
				"AND s.product_variant_id IN('NUT2053-03','HNUT7341-01','HNUT7750-01','HNUT7660-02','HNUT8002-01','HNUT8184-01','HNUT8257-01','NUT5220-01','HNUT7956-02','HNUT9059-01','HNUT7342-03','HNUT9122-01','NUT5282-01','NUT5282-02','NUT5135-01','NUT5135-02') " + 
				"SET si.sku_item_status_id = 10 " + 
				"WHERE si.sku_item_status_id =20 AND si.sku_item_owner_id=10;";
		System.out.println("Query formed ::" + query);		
		ResultSet rs = null;
		
        try {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();            
            statement.addBatch(query);
            System.out.println();
            System.out.println("Inventory Added on POS :: " + statement.executeUpdate(query));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
	}

	public String getBarcodeForPOS(String NutVariantID, String store) {		
		String barcode = null;
		
		for(String nutId:NutVariantID.trim().split(",")) {
			comnFunc.reportLogAndPrintInConsole("Searching barcode for variant id : " + nutId);
			String query = "SELECT si.barcode FROM "+ hkAquaDB+".sku_item si JOIN "+hkAquaDB+".sku_group sg JOIN "+hkAquaDB+".sku s ON s.id =sg.sku_id "
					+ "AND si.sku_group_id = sg.id AND s.warehouse_id = (SELECT id FROM "+hkAquaDB+".warehouse WHERE identifier = '"+store+"') "
							+ "AND s.product_variant_id = '"+nutId+"' "
									+ "WHERE si.sku_item_status_id=10 AND si.sku_item_owner_id = 10 ORDER BY si.update_dt DESC LIMIT 1;";
	        ResultSet rs = null;
	        System.out.println("SQL Query formed ::"+query);
	        try {
	        	connFact = new ConnectionFactory();
	            connection = connFact.getConnection();
	            statement = connection.createStatement();
	            rs = statement.executeQuery(query);
	            if(!rs.next()) {
	            	comnFunc.reportLogAndPrintInConsole("No Barcode found to scan, seems product variant "+nutId+" is out of stock on store "+store+" ..!!");
	            	continue;
	            }
	            else {
	            	barcode = rs.getString("barcode");
		            System.out.println("Barcode fetched for POS scan for variant id : "+nutId+" :: "+ barcode);
		            break;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBUtil.close(rs);
	            DBUtil.close(statement);
	            DBUtil.close(connection);
	        }  
		}
		return barcode;
	}
	
	public void deleteSignUpUserDataForAPI(String mobileNumber, int storeId) 
	{
    	// Deleting user data from API tables.
    	
    	String query1 = "DELETE ua FROM " + hkCatDB + ".user_auth AS ua JOIN " + hkCatDB + ".user AS u ON ua.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query2 = "DELETE cu FROM " + hkCatDB + ".coupon_user AS cu JOIN " + hkCatDB + ".user AS u ON cu.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query3 = "DELETE ur FROM " + hkCatDB + ".user_role AS ur JOIN " + hkCatDB + ".user AS u ON ur.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query4 = "DELETE s FROM " + hkCatDB + ".subscription AS s JOIN " + hkCatDB + ".user AS u ON s.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query5 = "DELETE ua FROM " + hkCatDB + ".user_application AS ua JOIN " + hkCatDB + ".user AS u ON ua.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query6 = "DELETE url FROM " + hkCatDB + ".user_retail_location AS url JOIN " + hkCatDB + ".user AS u ON url.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query7 = "DELETE scli FROM " + hkCatDB + ".shopping_cart_line_item AS scli JOIN " + hkCatDB + ".shopping_cart AS sc on scli.shopping_cart_id = sc.id JOIN " +hkCatDB+ ".user AS u ON sc.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query8 = "DELETE sc FROM " + hkCatDB + ".shopping_cart AS sc JOIN " + hkCatDB + ".user AS u ON sc.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query9 = "DELETE ua FROM " + hkCatDB + ".user_answer AS ua JOIN " + hkCatDB + ".user AS u ON ua.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query10 = "DELETE ua FROM " + hkCatDB + ".user_address AS ua JOIN " + hkCatDB + ".user AS u ON ua.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query11 = "DELETE r FROM " + hkCatDB + ".review AS r JOIN " + hkCatDB + ".user AS u ON r.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query12 = "DELETE up FROM " + hkCatDB + ".user_point AS up JOIN " + hkCatDB + ".user AS u ON up.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query13 = "DELETE upb FROM " + hkCatDB + ".user_point_balance AS upb JOIN " + hkCatDB + ".user AS u ON upb.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query14 = "DELETE tt FROM " + hkCatDB + ".temp_token AS tt JOIN " + hkCatDB + ".user AS u ON tt.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query15 = "DELETE tpa FROM " + hkCatDB + ".third_party_auth AS tpa JOIN " + hkCatDB + ".user AS u ON tpa.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query16 = "DELETE ucp FROM " + hkCatDB + ".user_counselling_preference AS ucp JOIN " + hkCatDB + ".user AS u ON ucp.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query17 = "DELETE uo FROM " + hkCatDB + ".user_otp AS uo JOIN " + hkCatDB + ".user AS u ON uo.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query18 = "DELETE FROM " + hkCatDB + ".user WHERE store_id = "+storeId+" and contact_number = '" + mobileNumber + "';";
    	
    	System.out.println(query1);
		System.out.println(query2);
		System.out.println(query3);
		System.out.println(query4);
		System.out.println(query5);
		System.out.println(query6);
		System.out.println(query7);
		System.out.println(query8);
		System.out.println(query9);
		System.out.println(query10);
		System.out.println(query11);
		System.out.println(query12);
		System.out.println(query13);
		System.out.println(query14);
		System.out.println(query15);
		System.out.println(query16);
		System.out.println(query17);
		System.out.println(query18);
		
		ResultSet rs = null;
		try {
			connFact = new ConnectionFactory();
	        connection = connFact.getConnection(hkCatDB);
	        statement = connection.createStatement();
	        statement.addBatch(query1);
	        statement.addBatch(query2);
	        statement.addBatch(query3);
	        statement.addBatch(query4);
	        statement.addBatch(query5);
	        statement.addBatch(query6);
	        statement.addBatch(query7);
	        statement.addBatch(query8);
	        statement.addBatch(query9);
	        statement.addBatch(query10);
	
	        statement.addBatch(query11);
	        statement.addBatch(query12);
	        statement.addBatch(query13);
	        statement.addBatch(query14);
	        statement.addBatch(query15);
	        statement.addBatch(query16);
	        statement.addBatch(query17);
	        statement.addBatch(query18);
	
	        System.out.println();
	        System.out.println(statement.executeBatch());
	    } 
		catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtil.close(rs);
	        DBUtil.close(statement);
	        DBUtil.close(connection);
	    	}
	}
	
	public String verifyIfSignUpMobileExistsAPI(String mobileNumber, int storeId) { 
    	
        String query = "SELECT id FROM "+hkCatDB+".user WHERE store_id = "+storeId+" and contact_number = '" + mobileNumber + "';";
        String userId = null;
        ResultSet rs = null;
        try {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();
            System.out.println("Query formed ::" + query);
            rs = statement.executeQuery(query);
            if(!rs.next())
                return userId;
            userId = rs.getString("id");
            comnFunc.reportLogAndPrintInConsole("User ID Existed on API : "+ userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return userId;
    }
    
    public void deleteSignUpUserDataForRetail(String mobileNumber, int storeId) 
	{    	
    	// Deleting user data from RETAIL tables.
    	
    	String query19 = "DELETE uhr FROM " + hkAquaDB + ".user_has_role AS uhr JOIN " + hkAquaDB + ".user AS u ON uhr.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query20 = "DELETE ud FROM " + hkAquaDB + ".user_detail AS ud JOIN " + hkAquaDB + ".user AS u ON ud.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query21 = "DELETE a FROM " + hkAquaDB + ".address AS a JOIN " + hkAquaDB + ".user AS u ON a.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query22 = "DELETE ur FROM " + hkAquaDB + ".user_report AS ur JOIN " + hkAquaDB + ".user AS u ON ur.user_id = u.id AND u.contact_number = '" + mobileNumber + "' AND u.store_id= " +storeId+ ";";
    	String query23 = "DELETE FROM " + hkAquaDB + ".user WHERE store_id = "+storeId+" and contact_number = '" + mobileNumber + "';";
		
		System.out.println(query19);
		System.out.println(query20);
		System.out.println(query21);
		System.out.println(query22);
		System.out.println(query23);
		
		ResultSet rs = null;
		
        try {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection(hkAquaDB);
            statement = connection.createStatement();
            
            statement.addBatch(query19);
            statement.addBatch(query20);
            statement.addBatch(query21);
            statement.addBatch(query22);
            statement.addBatch(query23);
            System.out.println();
            System.out.println(statement.executeBatch());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        	}
        }
	
	public String verifyIfSignUpMobileExistsPOS(String mobileNumber, int storeId) {		
		String query = "SELECT id FROM "+hkAquaDB+".user WHERE store_id = "+storeId+" AND contact_number = '" + mobileNumber + "';";
        String userId = null;
        ResultSet rs = null;
        try {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();
            System.out.println("Query formed ::" + query);
            rs = statement.executeQuery(query);
            if(!rs.next())
                return userId;
            userId = rs.getString("id");
            comnFunc.reportLogAndPrintInConsole("User ID Existed on POS : "+ userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return userId;
	}

	public long getOmniChnlCustomerOrder(String gatewayOrdrId) 
	{
		String query = "SELECT id FROM "+hkAquaDB+".customer_order WHERE gateway_order_id = '"+gatewayOrdrId+"' ;";
        long cstmrOrdrId=0;
        ResultSet rs = null;
        try 
        {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();
            System.out.println("Query formed ::" + query);
            rs = statement.executeQuery(query);
            if(!rs.next())
                return cstmrOrdrId;
            cstmrOrdrId = rs.getLong("id");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return cstmrOrdrId;
	}
	
	public String getOmniChnlCustomerOrderItems(long cstmrOrdrId) 
	{
		String query = "SELECT GROUP_CONCAT(product_variant_id) AS PV_Ids FROM "+hkAquaDB+".customer_order_line_item WHERE customer_order_id = '"+cstmrOrdrId+"' AND variant_in_cart_as = 10 ;";
        String NutId=null;
        ResultSet rs = null;
        try 
        {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();
            System.out.println("Query formed ::" + query);
            rs = statement.executeQuery(query);
            if(!rs.next())
                return NutId;
            NutId = rs.getString("PV_Ids");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return NutId;
	}
	
	public String getOmniChnlHKVariantId(String store) 
	{
		String query = "SELECT sv.id, COUNT(si.barcode) AS cntOfBarcodes FROM "+hkAquaDB+".sku_item si JOIN "+hkAquaDB+".sku_group sg JOIN "+hkAquaDB+".sku s "
				+ "JOIN "+hkCatDB+".variant_inventory vi JOIN "+hkCatDB+".store_variant sv JOIN "+hkCatDB+".store_product sp "
				+ "ON s.id =sg.sku_id AND si.sku_group_id = sg.id AND s.warehouse_id=(SELECT id FROM "+hkAquaDB+".warehouse WHERE identifier = '"+store+"')"
				+ "AND s.product_variant_id = vi.wms_variant_id AND sv.old_variant_id = vi.wms_variant_id AND sv.store_product_id = sp.id "
				+ "AND sp.store_id = 1 AND sv.offer_price BETWEEN 100 AND 499 AND sv.shipping_primary >0 "
				+ "AND vi.inventory > 1 AND vi.vendor_id = 291 AND vi.location_code = 701 AND sv.id IN(73963,51778,78039,75997,78107,74720,74047,75695,77579,72769,78325) "
				+ "WHERE si.sku_item_status_id=10 AND si.sku_item_owner_id = 10 "
				+ "GROUP BY vi.wms_variant_id "
				+ "HAVING cntOfBarcodes > 5 "
				+ "ORDER BY cntOfBarcodes DESC LIMIT 1 ;"; // ,76960
        String svId=null;
        ResultSet rs = null;
        try 
        {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();
            System.out.println("Query formed ::" + query);
            System.out.println();
            rs = statement.executeQuery(query);
            if(!rs.next())
                return svId;
            svId = rs.getString("id");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return svId;
	}
	
	public List<OrderDataItems> verifyRetailOrderUsingGatewayID(String orderID) {   	
    	
    	String query = "SELECT bo.gateway_order_id,bo.amount,GROUP_CONCAT(cli.id) AS cart_line_item_id "
        		+ "FROM "+hkAquaDB+".base_order bo JOIN "+hkAquaDB+".cart_line_item cli ON cli.order_id = bo.id "
        		+ "WHERE bo.gateway_order_id = '"+orderID+"' GROUP BY 1;";
        
        ResultSet rs = null;
        List<OrderDataItems> orderDataItemsList = new ArrayList<OrderDataItems>();
	    try {
	    	connFact = new ConnectionFactory();
	        connection = connFact.getConnection();
	        statement = connection.createStatement();
	        System.out.println("Query formed ::" + query);
	        rs = statement.executeQuery(query);        
	        
	        while(rs.next()) {
	        	String gatewayOrderId = rs.getString("gateway_order_id");
	        	String cliIds = rs.getString("cart_line_item_id");
	        	Long amount = rs.getLong("amount");
	        	Long oprId = null;
	        	String oprLiId = null;
	        	int orderType = 0;
	        	
	        	OrderDataItems orderDataItems = new OrderDataItems(gatewayOrderId, amount, oprId, oprLiId, cliIds, orderType);
	        	orderDataItemsList.add(orderDataItems);
	        }
	        
		}catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	DBUtil.close(rs);
	        DBUtil.close(statement);
	        DBUtil.close(connection);
	    }
	    
	    return orderDataItemsList;	    
	}

	public int validatePOSBarcodeReturnCheckin(String barcode) {
		String query = "SELECT sku_item_status_id FROM "+hkAquaDB+".sku_item WHERE barcode = '"+barcode+"' ;";
        int skuItemStatusId=0;
        ResultSet rs = null;
        try 
        {
        	connFact = new ConnectionFactory();
            connection = connFact.getConnection();
            statement = connection.createStatement();
            System.out.println("Query formed ::" + query);
            rs = statement.executeQuery(query);
            if(!rs.next())
                return skuItemStatusId;
            skuItemStatusId = rs.getInt("sku_item_status_id");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return skuItemStatusId;
		
	}
}
