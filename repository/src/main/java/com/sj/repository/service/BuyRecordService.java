package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.BuyRecord;
import com.sj.model.model.CommonUser;

public interface BuyRecordService {
	public BuyRecord save(BuyRecord buyRecord);

	public Page<BuyRecord> findPage(CommonUser user, Pageable pageable);
	
	public BuyRecord findOne(Long id);
	
	public void deleteOne(Long id);
}
