package com.sj.repository.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.BuyRecord;
import com.sj.model.model.CartLine;
import com.sj.model.model.CommonUser;

public interface BuyRecordService {
	public BuyRecord save(BuyRecord buyRecord,Set<CartLine> lines);

	public Page<BuyRecord> findPage(CommonUser user, Pageable pageable);
	
	public BuyRecord findOne(Long id,CommonUser user);
	
	public void deleteOne(Long id);
	
	public BuyRecord update(CommonUser user,BuyRecord buy);
	
	public BuyRecord findByNoId(String noId);
}
