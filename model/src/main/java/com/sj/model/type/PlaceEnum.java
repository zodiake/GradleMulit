package com.sj.model.type;

import java.util.Locale;

public enum PlaceEnum {
	DOMESTIC{public String getName(){return "国产";}}, 
	IMPORTED{public String getName(){return "进口";}},
	OTHER{public String getName(){return "";}};
	
	public abstract String getName();
	public static PlaceEnum formString(String source) {
		try {
			return PlaceEnum.valueOf(source.toUpperCase(Locale.US));
		} catch (Exception e) {
			throw new IllegalArgumentException(
					String.format(
							"Invalid value '%s' for orders given! Has to be either 'index' (case insensitive).",
							source), e);
		}
	}
}
