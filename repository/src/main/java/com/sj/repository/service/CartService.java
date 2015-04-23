package com.sj.repository.service;

import com.sj.model.model.Cart;
import com.sj.model.model.CartLine;

public interface CartService {
	Cart addCartLine(Cart cart,CartLine line);
	boolean contains(Cart cart,CartLine line);
}
