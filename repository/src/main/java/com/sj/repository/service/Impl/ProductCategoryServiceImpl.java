package com.sj.repository.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;
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
	public Set<ProductCategory> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<ProductCategory> findByParent(Pageable pageable,
			ProductCategory category) {
		return repository.findByParent(pageable, category);
	}

	@Override
	public ProductCategory save(ProductCategory category) {
		category.setActivate(ActivateEnum.ACTIVATE);
		category.setCreatedTime(Calendar.getInstance());
		return repository.save(category);
	}

	@Override
	public ProductCategory findByIdAndParent(Long id, ProductCategory category) {
		return repository.findByIdAndParent(id, category);
	}

	@Override
	public ProductCategory update(ProductCategory category) {
		ProductCategory memory = repository.findOne(category.getId());
		memory.setName(category.getName());
		memory.setActivate(category.getActivate());
		memory.setUpdatedBy(category.getUpdatedBy());
		memory.setUpdatedTime(Calendar.getInstance());
		return repository.save(memory);
	}

	@Override
	public List<ProductCategory> findByParentAndActivate(
			ProductCategory category, ActivateEnum activate) {
		return repository.findByParentAndActivate(category, activate);
	}

	@Override
	@Cacheable(value = "productCategoryCache")
	public List<ProductCategory> findAllSecondCategory(ActivateEnum activate) {
		List<ProductCategory> categories = repository.findByParentAndActivate(
				null, activate);
		List<ProductCategory> results = new LinkedList<>();
		categories.stream().forEach(
				(c) -> c.getCategories().forEach((i) -> results.add(i)));
		return results;
	}

	@Override
	@Cacheable(value = "productCategoryCache")
	public List<ProductCategory> findAllFirstCategory(ActivateEnum activate) {
		return repository.findByParentIsNullAndActivate(activate);
	}

	@Override
	public ProductCategory findByName(String name) {
		return repository.findByName(name,ActivateEnum.ACTIVATE);
	}

	@Override
	@Cacheable(value = "productCategoryCache", key = "#category.id")
	public List<ProductCategory> findByParent(ProductCategory category) {
		return repository.findByParentAndActivate(category,
				ActivateEnum.ACTIVATE);
	}

	@Override
	public List<ProductCategory> findByYQ() {

		return repository.findByParentAndActivate(new ProductCategory(1l),
				ActivateEnum.ACTIVATE);
	}

	@Override
	public void delete(Long id) {
		ProductCategory pc = repository.findOne(id);
		if (pc.getCategories() == null || pc.getCategories().size() == 0) {

		} else {
			Set<ProductCategory> pcs = pc.getCategories();
			for (ProductCategory productCategory : pcs) {
				repository.delete(productCategory);
			}
		}
		repository.delete(id);
	}

	@Override
	public List<List<ProductCategory>> findByShowOnIndex() {
		List<List<ProductCategory>> pcs = new ArrayList<List<ProductCategory>>();
		List<ProductCategory> yqs = findByParent(new ProductCategory(1l));
		List<ProductCategory> shs = findByParent(new ProductCategory(2l));
		List<ProductCategory> hcs = findByParent(new ProductCategory(3l));
		List<ProductCategory> fws = findByParent(new ProductCategory(4l));
		pcs.add(yqs);
		pcs.add(shs);
		pcs.add(hcs);
		pcs.add(fws);
		return pcs;
	}

	@Override
	public ProductCategory findByNameAndParentAndState(String child,
			ProductCategory parent, ActivateEnum status) {
		return repository.findByNameAndParentAndActivate(child, parent, status);
	}

	@Override
	public ProductCategory findOneActivate(Long id) {
		return repository.findByIdAndActivate(id, ActivateEnum.ACTIVATE);
	}

}
