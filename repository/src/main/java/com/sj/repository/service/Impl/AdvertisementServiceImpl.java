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

import com.sj.model.model.Advertisement;
import com.sj.model.model.AdvertisementCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.AdvertisementJson;
import com.sj.repository.repository.AdvertisementRepository;
import com.sj.repository.service.AdvertisementService;

@Service
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService {
	@Autowired
	private AdvertisementRepository repository;
	@Autowired
	private EntityManager em;

	@Override
	public Page<Advertisement> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Advertisement> findByActivate(Pageable pageable,
			ActivateEnum activate) {
		return repository.findByActivate(pageable, activate);
	}

	@Override
	public Page<AdvertisementJson> findAllJson(Pageable pageable,
			ActivateEnum activate) {
		Page<Advertisement> pages;
		if (activate != null)
			pages = repository.findByActivateOrderByUpdatedTimeDesc(pageable,
					activate);
		else
			pages = repository.findByOrderByUpdatedTimeDesc(pageable);
		List<AdvertisementJson> lists = pages.getContent().stream()
				.map(c -> new AdvertisementJson(c))
				.collect(Collectors.toList());
		return new PageImpl<AdvertisementJson>(lists, pageable,
				pages.getTotalElements());

	}

	@Override
	public Advertisement findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void update(Advertisement advertisement) {
		em.createQuery(
				"update Advertisement a set a.category=:category,a.coverImg=:coverImg,a.url=:url,a.updatedTime=:updatedTime where a.id=:id")
				.setParameter("category", advertisement.getCategory())
				.setParameter("coverImg", advertisement.getCoverImg())
				.setParameter("url", advertisement.getUrl())
				.setParameter("id", advertisement.getId())
				.setParameter("updatedTime", Calendar.getInstance())
				.executeUpdate();
	}

	@Override
	public Advertisement updateStatus(Long id, ActivateEnum state) {
		Advertisement adv = repository.findOne(id);
		adv.setActivate(state);
		return adv;
	}

	@Override
	public Advertisement save(Advertisement advertisement) {
		advertisement.setUpdatedTime(Calendar.getInstance());
		advertisement.setCreatedTime(Calendar.getInstance());
		return repository.save(advertisement);
	}

	@Override
	public boolean findByActivate(AdvertisementCategory category) {
		List<Advertisement> advs = repository.findByActivateAndCategory(
				ActivateEnum.ACTIVATE, category);
		if (advs.size() == 0) {
			return true;
		}
		return false;
	}
}
