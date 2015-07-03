package com.sj.repository.event;

import org.springframework.context.ApplicationEvent;

import com.sj.repository.search.model.ProductSearch;

public class JpaUpdatedEvent extends ApplicationEvent {

	public JpaUpdatedEvent(ProductSearch product) {
		super(product);
	}

}
