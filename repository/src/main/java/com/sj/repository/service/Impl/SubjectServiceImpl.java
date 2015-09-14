package com.sj.repository.service.Impl;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Subject;
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
		return repository.findByShowOnIndex(ActivateEnum.ACTIVATE);
	}

	@Override
	public Page<SubjectJson> findAllJson(Pageable pageable) {
		Page<Subject> page = repository.findByOrderByUpdatedTimeDesc(pageable);
		List<SubjectJson> list = page.getContent().stream()
				.map(c -> new SubjectJson(c)).collect(Collectors.toList());
		return new PageImpl<SubjectJson>(list, pageable,
				page.getTotalElements());
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

}
