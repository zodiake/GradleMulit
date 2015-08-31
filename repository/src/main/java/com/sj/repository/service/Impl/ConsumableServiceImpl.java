package com.sj.repository.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Consumable;
import com.sj.model.model.ProductCategory;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.model.ProductJson;
import com.sj.repository.repository.ConsumableRepository;
import com.sj.repository.service.ConsumableService;

@Service
@Transactional
public class ConsumableServiceImpl implements ConsumableService {
	@Autowired
	private ConsumableRepository repository;

	@Autowired
	private EntityManager em;

	@Override
	public Consumable saveNoPublisher(Consumable consumable) {
		return repository.save(consumable);
	}

	@Override
	public Page<ProductJson> findByFirstCategoryAndSecondCategoryAndStatusJson(
			Pageable pageable, ProductCategory fc, ProductCategory sc,
			ProductStatusEnum status) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Consumable> c = cb.createQuery(Consumable.class);
		Root<Consumable> consumable = c.from(Consumable.class);
		c.select(consumable);

		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Consumable.class)));

		if (fc != null) {
			c.where(cb.equal(consumable.get("firstCategory"), fc));
			cq.where(cb.equal(consumable.get("firstCategory"), fc));
		}
		if (sc != null) {
			c.where(cb.equal(consumable.get("secondCategory"), sc));
			cq.where(cb.equal(consumable.get("secondCategory"), sc));
		}
		if (status != null) {
			c.where(cb.equal(consumable.get("status"), status));
			cq.where(cb.equal(consumable.get("status"), status));
		}
		List<Consumable> lists = em
				.createQuery(c)
				.setFirstResult(
						(pageable.getPageNumber() - 1) * pageable.getPageSize())
				.setMaxResults(
						pageable.getPageNumber() * pageable.getPageSize())
				.getResultList();
		List<ProductJson> products = lists.stream()
				.map(m -> new ProductJson(m)).collect(Collectors.toList());

		Long count = em.createQuery(cq).getSingleResult();
		return new PageImpl<ProductJson>(products, pageable, count);
	}
}
