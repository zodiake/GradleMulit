package com.sj.model.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//仪器
@Entity
@DiscriminatorValue("i")
public class Instrument extends Product {
	public Instrument(){
		
	}
	public Instrument(Long id){
		super(id);
	}
	public Instrument(Product product){
		this.brand=product.getBrand();
		this.content = product.getContent();
		this.coverImg=product.getCoverImg();
		this.createdBy = product.getCreatedBy();
		this.firstCategory=product.getFirstCategory();
		this.label = product.getLabel();
		this.model = product.getModel();
		this.name = product.getName();
		this.nameEnglish = product.getNameEnglish();
		this.placeOfProduction = product.getPlaceOfProduction();
		this.price=product.getPrice();
		this.secondCategory = product.getSecondCategory();
		this.serialNO = product.getSerialNO();
		this.specifications = product.getSpecifications();
		this.status = product.getStatus();
		this.thirdCategory = product.getThirdCategory();
	}
}
