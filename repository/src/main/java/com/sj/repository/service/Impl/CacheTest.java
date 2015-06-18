package com.sj.repository.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

public class CacheTest {

	public List<Integer> lists = new ArrayList<Integer>();
	public void init() {
		for (int i = 0; i < 10; i++) {
			Integer in = Integer.valueOf(i);
			lists.add(in);
		}
	}
	@Cacheable(value="lists",key="category")
	public List<Integer> getList(){
		return lists;
	}
	@Caching(evict = @CacheEvict(value = "lists", key = "category"), put = @CachePut(value = "list", key = "category"))
	public List<Integer> update(Integer i){
		lists.set(lists.size()-1, i);
		return lists;
	}
}
