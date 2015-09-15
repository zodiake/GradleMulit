package com.sj.repository.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.Reagents;
import com.sj.model.model.Solution;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.repository.ReagentsRepository;
import com.sj.repository.service.ReagentsService;

@Service
@Transactional
public class ReagentsServiceImpl implements ReagentsService {
	@Autowired
	private ReagentsRepository repository;

	@Override
	public Reagents saveNoPublisher(Reagents reagents) {
		reagents.setCreatedTime(Calendar.getInstance());
		reagents.setStatus(ProductStatusEnum.EXAMINE);
		reagents.setSolutions(new ArrayList<Solution>());
		return repository.save(reagents);
	}

	@Override
	public Reagents updateNoPublisher(Reagents reagents) {
		Reagents source = repository.findOne(reagents.getId());
		if (source.getStatus() == ProductStatusEnum.UP)
			source.setStatus(ProductStatusEnum.EXAMINE);
		Reagents result = repository.save(bindNoPublisher(source, reagents));
		return repository.save(result);
	}

	private Reagents bindNoPublisher(Reagents old, Reagents newTarget) {
		old.setCoverImg(newTarget.getCoverImg());
		old.setModel(newTarget.getModel());
		old.setSpecifications(newTarget.getSpecifications());
		old.setLabel(newTarget.getLabel());
		old.setBrand(newTarget.getBrand());
		old.setSecondCategory(newTarget.getSecondCategory());
		old.setThirdCategory(newTarget.getThirdCategory());
		old.setName(newTarget.getName());
		old.setPrice(newTarget.getPrice());
		old.setContent(newTarget.getContent());
		return old;
	}
}
