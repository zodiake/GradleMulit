package com.sj.model.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//仪器
@Entity
@DiscriminatorValue("i")
public class Instrument extends Product {
	public Instrument() {

	}

	public Instrument(Long id) {
		super(id);
	}
	
	public Instrument(Product product){
		super(product);
	}
}
