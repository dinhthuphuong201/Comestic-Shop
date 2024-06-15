package com.example.CosmeticShop.entity;

public class OderLine {
	private int oid;
	private int bid;
	private int quantity;
	private String image;
	private String title;
	private String brand;
	private int price;
	
	public OderLine(int oid, int bid, int quantity) {
		super();
		this.oid = oid;
		this.bid = bid;
		this.quantity = quantity;
	}
	
	

	public OderLine(int oid, int bid,  String image, String title, String brand,int quantity) {
		super();
		this.oid = oid;
		this.bid = bid;
		this.quantity = quantity;
		this.image = image;
		this.title = title;
		this.brand = brand;
	}
	
	public OderLine(int price, int oid, int bid,  String image, String title, String brand,int quantity) {
		super();
		this.oid = oid;
		this.bid = bid;
		this.quantity = quantity;
		this.image = image;
		this.title = title;
		this.brand = brand;
		this.price= price;
	}



	public OderLine( String image, String title, String brand,int quantity) {
		super();
		this.quantity = quantity;
		this.image = image;
		this.title = title;
		this.brand = brand;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	@Override
	public String toString() {
		return "OderLine [oid=" + oid + ", bid=" + bid + ", quantity=" + quantity + ", image=" + image + ", title="
				+ title + ", brand=" + brand + "]";
	}
	
}
