package com.sj.repository.service.Impl;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.Solution;
import com.sj.repository.repository.SolutionRepository;
import com.sj.repository.service.SolutionService;

@Service
@Transactional
public class SolutionServiceImpl implements SolutionService {
	@Autowired
	private EntityManager em;
	@Autowired
	private SolutionRepository repository;

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public void updateName(Long id, String name) {
		em.createQuery("update Solution s set s.name=:name where s.id=:id")
				.setParameter("name", name).setParameter("id", id)
				.executeUpdate();
	}

	@Override
	public void save(Solution s) {
		repository.save(s);
	}
}
