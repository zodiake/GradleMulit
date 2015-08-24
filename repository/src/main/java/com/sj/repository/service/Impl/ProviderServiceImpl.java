package com.sj.repository.service.Impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
		Calendar c = Calendar.getInstance();
		provider.setCreatedTime(c);
		provider.setBusinessLicenseUrl(UpImageUtil.saveImage(
				provider.getBusinessLicenseImager(),
				provider.getName(),
				BUSINESSLICENSE
						+ StringUtils.trimAllWhitespace(provider
								.getBusinessLicenseImager()
								.getOriginalFilename()
								.substring(
										provider.getBusinessLicenseImager()
												.getOriginalFilename()
												.lastIndexOf("."))),
				UpImageUtil.PROVIDERBASE));
		provider.setTaxRegistrationUrl(UpImageUtil.saveImage(
				provider.getTaxReqistrationImager(),
				provider.getName(),
				TAXREQISTRATION
						+ StringUtils.trimAllWhitespace(provider
								.getTaxReqistrationImager()
								.getOriginalFilename()
								.substring(
										provider.getTaxReqistrationImager()
												.getOriginalFilename()
												.lastIndexOf("."))),
				UpImageUtil.PROVIDERBASE));
		provider.setStructureCodeUrl(UpImageUtil.saveImage(
				provider.getStructureCodeImager(),
				provider.getName(),
				STRUCTURECODE
						+ StringUtils.trimAllWhitespace(provider
								.getStructureCodeImager()
								.getOriginalFilename()
								.substring(
										provider.getStructureCodeImager()
												.getOriginalFilename()
												.lastIndexOf("."))),
				UpImageUtil.PROVIDERBASE));
		return repository.save(provider);
	}

	@Override
	public Provider findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Page<Provider> findAllDescAndStatus(Pageable pageable,
			ActivateEnum activate) {
		if (activate == null)
			return repository.findAll(pageable);
		else
			return repository.findByEnabled(pageable, activate);
	}

	@Override
	public Provider checkUser(Provider provider, ActivateEnum activate) {
		provider.setEnabled(activate);
		provider.setAuthenticatedTime(Calendar.getInstance());
		return repository.save(provider);
	}

	@Override
	public Provider updateProvider(Provider provider) {
		Provider p = repository.findOne(provider.getId());
		p.setLegalPerson(provider.getLegalPerson());
		p.setComponyType(provider.getComponyType());
		p.setRegisteredCapital(provider.getRegisteredCapital());
		p.setIndustryInformation(provider.getIndustryInformation());
		p.setMainProduct(provider.getMainProduct());
		p.setBusinessType(provider.getBusinessType());
		p.setOutput(provider.getOutput());
		p.setWebsite(provider.getWebsite());
		p.setProvince(provider.getProvince());
		p.setCity(provider.getCity());
		p.setAddress(provider.getAddress());
		p.setCode(provider.getCode());
		p.setRealName(p.getRealName());
		p.setPosition(provider.getPosition());
		p.setSex(provider.getSex());
		p.setContent(provider.getContent());
		p.setProviderPhone(provider.getProviderPhone());
		p.setFax(provider.getFax());
		return repository.save(p);
	}
}
