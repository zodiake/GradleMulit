package com.sj.repository.publisher;

import org.springframework.stereotype.Component;

import com.sj.model.model.Instrument;
import com.sj.repository.event.JpaSavedEvent;

@Component
public class InstrumentSavedEventPublisher extends
		AbstractBasicEventPublisher<Instrument> {

	@Override
	public void publish(Instrument p) {
		this.publisher.publishEvent(new JpaSavedEvent(p, Instrument.class));
	}
}
