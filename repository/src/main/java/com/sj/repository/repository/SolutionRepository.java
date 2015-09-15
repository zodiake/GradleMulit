package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sj.model.model.Solution;
import com.sj.model.type.ActivateEnum;

public interface SolutionRepository extends CrudRepository<Solution, Long> {
	List<Solution> findByActive(ActivateEnum active);
}
