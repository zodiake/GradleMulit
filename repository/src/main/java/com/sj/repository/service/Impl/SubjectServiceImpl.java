package com.sj.repository.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Subject;
import com.sj.model.model.SubjectCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.SubjectDetailJson;
import com.sj.repository.model.SubjectJson;
import com.sj.repository.repository.SubjectRepository;
import com.sj.repository.search.model.SubjectSearch;
import com.sj.repository.search.service.SubjectSearchService;
import com.sj.repository.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	private SubjectRepository repository;
	@Autowired
	private SubjectSearchService service;
	@Autowired
	private EntityManager em;

	@Override
	public Page<Subject> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Subject> findByActivated(Pageable pageable,
			ActivateEnum activate) {
		return repository.findByActivateOrderByCreatedTimeDesc(pageable,
				activate);
	}

	public SubjectDetailJson findOneJson(Long id) {
		Subject s = findOne(id);
		return new SubjectDetailJson(s);
	}

	@Override
	public Subject findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Subject save(Subject s) {
		s.setUpdatedTime(Calendar.getInstance());
		s.setCreatedTime(Calendar.getInstance());
		Subject sub = repository.save(s);
		service.save(new SubjectSearch(sub));
		return sub;
	}

	@Override
	public Subject update(Subject s, Subject old) {
		old.setContent(s.getContent());
		old.setCreatedBy(s.getCreatedBy());
		old.setName(s.getName());
		old.setShowOnIndex(s.getShowOnIndex());
		old.setActivate(s.getActivate());
		return repository.save(old);
	}

	@Override
	@Cacheable(value = "subjectsCache")
	public List<Subject> findByShowOnIndex() {
		return repository.findByShowOnIndexAndActivate(ActivateEnum.ACTIVATE,
				ActivateEnum.ACTIVATE);
	}

	@Override
	public Page<SubjectJson> findByCategoryAndActivateJson(
			ActivateEnum activate, SubjectCategory category, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Subject> c = cb.createQuery(Subject.class);
		Root<Subject> subject = c.from(Subject.class);

		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Subject.class)));

		List<Predicate> criteria = new ArrayList<>();

		if (category != null) {
			criteria.add(cb.equal(subject.get("category"), category));
		}
		if (activate != null) {
			criteria.add(cb.equal(subject.get("activate"), activate));
		}
		c.where(criteria.toArray(new Predicate[0]));
		cq.where(criteria.toArray(new Predicate[0]));

		List<Subject> lists = em
				.createQuery(c)
				.setFirstResult(
						(pageable.getPageNumber()) * pageable.getPageSize())
				.setMaxResults(
						pageable.getPageNumber() * pageable.getPageSize())
				.getResultList();

		List<SubjectJson> subjects = lists.stream()
				.map(s -> new SubjectJson(s)).collect(Collectors.toList());

		Long count = em.createQuery(cq).getSingleResult();
		return new PageImpl<SubjectJson>(subjects, pageable, count);

	}

	@Override
	public Subject update(Subject subject) {
		Subject s = repository.findOne(subject.getId());
		s.setContent(subject.getContent());
		s.setName(subject.getName());
		s.setSummary(subject.getSummary());
		s.setImage(subject.getImage());
		return s;
	}

	@Override
	public Subject updateState(Long id, ActivateEnum active) {
		Subject subject = repository.findOne(id);
		subject.setActivate(active);
		em.createQuery(
				"update Solution s set s.active=:active where s.subject=:subject")
				.setParameter("active", active)
				.setParameter("subject", subject).executeUpdate();
		return subject;
	}

}
