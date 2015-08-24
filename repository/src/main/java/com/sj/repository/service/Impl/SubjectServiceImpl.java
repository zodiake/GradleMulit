package com.sj.repository.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Subject;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.repository.SubjectRepository;
import com.sj.repository.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	private SubjectRepository repository;

	@Override
	public Page<Subject> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Subject> findByActivated(Pageable pageable,
			ActivateEnum activate) {
		return repository.findByActivateOrderByCreatedTimeDesc(pageable, activate);
	}

	@Override
	public Subject findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Subject save(Subject s) {
		return repository.save(s);
	}

	@Override
	public Subject update(Subject s,Subject old) {
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

}
