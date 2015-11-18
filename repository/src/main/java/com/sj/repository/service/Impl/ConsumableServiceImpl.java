package com.sj.repository.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.sj.model.model.Solution;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.model.ProductJson;
import com.sj.repository.repository.ConsumableRepository;
import com.sj.repository.repository.ProductSearchRepository;
import com.sj.repository.service.ConsumableService;

@Service
@Transactional
public class ConsumableServiceImpl implements ConsumableService {
	@Autowired
	private ConsumableRepository repository;
	@Autowired
	private ProductSearchRepository searchRepository;

	@Autowired
	private EntityManager em;

	@Override
	public Consumable saveNoPublisher(Consumable consumable) {
		consumable.setCreatedTime(Calendar.getInstance());
		consumable.setStatus(ProductStatusEnum.EXAMINE);
		consumable.setSolutions(new ArrayList<Solution>());
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

	@Override
	public Consumable updateNoPublisher(Consumable consumable) {
		Consumable source = repository.findOne(consumable.getId());
		if (source.getStatus().toString()
				.equals(ProductStatusEnum.UP.toString())){
			source.setStatus(ProductStatusEnum.EXAMINE);
			searchRepository.delete(consumable.getId());
		}
		Consumable result = repository
				.save(bindNoPublisher(source, consumable));
		return repository.save(result);
	}

	private Consumable bindNoPublisher(Consumable old, Consumable newTarget) {
		old.setCoverImg(newTarget.getCoverImg());
		old.setModel(newTarget.getModel());
		old.setSpecifications(newTarget.getSpecifications());
		old.setLabel(newTarget.getLabel());
		old.setBrand(newTarget.getBrand());
		old.setSecondCategory(newTarget.getSecondCategory());
		old.setThirdCategory(newTarget.getThirdCategory());
		old.setName(newTarget.getName());
		old.setPrice(newTarget.getPrice());
		old.setContent(newTarget.getContent());
		return old;
	}
}
