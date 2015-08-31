package com.sj.repository.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.ProductCategoryJson;
import com.sj.repository.service.Impl.ProductCategoryServiceImpl.Category;

public interface ProductCategoryService {

	public List<ProductCategory> findByParent(ProductCategory category);

	public ProductCategory findOne(Long id);

	public ProductCategory findOneActivate(Long id);

	public Set<ProductCategory> findAll();

	public Page<ProductCategory> findByParent(Pageable pageable,
			ProductCategory category);

	public List<ProductCategory> findByParentAndActivate(
			ProductCategory category, ActivateEnum activate);

	public List<ProductCategory> findAllSecondCategory(ActivateEnum activate);

	public ProductCategory findByIdAndParent(Long id, ProductCategory category);

	public ProductCategory save(ProductCategory category);

	public ProductCategory update(ProductCategory category);

	public List<ProductCategory> findSecondCategory(ProductCategory category);

	public List<ProductCategory> findByYQ();

	public void delete(Long id);

	public ProductCategory findByName(String name, ActivateEnum activate);

	public ProductCategory findActivateFirstCategoryById(Long id);

	public List<ProductCategory> findAllFirstCategory(ActivateEnum activate);

	public List<Category> ajaxFineByParent(ProductCategory category);

	public List<ProductCategoryJson> findByParentJson(ProductCategory pc);
}
