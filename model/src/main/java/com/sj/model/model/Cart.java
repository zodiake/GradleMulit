package com.sj.model.model;

import java.util.Set;

public class Cart {
	Set<CartLine> cartLines;

	public Set<CartLine> getCartLines() {
		return cartLines;
	}

	public void setCartLines(Set<CartLine> cartLines) {
		this.cartLines = cartLines;
	}
}
