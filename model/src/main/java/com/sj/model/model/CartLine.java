package com.sj.model.model;

import java.util.Calendar;

import com.sj.model.type.PlaceEnum;

public class CartLine {
	private Long id;
	private Long productId;
	private String url;
	private String name;
	private int number;
	private double price;
	private String image;
	private String brandName;
	private String model;
	private PlaceEnum place;
	private boolean check;

	public CartLine() {
		super();
	}

	public CartLine(String id, String url, String name, String price,
			String number,String image,String brandName,String model,String place,String productId,String check) {
		this.id = Long.valueOf(id);
		this.url = url;
		this.name = name;
		this.price = Double.valueOf(price);
		this.number = Integer.valueOf(number);
		this.image = image;
		this.brandName=brandName;
		this.place=PlaceEnum.valueOf(place);
		this.model = model;
		this.check=Boolean.valueOf(check);
		this.productId=Long.valueOf(productId);
	}

	public CartLine(Product p,int number) {
		this.id =Calendar.getInstance().getTime().getTime();
		this.productId = p.getId();
		this.name = p.getName();
		this.url = p.getUrl();
		this.price = p.getPrice();
		this.image = p.getCoverImg();
		this.brandName=p.getBrand().getName();
		this.place = p.getPlaceOfProduction();
		this.model = p.getModel();
		this.place=p.getPlaceOfProduction();
		this.number = number;
		this.check=true;
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


	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public PlaceEnum getPlace() {
		return place;
	}

	public void setPlace(PlaceEnum place) {
		this.place = place;
	}

	public boolean getCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

}