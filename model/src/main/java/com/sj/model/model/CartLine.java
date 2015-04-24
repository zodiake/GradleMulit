package com.sj.model.model;

public class CartLine {
	private Long id;
	private String url;
	private String name;
	private int number;
	private double price;

	public CartLine() {
		super();
	}

	public CartLine(Product p) {
		this.id = p.getId();
		this.name = p.getName();
		this.url = p.getUrl();
		this.price = p.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}