package com.sj.repository.service.Impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.CommonUser;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.repository.CommonUserRepository;
import com.sj.repository.service.CommonUserService;

@Service
public class CommonUserServiceImpl implements CommonUserService {

	@Autowired
	private CommonUserRepository commonUserRepository;

	@Override
	public CommonUser create(CommonUser user) {
		user.setCreatedTime(Calendar.getInstance());
		user.setEnabled(ActivateEnum.ACTIVATE);
		user.setSiteAuthority("ROLE_COMMONUSER");
		return commonUserRepository.save(user);
	}

	@Override
	public CommonUser findByName(String name) {
		return commonUserRepository.findByName(name);
	}

	@Override
	public Page<CommonUser> findAll(Pageable pageable) {

		return commonUserRepository.findAll(pageable);
	}

	@Override
	public CommonUser findOne(Long id) {
		return commonUserRepository.findOne(id);
	}

	@Override
	public CommonUser update(CommonUser user) {
		CommonUser u = commonUserRepository.findOne(user.getId());
		u.setIndustryInfo(user.getIndustryInfo());
		u.setCompany(user.getCompany());
		u.setDepartment(user.getDepartment());
		u.setTitle(user.getTitle());
		u.setCompanyPhone(user.getCompanyPhone());
		u.setFax(user.getFax());
		u.setCode(user.getCode());
		u.setAddress(user.getAddress());
		return commonUserRepository.save(u);
	}
}
