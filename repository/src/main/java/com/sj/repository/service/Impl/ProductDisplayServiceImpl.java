package com.sj.repository.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Product;
import com.sj.model.model.ProductDisplay;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.ProductDisplayJson;
import com.sj.repository.repository.ProductDisplayRepostiory;
import com.sj.repository.service.ProductDisplayService;

@Service
@Transactional
public class ProductDisplayServiceImpl implements ProductDisplayService{

	@Autowired
	private ProductDisplayRepostiory repository;
	
	@Override
	@Cacheable(value="ProductDisplayCache")
	public List<ProductDisplay> findAll() {
		return repository.findByState(ActivateEnum.ACTIVATE);
	}

	@Override
	public Page<ProductDisplayJson> findAllJson(Pageable pageable) {
		Page<ProductDisplay> displays = repository.findAll(pageable);
		List<ProductDisplayJson> jsons = new ArrayList<ProductDisplayJson>();
		for (ProductDisplay productDisplay : displays) {
			ProductDisplayJson json = new ProductDisplayJson(productDisplay);
			jsons.add(json);
		}
		return new PageImpl<ProductDisplayJson>(jsons, pageable, displays.getTotalElements());
	}

	@Override
	@CacheEvict(value = "ProductDisplayCache",allEntries = true)
	public String updateState(Long id, ActivateEnum activate) {
		if(activate.equals(ActivateEnum.ACTIVATE)){
			ProductDisplay pd = repository.findOne(id);
			pd.setState(ActivateEnum.DEACTIVATE);
		}else{
			Long count = repository.countByState(ActivateEnum.ACTIVATE);
			if(count>=8){
				return "limit";
			}
			ProductDisplay pd = repository.findOne(id);
			pd.setState(ActivateEnum.ACTIVATE);
		}
		return "success";
	}

	@Override
	@CacheEvict(value = "ProductDisplayCache",allEntries = true)
	public String save(Long productId) {
		Long count = repository.countByState(ActivateEnum.ACTIVATE);
		if(count>=8){
			return "limit";
		}
		ProductDisplay pd = new ProductDisplay();
		pd.setProduct(new Product(productId));
		pd.setCreatedTime(Calendar.getInstance());
		pd.setState(ActivateEnum.ACTIVATE);
		repository.save(pd);
		return "success";
	}

}
