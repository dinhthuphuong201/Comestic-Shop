package com.example.CosmeticShop.entity;

public class Ship {
	private int oid;
	private int uid;
	private String name;
	private String address;
	private String email;
	private String phone;
	private String mess;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}
	public Ship(int oid, int uid, String name, String address, String email, String phone, String mess) {
		super();
		this.oid = oid;
		this.uid = uid;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.mess = mess;
	}
	
	public Ship() {
		super();
	}
	
	@Override
	public String toString() {
		return "Ship [oid=" + oid + ", uid=" + uid + ", name=" + name + ", address=" + address + ", email="
				+ email + ", phone=" + phone + ", mess=" + mess + "]";
	}
	
}
