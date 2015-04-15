package com.sj.repository.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

}
