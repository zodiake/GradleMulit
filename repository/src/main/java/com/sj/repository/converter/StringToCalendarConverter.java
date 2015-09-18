package com.sj.repository.converter;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.core.convert.converter.Converter;

public class StringToCalendarConverter implements
		Converter<String, Calendar> {

	@Override
	public Calendar convert(String source) {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(source));
			return calendar;
		} catch (Exception e) {
			return null;
		}
	}
}
