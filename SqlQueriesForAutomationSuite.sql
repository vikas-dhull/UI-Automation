-- ## update google client id for social login ## --
UPDATE hk_cat.store_config_values SET config_value = '703284299857-svb4fr6k32uadoc6vjoandsna0nbktml.apps.googleusercontent.com' WHERE config_key='GOOGLE_CLIENT_ID_1' AND store_id = 1;
UPDATE hk_cat.store_config_values SET config_value = '703284299857-svb4fr6k32uadoc6vjoandsna0nbktml.apps.googleusercontent.com' WHERE config_key='GOOGLE_CLIENT_ID_2' AND store_id = 1;

-- ## enable Coupon Offer ## --
UPDATE hk_cat.offer SET offer_type_id = 2, end_dt = '2039-08-10 13:00:00', allowed_times_per_user_id='1000000' WHERE id = 7512;

-- ## enable B1G1 Offer ## --
UPDATE hk_cat.offer SET end_dt = '2039-08-10 13:00:00' WHERE id = 12504;

-- ## enable Prompt Offer ## --
UPDATE hk_cat.offer SET end_dt = '2039-08-10 13:00:00', allowed_times_per_user_id='1000000', disabled = '0' WHERE id = 12103;

-- ## Add HK cash to user ## --

-- HKWEB Full HK CASH --
INSERT IGNORE INTO hk_cat.user_point (user_id, VALUE, user_point_type, source_entity_id, user_point_mode_id, user_point_status_id, user_point_txn_type_id, parent_user_point_id, expiry_dt, update_dt, referred_user_id, approver_id, txn_dt, reason_id, from_store_id, to_store_id, COMMENT, hk_order_id, opr_li_status_id, VERSION, approved_dt) VALUES('20384861','5000','10','HK_SOURCE_ENTITY','30','20','1',NULL,'2029-12-28 23:59:59','2017-09-26 11:20:46',NULL,'15923131','2017-09-26 11:19:55','90','1','1','test..',NULL,NULL,'1',CURRENT_TIMESTAMP);

INSERT IGNORE INTO hk_cat.user_point_balance (user_id, reward_point_balance, loyalty_point_balance, store_id, VERSION) VALUES('20384861','5000','0','1','1');

UPDATE hk_cat.user_point_balance SET reward_point_balance = 5000,VERSION=1 WHERE user_id = 20384861;

--HKWEB Partial HK CASH --
INSERT IGNORE INTO hk_cat.user_point (user_id, VALUE, user_point_type, source_entity_id, user_point_mode_id, user_point_status_id, user_point_txn_type_id, parent_user_point_id, expiry_dt, update_dt, referred_user_id, approver_id, txn_dt, reason_id, from_store_id, to_store_id, COMMENT, hk_order_id, opr_li_status_id, VERSION, approved_dt) VALUES('23038296','1500','10','HK_SOURCE_ENTITY','30','20','1',NULL,'2029-12-28 23:59:59','2017-12-17 11:20:46',NULL,'15923131','2017-12-17 11:19:55','90','1','1','test..',NULL,NULL,'1',CURRENT_TIMESTAMP);

INSERT IGNORE INTO hk_cat.user_point_balance (user_id, reward_point_balance, loyalty_point_balance, store_id, VERSION) VALUES('23038296','1500','0','1','1');

UPDATE hk_cat.user_point_balance SET reward_point_balance = 1500,VERSION=1 WHERE user_id = 23038296;

-- HKWebMsite Full HK CASH --
INSERT IGNORE INTO hk_cat.user_point (user_id, VALUE, user_point_type, source_entity_id, user_point_mode_id, user_point_status_id, user_point_txn_type_id, parent_user_point_id, expiry_dt, update_dt, referred_user_id, approver_id, txn_dt, reason_id, from_store_id, to_store_id, COMMENT, hk_order_id, opr_li_status_id, VERSION, approved_dt) VALUES('23037825','10000','10','HK_SOURCE_ENTITY','30','20','1',NULL,'2029-12-28 23:59:59','2017-09-26 11:20:46',NULL,'15923131','2017-09-26 11:19:55','90','1','1','test..',NULL,NULL,'1',CURRENT_TIMESTAMP);

INSERT IGNORE INTO hk_cat.user_point_balance (user_id, reward_point_balance, loyalty_point_balance, store_id, VERSION) VALUES('23037825','10000','0','1','1');

UPDATE hk_cat.user_point_balance SET reward_point_balance = 10000,VERSION=1 WHERE user_id = 23037825;

-- HKWebMsite Partial HK CASH --
INSERT IGNORE INTO hk_cat.user_point (user_id, VALUE, user_point_type, source_entity_id, user_point_mode_id, user_point_status_id, user_point_txn_type_id, parent_user_point_id, expiry_dt, update_dt, referred_user_id, approver_id, txn_dt, reason_id, from_store_id, to_store_id, COMMENT, hk_order_id, opr_li_status_id, VERSION, approved_dt) VALUES('23037463','1000','10','HK_SOURCE_ENTITY','30','20','1',NULL,'2029-12-28 23:59:59','2017-09-26 11:20:46',NULL,'15923131','2017-09-26 11:19:55','90','1','1','test..',NULL,NULL,'1',CURRENT_TIMESTAMP);

INSERT IGNORE INTO hk_cat.user_point_balance (user_id, reward_point_balance, loyalty_point_balance, store_id, VERSION) VALUES('23037463','1000','0','1','1');

UPDATE hk_cat.user_point_balance SET reward_point_balance = 1000,VERSION=1 WHERE user_id = 23037463;

-- HK-Android App Full HK CASH --
INSERT IGNORE INTO hk_cat.user_point (user_id, VALUE, user_point_type, source_entity_id, user_point_mode_id, user_point_status_id, user_point_txn_type_id, parent_user_point_id, expiry_dt, update_dt, referred_user_id, approver_id, txn_dt, reason_id, from_store_id, to_store_id, COMMENT, hk_order_id, opr_li_status_id, VERSION, approved_dt) VALUES('23037891','10000','10','HK_SOURCE_ENTITY','30','20','1',NULL,'2029-12-28 23:59:59','2017-09-26 11:20:46',NULL,'15923131','2017-09-26 11:19:55','90','1','1','test..',NULL,NULL,'1',CURRENT_TIMESTAMP);

INSERT IGNORE INTO hk_cat.user_point_balance (user_id, reward_point_balance, loyalty_point_balance, store_id, VERSION) VALUES('23037891','10000','0','1','1');

UPDATE hk_cat.user_point_balance SET reward_point_balance = 10000,VERSION=1 WHERE user_id = 23037891;

-- HK-Android App Partial HK CASH --
INSERT IGNORE INTO hk_cat.user_point (user_id, VALUE, user_point_type, source_entity_id, user_point_mode_id, user_point_status_id, user_point_txn_type_id, parent_user_point_id, expiry_dt, update_dt, referred_user_id, approver_id, txn_dt, reason_id, from_store_id, to_store_id, COMMENT, hk_order_id, opr_li_status_id, VERSION, approved_dt) VALUES('23037521','1000','10','HK_SOURCE_ENTITY','30','20','1',NULL,'2029-12-28 23:59:59','2017-09-26 11:20:46',NULL,'15923131','2017-09-26 11:19:55','90','1','1','test..',NULL,NULL,'1',CURRENT_TIMESTAMP);

INSERT IGNORE INTO hk_cat.user_point_balance (user_id, reward_point_balance, loyalty_point_balance, store_id, VERSION) VALUES('23037521','1000','0','1','1');

UPDATE hk_cat.user_point_balance SET reward_point_balance = 1000,VERSION=1 WHERE user_id = 23037521;

-- ## Make User ready for Signup ## --
UPDATE hk_cat.user SET contact_number='1111111111', is_number_verified='0' WHERE contact_number = '6000000001';

-- Prapare Product Variant data --
UPDATE hk_cat.store_variant SET discount_percent='0.9990', bool_bitset = '36525409284485' WHERE id = 72738;
UPDATE hk_cat.store_variant SET discount_percent='0.9990', bool_bitset = '36525409284485' WHERE id = 71420;
UPDATE store_variant SET bool_bitset ='109576227853455' WHERE id =72367;
UPDATE store_variant SET bool_bitset ='109576227853455' WHERE id =78396;
UPDATE store_variant SET bool_bitset ='109576227853455' WHERE id =45108;

UPDATE variant_inventory SET inventory = 500 WHERE wms_variant_id IN ( 'NUT1367-39','HNUT9120-01','NUT5348-01','HNUT236-01','NUT3599-14','NUT3599-22') AND location_code = 601 AND vendor_id = 291;
UPDATE store_variant SET out_of_stock = 0 WHERE id IN (45108,78396,78322,72738,71420,72367);

-- ## Create table for Read OTP # --
CREATE TABLE hk_cat.read_otp (id INT(11) NOT NULL AUTO_INCREMENT,domain VARCHAR(80) DEFAULT NULL,otp INT(11) NOT NULL,create_dt DATETIME NOT NULL,PRIMARY KEY (id));
