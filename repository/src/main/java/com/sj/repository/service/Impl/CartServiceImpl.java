package com.sj.repository.service.Impl;

import org.springframework.stereotype.Service;

import com.sj.model.model.Cart;
import com.sj.model.model.CartLine;
import com.sj.repository.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Override
	public Cart addCartLine(Cart cart,CartLine line) {
		return null;
	}

	@Override
	public boolean contains(Cart cart, CartLine line) {
		return false;
	}

}
