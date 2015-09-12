package com.sj.repository.service;

import java.util.List;

import com.sj.model.model.Solution;
import com.sj.repository.model.SolutionJson;

public interface SolutionService {
	public void delete(Long id);

	public void updateName(Long id, String name);

	public Solution save(Solution s);

	public List<SolutionJson> findAll();
}
