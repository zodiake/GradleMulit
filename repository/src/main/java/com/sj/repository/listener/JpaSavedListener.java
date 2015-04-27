package com.sj.repository.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj.repository.event.JpaSavedEvent;

@Component
public class JpaSavedListener implements ApplicationListener<JpaSavedEvent>{

	@Override
	public void onApplicationEvent(JpaSavedEvent event) {
		System.out.println("------------------");
	}

}
