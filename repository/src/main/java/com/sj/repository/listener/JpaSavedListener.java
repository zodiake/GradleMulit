package com.sj.repository.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sj.model.model.Consumable;
import com.sj.model.model.Instrument;
import com.sj.repository.event.JpaSavedEvent;
import com.sj.repository.search.model.InstrumentSearch;
import com.sj.repository.search.service.InstrumentSearchService;

@Component
public class JpaSavedListener implements ApplicationListener<JpaSavedEvent> {
	@Autowired
	private InstrumentSearchService instrumentSearchService;

	@Override
	public void onApplicationEvent(JpaSavedEvent event) {
		Object o = event.getSource();
		Class cls = event.getCls();
		if (cls.equals(Instrument.class)) {
			saveInstrument((Instrument) o);
		} else if (cls.equals(Consumable.class)) {
			saveConsumable((Consumable) o);
		}
	}

	private void saveInstrument(Instrument i) {
		InstrumentSearch search = new InstrumentSearch(i);
		System.out.println(search);
	}

	private void saveConsumable(Consumable c) {

	}

}
