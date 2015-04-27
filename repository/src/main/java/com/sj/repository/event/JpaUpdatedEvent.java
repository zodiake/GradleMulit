package com.sj.repository.event;

import org.springframework.context.ApplicationEvent;

import com.sj.model.model.Product;

public class JpaUpdatedEvent extends ApplicationEvent {

	public JpaUpdatedEvent(Product product) {
		super(product);
	}

}
