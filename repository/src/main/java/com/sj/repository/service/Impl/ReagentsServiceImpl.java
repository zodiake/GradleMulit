package com.sj.repository.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.Reagents;
import com.sj.repository.repository.ReagentsRepository;
import com.sj.repository.service.ReagentsService;

@Service
@Transactional
public class ReagentsServiceImpl implements ReagentsService {
	@Autowired
	private ReagentsRepository repository;

	@Override
	public Reagents saveNoPublisher(Reagents reagents) {
		// TODO Auto-generated method stub
		return repository.save(reagents);
	}

}
