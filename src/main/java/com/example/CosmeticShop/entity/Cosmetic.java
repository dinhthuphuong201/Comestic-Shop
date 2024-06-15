package com.example.CosmeticShop.entity;

import java.sql.Date;

public class Cosmetic {
	private int stt;
	private int id;
	private String title;
	private String brand;
	private String description;
	private Date day;
	private int page;
	private int cid;
	private String category;
	private String image;
	private int quantity;
	private int sold;
	private int rating;
	private int price;
	public Cosmetic() {
		super();
	}
	
	
	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public Cosmetic(String title, String image, int sold, int rating) {
		super();
		this.title = title;
		this.image = image;
		this.sold = sold;
		this.rating = rating;
	}

	public Cosmetic(int id, String title, String brand, String description, Date day, int page, int cid, String image,
			int quantity, int sold) {
		super();
		this.id = id;
		this.title = title;
		this.brand = brand;
		this.description = description;
		this.day = day;
		this.page = page;
		this.cid = cid;
		this.image = image;
		this.quantity = quantity;
		this.sold = sold;
	}
	public Cosmetic(String title, String brand, String description, Date day, int page, int cid, String image,
			int quantity, int sold) {
		super();
		this.title = title;
		this.brand = brand;
		this.description = description;
		this.day = day;
		this.page = page;
		this.cid = cid;
		this.image = image;
		this.quantity = quantity;
		this.sold = sold;
	}
	
	
	
	public Cosmetic(int stt, String title, String brand, Date day, int page, String category, int sold) {
		super();
		this.stt = stt;
		this.title = title;
		this.brand = brand;
		this.day = day;
		this.page = page;
		this.category = category;
		this.sold = sold;
	}


	public Cosmetic(int id, String title, String brand, String description, Date day, int page, String category,
			String image, int quantity, int sold, int cid, int price) {
		super();
		this.id = id;
		this.title = title;
		this.brand = brand;
		this.description = description;
		this.day = day;
		this.page = page;
		this.category = category;
		this.image = image;
		this.quantity = quantity;
		this.sold = sold;
		this.cid = cid;
		this.price = price;
	}
	
	public Cosmetic(int id, String title, String brand, String category, Date day, int quantity, int sold ) {
		super();
		this.id = id;
		this.title = title;
		this.brand = brand;
		this.day = day;
		this.quantity = quantity;
		this.category = category;
		this.sold = sold;
	}
	
	public Cosmetic(int id, String title, String brand, String description, Date day, int price, int cid, String image,
			int quantity, int sold, int page) {
		super();
		this.id = id;
		this.title = title;
		this.brand = brand;
		this.description = description;
		this.day = day;
		this.page = page;
		this.cid = cid;
		this.image = image;
		this.quantity = quantity;
		this.sold = sold;
		this.price=price;
	}
	
	public Cosmetic(int price, String title, String image, int quantity) {
		this.price = price;
		this.title = title;
		this.image = image;
		this.quantity = quantity;
	}
	
	
	
	public int getStt() {
		return stt;
	}


	public void setStt(int stt) {
		this.stt = stt;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	@Override
	public String toString() {
		return "Cosmetic [id=" + id + ", title=" + title + ", brand=" + brand + ", description=" + description + ", day="
				+ day + ", page=" + page + ", cid=" + cid + ", category=" + category + ", image=" + image + ", quantity=" + quantity + ", sold="
				+ sold + "]";
	}
	
	
}
