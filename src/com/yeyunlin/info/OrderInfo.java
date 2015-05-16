package com.yeyunlin.info;

public class OrderInfo {
	private String orderId;
	private String username;
	private int foodid;
	private int deskid;
	private String time;
	private String paytype;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getFoodid() {
		return foodid;
	}

	public void setFoodid(int foodid) {
		this.foodid = foodid;
	}

	public int getDeskid() {
		return deskid;
	}

	public void setDeskid(int deskid) {
		this.deskid = deskid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
}
