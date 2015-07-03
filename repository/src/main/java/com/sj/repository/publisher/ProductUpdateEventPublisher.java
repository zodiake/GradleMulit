package com.sj.repository.publisher;

import org.springframework.stereotype.Component;

import com.sj.repository.event.JpaUpdatedEvent;
import com.sj.repository.search.model.ProductSearch;

@Component
public class ProductUpdateEventPublisher extends
		AbstractBasicEventPublisher<ProductSearch> {

	@Override
	public void publish(ProductSearch p) {
		this.publisher.publishEvent(new JpaUpdatedEvent(p));
	}

}
