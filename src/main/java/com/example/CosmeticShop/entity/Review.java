package com.example.CosmeticShop.entity;

import java.sql.Date;

public class Review {
	private int id;
	private int uid;
	private int bid;
	private String username;
	private String content;
	private int rating;
	private String day;
	private String title;
	public String avatar;
	
	public Review() {
		super();
	}
	

	public Review(String username, String content, int rating, String day) {
		super();
		this.username = username;
		this.content = content;
		this.rating = rating;
		this.day = day;
	}
	
	public Review(int id, String username, String title, String content, int rating, String day) {
		super();
		this.username = username;
		this.title = title;
		this.content = content;
		this.rating = rating;
		this.day = day;
		this.id = id;
	}


	public Review(int uid, int bid, String content, int rating, String day) {
		super();
		this.uid = uid;
		this.bid = bid;
		this.content = content;
		this.rating = rating;
		this.day = day;
	}
	
	public Review(String username, String content, int rating, String day, String avatar) {
		super();
		this.username = username;
		this.content = content;
		this.rating = rating;
		this.day = day;
		this.avatar = avatar;
	}
	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	

	public String getAvatar() {
		return avatar;
	}


	public void setAva(String avatar) {
		this.avatar = avatar;
	}


	@Override
	public String toString() {
		return "Review [id=" + id + " uid=" + uid + ", bid=" + bid + ", ava=" + avatar + ", content=" + content + ", rating=" + rating + ", day=" + day
				+ "]";
	}
	
	
}
