package com.sj.repository.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.Solution;
import com.sj.repository.model.SolutionJson;
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
	public Solution save(Solution s) {
		Solution so = new Solution();
		so.setName(s.getName());
		so.setSubject(s.getSubject());
		return repository.save(so);
	}

	@Override
	public List<SolutionJson> findAll() {
		List<Solution> solutions = Lists.newArrayList(repository.findAll());
		return solutions.stream().map(i -> new SolutionJson(i))
				.collect(Collectors.toList());
	}
}
