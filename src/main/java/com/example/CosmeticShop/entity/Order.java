package com.example.CosmeticShop.entity;

import java.sql.Date;

public class Order {
	private int stt;
	private int id;
	private int uid;
	private int quantity;
	private String day;
	private int totalMoney;
	private int status;
	private String username;
	
	public Order() {
		
	}
	
	public Order(int id, int quantity, String day, int status, String username) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.day = day;
		this.status = status;
		this.username = username;
	}
	
	public Order(int id, int quantity, String day, int status, String username, int totalMoney) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.day = day;
		this.status = status;
		this.username = username;
		this.totalMoney = totalMoney;
	}

	public Order(int id,int uid, int quantity, String day, int status) {
		super();
		this.id = id;
		this.uid = uid;
		this.quantity = quantity;
		this.day = day;
		this.status = status;
	}
	
	public Order(int id, int quantity, String day, int status, int totalMoney) {
		super();
		this.totalMoney = totalMoney;
		this.id = id;
		this.quantity = quantity;
		this.day = day;
		this.status = status;
	}
	
	public Order(int id, int uid, int quantity, String day, int status, int totalMoney) {
		super();
		this.totalMoney = totalMoney;
		this.id = id;
		this.uid = uid;
		this.quantity = quantity;
		this.day = day;
		this.status = status;
	}

	public Order(int id, int quantity, String day, int status) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.day = day;
		this.status = status;
	}
	
	
	public Order(int stt, int id,  String username, String day,int quantity, int status) {
		super();
		this.stt = stt;
		this.id = id;
		this.quantity = quantity;
		this.day = day;
		this.status = status;
		this.username = username;
	}
	
	public Order(int stt, int id,  String username, String day,int quantity, int status, int totalMoney) {
		super();
		this.stt = stt;
		this.id = id;
		this.quantity = quantity;
		this.day = day;
		this.status = status;
		this.username = username;
		this.totalMoney = totalMoney;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", uid=" + uid + ", quantity=" + quantity + ", day=" + day + ", totalMoney="
				+ totalMoney + ", status=" + status + ", username=" + username + "]";
	}
 
	
}
