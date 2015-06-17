package com.sj.repository.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj.repository.event.JpaUpdatedEvent;
import com.sj.repository.search.model.ProductSearch;
import com.sj.repository.search.service.ProductSearchService;

@Component
public class JpaUpdatedListener implements ApplicationListener<JpaUpdatedEvent> {

	@Autowired
	private ProductSearchService productSearchService;

	@Override
	public void onApplicationEvent(JpaUpdatedEvent event) {
		ProductSearch p = (ProductSearch) event.getSource();
		update(p);
	}

	private void update(ProductSearch p) {
		productSearchService.save(p);
	}
}
