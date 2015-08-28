package com.sj.repository.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Consumable;


public interface ConsumableRepository extends PagingAndSortingRepository<Consumable, Long> {
}
