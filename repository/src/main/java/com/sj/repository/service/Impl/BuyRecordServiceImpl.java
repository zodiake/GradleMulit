package com.sj.repository.service.Impl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.BuyProduct;
import com.sj.model.model.BuyRecord;
import com.sj.model.model.CartLine;
import com.sj.model.model.CommonUser;
import com.sj.model.model.Product;
import com.sj.repository.repository.BuyProductRepository;
import com.sj.repository.repository.BuyRecordRepository;
import com.sj.repository.service.BuyRecordService;
import com.sj.repository.service.CartLineService;
import com.sj.repository.service.CartService;

@Service
@Transactional
public class BuyRecordServiceImpl implements BuyRecordService {
	@Autowired
	private BuyRecordRepository buyRecordRepository;
	@Autowired
	private BuyProductRepository buyProductRepository;
	@Autowired
	private CartLineService cartLineService;

	@Override
	public BuyRecord save(BuyRecord buyRecord, Set<CartLine> lines) {
		buyRecord.setNoId(Calendar.getInstance().getTime().getTime() + "");
		buyRecord.setCreateTime(Calendar.getInstance());
		buyRecord.setArrivalTime(Calendar.getInstance());
		Set<BuyProduct> products = new HashSet<BuyProduct>();
		float totalPrice = 0f;
		for (CartLine cartLine : lines) {
			BuyProduct buyProduct = new BuyProduct();
			buyProduct.setBuy(buyRecord);
			buyProduct.setNumber(cartLine.getNumber());
			buyProduct.setProduct(new Product(cartLine.getProductId()));
			products.add(buyProduct);
			totalPrice = totalPrice + cartLine.getPrice()* cartLine.getNumber();
			cartLineService.remove(buyRecord.getUser().getId(), cartLine.getId());
		}
		buyRecord.setProducts(products);
		buyRecord.setPrice(totalPrice);
		return buyRecordRepository.save(buyRecord);
	}

	@Override
	public Page<BuyRecord> findPage(CommonUser user, Pageable pageable) {
		return buyRecordRepository.findByUser(user, pageable);
	}

	@Override
	public BuyRecord findOne(Long id, CommonUser user) {
		return buyRecordRepository.findByIdAndUser(id, user);
	}

	@Override
	public void deleteOne(Long id) {
		BuyRecord buy = buyRecordRepository.findOne(id);
		buyRecordRepository.delete(buy);
	}

	@Override
	public BuyRecord update(CommonUser user, BuyRecord newBuy) {
		BuyRecord old = buyRecordRepository.findByIdAndUser(newBuy.getId(),
				user);

		return buyRecordRepository.save(bind(old, newBuy));
	}

	public BuyRecord bind(BuyRecord old, BuyRecord newBuy) {
		old.setName(newBuy.getName());
		old.setFundCategory(newBuy.getFundCategory());
		old.setReason(newBuy.getReason());
		// old.setArrivalTime(newBuy.getArrivalTime());
		return old;
	}

}
