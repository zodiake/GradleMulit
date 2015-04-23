package com.sj.repository.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj.model.model.Manufacturer;
import com.sj.repository.repository.ManufacturerRepository;
import com.sj.repository.service.ManufacturerService;

@Service
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService{
	@Autowired
	private ManufacturerRepository repository;

	public Manufacturer findOne(int id){
		return repository.findOne(id);
	}
}
