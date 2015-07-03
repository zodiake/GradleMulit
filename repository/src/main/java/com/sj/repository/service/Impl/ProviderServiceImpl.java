package com.sj.repository.service.Impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.Provider;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.repository.ProviderRepository;
import com.sj.repository.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService{
	@Autowired
	private ProviderRepository repository;

	@Override
	public Provider findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Provider regirectedProvider(Provider provider) {
		//添加非用户输入的内容
		provider.setEnabled(ActivateEnum.DEACTIVATE);
		//添加权限
		provider.setSiteAuthority("");
		provider.setCreatedTime(Calendar.getInstance());
		return repository.save(provider);
	}

}
