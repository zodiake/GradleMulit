package com.sj.repository.service.Impl;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Provider;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.ProviderJson;
import com.sj.repository.repository.ProviderRepository;
import com.sj.repository.service.ProviderService;

@Service
@Transactional
public class ProviderServiceImpl implements ProviderService {
	@Autowired
	private ProviderRepository repository;
	@Autowired
	private EntityManager em;

	@Override
	public Provider findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Provider create(Provider provider) {
		provider.setEnabled(ActivateEnum.ACTIVATE);
		provider.setSiteAuthority("ROLE_PROVIDER");
		Calendar c = Calendar.getInstance();
		provider.setCreateTime(c);
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
		p.setIndustryInfo(provider.getIndustryInfo());
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

	@Override
	public Page<ProviderJson> toJson(Pageable pageable, ActivateEnum activate) {
		Page<Provider> pages = findAllDescAndStatus(pageable, activate);
		List<ProviderJson> lists = pages.getContent().stream()
				.map(c -> new ProviderJson(c)).collect(Collectors.toList());
		return new PageImpl<ProviderJson>(lists, pageable,
				pages.getTotalElements());
	}

	@Override
	public void authentic(Long id) {
		// TODO Auto-generated method stub
		em.createQuery(
				"update Provider p set p.isAuthenticated=:state where id=:id")
				.setParameter("state", 1).setParameter("id", id)
				.executeUpdate();
	}
}
