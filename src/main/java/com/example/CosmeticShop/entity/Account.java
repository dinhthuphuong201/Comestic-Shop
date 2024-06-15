package com.example.CosmeticShop.entity;

public class Account {
	private int id;
	private String username;
	private String password;
	private String email;
	private int duty;
	private String avatar;
	private String firstname;
	private String surname;
	private String district;
	private String city;
	
	public Account() {
		super();
	}
	
	public Account(int id, String username, String password, String email, int duty) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.duty = duty;
	}
	
	
	public Account(int id, String username, String password, String email, int duty, String avatar, String firstname,
			String surname, String district, String city) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.duty = duty;
		this.avatar = avatar;
		this.firstname = firstname;
		this.surname = surname;
		this.district = district;
		this.city = city;
	}


	public Account(String username, String password, String email, int duty) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.duty = duty;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDuty() {
		return duty;
	}
	public void setDuty(int duty) {
		this.duty = duty;
	}

	
	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", duty=" + duty + ", avata=" + avatar + ", firstname=" + firstname + ", surname=" + surname + ", district=" + district + ", city=" + city + "]";
	}
	
}
