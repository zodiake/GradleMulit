package com.sj.repository.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.BuyRecord;
import com.sj.model.model.CommonUser;
import com.sj.repository.repository.BuyRecordRepository;
import com.sj.repository.service.BuyRecordService;

@Service
@Transactional
public class BuyRecordServiceImpl implements BuyRecordService{
	@Autowired
	private BuyRecordRepository buyRecordRepository;

	@Override
	public BuyRecord save(BuyRecord buyRecord) {
		return buyRecordRepository.save(buyRecord);
	}

	@Override
	public Page<BuyRecord> findPage(CommonUser user ,Pageable pageable) {
		return buyRecordRepository.findByUser(user, pageable);
	}

	@Override
	public BuyRecord findOne(Long id,CommonUser user) {
		return buyRecordRepository.findByIdAndUser(id, user);
	}

	@Override
	public void deleteOne(Long id) {
		buyRecordRepository.delete(new BuyRecord(id));
	}

	@Override
	public BuyRecord update(CommonUser user, BuyRecord newBuy) {
		BuyRecord old = buyRecordRepository.findByIdAndUser(newBuy.getId(), user);
		
		return buyRecordRepository.save(bind(old,newBuy));
	}
	
	public BuyRecord bind(BuyRecord old,BuyRecord newBuy){
		old.setName(newBuy.getName());
		old.setFundCategory(newBuy.getFundCategory());
		old.setReason(newBuy.getReason());
//		old.setArrivalTime(newBuy.getArrivalTime());
		return old;
	}

}
