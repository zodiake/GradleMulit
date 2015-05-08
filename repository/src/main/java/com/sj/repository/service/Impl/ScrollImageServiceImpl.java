package com.sj.repository.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.sj.model.model.ScrollImage;
import com.sj.repository.repository.ScrollImageRepository;
import com.sj.repository.service.ScrollImageService;

@Service
public class ScrollImageServiceImpl implements ScrollImageService {
	@Autowired
	private ScrollImageRepository repository;

	@Override
	public List<ScrollImage> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

}
