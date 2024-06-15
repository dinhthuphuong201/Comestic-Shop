package com.example.CosmeticShop.entity;

public class Category {
	private int id;
	private String name;
	private String img;
	private int sum;
	
	public Category() {
		super();
	}

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Category(int id, String name, String img) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
	}
	
	public Category(int id, String name, String img, int sum) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
		this.sum = sum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name +  " ,img=" + img + " ,sum=" + sum + "]";
	}
	
	
}
