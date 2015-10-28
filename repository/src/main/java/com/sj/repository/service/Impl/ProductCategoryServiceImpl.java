package com.sj.repository.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.CategoryJson;
import com.sj.repository.model.ProductCategoryDetailJson;
import com.sj.repository.model.ProductCategoryJson;
import com.sj.repository.repository.ProductCategoryRepository;
import com.sj.repository.service.ProductCategoryService;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryRepository repository;

	@Override
	public ProductCategory findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Page<ProductCategory> findByParent(Pageable pageable,
			ProductCategory category) {
		return repository.findByParent(pageable, category);
	}

	@Override
	@CacheEvict(value = { "secondProductCategoriesCache",
			"indexSecondProductCategoryCache", "secondJsonCategoriesCache",
			"productCategoryCache" })
	public ProductCategory save(ProductCategory category) {
		ProductCategory pc = new ProductCategory();
		pc.setName(category.getName());
		pc.setParent(category.getParent());
		pc.setActivate(ActivateEnum.ACTIVATE);
		pc.setCreatedTime(Calendar.getInstance());
		pc.setUpdatedTime(Calendar.getInstance());
		return repository.save(pc);
	}

	@Override
	@CacheEvict(value = { "secondProductCategoriesCache",
			"indexSecondProductCategoryCache", "secondJsonCategoriesCache",
			"productCategoryCache" })
	public ProductCategory update(ProductCategory category) {
		ProductCategory memory = repository.findOne(category.getId());
		memory.setName(category.getName());
		memory.setActivate(ActivateEnum.ACTIVATE);
		memory.setParent(category.getParent());
		memory.setUpdatedBy(category.getUpdatedBy());
		memory.setUpdatedTime(Calendar.getInstance());
		return repository.save(memory);
	}

	@Override
	@Cacheable(value = "secondProductCategoriesCache", key = "#category.id")
	public List<ProductCategory> findByParentAndActivate(
			ProductCategory category, ActivateEnum activate) {
		List<ProductCategory> categories = repository.findByParentAndActivate(category, activate);
		for (ProductCategory productCategory : categories) {
			List<ProductCategory> thirds = repository.findByParentAndActivate(productCategory, activate);
			productCategory.setCategories(thirds);
		}
		return categories;
	}

	@Override
	public List<ProductCategory> findSecondCategory(ProductCategory category) {
		return repository.findByParentAndActivate(category,ActivateEnum.ACTIVATE);
	}

	@Override
	@Cacheable(value = "firstProductCategoryCache", key = "#id")
	public ProductCategory findOneActivate(Long id) {
		return repository.findByIdAndActivate(id, ActivateEnum.ACTIVATE);
	}

	@Override
	@Cacheable(value = "firstProductCategoriesCache")
	public List<ProductCategory> findAllFirstCategory(ActivateEnum activate) {
		return repository.findByParentIsNullAndActivate(activate);
	}

	@Override
	public List<ProductCategory> findByParent(ProductCategory category) {
		return repository.findByParentAndActivate(category,
				ActivateEnum.ACTIVATE);
	}

	@Override
	@Cacheable(value = "secondJsonCategoriesCache", key = "#productCategory.id")
	public List<CategoryJson> ajaxFindByParent(ProductCategory productCategory) {
		List<ProductCategory> productCategories = findByParentAndActivate(
				productCategory, ActivateEnum.ACTIVATE);
		List<CategoryJson> categories = new ArrayList<CategoryJson>();
		productCategories.stream().map(r -> {
			CategoryJson category = new CategoryJson(r.getId(), r.getName());
			return category;
		}).forEach(i -> categories.add(i));
		return categories;
	}

	@Override
	public List<ProductCategoryJson> findByParentJson(ProductCategory pc) {
		return repository.findByParentOrderByNameAsc(pc).stream()
				.map(m -> new ProductCategoryJson(m))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductCategoryDetailJson> findAllDetail() {
		List<ProductCategory> lists = Lists.newArrayList(repository
				.findByParentOrderByNameAsc(null));
		return lists.stream().map(p -> new ProductCategoryDetailJson(p))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductCategory> findSiblings(ProductCategory category) {
		ProductCategory c = repository.findOne(category.getId());
		return Lists.newArrayList(repository.findOne(c.getParent().getId())
				.getCategories());
	}

	@Override
	@CacheEvict(value = { "secondProductCategoriesCache",
			"indexSecondProductCategoryCache", "secondJsonCategoriesCache",
			"productCategoryCache" })
	public ProductCategory updateState(Long id, ActivateEnum activate) {
		ProductCategory pc = repository.findOne(id);
		pc.setActivate(activate);
		pc.setUpdatedTime(Calendar.getInstance());
		return pc;
	}

	@Override
	@Cacheable(value = "productCategoryCache", key = "#id")
	public ProductCategory findByIdAndParent(Long id) {
		return repository.findByIdAndActivateAndParentIsNull(id,
				ActivateEnum.ACTIVATE);
	}

	@Override
	@Cacheable(value = "indexSecondProductCategoryCache")
	public Map<String, List<CategoryJson>> findAllShowOnHead() {
		Map<String, List<CategoryJson>> maps = new HashMap<String, List<CategoryJson>>();
		List<ProductCategory> firstCategories = findAllFirstCategory(ActivateEnum.ACTIVATE);
		for (int i = 0,len = firstCategories.size(); i < len; i++) {
			List<ProductCategory> pcs = repository.findByParentAndActivate(firstCategories.get(i),ActivateEnum.ACTIVATE);
			List<CategoryJson> jsons = new ArrayList<CategoryJson>();
			for (int j = 0,jlen = pcs.size(); j < jlen; j++) {
				ProductCategory pc = pcs.get(j);
				jsons.add(new CategoryJson(pc.getId(),pc.getName()));
			}
			maps.put(String.valueOf(i+1), jsons);
		}
		return maps;
	}
}
