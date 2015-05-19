package com.sj.repository.event;

import org.springframework.context.ApplicationEvent;

import com.sj.model.model.Product;

public class JpaSavedEvent extends ApplicationEvent {
	private Class<? extends Product> cls;

	public JpaSavedEvent(Product source, Class<? extends Product> cls) {
		super(source);
		this.cls = source.getClass();
	}

	public Class<? extends Product> getCls() {
		return cls;
	}

	public void setCls(Class<? extends Product> cls) {
		this.cls = cls;
	}
}
