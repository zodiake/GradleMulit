package com.sj.repository.service;

import com.sj.model.model.Solution;

public interface SolutionService {
	public void delete(Long id);

	public void updateName(Long id, String name);

	public void save(Solution s);
}
