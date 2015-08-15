package com.sj.repository.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sj.model.model.Brand;
import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Provider;
import com.sj.model.type.ProductStatusEnum;

public interface ProductService {
	public Page<Product> findByUsers(Provider user, Pageable pageable,
			String original);

	public Product findOne(Long id);

	public void addViewCount(Long id);

	public Product addOneProduct(Product product);

	public Product findOneByUser(Provider user, Long id);

	public Product updateProduct(Product newProduct, Product oldProduct);

	public void offProduct(Product product);

	public Page<Product> findByBrand(Pageable pageable, Brand brand);

	public Page<Product> findByCategory(ProductCategory category,
			Pageable pageable);

	public Page<Product> search(Specification<Product> sp, Pageable pageable);

	public Product updateStatus(Product product, ProductStatusEnum status);

	public List<String> saveProducts(List<Product> products);
	
	public Product saveOne(Product product);
}
