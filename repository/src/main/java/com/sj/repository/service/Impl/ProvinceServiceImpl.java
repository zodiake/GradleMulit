package com.sj.repository.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Province;
import com.sj.repository.repository.ProvinceRepository;
import com.sj.repository.service.ProvinceService;
@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService{
	@Autowired
	private ProvinceRepository provinceRepository;

	@Override
	@Cacheable(value = "provinceCache",key="#id")
	public Province findOne(Long id) {
		return provinceRepository.findOne(id);
	}

	@Override
	@Cacheable(value = "provincesCache")
	public Iterable<Province> findAll() {
		return provinceRepository.findAll();
	}

}
