package com.sj.repository.service.Impl;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.BuyProduct;
import com.sj.repository.repository.BuyProductRepository;
import com.sj.repository.service.BuyProductService;
@Service
@Transactional
public class BuyProductServiceImpl implements BuyProductService{

	@Autowired
	private BuyProductRepository repository;
	@Override
	public Iterable<BuyProduct> save(Set<BuyProduct> products) {
		return repository.save(products);
	}

}
