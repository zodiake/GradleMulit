package com.sj.repository.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj.repository.event.JpaSavedEvent;
import com.sj.repository.search.model.ProductSearch;
import com.sj.repository.search.service.ProductSearchService;

@Component
public class JpaSavedListener implements ApplicationListener<JpaSavedEvent> {
	@Autowired
	private ProductSearchService productSearchService;

	@Override
	public void onApplicationEvent(JpaSavedEvent event) {
		ProductSearch o = (ProductSearch) event.getSource();
		save(o);
	}

	private void save(ProductSearch p) {
		productSearchService.save(p);
	}

}
