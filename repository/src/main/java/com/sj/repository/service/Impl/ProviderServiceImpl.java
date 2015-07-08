package com.sj.repository.service.Impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.Provider;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.repository.ProviderRepository;
import com.sj.repository.service.ProviderService;
import com.sj.repository.util.UpImageUtil;

@Service
public class ProviderServiceImpl implements ProviderService {
	@Autowired
	private ProviderRepository repository;

	private static final String BUSINESSLICENSE = "businesslicense";
	private static final String TAXREQISTRATION = "taxreqistration";
	private static final String STRUCTURECODE = "structurecode";

	@Override
	public Provider findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Provider create(Provider provider) {
		// 添加非用户输入的内容
		provider.setEnabled(ActivateEnum.ACTIVATE); // 测试需要将用户激活
		// 添加权限
		provider.setSiteAuthority("ROLE_PROVIDER");
		provider.setCreatedTime(Calendar.getInstance());
		provider.setBusinessLicenseUrl(UpImageUtil.saveImage(
				provider.getBusinessLicenseImager(), provider.getName(),
				BUSINESSLICENSE));
		provider.setTaxRegistrationUrl(UpImageUtil.saveImage(
				provider.getTaxReqistrationImager(), provider.getName(),
				TAXREQISTRATION));
		provider.setStructureCodeUrl(UpImageUtil.saveImage(
				provider.getStructureCodeImager(), provider.getName(),
				STRUCTURECODE));
		Provider p = repository.save(provider);
		System.out.println(p.getBusinessLicenseUrl());
		System.out.println(p.getTaxRegistrationUrl());
		System.out.println(p.getStructureCodeUrl());
		return p;
	}

	@Override
	public Provider findByName(String name) {
		return repository.findByName(name);
	}
}
