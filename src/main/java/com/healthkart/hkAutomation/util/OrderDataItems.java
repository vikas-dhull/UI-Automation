package com.healthkart.hkAutomation.util;

public class OrderDataItems {

	private String gatewayOrderId;
	private Long amount;
	private Long oprId;
	private String oprLiId;
	private String cliIds;
	private int orderType;
	
	
	public OrderDataItems() {
		
	}
	
	public OrderDataItems(String gatewayOrderId, Long amount, Long oprId, String oprLiId, String cliIds, int orderType) {
		super();
		this.gatewayOrderId = gatewayOrderId;
		this.amount = amount;
		this.oprId = oprId;
		this.oprLiId = oprLiId;
		this.cliIds = cliIds;
		this.orderType = orderType;
	}
	
	public String getGatewayOrderId() {
		return gatewayOrderId;
	}
	public void setGatewayOrderId(String gatewayOrderId) {
		this.gatewayOrderId = gatewayOrderId;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getOprId() {
		return oprId;
	}
	public void setOprId(Long oprId) {
		this.oprId = oprId;
	}
	public String getOprLiId() {
		return oprLiId;
	}
	public void setOprLiId(String oprLiId) {
		this.oprLiId = oprLiId;
	}
	public String getCliIds() {
		return cliIds;
	}
	public void setCliIds(String cliIds) {
		this.cliIds = cliIds;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public int getOrderType() {
		return orderType;
	}
	
	
	
}
