package com.sj.repository.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.CategoryJson;
import com.sj.repository.model.ProductCategoryDetailJson;
import com.sj.repository.model.ProductCategoryJson;

public interface ProductCategoryService {

	public List<ProductCategory> findByParent(ProductCategory category);

	public ProductCategory findOne(Long id);

	public ProductCategory findOneActivate(Long id);

	public Page<ProductCategory> findByParent(Pageable pageable,
			ProductCategory category);

	public List<ProductCategory> findByParentAndActivate(ProductCategory category, ActivateEnum activate);

	public ProductCategory save(ProductCategory category);

	public ProductCategory update(ProductCategory category);

	public List<ProductCategory> findSecondCategory(ProductCategory category);

	public List<ProductCategory> findAllFirstCategory(ActivateEnum activate);

	public List<ProductCategoryJson> findByParentJson(ProductCategory pc);

	public List<ProductCategoryDetailJson> findAllDetail();

	public List<ProductCategory> findSiblings(ProductCategory category);

	public ProductCategory updateState(Long id, ActivateEnum activate);
	
	public ProductCategory findByIdAndParent(Long id);

	List<CategoryJson> ajaxFindByParent(ProductCategory productCategory);
	
	public Map<String, List<CategoryJson>> findAllShowOnHead();
}
