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

	public static ProductCategory getFirst(String name) {
		ProductCategory pc = new ProductCategory();
		if(name.equals("仪器")){
			pc.setId(1l);
			pc.setName("仪器");
		}
		else if(name.equals("试剂")){
			pc.setId(2l);
			pc.setName("试剂");
		}
		else if(name.equals("耗材")){
			pc.setId(3l);
			pc.setName("耗材");
		}
		else if(name.equals("服务")){
			pc.setId(4l);
			pc.setName("服务");
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
