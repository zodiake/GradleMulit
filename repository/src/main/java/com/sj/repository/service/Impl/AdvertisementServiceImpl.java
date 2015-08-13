package com.sj.repository.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Advertisement;
import com.sj.model.model.AdvertismentCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.repository.AdvertisementRepository;
import com.sj.repository.service.AdvertisementService;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	private AdvertisementRepository repository;

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
	public Advertisement findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Advertisement update(Advertisement advertisement, Advertisement adv) {
		adv.setUrl(advertisement.getUrl());
		return null;
	}

	@Override
	public Advertisement updateStatus(Advertisement adv) {
		if (adv.getActivate() == ActivateEnum.ACTIVATE) {
			List<Advertisement> advs = repository.findByActivateAndCategory(
					ActivateEnum.ACTIVATE, adv.getCategory());
			if (advs == null || advs.size() == 0) {
			} else {
				for (Advertisement advertisement : advs) {
					advertisement.setActivate(ActivateEnum.DEACTIVATE);
					repository.save(advertisement);
				}
			}
		}
		return repository.save(adv);
	}

	@Override
	public Advertisement save(Advertisement advertisement) {
		return repository.save(advertisement);
	}

	@Override
	public boolean findByActivate(AdvertismentCategory category) {
		List<Advertisement> advs = repository.findByActivateAndCategory(
				ActivateEnum.ACTIVATE, category);
		if (advs.size() == 0) {
			return true;
		}
		return false;
	}
}
