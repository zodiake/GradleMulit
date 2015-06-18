package com.sj.repository.event;

import org.springframework.context.ApplicationEvent;

import com.sj.repository.search.model.ProductSearch;

public class JpaSavedEvent extends ApplicationEvent {
	public JpaSavedEvent(ProductSearch source) {
		super(source);
	}
}
