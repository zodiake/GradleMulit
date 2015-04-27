package com.sj.repository.event;

import org.springframework.context.ApplicationEvent;

import com.sj.model.model.Product;

public class JpaSavedEvent extends ApplicationEvent {

	public JpaSavedEvent(Product source) {
		super(source);
	}

}
