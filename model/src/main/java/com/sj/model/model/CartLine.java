package com.sj.model.model;

import java.util.Calendar;

import com.sj.model.type.PlaceEnum;

public class CartLine {
	private Long id;
	private Long productId;
	private String name;
	private int number;
	private float price;
	private String image;
	private String brandName;
	private String model;
	private PlaceEnum place;
	private boolean check;
	private float count;

	public CartLine() {
		super();
	}

	public CartLine(String id, String name, String price,
			String number,String image,String brandName,String model,String place,String productId,String check) {
		super();
		this.id = Long.valueOf(id);
		this.name = name;
		this.price = Float.valueOf(price);
		this.number = Integer.valueOf(number);
		this.image = image;
		this.brandName=brandName;
		this.place=PlaceEnum.valueOf(place);
		this.model = model;
		this.check=Boolean.valueOf(check);
		this.productId=Long.valueOf(productId);
		this.count = this.number * this.price;
	}

	public CartLine(Product p,int number) {
		this.id =Calendar.getInstance().getTime().getTime();
		this.productId = p.getId();
		this.name = p.getName();
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
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

	public float getCount() {
		return count;
	}

	public void setCount(float count) {
		this.count = count;
	}

}