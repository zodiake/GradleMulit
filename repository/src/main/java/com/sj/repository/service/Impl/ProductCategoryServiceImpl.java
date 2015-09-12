package com.sj.repository.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;
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
	@Transactional(readOnly = true)
	public List<ProductCategory> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Page<ProductCategory> findByParent(Pageable pageable,
			ProductCategory category) {
		return repository.findByParent(pageable, category);
	}

	@Override
	public ProductCategory save(ProductCategory category) {
		ProductCategory pc = new ProductCategory();
		pc.setName(category.getName());
		pc.setParent(category.getParent());
		pc.setActivate(ActivateEnum.ACTIVATE);
		pc.setCreatedTime(Calendar.getInstance());
		return repository.save(pc);
	}

	@Override
	public ProductCategory findByIdAndParent(Long id, ProductCategory category) {
		return repository.findByIdAndParent(id, category);
	}

	@Override
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
	public List<ProductCategory> findByParentAndActivate(ProductCategory category, ActivateEnum activate) {
		return repository.findByParentAndActivate(category, activate);
	}

	@Override
	public List<ProductCategory> findAllSecondCategory(ActivateEnum activate) {
		List<ProductCategory> categories = repository.findByParentAndActivate(
				null, activate);
		List<ProductCategory> results = new LinkedList<>();
		categories.stream().forEach(
				(c) -> c.getCategories().forEach((i) -> results.add(i)));
		return results;
	}

	@Override
	@Cacheable(value = "secondProductCategoryCache", key = "#category.id")
	public List<ProductCategory> findSecondCategory(ProductCategory category,Pageable pageable) {
		return repository.findByParentAndActivate(category,ActivateEnum.ACTIVATE,pageable);
	}

	@Override
	public void delete(Long id) {
		ProductCategory pc = repository.findOne(id);
		if (pc.getCategories() == null || pc.getCategories().size() == 0) {

		} else {
			List<ProductCategory> pcs = pc.getCategories();
			for (ProductCategory productCategory : pcs) {
				repository.delete(productCategory);
			}
		}
		repository.delete(id);
	}

	@Override
	public ProductCategory findOneActivate(Long id) {
		return repository.findByIdAndActivate(id, ActivateEnum.ACTIVATE);
	}

	@Override
	@Cacheable(value = "productCategoryCache", key = "#id")
	public ProductCategory findActivateFirstCategoryById(Long id) {
		return repository.findByIdAndActivate(id, ActivateEnum.ACTIVATE);
	}

	@Override
	@Cacheable(value = "productCategoryCache")
	public List<ProductCategory> findAllFirstCategory(ActivateEnum activate) {
		return repository.findByParentIsNullAndActivate(activate);
	}

	@Override
	@Cacheable(value = "productCategoryCache", key = "#name")
	public ProductCategory findByName(String name, ActivateEnum activate) {
		return repository.findByNameAndActivate(name, activate);
	}

	@Override
	public List<ProductCategory> findByParent(ProductCategory category) {
		return repository.findByParentAndActivate(category,
				ActivateEnum.ACTIVATE);
	}

	@Override
	public List<Category> ajaxFineByParent(ProductCategory productCategory) {
		List<ProductCategory> productCategories = repository
				.findByParentAndActivate(productCategory, ActivateEnum.ACTIVATE);
		List<Category> categories = new ArrayList<Category>();
		productCategories.stream().map(r -> {
			Category category = new Category(r.getId(), r.getName());
			return category;
		}).forEach(i -> categories.add(i));
		return categories;
	}

	public class Category {
		private Long id;
		private String name;

		public Category(Long id, String name) {
			this.id = id;
			this.name = name;
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
	}

	@Override
	public List<ProductCategoryJson> findByParentJson(ProductCategory pc) {
		return repository.findByParent(pc).stream()
				.map(m -> new ProductCategoryJson(m))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductCategoryDetailJson> findAllDetail() {
		List<ProductCategory> lists = Lists.newArrayList(repository
				.findByParent(null));
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
	public ProductCategory updateState(Long id, ActivateEnum activate) {
		ProductCategory pc = repository.findOne(id);
		pc.setActivate(activate);
		pc.setUpdatedTime(Calendar.getInstance());
		return pc;
	}

	@Override
	public ProductCategory findByIdAndParent(Long id) {
		return repository.findByIdAndActivateAndParentIsNull(id, ActivateEnum.ACTIVATE);
	}
}
