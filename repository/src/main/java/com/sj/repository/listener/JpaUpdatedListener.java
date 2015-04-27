package com.sj.repository.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj.model.model.Product;
import com.sj.repository.event.JpaUpdatedEvent;

@Component
public class JpaUpdatedListener implements ApplicationListener<JpaUpdatedEvent>{

	@Override
	public void onApplicationEvent(JpaUpdatedEvent event) {
		Product p=(Product)event.getSource();
	}

}
