package com.sj.model.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("r")
public class Reagents extends Product implements Serializable{
	public Reagents(){
		
	}
	public Reagents(Long id){
		super(id);
	}
	public Reagents(Product product){
		super(product);
	}
}
