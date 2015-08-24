package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.BuyRecord;
import com.sj.model.model.CommonUser;

public interface BuyRecordRepository extends PagingAndSortingRepository<BuyRecord, Long> {
	
	Page<BuyRecord> findByUser(CommonUser user,Pageable pageable);
}
