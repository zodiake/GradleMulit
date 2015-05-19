package com.sj.repository.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

public abstract class AbstractBasicEventPublisher<T> implements
		ApplicationEventPublisherAware {
	@Autowired
	protected ApplicationEventPublisher publisher;

	@Override
	public void setApplicationEventPublisher(
			ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	public abstract void publish(T p);
}
