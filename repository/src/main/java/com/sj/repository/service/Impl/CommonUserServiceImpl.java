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

import com.sj.model.model.CommonUser;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.CommonUserJson;
import com.sj.repository.repository.CommonUserRepository;
import com.sj.repository.service.CommonUserService;

@Service
@Transactional
public class CommonUserServiceImpl implements CommonUserService {
	@Autowired
	private CommonUserRepository commonUserRepository;
	@Autowired
	private EntityManager em;

	@Override
	public CommonUser create(CommonUser user) {
		user.setCreateTime(Calendar.getInstance());
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
		u.setProvince(user.getProvince());
		u.setCity(user.getCity());
		u.setIndustryInfo(user.getIndustryInfo());
		u.setCompany(user.getCompany());
		u.setDepartment(user.getDepartment());
		u.setCompanyPhone(user.getCompanyPhone());
		u.setFax(user.getFax());
		u.setCode(user.getCode());
		u.setAddress(user.getAddress());
		return commonUserRepository.save(u);
	}

	@Override
	public Page<CommonUserJson> toJson(Pageable pageable) {
		Page<CommonUser> page = findAll(pageable);
		List<CommonUserJson> lists = page.getContent().stream()
				.map(c -> new CommonUserJson(c)).collect(Collectors.toList());
		PageImpl<CommonUserJson> impl = new PageImpl<CommonUserJson>(lists,
				pageable, page.getTotalElements());
		return impl;
	}

	@Override
	public void updateScore(Long id, int score) {
		em.createQuery("update CommonUser c set c.score=:score where c.id=:id")
				.setParameter("score", score).setParameter("id", id)
				.executeUpdate();
	}
}
