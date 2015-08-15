package com.sj.model.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@DiscriminatorValue("pc")
public class ProductCategory extends Category implements Serializable{
	
	public static final String HQ="仪器";
	public static final String SJ="试剂";
	public static final String HC="耗材";
	public static final String FW="服务";
	public static final String ZT="专题";

	@OneToMany(mappedBy = "parent")
	private Set<ProductCategory> categories;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private ProductCategory parent;

	public ProductCategory() {
		super();
	}

	public ProductCategory(Long id) {
		super();
		this.id = id;
	}
	public ProductCategory(String name){
		super();
		this.name=name;
	}

	public static ProductCategory getFirst(String name) {
		ProductCategory pc = new ProductCategory();
		if(name.equals(HQ)){
			pc.setId(1l);
			pc.setName(HQ);
		}
		else if(name.equals(SJ)){
			pc.setId(2l);
			pc.setName(SJ);
		}
		else if(name.equals(HC)){
			pc.setId(3l);
			pc.setName(HC);
		}
		else if(name.equals(FW)){
			pc.setId(4l);
			pc.setName(FW);
		}
		return  pc;
	}

	public Set<ProductCategory> getCategories() {
		return categories;
	}

	public void setCategories(Set<ProductCategory> categories) {
		this.categories = categories;
	}

	public ProductCategory getParent() {
		return parent;
	}

	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}
}
