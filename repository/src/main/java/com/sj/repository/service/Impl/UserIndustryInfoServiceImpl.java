package com.sj.repository.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sj.model.model.UserIndustryInfo;
import com.sj.repository.repository.UserIndustryInfoRepository;
import com.sj.repository.service.UserIndustryInfoService;
@Service
@Transactional
public class UserIndustryInfoServiceImpl implements UserIndustryInfoService{

	@Autowired
	private UserIndustryInfoRepository repository;
	@Override
	public List<UserIndustryInfo> findAll() {
		return repository.findAll(new PageRequest(0, 15)).getContent();
	}

}
