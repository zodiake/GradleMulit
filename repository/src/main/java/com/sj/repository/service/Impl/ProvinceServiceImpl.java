package com.sj.repository.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.Province;
import com.sj.repository.repository.ProvinceRepository;
import com.sj.repository.service.ProvinceService;
@Service
public class ProvinceServiceImpl implements ProvinceService{
	@Autowired
	private ProvinceRepository provinceRepository;

	@Override
	public Province findOne(Long id) {
		return provinceRepository.findOne(id);
	}

	@Override
	public Iterable<Province> findAll() {
		return provinceRepository.findAll();
	}

}
