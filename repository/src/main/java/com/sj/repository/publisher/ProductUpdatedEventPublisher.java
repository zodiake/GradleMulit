package com.sj.repository.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.sj.model.model.Product;
import com.sj.repository.event.JpaSavedEvent;

public class ProductUpdatedEventPublisher implements
		ApplicationEventPublisherAware {

	@Autowired
	private ApplicationEventPublisher publisher;

	@Override
	public void setApplicationEventPublisher(
			ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	public void publish(Product p) {
		this.publisher.publishEvent(new JpaSavedEvent(p));
	}
}
