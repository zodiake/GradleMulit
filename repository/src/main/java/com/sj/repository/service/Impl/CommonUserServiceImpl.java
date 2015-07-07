package com.sj.repository.service.Impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.CommonUser;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.repository.CommonUserRepository;
import com.sj.repository.service.CommonUserService;

@Service
public class CommonUserServiceImpl implements CommonUserService{
	
	@Autowired
	private CommonUserRepository commonUserRepository;
	
	@Override
	public CommonUser create(CommonUser user) {
		user.setCreatedTime(Calendar.getInstance());
		user.setEnabled(ActivateEnum.ACTIVATE); 		//由于测试方便将用户激活，实际中为未激活状态
		user.setSiteAuthority("ROLE_COMMONUSER");
		return commonUserRepository.save(user);
	}

	@Override
	public CommonUser findByName(String name) {
		return commonUserRepository.findByName(name);
	}
}